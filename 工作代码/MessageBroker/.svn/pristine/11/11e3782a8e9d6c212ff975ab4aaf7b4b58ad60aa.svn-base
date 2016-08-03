package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TrustedIpaddress entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.TrustedIpaddress
 * @author MyEclipse Persistence Tools
 */

public class TrustedIpaddressDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TrustedIpaddressDAO.class);
	// property constants
	public static final String STARTIP = "startip";
	public static final String ENDIP = "endip";
	public static final String DESCRIPTION = "description";

	public boolean save(TrustedIpaddress transientInstance) {
		log.debug("saving TrustedIpaddress instance");
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

	public boolean update(TrustedIpaddress transientInstance) {
		log.debug("updating TrustedIpaddress instance");
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
	
	public boolean delete(TrustedIpaddress persistentInstance) {
		log.debug("deleting TrustedIpaddress instance");
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

	public TrustedIpaddress findById(java.lang.String id) {
		log.debug("getting TrustedIpaddress instance with id: " + id);
		try {
			TrustedIpaddress instance = (TrustedIpaddress) getSession().get(
					"com.ibm.hibernate.TrustedIpaddress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TrustedIpaddress instance) {
		log.debug("finding TrustedIpaddress instance by example");
		try {
			List results = getSession()
					.createCriteria("com.ibm.hibernate.TrustedIpaddress")
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
		log.debug("finding TrustedIpaddress instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TrustedIpaddress as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStartip(Object startip) {
		return findByProperty(STARTIP, startip);
	}

	public List findByEndip(Object endip) {
		return findByProperty(ENDIP, endip);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all TrustedIpaddress instances");
		try {
			String queryString = "from TrustedIpaddress";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TrustedIpaddress merge(TrustedIpaddress detachedInstance) {
		log.debug("merging TrustedIpaddress instance");
		try {
			TrustedIpaddress result = (TrustedIpaddress) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TrustedIpaddress instance) {
		log.debug("attaching dirty TrustedIpaddress instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TrustedIpaddress instance) {
		log.debug("attaching clean TrustedIpaddress instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TrustedIpaddress> findByTid(String tid) {
		log.debug("finding the TrustedIpaddress instances");
		try {
			String queryString = "from TrustedIpaddress where tid='" + tid + "'";
			getSession().clear();
			Query queryObject = getSession().createQuery(queryString);
	
			return queryObject.list();
		
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}		
	}
	
	@SuppressWarnings("unchecked")
	public List<TrustedIpaddress> findAllByPage(int page, int rowsPerPage) {
		log.debug("finding all TrustedIpaddress instances");
		try {
			String queryString = "from TrustedIpaddress order by startip asc";
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

		String queryString = "select count(*) from TrustedIpaddress";
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

		String queryString = "select count(*) from TrustedIpaddress";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}		
}