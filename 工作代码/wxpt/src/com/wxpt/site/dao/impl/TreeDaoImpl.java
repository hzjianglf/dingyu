package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.TreeDao;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;

public class TreeDaoImpl implements TreeDao {
	
	SessionFactory sessionFactory;	
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Role> listrole;
	Role rro;
	Privilege pr;
	List<Privilege> pri;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Role> rolefindAll(int roleidshezhiint) {
		// TODO Auto-generated method stub
		String sql="select * from role where role_id="+roleidshezhiint;
		
		List<Role> rolelist=null;
		
				try {
					// TODO Auto-generated method stub
					 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Role.class);		
						
					 rolelist=query.list();
						
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return rolelist;
	}

	@Override
	public void roleupdate(Role rro) {
		// TODO Auto-generated method stub
		try {
			String sql="UPDATE role SET privilege_list='"+rro.getPrivilegeList()+"' WHERE role_id="+rro.getRoleId()+"";
			System.out.println(sql);
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addquanxian(Role rro) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO `role`(`role_id`, `role_name`, `role_parent_id`, `role_statement`, `privilege_list`) VALUES ("+rro.getRoleId()+",'"+rro.getRoleName()+"',"+rro.getRoleParentId()+",'"+rro.getRoleStatement()+"','"+rro.getPrivilegeList()+"')";
		System.out.println(sql);
		/*this.sessionFactory.getCurrentSession().save(luckUser);*/
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updatequanxian(Role rro) {
		// TODO Auto-generated method stub
		try {
			String sql="UPDATE role SET role_name='"+rro.getRoleName()+"',role_parent_id="+rro.getRoleParentId()+",role_statement='"+rro.getRoleStatement()+"',privilege_list='"+rro.getPrivilegeList()+"' WHERE role_id="+rro.getRoleId()+"";
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deljuese(int roleidshanint) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM role WHERE role_id="+roleidshanint;
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Privilege getPrivilegebyprivilegeStatement(String gongnengid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Privilege> getprivilegeparentid(String cunquanxian) {
		// TODO Auto-generated method stub
		String sql="select * from privilege where privilege_id in("+ cunquanxian+")";
        List<Privilege> privilegelelist=null;	
		try {
			// TODO Auto-generated method stub
			 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Privilege.class);		
				
			 privilegelelist=query.list();
				
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return privilegelelist;
		
	}

	@Override
	public List<Privilege> getprivilegebyId(int id) {
		// TODO Auto-generated method stub
		String sql="select * from privilege  where privilege_parentId="+id;
		List<Privilege> privilegelelist=null;
		
		try {
			// TODO Auto-generated method stub
			 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Privilege.class);		
				
			 privilegelelist=query.list();
				
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return privilegelelist;
	}







}
