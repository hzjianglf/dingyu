package net.risesoft.soa.asf.web.controller;

import egov.appservice.asf.model.ServiceComponent;
import egov.appservice.asf.model.ServiceModule;
import egov.appservice.asf.service.RepositoryManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.asf.core.ServiceDefinition;
import net.risesoft.soa.asf.core.ServiceMetaData;
import net.risesoft.soa.asf.core.ServiceRepository;
import net.risesoft.soa.asf.core.ServiceRepositoryHolder;
import net.risesoft.soa.asf.core.remote.RemoteServiceRepository;
import net.risesoft.soa.asf.core.thirdparty.ThirdpartyServiceProcessor;
import net.risesoft.soa.asf.core.thirdparty.ThirdpartyServiceRepository;
import net.risesoft.soa.asf.dao.ServiceComponent2Dao;
import net.risesoft.soa.asf.dao.ServiceLogDao;
import net.risesoft.soa.asf.dao.ServiceModule2Dao;
import net.risesoft.soa.asf.model.RepositoryInfo;
import net.risesoft.soa.asf.model.ServiceComponent2;
import net.risesoft.soa.asf.model.ServiceLog;
import net.risesoft.soa.asf.model.ServiceModule2;
import net.risesoft.soa.asf.web.helper.ServiceHelper;
import net.risesoft.soa.asf.web.helper.ServiceHelper.RepoState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/services"})
public class ServiceController
{

  @Autowired
  private RepositoryManager repositoryManager;

  @Autowired
  private ServiceLogDao serviceLogDao;

  @Autowired
  private ServiceHelper helper;

  @Autowired
  private ServiceModule2Dao moduleDao;

  @Autowired
  private ServiceComponent2Dao compDao;

  @Autowired
  private ThirdpartyServiceProcessor thirdpartyServiceProcessor;

  @RequestMapping
  public String index()
  {
    return "redirect:services/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"log.json"})
  @ResponseBody
  public Map<String, Object> log(@RequestParam(value="serviceName", defaultValue="all") String serviceName, @RequestParam(value="wrongOnly", defaultValue="false") boolean wrongOnly, @RequestParam(value="methodList", defaultValue="") String[] methodList, @RequestParam(value="start", defaultValue="0") long start, @RequestParam(value="limit", defaultValue="20") long limit, @RequestParam(value="sort", defaultValue="invokeTime") String sort, @RequestParam(value="dir", defaultValue="DESC") String dir)
  {
    Map model = new HashMap();

    Specification spec = filterLogs(serviceName, wrongOnly);

    long count = this.serviceLogDao.count(spec);
    int page = (int)(start / limit);
    int size = (int)limit;
    model.put("totalCount", Long.valueOf(count));

    Pageable pageable = new PageRequest(page, size, Sort.Direction.fromString(dir), new String[] { sort });
    model.put("logs", this.serviceLogDao.findAll(spec, pageable).getContent());
    return model;
  }

  private Specification<ServiceLog> filterLogs(String object, boolean wrongOnly) {
    return new Specification(object, wrongOnly)
    {
      public Predicate toPredicate(Root<ServiceLog> root, CriteriaQuery<?> query, CriteriaBuilder cb)
      {
        Path objectExp = root.get("object");
        Path successExp = root.get("success");
        boolean filterObject = (!("all".equals(this.val$object))) && (this.val$object.trim().length() > 0);
        if ((this.val$wrongOnly) && (filterObject)) {
          return cb.and(cb.like(objectExp, this.val$object), cb.equal(successExp, Boolean.valueOf(false)));
        }
        if (this.val$wrongOnly) {
          return cb.equal(successExp, Boolean.valueOf(false));
        }
        if (filterObject) {
          return cb.equal(objectExp, this.val$object);
        }

        return null;
      }
    };
  }

  @RequestMapping({"log.html"})
  public void log(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"log-cmp-names.json"})
  @ResponseBody
  public List<Map<String, String>> logCmpNames() {
    List cmpNames = this.serviceLogDao.cmpNames();
    List model = new ArrayList();
    Map record = new HashMap();
    record.put("name", "全部显示");
    record.put("value", "all");
    model.add(record);
    for (String cmp : cmpNames) {
      record = new HashMap();
      record.put("name", cmp);
      record.put("value", cmp);
      model.add(record);
    }
    return model;
  }

  @RequestMapping({"log-monitor.json"})
  @ResponseBody
  public List<Map<String, Object>> logMonitor(@RequestParam(value="realtime", defaultValue="true") boolean realtime, @RequestParam(value="currentTime", required=false) Long currentTime, @RequestParam(value="beginTime", required=false) Long beginTime, @RequestParam(value="endTime", required=false) Long endTime, @RequestParam("samplingInterval") Long samplingInterval)
  {
    List data = new ArrayList();
    if (realtime) {
      Map map = new HashMap();
      map.put("time", new Date(currentTime.longValue()));
      map.put("count", Long.valueOf(this.serviceLogDao.count(invokeCountBetween(currentTime.longValue() - samplingInterval.longValue(), currentTime.longValue()))));

      data.add(map);
    } else {
      long mod = (endTime.longValue() - beginTime.longValue()) % samplingInterval.longValue();
      if (mod != 0L) {
        beginTime = Long.valueOf(beginTime.longValue() - mod);
      }
    }

    return data;
  }

