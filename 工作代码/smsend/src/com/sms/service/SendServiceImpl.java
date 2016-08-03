package com.sms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sms.service.SendService;
import com.sms.util.ConnectionPool;

@WebService(endpointInterface="com.sms.service.SendService")
public class SendServiceImpl implements SendService {
	private Logger log = LoggerFactory.getLogger(SendServiceImpl.class);
	
	@Override
	public String send(String mobile, String content) {
	       ConnectionPool pool = null;
	           pool = ConnectionPool.getInstance();
	           Connection conn = pool.getConnection();
	           String id = UUID.randomUUID().toString();
	           String sql="insert into smssend (ID, MOBILE, CONTENT)values ('{"+id+"}', '"+mobile+"', '"+content+"')";
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
}
