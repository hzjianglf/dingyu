package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * AlertCondition entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.AlertCondition
 * @author MyEclipse Persistence Tools
 */

public class AlertConditionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AlertConditionDAO.class);
	// property constants
	public static final String CONDITIONITEM = "conditionitem";
	public static final String NUMBERITEM = "numberitem";
	public static final String DESCRIPTION = "description";

	public boolean save(AlertCondition transientInstance) {
		log.debug("saving AlertCondition instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();
			log.debug("save successful");
			
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			
			return false;
		}
	}

	public boolean update(AlertCondition transientInstance) {
		log.debug("updating AlertCondition instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().update(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();
			log.debug("update successful");
			
			return true;
		} catch (RuntimeException re) {
			log.error("update failed", re);
			
			return false;
		}
	}
	
	public void delete(AlertCondition persistentInstance) {
		log.debug("deleting AlertCondition instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().delete(persistentInstance);
			getSession().getTransaction().commit();
						
			getSession().flush();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AlertCondition findById(java.lang.String id) {
		log.debug("getting AlertCondition instance with id: " + id);
		try {
			AlertCondition instance = (AlertCondition) getSession().get(
					"com.ibm.hibernate.AlertCondition", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AlertCondition> findByExample(AlertCondition instance) {
		log.debug("finding AlertCondition instance by example");
		try {
			List<AlertCondition> results = getSession().createCriteria(
					"com.ibm.hibernate.AlertCondition").add(
					Example.create(instance)).list();
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
		log.debug("finding AlertCondition instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AlertCondition as model where model."
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
	public List findByConditionitem(Object conditionitem) {
		return findByProperty(CONDITIONITEM, conditionitem);
	}

	@SuppressWarnings("unchecked")
	public List findByNumberitem(Object numberitem) {
		return findByProperty(NUMBERITEM, numberitem);
	}

	@SuppressWarnings("unchecked")
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	@SuppressWarnings("unchecked")
	public List<AlertCondition> findAll() {
		log.debug("finding all AlertCondition instances");
		getSession().flush();
		
		try {
			String queryString = "from AlertCondition";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AlertCondition merge(AlertCondition detachedInstance) {
		log.debug("merging AlertCondition instance");
		try {
			AlertCondition result = (AlertCondition) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AlertCondition instance) {
		log.debug("attaching dirty AlertCondition instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AlertCondition instance) {
		log.debug("attaching clean AlertCondition instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	@SuppressWarnings("unchecked")
	public List<AlertCondition> findAlertConditionByName(String alertname) {
		log.debug("finding the AlertCondition instances");
		getSession().clear();
		
		try {
			String queryString = "from AlertCondition where alertname='" + alertname + "'";			
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}

	@SuppressWarnings("unchecked")
	public List<AlertCondition> findAlertConditionByStatus(String status) {
		log.debug("finding the AlertCondition instances");
		getSession().clear();
		
		try {
			String queryString = "from AlertCondition where status='" + status + "'";			
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<AlertCondition> findAlertConditionByPage(int page, int rowsPerPage) {
		log.debug("finding all AlertCondition instances");
		try {
			String queryString = "from AlertCondition";
			getSession().clear();
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
	public int getAlertConditionTotalPage(int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;

		String queryString = "select count(*) from AlertCondition";
		Query queryObject = getSession().createQuery(queryString);
		
		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getAlertConditionNum() {

		String queryString = "select count(*) from AlertCondition";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}	
}