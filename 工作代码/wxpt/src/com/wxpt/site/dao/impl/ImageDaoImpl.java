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
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.JDBC_test;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.ImageDao;
import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Prize;
import com.wxpt.site.entity.Question;

public class ImageDaoImpl implements ImageDao  {

	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Imageroll> getImageList(int enterId) {
		// TODO Auto-generated method stub

		String hql="select * from wxpt"+enterId+".imageroll where template_count = 1 ";

		List<Imageroll> imagerollList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Imageroll.class).list();
		return imagerollList;
	}

	@Override
	public void addImage(int enterId,Imageroll image) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".imageroll( `upload_image`, `upload_time`, `template_count`) " +
				"VALUES ('"+image.getUploadImage()+"','"+image.getUploadTime()+"',"+image.getTemplateCount()+")";
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Imageroll getImagerollById(int imageId) {
		// TODO Auto-generated method stub
		String hql="from Imageroll where imageId = " +imageId;
		Imageroll imageroll = (Imageroll) this.sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
		return imageroll ;
	}
@Transactional
	@Override
	public void updateImage(int enterId,Imageroll image) {
		// TODO Auto-generated method stub

	String sql="UPDATE wxpt"+enterId+".imageroll SET `upload_image`='"+image.getUploadImage()+"',`upload_time`='"+image.getUploadTime()+"',`template_count`="+image.getTemplateCount()+" where image_id="+image.getImageId()+"";

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
@Transactional
public void addCheck(int enterId,CheckIn checkin) {
	// TODO Auto-generated method stub
	try {
		String sql="INSERT INTO wxpt"+enterId+".checkIn( `checkuser`, `checTime`, `checkNum`, `score`, `count`)" +
				" VALUES ('"+checkin.getCheckuser()+"','"+checkin.getChecTime()+"','"+checkin.getCheckNum()+"','"+checkin.getScore()+"',"+checkin.getCount()+")";
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
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public List<CheckIn> queryAllChenck(int enterId,String username) {
	// TODO Auto-generated method stub
	List<CheckIn> checklist= null;
	try {
		//String hql="from CheckIn where checkuser='"+username+"' order by  checTime asc checTime";
		String hql = "select * from wxpt"+enterId+".checkIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		checklist=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklist;
}

@Override
public List<CheckIn> queryAllChenckByName(int enterId,String username) {
	List<CheckIn> checklistByName= null;
	try {
		String hql="select * from wxpt"+enterId+".checkIn where checkuser='"+username+"' order by  checTime desc ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		checklistByName=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklistByName;
}

@Override
public List<CheckIn> queryAllChenck1(int enterId,String username) {
	List<CheckIn> checklist= null;
	try {
		//String hql="from CheckIn where checkuser='"+username+"' order by  checTime asc checTime";
		String hql = "select * from wxpt"+enterId+".checkIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=1";
		checklist=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklist;
}

@Override
public List<CheckIn> queryAllCheckByNameAndTime(int enterId,String username, String year,
		String month) {
	List<CheckIn> checklistTime= null;
	try {
		String hql="select * from wxpt"+enterId+".checkIn where checkuser='"+username+"' and checTime  like '"+year+"%"+month+"%'  and count >=5 and  count <15 order by  checTime desc  ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		checklistTime=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklistTime;
	
}

@Override
public List<CheckIn> queryAllCheckByNameAndTime2(int enterId,String username, String year,
		String month) {
	// TODO Auto-generated method stub
	List<CheckIn> checklistTime2= null;
	try {
		String hql="select * from wxpt"+enterId+".checkIn where checkuser='"+username+"' and checTime  like '"+year+"%"+month+"%'  and count >=15 and  count <30 order by  checTime desc  ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		checklistTime2=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklistTime2;
}

@Override
public List<CheckIn> queryAllCheckByNameAndTime3(int enterId,String username, String year,
		String month) {
	List<CheckIn> checklistTime3= null;
	try {
		String hql="select * from wxpt"+enterId+".checkIn where checkuser='"+username+"' and checTime  like '"+year+"%"+month+"%'  and count >=30 order by  checTime desc  ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		checklistTime3=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checklistTime3;
}

@Override
public void addPrize(int enterId,Prize prize) {
	String sql="INSERT INTO wxpt"+enterId+".prize( `prizegrade`, `prizenum`, `probability`, `prizeuser`, `prizeTime`, `state`) " +
			"VALUES ("+prize.getPrizegrade()+",'"+prize.getPrizenum()+"','"+prize.getProbability()+"','"+prize.getPrizeuser()+"','"+prize.getPrizeTime()+"',"+prize.getState()+")";
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
public List<Prize> queryByName(int enterId,String username) {
	List<Prize> prizeList= null;
	try {
		String hql = "select * from wxpt"+enterId+".prize where prizeuser = '"+username+"' and datediff(now(),str_to_date(prizeTime,'%Y/%m/%d %H:%i:%s') )=0";
		
		prizeList=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prizeList;
}


@Override
public List<Prize> queryByNameOrder(int enterId,String username) {
	List<Prize> prizelistByName= null;
	try {
		String hql="select * from wxpt"+enterId+".prize where prizeuser='"+username+"' order by  prizeTime desc ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		prizelistByName=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class).list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prizelistByName;
}

@Override
public boolean queryCheck(String checktime, String prizeTime, String username,int count) {
	// TODO Auto-generated method stub
	int co=count-5;
	 String hql2="SELECT DATEDIFF('str_to_date('"+checktime+"','%Y/%m/%d %H:%i:%s')','str_to_date('"+prizeTime+"','%Y/%m/%d %H:%i:%s'') > '"+co+"') ";	


	return false;
}


@Override
public List<Imageroll> getImageList1(int enterId) {
	String hql="select * from wxpt"+enterId+".imageroll where template_count = 2 ";

	List<Imageroll> imagerollList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Imageroll.class).list();
	return imagerollList;
	
	
	
	/*String hql="from Imageroll where templateCount = 2";
	List<Imageroll> imagerollList1 = this.sessionFactory.getCurrentSession().createQuery(hql).list();
	return imagerollList1;*/
}

@Override
public List<Imageroll> getImageList2(int enterId) {
	String hql="select * from wxpt"+enterId+".imageroll where template_count = 3 ";

	List<Imageroll> imagerollList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Imageroll.class).list();
	return imagerollList;
	/*String hql="from Imageroll where templateCount = 3";
	List<Imageroll> imagerollList2 = this.sessionFactory.getCurrentSession().createQuery(hql).list();
	return imagerollList2;*/
}

@Override
public List<Prize> queryByNameByTime(int enterId,String year, String month) {
	// TODO Auto-generated method stub
	List<Prize> prizelist= null;
		try {
			String hql="select * from wxpt"+enterId+".prize where  prizeTime  like '"+year+"%"+month+"%'  and prizegrade=1 ";
			//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
			prizelist=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prizelist;
}

//后来新加
@Override
public List<CheckIn> queryAllChenckin(int enterid) {
	// TODO Auto-generated method stub
	List<CheckIn> queryAllCheckinlist=null;
	try {

		//String sql1="select * from wxpt"+enterid+".checkIn ";
		String sql="SELECT * FROM wxpt"+enterid+".checkIn group by checkuser  order by checTime desc";
		queryAllCheckinlist=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CheckIn.class).list();
		
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return queryAllCheckinlist;

}

@Override
public List<CheckIn> queryAllChenckin(int enterid, int start, int number) {
	List<CheckIn> queryAllCheckinlist=null;
	try {

		//String sql="SELECT * FROM wxpt "+enterid+".checkIn group by checkuser  order by checTime desc";
		//String sql="select *from  wxpt"+enterid+".checkIn";
		String sql="SELECT * FROM wxpt"+enterid+".checkIn group by checkuser  order by checTime desc";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CheckIn.class);

		query.setFirstResult(start);
		query.setMaxResults(number);
		queryAllCheckinlist=query.list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return queryAllCheckinlist;
}

@Override
public void deleteCheckin(int enterid, int id) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
			String sql="DELETE FROM wxpt"+enterid+".checkIn WHERE checkId="+id;
			System.out.println(sql);
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
}

@Override
public CheckIn queryByCheckinId(int enterid, int id) {
	// TODO Auto-generated method stub
	CheckIn checkIn=null;
	try {
		

		String hql="select * from wxpt"+enterid+".checkIn WHERE checkId =" +id;
		checkIn = (CheckIn) this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list().get(0);
		
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checkIn;
}

@Override
public List<CheckIn> queryAllChenckinByName(int enterid, String username,
		int start, int number) {
	List<CheckIn> queryAllCheckinlist=null;
	try {

		//String sql="SELECT * FROM wxpt "+enterid+".checkIn group by checkuser  order by checTime desc";
		//String sql="select *from  wxpt"+enterid+".checkIn";
		String sql="SELECT * FROM wxpt"+enterid+".checkIn  where checkuser ='"+username+"'  order by checTime desc ";
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(CheckIn.class);
		query.setFirstResult(start);
		query.setMaxResults(number);
		queryAllCheckinlist=query.list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return queryAllCheckinlist;
}

@Override
public List<CheckIn> queryAllChenckByName(String username, int enterid) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	List<CheckIn> queryAllCheckinlist=null;
		try {
			
			String hql="select * from wxpt"+enterid+".checkIn WHERE checkuser ='" +username+"'" ;
			queryAllCheckinlist =  this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(CheckIn.class).list();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryAllCheckinlist;
}

@Override
public List<Prize> queryByNamePage(int enterId, int start,
		int number) {
	List<Prize> prizelistByName= null;
	try {
		String hql="select * from wxpt"+enterId+".prize  order by  prizeTime desc ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class);
		query.setFirstResult(start);
		query.setMaxResults(number);
		prizelistByName=query.list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prizelistByName;
}

@Override
public List<Prize> queryPrizeAll(int enterId) {
	// TODO Auto-generated method stub
	List<Prize> prizelistByName= null;
	try {
		String hql="select * from wxpt"+enterId+".prize  order by  prizeTime desc ";
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		System.out.println(hql);
		prizelistByName=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class).list();		
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prizelistByName;
}

@Override
public Prize queryByPrizeId(int enterid, int id) {
	// TODO Auto-generated method stub
	Prize prize=null;
	try {
		

		String hql="select * from wxpt"+enterid+".prize WHERE prizeId =" +id;
		prize = (Prize) this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class).list().get(0);
		
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prize;
}

@Override
public void deletePrize(int enterid, int id) {
	// TODO Auto-generated method stub
	String sql="DELETE FROM wxpt"+enterid+".prize WHERE prizeId="+id;
	System.out.println(sql);
	this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
}

@Override
public void updatePrizeisState(int enterId, int prizeid, int state) {
	// TODO Auto-generated method stub
	String sql = "update wxpt"+enterId+".prize set isstate = "+state+",probability='"+TimeUtil.getTime()+"' where prizeId = "+prizeid;
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
public int queryByNameByTimeCount(int enterId,int i, String year, String month) {
	// TODO Auto-generated method stub
	List<Prize> prizelist= null;
	try {
		String hql="select *  from wxpt"+enterId+".prize where  prizeTime  like '"+year+"%"+month+"%'  and prizegrade="+i;
		//String hql = "from CheckIn where checkuser = '"+username+"' and datediff(now(),str_to_date(checTime,'%Y/%m/%d %H:%i:%s') )=0";
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Prize.class);
		prizelist=query.list();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prizelist.size();
}

	
}
