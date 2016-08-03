package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RouteInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.RouteInfo
 * @author MyEclipse Persistence Tools
 */

public class RouteInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RouteInfoDAO.class);
	// property constants
	public static final String DESTINATION = "destination";
	public static final String DESCRIPTION = "description";

	public boolean save(RouteInfo transientInstance) {
		log.debug("saving RouteInfo instance");
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

	public boolean update(RouteInfo transientInstance) {
		log.debug("updating RouteInfo instance");
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
	
	public boolean delete(RouteInfo persistentInstance) {
		log.debug("deleting RouteInfo instance");
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

	public RouteInfo findById(java.lang.String id) {
		log.debug("getting RouteInfo instance with id: " + id);
		try {
			RouteInfo instance = (RouteInfo) getSession().get(
					"com.ibm.hibernate.RouteInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RouteInfo instance) {
		log.debug("finding RouteInfo instance by example");
		try {
			List results = getSession()
					.createCriteria("com.ibm.hibernate.RouteInfo")
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
		log.debug("finding RouteInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RouteInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDestination(Object destination) {
		return findByProperty(DESTINATION, destination);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all RouteInfo instances");
		try {
			String queryString = "from RouteInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RouteInfo merge(RouteInfo detachedInstance) {
		log.debug("merging RouteInfo instance");
		try {
			RouteInfo result = (RouteInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RouteInfo instance) {
		log.debug("attaching dirty RouteInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RouteInfo instance) {
		log.debug("attaching clean RouteInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<RouteInfo> findBySource(String source) {
		log.debug("finding the RouteInfo instances");
		try {
			String queryString = "from RouteInfo where source='" + source + "'";
			getSession().clear();
			Query queryObject = getSession().createQuery(queryString);
	
			return queryObject.list();
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<RouteInfo> findAllByPage(int page, int rowsPerPage) {
		log.debug("finding all RouteInfo instances");
		try {
			String queryString = "from RouteInfo order by source asc";
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

		String queryString = "select count(*) from RouteInfo";
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

		String queryString = "select count(*) from RouteInfo";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}	
}