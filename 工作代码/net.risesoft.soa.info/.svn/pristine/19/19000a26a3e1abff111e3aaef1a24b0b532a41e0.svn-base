package net.risesoft.soa.info.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class InfoServiceCache
  implements InitializingBean
{
  private static final String RC7_INFO_TIMEMILLIS = "rc7.info.TimeMillis";
  private HashMap<String, Object> localCache = new HashMap();

  private long localTimeMillis = System.currentTimeMillis();
  private Cache refreshTimeMillisCache;

  @Autowired
  private CacheManager cacheManager;

  public Object get(String key)
  {
    Cache.ValueWrapper valueWrapper = this.refreshTimeMillisCache.get("rc7.info.TimeMillis");
    Long refreshTimeMillis = Long.valueOf((valueWrapper == null) ? 0L : ((Long)valueWrapper.get()).longValue());

    if (this.localTimeMillis < refreshTimeMillis.longValue()) {
      this.localCache.clear();
      this.localTimeMillis = refreshTimeMillis.longValue();
    }

    return this.localCache.get(key);
  }

  public void put(String key, Object obj) {
    this.localCache.put(key, obj);
  }

  public void clear() {
    long refreshTimeMillis = System.currentTimeMillis();

    this.refreshTimeMillisCache.put("rc7.info.TimeMillis", Long.valueOf(refreshTimeMillis));
  }

  public String convertParamsToKey(Object[] params) {
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < params.length; ++i) {
      sb.append('.');

      if (params[i] == null) {
        sb.append('N');
      }
      else if (params[i] instanceof String) {
        sb.append((String)params[i]);
      }
      else if (params[i] instanceof Integer) {
        sb.append((Integer)params[i]);
      }
      else if (params[i] instanceof Long) {
        sb.append((Long)params[i]);
      }
      else if (params[i] instanceof Map)
      {
        Map map = (Map)params[i];
        sb.append(Arrays.toString(map.keySet().toArray()));
        sb.append(Arrays.toString(map.entrySet().toArray()));
      }
      else {
        throw new UnsupportedOperationException("不支持的对象类型！" + params[i]);
      }
    }

    return sb.toString();
  }

  public void afterPropertiesSet() throws Exception {
    this.refreshTimeMillisCache = this.cacheManager.getCache("rc7.info.InfoServiceCache");
  }
}