  private Specification<ServiceLog> invokeCountBetween(long beginTime, long endTime) {
    return new Specification(beginTime, endTime)
    {
      public Predicate toPredicate(Root<ServiceLog> root, CriteriaQuery<?> query, CriteriaBuilder cb)
      {
        Path invokeTimeExp = root.get("invokeTime");
        return cb.between(invokeTimeExp, Long.valueOf(this.val$beginTime), Long.valueOf(this.val$endTime));
      }
    };
  }

  @RequestMapping({"repos.json"})
  @ResponseBody
  public List<Map<String, Object>> repos() {
    List list = ServiceRepositoryHolder.getRepositoryList();
    List model = new ArrayList();
    for (ServiceRepository sr : list) {
      RepositoryInfo ri = sr.getMetaData();
      Map map = new HashMap();
      map.put("alias", ri.getAlias());
      map.put("description", ri.getDescription());
      map.put("basePath", ri.getBasePath());
      map.put("ip", ri.getIp());
      map.put("port", ri.getPort());
      ServiceHelper.RepoState state = this.helper.getRepoState(ri.getAlias());
      map.put("state", Boolean.valueOf(state.getState()));
      map.put("stateMsg", state.getStateMsg());
      model.add(map);
    }
    return model;
  }

  @RequestMapping(value={"saveRepo.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Map<String, Object> saveRepo(@RequestParam("alias") String name, @RequestParam("description") String desc, @RequestParam("ip") String host, @RequestParam("port") int port, @RequestParam("basePath") String basePath)
  {
    Map model = new HashMap();
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(name);
    if (sr != null) {
      ServiceRepositoryHolder.removeRepository(name);
      this.repositoryManager.removeRepository(name);
    }
    this.repositoryManager.registerRepository(name, host, port, basePath, desc);
    model.put("success", Boolean.valueOf(true));
    model.put("msg", "ok");
    return model;
  }

  @RequestMapping({"delRepo.do"})
  @ResponseBody
  public Map<String, Object> delRepo(@RequestParam("repoName") String name) {
    Map model = new HashMap();
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(name);
    if ((sr != null) && (sr.isRemote())) {
      ServiceRepositoryHolder.removeRepository(name);
      this.repositoryManager.removeRepository(name);
    }
    model.put("success", Boolean.valueOf(true));
    model.put("msg", "ok");
    return model;
  }

  @RequestMapping({"modules.json"})
  @ResponseBody
  public List<Map<String, String>> modules(@RequestParam("repoName") String repoName) {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoName);
    List model = new ArrayList();
    Map map;
    if (sr instanceof ThirdpartyServiceRepository) {
      List list = this.moduleDao.findAll();
      Iterator localIterator = list.iterator();
      while (true) { ServiceModule2 m2 = (ServiceModule2)localIterator.next();
        map = new HashMap();
        map.put("id", m2.getId());
        map.put("name", m2.getName());
        map.put("description", m2.getDescription());
        map.put("version", m2.getVersion());
        model.add(map);

        if (!(localIterator.hasNext()))
        {
          label135: return model; } }
    }
    List<ServiceDefinition> defs = sr.getServiceDefinitionList();
    List readedModules = new ArrayList();
    for (ServiceDefinition def : defs) {
      ServiceModule sm = def.getMetaData().getModule();
      if (!(readedModules.contains(sm.getId()))) {
        readedModules.add(sm.getId());
        Map map = new HashMap();
        map.put("id", sm.getId());
        map.put("name", sm.getName());
        map.put("description", sm.getDescription());
        map.put("version", sm.getVersion());
        model.add(map);
      }
    }
    return model;
  }

  @RequestMapping({"saveModule.do"})
  @ResponseBody
  public Object saveModule(@RequestParam("repoId") String repoId, @RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("version") String version, @RequestParam("description") String desc)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    if (sr instanceof ThirdpartyServiceRepository) {
      ServiceModule2 module = new ServiceModule2();
      module.setId(id);
      module.setName(name);
      module.setDescription(desc);
      module.setVersion(version);
      this.moduleDao.saveAndFlush(module);
      this.thirdpartyServiceProcessor.refresh();
    }
    return "{success:true, msg:'ok'}";
  }

