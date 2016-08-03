package net.risesoft.soa.info.service;

import egov.appservice.info.InfoService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import net.risesoft.soa.info.dao.InformationFileDao;
import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.FileContentType;

public class InfoServiceImpl
  implements InfoService
{
  private InfoServiceCache infoServiceCache = (InfoServiceCache)SpringUtil.getBean("infoServiceCache");

  public List<String[]> getList(String infoID, int pageSize)
  {
    String key = this.infoServiceCache.convertParamsToKey(new Object[] { infoID });
    List list = (List)this.infoServiceCache.get(key);
    if (list != null) {
      if (list.size() < pageSize)
        this.infoServiceCache.clear();
      else {
        return list;
      }
    }

    list = new ArrayList();

    Information information = InformationList.get(infoID);

    if (information != null)
    {
      EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

      EntityManager em = emf.createEntityManager();

      String tableName = information.getTableName();

      String sortField = information.getSortField();

      InformationDesc informationDesc_title = null;

      List<InformationDesc> informationDescs = information.getElements();
      for (InformationDesc informationDesc : informationDescs) {
        String listName = informationDesc.getListName();
        String columnName = informationDesc.getColumnName();
        if ((listName == null) || (listName.length() <= 0) || 
          (!(columnName.equals(information.getTitleField()
          .toUpperCase())))) continue;
        informationDesc.setTitle(true);
        informationDesc_title = informationDesc;
      }

      String sql = "select info_main_index.id, " + tableName + "." + information.getTitleField() + ", info_main_index.createrName, info_main_index.updateTime";
      sql = sql + " from info_main_index," + tableName + 
        " where info_main_index.id = " + tableName + 
        ".id and info_main_index.infoID = '" + infoID + "'";
      if ((sortField != null) && (sortField.trim().length() > 0))
        sql = sql + " order by " + sortField;
      else {
        sql = sql + " order by info_main_index.updateTime desc";
      }

      try
      {
        Query query = em.createNativeQuery(sql);

        query.setFirstResult(0 * pageSize);

        query.setMaxResults(pageSize);

        List resultList = query.getResultList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (resultList.size() > 0)
          for (int j = 0; j < resultList.size(); ++j) {
            Object[] object = (Object[])resultList.get(j);
            String[] string = new String[object.length + 1];
            string[0] = String.valueOf(object[0]);
            String value = String.valueOf(object[1]);
            if (informationDesc_title != null) {
              String titleDesc = informationDesc_title.getTextDec();
              if ((titleDesc != null) && (titleDesc.trim().length() > 0)) {
                value = titleDesc.replaceAll(informationDesc_title.getColumnName(), String.valueOf(object[1]));
              }
            }
            string[1] = value;
            string[2] = String.valueOf(object[2]);
            string[3] = sdf.format(object[3]);
            string[4] = information.getName();
            list.add(string);
          }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    this.infoServiceCache.put(key, list);
    return list;
  }

  public List<String[]> getList(String infoID, String condition, int pageSize)
  {
    String key = this.infoServiceCache.convertParamsToKey(new Object[] { infoID + "_s" });
    List list = (List)this.infoServiceCache.get(key);
    if (list != null) {
      if (list.size() < pageSize)
        this.infoServiceCache.clear();
      else {
        return list;
      }
    }

    list = new ArrayList();

    Information information = InformationList.get(infoID);

    if (information != null)
    {
      EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

      EntityManager em = emf.createEntityManager();

      String tableName = information.getTableName();

      String sortField = information.getSortField();

      InformationDesc informationDesc_title = null;

      List<InformationDesc> informationDescs = information.getElements();
      for (InformationDesc informationDesc : informationDescs) {
        String listName = informationDesc.getListName();
        String columnName = informationDesc.getColumnName();
        if ((listName == null) || (listName.length() <= 0) || 
          (!(columnName.equals(information.getTitleField()
          .toUpperCase())))) continue;
        informationDesc.setTitle(true);
        informationDesc_title = informationDesc;
      }

      String sql = "select info_main_index.id, " + tableName + "." + information.getTitleField() + ", info_main_index.createrName, info_main_index.updateTime";
      sql = sql + " from info_main_index," + tableName + 
        " where info_main_index.id = " + tableName + 
        ".id and info_main_index.infoID = '" + infoID + "' and " + condition;
      if ((sortField != null) && (sortField.trim().length() > 0))
        sql = sql + " order by " + sortField;
      else {
        sql = sql + " order by info_main_index.updateTime desc";
      }

      try
      {
        Query query = em.createNativeQuery(sql);

        query.setFirstResult(0 * pageSize);

        query.setMaxResults(pageSize);

        List resultList = query.getResultList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (resultList.size() > 0)
          for (int j = 0; j < resultList.size(); ++j) {
            Object[] object = (Object[])resultList.get(j);
            String[] string = new String[object.length + 1];
            string[0] = String.valueOf(object[0]);
            String value = String.valueOf(object[1]);
            if (informationDesc_title != null) {
              String titleDesc = informationDesc_title.getTextDec();
              if ((titleDesc != null) && (titleDesc.trim().length() > 0)) {
                value = titleDesc.replaceAll(informationDesc_title.getColumnName(), String.valueOf(object[1]));
              }
            }
            string[1] = value;
            string[2] = String.valueOf(object[2]);
            string[3] = sdf.format(object[3]);
            string[4] = information.getName();
            list.add(string);
          }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    this.infoServiceCache.put(key, list);
    return list;
  }

  public List<String[]> getListByCondition(String condition, int pageSize)
  {
    String key = this.infoServiceCache.convertParamsToKey(new Object[] { condition });
    List list = (List)this.infoServiceCache.get(key);
    if (list != null) {
      if (list.size() < pageSize)
        this.infoServiceCache.clear();
      else {
        return list;
      }
    }

    list = new ArrayList();

    EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

    EntityManager em = emf.createEntityManager();

    String sql = "select info_main_index.id,  info_main_index.title, info_main_index.createrName, info_main_index.updateTime, info_main_index.infoID";
    sql = sql + " from info_main_index where " + condition;

    sql = sql + " order by info_main_index.updateTime desc";
    try
    {
      Query query = em.createNativeQuery(sql);

      query.setFirstResult(0 * pageSize);

      query.setMaxResults(pageSize);

      List resultList = query.getResultList();

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      if (resultList.size() > 0)
        for (int j = 0; j < resultList.size(); ++j) {
          Object[] object = (Object[])resultList.get(j);
          String infoID = String.valueOf(object[4]);
          Information information = InformationList.get(infoID);
          if (information != null) {
            InformationDesc informationDesc_title = null;
            List<InformationDesc> informationDescs = information.getElements();
            for (InformationDesc informationDesc : informationDescs) {
              String listName = informationDesc.getListName();
              String columnName = informationDesc.getColumnName();
              if ((listName == null) || (listName.length() <= 0) || 
                (!(columnName.equals(information.getTitleField()
                .toUpperCase())))) continue;
              informationDesc.setTitle(true);
              informationDesc_title = informationDesc;
            }

            String[] string = new String[object.length];
            string[0] = String.valueOf(object[0]);
            String value = String.valueOf(object[1]);
            if (informationDesc_title != null) {
              String titleDesc = informationDesc_title.getTextDec();
              if ((titleDesc != null) && (titleDesc.trim().length() > 0)) {
                value = titleDesc.replaceAll(informationDesc_title.getColumnName(), String.valueOf(object[1]));
              }
            }
            string[1] = "[" + information.getName() + "]" + value;
            string[2] = String.valueOf(object[2]);
            string[3] = sdf.format(object[3]);
            string[4] = information.getName();
            list.add(string);
          } else {
            InformationIndexDao informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
            informationIndexDao.delete(String.valueOf(object[0]));
          }
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    this.infoServiceCache.put(key, list);
    return list;
  }

  public List<String[]> getImgList(String infoID, int pageSize)
  {
    String key = this.infoServiceCache.convertParamsToKey(new Object[] { infoID + "_i" });
    List list = (List)this.infoServiceCache.get(key);
    if (list != null) {
      if (list.size() < pageSize)
        this.infoServiceCache.clear();
      else {
        return list;
      }
    }

    list = new ArrayList();

    Information information = InformationList.get(infoID);

    if (information != null)
    {
      EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

      EntityManager em = emf.createEntityManager();

      String tableName = information.getTableName();

      String sortField = information.getSortField();

      InformationDesc informationDesc_title = null;

      List<InformationDesc> informationDescs = information.getElements();
      for (InformationDesc informationDesc : informationDescs) {
        String listName = informationDesc.getListName();
        String columnName = informationDesc.getColumnName();
        if ((listName == null) || (listName.length() <= 0) || 
          (!(columnName.equals(information.getTitleField()
          .toUpperCase())))) continue;
        informationDesc.setTitle(true);
        informationDesc_title = informationDesc;
      }

      String sql = "select info_main_index.id, info_main_index.title, info_main_index.createrName, info_main_index.updateTime";
      sql = sql + " from info_main_index," + tableName + 
        " where info_main_index.id = " + tableName + 
        ".id and info_main_index.infoID = '" + infoID + "'";
      if ((sortField != null) && (sortField.trim().length() > 0))
        sql = sql + " order by " + sortField;
      else {
        sql = sql + " order by info_main_index.updateTime desc";
      }

      try
      {
        Query query = em.createNativeQuery(sql);

        int pageNo = 0;

        query.setFirstResult(pageNo * pageSize);

        query.setMaxResults(pageSize);

        List resultList = query.getResultList();

        InformationFileDao infoFileDao = (InformationFileDao)SpringUtil.getBean("informationFileDao");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (resultList.size() > 0)
          for (int j = 0; j < resultList.size(); ++j) {
            Object[] object = (Object[])resultList.get(j);
            String id = String.valueOf(object[0]);
            List<InformationFile> infoFiles = infoFileDao.findByInstanceID(id, 0, "file");
            String imgUrl = "";
            for (InformationFile informationFile : infoFiles) {
              String ext = informationFile.getFileNameExt();
              if (FileContentType.isImgType(ext.toLowerCase())) {
                imgUrl = "/info/infoImgShow.action?id=" + informationFile.getId();
                break;
              }
            }
            if (!("".equals(imgUrl))) {
              String[] string = new String[object.length + 1];
              string[0] = String.valueOf(object[0]);
              String value = String.valueOf(object[1]);
              if (informationDesc_title != null) {
                String titleDesc = informationDesc_title.getTextDec();
                if ((titleDesc != null) && (titleDesc.trim().length() > 0)) {
                  value = titleDesc.replaceAll(informationDesc_title.getColumnName(), String.valueOf(object[1]));
                }
              }
              string[1] = value;
              string[2] = String.valueOf(object[2]);
              string[3] = sdf.format(object[3]);
              string[4] = imgUrl;
              list.add(string);
            }
          }
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    this.infoServiceCache.put(key, list);
    return list;
  }
}