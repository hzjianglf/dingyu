package com.ibm.hibernate;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * InterfaceService entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.InterfaceService
 * @author MyEclipse Persistence Tools
 */

public class InterfaceServiceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(InterfaceServiceDAO.class);
	// property constants
	public static final String SERVICENAME = "servicename";
	public static final String OPERATION = "operation";
	public static final String MAPPINGNAME = "mappingname";
	public static final String DESCRIPTION = "description";

	
	public static final String NAMESPACE = "namespace";
	public static final String ENDPOINT = "endpoint";
	public static final String FLAG = "flag";
	public static final String AVAILABLE = "available";
	public static final String ONLINE = "onLine";


	public void save(InterfaceService transientInstance) {
		log.debug("saving InterfaceService instance");
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

	public void delete(InterfaceService persistentInstance) {
		log.debug("deleting InterfaceService instance");
		try {
			getSession().clear();
			
			Transaction tx = getSession().beginTransaction();
			getSession().delete(persistentInstance);
			tx.commit();
			
			getSession().clear();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(InterfaceService transientInstance) {
		log.debug("updating InterfaceService instance");
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
	
	public void deleteWithServicename(String servicename) {
		log.debug("delete all InterfaceService instances with servicename");

		try {
			getSession().flush();
			String queryString = "delete from InterfaceService where servicename='" + servicename + "'";

			Query queryObject = getSession().createQuery(queryString);
			queryObject.executeUpdate();
			
			getSession().clear();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteByUUID(String uuid) {
		log.debug("delete a InterfaceService instances with uuid");

		try {
			getSession().flush();
			String queryString = "delete from InterfaceService where uuid='" + uuid + "'";

			Query queryObject = getSession().createQuery(queryString);
			queryObject.executeUpdate();
			
			getSession().clear();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	
	
	public InterfaceService findById(java.lang.String id) {
		log.debug("getting InterfaceService instance with id: " + id);
		try {
			InterfaceService instance = (InterfaceService) getSession().get(
					"com.ibm.hibernate.InterfaceService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InterfaceService instance) {
		log.debug("finding InterfaceService instance by example");
		try {
			List results = getSession().createCriteria(
					"com.ibm.hibernate.InterfaceService").add(
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
		log.debug("finding InterfaceService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InterfaceService as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFlowname(Object servicename) {
		return findByProperty(SERVICENAME, servicename);
	}

	public List findByOperation(Object operation) {
		return findByProperty(OPERATION, operation);
	}

	public List findByMappingname(Object mappingname) {
		return findByProperty(MAPPINGNAME, mappingname);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}
	
//	public static final String NAMESPACE = "namespace";
//	public static final String ENDPOINT = "endpoint";
//	public static final String FLAG = "flag";
//	public static final String AVAILABLE = "available";
//	public static final String ONLINE = "onLine";
	
	public List findByNamespace(Object namespace) {
		return findByProperty(NAMESPACE, namespace);
	}	
	
	public List findByEndpoint(Object endpoint) {
		return findByProperty(ENDPOINT, endpoint);
	}		
	
	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}		
	
	public List findByAvailable(Object available) {
		return findByProperty(AVAILABLE, available);
	}		
	
	public List findByOnLine(Object onLine) {
		return findByProperty(ONLINE, onLine);
	}
	
	public List findAll() {
		log.debug("finding all InterfaceService instances");
		try {
			String queryString = "from InterfaceService";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InterfaceService merge(InterfaceService detachedInstance) {
		log.debug("merging InterfaceService instance");
		try {
			InterfaceService result = (InterfaceService) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InterfaceService instance) {
		log.debug("attaching dirty InterfaceService instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InterfaceService instance) {
		log.debug("attaching clean InterfaceService instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<InterfaceService> findOperationByPage(String servicename, int page, int rowsPerPage) {
		log.debug("finding all InterfaceService instances");

		try {

			String queryString = "from InterfaceService as model where model.servicename='" + servicename + "' order by model.operation asc";
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
	
	@SuppressWarnings("unchecked")
	public List<InterfaceService> findServiceByPage(int page, int rowsPerPage) {
		log.debug("finding all InterfaceService instances");

		try {

			String queryString = "from InterfaceService as model order by model.servicename asc";
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

		String queryString = "select count(*) from InterfaceService";
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
		String queryString = "select count(*) from InterfaceService";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}	
	
	/**
	 * 共多少页计划数据
	 */
	public int getOperationTotalPage(String servicename, int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;

		String queryString = "select count(*) from InterfaceService as model where model.servicename='" + servicename + "'";
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getOperationNum(String servicename) {
		String queryString = "select count(*) from InterfaceService as model where model.servicename='" + servicename + "'";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}
	
	public String findMappingName(String servicename, String operation) {
		log.debug("finding all InterfaceService instances");

		try {			
			String queryString = "select mappingname from InterfaceService as model where model.servicename='" + servicename + "' and model.operation='" + operation + "'";			
			Query queryObject = getSession().createQuery(queryString);
			
			if (queryObject.iterate().hasNext()) {
				return ((String) queryObject.iterate().next()).toString();	
			} else 
				return "";		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}		
	
	public String findEndpoint(String servicename) {
		log.debug("finding all InterfaceService instances");

		try {			
			String queryString = "select endpoint from InterfaceService as model where model.servicename='" + servicename + "'";			
			Query queryObject = getSession().createQuery(queryString);
			
			if (queryObject.iterate().hasNext()) {
				return ((String) queryObject.iterate().next()).toString();	
			} else 
				return "";		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}		
	
}