package lmd.extend.wso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {

	public static String getJianPin(String uid){
		//String type=(String)request.getParameter("type");
		if (uid == null) {
			uid="";
		}
		String loginname=null;
		String sn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@10.10.12.118:1521:asmdb1"; 
			Connection conn = DriverManager.getConnection(url, "zhpt", "zhpt2013");
			Statement stmt = conn.createStatement(); 
			ResultSet rs =null; 
			String sql="select staff_name,sn from org_yusi_dy a,org_person b where a.sn=b.idnum and b.loginname='"+uid+"'";	
			//out.print(sql);
			rs=stmt.executeQuery(sql); 
			if(rs.next()){
				loginname=rs.getString(1);

                            sn = rs.getString(2);
                         
				if(loginname==null)
				{
					loginname = "bucunzai";
				}
			}
			rs.close();
			
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		
		return loginname;
		
	}
	
	public static void main(String[] args) {
		System.out.println(getJianPin("baijianqun"));
	}
}
