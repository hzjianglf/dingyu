package com.sms.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    public static void main(String[] args) throws SQLException{
        String sql = "select * from ac_resource";
//        ConnectionPool pool = null;
//        
//        for (int i = 0; i < 2; i++) {
//            pool = ConnectionPool.getInstance();
//            Connection conn = pool.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                System.out.println(rs.getString("ID"));
//            }
//            rs.close();
//            stmt.close();
//            pool.release(conn);
//        }
//        pool.closePool();
        for(int i=0;i<100;i++){
        	 System.out.println(i+" "+customSelect(sql).get(0));
        }
       
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
//        pool.closePool();
        return list;
    }
}