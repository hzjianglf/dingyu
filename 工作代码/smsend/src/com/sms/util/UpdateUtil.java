package com.sms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UpdateUtil {
  /*传入父节点ID 递归修改其下所有节点的url  site  添加扩展属性（将url的值放在site 统一赋值url为redirect+该节点ID）*/
public static void main(String[] args) {
	String ids=getIds("","'_RxjFEOtDEeWpOY-suQsCcA'");
	System.out.println(ids.split(",").length);
	System.out.println(ids.subSequence(0, ids.length()-1));
}	

	public static String getIds(String ids,String parentIds){
		String sql ="select id,name,properties,url from ac_resource where parent_id in("+parentIds+")";
		System.out.println(parentIds.split(",").length+"  mmmmmmmmm8888888888888888888");
		System.out.println(sql);
		String childParentsIds="";
		 ConnectionPool pool = null;
	       pool = ConnectionPool.getInstance();
	       Connection conn = pool.getConnection();
	       int count=0;
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);
	           while (rs.next()) {
	           	 System.out.println(rs.getString("id")+" "+rs.getString("Name")+"  "+rs.getString("properties"));
	           	childParentsIds+="'"+rs.getString("id")+"',";
	           	if(null==rs.getString("properties")){
	           		System.out.println("扩展属性为空");
//	           		updateBySql("update ac_resource set site='"+(rs.getString("url")==null?"/jsp/notice/notice.jsp":rs.getString("url"))+"',url='/redirect.html?id="+rs.getString("id")+"',properties='{\"visitCount\":\"0\"}' " +
//           					" where id='"+rs.getString("id")+"'");
	           	}else{
	           		System.out.println("扩展属性为："+rs.getString("properties"));
	           		if(!rs.getString("properties").contains("visitCount")&&!rs.getString("properties").contains("{}")){
	           			System.out.println("没有扩展属性 ：visitCount" +rs.getString("properties"));
	           			System.out.println(rs.getString("properties").replaceAll("}", ",\"visitCount\":\"0\"}"));
//	           			updateBySql("update ac_resource set site='"+(rs.getString("url")==null?"/jsp/notice/notice.jsp":rs.getString("url"))+"',url='/redirect.html?id="+rs.getString("id")+"',properties='"+rs.getString("properties").replaceAll("}", ",\"visitCount\":\"0\"}")+"' " +
//	           					" where id='"+rs.getString("id")+"'");
	           		}
	           		if(rs.getString("properties").contains("{}")){
	           			System.out.println("没有扩展属性 ：visitCount" +rs.getString("properties"));
	           			System.out.println(rs.getString("properties").replaceAll("}", "\"visitCount\":\"0\"}"));
//	           			updateBySql("update ac_resource set site='"+(rs.getString("url")==null?"/jsp/notice/notice.jsp":rs.getString("url"))+"',url='/redirect.html?id="+rs.getString("id")+"',properties='"+rs.getString("properties").replaceAll("}", "\"visitCount\":\"0\"}")+"' " +
//	           					" where id='"+rs.getString("id")+"'");
	           		}
	           	}
	           	 count++;
	           	 if(!ids.contains(rs.getString("id"))){
	           		ids+="'"+rs.getString("id")+"',";
	           	 }
	           }
	           rs.close();
	           stmt.close();
	           System.out.println(count+" 000000000000000----------------------------//////////////");
			} catch (SQLException e) {
				// TODO �Զ���� catch ��
				e.printStackTrace();
			}
			 pool.release(conn);
			 if(count!=0){
				 getIds(ids,childParentsIds.subSequence(0, childParentsIds.length()-1).toString());
			 }
		return ids;
	}
	
	
	public static void updateBySql(String upsql){
		

	       ConnectionPool pool = null;
	           pool = ConnectionPool.getInstance();
	           Connection conn = pool.getConnection();
			   String count="";
	           try {
					Statement stmt = conn.createStatement();
					count=stmt.executeUpdate(upsql)+"";
					System.out.println(count);
		            stmt.close();
				} catch (SQLException e) {
					// TODO �Զ���� catch ��
					e.printStackTrace();
				}
	           pool.release(conn);
		
	
	}
	

}
