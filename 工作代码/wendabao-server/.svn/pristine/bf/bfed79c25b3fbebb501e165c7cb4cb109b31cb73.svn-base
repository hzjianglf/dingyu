package com.handany.base.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.common.ComponentFactory;

public class RandomCode {

	
	private static Logger logger = LoggerFactory.getLogger(RandomCode.class);
	
	private static Random random = new Random();
	
	private static String SHOP_ID_FORMAT = "0000000000";
	
	private static String RANDOM_CODE_FORMAT = "000000";
	
	
	/**
	 * 
	 */
	private static int nextRandom(){
		
		return random.nextInt(1000000);
	}
	
	
	public static void generator(int start,int end) {
		
	
	
		
		for(int i=start;i<=end;i++){
			
			int rnd = nextRandom();
			
			if(rnd == 0){
				continue;
			}
			
			try{
				save(i,rnd);
				
			}catch(RandomException ex){
				i --;
			} catch (SQLException e) {
				
				break;
			}
			
			
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Connection conn = null;
	
	private static void save(int seqNo,int rndm) throws SQLException, RandomException{
		String shopId = SHOP_ID_FORMAT+seqNo;
		
		String randomCode = RANDOM_CODE_FORMAT + rndm;

		shopId = shopId.substring(shopId.length()-SHOP_ID_FORMAT.length());
		
		randomCode = randomCode.substring(randomCode.length() - RANDOM_CODE_FORMAT.length());
		
	
		
		DataSource ds = ComponentFactory.getBean("dataSource",DataSource.class);
		Statement stmt = null;
			try{
			if( conn == null){
				conn = ds.getConnection();
			}
				stmt = conn.createStatement();
			}catch(SQLException ex){
				logger.error("",ex);
				throw ex;
			}
			
			try {
				stmt .executeUpdate("insert into Bm_Shop_Code(shop_id,code)"
						+ " values('"+shopId+"','"+randomCode+"')");
			} catch (SQLException ex) {
				logger.error("",ex);
				throw new RandomException();
			}
			stmt.close();
			
		
		
		
		
	}
	
	
	static class RandomException extends Exception{
		public RandomException(){}
	}
	
	public static void main(String[] args) {
		
		generator(9311,10000);
	}
}
