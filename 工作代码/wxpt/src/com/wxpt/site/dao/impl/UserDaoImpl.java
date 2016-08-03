package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.JDBC_test;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.UserDao;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.Vote;
import com.wxpt.site.entity.pojo.KeyListPojo;
import com.wxpt.site.entity.pojo.UserPojo;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public void saveUser(int enterId,String userName,String userNum) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".user ( `userNum`, `userName`) " +
				"VALUES ('"+userNum+"','"+userName+"')";
		System.out.println(sql);
		//session=HibernateSessionFactory.getSession();  
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
	public User getUserByNum(String userNum) {
		// TODO Auto-generated method stub
		String hql = "from User where userNum = '" + userNum + "'";
		try {
			User user = (User) this.sessionFactory.getCurrentSession()
					.createQuery(hql).list().get(0);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPojo> getUserPojo(String paixu) {
		// TODO Auto-generated method stub
		List<UserPojo> userPojoList = new ArrayList<UserPojo>();
		String hql = "SELECT count(*) a,user.userId,userName,userNum FROM `userCount`,`user` WHERE user.userId = userCount.userId group by userId order by a "
				+ paixu;
		System.out.println(hql);
		List<Object[]> aList = new ArrayList<Object[]>();
		aList = this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.list();
		for (Object[] o : aList) {
			UserPojo userPojo = new UserPojo();
			userPojo.setCount(Integer.parseInt(o[0].toString()));
			userPojo.setUserId((Integer) o[1]);
			userPojo.setUserName(o[2].toString());
			userPojo.setUserNum(o[3].toString());
			userPojoList.add(userPojo);
		}
		return userPojoList;
	}

	public List<User> getUserList() {
		String hql = "from User";
		List<User> userList = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return userList;
	}

	public int getUserCount(int userId) {
		String sql = " from UserCount where userId = " + userId;
		try {
			int count = this.sessionFactory.getCurrentSession()
					.createQuery(sql).list().size();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public List<UserCount> getUserCountList(int userId) {
		// TODO Auto-generated method stub
		List<UserCount> uCountsList = new ArrayList<UserCount>();
		String hql = "from UserCount where user.userId =" + userId;
		uCountsList = this.sessionFactory.getCurrentSession().createQuery(hql)
				.list();
		return uCountsList;
	}

	@Override
	public UserCount getUserCountBySend(int enterId,String sendName) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".userCount where sendUser = '" + sendName
				+ "' ORDER BY countId DESC  ";
		try {
			UserCount u = (UserCount) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(UserCount.class).list().get(0);
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public void saveUserCount(int enterId,String time,String forUser,int userId) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".userCount( `userId`, `sendUser`, `sendTime`) VALUES ("+userId+",'"+forUser+"','"+time+"')";
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
	public User getUserByName(int enterId,String userName) {
		// TODO Auto-generated method stub
		List<User> userlist=null;
		User user=null;
		String hql = "select * from wxpt"+enterId+".user where userName = '" + userName + "'";
		System.out.println(hql);
		try {
			userlist =  this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(User.class).list();
			if(userlist.size()>0){
				user= userlist.get(0);
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return user;
		}

	}

	@Override
	public void saveKeywords(int enterId,Keywords keywords) {
		try {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String sql="INSERT INTO wxpt"+enterId+".keywords ( `keywordcontent`, `wordCount`, `fileCount`, `imagesCount`, `rule`)" +
					" VALUES ('"+keywords.getKeywordcontent()+"','"
					+keywords.getWordCount()+"','"
					+keywords.getFileCount()+"','"
					+keywords.getImagesCount()+"','"
					+keywords.getRule()+"')";
			//session=HibernateSessionFactory.getSession();
			System.out.println(sql); 
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
			
			/*this.sessionFactory.getCurrentSession().save(keywords);*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Keywords getMaxkeywords(int enterId) {
		// TODO Auto-generated method stub
		String hql = "select  * from "+enterId+".keywords  ORDER BY keywordID DESC ";
		try {
			List<Keywords> keywords = (List<Keywords>) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Keywords.class).list().get(0);
			return (Keywords) keywords;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@Override
	public void saveKeywordsexplicit(int enterId,Keywordexplicit keywordexplicit) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".keywordexplicit( `keywordID`, `eContent`, `ekey`, `addTime`, `einstruction`, `title`, `url`, `type`) " +
				"VALUES ("+keywordexplicit.getKeywords().getKeywordId()+",'"+keywordexplicit.getEcontent()+"','"+keywordexplicit.getEkey()+"','"+keywordexplicit.getAddTime()+"'," +
						"'"+keywordexplicit.getEinstruction()+"','"+keywordexplicit.getTitle()+"','"+keywordexplicit.getUrl()+"',"+keywordexplicit.getType()+")";
		 /*this.sessionFactory.getCurrentSession().save(keywordexplicit);*/
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
	public void updateKeywordsexplicit(int enterId,
			Keywordexplicit keywordexplicit) {
		// TODO Auto-generated method stub
		String sql="UPDATE keywordexplicit SET `keywordID`="+keywordexplicit.getKeywords().getKeywordId()+"," +
				"`eContent`='"+keywordexplicit.getEcontent()+"',`ekey`='"+keywordexplicit.getEkey()+"',`addTime`='"+keywordexplicit.getAddTime()+"',`einstruction`='"+keywordexplicit.getEinstruction()+"'," +
				"`title`='"+keywordexplicit.getTitle()+"',`url`='"+keywordexplicit.getUrl()+"',`type`="+keywordexplicit.getType()+" WHERE `explicitID`="+keywordexplicit.getExplicitId()+"";
		System.out.println(keywordexplicit.getKeywords().getKeywordId());
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			/*pstmt.setString(1, "userName");
			pstmt.setString(2, "userNum");
			pstmt.setInt(3, 5);*/
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Keywords getKeyBysendContent(int enterId,String keywordcontent) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".keywords where keywordcontent = '" + keywordcontent
				+ "'";
		try {
			Keywords keywords = (Keywords) this.sessionFactory
					.getCurrentSession().createSQLQuery(hql).addEntity(Keywords.class).list().get(0);
			return keywords;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Keywordexplicit> getkExplicitByKeyID(int enterId,int keywordsID) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".keywordexplicit where keywordID = "+keywordsID;
			System.out.println(hql);	
		try {
			List<Keywordexplicit> e = this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).list();
			return e;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Keywords> getkeyList(int enterId) {
		// TODO Auto-generated method stub
		String hql = "select  * from wxpt"+enterId+".keywords  ORDER BY keywordID DESC ";
		try {
			List<Keywords> keywords = (List<Keywords>) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Keywords.class).list().get(0);
			return keywords;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Keywords getByID(int enterId,int keywordsid) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".keywords where keywordID = " + keywordsid;
		try {
			Keywords keywords = (Keywords) this.sessionFactory
					.getCurrentSession().createSQLQuery(hql).addEntity(Keywords.class).list().get(0);
			return keywords;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,int keywordsID,
			String eKey, int type) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".keywordexplicit where keywordID = "
				+ keywordsID + " and ekey = '" + eKey + "' and type = " + type;
		try {
			List<Keywordexplicit> e = this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Keywordexplicit.class).list();
			return e;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<KeyListPojo> keyListPojo() {
		// TODO Auto-generated method stub
		String hql = "from Keywordexplicit";
		List<Keywordexplicit> e = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		List<KeyListPojo> kList = new ArrayList<KeyListPojo>();
		for (Keywordexplicit keywordexplicit : e) {
			/*Keywords keywords = this.getByID(keywordexplicit.getKeywords()
					.getKeywordId());*/
			KeyListPojo k = new KeyListPojo();
			k.setAddTime(keywordexplicit.getAddTime());
			k.setEcontent(keywordexplicit.getEcontent());
			k.setEinstruction(keywordexplicit.getEinstruction());
			k.setExplicitId(keywordexplicit.getExplicitId());
			k.setTitle(keywordexplicit.getTitle());
			k.setUrl(keywordexplicit.getUrl());
			if (keywordexplicit.getEkey().equals("word")) {
				k.setEkey("文本消息");
			}
			if (keywordexplicit.getEkey().equals("images")) {
				k.setEkey("图文消息");
			}
			if (keywordexplicit.getEkey().equals("vodio")) {
				k.setEkey("视频消息");
			}
			/*k.setKeyName(keywords.getKeywordcontent());*/
			kList.add(k);
		}
		return kList;
	}

	@Override
	public List<UserPojo> getSqlPaixu(String sql) {
		// TODO Auto-generated method stub
		List<UserPojo> userPojoList = new ArrayList<UserPojo>();
		List<Object[]> aList = new ArrayList<Object[]>();
		aList = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.list();
		for (Object[] o : aList) {
			UserPojo userPojo = new UserPojo();
			userPojo.setCount(Integer.parseInt(o[0].toString()));
			userPojo.setUserId((Integer) o[1]);
			userPojo.setUserName(o[2].toString());
			userPojo.setUserNum(o[3].toString());
			userPojoList.add(userPojo);
		}
		return userPojoList;
	}

	@Override
	public void updateKeyword(int keywordid, String keywordsContent) {
		// TODO Auto-generated method stub
		String sql = "update keywords set keywordcontent = '" + keywordsContent
				+ "' where  keywordID	 =" + keywordid;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
	}

	@Override
	public void deleteSql(int enterId,String ids) {
		// TODO Auto-generated method stub
		String sql = "DELETE from wxpt"+enterId+".keywordexplicit where explicitID in (" + ids
				+ ")";
		this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
	}

	@Override
	public void deleteKeyBySql(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE from keywords WHERE keywordID = " + id;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
	}

	@Override
	public Keywordexplicit getkExplicitBy(int enterId,int explicitiD) {
		// TODO Auto-generated method stub
		
		String hql = "select * from wxpt"+enterId+".keywordexplicit where explicitId = " + explicitiD;
		try {
			Keywordexplicit e = (Keywordexplicit) this.sessionFactory
					.getCurrentSession().createSQLQuery(hql).addEntity(Keywordexplicit.class).list().get(0);
			return e;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Override
	public List<User> getList(String ids) {
		// TODO Auto-generated method stub
		String hql = "from User where userId not in (" + ids + ")";
		List<User> userList = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return userList;
	}

	public int getUserCountById(int userId) {
		String sql = "select count(*) from user where userId =" + userId;
		try {
			int count = (Integer) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).list().get(0);
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	@Override
	public List<UserCount> getUserCounts(int enterId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".userCount";
		List<UserCount> u = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(UserCount.class).list();
		return u;
	}

	@Override
	public List<UserCount> getUserCounts(int enterId,String sendUsers) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".userCount where sendUser not in (" + sendUsers + ")";
		System.out.println(hql);
		List<UserCount> u = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(UserCount.class).list();
		return u;
	}

	@Override
	public List<UserPojo> getUserPojo(int enterId,String paixu, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<UserPojo> userPojoList = new ArrayList<UserPojo>();
		String hql = "SELECT count(*) a,user.userId,userName,userNum FROM `wxpt"+enterId+".userCount`,`user` WHERE user.userId = userCount.userId group by userId order by a "
				+ paixu;
		
		System.out.println(hql);
		List<Object[]> aList = new ArrayList<Object[]>();
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				hql);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		aList = query.list();
		for (Object[] o : aList) {
			UserPojo userPojo = new UserPojo();
			userPojo.setCount(Integer.parseInt(o[0].toString()));
			userPojo.setUserId((Integer) o[1]);
			userPojo.setUserName(o[2].toString());
			userPojo.setUserNum(o[3].toString());
			userPojoList.add(userPojo);
		}
		return userPojoList;
	}

	@Override
	public List<UserCount> getUserCount(int enterId,String ids) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from wxpt"+enterId+".userCount where user.userId  in (" + ids + ")";
		List<UserCount> u = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(UserCount.class).list();
		return u;
	}

	@Override
	public List<User> getUserList(String sql) {
		// TODO Auto-generated method stub
		List<User> userList = this.sessionFactory.getCurrentSession()
				.createQuery(sql).list();
		return userList;
	}

	@Override
	public int getUserCountById(String sql) {
		try {
			// TODO Auto-generated method stub
			System.out.println(sql);
			List<User>list=this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(User.class).list();
			int count = list.size();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

	@Override
	public int getuserCountCount(String sql) {
		// TODO Auto-generated method stub
		try{
		List<UserCount>list=this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(UserCount.class).list();
		int count = list.size();

		return count;
	} catch (Exception e) {
		// TODO: handle exception
		return 0;
	}
	}

	@Override
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,int keywordsID, int type) {
		// TODO Auto-generated method stub
		
		String hql = "select * from wxpt"+enterId+".keywordexplicit where keywordID = "
				+ keywordsID + " and type = " + type;

		List<Keywordexplicit> e = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(Keywordexplicit.class).list();
		return e;

	}

	@Override
	public ManageUser checkLogin(int enterId,String uname, String password) {
		// TODO Auto-generated method stub
		String sql="select * from webchat.manage_user where manage_user_name = '" + uname
				+ "' and passwrod = '" + password + "' and enterid="+enterId;
		try {
			ManageUser user = (ManageUser) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(ManageUser.class).list().get(0);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public ManageUser getManageUserByName(int enterId,String userName) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			return (ManageUser) this.sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select * from webchat.manage_user where manage_user_name = '" + userName + "'").addEntity(ManageUser.class)
					.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void update(int enterId,String sql) {
		// TODO Auto-generated method stub
			con=jdbc.getConnection(enterId);
			try {
				pstmt = con.prepareStatement(sql);
				//执行
				pstmt.executeUpdate();
				con.close();	//关闭数据库连接
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}

	@Override
	public List<Keywordexplicit> getKeyList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		try {
		String sql="select * from wxpt"+enterId+".keywordexplicit";
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Keywordexplicit.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Keywords> getBykeyList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		try {
			
		
		String sql="select * from wxpt"+enterId+".keywords";
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Keywords.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<User> getUserList(int page, int rows,String hql) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(User.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public User getUserByID(int enterId,int userId) {
		try {
			// TODO Auto-generated method stub
			String sql="select * from wxpt"+enterId+".user where userId =" + userId;
			System.out.println(sql);
			User user = (User) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(User.class).list()
					.get(0);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public void deleteByUserID(int enterId,int userId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".user WHERE `userId`="+userId;
		System.out.println(sql);
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List<UserCount> getUserCountList(int enterId,int page, int rows, int userId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".userCount where userId =" + userId;
		System.out.println(hql);
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(UserCount.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public int getKeyWordId(int enterId,int explicitiD) {
		// TODO Auto-generated method stub
		String sql="select keywordID from wxpt"+enterId+".keywordexplicit where explicitID="+explicitiD;
		System.out.println(sql);
		int keywordId= (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		System.out.println(keywordId);
		return keywordId;
	}

	@Override
	public void saveVote(int enterId,String name) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".vote( `voteStartTime`, `voteEndTime`, `state`, `voteUser`) VALUES ('"+TimeUtil.getTime()+"','"+null+"',"+0+",'"+name+"')";
		System.out.println(sql);
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
	public List<Vote> getAll(String sql, int page, int rows) {
		List<Vote> list=null;
		try {
			
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Vote.class);
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateVote(int enterId, Vote vote) {
		// TODO Auto-generated method stub
		String sql="UPDATE vote SET `voteStartTime`='"+vote.getVoteStartTime()+"',`voteEndTime`='"+vote.getVoteEndTime()+"',`state`="+vote.getState()+",`voteUser`='"+vote.getVoteUser()+"' WHERE `voteId`="+vote.getVoteId()+"";
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Vote getByVoteId(String sql) {
		// TODO Auto-generated method stub
		List<Vote> vote=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Vote.class).list();
		if(vote.size()==0){
			return null;
		}else{
			return vote.get(0);
		}
	}

	@Override
	public void deleteVote(String sql) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().createSQLQuery(sql)
		.executeUpdate();
	}

	@Override
	public List<Vote> getAll(int enterId) {
		 List<Vote> votelist=null;
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".vote where voteUser = '投票活动' and state=0 ";
		try {
			votelist =  this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Vote.class).list();
			return votelist;
		} catch (Exception e) {
			// TODO: handle exception
			return votelist;
		}
		
	}

	@Override
	public List<Vote> getAll(String sql) {
		List<Vote> votelist = null;
		try {
			// TODO Auto-generated method stub
		 votelist = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Vote.class).list();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return votelist;
	}

	@Override
	public List<Keywordexplicit> getkExplicitByEkey(Integer keywordId, int type) {
		// TODO Auto-generated method stub
		String hql = "from Keywordexplicit where keywords.keywordId = "
				+ keywordId + " and type = " + type;

		List<Keywordexplicit> e = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return e;
	}

	@Override
	public int getRecordId(int enterId, int laId) {
		// TODO Auto-generated method stub
		String sql="SELECT recordId FROM wxpt"+enterId+".luckanwer WHERE `luckanswerid`="+laId;
		int id=(Integer) this.sessionFactory.getCurrentSession()
		.createSQLQuery(sql).list().get(0);
		return id;
	}

	@Override
	public ManageUser getManageUserById(int enterId, int userid) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			return (ManageUser) this.sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select * from wxpt"+enterId+".manage_user where manage_user_id  = " + userid + "").addEntity(ManageUser.class)
					.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteKeywords(int enterId, int keywordsID) {
		// TODO Auto-generated method stub
		try {
			String sql="delete from wxpt"+enterId+".keywords where keywordID="+keywordsID;
			System.out.println(sql);
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			
		}
	}
	@Transactional
	@Override
	public ManageUser getByEnterId(String sql) {
		// TODO Auto-generated method stub
		try {
			ManageUser user = (ManageUser) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(ManageUser.class).list().get(0);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ManageUser> getAllUser(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(ManageUser.class).list();
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateUser(String sql) {
		// TODO Auto-generated method stub
		con=jdbc.getConnection2();
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
	

