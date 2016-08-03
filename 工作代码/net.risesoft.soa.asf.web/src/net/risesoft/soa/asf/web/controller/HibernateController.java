package net.risesoft.soa.asf.web.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/hibernate"})
public class HibernateController
{

  @Autowired
  private SessionFactory sessionFactory;

  @RequestMapping
  public String index()
  {
    return "redirect:hibernate/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"statAll.json"})
  @ResponseBody
  public List<StatInfo> statAll() {
    List model = new ArrayList();
    String group = "全局统计";
    Statistics stat = this.sessionFactory.getStatistics();
    DateFormat dformat = DateFormat.getDateTimeInstance();
    if (stat.isStatisticsEnabled()) {
      model.add(new StatInfo("start time: ", dformat.format(new Date(stat.getStartTime())), group));
      model.add(new StatInfo("sessions opened: ", Long.valueOf(stat.getSessionOpenCount()), group));
      model.add(new StatInfo("sessions closed: ", Long.valueOf(stat.getSessionCloseCount()), group));
      model.add(new StatInfo("transactions: ", Long.valueOf(stat.getTransactionCount()), group));
      model.add(new StatInfo("successful transactions: ", Long.valueOf(stat.getSuccessfulTransactionCount()), group));
      model.add(new StatInfo("optimistic lock failures: ", Long.valueOf(stat.getOptimisticFailureCount()), group));
      model.add(new StatInfo("flushes: ", Long.valueOf(stat.getFlushCount()), group));
      model.add(new StatInfo("connections obtained: ", Long.valueOf(stat.getConnectCount()), group));
      model.add(new StatInfo("statements prepared: ", Long.valueOf(stat.getPrepareStatementCount()), group));
      model.add(new StatInfo("statements closed: ", Long.valueOf(stat.getCloseStatementCount()), group));
      model.add(new StatInfo("second level cache puts: ", Long.valueOf(stat.getSecondLevelCachePutCount()), group));
      model.add(new StatInfo("second level cache hits: ", Long.valueOf(stat.getSecondLevelCacheHitCount()), group));
      model.add(new StatInfo("second level cache misses: ", Long.valueOf(stat.getSecondLevelCacheMissCount()), group));
      model.add(new StatInfo("entities loaded: ", Long.valueOf(stat.getEntityLoadCount()), group));
      model.add(new StatInfo("entities updated: ", Long.valueOf(stat.getEntityUpdateCount()), group));
      model.add(new StatInfo("entities inserted: ", Long.valueOf(stat.getEntityInsertCount()), group));
      model.add(new StatInfo("entities deleted: ", Long.valueOf(stat.getEntityDeleteCount()), group));
      model.add(new StatInfo("entities fetched (minimize this): ", Long.valueOf(stat.getEntityFetchCount()), group));
      model.add(new StatInfo("collections loaded: ", Long.valueOf(stat.getCollectionLoadCount()), group));
      model.add(new StatInfo("collections updated: ", Long.valueOf(stat.getCollectionUpdateCount()), group));
      model.add(new StatInfo("collections removed: ", Long.valueOf(stat.getCollectionRemoveCount()), group));
      model.add(new StatInfo("collections recreated: ", Long.valueOf(stat.getCollectionRecreateCount()), group));
      model.add(new StatInfo("collections fetched (minimize this): ", Long.valueOf(stat.getCollectionFetchCount()), group));
      model.add(new StatInfo("queries executed to database: ", Long.valueOf(stat.getQueryExecutionCount()), group));
      model.add(new StatInfo("query cache puts: ", Long.valueOf(stat.getQueryCachePutCount()), group));
      model.add(new StatInfo("query cache hits: ", Long.valueOf(stat.getQueryCacheHitCount()), group));
      model.add(new StatInfo("query cache misses: ", Long.valueOf(stat.getQueryCacheMissCount()), group));
      model.add(new StatInfo("max query time: ", Long.valueOf(stat.getQueryExecutionMaxTime()), group));
      model.add(new StatInfo("max time query string", stat.getQueryExecutionMaxTimeQueryString(), group));
    }
    else {
      model.add(new StatInfo("Hibernate 统计", "已禁用", group));
    }
    return model;
  }

  public void statEntity(String entity)
  {
  }

  public void statQuery(String query)
  {
  }

  @RequestMapping({"clearL2.do"})
  @ResponseBody
  public Map<String, Object> clearL2(@RequestParam(value="regions", defaultValue="e,c,q") String regions) {
    Cache cache = this.sessionFactory.getCache();
    Map model = new HashMap();
    if (cache != null) {
      if (regions.contains("e")) {
        cache.evictEntityRegions();
      }
      if (regions.contains("c")) {
        cache.evictCollectionRegions();
      }
      if (regions.contains("q")) {
        cache.evictQueryRegions();
        cache.evictDefaultQueryRegion();
      }
    }
    model.put("sucess", Boolean.TRUE);
    model.put("msg", "ok");
    return model;
  }

  public static class StatInfo
  {
    private String key;
    private Object value;
    private String group;

    public StatInfo(String key, Object value, String group) {
      this.key = key;
      this.value = value;
      this.group = group;
    }

    public String getKey() {
      return this.key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public Object getValue() {
      return this.value;
    }

    public void setValue(Object value) {
      this.value = value;
    }

    public String getGroup() {
      return this.group;
    }

    public void setGroup(String group) {
      this.group = group;
    }
  }
}