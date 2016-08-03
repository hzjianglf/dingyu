package lmd.res.count.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lmd.res.count.service.QueryManager;

import net.sf.json.JSONArray;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("queryManager")
public class QueryManagerImpl implements QueryManager{
	@Autowired
	  private SessionFactory sessionFactory;
	@Override
	public List<Map> getBySql(String sql) {
		List list = new ArrayList();
		// TODO 自动生成的方法存根
		Session session = this.sessionFactory.openSession();
	      Connection conn = null; 
	  	  Statement  st= null;
	  	  ResultSet rs = null;
	  	try {
	  		conn = session.connection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				 Map map = new HashMap();
            	 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
       	          map.put(rs.getMetaData().getColumnName(i), rs.getString(i));
       	        }
       	        list.add(map);
       	        if(list.size()==10){
       	        	break;
       	        }
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			   try {
					 rs.close();
					 st.close();
					 conn.close();
				   } catch (SQLException e) {
					// TODO Auto-generated catch block
					 e.printStackTrace();
				   }
				   session.close();
			   }
		return list;
	
	}
	@Override
	public String queryBySql(String sql) {
		List list = new ArrayList();
		// TODO 自动生成的方法存根
		Session session = this.sessionFactory.openSession();
	      Connection conn = null; 
	  	  Statement  st= null;
	  	  ResultSet rs = null;
	  	try {
	  		conn = session.connection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				 Map map = new HashMap();
            	 for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
       	          map.put(rs.getMetaData().getColumnName(i), rs.getString(i));
       	        }
       	        list.add(map);
       	        if(list.size()==10){
       	        	break;
       	        }
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			   try {
					 rs.close();
					 st.close();
					 conn.close();
				   } catch (SQLException e) {
					// TODO Auto-generated catch block
					 e.printStackTrace();
				   }
				   session.close();
			   }
		JSONArray jsonStr = JSONArray.fromObject(list);
		return jsonStr.toString();
	
	}
	@Override
	public List<Map> getByKey(String key) {
		String sql = "select sql from info_main_zydy where zyid='"+key+"'";
		String getSql="";
		List<Map> list = new ArrayList<Map>();
		// TODO 自动生成的方法存根
		Session session = this.sessionFactory.openSession();
	      Connection conn = null; 
	  	  Statement  st= null;
	  	  ResultSet rs = null;
	  	Connection conn2 = null; 
	  	  Statement  st2= null;
	  	  ResultSet rs2 = null;
	  	try {
	  		conn = session.connection();
			st= conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				getSql = rs.getString("SQL");
				
			}
			conn2 = session.connection();
			st2= conn.createStatement();
			Map<String,String> map = new HashMap<String, String>();
			if(getSql==null || getSql.equals("") || getSql.equals("null")){
				map = new HashMap<String, String>();
				map.put("error", "nodata");
				list.add(map);
			}else{
				rs2 = st.executeQuery(getSql);
				while (rs2.next()) {
					 map = new HashMap<String, String>();
	           	 for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
	      	          map.put(rs2.getMetaData().getColumnName(i), rs2.getString(i));
	      	        }
	      	        list.add(map);
				}
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			   try {
					 rs.close();
					 st.close();
					 conn.close();
					 rs2.close();
					 st2.close();
					 conn2.close();
				   } catch (SQLException e) {
					// TODO Auto-generated catch block
					 e.printStackTrace();
				   }
				   session.close();
			   }
		return list;
	
	}

}
