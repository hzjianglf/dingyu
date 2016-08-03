package net.risesoft.soa.asf.core;

import egov.appservice.asf.service.ServiceParameter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.http.HttpSession;
import net.risesoft.soa.asf.core.util.RuntimeConfig;
import net.risesoft.soa.asf.core.util.TimeUtils;
import net.risesoft.soa.asf.core.ws.HttpSessionManager;
import net.risesoft.soa.asf.dao.ServiceLogDao;
import net.risesoft.soa.asf.model.ServiceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceProxyFactory
  implements InitializingBean
{

  @Autowired
  private ServiceLogDao serviceLogDao;
  private ServiceLogSaveThread serviceLogSaveThread;

  public Object getProxy(Class<?> serviceClass, Object serviceObj, Map params)
  {
    return Proxy.newProxyInstance(serviceObj.getClass().getClassLoader(), 
      new Class[] { serviceClass }, 
      new InvocationHandler(serviceClass, params, serviceObj)
    {
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ServiceLog svcLog = new ServiceLog();
        svcLog.setObject(this.val$serviceClass.getName());
        svcLog.setMethod(method.getName());
        svcLog.setInvokeTime(System.currentTimeMillis());
        ServiceProxyFactory.bindParams(this.val$params);
        long startInvoke = TimeUtils.currentTimeMillis();
        Object result = null;
        try {
          result = method.invoke(this.val$serviceObj, args);
          if ((ServiceProxyFactory.isAuthenticateService(this.val$serviceClass, method)) && (result != null)) {
            HttpSession session = HttpSessionManager.getHttpSession();
            if (session != null) {
              session.setAttribute("ASF_VALID_USER", "true");
            }
          }
          svcLog.setProcessTime(TimeUtils.currentTimeMillis() - startInvoke);
          svcLog.setSuccess(true);
          svcLog.setErrMsg("");
          svcLog.setThrowable("");
        } catch (Throwable t) {
          svcLog.setProcessTime(TimeUtils.currentTimeMillis() - startInvoke);
          svcLog.setSuccess(false);
          if (t instanceof InvocationTargetException) {
            InvocationTargetException ite = (InvocationTargetException)t;
            Throwable te = ite.getTargetException();
            svcLog.setErrMsg(te.getMessage());
            svcLog.setThrowable(ServiceProxyFactory.throwableToString(te));
            throw te;
          }
          svcLog.setErrMsg(t.getMessage());
          svcLog.setThrowable(ServiceProxyFactory.throwableToString(t));
          throw t;
        }
        finally {
          ServiceProxyFactory.unbindParams();

          ServiceProxyFactory.this.serviceLogSaveThread.saveServiceLog(svcLog);
        }
        return result;
      }
    });
  }

  static void bindParams(Map params)
  {
    ServiceParameter.set(params);
  }

  static void unbindParams() {
    ServiceParameter.set(null);
  }

  static boolean isAuthenticateService(Class<?> serviceClass, Method method) {
    return (("egov.appservice.org.service.AuthenticateService".equals(serviceClass.getName())) && 
      ("authenticate".equals(method.getName())));
  }

  static String throwableToString(Throwable thr) {
    StringWriter sw = new StringWriter();
    thr.printStackTrace(new PrintWriter(sw, true));
    return sw.getBuffer().toString();
  }

  public void afterPropertiesSet()
    throws Exception
  {
    boolean saveEnabled = Boolean.parseBoolean(RuntimeConfig.getProperty("asf.servicelog.save", "true"));
    this.serviceLogSaveThread = new ServiceLogSaveThread(this.serviceLogDao, saveEnabled);
  }

  private static class ServiceLogSaveThread
    implements Runnable
  {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogSaveThread.class);

    private boolean running = true;

    private static int currentThreadIndex = 0;

    private static int MAX_WAITTOPERSISTENCEQUEUE = 100000;

    private BlockingQueue<ServiceLog> waitToPersistenceQueue = new LinkedBlockingQueue();
    private ServiceLogDao dao;
    private final boolean saveEnabled;

    public ServiceLogSaveThread(ServiceLogDao dao, boolean saveEnabled)
    {
      this.dao = dao;
      this.saveEnabled = saveEnabled;

      Thread t = new Thread(this);

      t.setPriority(t.getPriority() - 1);
      t.setName("ServiceLogSaveThread-" + (currentThreadIndex++));
      t.start();

      new ServiceLogSaveThreadShutdownHook();

      if (logger.isDebugEnabled())
        logger.debug("保存线程已经启动：this=" + this + ",dao=" + dao);
    }

    public void saveServiceLog(ServiceLog log)
    {
      if (!(this.saveEnabled)) return;
      while (true) try {
          if (this.waitToPersistenceQueue.size() > MAX_WAITTOPERSISTENCEQUEUE) {
            logger.warn("保存线程太慢，处理不过来，跳过记录。obj=" + log);
            return; }
          this.waitToPersistenceQueue.put(log);
        }
        catch (InterruptedException localInterruptedException)
        {
        }
    }

    public void run()
    {
      while (this.running)
        try {
          ServiceLog log = (ServiceLog)this.waitToPersistenceQueue.take();

          this.dao.save(log);
        }
        catch (Throwable e) {
          if (!(e instanceof InterruptedException)) logger.warn("保存 ServiceLog 时发生错误: ", e);
        }
    }

    public void stop()
    {
      this.running = false;
    }

    class ServiceLogSaveThreadShutdownHook implements Runnable
    {
      ServiceLogSaveThreadShutdownHook()
      {
        Runtime.getRuntime().addShutdownHook(new Thread(this));
        ServiceProxyFactory.ServiceLogSaveThread.logger.info(">>> ServiceLogSaveThreadShutdownHook shutdown hook registered.");
      }

      public void run() {
        int size = ServiceProxyFactory.ServiceLogSaveThread.this.waitToPersistenceQueue.size();
        if (size > 0)
          ServiceProxyFactory.ServiceLogSaveThread.logger.warn("尚未写入数据库的对象：" + this + "，size=" + size);
      }
    }
  }
}