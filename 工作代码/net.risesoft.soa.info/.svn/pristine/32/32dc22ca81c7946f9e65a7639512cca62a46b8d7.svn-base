package net.risesoft.soa.info.action;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import net.risesoft.soa.framework.service.config.Config;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.manager.template.RenderTemplate;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.TemplateLoaderImpl;
import net.risesoft.soa.org.manager.PersonManager;
import net.risesoft.soa.org.model.OrgUnit;

import org.apache.struts2.ServletActionContext;

import cn.com.qimingx.utils.SQLTypeUtils;

import com.opensymphony.xwork2.ActionContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class InfoTemplate extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String infoID;
  private String id;

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

  public String execute()
    throws Exception
  {
    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");

    Configuration cfg = new Configuration();
    Map root;
    SimpleDateFormat sdf;
    Information information;
    Map map;
    List list;
    List searchList;
    root = new HashMap();
    InformationIndexDao informationIndexDao ;
    InformationIndex informationIndex ;
  
   
    List informationDescs;
    EntityManagerFactory emf = null;
    EntityManager em = null;
    String sql;
    Query query;
    Object[] object;
    byte[] template;
    Template t;
    HttpServletResponse response1;
    InformationDesc informationDesc=null;
    String form;
   
   
  
    if("create".equals(operation)){
        try
        {
            root = new HashMap();
             information = InformationList.get(infoID);
             String shortacnum="";
             try{
           	  shortacnum=this.getServletRequest().getParameter("shortacnum");
             this.getServletRequest().getSession().setAttribute("shortacnum", shortacnum);
             }catch(Exception e){}
            if(information != null)
            {
                 informationDescs = information.getElements();
                for(Iterator iterator = informationDescs.iterator(); iterator.hasNext();)
                {
                     informationDesc = (InformationDesc)iterator.next();
                        
                    String defaultValue = informationDesc.getDefaultValue();
                    if(defaultValue != null && defaultValue.length() > 0)
                    {
                        String value = "";
                        if(defaultValue.equals("Person"))
                            value = su.getUserName();
                        if(defaultValue.equals("Department"))
                            value = getDepartment(su);
                        if(defaultValue.equals("Date"))
                        {
                            String dateFormat = informationDesc.getDateFormat();
                            if(dateFormat == null || dateFormat.length() == 0)
                                dateFormat = "yyyy-MM-dd";
                             sdf = new SimpleDateFormat(dateFormat);
                            value = sdf.format(Calendar.getInstance().getTime());
                        }
                        if(defaultValue.equals("DateTime"))
                        {
                            String dateFormat = informationDesc.getDateFormat();
                            if(dateFormat == null || dateFormat.length() == 0)
                                dateFormat = "yyyy-MM-dd HH:mm:ss";
                             sdf = new SimpleDateFormat(dateFormat);
                            value = sdf.format(Calendar.getInstance().getTime());
                        }
                  
                        
                        root.put(informationDesc.getFormName(), value);
                    } else if(informationDesc.getFormName() != null){
                        root.put(informationDesc.getFormName(), "");
                        
                        
                     try{
                        //资源别名（ 存储资源编号）
//                      String   sql123 = "select t.aliasname from ac_resource t where t.id='"+infoID+"'";
//                      emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
//                      em = emf.createEntityManager();
//                   
//                      query = em.createNativeQuery(sql123);
//                      searchList = query.getResultList();
//                     
//                      String aliasname=(String)searchList.get(0);
                      
                      //信息发布主表名
                      String sql11="select t.tablename from info_main_define t where t.id='"+infoID+"'";
                 
                      emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
                      em = emf.createEntityManager();
                      query = em.createNativeQuery(sql11);
                      searchList = query.getResultList();
                      String tablename=(String)searchList.get(0);
                      
                      
                      String myid="";
                      try{
                    	  myid=this.getServletRequest().getParameter("myid");
                      }catch(Exception e){}
                     
//                    Enumeration e=  this.getServletRequest().getParameterNames();
//                   while (e.hasMoreElements()) {
//                        Object o= e.nextElement();
//                        System.out.println("<><><><><><><>"+o);
//                    }
                      
                   //   System.out.println("<<<<<<<<<<<<<<<<<name------"+this.getServletRequest().getParameterNames().toString());
                     // System.out.println(this.getServletRequest().getp);
                      
                      //查询是否已经存在信息发布的编码 如果查询不到编号赋值00001  版本自动加0.1
                      String sqlnum1=" select nvl(acnum ,'00001'),trim(to_char((acversion+0.1),'9.9'))  from  "+tablename+" t where t.id='"+myid+"' and shortacnum='"+shortacnum+"'";
                      
                      
                      emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
                      em = emf.createEntityManager();
                      query = em.createNativeQuery(sqlnum1);
                      searchList = query.getResultList();
                      String num1="";
                      String acv="";
                      try{
                    	  Object[] obj = (Object[]) searchList.get(0);   
                    	  
                    
                       num1=(String)obj[0];
                       acv=(String)obj[1];
                   
                     
                      }catch(Exception es){
                    	
                      }
                    //信息发布编码最大值  最大值加1 并补位
                   
                      String sqlnum="select  nvl( replace(lpad((max(substr(acnum,10,5))+1),5),' ','0') ,'00001') from "+tablename+"  where  length(acnum)=24 and shortacnum='"+shortacnum+"'";
                      
                      emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
                      em = emf.createEntityManager();
                      query = em.createNativeQuery(sqlnum);
                      searchList = query.getResultList();
                   
                      
                      String acshortnum=(String)searchList.get(0);

                        emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
                        String num=shortacnum+acshortnum+"3700001000";
                        if(informationDesc.getFormName().toLowerCase().equals("acnum")){
                        	 if(num1.length()>10){
                        		 num=num1;
                             }
                        	root.put("acnum",num);
                        }     
                        if(informationDesc.getFormName().toLowerCase().equals("acversion")){
                        	 if(num1.length()>10){
                        		 
                        		 root.put("acversion",acv);
                             }
                        	 else{
                        		 root.put("acversion", "1.0");
                        	 }
                        	
                        }  
                        
                     }catch(Exception e1){}
                        
                        
                        
                    }
                    }
                
           
                root.put("id", id);
                root.put("shortacnum", shortacnum);
                root.put("infoID", infoID);
                template= RenderTemplate.create(information);
                cfg.setTemplateLoader(new TemplateLoaderImpl(template));
                cfg.setObjectWrapper(new DefaultObjectWrapper());
                 t = cfg.getTemplate("");
                 response1 = ServletActionContext.getResponse();
                response1.setCharacterEncoding("utf-8");
                t.process(root, response1.getWriter());
                
            }
        }
        catch(Exception _ex)
        {
            return "error";
        }
    
    }
    if ("edit".equals(this.operation)) {
    	 
      try {
        root = new HashMap();

          informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
          informationIndex = (InformationIndex)informationIndexDao.findOne(this.id);
        this.infoID = informationIndex.getInfoID();

        information = InformationList.get(this.infoID);
        if (information != null) {
          map = new HashMap();
          list = new ArrayList();
          informationDescs = information.getElements();
          for(Iterator iterator = informationDescs.iterator(); iterator.hasNext();){
        
        	  informationDesc = (InformationDesc)iterator.next();
          ((Map)map).put(informationDesc.getColumnName(), informationDesc);
          if (informationDesc.getFormName() != null) {
            list.add(informationDesc.getFormName());
          }
        }
          root.put("id", this.id);
          root.put("infoID", this.infoID);
          root.put("infoHtmlType", (informationIndex.getInfoHtmlType() == null) ? "all" : informationIndex.getInfoHtmlType());

          emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

          em = emf.createEntityManager();

          sql = "select ";
          for (int i = 0; i < list.size(); ++i) {
            if (i == list.size() - 1)
              sql = sql + ((String)list.get(i));
            else {
              sql = sql + ((String)list.get(i)) + ",";
            }
          }
          sql = sql + " from " + information.getTableName() + " where id = '" + this.id + "'";

          query = em.createNativeQuery(sql);

          searchList = query.getResultList();

          if (searchList.size() == 0) {
            for (int i = 0; i < list.size(); ++i)
              root.put((String)list.get(i), "");
          }
          else
          {
            if ((searchList.get(0) instanceof String) || (searchList.get(0) instanceof Number))
              object = new Object[] { searchList.get(0) };
            else {
              object = (Object[])searchList.get(0);
            }
            for (int i = 0; i < object.length; ++i) {
              if (object[i] != null) {
                informationDesc = (InformationDesc)((Map)map).get(((String)list.get(i)).toUpperCase());
                if (SQLTypeUtils.isDateType(informationDesc.getColumnType())) {
                  form = informationDesc.getDateFormat();
                  if ((form != null) && (form.length() > 0)) {
                    sdf = new SimpleDateFormat(form);
                    try {
                      root.put((String)list.get(i), sdf.format(object[i]));
                    } catch (Exception e) {
                      e.printStackTrace();
                      return "none";
                    }
                  } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (informationDesc.getColumnType() == 91) {
                      sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }

                    if (informationDesc.getColumnType() == 92)
                      sdf = new SimpleDateFormat("HH:mm:ss");
                    try
                    {
                      root.put((String)list.get(i), sdf.format(object[i]));
                    } catch (Exception e) {
                      e.printStackTrace();
                      return "none";
                    }
                  }
                } else {
                  root.put((String)list.get(i), object[i].toString());
                }
              } else {
                root.put((String)list.get(i), "");
              }
            }
          }
       // add by sunming 20150924  start
          String url_str = getSystemURI()+"/mnicontro/getmbwsurl.do?infoid="+id;//  lmdextend.wso  jar包
          String wmburl = opration(url_str);                     
          root.put("mburl",wmburl);
       // add by sunming 20150924  end
          template = RenderTemplate.update(information, root);

          cfg.setTemplateLoader(new TemplateLoaderImpl(template));

          cfg.setObjectWrapper(new DefaultObjectWrapper());

          t = cfg.getTemplate("");

          t.setEncoding("utf-8");

          response1 = ServletActionContext.getResponse();

          response1.setCharacterEncoding("utf-8");

          t.process(root, response1.getWriter());
        }
      }
      catch (Exception localException2) {
        return "error";
      }
    }
    
    if ("display".equals(this.operation)) {
      try {
        root = new HashMap();
        response1 = ServletActionContext.getResponse();
        informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
        informationIndex = (InformationIndex)informationIndexDao.findOne(this.id);
        if (informationIndex != null) {
          this.infoID = informationIndex.getInfoID();

          information = InformationList.get(this.infoID);
          if (information != null) {
            map = new HashMap();
            list = new ArrayList();
            informationDescs = information.getElements();
//            for (em = informationDescs.iterator(); em.hasNext(); ) { emf = (InformationDesc)em.next();
//              ((Map)map).put(emf.getColumnName(), emf);
//              if (emf.getFormName() != null) {
//                list.add(emf.getFormName());
//              }
//            }
            
            
            
            
            for(Iterator iterator = informationDescs.iterator(); iterator.hasNext();){
                
          	  informationDesc = (InformationDesc)iterator.next();
            ((Map)map).put(informationDesc.getColumnName(), informationDesc);
            if (informationDesc.getFormName() != null) {
              list.add(informationDesc.getFormName());
            }
          }
            
            
            
            root.put("id", this.id);

            root.put("infoHtmlType", (informationIndex.getInfoHtmlType() == null) ? "all" : informationIndex.getInfoHtmlType());

            emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

            em = emf.createEntityManager(); 
            
            
            
            root.put("id", id);
            root.put("infoHtmlType", informationIndex.getInfoHtmlType() != null ? ((Object) (informationIndex.getInfoHtmlType())) : "all");
             emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
             em = emf.createEntityManager();
             sql = "select ";
            for(int i = 0; i < list.size(); i++)
                if(i == list.size() - 1)
                    sql = (new StringBuilder(String.valueOf(sql))).append((String)list.get(i)).toString();
                else
                    sql = (new StringBuilder(String.valueOf(sql))).append((String)list.get(i)).append(",").toString();

            sql = (new StringBuilder(String.valueOf(sql))).append(" from ").append(information.getTableName()).append(" where id = '").append(id).append("'").toString();
             query = em.createNativeQuery(sql);
            searchList = query.getResultList();
            
            
            
            

          
            if (searchList.size() == 0) {
              for (int i = 0; i < list.size(); ++i)
                root.put((String)list.get(i), "");
            }
              else
              {
                  if ((searchList.get(0) instanceof String) || (searchList.get(0) instanceof Number))
                      object = new Object[] { searchList.get(0) };
                    else {
                      object = (Object[])searchList.get(0);
                    }
                    for (int i = 0; i < object.length; ++i) {
                      if (object[i] != null) {
                        informationDesc = (InformationDesc)((Map)map).get(((String)list.get(i)).toUpperCase());
                        if (SQLTypeUtils.isDateType(informationDesc.getColumnType())) {
                          form = informationDesc.getDateFormat();
                          if ((form != null) && (form.length() > 0)) {
                            sdf = new SimpleDateFormat(form);
                            try {
                              root.put((String)list.get(i), sdf.format(object[i]));
                            } catch (Exception e) {
                              e.printStackTrace();
                              return "none";
                            }
                          } else {
                            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            if (informationDesc.getColumnType() == 91) {
                              sdf = new SimpleDateFormat("yyyy-MM-dd");
                            }

                            if (informationDesc.getColumnType() == 92)
                              sdf = new SimpleDateFormat("HH:mm:ss");
                            try
                            {
                              root.put((String)list.get(i), sdf.format(object[i]));
                            } catch (Exception e) {
                              e.printStackTrace();
                              return "none";
                            }
                          }
                        } else {
                          root.put((String)list.get(i), object[i].toString());
                        }
                      } else {
                        root.put((String)list.get(i), "");
                      }
                    }
                  }
            
            
         // add by sunming 20150924  start
            String url_str = getSystemURI()+"/mnicontro/getmbwsurl.do?infoid="+id;//  lmdextend.wso  jar包
            String wmburl = opration(url_str);                     
            root.put("mburl",wmburl);           
         // add by sunming 20150924  end
            
            
            template = RenderTemplate.display(information, root);

            cfg.setTemplateLoader(new TemplateLoaderImpl(template));

            cfg.setObjectWrapper(new DefaultObjectWrapper());

            t = cfg.getTemplate("");

            t.setEncoding("utf-8");

            response1 = ServletActionContext.getResponse();

            response1.setCharacterEncoding("utf-8");

            t.process(root, response1.getWriter());
          }
        }
      }
      catch (Exception localException3) {
        return "error";
      }
    }

    try{
   	 
//   em.close();
//   emf.close();
    }catch(Exception e){}
    return ((String)"none");
  }
   
  private String getDepartment(SessionUser su) {
    PersonManager pm = (PersonManager)SpringUtil.getBean("personManager");
    OrgUnit ou = pm.getParent(su.getUserUID());
    return ou.getName();
  }
  public static void main(String[] ar){

  }
  //获取平台配置文件中的system.uri属性值
  public String getSystemURI(){// add by sunming 20150924
	       URL sysConfigUrl = Config.getConfigFileAsURL("/META-INF/config/system.properties");
	       String systemuri="";
	       if (sysConfigUrl != null) {
	            Properties prop = new Properties();
	            try {
	               prop.load(sysConfigUrl.openStream());
	            } catch (Exception ex) {
	               System.err.println("IO Error: " + ex.getMessage());
	            }
	            Properties directProp = System.getProperties();
	            systemuri=directProp.getProperty("system.uri");
	        }       
		    return systemuri;
  }
  //类内部触发action
  public String opration(String url_str){// add by sunming 20150924
	    String returnmess="";
        try {
			URL url = new URL(url_str);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.connect();
			int code = connection.getResponseCode();
			if (code == 404) {
			    throw new Exception("认证无效，找不到此次认证的会话信息！");
			}
			if (code == 500) {
			    throw new Exception("认证服务器发生内部错误！");
			}
			if (code != 200) {
			    throw new Exception("发生其它错误，认证服务器返回 " + code);
			}				
	        InputStream is = connection.getInputStream();
	        byte[] response = new byte[is.available()];
	        is.read(response);
	        is.close();
	        if (response == null || response.length == 0) {
	            throw new Exception("认证无效，找不到此次认证的会话信息！");
	        }
	        returnmess = new String(response, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnmess;
   }
}