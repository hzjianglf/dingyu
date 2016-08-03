package com.ibm.hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * BrokerInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ibm.hibernate.BrokerInfo
 * @author MyEclipse Persistence Tools
 */

public class BrokerInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BrokerInfoDAO.class);
	// property constants
	public static final String BNAME = "bname";
	public static final String HOSTNAME = "hostname";
	public static final String PORT = "port";
	public static final String SVRCONN = "svrconn";
	public static final String DESCRIPTION = "description";
	public static final String REGISTRYTIME = "registrytime";
	public static final String PID = "pid";
	public static final String STATUS = "status";
	public static final String INSTANCES = "instances";
	
	public boolean save(BrokerInfo transientInstance) {
		boolean flag = true;
		log.debug("saving BrokerInfo instance");
		
		try {
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			
			getSession().flush();
			getSession().clear();
			log.debug("save successful");
		} catch (RuntimeException re) {
			flag = false;
			//log.error("save failed", re);
			//throw re;
		}
		
		return flag;
	}

	public void update(BrokerInfo transientInstance) {
		log.debug("updating BrokerInfo instance");
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
	
	public void delete(BrokerInfo persistentInstance) {
		log.debug("deleting BrokerInfo instance");
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

	public BrokerInfo findById(java.lang.String id) {
		log.debug("getting BrokerInfo instance with id: " + id);
		try {
			BrokerInfo instance = (BrokerInfo) getSession().get(
					"com.ibm.hibernate.BrokerInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BrokerInfo> findByExample(BrokerInfo instance) {
		log.debug("finding BrokerInfo instance by example");
		try {
			List<BrokerInfo> results = getSession().createCriteria(
					"com.ibm.hibernate.BrokerInfo").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

    @SuppressWarnings("unchecked")
	public List<BrokerInfo> findByQmgrname(String qmgrname) {  
    	String queryString = "from BrokerInfo as model where model.qmgrname ='" + qmgrname + "'";
    	
    	try {
	    	getSession().flush();
			Query queryObject = getSession().createQuery(queryString);		
	        
	        return queryObject.list(); 
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}   

    } 
    
	@SuppressWarnings("unchecked")
	public List<BrokerInfo> findByProperty(String propertyName, Object value) {
		log.debug("finding BrokerInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BrokerInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<BrokerInfo> findByBname(Object bname) {
		return findByProperty(BNAME, bname);
	}

	public List<BrokerInfo> findByHostname(Object hostname) {
		return findByProperty(HOSTNAME, hostname);
	}

	public List<BrokerInfo> findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List<BrokerInfo> findBySvrconn(Object svrconn) {
		return findByProperty(SVRCONN, svrconn);
	}

	public List<BrokerInfo> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<BrokerInfo> findByRegistrytime(Object registrytime) {
		return findByProperty(REGISTRYTIME, registrytime);
	}

	public List<BrokerInfo> findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List<BrokerInfo> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	public List<BrokerInfo> findByInstances(Object instances) {
		return findByProperty(INSTANCES, instances);
	}
	
	@SuppressWarnings("unchecked")
	public List<BrokerInfo> findAll() {
		getSession().clear();
		
		log.debug("finding all BrokerInfo instances");
		try {
			String queryString = "from BrokerInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BrokerInfo merge(BrokerInfo detachedInstance) {
		log.debug("merging BrokerInfo instance");
		try {
			BrokerInfo result = (BrokerInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BrokerInfo instance) {
		log.debug("attaching dirty BrokerInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BrokerInfo instance) {
		log.debug("attaching clean BrokerInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
    @SuppressWarnings("unchecked")
	public List<BrokerInfo> findBrokerInfoByPage(int page, int rowsPerPage) {  
    	getSession().flush();
    	getSession().clear();
    	
    	String queryString = "from BrokerInfo as model order by model.qmgrname asc";
    	
    	try {	    	
			Query queryObject = getSession().createQuery(queryString);		
			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条  
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始  
			return queryObject.list();	        
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}   
    }  

    /** 
     * 共多少页计划数据 
     */  
    public int getBrokerInfoTotalPage(int rowsPerPage) {  
    	getSession().flush();  
        int rows = 0;  		
        String queryString = "select count(*) from BrokerInfo";  
        try {
	        Query queryObject = getSession().createQuery(queryString);                 
	        rows = ((Long) queryObject.iterate().next()).intValue();         
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			//throw re;
		} 
		
        if (rows % rowsPerPage == 0) {  
            return rows / rowsPerPage;  
        } else {  
            return rows / rowsPerPage + 1;  
        }  
    }
    
    public int getBrokerInfoNum() { 
    	getSession().flush();
        int rows = 0;
        String queryString = "select count(*) from BrokerInfo"; 
        try {
        	Query queryObject = getSession().createQuery(queryString);                 
        	rows = ((Long) queryObject.iterate().next()).intValue();  
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			//throw re;
		} 
        return rows;  
    }	
    
    public String getZymc(String num){
    	String acnum="";
    	String[] ary = num.split("_");
    	if(ary.length>2){
    		acnum=ary[1];
    	}
    	getSession().flush();
    	String queryString = "select zymc from info_main_ggmb where acnum = '"+acnum+"'";
    	String zymc="";
        try {
        	Query queryObject = getSession().createSQLQuery(queryString); 
        	zymc = (String) queryObject.uniqueResult();  
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			//throw re;
		} 
        return zymc;  
    }
}