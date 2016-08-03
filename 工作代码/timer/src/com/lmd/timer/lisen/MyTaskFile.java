package com.lmd.timer.lisen;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.xml.rpc.ServiceException;

import com.lmd.timer.service.jcg.GsmManagerLocator;
import com.lmd.timer.service.jcg.GsmManagerSoapBindingStub;
import com.lmd.timer.service.rtx.RtxServiceImplServiceLocator;
import com.lmd.timer.service.rtx.RtxServiceImplServiceSoapBindingStub;
import com.lmd.timer.util.ConnectionPool;
import com.lmd.timer.util.TimeUtil;

public class MyTaskFile extends TimerTask {
	@Override
	public void run() {
		try {
			//先查询出所有的符合发送条件的记录
			//发送信息
			//发送完后要修改状态
			String currentTime = TimeUtil.getTime();
//			String currentTime = "2015-12-24 09:12:03";
			String sql="select id, createperson,ismess,istengxt, sendtime,rizhibiaoti,rizhineirong, messcontent from " +
					"info_datelog where issend is null and" +
					"(ceil((To_date('"+currentTime+"' , 'yyyy-mm-dd hh24-mi-ss') - To_date(sendtime , 'yyyy-mm-dd hh24-mi-ss')) * 24 * 60 * 60 * 1000))" +
					" between -300000 and 300000";
			sendMessage(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void sendMessage(String sql) {
	       ConnectionPool pool = null;
	           pool = ConnectionPool.getInstance();
	           Connection conn = pool.getConnection();
				try {
					PreparedStatement stmt = conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("定时启动开始");
		            while (rs.next()) {
		            	System.out.println("开始挨个发送");
		            	String id=rs.getString("id");
		            	String createperson=rs.getString("createperson");
		            	String sendtime=rs.getString("sendtime");
		            	String messcontent=rs.getString("messcontent");
		            	String rizhibiaoti=rs.getString("rizhibiaoti");
		            	String ismess = rs.getString("ismess");
		            	String istengxt = rs.getString("istengxt");
		            	String smStatus="";
		            	String rtxStatus="";
		            	if("0".equals(ismess)){
		            		smStatus=sendSms(createperson, messcontent);
		            		System.out.println("给"+createperson+"发送短信");
		            	}
		            	if("0".equals(istengxt)){
		            		rtxStatus=sendRtx(createperson, rizhibiaoti, messcontent);
		            		System.out.println("给"+createperson+"发送rtx");
		            	}
		            	if("1".equals(smStatus)
		            	|| "success".equals(rtxStatus)){
		            		update(id);
		            	}
		            }
		            System.out.println("定时启动结束");
		            rs.close();
		            stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	           pool.release(conn);
//	       pool.closePool();
	   }
	//根据用户名发送手机短信
	public static String sendSms(String username,String content){
		String smsStatus="";
		try {
			GsmManagerLocator gs = new GsmManagerLocator();
			GsmManagerSoapBindingStub service = (GsmManagerSoapBindingStub) gs.getGsmManagerPort();
			smsStatus=service.sendByLoginName(username, content);
		} catch (ServiceException e) {
			System.out.println("短信发送消息失败");
			e.printStackTrace();
		}catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return smsStatus;
	}
	
	//发送Rtx消息
	
	public static String sendRtx(String username,String title,String content){
		String rtxStatus="";
		try {
			RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
			RtxServiceImplServiceSoapBindingStub service = (RtxServiceImplServiceSoapBindingStub) rs.getRtxServiceImplPort();
			rtxStatus=service.sendNotify(username, title, content, "1", "5000");
		} catch (ServiceException e) {
			// TODO 自动生成的 catch 块
			System.out.println("Rtx发送消息失败");
			e.printStackTrace();
		}
		 catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return rtxStatus;
	}
	
	public static List customSelect(String sql) {
   	 List list = new ArrayList();
       ConnectionPool pool = null;
           pool = ConnectionPool.getInstance();
           Connection conn = pool.getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	            	 Map map = new HashMap();
	            	 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	       	          map.put(rs.getMetaData().getColumnName(i), rs.getString(i));
	       	        }
	       	        list.add(map);
	            }
	            rs.close();
	            stmt.close();
			} catch (SQLException e) {
				// TODO �Զ���� catch ��
				e.printStackTrace();
			}
           pool.release(conn);
//       pool.closePool();
           System.out.println(list.size());
       return list;
   }
	
	public static String update(String id) {
	       ConnectionPool pool = null;
	           pool = ConnectionPool.getInstance();
	           Connection conn = pool.getConnection();
	           String sql="update info_datelog set issend='1' where id='"+id+"'";
			   String count="";
	           try {
					Statement stmt = conn.createStatement();
					count=stmt.executeUpdate(sql)+"";
		            stmt.close();
				} catch (SQLException e) {
					// TODO �Զ���� catch ��
					e.printStackTrace();
				}
	           pool.release(conn);
	           return count;
		
	}


	public static void main(String[] args) {
	}
}