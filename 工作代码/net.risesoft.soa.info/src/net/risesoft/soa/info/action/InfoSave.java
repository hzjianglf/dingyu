package net.risesoft.soa.info.action;

import cn.com.qimingx.utils.SQLTypeUtils;

import com.opensymphony.xwork2.ActionContext;

import java.io.File;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import net.risesoft.soa.ac.manager.ActorManager;
import net.risesoft.soa.ac.manager.ResourceManager;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.UUID;
import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.service.InfoFileManager;
import net.risesoft.soa.info.service.InfoServiceCache;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.FileContentType;
import net.risesoft.soa.org.manager.PersonManager;
import net.risesoft.soa.org.model.Person;

public class InfoSave extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private InfoServiceCache infoServiceCache = (InfoServiceCache)SpringUtil.getBean("infoServiceCache");
  private String id;
  private String infoID;
  private String infoHtml;
  private File file;
  private String fileName;

  public String getInfoHtml()
  {
    return this.infoHtml;
  }

  public void setInfoHtml(String infoHtml) {
    this.infoHtml = infoHtml;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFileFileName() {
    return this.fileName;
  }

  public void setFileFileName(String fileName) {
    this.fileName = fileName;
  }

  public String execute()
    throws Exception
  {
    HttpServletRequest request = getServletRequest();

    HttpServletResponse response = getServletResponse();

    response.setContentType("text/html;charset=UTF-8");

    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");

    if (su == null) {
      response.getWriter().write("{\"success\":false,\"message\":\"用户session丢失,请刷新页面重新登录!\"}");
      return "none";
    }

    if (this.fileName != null) {
      String ext = this.fileName.substring(this.fileName.lastIndexOf("."));
      if ((!(FileContentType.isOfficeType(ext.toLowerCase()))) || (!(FileContentType.isPdfType(ext.toLowerCase())))) {
        response.getWriter().write("{\"success\":false,\"message\":\"正文格式错误,请上传OFFICE文档!\"}");
        return "none";
      }
    }

    PersonManager pm = (PersonManager)SpringUtil.getBean("personManager");

    Person person = pm.get(su.getUserUID());

    this.infoID = request.getParameter("infoID");

    this.id = request.getParameter("id");

    Information information = InformationList.get(request.getParameter("infoID"));

    String titleField = information.getTitleField();

    String tableName = information.getTableName();

    List informationDescs = information.getElements();

    List list = new ArrayList();

    Map map = new HashMap();

    for(Iterator iterator = informationDescs.iterator(); iterator.hasNext();)
    {
        InformationDesc informationDesc = (InformationDesc)iterator.next();
        String formName = informationDesc.getFormName();
        if(formName != null && formName.length() > 0)
        {
            if(formName.toUpperCase().equals(titleField.toUpperCase()))
                titleField = formName;
            map.put(informationDesc.getColumnName(), informationDesc);
            list.add(formName);
        }
    }

    String title = request.getParameter(titleField);

    if ((title == null) || (title.trim().length() == 0)) {
      response.getWriter().write("{\"success\":false,\"message\":\"标题字段不能为空!\"}");
      return "none";
    }

    String sql = "";

    InformationIndexDao informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");

    InformationIndex informationIndex = (InformationIndex)informationIndexDao.findOne(this.id);

    boolean isExit = false;
    
    
    
 String shortacnum   ="";
 
 try{
	 shortacnum= (String) this.getServletRequest().getSession().getAttribute("shortacnum");
	 this.getServletRequest().getSession().removeAttribute("shortacnum");
	
 }catch(Exception e){}
    
 System.out.print("<<<<<<<<<<<<<shortacnum<<<<<<<<"+shortacnum);
 
//    //资源别名（ 存储资源编号）
//  String   sql123 = "select t.aliasname from ac_resource t where t.id='"+infoID+"'";
//  EntityManagerFactory  emf1 = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
//  EntityManager em1 = emf1.createEntityManager();
//
//  Query query1 = em1.createNativeQuery(sql123);
// List searchList = query1.getResultList();
//  String aliasname=(String)searchList.get(0);
 
 
// ResourceManager s=(ResourceManager)SpringUtil.getBean("resourceManager");
//
// 
//EntityManagerFactory  emf1 = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
// ActorManager pm1 = emf1.createEntityManager();


    
    if (informationIndex == null) {
      informationIndex = new InformationIndex();
      sql = "insert into " + tableName + "(id,shortacnum ";
      String names = "";
      String values = "";
      for (int i = 0; i < list.size(); ++i) {
        names = names + "," + ((String)list.get(i));
        values = values + ",:" + ((String)list.get(i));
      }
      sql = sql + names + ") values('" + this.id + "','"+shortacnum+"'" + values + ")";
      
      
    } else {
      sql = "update " + tableName + " set ";
      String values = "";
      for (int i = 0; i < list.size(); ++i) {
        if (i == list.size() - 1)
          values = values + ((String)list.get(i)) + " = :" + ((String)list.get(i));
        else {
          values = values + ((String)list.get(i)) + " = :" + ((String)list.get(i)) + ",";
        }
      }
      sql = sql + values + " where id = '" + this.id + "'";

      isExit = true;
    }

    EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

    EntityManager em = emf.createEntityManager();

    
    Query query = em.createNativeQuery(sql);

    NumberFormat nf2 = NumberFormat.getInstance();

    for(Iterator iterator1 = list.iterator(); iterator1.hasNext();)
    {
        String string = (String)iterator1.next();
        InformationDesc informationDesc = (InformationDesc)map.get(string.toUpperCase());
        if(SQLTypeUtils.isNumberType(informationDesc.getColumnType()))
        {
            if(request.getParameter(string) != null && request.getParameter(string).trim().length() > 0)
                try
                {
                    query.setParameter(string, nf2.parse(request.getParameter(string)));
                }
                catch(Exception _ex)
                {
                    response.getWriter().write("{\"success\":false,\"message\":\"\u6570\u503C\u8F6C\u6362\u9519\u8BEF,\u4E0E\u6570\u636E\u5E93\u4E0D\u5339\u914D!\"}");
                    return "none";
                }
            else
                query.setParameter(string, Integer.valueOf(0));
        } else
        if(SQLTypeUtils.isDateType(informationDesc.getColumnType()))
        {
            if(request.getParameter(string) != null && request.getParameter(string).trim().length() > 0)
            {
                String form = informationDesc.getDateFormat();
                if(form != null && form.length() > 0)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(form);
                    try
                    {
                        query.setParameter(string, sdf.parse(request.getParameter(string)));
                    }
                    catch(Exception _ex)
                    {
                        response.getWriter().write("{\"success\":false,\"message\":\"\u65E5\u671F\u8F6C\u6362\u9519\u8BEF!\"}");
                        return "none";
                    }
                } else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(informationDesc.getColumnType() == 91)
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if(informationDesc.getColumnType() == 92)
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    try
                    {
                        query.setParameter(string, sdf.parse(request.getParameter(string)));
                    }
                    catch(Exception _ex)
                    {
                        response.getWriter().write("{\"success\":false,\"message\":\"\u65E5\u671F\u8F6C\u6362\u9519\u8BEF!\"}");
                        return "none";
                    }
                }
            } else
            if(request.getParameter(string) == null)
                query.setParameter(string, "");
            else
                query.setParameter(string, request.getParameter(string));
        } else
        if(request.getParameter(string) == null)
            query.setParameter(string, "");
        else
            query.setParameter(string, request.getParameter(string));
    }

    informationIndex.setId(this.id);
    informationIndex.setInfoID(this.infoID);
    informationIndex.setTitle(title);
    informationIndex.setTableName(information.getTableName());
    informationIndex.setModule(information.getModule());
    informationIndex.setInfoType(information.getInfoType());
    informationIndex.setCreater(su.getUserUID());
    informationIndex.setCreaterName(su.getUserName());
    informationIndex.setDn(person.getDn());
    if (!(isExit)) {
      informationIndex.setCreateTime(Calendar.getInstance().getTime());
    }
    informationIndex.setUpdateTime(Calendar.getInstance().getTime());
    try
    {
    	InfoFileManager ifm;
        List ifs;
    	if(infoHtml != null && infoHtml.length() > 0)
        {
            ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
            ifs = ifm.find(id, 0, "html");
            InformationFile informationFile;
            if(ifs.size() > 0)
            {
                informationFile = (InformationFile)ifs.get(0);
            } else
            {
                informationFile = new InformationFile();
                informationFile.setId(UUID.generateUUID());
                informationFile.setCreateDateTime(Calendar.getInstance().getTime());
            }
            informationFile.setInstanceID(id);
            informationFile.setModule("riseinfo");
            if(person != null)
            {
                informationFile.setCreater(person.getId());
                informationFile.setCreaterName(person.getName());
            }
            informationFile.setUpdateDateTime(Calendar.getInstance().getTime());
            informationFile.setFileType("html");
            byte bytes[] = infoHtml.getBytes("utf-8");
            informationFile.setFileLength(bytes.length);
            informationFile.setTabIndex(1);
            informationFile.setContent(bytes);
            informationFile.setStatus(0);
            ifm.save(informationFile, null);
            informationIndex.setInfoHtmlType("html");
        }

       ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
       ifs = ifm.find(this.id, 0, "doc");
      if (ifs.size() > 0) {
        informationIndex.setInfoHtmlType("doc");
      }

      em.getTransaction().begin();
      query.executeUpdate();
      em.getTransaction().commit();

      informationIndexDao.save(informationIndex);

      this.infoServiceCache.clear();
    } catch (Exception e) {
      e.printStackTrace();
      response.getWriter().write("{\"success\":false,\"message\":\"保存失败!\"}");
      return "none";
    }

    response.getWriter().write("{\"success\":true,\"message\":\"保存成功!\"}");
    return "none";
  }
}