package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EsbTimeout entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.EsbTimeout
 * @author MyEclipse Persistence Tools
 */

public class EsbTimeoutDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EsbTimeoutDAO.class);
	// property constants
	public static final String NODETYPE = "nodetype";
	public static final String NODETIMEOUT = "nodetimeout";

	public boolean save(EsbTimeout transientInstance) {
		log.debug("saving EsbTimeout instance");
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
			re.printStackTrace();
			return false;
		}
	}
	
	public void delete(EsbTimeout persistentInstance) {
		log.debug("deleting EsbTimeout instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EsbTimeout findById(java.lang.String id) {
		log.debug("getting EsbTimeout instance with id: " + id);
		try {
			EsbTimeout instance = (EsbTimeout) getSession().get(
					"com.ibm.hibernate.EsbTimeout", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EsbTimeout instance) {
		log.debug("finding EsbTimeout instance by example");
		try {
			List results = getSession()
					.createCriteria("com.ibm.hibernate.EsbTimeout")
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
		log.debug("finding EsbTimeout instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EsbTimeout as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNodetype(Object nodetype) {
		return findByProperty(NODETYPE, nodetype);
	}

	public List findByNodetimeout(Object nodetimeout) {
		return findByProperty(NODETIMEOUT, nodetimeout);
	}

	public List findAll() {
		log.debug("finding all EsbTimeout instances");
		try {
			String queryString = "from EsbTimeout";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EsbTimeout merge(EsbTimeout detachedInstance) {
		log.debug("merging EsbTimeout instance");
		try {
			EsbTimeout result = (EsbTimeout) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EsbTimeout instance) {
		log.debug("attaching dirty EsbTimeout instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EsbTimeout instance) {
		log.debug("attaching clean EsbTimeout instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public String findByType(String type) {
		log.debug("finding all EsbTimeout instances");
		try {
			String queryString = "select nodetimeout from EsbTimeout where nodetype='" + type + "'";
			getSession().clear();
			Query queryObject = getSession().createQuery(queryString);
			
			if (queryObject.iterate().hasNext()) {
				return ((String)queryObject.iterate().next());
			} else {
				return "";
			}
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}	
}