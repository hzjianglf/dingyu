package net.risesoft.soa.info.action;

import cn.com.qimingx.utils.SQLTypeUtils;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.service.InfoFileManager;
import net.risesoft.soa.info.service.InfoServiceCache;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import net.risesoft.soa.info.util.FileContentType;

public class InfoList2 extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private InfoServiceCache infoServiceCache = (InfoServiceCache)SpringUtil.getBean("infoServiceCache");
  private String infoID;
  private String id;
  private int pageSize = 10;

  private int pageNo = 0;
  private String action;

  public String getInfoID()
  {
    return this.infoID;
  }

  public void setInfoID(String infoID) {
    this.infoID = infoID;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getAction() {
    return this.action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String execute()
    throws Exception
  {
    HttpServletRequest request = getServletRequest();
    if ("enable".equals(this.operation)) {
        HttpServletResponse response = getServletResponse();
        response.setContentType("text/html;charset=UTF-8");
       
      
      	 
        try
        {
          InformationIndexDao iid = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
          InformationIndex informationIndex = (InformationIndex)iid.findOne(this.id);
          if (informationIndex != null) {
            String tableName = informationIndex.getTableName();
         
            EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

            EntityManager em = emf.createEntityManager();

           Query query = em.createNativeQuery("update  " + tableName + " set acshow='0' where id = '" + this.id + "'");

            em.getTransaction().begin();

            query.executeUpdate();

            em.getTransaction().commit();
          }

          this.infoServiceCache.clear();

          response.getWriter().write("{\"success\":true,\"message\":\"启用资源成功!\"}");
        } catch (Exception e) {
          e.printStackTrace();
          response.getWriter().write("{\"success\":false,\"message\":\"启用资源失败!\"}");
          return "none";
        }
        return "none";
      }
    if ("delete".equals(this.operation)) {
      HttpServletResponse response = getServletResponse();
      response.setContentType("text/html;charset=UTF-8");
      InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
      List informationFiles = ifm.find(this.id);
     
      try
      {
        InformationIndexDao iid = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
        InformationIndex informationIndex = (InformationIndex)iid.findOne(this.id);
        if (informationIndex != null) {
          String tableName = informationIndex.getTableName();
       
          EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

          EntityManager em = emf.createEntityManager();

       //   Query query = em.createNativeQuery("delete from " + tableName + " where id = '" + this.id + "'");
          Query query = em.createNativeQuery("update "+tableName+" set acshow='1' where acnum=(select acnum from "+tableName+" t where t.id='"+this.id+"')  ");
          em.getTransaction().begin();

          query.executeUpdate();

          em.getTransaction().commit();
          
          
          Query query1 = em.createNativeQuery("update "+tableName+" set acshow='0' where id='"+this.id+"'  ");
          em.getTransaction().begin();

          query1.executeUpdate();

          em.getTransaction().commit();
        }

        this.infoServiceCache.clear();

        response.getWriter().write("{\"success\":true,\"message\":\"启用信息成功!\"}");
      } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().write("{\"success\":false,\"message\":\"启用信息失败!\"}");
        return "none";
      }
      return "none";
    }

    Map map = new HashMap();
    List list = new ArrayList();
    List infos = new ArrayList();
    int count = 0;
    Object header = new ArrayList();
    Information information = InformationList.get(this.infoID);
    InformationDesc informationDesc_title = null;
    String shortacnum="";
    try{
  	  shortacnum=this.getServletRequest().getParameter("shortacnum");
    }catch(Exception e){}
    if (information != null) {
      if (information.getPageSize() > 0) {
        this.pageSize = information.getPageSize();
      }
      String tableName = information.getTableName();
      List informationDescs = information.getElements();
      for(Iterator iterator1 = informationDescs.iterator(); iterator1.hasNext();)
      {
          InformationDesc informationDesc = (InformationDesc)iterator1.next();
          String listName = informationDesc.getListName();
          String columnName = informationDesc.getColumnName();
          if(listName != null && listName.length() > 0)
          {
              if(columnName.equals(information.getTitleField().toUpperCase()))
              {
                  informationDesc.setTitle(true);
                  informationDesc_title = informationDesc;
              }
              list.add(columnName);
              ((List) header).add(informationDesc);
              map.put(columnName, informationDesc);
          }
      }

      String listShowType = information.getListShowStyle();

      String sortField = information.getSortField();

      String sql = "select info_main_index.id, " + tableName + "." + information.getTitleField() + ", info_main_index.createrName, info_main_index.updateTime";

      if ("list".equals(listShowType)) {
        sql = "select info_main_index.id";
        for (int i = 0; i < list.size(); ++i) {
          sql = sql + "," + tableName + "." + ((String)list.get(i));
        }
      }
      String countSql = "select count(info_main_index.id)";

      String tempSql = "";

      if("all".equals(shortacnum)){
    	   tempSql = tempSql + " from info_main_index," + tableName + 
    		        " where info_main_index.id = " + tableName + 
    		        ".id and info_main_index.infoID = '" + this.infoID + "' and  acshow='1' "; 
      }
      else{
    	   tempSql = tempSql + " from info_main_index," + tableName + 
    		        " where info_main_index.id = " + tableName + 
    		        ".id and info_main_index.infoID = '" + this.infoID + "' and  acshow='1' and substr( shortacnum ,0,"+shortacnum.length()+") ='"+shortacnum+"' ";
      }
   
     
      String search = request.getParameter("search");

      if ((search != null) && (search.trim().length() > 0)) {
        tempSql = tempSql + " and " + search;
      }
      String title = request.getParameter("title");
      if ((title != null) && (title.trim().length() > 0)) {
        tempSql = tempSql + " and info_main_index.title like '%" + title + "%'";
      }

      for(Iterator iterator2 = map.keySet().iterator(); iterator2.hasNext();)
      {
          String string = (String)iterator2.next();
          String param = request.getParameter(string.toUpperCase());
          if(param != null && param.length() > 0)
          {
              InformationDesc informationDesc = (InformationDesc)map.get(string);
              if(SQLTypeUtils.isNumberType(informationDesc.getColumnType()) || SQLTypeUtils.isLongType(informationDesc.getColumnType()))
                  tempSql = (new StringBuilder(String.valueOf(tempSql))).append(" and ").append(tableName).append(".").append(string).append(" = ").append(param).toString();
              else
              if(SQLTypeUtils.isDateType(informationDesc.getColumnType()))
                  tempSql = (new StringBuilder(String.valueOf(tempSql))).append(" and to_char(").append(tableName).append(".").append(string).append(",'yyyy-mm-dd') = '").append(param).append("'").toString();
              else
                  tempSql = (new StringBuilder(String.valueOf(tempSql))).append(" and ").append(tableName).append(".").append(string).append(" like '%").append(param).append("%'").toString();
          }
      }

      String createTime_start = request.getParameter("createTime_start");
      if ((createTime_start != null) && (createTime_start.trim().length() > 0) && (!("开始时间".equals(createTime_start)))) {
        tempSql = tempSql + " and info_main_index.createTime >= to_date('" + createTime_start + "', 'yyyy-mm-dd')";
      }

      String createTime_end = request.getParameter("createTime_end");
      if ((createTime_end != null) && (createTime_end.trim().length() > 0) && (!("结束时间".equals(createTime_end)))) {
        tempSql = tempSql + " and info_main_index.createTime <= to_date('" + createTime_end + "', 'yyyy-mm-dd')";
      }

      if ((sortField != null) && (sortField.trim().length() > 0))
        tempSql = tempSql + " order by " + sortField;
      else {
        tempSql = tempSql + " order by info_main_index.updateTime desc";
      }

      sql = sql + tempSql;

      countSql = countSql + tempSql;

      EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

      EntityManager em = emf.createEntityManager();

      Query query = em.createNativeQuery(sql);

      query.setFirstResult(this.pageNo * this.pageSize);

      query.setMaxResults(this.pageSize);

      List resultList = query.getResultList();

      if (resultList.size() > 0)
      {
        int j;
        Object[] object;
        if ("list".equals(listShowType))
          for (j = 0; j < resultList.size(); ++j) {
            object = (Object[])resultList.get(j);
            List temp = new ArrayList();
            for (int i = 0; i < object.length; ++i)
            {
              if (i == 0) {
                temp.add(String.valueOf(object[i]));
              }
              else if (i <= list.size()) {
                InformationDesc informationDesc = (InformationDesc)map.get(((String)list.get(
                  i - 1)).toUpperCase());
                if (object[i] == null) {
                  temp.add("");
                }
                else if (SQLTypeUtils.isDateType(informationDesc
                  .getColumnType())) {
                  String form = informationDesc.getDateFormat();
                  SimpleDateFormat sdf;
                  if ((form != null) && (form.length() > 0)) {
                    sdf = new SimpleDateFormat(
                      form);
                    try {
                      temp.add(sdf.format(object[i]));
                    } catch (Exception e) {
                      e.printStackTrace();
                      return "none";
                    }
                  } else {
                    sdf = new SimpleDateFormat(
                      "yyyy-MM-dd HH:mm:ss");
                    if (informationDesc.getColumnType() == 91) {
                      sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }

                    if (informationDesc.getColumnType() == 92)
                      sdf = new SimpleDateFormat("HH:mm:ss");
                    try
                    {
                      temp.add(sdf.format(object[i]));
                    } catch (Exception e) {
                      e.printStackTrace();
                      return "none";
                    }
                  }
                } else {
                  String value = String.valueOf(object[i]);
                  if (informationDesc.getTextDec() != null)
                    temp.add(informationDesc.getTextDec().replaceAll(informationDesc_title.getColumnName(), value));
                  else {
                    temp.add(String.valueOf(object[i]));
                  }
                }
              }
            }

            infos.add(temp);
          }
        else {
          for (j = 0; j < resultList.size(); ++j) {
            object = (Object[])resultList.get(j);
            for (int i = 0; i < object.length; ++i)
            {
              if (i == 0) {
                infos.add(String.valueOf(object[i]));
              }
              else if (i == 1) {
                if (informationDesc_title != null) {
                  String titleDesc = informationDesc_title.getTextDec();
                  if ((titleDesc != null) && (titleDesc.trim().length() > 0))
                    infos.add(titleDesc.replaceAll(informationDesc_title.getColumnName(), String.valueOf(object[i])));
                  else
                    infos.add(String.valueOf(object[i]));
                }
                else {
                  infos.add(String.valueOf(object[i]));
                }

              }
              else if (i == 2) {
                if (object[i] != null)
                  infos.add(String.valueOf(object[i]));
                else {
                  infos.add("");
                }

              }
              else if (i == 3) {
                SimpleDateFormat sdf = new SimpleDateFormat(
                  "yyyy-MM-dd HH:mm");
                infos.add(sdf.format(object[i]));
              }
            }
          }

        }

        query = em.createNativeQuery(countSql);
        count = Integer.parseInt(query.getResultList().get(0).toString());
      }
      request.setAttribute("header", header);

      request.setAttribute("infos", infos);

      request.setAttribute("count", Integer.valueOf(count));

      if ("list".equals(listShowType)) {
        return listShowType;
      }
    }
    return ((String)"success");
  }
}