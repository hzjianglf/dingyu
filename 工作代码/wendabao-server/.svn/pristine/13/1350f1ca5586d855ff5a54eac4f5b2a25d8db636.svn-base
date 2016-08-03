package com.handany.base.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.handany.base.common.ComponentFactory;

/**
 * 事务管理器
 * @author lhb
 *
 */
public class TransactionManager {

	
	private static Logger logger = LoggerFactory.getLogger(TransactionManager.class);
	
	private static ThreadLocal<TransactionStatus> local = new ThreadLocal<TransactionStatus>();
	
	private static DefaultTransactionDefinition def= new DefaultTransactionDefinition(); 

	private static PlatformTransactionManager txManager;
	static{
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		txManager = (PlatformTransactionManager) ComponentFactory.getBean("txManager");
	}
	
	public static void begin() throws Exception{
		
		
		if(local.get() != null){
			throw new Exception("事务已经启动，不可嵌套");
		}
		TransactionStatus status =txManager.getTransaction(def);
		
		local.set(status);
		
	}
	
	public static void commit() throws Exception{
		
		try{
			TransactionStatus status = local.get();
			
			if(status == null){
				throw new Exception("未开启事务");
			}
			
			txManager.commit(status);
			local.remove();
		}catch(Exception ex){
			logger.error("",ex);
			throw ex;
		}		
		
	}
	
	public static void rollback() throws Exception{
		try{
			TransactionStatus status = local.get();
			
			if(status == null){
				throw new Exception("未开启事务");
			}
			
			txManager.rollback(status);
		}catch(Exception ex){
			logger.error("",ex);
			throw ex;
		}finally{	
			local.remove();
		}
	}
	
	
}
