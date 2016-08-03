package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.RoleDao;
import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.Menu;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;



public class RoleDaoImpl  implements RoleDao{
	@Autowired
	SessionFactory sessionFactory;
	
	/*protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;*/

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExistName(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int queryCount(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override

	public int getroleCount(String sql) {
		try {
			int size=0;
			List<Role> list = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Role.class).list();
			size=list.size();
			return size;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
				
	}

	@Override
	public List<Role> rolefindAll(String sql2,int start, int number) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println(sql2);
		List<Role> roleList=null;
				try {
					// TODO Auto-generated method stub
					 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql2).addEntity(Role.class);		
						query.setFirstResult(start*number);
						query.setMaxResults(number);
						roleList=query.list();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return roleList;
	}

	@Override
	public List<Privilege> getPrivilege(String privilegeList) {
		// TODO Auto-generated method stub
		List<Privilege> p2=null;
		try {
			String sql="select * from privilege where privilege_id in("+ privilegeList+")";
			 Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Privilege.class);
			 p2=query.list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		 return p2;
	}

	@Override
	public List<Role> rolefindAll2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRole(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Role.class).list();
	}

	@Override
	public Role getById(String sql) {
		// TODO Auto-generated method stub
		return (Role) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Role.class).list().get(0);
	}

	@Override
	public Privilege getByPrivilegeId(String sql) {
		// TODO Auto-generated method stub
		return (Privilege) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Privilege.class).list().get(0);
	}

	
	
	

}
