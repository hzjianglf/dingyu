package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.filecube.model.FileLog;
import net.risesoft.soa.filecube.service.LogService;
import net.risesoft.soa.filecube.web.util.DateJsonValueProcessor;
import net.risesoft.soa.framework.session.SessionUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class BaseAction extends ActionSupport
{
  protected SessionUser sessionUser = (SessionUser)ActionContext.getContext().getSession().get("session.User");

  private static final Logger log = LoggerFactory.getLogger(BaseAction.class);
  protected HttpServletResponse response = ServletActionContext.getResponse();

  @Autowired
  private LogService fileLogService;

  protected void printJson(String key, String value)
  {
    try
    {
      this.response.setCharacterEncoding("utf-8");
      PrintWriter out = this.response.getWriter();
      JSONObject map = new JSONObject();
      map.put(key, value);
      out.print(JSONObject.fromObject(map));
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void printJsonArray(Object object)
  {
    try
    {
      this.response.setCharacterEncoding("utf-8");
      PrintWriter out = this.response.getWriter();
      JsonConfig config = new JsonConfig();

      config.setIgnoreDefaultExcludes(false);
      config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

      config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
      out.print(JSONArray.fromObject(object, config).toString());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void printJson(Object object)
  {
    try
    {
      this.response.setCharacterEncoding("utf-8");
      PrintWriter out = this.response.getWriter();
      JsonConfig config = new JsonConfig();

      config.setIgnoreDefaultExcludes(false);
      config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

      config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
      out.print(JSONObject.fromObject(object, config).toString());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void saveSystemLog(String optionType, String title, String description)
  {
    FileLog fileLog = new FileLog();
    fileLog.setIp(this.sessionUser.getIp());
    fileLog.setUserUid(this.sessionUser.getUserUID());
    fileLog.setUserName(this.sessionUser.getUserName());
    fileLog.setLogDate(new Date());
    fileLog.setDescription(description);
    fileLog.setTitle(title);
    fileLog.setLogType(optionType);
    this.fileLogService.save(fileLog);
  }
}