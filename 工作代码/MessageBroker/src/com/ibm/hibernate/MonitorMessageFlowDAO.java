package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorMessageFlow entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.MonitorMessageFlow
 * @author MyEclipse Persistence Tools
 */

public class MonitorMessageFlowDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MonitorMessageFlowDAO.class);
	// property constants
	public static final String FLOWNAME = "flowname";	
	public static final String EVENTSOURCEADDRESS = "eventsourceaddress";
	public static final String EVENTNAME = "eventname";
	public static final String CREATIONTIME = "creationtime";
	public static final String COUNTER = "counter";
	public static final String LOCALTRANSACTIONID = "localtransactionid";
	public static final String NODELABEL = "nodelabel";
	public static final String NODETYPE = "nodetype";
	public static final String TERMINAL = "terminal";
	public static final String APPDATATYPE = "appdatatype";
	public static final String APPDAtAFIlEPATH = "appdatafilepath";

	public void save(MonitorMessageFlow transientInstance) {
		log.debug("saving MonitorMessageFlow instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().flush();
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(MonitorMessageFlow transientInstance) {
		log.debug("updating MonitorMessageFlow instance");
		try {		
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().update(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().flush();
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void delete(MonitorMessageFlow persistentInstance) {
		log.debug("deleting MonitorMessageFlow instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMessageFlow> findByExample(MonitorMessageFlow instance) {
		log.debug("finding MonitorMessageFlow instance by example");
		try {
			List<MonitorMessageFlow> results = getSession().createCriteria(
					"com.ibm.hibernate.MonitorMessageFlow").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public MonitorMessageFlow findById(java.lang.Integer id) {
		log.debug("getting MonitorMessageFlow instance with id: " + id);
		try {
			MonitorMessageFlow instance = (MonitorMessageFlow) getSession()
					.get("com.ibm.hibernate.MonitorMessageFlow", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMessageFlow> findByProperty(String propertyName, Object value) {
		log.debug("finding MonitorMessageFlow instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorMessageFlow as model where model."
					+ propertyName + "= ? " + "order by model.counter asc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<MonitorMessageFlow> findByFlowname(Object flowname) {
		return findByProperty(FLOWNAME, flowname);
	}

	public List<MonitorMessageFlow> findByEventsourceaddress(Object eventsourceaddress) {
		return findByProperty(EVENTSOURCEADDRESS, eventsourceaddress);
	}

	public List<MonitorMessageFlow> findByEventname(Object eventname) {
		return findByProperty(EVENTNAME, eventname);
	}

	public List<MonitorMessageFlow> findByCreationtime(Object creationtime) {
		return findByProperty(CREATIONTIME, creationtime);
	}

	public List<MonitorMessageFlow> findByCounter(Object counter) {
		return findByProperty(COUNTER, counter);
	}

	public List<MonitorMessageFlow> findByLocaltransactionid(Object localtransactionid) {
		return findByProperty(LOCALTRANSACTIONID, localtransactionid);
	}

	public List<MonitorMessageFlow> findByNodelabel(Object nodelabel) {
		return findByProperty(NODELABEL, nodelabel);
	}

	public List<MonitorMessageFlow> findByNodetype(Object nodetype) {
		return findByProperty(NODETYPE, nodetype);
	}

	public List<MonitorMessageFlow> findByTerminal(Object terminal) {
		return findByProperty(TERMINAL, terminal);
	}

	public List<MonitorMessageFlow> findByAppdatafilepath(Object appdatafilepath) {
		return findByProperty(APPDAtAFIlEPATH, appdatafilepath);
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMessageFlow> findAll() {
		log.debug("finding all MonitorMessageFlow instances");
		try {
			String queryString = "from MonitorMessageFlow";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorMessageFlow merge(MonitorMessageFlow detachedInstance) {
		log.debug("merging MonitorMessageFlow instance");
		try {
			MonitorMessageFlow result = (MonitorMessageFlow) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorMessageFlow instance) {
		log.debug("attaching dirty MonitorMessageFlow instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorMessageFlow instance) {
		log.debug("attaching clean MonitorMessageFlow instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}