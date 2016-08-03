package net.risesoft.soa.asf.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.risesoft.soa.framework.bizlog.BizLogModel;
import net.risesoft.soa.framework.bizlog.BizLogService;
import net.risesoft.soa.framework.bizlog.PageRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/bizlog/"})
public class BizLogController
{
  private static final String APP_ALL = "all";

  @Autowired
  private BizLogService bizlogService;

  @RequestMapping({"list.do"})
  public void list(HttpServletRequest req, @RequestParam(value="app", defaultValue="all") String app, Map<String, String> model)
  {
    model.put("AppRoot", req.getContextPath());
    model.put("app", app);
  }

  @RequestMapping(value={"/list.json"}, produces={"application/json"})
  @ResponseBody
  public Map<String, Object> list(@RequestParam(value="app", defaultValue="all") String app, @RequestParam(value="start", defaultValue="0") int start, @RequestParam(value="limit", defaultValue="20") int limit)
  {
    boolean all = "all".equals(app);
    PageRecord pageRecord = (all) ? this.bizlogService.list(start, limit) : this.bizlogService.listByApp(app, 
      start, 
      limit);
    Map model = new HashMap();
    model.put("data", pageRecord.getContent());
    model.put("totalCount", Integer.valueOf(pageRecord.getTotalCount()));
    return model;
  }

  @RequestMapping(value={"detail.json"}, produces={"application/json"})
  @ResponseBody
  public BizLogModel detail(@RequestParam("id") String id) {
    return this.bizlogService.detail(id);
  }

  @RequestMapping(value={"/appNames.json"}, produces={"application/json"})
  @ResponseBody
  public List<Map<String, String>> appNames() {
    List <String>appNames = this.bizlogService.appNames();
    List model = new ArrayList();
    Map record = new HashMap();
    record.put("name", "全部显示");
    record.put("value", "all");
    model.add(record);
    for (String app : appNames) {
      record = new HashMap();
      record.put("name", app);
      record.put("value", app);
      model.add(record);
    }
    return model;
  }
}