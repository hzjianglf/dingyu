package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * PartofthroughoutControl entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.PartofthroughoutControl
 * @author MyEclipse Persistence Tools
 */

public class PartofthroughoutControlDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PartofthroughoutControlDAO.class);
	// property constants
	public static final String PARTOFINTERVAL = "partofinterval";
	public static final String PARTOFUNIT = "partofunit";
	public static final String PARTOFTHRESHOLD = "partofthreshold";
	public static final String STATUS = "status";

	public boolean save(PartofthroughoutControl transientInstance) {
		log.debug("saving PartofthroughoutControl instance");
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

	public boolean update(PartofthroughoutControl transientInstance) {
		log.debug("updating PartofthroughoutControl instance");
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
	
	public boolean delete(PartofthroughoutControl persistentInstance) {
		log.debug("deleting PartofthroughoutControl instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().delete(persistentInstance);
			getSession().getTransaction().commit();
						
			getSession().flush();
			log.debug("delete successful");
			
			return true;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			return false;
		}
	}

	public PartofthroughoutControl findById(java.lang.String id) {
		log.debug("getting PartofthroughoutControl instance with id: " + id);
		try {
			PartofthroughoutControl instance = (PartofthroughoutControl) getSession()
					.get("com.ibm.hibernate.PartofthroughoutControl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PartofthroughoutControl instance) {
		log.debug("finding PartofthroughoutControl instance by example");
		try {
			List results = getSession()
					.createCriteria("com.ibm.hibernate.PartofthroughoutControl")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PartofthroughoutControl instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PartofthroughoutControl as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPartofinterval(Object partofinterval) {
		return findByProperty(PARTOFINTERVAL, partofinterval);
	}

	public List findByPartofunit(Object partofunit) {
		return findByProperty(PARTOFUNIT, partofunit);
	}

	public List findByPartofthreshold(Object partofthreshold) {
		return findByProperty(PARTOFTHRESHOLD, partofthreshold);
	}

	public List findAll() {
		log.debug("finding all PartofthroughoutControl instances");
		try {
			String queryString = "from PartofthroughoutControl";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PartofthroughoutControl merge(
			PartofthroughoutControl detachedInstance) {
		log.debug("merging PartofthroughoutControl instance");
		try {
			PartofthroughoutControl result = (PartofthroughoutControl) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PartofthroughoutControl instance) {
		log.debug("attaching dirty PartofthroughoutControl instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PartofthroughoutControl instance) {
		log.debug("attaching clean PartofthroughoutControl instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PartofthroughoutControl> findByFlowName(String flowname) {
		log.debug("finding the PartofthroughoutControl instances");
		try {
			String queryString = "from PartofthroughoutControl where flowname='" + flowname + "'";
			getSession().clear();
			Query queryObject = getSession().createQuery(queryString);
	
			return queryObject.list();
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<PartofthroughoutControl> findAllByPage(int page, int rowsPerPage) {
		log.debug("finding all PartofthroughoutControl instances");
		try {
			String queryString = "from PartofthroughoutControl";
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
	public int getTotalPage(int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;

		String queryString = "select count(*) from PartofthroughoutControl";
		Query queryObject = getSession().createQuery(queryString);
		
		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getTotalNum() {

		String queryString = "select count(*) from PartofthroughoutControl";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}		
}