package net.risesoft.soa.info.action;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.service.InfoFileManager;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.FileContentType;
import net.risesoft.soa.org.manager.PersonManager;
import net.risesoft.soa.org.model.OrgUnit;

import org.apache.struts2.ServletActionContext;

import cn.com.qimingx.utils.SQLTypeUtils;

import com.opensymphony.xwork2.ActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class InfoDetailAction extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String infoID;
  private String id;
  private Map root;
  private List<String[]> list;


  
public List<String[]> getList() {
	return list;
}

public void setList(List<String[]> list) {
	this.list = list;
}

public Map getRoot() {
	return root;
}

public void setRoot(Map root) {
	this.root = root;
}

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
           
          }
        }
        
        InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
        List ifs = ifm.find(this.id, 0, "file");
        this.list = new ArrayList();
        for (int i = 0; i < ifs.size(); i++) {
          InformationFile informationFile = (InformationFile)ifs.get(i);
          String ext = informationFile.getFileNameExt();

          String[] string = new String[5];
          string[0] = informationFile.getId();
          string[1] = informationFile.getFileName();
          if (ext == null) {
            string[2] = "other";
          }
          else if (FileContentType.isImgType(ext.toLowerCase())) {
            string[2] = "image";
            string[3] = ("/info/infoImgShow.action?id=" + string[0]);
          } else if ((FileContentType.isOfficeType(ext.toLowerCase())) || (FileContentType.isPdfType(ext.toLowerCase()))) {
            string[2] = "office";
            string[3] = FileContentType.getIcon(ext.toLowerCase());
          } else {
            string[2] = "other";
            string[3] = FileContentType.getIcon(ext.toLowerCase());
          }

          string[4] = informationFile.getFileNameExt();
          this.list.add(string);
        }
      }
      catch (Exception localException3) {
        return "error";
      }
    }

    return this.operation;
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