package net.risesoft.soa.asf.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.risesoft.soa.asf.web.helper.BundleHelper;
import net.risesoft.soa.asf.web.helper.SystemHelper;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import frameserver.license.CheckLicense;

@Controller
@RequestMapping({"/system"})
public class SystemController
{
  private static final Logger log = LoggerFactory.getLogger(SystemController.class);

  @Autowired
  private BasicDataSource basicDataSource;

  @Autowired
  private SystemHelper helper;

  @Autowired
  private BundleHelper bundleHelper;

  @Autowired
  private CheckLicense checkLicense;

  @RequestMapping
  public String index() {
    return "redirect:system/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
    model.put("osgiDev", String.valueOf(this.bundleHelper.isDevMode()));
  }

  @RequestMapping({"sysInfo.json"})
  @ResponseBody
  public List<SysInfo> sysInfo() {
    List model = new ArrayList();
    model.addAll(getServerInfo());
    model.addAll(getLicenseInfo());
    model.addAll(getDBInfo());
    model.addAll(getVMInfo());
    return model;
  }

  @RequestMapping({"uploadLicense.do"})
  @ResponseBody
  public String uploadLicense(MultipartRequest multipartRequest) {
    if (!(this.bundleHelper.isDevMode())) {
      MultipartFile multipartFile = multipartRequest.getFile("licenseFile");

      File licenseFile = new File(System.getProperty("user.dir") + "/../license/" + 
        multipartFile.getOriginalFilename());
      try {
        multipartFile.transferTo(licenseFile);
        InputStream istream = null;
        try {
          istream = new FileInputStream(licenseFile);
          if (!(this.checkLicense.check(istream)))
            throw new RuntimeException("License 文件校验失败.");
        }
        finally {
          IOUtils.closeQuietly(istream); } IOUtils.closeQuietly(istream);

        File licensePath = licenseFile.getParentFile();
        if (licensePath.isDirectory()) {
          File[] files = licensePath.listFiles();
          for (File f : files) {
            if ((!(f.isFile())) || (f.equals(licenseFile))) continue; f.delete();
          }
        }

        if (!(this.checkLicense.refresh()))
          throw new RuntimeException("License 文件校验失败.");
      }
      catch (Exception ex) {
        log.error("更新 License 失败: " + ex.getMessage(), ex);
        if (licenseFile.exists()) {
          licenseFile.delete();
        }
        return "{success:false, msg:'" + ex.getMessage() + "'}";
      }
      return "{success:true, msg:'更新 License 成功.'}";
    }
    return "{success:false, msg:'更新 License 文件仅在产品部署模式下可用.'}";
  }

  private List<SysInfo> getServerInfo()
  {
    List list = new ArrayList();
    String group = "1. 服务器信息";
    list.add(new SysInfo("RC7 版本", this.bundleHelper.getRC7Version(), group));
    list.add(new SysInfo("JVM 版本", this.helper.getJavaVM(), group));
    list.add(new SysInfo("OSGi Telnet 端口", this.bundleHelper.getOSGiConsole(), group));
    return list;
  }

  private List<SysInfo> getLicenseInfo() {
    List list = new ArrayList();
    String group = "2. License 文件";
    list.add(new SysInfo("客户", this.checkLicense.getLicensee(), group));
    list.add(new SysInfo("版本", this.checkLicense.getEdition(), group));
    list.add(new SysInfo("类型", this.checkLicense.getType(), group));
    list.add(new SysInfo("过期", this.checkLicense.getExpiration(), group));
    return list;
  }

