package com.handany.base.dao;


import java.lang.reflect.Field;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO {

	@Autowired
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	
	public <T> T getMapper(Class<T> cls){
		return sqlSession.getMapper(cls);
	}
	


	public static String objToStr(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();

		String tmp = "";

		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				tmp += fields[i].getName() + "=" + fields[i].get(obj) + ";";
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tmp;
	}
	
	
}
