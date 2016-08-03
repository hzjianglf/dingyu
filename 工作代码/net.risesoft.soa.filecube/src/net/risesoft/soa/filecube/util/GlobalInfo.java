package net.risesoft.soa.filecube.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import net.risesoft.soa.framework.service.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalInfo
{
  protected static Logger log = LoggerFactory.getLogger(GlobalInfo.class);
  public static final int pageSize = 10;
  protected static final String PROPERTY_OPENOFFICE_HOME = "OpenOffice_Home";
  protected static final String PROPERTY_OPENOFFICE_PORT = "OpenOffice_Port";
  protected static final String PROPERTY_OPENOFFICE_IP = "OpenOffice_IP";
  protected static final String PROPERTY_SWFTOOLS_HOME = "SwfTools_HOME";
  protected static final String PROPERTY_SWFTOOLS_COMMAND_S_PARAMS = "SwfTools_Command_S_Params";
  protected static final String PROPERTY_OPENOFFICE_COMMAND = "OpenOffice_Command";
  protected static final String PROPERTY_UPLOADFILEDIR = "uploadFileDir";
  protected static final String FILE_ROOT_ID = "fileRootID";
  protected static final String RESOURCEUID_GLOBAL = "globalResourceUid";
  protected static final String RESOURCEUID_DEPARTMENT = "departMentResourceUid";
  protected static final String HD_FILE_SHARE_DIR = "HD_fileShareDir";
  protected static final String ORG_ROOTS = "orgRoots";
  protected static final String RESOURCE_ROOT = "resourceRoot";
  protected static final String ERROR_LOAD_PROPERTIES = " 加载全局配置文件失败！";
  protected static final String NOTFOUND_OPENOFFICE_HOME = " 没有配置OpenOffice的路径！ ";
  protected static final String NOTFOUND_OPENOFFICE_PATH = " 找不到OpenOffice的安装路径！ ";
  protected static final String NOTFOUND_OPENOFFICE_IP = " 没有配置OpenOffice使用的ip！";
  protected static final String NOTFOUND_OPENOFFICE_PORT = " 没有配置OpenOffice使用端口！";
  protected static final String NOTFOUND_OPENOFFICE_COMMAND = " 没有配置OpenOffice服务启动命令！";
  protected static final String NOTFOUND_SWFTOOLS_HOME = " 没有配置SwfTools_HOME！";
  protected static final String NOTFOUND_SWFTOOLS_COMMAND_S_PARAMS = " 没有配置SwfTools_HOME -s 的命令参数！";
  protected static final String OPERATIONTYPE_DELETE = "OPERATIONTYPE_DELETE";
  protected static final String OPERATIONTYPE_MODIFY = "OPERATIONTYPE_MODIFY";
  protected static final String OPERATIONTYPE_SHARE = "OPERATIONTYPE_SHARE";
  protected static final String OPERATIONTYPE_ADD = "OPERATIONTYPE_ADD";
  protected static final String OPERATIONTYPE_VIEW = "OPERATIONTYPE_VIEW";
  protected static final String OPERATIONTYPE_DOWNLOAD = "OPERATIONTYPE_DOWNLOAD";
  protected static Properties properties = null;
  private static GlobalInfo instance = null;

  public String getOperationType_DELETE()
  {
    String str = properties.getProperty("OPERATIONTYPE_DELETE");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_DELETE");
    return null;
  }

  public String getOperationType_MODIFY()
  {
    String str = properties.getProperty("OPERATIONTYPE_MODIFY");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_MODIFY");
    return null;
  }

  public String getHD_fileShareDir()
  {
    String str = properties.getProperty("HD_fileShareDir");
    if (str != null) {
      try {
        str = new String(str.getBytes("iso-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        log.error("获取海淀文件的根目录失败！");
      }
      return str;
    }
    log.error("没有配置海淀现有文件的根目录！");
    return null;
  }

  public String getOperationType_SHARE()
  {
    String str = properties.getProperty("OPERATIONTYPE_SHARE");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_SHARE");
    return null;
  }

  public String getOperationType_ADD()
  {
    String str = properties.getProperty("OPERATIONTYPE_ADD");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_ADD");
    return null;
  }

  public String getOperationType_VIEW()
  {
    String str = properties.getProperty("OPERATIONTYPE_VIEW");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_VIEW");
    return null;
  }

  public String getOperationType_DOWNLOAD()
  {
    String str = properties.getProperty("OPERATIONTYPE_DOWNLOAD");
    if (str != null) {
      return str;
    }
    log.error("没有操作类型：OPERATIONTYPE_DOWNLOAD");
    return null;
  }

  private GlobalInfo()
  {
    properties = new Properties();
    InputStream is = null;
    try {
      is = Config.getConfigFileAsURL("filecube.properties").openStream();
      try
      {
        properties.load(is);
      } catch (IOException e) {
        log.error(" 加载全局配置文件失败！", e);
        e.printStackTrace();
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
      try
      {
        if (is != null)
          is.close();
      } catch (IOException e) {
        log.error(" 加载全局配置文件失败！", e);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      try
      {
        if (is != null)
          is.close();
      } catch (IOException e) {
        log.error(" 加载全局配置文件失败！", e);
      }
    }
    finally
    {
      try
      {
        if (is != null)
          is.close();
      } catch (IOException e) {
        log.error(" 加载全局配置文件失败！", e);
      }
    }
  }

  public static GlobalInfo getInstance()
  {
    if (instance == null) {
      instance = new GlobalInfo();
    }
    return instance;
  }

  public String getFileRootID()
  {
    String str = properties.getProperty("fileRootID");
    if (str != null) {
      return str;
    }
    log.error("没有配置文件的根目录ID！");
    return null;
  }

  public String getGlobalResourceUid()
  {
    String str = properties.getProperty("globalResourceUid");
    if (str != null) {
      return str;
    }
    log.error("没有配置全局文件的资源根目录！");
    return null;
  }

  public String getDepartMentResourceUid()
  {
    String str = properties.getProperty("departMentResourceUid");
    if (str != null) {
      return str;
    }
    log.error("没有配置部门文件的资源根目录！");
    return null;
  }

  public String getOrgRoots()
  {
    String str = properties.getProperty("orgRoots");
    if (str != null) {
      return str;
    }
    log.error("没有配置组织机构的根节点！");
    return null;
  }

  public String getResourceRoot()
  {
    String str = properties.getProperty("resourceRoot");
    if (str != null) {
      return str;
    }
    log.error("没有配置资源的根节点！");
    return null;
  }

  public String getAsfLoginName()
  {
    String str = properties.getProperty("asfLoginName");
    if (str != null) {
      return str;
    }
    log.error("没有配置asf登录用户名！");
    return null;
  }

  public String getAsfLoginPwd()
  {
    String str = properties.getProperty("asfLoginPwd");
    if (str != null) {
      return str;
    }
    log.error("没有配置asf登录用户的密码！");
    return null;
  }

  public String getOpenOfficeHome()
  {
    String str = properties.getProperty("OpenOffice_Home");
    if (str != null) {
      try {
        str = new String(str.getBytes("iso-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        log.error("读取配置文件上传路径失败！");
      }
      if (!SystemInfo.OS_NAME.toLowerCase().contains("windows")) return "";
      if (!new File(str).exists()) {
        log.error(" 找不到OpenOffice的安装路径！ ");
      }
      return str;
    }
    log.error(" 没有配置OpenOffice的路径！ ");
    return null;
  }

  public String getOpenOfficeIP()
  {
    String str = properties.getProperty("OpenOffice_IP");
    if (str != null) {
      return str;
    }
    log.error(" 没有配置OpenOffice使用的ip！");
    return null;
  }

  public int getOpenOfficePort()
  {
    String str = properties.getProperty("OpenOffice_Port");
    int port = Integer.parseInt(str);
    if (str != null) {
      return port;
    }
    log.error(" 没有配置OpenOffice使用端口！");
    return -1;
  }

  public String getOpenOfficeCommand()
  {
    String str = properties.getProperty("OpenOffice_Command");
    if (str != null) {
      return str;
    }
    log.error(" 没有配置OpenOffice服务启动命令！");
    return null;
  }

  public String getSwfToolsHome()
  {
    String str = properties.getProperty("SwfTools_HOME");
    if (str != null) {
      try {
        str = new String(str.getBytes("iso-8859-1"), "utf-8");
      } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        log.error("读取配置文件上传路径失败！");
      }
      if (!SystemInfo.OS_NAME.toLowerCase().contains("windows")) return "";
      return str;
    }
    log.error(" 没有配置SwfTools_HOME！");
    return null;
  }

  public String getSwfToolsCMDSParam()
  {
    String str = properties.getProperty("SwfTools_Command_S_Params");
    if (str != null) {
      return str;
    }
    log.error(" 没有配置SwfTools_HOME -s 的命令参数！");
    return null;
  }

  public String getUploadPath(String departMentName)
  {
    String str = "";
    String departMentPath = SystemInfo.SEPARATOR_FILE + departMentName + SystemInfo.SEPARATOR_FILE;
    if ("".equals(departMentName)) {
      departMentPath = SystemInfo.SEPARATOR_FILE + "全局文件" + SystemInfo.SEPARATOR_FILE;
    }
    str = str + departMentPath + getDatePath(new Date());
    return str;
  }

  public String getUploadRoot()
  {
    String path = properties.getProperty("uploadFileDir");
    if (path != null) {
      try {
        path = new String(path.getBytes("iso-8859-1"), "utf-8");
      } catch (Exception localException) {
      }
      return path;
    }
    log.error("没有配置上传文件的根目录！");
    return null;
  }

  public static String getDatePath(Date date)
  {
    String rtnStr = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    rtnStr = sdf.format(date);
    return rtnStr;
  }

  public void startOpenOfficeService()
  {
    String openOffice_HOME = getOpenOfficeHome();
    if (openOffice_HOME.charAt(openOffice_HOME.length() - 1) != '/') {
      openOffice_HOME = openOffice_HOME + '/';
    }
    String command = openOffice_HOME + getOpenOfficeCommand();
    Socket socket = null;
    try
    {
      socket = new Socket(getOpenOfficeIP(), getOpenOfficePort());
      socket.close();
    }
    catch (Exception localException) {
      try {
        Runtime.getRuntime().exec(command);
      } catch (IOException localIOException) {
        log.error("启动openoffice服务失败!");
      }
    }
    log.info("启动openoffice服务成功!");
  }
}