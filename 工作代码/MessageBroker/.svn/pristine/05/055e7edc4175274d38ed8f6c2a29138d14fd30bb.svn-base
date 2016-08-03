package com.ibm.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessService entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.BusinessService
 * @author MyEclipse Persistence Tools
 */

public class BusinessServiceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BusinessServiceDAO.class);
	// property constants
	public static final String FLOWNAME = "flowname";
	public static final String DESCRIPTION = "description";

	public void save(BusinessService transientInstance) {
		log.debug("saving BusinessService instance");
		try {			
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();
			getSession().flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessService persistentInstance) {
		log.debug("deleting BusinessService instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().delete(persistentInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessService findById(java.lang.String id) {
		log.debug("getting BusinessService instance with id: " + id);
		try {
			BusinessService instance = (BusinessService) getSession().get(
					"com.ibm.hibernate.BusinessService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BusinessService instance) {
		log.debug("finding BusinessService instance by example");
		try {
			List results = getSession().createCriteria(
					"com.ibm.hibernate.BusinessService").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BusinessService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessService as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFlowname(Object flowname) {
		return findByProperty(FLOWNAME, flowname);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all BusinessService instances");
		try {
			String queryString = "from BusinessService";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessService merge(BusinessService detachedInstance) {
		log.debug("merging BusinessService instance");
		try {
			BusinessService result = (BusinessService) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessService instance) {
		log.debug("attaching dirty BusinessService instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessService instance) {
		log.debug("attaching clean BusinessService instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessService> findServiceByPage(int page, int rowsPerPage) {
		log.debug("finding all BusinessService instances");

		try {

			String queryString = "from BusinessService as model order by model.servicename asc";
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
	public int getServiceTotalPage(int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;

		String queryString = "select count(*) from BusinessService";
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getServiceNum() {
		String queryString = "select count(*) from BusinessService";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findServices() {
		log.debug("finding all BusinessService instances");

		try {

			String queryString = "select servicename from BusinessService as model order by model.servicename asc";			
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String findMessageFlow(String servicename) {
		log.debug("finding all BusinessService instances");

		try {

			String queryString = "select flowname from BusinessService as model where model.servicename='" + servicename + "'";			
			Query queryObject = getSession().createQuery(queryString);
			
			return ((String) queryObject.iterate().next()).toString(); 			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
}