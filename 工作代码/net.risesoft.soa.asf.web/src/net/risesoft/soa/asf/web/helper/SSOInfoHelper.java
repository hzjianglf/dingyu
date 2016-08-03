package net.risesoft.soa.asf.web.helper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.risesoft.soa.framework.bizlog.PageRecord;
import net.risesoft.soa.framework.service.sso.log.LoginLog;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jasig.cas.authentication.principal.Service;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class SSOInfoHelper
{

  @Autowired
  private SessionFactory sessionFactory;

  public PageRecord<LoginLog> findLoginLogAsPage(String dnKeyword, int start, int limit)
  {
    String like = '%' + dnKeyword + '%';
    Session session = this.sessionFactory.openSession();
    List content = Collections.emptyList();
    int count = 0;
    try {
      Query q = session.createQuery("from LoginLog t where t.userDN like ? or t.loginName like ? order by t.loginTime desc");
      q.setFirstResult(start);
      q.setMaxResults(limit);
      q.setString(0, like);
      q.setString(1, like);
      content = q.list();
      q = session.createQuery(" select count(*) from LoginLog t where t.userDN like ? or t.loginName like ?");
      q.setString(0, like);
      q.setString(1, like);
      count = ((Long)q.uniqueResult()).intValue();
    } finally {
      session.close();
    }
    return new PageRecord(count, content);
  }

  public List<String> getLogonServices(TicketGrantingTicket ticket) {
    List result = new ArrayList();
    Field servicesField = ReflectionUtils.findField(ticket.getClass(), "services");
    if (servicesField != null) {
      ReflectionUtils.makeAccessible(servicesField);
      Map <String,String>services = (Map)ReflectionUtils.getField(servicesField, ticket);
      if (services != null) {
        for (Map.Entry entry : services.entrySet()) {
          String url = ((Service)entry.getValue()).getId();
          if (url.indexOf(63) > 0) {
            url = url.substring(0, url.indexOf(63));
          }
          if (!(result.contains(url))) {
            result.add(url);
          }
        }
      }
    }
    return result;
  }

  public Object decode(Object v) {
    if (v instanceof String)
      try {
        return URLDecoder.decode((String)v, "UTF-8");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
    return v;
  }

  public boolean matches(String keyword, String[] options) {
    boolean result = false;
    result = keyword.length() == 0;
    for (String opt : options) {
      result |= ((opt != null) && (opt.contains(keyword)));
    }
    return result;
  }
}