package net.risesoft.soa.asf.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.risesoft.soa.asf.core.ServiceDefinition;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.thirdparty.ThirdpartyServiceRepository;
import net.risesoft.soa.asf.dao.ServiceModule2Dao;
import net.risesoft.soa.asf.model.RepositoryInfo;
import net.risesoft.soa.asf.model.ServiceModule2;
import net.risesoft.soa.asf.web.util.TreeNodeNames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IndexController
  implements TreeNodeNames
{

  @Autowired
  private ServiceModule2Dao moduleDao;

  @RequestMapping
  public String index()
  {
    return "redirect:index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(@RequestParam(value="console", defaultValue="false") boolean console, HttpServletRequest req, Map<String, String> model)
  {
    model.put("console", String.valueOf(console));
    model.put("sysmgrTree", req.getParameter("sysmgrTree"));
    model.put("serviceTree", req.getParameter("serviceTree"));
    model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"sysmgrTree.json"})
  @ResponseBody
  public List<NodeInfo> sysmgrTree(@RequestParam(value="node", defaultValue="_root") String node, HttpServletRequest req)
  {
    List list = new ArrayList();
    String ctx = req.getContextPath();
    if (node.startsWith("_root")) {
      list.add(new NodeInfo("_bundles", "插件列表", "asf-bundle-node", ctx + "/bundles"));
      list.add(new NodeInfo("_monitor", "性能监控", "asf-monitor-node", ctx + "/monitor"));
      list.add(new NodeInfo("_syslog", "系统日志", "asf-syslog-node", ctx + "/syslog"));
      list.add(new NodeInfo("_bizlog", "业务日志", "asf-bizlog-node", ctx + "/bizlog/list.do"));
      list.add(new NodeInfo("_ssoinfo", "在线用户", "asf-ssoinfo-node", ctx + "/ssoinfo"));
      list.add(new NodeInfo("_loginlog", "登录日志", "asf-bizlog-node", ctx + "/ssoinfo/loginLogs.do"));
      list.add(new NodeInfo("_hibernate", "Hibernate", "asf-hibernate-node", ctx + "/hibernate"));
      list.add(new NodeInfo("_system", "服务器信息", "asf-system-node", ctx + "/system"));
    }
    return list;
  }

  @RequestMapping({"serviceTree.json"})
  @ResponseBody
  public List<NodeInfo> serviceTree(@RequestParam(value="node", defaultValue="_root") String node, HttpServletRequest req) {
    List list = new ArrayList();
    String ctx = req.getContextPath();
    if (node.startsWith("_root")) {
      list.add(new NodeInfo("_services", "服务仓库", "asf-repo-node", ctx + "/services", false));
      list.add(new NodeInfo("_servicelog", "服务日志", "asf-servicelog-node", ctx + "/services/log.html"));
    }
    else
    {
      ServiceRepository sr;
      if (node.startsWith("_services")) {
        List repos = ServiceRepositoryHolder.getRepositoryList();
        for (Iterator localIterator1 = repos.iterator(); localIterator1.hasNext(); ) { sr = (ServiceRepository)localIterator1.next();
          RepositoryInfo ri = sr.getMetaData();
          list.add(
            new NodeInfo("_repo_" + ri.getAlias(), 
            ri.getDescription(), 
            "asf-repo2-node", 
            ctx + "/services", 
            false, 
            ri.getAlias()));
        }
      }
      else
      {
        String repoId;
        Iterator localIterator2;
        ServiceDefinition def;
        ServiceMetaData smd;
        if (node.startsWith("_repo_")) {
          repoId = node.substring("_repo_".length());
          sr = ServiceRepositoryHolder.getRepository(repoId);
          if ((sr != null) && (!(sr instanceof ThirdpartyServiceRepository))) {
            List defs = sr.getServiceDefinitionList();
            List readedModules = new ArrayList();
            for (localIterator2 = defs.iterator(); localIterator2.hasNext(); ) { def = (ServiceDefinition)localIterator2.next();
              smd = def.getMetaData();
              if (!(readedModules.contains(smd.getModule().getId()))) {
                list.add(
                  new NodeInfo("_module_" + smd.getModule().getId() + node, 
                  smd.getModule().getName(), 
                  "asf-module-node", 
                  ctx + "/services", 
                  false, 
                  smd.getModule().getId()));
                readedModules.add(smd.getModule().getId());
              }
            }
          }
          if ((sr != null) && (sr instanceof ThirdpartyServiceRepository)) {
            List<ServiceModule2> moduleList = this.moduleDao.findAll();
            for (ServiceModule2 m2 : moduleList)
              list.add(
                new NodeInfo("_module_" + m2.getId() + node, 
                m2.getName(), 
                "asf-module-node", 
                ctx + "/services", 
                false, 
                m2.getId()));
          }
        }
        else if (node.startsWith("_module_")) {
          repoId = node.substring(node.indexOf("_repo_") + "_repo_".length());
          sr = ServiceRepositoryHolder.getRepository(repoId);
          if (sr != null) {
            String moduleId = node.substring("_module_".length(), node.indexOf("_repo_"));
            List defs = sr.getServiceDefinitionList();
            for (localIterator2 = defs.iterator(); localIterator2.hasNext(); ) { def = (ServiceDefinition)localIterator2.next();
              smd = def.getMetaData();
              if (smd.getModule().getId().equals(moduleId))
                list.add(
                  new NodeInfo("_comp_" + smd.getComponent().getId() + node, 
                  smd.getComponent().getName(), 
                  "asf-service-comp-icon", 
                  ctx + "/services", 
                  true, 
                  smd.getComponent().getId())); 
            }
          }
        }
      }
    }
    return list; } 
  public static class NodeInfo { private String id;
    private String text;
    private String iconCls;
    private String url = "#";
    private boolean leaf = true;
    private String rid;

    public NodeInfo(String id, String text, String iconCls) { this.id = id;
      this.text = text;
      this.iconCls = iconCls;
    }

    public NodeInfo(String id, String text, String iconCls, String url) {
      this.id = id;
      this.text = text;
      this.iconCls = iconCls;
      this.url = url;
    }

    public NodeInfo(String id, String text, String iconCls, String linkTo, boolean leaf) {
      this.id = id;
      this.text = text;
      this.iconCls = iconCls;
      this.url = linkTo;
      this.leaf = leaf;
    }

    public NodeInfo(String id, String text, String iconCls, String linkTo, boolean leaf, String rid) {
      this.id = id;
      this.text = text;
      this.iconCls = iconCls;
      this.url = linkTo;
      this.leaf = leaf;
      this.rid = rid;
    }

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getText() {
      return this.text;
    }

    public void setText(String text) {
      this.text = text;
    }

    public String getIconCls() {
      return this.iconCls;
    }

    public void setIconCls(String iconCls) {
      this.iconCls = iconCls;
    }

    public String getUrl() {
      return this.url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public boolean isLeaf() {
      return this.leaf;
    }

    public void setLeaf(boolean leaf) {
      this.leaf = leaf;
    }

    public String getRid()
    {
      return this.rid;
    }

    public void setRid(String rid)
    {
      this.rid = rid;
    }
  }
}