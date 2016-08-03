package com.ibm.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Monitormain entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ibm.hibernate.Monitormain
 * @author MyEclipse Persistence Tools
 */

public class MonitorMainDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MonitorMainDAO.class);
	// property constants
	public static final String BROKERNAME = "brokername";
	public static final String EXECGROUPNAME = "execgroupname";
	public static final String FLOWNAME = "flowname";
	public static final String FLOWTYPE = "flowtype";
	public static final String STARTTIME = "starttime";
	public static final String ENDTIME = "endtime";
	public static final String TOTALFLOWTIME = "totalflowtime";
	public static final String RETURNCODE = "returncode";

	public void save(MonitorMain transientInstance) {
		log.debug("saving MonitorMains instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();

			getSession().flush();			
			log.debug("save successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("save failed", re);
			throw re;
		}
	}

	public void update(MonitorMain transientInstance) {
		log.debug("updating MonitorMain instance");
		try {
			getSession().clear();
			
			getSession().beginTransaction();
			getSession().update(transientInstance);
			getSession().getTransaction().commit();

			getSession().flush();
			log.debug("update successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(MonitorMain persistentInstance) {
		log.debug("deleting MonitorMain instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorMain findById(java.lang.String id) {
		log.debug("getting Monitormain instance with id: " + id);
		try {
			MonitorMain instance = (MonitorMain) getSession().get(
					"com.ibm.hibernate.MonitorMain", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(MonitorMain instance) {
		log.debug("finding Monitormain instance by example");
		try {
			List<MonitorMain> results = getSession().createCriteria(
					"com.ibm.hibernate.MonitorMain").add(
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
	public List findStartTime(String value) {
		getSession().clear();
		
		try {
			String queryString = "select starttime from MonitorMain as model where model.localtransactionid='"
					+ value + "'";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findReturnCode(String value) {
		getSession().clear();
		
		try {
			String queryString = "select returncode from MonitorMain as model where model.localtransactionid='"
					+ value + "'";
			Query queryObject = getSession().createQuery(queryString);
						
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findByProperty(String propertyName, Object value,
			int page, int rowsPerPage) {
		log.debug("finding MonitorMain instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MonitorMain as model where model."
					+ propertyName + "= ? order by model.starttime desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);

			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MonitorMain instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MonitorMain as model where model."
					+ propertyName + "= ? order by model.starttime desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findAll() {
		log.debug("finding all MonitorMain instances");
		try {
			String queryString = "from MonitorMain as model order by model.starttime desc";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findCurrentAll() {
		log.debug("finding all MonitorMain instances");
		try {
			Calendar day = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(day.getTime());
			// String date = "2010-09-27";
			String queryString = "from MonitorMain as model where model.starttime like :start order by model.starttime desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("start", "%" + date + "%");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findHistoryAll() {
		log.debug("finding all MonitorMain instances");
		try {
			Calendar day = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(day.getTime());

			String queryString = "from MonitorMain as model where model.starttime not like :start order by model.starttime desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("start", "%" + date + "%");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Map<String, String> getSearchDateCount(int beforeday,
			String returncode) {
		log.debug("finding all MonitorMain instances");
		try {
			Map<String, String> map = new HashMap<String, String>();

			Date d = new Date();
			for (int i = 1; i <= beforeday; i++) {
				String queryString = "select count(*) from MonitorMain as model where model.starttime like :start and model.returncode = '"
						+ returncode + "'";
				Query queryObject = getSession().createQuery(queryString);
				String gettime = getDateBefore(d, i);
				queryObject.setString("start", "%" + gettime + "%");
				map.put(gettime, ((Number) queryObject.uniqueResult())
						.toString());
			}
			return map;

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Map<Integer, String> getSearchHourCount() {
		log.debug("finding all MonitorMain instances");
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			Calendar day = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(day.getTime());
			// String date = "2010-09-27";

			for (int i = 0; i < 24; i++) {
				String queryString = "select count(*) from MonitorMain as model where model.starttime like :start";
				Query queryObject = getSession().createQuery(queryString);
				String hours = null;
				if (i < 10) {
					hours = date + " 0" + i;
				} else {
					hours = date + " " + i;
				}
				queryObject.setString("start", "%" + hours + "%");
				map.put(i, ((Number) queryObject.uniqueResult()).toString());
			}
			return map;

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public String getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now.getTime());
	}

	public long getHistoryResultNumber(String returncode) {
		log.debug("finding all MonitorMain instances");

		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());
		try {
			String queryString = "select count(*) from MonitorMain as model where model.returncode = '"
					+ returncode
					+ "' and model.starttime not like '%"
					+ date
					+ "%'";
			;
			Query queryObject = getSession().createQuery(queryString);
			return ((Number) queryObject.uniqueResult()).intValue();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public long getCurrentResultNumber(String returncode) {
		log.debug("finding all MonitorMain instances");

		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		try {
			String queryString = "select count(*) from MonitorMain as model where model.returncode = '"
					+ returncode
					+ "' and model.starttime like '%"
					+ date
					+ "%'";
			Query queryObject = getSession().createQuery(queryString);
			return ((Number) queryObject.uniqueResult()).intValue();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findMonitorMainByPage(int page, int rowsPerPage) {
		log.debug("finding all MonitorMain instances");

		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		try {

			String queryString = "from MonitorMain as model where model.starttime not like :start order by model.starttime desc";
			getSession().flush();
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("start", "%" + date + "%");

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
	public int getMonitorMainTotalPage(int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		String queryString = "select count(*) from MonitorMain as model where model.starttime not like :start";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("start", "%" + date + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getMonitorMainNum() {
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		String queryString = "select count(*) from MonitorMain as model where model.starttime not like :start";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("start", "%" + date + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

	/**
	 * 条件查询后返回的计划总页数
	 */
	public int getMonitorMainTotalPage(int rowsPerPage, String type,
			String search) {
		int rows = 0;
		String queryString = "select count(*) from MonitorMain as model where model."
				+ type + " like :type";

		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("type", "%" + search + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	/**
	 * 条件查询后返回的计划数据总数
	 */
	public int getMonitorMainNum(String type, String search) {
		int rows = 0;
		String queryString = "select count(*) from MonitorMain as model where model."
				+ type + " like :type";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("type", "%" + search + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findCurrentByPage(int page, int rowsPerPage) {
		log.debug("finding all MonitorMain instances");
		
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());
		try {
			String queryString = "from MonitorMain as model where model.starttime like :start order by model.starttime desc";
			getSession().flush();
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setString("start", "%" + date + "%");

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
	public int getCurrentTotalPage(int rowsPerPage) {
		// System.out.println("rowsPerPage:" + rowsPerPage);
		int rows = 0;
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		String queryString = "select count(*) from MonitorMain as model where model.starttime like :start";
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("start", "%" + date + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();
		// System.out.println("rows:" + rows);

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getCurrentNum() {
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(day.getTime());

		String queryString = "select count(*) from MonitorMain as model where model.starttime like :start";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setString("start", "%" + date + "%");

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

	/**
	 * 共多少页计划数据
	 */
	public int getFailureTotalPage(int rowsPerPage) {

		int rows = 0;
		String queryString = "select count(*) from MonitorMain as model where model.returncode = '1'";
		Query queryObject = getSession().createQuery(queryString);
		rows = ((Long) queryObject.iterate().next()).intValue();

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getFailureNum() {
		String queryString = "select count(*) from MonitorMain as model where model.returncode = '1'";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);
		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<MonitorMain> findPropertyByPage(String brokername,
			String flowname, String sstime, String eetime, int page,
			int rowsPerPage) {
		
		log.debug("finding all MonitorMain instances by properties");
		
		String queryString = null;
		if (sstime.length() < 16) {
			queryString = "from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%' order by model.starttime desc";
		} else {
			queryString = "from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%' and model.starttime between '"
					+ sstime
					+ "' and '"
					+ eetime + "' order by model.starttime desc";
		}
		
		try {
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
	public int getPropertyTotalPage(String brokername, String flowname,
			String sstime, String eetime, int rowsPerPage) {
		String queryString = null;
		int rows = 0;
		if (sstime.length() < 16) {
			queryString = "select count(*) from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%'";
		} else {
			queryString = "select count(*) from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%' and model.starttime between '"
					+ sstime
					+ "' and '"
					+ eetime + "'";
		}
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();

		if (rows % rowsPerPage == 0) {
			return rows / rowsPerPage;
		} else {
			return rows / rowsPerPage + 1;
		}
	}

	public int getPropertyNum(String brokername, String flowname,
			String sstime, String eetime) {
		String queryString = null;
		int rows = 0;
		if (sstime.length() < 16) {
			queryString = "select count(*) from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%'";
		} else {
			queryString = "select count(*) from MonitorMain as model where model.brokername like '%"
					+ brokername
					+ "%' and model.flowname like '%"
					+ flowname
					+ "%' and model.starttime between '"
					+ sstime
					+ "' and '"
					+ eetime + "'";
		}

		Query queryObject = getSession().createQuery(queryString);
		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

    public String getDecimal(String decimal) {
    	BigDecimal bd = new BigDecimal(decimal);
    	bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
    	
    	return bd.toString();
    }
    
	@SuppressWarnings("unchecked")
	public List<WebServicesInvoke> getWSProvide(String sstime, String eetime, int page, int rowsPerPage) {
		
		String queryString = "select distinct flowname from MonitorMain where flowtype='WS' and starttime between '"
					+ sstime + "' and '" + eetime + "'";
		Query queryObject = null;
		List<WebServicesInvoke> wslistInvokes = new ArrayList<WebServicesInvoke>();

		try {
			queryObject = getSession().createQuery(queryString);
			queryObject.setMaxResults(rowsPerPage); // 每页最多显示几条
			queryObject.setFirstResult((page - 1) * rowsPerPage); // 每页从第几条记录开始
			
			for (Iterator i = queryObject.list().iterator(); i.hasNext();) {
				queryString = "select flowname, max(totalflowtime) as maxproctime, min(totalflowtime) as minproctime, avg(totalflowtime) "
						+ "as avgproctime, count(*) as totalprocnumber from MonitorMain as model where model.flowtype='WS' and model.starttime between '"
						+ sstime + "' and '" + eetime + "' and model.flowname='" + (String) i.next() + "' group by model.flowname";

				Query queryWS = getSession().createQuery(queryString);
				WebServicesInvoke invoke = new WebServicesInvoke();

				Object[] obj = (Object[]) queryWS.list().get(0);

				invoke.setFlowname(obj[0].toString());
				invoke.setMaxproctime(obj[1].toString());
				invoke.setMinproctime(obj[2].toString());
				invoke.setAvgproctime(getDecimal(obj[3].toString()));
				invoke.setTotalprocnumber(obj[4].toString());
				int success = getWSSuccessProvide(obj[0].toString(), sstime, eetime);
				invoke.setSuccessnumber(new Integer(success).toString());
				int fail = new Integer(obj[4].toString()).intValue() - success;
				invoke.setFailurenumber(new Integer(fail).toString());
				
				wslistInvokes.add(invoke);
			}

			return wslistInvokes;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}

	}

	/**
	 * 共多少页计划数据
	 */
	public int getWSProvideTotalPage(String sstime, String eetime, int rowsPerPage) {
		int rows = 0;
		
		String queryString = "select count(distinct flowname) from MonitorMain where flowtype='WS' and starttime between '"
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

	public int getWSProvideNum(String sstime, String eetime) {
		
		String queryString = "select count(distinct flowname) from MonitorMain where flowtype='WS' and starttime between '"
					+ sstime + "' and '" + eetime + "'";
		int rows = 0;
		Query queryObject = getSession().createQuery(queryString);

		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

    public int getWSSuccessProvide(String flowname, String sstime, String eetime) { 
 		
    	String queryString = "select count(*) from MonitorMain where flowtype='WS' and starttime between '"
			+ sstime + "' and '" + eetime + "' and flowname='" + flowname + "' and returncode = '0'";
        
        int rows = 0;  
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
 
        return rows;  
    }
    
	public List<WebServicesInvoke> getWSTotalProvide(String sstime, String eetime) {
		List<WebServicesInvoke> totalInvoke = new ArrayList<WebServicesInvoke>();
		WebServicesInvoke invoke = new WebServicesInvoke();
		invoke.setWsprovidenumber(new Integer(getWSProvideNum(sstime, eetime)).toString());

		String queryString = "select max(totalflowtime) as maxproctime, min(totalflowtime) as minproctime, avg(totalflowtime) "
				+ "as avgproctime, count(*) as totalprocnumber from MonitorMain where flowtype='WS' and starttime between '"
					+ sstime + "' and '" + eetime + "'";

		Query queryObject = null;
		try {
			queryObject = getSession().createQuery(queryString);
			Object[] obj = (Object[]) queryObject.list().get(0);
			if (obj[0] != null) {

				invoke.setMaxproctime(obj[0].toString());
				invoke.setMinproctime(obj[1].toString());
				invoke.setAvgproctime(getDecimal(obj[2].toString()));
				invoke.setTotalprocnumber(obj[3].toString());
				int success = getWSTotalSuccessProvide(sstime, eetime);
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

    public int getWSTotalSuccessProvide(String sstime, String eetime) { 
 		
    	String queryString = "select count(*) from MonitorMain where flowtype='WS' and starttime between '"
			+ sstime + "' and '" + eetime + "' and returncode = '0'";
        
        int rows = 0;  
        Query queryObject = getSession().createQuery(queryString);  
        
        rows = ((Long) queryObject.iterate().next()).intValue();  
 
        return rows;  
    }
    
	public List<MonitorMain> findByResult(Object resultcode, int page,
			int rowsPerPage) {
		return findByProperty(RETURNCODE, resultcode, page, rowsPerPage);
	}

	public MonitorMain merge(MonitorMain detachedInstance) {
		log.debug("merging MonitorMain instance");
		try {
			MonitorMain result = (MonitorMain) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorMain instance) {
		log.debug("attaching dirty MonitorMain instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorMain instance) {
		log.debug("attaching clean MonitorMain instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public int getAlertTotalNumber(String flowname, String sstime, String eetime) {
		String queryString = null;
		int rows = 0;

		queryString = "select count(*) from MonitorMain as model where model.starttime between '"
				+ sstime + "' and '" + eetime + "' and model.flowname = '" + flowname + "'";

		Query queryObject = getSession().createQuery(queryString);
		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

	public int getAlertErrorNumber(String flowname, String sstime, String eetime) {
		String queryString = null;
		int rows = 0;

		queryString = "select count(*) from MonitorMain as model where model.returncode='1' and model.starttime between '"
				+ sstime + "' and '" + eetime + "' and model.flowname = '" + flowname + "'";

		Query queryObject = getSession().createQuery(queryString);
		rows = ((Long) queryObject.iterate().next()).intValue();

		return rows;
	}

	public double getAlertWsProvideAvg(String flowname, String sstime, String eetime) {
		String queryString = null;
		double rows = -1;

		queryString = "select avg(totalflowtime) from MonitorMain as model where flowtype='WS' and model.starttime between '"
				+ sstime + "' and '" + eetime + "' and model.flowname = '" + flowname + "'";

		try {
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
	
	
	public List findEndTime(String value) {
		getSession().clear();
		
		try {
			String queryString = "select endtime from MonitorMain as model where model.localtransactionid='"
					+ value + "'";
			Query queryObject = getSession().createQuery(queryString);

			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}