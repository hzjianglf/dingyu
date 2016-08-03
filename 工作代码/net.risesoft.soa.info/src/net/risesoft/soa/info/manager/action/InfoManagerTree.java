package net.risesoft.soa.info.manager.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.ac.manager.DomainManager;
import net.risesoft.soa.ac.manager.ResourceManager;
import net.risesoft.soa.ac.model.Resource;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.json.JSONException;
import net.risesoft.soa.framework.util.json.JSONWriter;
import net.risesoft.soa.info.action.BaseAction;
import net.risesoft.soa.info.tools.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoManagerTree extends BaseAction
{
  private static final Logger log = LoggerFactory.getLogger(InfoManagerTree.class);
  private static final long serialVersionUID = -5852111163521655961L;
  private String node;
  private String index;

  public final String getNode()
  {
    return this.node;
  }

  public final void setNode(String node) {
    this.node = node;
  }

  public final String getIndex() {
    return this.index;
  }

  public final void setIndex(String index) {
    this.index = index;
  }

  public String execute()
    throws Exception
  {
    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");

    System.out.println("<<<<<<<<<<<<<<<<<<<<<node<<<<<" + this.node);

    if (Boolean.parseBoolean(this.index)) {
      System.out.println("11111111111111111");
      return "json";
    }

    if ((this.node != null) && (this.node.equals("root"))) {
      System.out.println("2222222222222222");
      this.json = getFirstTree(this.node, su);
    }
    else {
      System.out.println("3333333333333333333");
      this.json = getSubTree(this.node);
    }

    return "json";
  }

  private String getFirstTree(String nodeID, SessionUser su) {
    List resources = new ArrayList();
    if (nodeID.equals("root")) {
      resources.addAll(getResources(su));
    } else {
      ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
      resources.add(rm.getResource(nodeID));
    }
    StringWriter sw = new StringWriter();
    try {
      JSONWriter json = new JSONWriter(sw);
      json.array();
      for (int i = 0; i < resources.size(); ++i) {
        Resource resource = (Resource)resources.get(i);
        json.object();
        if (i == 0)
          getResourceJSON(resource, json, true);
        else {
          getResourceJSON(resource, json, false);
        }
        json.endObject();
      }
      json.endArray();
    } catch (JSONException e) {
      log.error("生成json数据报错!", e);
    }

    System.out.println("<<<<<<<<<<<<<<<<<getFirstTree sw.toString()<<<<<<" + sw.toString());
    return sw.toString();
  }

  public String getSubTree(String nodeID) {
    ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
    StringWriter sw = new StringWriter();
    try {
      JSONWriter json = new JSONWriter(sw);
      json.array();
      for (Resource sub : rm.getSubResources(nodeID)) {
        String type = sub.getType();
        if (("infoContainer".equals(type)) || ("information".equals(type))) {
          json.object();
          getResourceJSON(sub, json, false);
          json.endObject();
        }
      }
      json.endArray();
    } catch (JSONException e) {
      log.error("生成json数据报错!", e);
    }
    System.out.println("<<<<<<<<<<<<<<<<<sw.toString()<<<<<<" + sw.toString());
    return sw.toString();
  }

  public void getResourceJSON(Resource resource, JSONWriter json, boolean expanded) {
    try {
      json.key("id").value(resource.getUID());
      json.key("text").value(resource.getName());
      String type = resource.getType();
      System.out.println("type-------" + type);
      if ((type == null) || (type.equals("infoContainer"))) {
        json.key("iconCls").value("folder-page-icon");
      } else {
        json.key("iconCls").value("info-icon");
        json.key("leaf").value(true);
      }
      json.key("tabIndex").value(resource.getTabIndex());
      json.key("type").value(resource.getType());
      if (expanded) {
        json.key("expanded").value(true);
      }
      if (!(resource.isInherit())) {
        if ((type == null) || (type.equals("infoContainer")))
          json.key("iconCls").value("folder-page-break-icon");
        else
          json.key("iconCls").value("info-break-icon");
      }
    }
    catch (JSONException e)
    {
      log.error("生成json数据报错!", e);
    }
    System.out.println("json-----------" + json.toString());
  }

  private List<Resource> getResources(SessionUser su) {
    List resources = new ArrayList();
    System.out.println("<<<<<<<<<<<<<<<<<su.getOrgType()<<<<<<" + su.getOrgType());
    if (su.getOrgType().equals("Admin")) {
      ResourceManager rm = (ResourceManager)SpringUtil.getBean("resourceManager");
      resources.addAll(rm.search("type = 'infoContainer' and parent is null"));
    } else {
      DomainManager dm = (DomainManager)SpringUtil.getBean("domainManager");
      resources = dm.getResources(su.getUserUID());
      for (Iterator iterator = resources.iterator(); iterator.hasNext(); ) {
        Resource resource = (Resource)iterator.next();
        String type = resource.getType();
        if (!("infoContainer".equals(type))) {
          iterator.remove();
        }
      }
    }
    return resources;
  }
}