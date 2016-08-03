package net.risesoft.soa.info.service;

import cn.com.qimingx.utils.SQLTypeUtils;
import egov.appservice.info.InfoManager;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import net.risesoft.soa.framework.util.Base64;
import net.risesoft.soa.framework.util.UUID;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import net.risesoft.soa.org.manager.PersonManager;
import net.risesoft.soa.org.model.Person;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class InfoManagerImpl
  implements InfoManager
{
  public boolean create(String infoID, String xml)
  {
    boolean isOK = false;
    PersonManager pm = (PersonManager)SpringUtil.getBean("personManager");
    Information information = InformationList.get(infoID);
    if (information == null) {
      System.out.println(infoID);
    }
    String titleField = information.getTitleField();

    String tableName = information.getTableName();

    List<InformationDesc> informationDescs = information.getElements();

    List list = new ArrayList();

    Map map = new HashMap();

    for (InformationDesc informationDesc : informationDescs) {
      String formName = informationDesc.getFormName();
      if ((formName != null) && (formName.length() > 0)) {
        if (formName.toUpperCase().equals(titleField.toUpperCase())) {
          titleField = formName;
        }
        map.put(informationDesc.getColumnName(), informationDesc);
        list.add(formName.toUpperCase());
      }
    }
    try {
      String id = "";
      String title = "";
      Date audit_time = null;
      Person person = null;
      String content = "";
      byte[] photo = null;
      String fileName = "";
      String fileType = "";
      byte[] fileBlob = null;
      String employee_name = "";
      String department_name = "";
      Map tempMap = new HashMap();
      SAXReader reader = new SAXReader();
      StringReader sr = new StringReader(xml);
      Document doc = reader.read(sr);

      Element root = doc.getRootElement();
      for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
        Element foo = (Element)iter.next();
        String name = foo.getName();
        Iterator i;
        Element att;
        String attName;
        if (name.equals("infor")) {
          for (i = foo.elementIterator(); i.hasNext(); ) {
            att = (Element)i.next();
            attName = att.getName();
            if ("audit_time".equals(attName)) {
              SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              try {
                audit_time = format.parse(att.getTextTrim());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            if ("employee_guid".equals(attName)) {
              List persons = pm.search("caid = '" + att.getTextTrim() + "'");
              if (persons.size() > 0) {
                person = (Person)persons.get(0);
              }
              if (person == null) {
                person = pm.getByLoginName("admin");
              }
            }
            if ("employee_name".equals(attName)) {
              employee_name = att.getTextTrim();
            }
            if ("department_name".equals(attName)) {
              department_name = att.getTextTrim();
            }
          }
        }

        if (name.equals("tableName")) {
          for (i = foo.elementIterator(); i.hasNext(); ) {
            att = (Element)i.next();
            attName = att.getName();
            String value = att.getTextTrim();
            if (value == null) continue; if (value.length() == 0) {
              continue;
            }
            if (attName.equals("content")) {
              content = value;
            }
            else if (attName.equals("photo")) {
              photo = Base64.decode(value);
            }
            else if (attName.toUpperCase().equals(titleField.toUpperCase())) {
              title = value;
              tempMap.put(attName.toUpperCase(), value);
            }
            else if (attName.toUpperCase().equals("ID")) {
              id = value;
            }
            else {
              tempMap.put(attName.toUpperCase(), value);
            }
          }
        }
        if (name.equals("files")) {
          for (i = foo.elementIterator(); i.hasNext(); ) {
            att = (Element)i.next();
            for (Iterator j = att.elementIterator(); j.hasNext(); ) {
              Element sub = (Element)j.next();
              String subName = sub.getName();
              if (subName.equals("blob")) {
                fileBlob = Base64.decode(sub.getTextTrim());
              }
              if (subName.equals("name")) {
                fileName = sub.getTextTrim();
              }
              if (subName.equals("type")) {
                fileType = sub.getTextTrim();
              }
            }
          }
        }
      }
      InformationIndexManager informationIndexManager = (InformationIndexManager)SpringUtil.getBean("informationIndexManager");
      List informationIndexs = informationIndexManager.findByInstance(infoID, id);
      if (informationIndexs.size() == 0) {
        String newID = UUID.generateUUID();
        InformationIndex informationIndex = new InformationIndex();
        String sql = "insert into " + tableName + "(id";
        String names = "";
        String values = "";
        for (int i = 0; i < list.size(); ++i) {
          names = names + "," + ((String)list.get(i));
          values = values + ",:" + ((String)list.get(i));
        }
        sql = sql + names + ") values('" + newID + "'" + values + ")";

        EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");

        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery(sql);

        NumberFormat nf2 = NumberFormat.getInstance();

        for (Object string : list) {
			
          InformationDesc informationDesc = (InformationDesc)map.get(((String) string).toUpperCase());
          String value = (String)tempMap.get(string);

          if (SQLTypeUtils.isNumberType(informationDesc.getColumnType()))
            if (value != null)
              try {
                query.setParameter((String) string, nf2.parse(value));
              } catch (Exception e) {
                e.printStackTrace();
              }
            else
              query.setParameter((String) string, Integer.valueOf(0));
          else if (SQLTypeUtils.isDateType(informationDesc.getColumnType())) {
            if (value != null) {
              String form = informationDesc.getDateFormat();
              SimpleDateFormat sdf;
              if ((form != null) && (form.length() > 0)) {
                sdf = new SimpleDateFormat(form);
                try {
                  query.setParameter((String) string, sdf.parse(value));
                } catch (Exception e) {
                  e.printStackTrace();
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
                  query.setParameter((String) string, sdf.parse(value));
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            } else {
              query.setParameter((String) string, "");
            }
          }
          else if (value == null)
            query.setParameter((String) string, "");
          else {
            query.setParameter((String) string, value);
          }

        }

        informationIndex.setId(newID);
        informationIndex.setInfoID(infoID);
        informationIndex.setTitle(title);
        informationIndex.setTableName(information.getTableName());
        informationIndex.setModule(information.getModule());
        informationIndex.setInfoType(information.getInfoType());
        informationIndex.setInstance(id);
        if (person != null) {
          informationIndex.setCreater(person.getId());
          informationIndex.setCreaterName(person.getName());
          informationIndex.setDn(person.getDn());
        }
        if (employee_name.length() > 0) {
          informationIndex.setCreaterName(employee_name);
        }
        if (department_name.length() > 0) {
          informationIndex.setDn(department_name);
        }
        if (audit_time != null) {
          informationIndex.setCreateTime(audit_time);
          informationIndex.setUpdateTime(audit_time);
        } else {
          informationIndex.setCreateTime(Calendar.getInstance().getTime());
          informationIndex.setUpdateTime(Calendar.getInstance().getTime());
        }
        InfoFileManager ifm;
        InformationFile informationFile;
        if ((content != null) && (content.length() > 0)) {
          ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");

          informationFile = new InformationFile();
          informationFile.setId(UUID.generateUUID());
          if (audit_time != null) {
            informationFile.setCreateDateTime(audit_time);
            informationFile.setUpdateDateTime(audit_time);
          } else {
            informationFile.setCreateDateTime(Calendar.getInstance().getTime());
            informationFile.setUpdateDateTime(Calendar.getInstance().getTime());
          }

          informationFile.setInstanceID(newID);
          informationFile.setInfoID(infoID);

          informationFile.setModule("riseinfo");

          if (person != null) {
            informationFile.setCreater(person.getId());
            informationFile.setCreaterName(person.getName());
          }

          if (employee_name.length() > 0) {
            informationFile.setCreaterName(employee_name);
          }

          informationFile.setFileType("html");
          byte[] bytes = content.getBytes("utf-8");
          informationFile.setFileLength(bytes.length);
          informationFile.setTabIndex(1);
          informationFile.setContent(bytes);
          informationFile.setStatus(0);
          ifm.save(informationFile, null);
          informationIndex.setInfoHtmlType("html");
        }

        if ((photo != null) && (photo.length > 0)) {
          ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");

          informationFile = new InformationFile();
          informationFile.setId(UUID.generateUUID());
          informationFile.setInstanceID(newID);
          informationFile.setFileName("image.jpeg");
          informationFile.setFileNameExt(".jpeg");
          informationFile.setTitle("image");
          informationFile.setModule("riseinfo");
          if (person != null) {
            informationFile.setCreater(person.getId());
            informationFile.setCreaterName(person.getName());
          }

          if (employee_name.length() > 0) {
            informationFile.setCreaterName(employee_name);
          }

          if (audit_time != null) {
            informationFile.setCreateDateTime(audit_time);
            informationFile.setUpdateDateTime(audit_time);
          } else {
            informationFile.setCreateDateTime(Calendar.getInstance().getTime());
            informationFile.setUpdateDateTime(Calendar.getInstance().getTime());
          }
          informationFile.setFileType("file");
          informationFile.setFileLength(photo.length);
          informationFile.setTabIndex(0);
          informationFile.setStatus(0);
          informationFile.setInfoID(infoID);
          informationFile.setRealPath(getRealPath(information.getDn(), informationFile));
          ifm.saveFormInputStream(informationFile, new ByteArrayInputStream(photo));
        }

        if ((fileBlob != null) && (fileBlob.length > 0)) {
          ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
          informationFile = new InformationFile();
          informationFile.setId(UUID.generateUUID());
          informationFile.setInstanceID(newID);
          if ((fileName == null) || (fileName.length() == 0)) {
            fileName = title + ".doc";
          }
          if (fileName.indexOf(".") == -1) {
            fileName = fileName + ".doc";
          }
          if (fileName.indexOf("??") > 0) {
            String ext = fileName.substring(fileName.lastIndexOf("."));
            fileName = title + ext;
          }

          informationFile.setFileName(fileName);
          informationFile.setFileNameExt(fileName.substring(fileName.lastIndexOf(".")));
          informationFile.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
          informationFile.setModule("riseinfo");

          if (person != null) {
            informationFile.setCreater(person.getId());
            informationFile.setCreaterName(person.getName());
          }

          if (audit_time != null) {
            informationFile.setCreateDateTime(audit_time);
            informationFile.setUpdateDateTime(audit_time);
          } else {
            informationFile.setCreateDateTime(Calendar.getInstance().getTime());
            informationFile.setUpdateDateTime(Calendar.getInstance().getTime());
          }

          if ("doc".equals(fileType)) {
            informationFile.setFileType("doc");
            informationIndex.setInfoHtmlType("doc");
          } else {
            informationFile.setFileType("file");
          }

          informationFile.setFileLength(fileBlob.length);
          informationFile.setTabIndex(1);
          informationFile.setStatus(0);
          informationFile.setInfoID(infoID);
          informationFile.setRealPath(getRealPath(information.getDn(), informationFile));
          ifm.update(informationFile);
          ifm.saveFormInputStream(informationFile, new ByteArrayInputStream(fileBlob));
        }

        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();

        informationIndexManager.save(informationIndex);
        isOK = true;
      }
      content = null;
      photo = null;
      fileBlob = null;
      doc = null;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return isOK;
  }

  public String getRealPath(String dn, InformationFile informationFile) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    dn = dn.replaceAll("rsn=", "");
    String[] dns = dn.split(",");
    String path = "";
    for (int i = dns.length - 1; i >= 0; --i) {
      path = path + dns[i] + "/";
    }
    File dir = new File(ConfigUtil.filePath + path + sdf.format(informationFile.getCreateDateTime()) + "/");
    if (!(dir.isDirectory())) {
      dir.mkdirs();
    }

    return path + sdf.format(informationFile.getCreateDateTime()) + "/";
  }

  public static void main(String[] args) {
    InfoManagerImpl im = new InfoManagerImpl();
    im.create("", "");
  }
}