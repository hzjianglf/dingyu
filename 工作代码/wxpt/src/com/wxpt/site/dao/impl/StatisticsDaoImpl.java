package com.wxpt.site.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.StatisticsDao;
import com.wxpt.site.entity.SiteStatistics;
import com.wxpt.site.entity.User;

public class StatisticsDaoImpl implements StatisticsDao{
	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void saveStatistice(SiteStatistics siteStatistics) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(siteStatistics);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiteStatistics> getAll() {
		// TODO Auto-generated method stub
                               		List<SiteStatistics> stList=new ArrayList<SiteStatistics>();
		SiteStatistics siteStatistics=null;
		List<Object[]> list=new ArrayList<Object[]>();
		String hql="select * from site_statistics where record_time='"+TimeUtil.getYesterdayTime()+"' and type=1";
		try {
			list=sessionFactory.getCurrentSession().createSQLQuery(hql).list();
			for(int i=0;i<list.size();i++){
				siteStatistics=new SiteStatistics();
				siteStatistics.setSId(Integer.parseInt(list.get(i)[0].toString()));
//				siteStatistics.setIps(Integer.parseInt(list.get(i)[1].toString()));
				siteStatistics.setPage(list.get(i)[1].toString());
				siteStatistics.setDomain(list.get(i)[2].toString());
				siteStatistics.setVisitors(Integer.parseInt(list.get(i)[3].toString()));
				siteStatistics.setPageviews(Integer.parseInt(list.get(i)[4].toString()));
				siteStatistics.setIps(Integer.parseInt(list.get(i)[5].toString()));
				siteStatistics.setEntrances(Integer.parseInt(list.get(i)[6].toString()));
				siteStatistics.setOutwards(Integer.parseInt(list.get(i)[7].toString()));
				siteStatistics.setExits(Integer.parseInt(list.get(i)[8].toString()));
				siteStatistics.setStayTime(Double.valueOf(list.get(i)[9].toString()));
				siteStatistics.setExitRate(Double.valueOf(list.get(i)[10].toString()));
				siteStatistics.setRecordTime(list.get(i)[12].toString());
				siteStatistics.setType(Integer.parseInt(list.get(i)[13].toString()));
				String l=list.get(i)[0].toString();
				stList.add(siteStatistics);
				System.out.println(l);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stList;
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		int totalCount=sessionFactory.getCurrentSession().createSQLQuery(hql).list().size();
		return totalCount;
	}

	@Override
	public List<SiteStatistics> getAll(String hql, int start, int number) {
		// TODO Auto-generated method stub
		List<SiteStatistics> pageList = new ArrayList<SiteStatistics>();
		try {
			Query query =  this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(SiteStatistics.class);
			
			/*Session session = super.getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);*/
			query.setFirstResult(start);
			query.setMaxResults(number);
			pageList = (ArrayList<SiteStatistics>) query.list();
			//session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao没有找到对应的文章内容");
		}
		return pageList;
	}
	public int getUserId() {
		String sql = "select user_id from uniqyw.user where user_name='ifinding'";
		int i = 0;
		try {
			i =  Integer.valueOf(this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dao没有找到对应的文章内容");
		}
		return i;
	}
}
