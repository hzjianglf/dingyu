package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.UserInfo
 * @author MyEclipse Persistence Tools
 */

public class UserInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UserInfoDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String GROUPS = "groups";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";
	public static final String FAILCOUNT = "failcount";
	public static final String LOCKTIME = "locktime";

	public boolean save(UserInfo transientInstance) {
		log.debug("saving UserInfo instance");
		try {
			getSession().flush();
			
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

	public void update(UserInfo transientInstance) {
		log.debug("updating UserInfo instance");
		try {
			
			getSession().beginTransaction();
			getSession().update(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().clear();						
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(UserInfo persistentInstance) {
		log.debug("deleting UserInfo instance");
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

	@SuppressWarnings("unchecked")
	public List<UserInfo> findByUsername(String username) {

		String queryString = "from UserInfo as model where model.username ='"
				+ username + "'";

		try {
			getSession().flush();
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserInfo findById(java.lang.String id) {
		log.debug("getting UserInfo instance with id: " + id);
		try {
			UserInfo instance = (UserInfo) getSession().get(
					"com.ibm.hibernate.UserInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findByExample(UserInfo instance) {
		log.debug("finding UserInfo instance by example");
		try {
			List<UserInfo> results = getSession().createCriteria(
					"com.ibm.hibernate.UserInfo").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findByProperty(String propertyName, Object value) {
		log.debug("finding UserInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<UserInfo> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<UserInfo> findByGroups(Object groups) {
		return findByProperty(GROUPS, groups);
	}

	public List<UserInfo> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<UserInfo> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<UserInfo> findByFailcount(Object failcount) {
		return findByProperty(FAILCOUNT, failcount);
	}

	public List<UserInfo> findByLocktime(Object locktime) {
		return findByProperty(LOCKTIME, locktime);
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findAll() {
		log.debug("finding all UserInfo instances");
		try {
			String queryString = "from UserInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all UserInfo failed", re);
			throw re;
		}
	}

	public UserInfo merge(UserInfo detachedInstance) {
		log.debug("merging UserInfo instance");
		try {
			UserInfo result = (UserInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserInfo instance) {
		log.debug("attaching dirty UserInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserInfo instance) {
		log.debug("attaching clean UserInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findUserInfoByPage(int page, int rowsPerPage) {
		log.debug("finding all UserInfo instances by page");
		try {
			String queryString = "from UserInfo";
			getSession().flush();
			Query queryObject = getSession().createQuery(queryString);
	
			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始
			return queryObject.list();			
		} catch (RuntimeException re) {
			log.error("find all UserInfo failed", re);
			throw re;
		}
	}

	/**
	 * 共多少页计划数据
	 */
	public int getUserInfoTotalPage(int rowsPerPage) {
		getSession().flush();
		int rows = 0;

		String queryString = "select count(*) from UserInfo";
		Query queryObject = getSession().createQuery(queryString);
		
		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getUserInfoNum() {
		getSession().flush();
		String queryString = "select count(*) from UserInfo";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);		

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}
}