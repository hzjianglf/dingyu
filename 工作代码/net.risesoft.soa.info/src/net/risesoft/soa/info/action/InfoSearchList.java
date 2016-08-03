package net.risesoft.soa.info.action;

import egov.appservice.org.model.OrgUnit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.org.manager.PersonManager;

public class InfoSearchList extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private int pageSize;
  private int pageNo;
  private long total;
  private String infoType;
  private String startTime;
  private String endTime;
  private String title;
  private List<String[]> list = new ArrayList();

  public String execute() {
    this.list = new ArrayList();

    EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

    EntityManager em = emf.createEntityManager();

    String sql = "select info_main_index.id,  info_main_index.title, info_main_index.creater, info_main_index.createTime, info_main_index.infoID";
    sql = sql + " from info_main_index where  1 = 1 ";
    if ((this.infoType != null) && (this.infoType.trim().length() > 0)) {
      sql = sql + "and info_main_index.infoType = '" + this.infoType + "'";
    }
    if ((this.title != null) && (this.title.trim().length() > 0) && (!("".equals(this.title)))) {
      sql = sql + " and info_main_index.title like '%" + this.title + "%'";
    }
    if ((this.startTime != null) && (this.startTime.trim().length() > 0) && (!("".equals(this.startTime)))) {
      sql = sql + " and info_main_index.createTime >= to_date('" + this.startTime + " 00:00:00', 'yyyy-mm-dd HH24:mi:ss')";
    }
    if ((this.endTime != null) && (this.endTime.trim().length() > 0) && (!("".equals(this.endTime)))) {
      sql = sql + " and info_main_index.createTime <= to_date('" + this.endTime + " 23:59:59', 'yyyy-mm-dd HH24:mi:ss')";
    }
    sql = sql + " order by info_main_index.updateTime desc";
    try
    {
      Query query = em.createNativeQuery(sql);

      this.total = query.getResultList().size();

      query.setFirstResult(this.pageNo * this.pageSize);

      query.setMaxResults(this.pageSize);

      List resultList = query.getResultList();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      if (resultList.size() > 0)
        for (int j = 0; j < resultList.size(); ++j) {
          Object[] object = (Object[])resultList.get(j);
          String[] string = new String[object.length - 1];
          string[0] = String.valueOf(object[0]);
          string[1] = String.valueOf(object[1]);
          String uid = String.valueOf(object[2]);
          PersonManager pm = (PersonManager)SpringUtil.getBean("personManager");
          OrgUnit ou = pm.getOrganization(uid);
          string[2] = ou.getName();
          string[3] = sdf.format(object[3]);
          this.list.add(string);
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return "toSearchList";
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return this.pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public long getTotal() {
    return this.total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public String getInfoType() {
    return this.infoType;
  }

  public void setInfoType(String infoType) {
    this.infoType = infoType;
  }

  public String getStartTime() {
    return this.startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return this.endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public List<String[]> getList() {
    return this.list;
  }

  public void setList(List<String[]> list) {
    this.list = list;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}