package com.ibm.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorNodeItems entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.MonitorNodeItems
 * @author MyEclipse Persistence Tools
 */

public class MonitorNodeItemsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MonitorNodeItemsDAO.class);
	// property constants
	public static final String LOCALTRANSACTIONID = "localtransactionid";
	public static final String BROKERNAME = "brokername";
	public static final String EXECGROUPNAME = "execgroupname";
	public static final String FLOWNAME = "flowname";
	public static final String SERVICELABEL = "servicelabel";
	public static final String SERVICETYPE = "servicetype";
	public static final String STARTTIME = "starttime";
	public static final String ENDTIME = "endtime";
	public static final String TOTALNODETIME = "totalnodetime";
	public static final String RETURNCODE = "returncode";
	
	public void save(MonitorNodeItems transientInstance) {
		log.debug("saving MonitorNodeItems instance");
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

	public void update(MonitorNodeItems transientInstance) {
		log.debug("updating MonitorMain instance");
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
	
	public void delete(MonitorNodeItems persistentInstance) {
		log.debug("deleting MonitorNodeItems instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findStartTime(String value) {

		try {
			String queryString = "select starttime from MonitorNodeItems as model where model.localtransactionid='" + value + "'";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findReturnCode(String value) {

		try {
			String queryString = "select returncode from MonitorNodeItems as model where model.localtransactionid='" + value + "'";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public MonitorNodeItems findById(java.lang.Integer id) {
		log.debug("getting MonitorNodeItems instance with id: " + id);
		try {
			MonitorNodeItems instance = (MonitorNodeItems) getSession().get(
					"com.ibm.hibernate.MonitorNodeItems", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorNodeItems> findByExample(MonitorNodeItems instance) {
		log.debug("finding MonitorNodeItems instance by example");
		try {
			List<MonitorNodeItems> results = getSession().createCriteria(
					"com.ibm.hibernate.MonitorNodeItems").add(
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
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MonitorNodeItems instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorNodeItems as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByLocaltransactionid(Object localtransactionid) {
		return findByProperty(LOCALTRANSACTIONID, localtransactionid);
	}
	
	@SuppressWarnings("unchecked")
	public List findByBrokername(Object brokername) {
		return findByProperty(BROKERNAME, brokername);
	}

	@SuppressWarnings("unchecked")
	public List findByExecgroupname(Object execgroupname) {
		return findByProperty(EXECGROUPNAME, execgroupname);
	}

	@SuppressWarnings("unchecked")
	public List findByFlowname(Object flowname) {
		return findByProperty(FLOWNAME, flowname);
	}

	@SuppressWarnings("unchecked")
	public List findByNodelabel(Object servicelabel) {
		return findByProperty(SERVICELABEL, servicelabel);
	}

	@SuppressWarnings("unchecked")
	public List findByNodetype(Object servicetype) {
		return findByProperty(SERVICETYPE, servicetype);
	}

	@SuppressWarnings("unchecked")
	public List findByStarttime(Object starttime) {
		return findByProperty(STARTTIME, starttime);
	}

	@SuppressWarnings("unchecked")
	public List findByEndtime(Object endtime) {
		return findByProperty(ENDTIME, endtime);
	}
	
	@SuppressWarnings("unchecked")	
	public List findByTotalnodetime(Object totalnodetime) {
		return findByProperty(TOTALNODETIME, totalnodetime);
	}

	@SuppressWarnings("unchecked")
	public List<MonitorNodeItems> findAll() {
		log.debug("finding all MonitorNodeItems instances");
		try {
			String queryString = "from MonitorNodeItems";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorNodeItems merge(MonitorNodeItems detachedInstance) {
		log.debug("merging MonitorNodeItems instance");
		try {
			MonitorNodeItems result = (MonitorNodeItems) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorNodeItems instance) {
		log.debug("attaching dirty MonitorNodeItems instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorNodeItems instance) {
		log.debug("attaching clean MonitorNodeItems instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
    @SuppressWarnings("unchecked")
	public List<WebServicesInvoke> getWSRequest(String sstime, String eetime, int page, int rowsPerPage) { 
		    	
        String queryString = "select distinct servicelabel from MonitorNodeItems as model where model.starttime between '"
					+ sstime + "' and '" + eetime + "'";
        
        Query queryObject = null;
        List<WebServicesInvoke> wslistInvokes =  new ArrayList<WebServicesInvoke>();
        InterfaceServiceDAO idao = new InterfaceServiceDAO();
        
		try {
			queryObject = getSession().createQuery(queryString); 
			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始
			
			for(Iterator i = queryObject.list().iterator(); i.hasNext();) {				
				queryString = "select servicelabel, max(totalnodetime) as maxproctime, min(totalnodetime) as minproctime, avg(totalnodetime) " 
					+ "as avgproctime, count(*) as totalprocnumber from MonitorNodeItems as model where model.starttime between '"
					+ sstime + "' and '" + eetime + "' and model.servicelabel='" + (String)i.next() + "' group by model.servicelabel";
				
				Query queryWS = getSession().createQuery(queryString);
				WebServicesInvoke invoke = new WebServicesInvoke();
				
				List returnValues = queryWS.list();
				if ( returnValues.size() > 0 )
				{
					Object[] obj = (Object[])returnValues.get(0);
					
//					String mappingname = idao.findMappingName(servicename, obj[0].toString());				
//					if (!mappingname.equalsIgnoreCase("")) {
//						invoke.setServicelabel(mappingname);
//					} else { 
//						invoke.setServicelabel(obj[0].toString());
//					}
					
					invoke.setServicelabel(obj[0].toString());
					invoke.setMaxproctime(obj[1].toString());
					invoke.setMinproctime(obj[2].toString());
					invoke.setAvgproctime(getDecimal(obj[3].toString()));
					invoke.setTotalprocnumber(obj[4].toString());
					int success = getWSSuccessRequest(sstime, eetime, obj[0].toString());
					invoke.setSuccessnumber(new Integer(success).toString());
					int fail = new Integer(obj[4].toString()).intValue() - success;
					invoke.setFailurenumber(new Integer(fail).toString());
					
					wslistInvokes.add(invoke);	
				}

			}		
			
			return wslistInvokes;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}	
  
    }
    
    public String getDecimal(String decimal) {
    	BigDecimal bd = new BigDecimal(decimal);
    	bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
    	
    	return bd.toString();
    }
    
    /** 
     * 共多少页计划数据 
     */  
    public int getWSRequestTotalPage(String sstime, String eetime, int rowsPerPage) {    
        int rows = 0;  
		
        String queryString = "select count(distinct servicelabel) from MonitorNodeItems where starttime between '"
					+ sstime + "' and '" + eetime + "'";
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
        // System.out.println("rows:" + rows);  

        if (rows % rowsPerPage == 0) {  
            return rows / rowsPerPage;  
        } else {  
            return rows / rowsPerPage + 1;  
        }  
    }
    
    public int getWSRequestNum(String sstime, String eetime) { 
		
        String queryString = "select count(distinct servicelabel) from MonitorNodeItems where starttime between '"
					+ sstime + "' and '" + eetime + "'";  
        int rows = 0;  
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
 
        return rows;  
    }

    public int getWSSuccessRequest(String sstime, String eetime, String servicelabel) { 
 		
        String queryString = "select count(*) from MonitorNodeItems where starttime between '"
					+ sstime + "' and '" + eetime + "' and servicelabel ='" 
					+ servicelabel + "' and returncode = '0'";
        
        int rows = 0;  
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
 
        return rows;  
    }
    
    public List<WebServicesInvoke> getWSTotalRequest(String sstime, String eetime) { 
    	List<WebServicesInvoke> totalInvoke =  new ArrayList<WebServicesInvoke>();
    	WebServicesInvoke invoke = new WebServicesInvoke();
    	invoke.setWsrequestnumber(new Integer(getWSRequestNum(sstime, eetime)).toString());
		
    	String queryString = "select max(totalnodetime) as maxproctime, min(totalnodetime) as minproctime, avg(totalnodetime) " 
			+ "as avgproctime, count(*) as totalprocnumber from MonitorNodeItems where starttime between '"
			+ sstime + "' and '" + eetime + "'";  
		         
        Query queryObject = null;        
		try {
			queryObject = getSession().createQuery(queryString); 
			Object[] obj = (Object[])queryObject.list().get(0);
			if (obj[0] != null) {
				
				invoke.setMaxproctime(obj[0].toString());
				invoke.setMinproctime(obj[1].toString());
				invoke.setAvgproctime(getDecimal(obj[2].toString()));						
				invoke.setTotalprocnumber(obj[3].toString());				
				int success = getWSTotalSuccessRequest(sstime, eetime);
				invoke.setSuccessnumber(new Integer(success).toString());	
				int fail = new Integer(obj[3].toString()).intValue() - success;	
				invoke.setFailurenumber(new Integer(fail).toString());
				
				totalInvoke.add(invoke);
				
			}
			
			return totalInvoke;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}	
  
    }

    public int getWSTotalSuccessRequest(String sstime, String eetime) { 
 		
        String queryString = "select count(*) from MonitorNodeItems where starttime between '"
					+ sstime + "' and '" + eetime + "' and returncode = '0'";
        
        int rows = 0;  
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
 
        return rows;  
    }
    
    public double getAlertWsReqAvg(String flowname, String sstime, String eetime) { 
    	String queryString = null;
        double rows = -1;  	
    	
        queryString = "select avg(totalnodetime) from MonitorNodeItems as model where model.starttime between '" 
			+ sstime + "' and '" + eetime + "' and model.flowname = '" + flowname + "'";
        try{         
        	Query queryObject = getSession().createQuery(queryString);
        	if ((Double) queryObject.iterate().next() != null) {
        		rows = ((Double) queryObject.iterate().next()).doubleValue();
        	}
    	
			return rows;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}	
  
    }
    
    public int getAlertWsReqFailure(String flowname, String sstime, String eetime) { 
    	String queryString = null;
    	int rows = 0;  	

        queryString = "select count(*) from MonitorNodeItems as model where model.returncode='1' AND model.starttime between '" 
			+ sstime + "' and '" + eetime + "' and model.flowname = '" + flowname + "'";         
    	
        Query queryObject = getSession().createQuery(queryString); 
        rows = ((Long) queryObject.iterate().next()).intValue();  	
        
        return rows;
    }
}