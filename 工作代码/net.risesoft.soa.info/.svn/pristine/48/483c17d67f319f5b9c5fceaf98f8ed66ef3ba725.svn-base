package net.risesoft.soa.info.manager.util;
 
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.risesoft.soa.ac.manager.ResourceManager;
import net.risesoft.soa.ac.model.Resource;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.UUID;
import net.risesoft.soa.info.dao.InformationDao;
import net.risesoft.soa.info.dao.InformationDescDao;
import net.risesoft.soa.info.manager.service.InformationManager;
import net.risesoft.soa.info.manager.template.ShowTemplate;
import net.risesoft.soa.info.manager.template.Template;
import net.risesoft.soa.info.manager.template.TemplateFactory;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ExAttrHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InformationUtil
{

  @Autowired
  private ResourceManager resourceManager;

  @Autowired
  private InformationManager informationManager;

  @Autowired
  private InformationDescDao informationDescDao;

  public Information get(String id)
  {
    return InformationList.get(id);
  }

  @Transactional
  public void save(Information information, String parentID, File file, String elements, SessionUser su) throws Exception {
    Map formNames = new HashMap();
    String type;
    Object showtemplate;
    if (file != null) {
      try {
        Document doc = Jsoup.parse(file, "UTF-8", "");

        Document editDoc = doc.clone();

        Document showDoc = doc.clone();

        Elements formElements = doc.getElementsByAttribute("name");
        List formNameList = new ArrayList();
        for (Element element : formElements) {
          formNames.put(element.attr("name").toUpperCase(), element.attr("name"));
          if (elements.contains(element.attr("name").toUpperCase())) {
            formNameList.add(element.attr("name"));
          }
        }

        Elements formatElements = doc.getElementsByAttribute("dateFormat");
        for (Element element : formatElements) {
          type = element.attr("type");
          String format = element.attr("dateFormat");
          if ("dateTime".equals(type)) {
            format = element.attr("dateFormat") + " " + element.attr("timeFormat");
          }
          formNames.put(element.attr("name").toUpperCase() + "_format", format.trim());
        }

        information.setTemplate(doc.html().getBytes("utf-8"));

        Template template = TemplateFactory.getTemplate();
        template.transform(editDoc, formNameList);
        information.setEditTemplate(editDoc.html().getBytes("utf-8"));

        showtemplate = TemplateFactory.getShowTemplate();
        ((ShowTemplate)showtemplate).transform(showDoc, formNameList);
        information.setShowTemplate(showDoc.html().getBytes("utf-8"));
      } catch (Exception e) {
        e.printStackTrace();
        throw new Exception(e.getMessage());
      }
    }
    String id = information.getId();
    String tableName = information.getTableName();
    if (id.length() == 0)
      try {
        id = UUID.generateUUID();
        Resource resource = new Resource();
        resource.setId(id);
        resource.setName(information.getName());
        resource.setIcon(information.getIcon());
        resource.setUrl(information.getUrl());
        resource.setType("information");
        resource = this.resourceManager.create(resource, parentID);
        information.setId(id);
        information.setDn(resource.getDn());
        this.informationManager.save(information);
      } catch (Exception e) {
        this.resourceManager.delete(id);
        e.printStackTrace();
        throw new Exception("创建信息发布失败!");
      }
    else {
      try {
        Resource resource = this.resourceManager.getResource(id);
        resource.setName(information.getName());
        resource.setIcon(information.getIcon());
        resource.setUrl(information.getUrl());
        this.resourceManager.update(resource);
        resource = this.resourceManager.getResource(id);
        Information base = InformationList.get(information.getId());
        information.setDn(resource.getDn());
        if (file == null) {
          information.setTemplate(base.getTemplate());
          information.setEditTemplate(base.getEditTemplate());
          information.setShowTemplate(base.getShowTemplate());
        }
        this.informationManager.save(information);
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new Exception("更新信息发布失败!");
      }
    }

    if (file == null) {
      InputStream is = new ByteArrayInputStream(information.getTemplate());
      Document doc = Jsoup.parse(is, "UTF-8", "");
      List formElements = doc.getElementsByAttribute("name");
      List formNameList = new ArrayList();
      for (showtemplate = formElements.iterator(); ((Iterator)showtemplate).hasNext(); ) { Element element = (Element)((Iterator)showtemplate).next();
        formNames.put(element.attr("name").toUpperCase(), element.attr("name"));
        if (elements.contains(element.attr("name").toUpperCase())) {
          formNameList.add(element.attr("name"));
        }
      }

      Object formatElements = doc.getElementsByAttribute("dateFormat");
      for (Element element : (Elements)formatElements) {
        String type1 = element.attr("type");
        String format = element.attr("dateFormat");
        if ("dateTime".equals(type1)) {
          format = element.attr("dateFormat") + " " + element.attr("timeFormat");
        }
        formNames.put(element.attr("name").toUpperCase() + "_format", format.trim());
      }
    }

    if (id.length() > 0) {
      List informationDescs = this.informationDescDao.findByInfoID(id);
      this.informationDescDao.delete(informationDescs);
    }
    String[] elementsList = elements.split("\\},");
    for (int i = 0; i < elementsList.length; i++) {
      String element = elementsList[i] + "}";
      if (i == elementsList.length - 1) {
        element = elementsList[i];
      }
      Map map = ExAttrHelper.map(element);
      InformationDesc informationDesc = new InformationDesc();
      informationDesc.setId(UUID.generateUUID());
      informationDesc.setInfoID(id);
      informationDesc.setTableName(tableName);
      informationDesc.setColumnName((String)map.get("columnName"));
      informationDesc.setColumnType(Integer.parseInt((String)map.get("columnType")));
      informationDesc.setColumnTypeName((String)map.get("columnTypeName"));
      if (formNames.get(map.get("columnName")) != null) {
        informationDesc.setFormName((String)formNames.get(map.get("columnName")));
      }
      if (formNames.get((String)map.get("columnName") + "_format") != null) {
        informationDesc.setDateFormat((String)formNames.get((String)map.get("columnName") + "_format"));
      }

      if ((map.get("isList") != null) && (((String)map.get("isList")).length() > 0))
        informationDesc.setList(Boolean.parseBoolean((String)map.get("isList")));
      else {
        informationDesc.setList(false);
      }
      informationDesc.setListName((String)map.get("listName"));
      if ((map.get("listLength") != null) && (((String)map.get("listLength")).length() > 0)) {
        informationDesc.setListLength(Integer.parseInt((String)map.get("listLength")));
      }
      if ((map.get("textLength") != null) && (((String)map.get("textLength")).length() > 0)) {
        informationDesc.setTextLength(Integer.parseInt((String)map.get("textLength")));
      }
      informationDesc.setTextDec((String)map.get("textDec"));
      informationDesc.setDefaultValue((String)map.get("defaultValue"));
      if ((map.get("isSearch") != null) && (((String)map.get("isSearch")).length() > 0))
        informationDesc.setSearch(Boolean.parseBoolean((String)map.get("isSearch")));
      else {
        informationDesc.setSearch(false);
      }
      informationDesc.setSearchType((String)map.get("searchType"));
      if ((map.get("tabIndex") != null) && (((String)map.get("tabIndex")).length() > 0))
        informationDesc.setTabIndex(Integer.parseInt((String)map.get("tabIndex")));
      else {
        informationDesc.setTabIndex(i);
      }
      this.informationDescDao.save(informationDesc);
    }
  }

  public void delete(String id, SessionUser su) throws Exception {
    ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
    Resource resource = rm.getResource(id);
    if (resource != null) {
      resource.getName();
      rm.delete(id);
    }
    InformationDao informationDao = (InformationDao)SpringUtil.getBean("informationDao");
    InformationDescDao informationDescDao = (InformationDescDao)SpringUtil.getBean("informationDescDao");
    List informationDescs = informationDescDao.findByInfoID(id);
    informationDescDao.delete(informationDescs);
    informationDao.delete(id);
  }
}