  private List<SysInfo> getVMInfo() {
    List list = new ArrayList();
    String group = "3. VM 信息";
    RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
    list.add(new SysInfo("BootClassPath", rt.getBootClassPath(), group));
    list.add(new SysInfo("ClassPath", rt.getClassPath(), group));
    list.add(new SysInfo("LibraryPath", rt.getLibraryPath(), group));
    list.add(new SysInfo("ManagementSpecVersion", rt.getManagementSpecVersion(), group));
    list.add(new SysInfo("Name", rt.getName(), group));
    list.add(new SysInfo("SpecName", rt.getSpecName(), group));
    list.add(new SysInfo("SpecVendor", rt.getSpecVendor(), group));
    list.add(new SysInfo("SpecVersion", rt.getSpecVersion(), group));
    list.add(new SysInfo("VmName", rt.getVmName(), group));
    list.add(new SysInfo("VmVendor", rt.getVmVendor(), group));
    list.add(new SysInfo("VmVersion", rt.getVmVersion(), group));
    list.add(new SysInfo("StartTime", DateFormat.getDateTimeInstance().format(new Date(rt.getStartTime())), group));

    list.add(new SysInfo("UpTime", this.helper.getUpTimeStr(rt.getUptime()), group));
    list.add(new SysInfo("InputArguments", rt.getInputArguments(), group));

    group = "6. 系统参数";
    Map <String,String>sysProps = rt.getSystemProperties();
    for (Map.Entry entry : sysProps.entrySet()) {
      list.add(new SysInfo((String)entry.getKey(), entry.getValue(), group));
    }
    Collections.sort(list, new Comparator()
    {
      public int compare(SystemController.SysInfo o1, SystemController.SysInfo o2)
      {
        String key1 = o1.getKey();
        String key2 = o2.getKey();
        return key1.compareToIgnoreCase(key2);
      }
    });
    return list;
  }

  private List<SysInfo> getDBInfo() {
    List list = new ArrayList();
    String group = "4. 数据库信息";
    try {
      Connection conn = this.basicDataSource.getConnection();
      try {
        DatabaseMetaData dbmd = conn.getMetaData();
        list.add(new SysInfo("DatabaseProductName", dbmd.getDatabaseProductName(), group));
        list.add(new SysInfo("DatabaseProductVersion", dbmd.getDatabaseProductVersion(), group));
        list.add(new SysInfo("DatabaseMajorVersion", Integer.valueOf(dbmd.getDatabaseMajorVersion()), group));
        list.add(new SysInfo("DatabaseMinorVersion", Integer.valueOf(dbmd.getDatabaseMinorVersion()), group));
        list.add(new SysInfo("DriverName", dbmd.getDriverName(), group));
        list.add(new SysInfo("DriverVersion", dbmd.getDriverVersion(), group));
        list.add(new SysInfo("DriverMajorVersion", Integer.valueOf(dbmd.getDriverMajorVersion()), group));
        list.add(new SysInfo("DriverMinorVersion", Integer.valueOf(dbmd.getDriverMinorVersion()), group));
      } finally {
        conn.close();
      }
      group = "5. 连接池信息";
      BasicDataSource bds = this.basicDataSource;
      list.add(new SysInfo("InitialSize", Integer.valueOf(bds.getInitialSize()), group));
      list.add(new SysInfo("MaxActive", Integer.valueOf(bds.getMaxActive()), group));
      list.add(new SysInfo("MaxIdle", Integer.valueOf(bds.getMaxIdle()), group));
      list.add(new SysInfo("MinIdle", Integer.valueOf(bds.getMinIdle()), group));
      list.add(new SysInfo("MaxWait", Long.valueOf(bds.getMaxWait()), group));
      list.add(new SysInfo("NumActive", Integer.valueOf(bds.getNumActive()), group));
      list.add(new SysInfo("NumIdle", Integer.valueOf(bds.getNumIdle()), group));
      list.add(new SysInfo("DriverClass", bds.getDriverClassName(), group));
      list.add(new SysInfo("URL", bds.getUrl(), group));
      list.add(new SysInfo("Username", bds.getUsername(), group));
      list.add(new SysInfo("Password", "******", group));
    } catch (Exception ex) {
      log.warn("检索数据库信息时发生错误: " + ex.getMessage(), ex);
    }
    return list;
  }

  public static class SysInfo
  {
    private String key;
    private Object value;
    private String group;

    public SysInfo(String key, Object value, String group) {
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