  @RequestMapping({"delModule.do"})
  @ResponseBody
  public Object delModule(@RequestParam("repoId") String repoId, @RequestParam("moduleId") String moduleId)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    if (sr instanceof ThirdpartyServiceRepository) {
      this.moduleDao.delete(moduleId);
      this.thirdpartyServiceProcessor.refresh();
    }
    return "{success:true, msg:'ok'}";
  }

  @RequestMapping({"comps.json"})
  @ResponseBody
  public List<Map<String, String>> comps(@RequestParam("repoName") String repoId, @RequestParam("moduleName") String moduleId)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    List model = new ArrayList();
    if (sr instanceof ThirdpartyServiceRepository) {
      List<ServiceComponent2> list = this.compDao.findAll();
      for (ServiceComponent2 comp : list)
        if (comp.getModuleId().equals(moduleId)) {
          Map map = new HashMap();
          map.put("id", comp.getId());
          map.put("name", comp.getName());
          map.put("description", comp.getDescription());
          map.put("helpUrl", comp.getHelpUrl());
          map.put("interfaze", comp.getInterfaze());
          map.put("version", comp.getVersion());
          map.put("wsdl", comp.getWsdl());
          model.add(map);
        }
    }
    else {
      List<ServiceDefinition> defs = sr.getServiceDefinitionList();
      for (ServiceDefinition def : defs) {
        if (def.getMetaData().getModule().getId().equals(moduleId)) {
          ServiceComponent comp = def.getMetaData().getComponent();
          Map map = new HashMap();
          map.put("id", comp.getId());
          map.put("name", comp.getName());
          map.put("description", comp.getDescription());
          map.put("helpUrl", "");
          map.put("interfaze", comp.getInterfaze());
          map.put("version", comp.getVersion());
          map.put("wsdl", comp.getWsdl());
          model.add(map);
        }
      }
    }
    return model;
  }

  @RequestMapping({"saveComp.do"})
  @ResponseBody
  public Object saveComp(@RequestParam("repoId") String repoId, @RequestParam("moduleId") String moduleId, @ModelAttribute("comp") ServiceComponent2 comp)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    if (sr instanceof ThirdpartyServiceRepository)
    {
      comp.setModuleId(moduleId);
      comp.setCategory(moduleId);
      this.compDao.saveAndFlush(comp);
      this.thirdpartyServiceProcessor.refresh();
    }
    return "{success:true, msg:'ok'}";
  }

  @RequestMapping({"delComp"})
  @ResponseBody
  public Object delComp(@RequestParam("repoName") String repoId, @RequestParam("moduleName") String moduleId, @RequestParam("compName") String compId)
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    if (sr instanceof ThirdpartyServiceRepository) {
      this.compDao.delete(compId);
      this.thirdpartyServiceProcessor.refresh();
    }
    return "{success:true, msg:'ok'}";
  }

  @RequestMapping({"operates.json"})
  @ResponseBody
  public List<Map<String, Object>> operates(@RequestParam("repoName") String repoId, @RequestParam("moduleName") String moduleId, @RequestParam("compName") String compId)
  {
    List model = new ArrayList();
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    if (sr instanceof ThirdpartyServiceRepository) {
      ServiceComponent2 comp = (ServiceComponent2)this.compDao.findOne(compId);
      String wsdl = comp.getWsdl();
      model.addAll(this.helper.fetchOperatesFromWSDL(wsdl));
    }
    else
    {
      ServiceDefinition sd;
      Class serviceClass;
      if (sr instanceof RemoteServiceRepository) {
        sd = sr.getServiceDefinition(compId);
        serviceClass = sd.getServiceClass();
        model.addAll(this.helper.fetchOperatesFromClass(serviceClass));
      } else {
        sd = sr.getServiceDefinition(compId);
        serviceClass = sd.getServiceClass();
        model.addAll(this.helper.fetchOperatesFromClass(serviceClass)); }
    }
    return model;
  }

  @RequestMapping({"operateDetail.json"})
  @ResponseBody
  public String operateDetail() {
    return "{success:true, msg:'ok'}";
  }

  @RequestMapping({"viewWsdl.do"})
  public void viewWsdl(@RequestParam("repoName") String repoId, @RequestParam("moduleName") String moduleId, @RequestParam("compName") String compId, HttpServletResponse resp)
    throws Exception
  {
    ServiceRepository sr = ServiceRepositoryHolder.getRepository(repoId);
    String wsdl = null;
    if (sr instanceof ThirdpartyServiceRepository) {
      ServiceComponent2 comp = (ServiceComponent2)this.compDao.findOne(compId);
      wsdl = comp.getWsdl();
    }
    else
    {
      ServiceDefinition sd;
      if (sr instanceof RemoteServiceRepository) {
        sd = sr.getServiceDefinition(compId);
        RepositoryInfo si = sr.getMetaData();
        String base = "http://" + si.getIp() + ':' + si.getPort();
        if (si.getBasePath() != null) {
          base = base + si.getBasePath();
        }
        wsdl = base + sd.getMetaData().getComponent().getWsdl();
      } else {
        sd = sr.getServiceDefinition(compId);
        wsdl = "/services" + sd.getMetaData().getComponent().getWsdl(); }
    }
    if (wsdl != null) {
      if (!(wsdl.endsWith("?wsdl"))) wsdl = wsdl + "?wsdl";
      resp.sendRedirect(wsdl);
    } else {
      resp.sendError(404);
    }
  }
}