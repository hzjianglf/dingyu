package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * GroupInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.GroupInfo
 * @author MyEclipse Persistence Tools
 */

public class GroupInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GroupInfoDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	public void save(GroupInfo transientInstance) {
		log.debug("saving GroupInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GroupInfo persistentInstance) {
		log.debug("deleting GroupInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GroupInfo findById(java.lang.String id) {
		log.debug("getting GroupInfo instance with id: " + id);
		try {
			GroupInfo instance = (GroupInfo) getSession().get(
					"com.ibm.hibernate.GroupInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GroupInfo> findByExample(GroupInfo instance) {
		log.debug("finding GroupInfo instance by example");
		try {
			List<GroupInfo> results = getSession().createCriteria(
					"com.ibm.hibernate.GroupInfo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GroupInfo> findByProperty(String propertyName, Object value) {
		log.debug("finding GroupInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GroupInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GroupInfo> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	@SuppressWarnings("unchecked")
	public List<GroupInfo> findAll() {
		getSession().flush();
		
		log.debug("finding all GroupInfo instances");
		try {
			String queryString = "from GroupInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GroupInfo merge(GroupInfo detachedInstance) {
		log.debug("merging GroupInfo instance");
		try {
			GroupInfo result = (GroupInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GroupInfo instance) {
		log.debug("attaching dirty GroupInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GroupInfo instance) {
		log.debug("attaching clean GroupInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}