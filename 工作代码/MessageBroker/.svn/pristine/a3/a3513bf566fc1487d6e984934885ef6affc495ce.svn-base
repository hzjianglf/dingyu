package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TotalthroughoutControl entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.TotalthroughoutControl
 * @author MyEclipse Persistence Tools
 */

public class TotalthroughoutControlDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TotalthroughoutControlDAO.class);
	// property constants
	public static final String TOTALINTERVAL = "totalinterval";
	public static final String TOTALUNIT = "totalunit";
	public static final String TOTALTHRESHOLD = "totalthreshold";

	public boolean save(TotalthroughoutControl transientInstance) {
		log.debug("saving TotalthroughoutControl instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().saveOrUpdate(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().flush();
			log.debug("save successful");
			
			return true;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			return false;
		}
	}

	public void delete(TotalthroughoutControl persistentInstance) {
		log.debug("deleting TotalthroughoutControl instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TotalthroughoutControl findById(java.lang.String id) {
		log.debug("getting TotalthroughoutControl instance with id: " + id);
		try {
			TotalthroughoutControl instance = (TotalthroughoutControl) getSession()
					.get("com.ibm.hibernate.TotalthroughoutControl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TotalthroughoutControl instance) {
		log.debug("finding TotalthroughoutControl instance by example");
		try {
			List results = getSession()
					.createCriteria("com.ibm.hibernate.TotalthroughoutControl")
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
		log.debug("finding TotalthroughoutControl instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TotalthroughoutControl as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTotalinterval(Object totalinterval) {
		return findByProperty(TOTALINTERVAL, totalinterval);
	}

	public List findByTotalunit(Object totalunit) {
		return findByProperty(TOTALUNIT, totalunit);
	}

	public List findByTotalthreshold(Object totalthreshold) {
		return findByProperty(TOTALTHRESHOLD, totalthreshold);
	}

	public List findAll() {
		log.debug("finding all TotalthroughoutControl instances");
		try {
			String queryString = "from TotalthroughoutControl";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TotalthroughoutControl merge(TotalthroughoutControl detachedInstance) {
		log.debug("merging TotalthroughoutControl instance");
		try {
			TotalthroughoutControl result = (TotalthroughoutControl) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TotalthroughoutControl instance) {
		log.debug("attaching dirty TotalthroughoutControl instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TotalthroughoutControl instance) {
		log.debug("attaching clean TotalthroughoutControl instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public TotalthroughoutControl findByID(String id) {
		log.debug("finding all TotalthroughoutControl instances");
		try {
			String queryString = "from TotalthroughoutControl where tids='" + id + "'";
			getSession().clear();
			Query queryObject = getSession().createQuery(queryString);
											
			if (queryObject.iterate().hasNext()) {
				return (TotalthroughoutControl)queryObject.iterate().next();
			} else {
				return null;
			}			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}		
}