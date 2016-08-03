package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * AlertInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.AlertInfo
 * @author MyEclipse Persistence Tools
 */

public class AlertInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AlertInfoDAO.class);
	// property constants
	public static final String CREATETIME = "createtime";
	public static final String CONDITIONITEM = "conditionitem";
	public static final String NUMBERITEM = "numberitem";
	public static final String CURCOUNT = "curcount";
	public static final String DESCRIPTION = "description";

	public void save(AlertInfo transientInstance) {
		log.debug("saving AlertInfo instance");
		try {
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();	
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AlertInfo persistentInstance) {
		log.debug("deleting AlertInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlertInfo findById(java.lang.String id) {
		log.debug("getting AlertInfo instance with id: " + id);
		try {
			AlertInfo instance = (AlertInfo) getSession().get(
					"com.ibm.hibernate.AlertInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AlertInfo> findByExample(AlertInfo instance) {
		log.debug("finding AlertInfo instance by example");
		try {
			List<AlertInfo> results = getSession().createCriteria(
					"com.ibm.hibernate.AlertInfo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AlertInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AlertInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByCreatetime(Object createtime) {
		return findByProperty(CREATETIME, createtime);
	}

	@SuppressWarnings("unchecked")
	public List findByConditionitem(Object conditionitem) {
		return findByProperty(CONDITIONITEM, conditionitem);
	}

	@SuppressWarnings("unchecked")
	public List findByNumberitem(Object numberitem) {
		return findByProperty(NUMBERITEM, numberitem);
	}

	@SuppressWarnings("unchecked")
	public List findByCurcount(Object curcount) {
		return findByProperty(CURCOUNT, curcount);
	}

	@SuppressWarnings("unchecked")	
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	@SuppressWarnings("unchecked")
	public List<AlertInfo> findAll() {
		getSession().flush();
		log.debug("finding all AlertInfo instances");
		try {
			String queryString = "from AlertInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AlertInfo merge(AlertInfo detachedInstance) {
		log.debug("merging AlertInfo instance");
		try {
			AlertInfo result = (AlertInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AlertInfo instance) {
		log.debug("attaching dirty AlertInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlertInfo instance) {
		log.debug("attaching clean AlertInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AlertInfo> findAlertInfoByPage(int page, int rowsPerPage) {
		log.debug("finding all AlertInfo instances");		
		getSession().flush();
		
		try {
			String queryString = "from AlertInfo as model order by model.createtime desc";
			getSession().flush();
			Query queryObject = getSession().createQuery(queryString);
	
			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		

	}

	/**
	 * 共多少页计划数据
	 */
	public int getAlertInfoTotalPage(int rowsPerPage) {
		getSession().flush();
		int rows = 0;

		String queryString = "select count(*) from AlertInfo";
		Query queryObject = getSession().createQuery(queryString);
		
		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getAlertInfoNum() {
		getSession().flush();
		String queryString = "select count(*) from AlertInfo";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}
}