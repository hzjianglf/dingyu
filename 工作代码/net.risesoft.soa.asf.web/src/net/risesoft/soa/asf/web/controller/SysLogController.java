package net.risesoft.soa.asf.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.risesoft.soa.asf.web.helper.BundleHelper;
import net.risesoft.soa.asf.web.helper.SysLogHelper;
import net.risesoft.soa.asf.web.helper.ZipHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/syslog"})
public class SysLogController
{
  private static final Logger log = LoggerFactory.getLogger(SysLogController.class);

  @Autowired
  private SysLogHelper helper;

  @Autowired
  private ZipHelper zipHelper;

  @Autowired
  private BundleHelper bundleHelper;

  @RequestMapping
  public String index() {
    return "redirect:syslog/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
    model.put("osgiDev", String.valueOf(this.bundleHelper.isDevMode()));
  }

  @RequestMapping({"loggers.json"})
  @ResponseBody
  public List<Map<String, String>> loggers(@RequestParam(value="keyword", defaultValue="") String keyword) {
    log.debug(MessageFormat.format("获取 Logger 列表: [Keyword={0}]", new Object[] { keyword }));
    List model = new ArrayList();
    if (keyword.length() > 0) {
      List<String> loggers = this.helper.getLoggerList();
      for (String logger : loggers) {
        if (logger.contains(keyword)) {
          Map map = new HashMap();
          map.put("logger", logger);
          map.put("level", this.helper.getLoggerLevel(logger));
          map.put("effectiveLevel", this.helper.getLoggerEffectiveLevel(logger));
          model.add(map);
        }
      }
    }
    return model;
  }

  @RequestMapping(value={"setLoggerLevel.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public String setLoggerLevel(@RequestParam("loggers") String logger, @RequestParam("levels") String level, HttpServletRequest req)
  {
    log.debug(MessageFormat.format("设置 Logger Level: [loggers={0}; levels={1}]", new Object[] { logger, level }));
    String[] loggers = logger.split(",");
    String[] levels = level.split(",");
    int maxIndex = Math.min(loggers.length, levels.length);
    for (int i = 0; i < maxIndex; ++i) {
      String log = loggers[i].trim();
      String lev = levels[i].trim();
      this.helper.setLoggerLevel(log, lev);
    }
    return "{sucess:true,msg:'ok'}";
  }

  @RequestMapping({"downloadLog.do"})
  public String downloadLog(@RequestParam(value="logFiles", defaultValue="") String logFiles, HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    if (this.bundleHelper.isDevMode()) {
      return null;
    }
    response.setContentType("text/html;charset=utf-8");
    request.setCharacterEncoding("UTF-8");
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    File logsDir = new File(System.getProperty("user.dir") + "/../logs/");

    File logzip = File.createTempFile("RC7-Logs-", ".zip");

    if ((logsDir.exists()) && (logsDir.isDirectory())) {
      List keywords = Arrays.asList(logFiles.split("\\s*,\\s*"));
      File[] files = logsDir.listFiles(new FilenameFilter(logFiles, keywords)
      {
        public boolean accept(File dir, String name)
        {
          return ((this.val$logFiles.length() == 0) || (this.val$keywords.contains(name)));
        }
      });
      if ((files != null) && (files.length > 0))
        this.zipHelper.zipFiles(logzip.getAbsolutePath(), files);
    }
    try
    {
      long fileLength = logzip.length();
      response.setContentType("application/x-msdownload;");
      response.setHeader("Content-disposition", 
        "attachment; filename=" + new String(logzip.getName().getBytes("utf-8"), "ISO-8859-1"));
      response.setHeader("Content-Length", String.valueOf(fileLength));
      bis = new BufferedInputStream(new FileInputStream(logzip));
      bos = new BufferedOutputStream(response.getOutputStream());
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
        bos.write(buff, 0, bytesRead);
    }
    catch (Exception e) {
      log.warn(e.getMessage());
    } finally {
      if (bis != null) bis.close();
      if (bos != null) bos.close();
    }
    return null;
  }

  @RequestMapping({"logFiles.json"})
  @ResponseBody
  public List<Map<String, Object>> viewLogFiles() {
    List model = new ArrayList();
    if (this.bundleHelper.isDevMode()) {
      return model;
    }
    File logsDir = new File(System.getProperty("user.dir") + "/../logs/");

    if ((logsDir.exists()) && (logsDir.isDirectory())) {
      File[] logFiles = logsDir.listFiles(new FileFilter()
      {
        public boolean accept(File pathname)
        {
          return pathname.isFile();
        }
      });
      if ((logFiles != null) && (logFiles.length > 0)) {
        for (File f : logFiles) {
          HashMap map = new HashMap();
          map.put("name", f.getName());
          map.put("path", f.getParent());
          map.put("lastModified", Long.valueOf(f.lastModified()));
          map.put("length", (f.length() / 1024L) + " KB");
          model.add(map);
        }
      }
    }
    return model;
  }

  @RequestMapping({"deleteHistoryLog.do"})
  @ResponseBody
  public Map<String, Object> deleteHistory() {
    Map model = new HashMap();
    if (this.bundleHelper.isDevMode()) {
      return model;
    }
    File logsDir = new File(System.getProperty("user.dir") + "/../logs/");
    if ((logsDir.exists()) && (logsDir.isDirectory())) {
      File[] hisLogs = logsDir.listFiles(new FilenameFilter()
      {
        public boolean accept(File dir, String name)
        {
          return name.startsWith("rc7.log.");
        }
      });
      if (hisLogs != null) for (File f : hisLogs) {
          f.delete();
        }
    }
    model.put("success", Boolean.valueOf(true));
    model.put("msg", "ok");
    return model;
  }
}