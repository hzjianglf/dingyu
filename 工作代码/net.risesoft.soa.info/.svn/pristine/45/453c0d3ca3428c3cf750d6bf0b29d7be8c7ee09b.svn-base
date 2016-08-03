package net.risesoft.soa.info.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.UUID;
import net.risesoft.soa.info.dao.InformationIndexDao;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.model.InformationIndex;
import net.risesoft.soa.info.service.InfoFileManager;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import org.apache.commons.lang.StringUtils;

public class InfoFile extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String id;
  private String infoID;
  private File file;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getInfoID() {
    return this.infoID;
  }

  public void setInfoID(String infoID) {
    this.infoID = infoID;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String execute() throws Exception
  {
    HttpServletRequest request = getServletRequest();
    HttpServletResponse response = getServletResponse();

    response.setContentType("text/html;charset=UTF-8");
    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");
    String contentLengthHeader = request.getHeader("Content-Length");
    String fileName = request.getParameter("fileName");
    Long expectedFileSize = StringUtils.isBlank(contentLengthHeader) ? null : Long.valueOf(Long.parseLong(contentLengthHeader));
    try {
      if (expectedFileSize.longValue() > 0L) {
        InformationIndexDao informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
        InformationIndex informationIndex = (InformationIndex)informationIndexDao.findOne(this.id);
        if (informationIndex != null) {
          this.infoID = informationIndex.getInfoID();
        }
        Information information = InformationList.get(this.infoID);
        InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
        List ifs = ifm.find(this.id);
        InformationFile informationFile = new InformationFile();
        informationFile.setId(UUID.generateUUID());
        informationFile.setInstanceID(this.id);
        informationFile.setInfoID(this.infoID);
        informationFile.setFileName(fileName);
        informationFile.setFileNameExt(fileName.substring(fileName.lastIndexOf(".")));
        informationFile.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
        informationFile.setModule("riseinfo");
        informationFile.setCreater(su.getUserUID());

        informationFile.setCreaterName(su.getUserName());
        informationFile.setCreateDateTime(Calendar.getInstance().getTime());
        informationFile.setUpdateDateTime(Calendar.getInstance().getTime());
        informationFile.setFileType("file");
        informationFile.setFileLength(expectedFileSize.longValue());
        informationFile.setTabIndex(ifs.size() + 1);
        informationFile.setStatus(0);
        informationFile.setRealPath(getRealPath(information.getDn()));
        if (this.file == null)
          ifm.saveFormInputStream(informationFile, request.getInputStream());
        else
          ifm.save(informationFile, this.file);
      }
      else {
        response.getWriter().write("{\"success\":false,\"message\":\"文件大小为0!\"}");
      }
    } catch (Exception e) {
      response.getWriter().write("{\"success\":false,\"message\":\"上传附件失败!\"}");
      e.printStackTrace();
    }
    response.getWriter().write("{\"success\":true,\"message\":\"上传附件成功!\"}");
    return "none";
  }

  public String getRealPath(String dn) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    dn = dn.replaceAll("rsn=", "");
    String[] dns = dn.split(",");
    String path = "";
    for (int i = dns.length - 1; i >= 0; i--) {
      path = path + dns[i] + File.separator;
    }
    File dir = new File(ConfigUtil.filePath + path + sdf.format(Calendar.getInstance().getTime()) + File.separator);
    if (!dir.isDirectory()) {
      dir.mkdirs();
    }

    return path + sdf.format(Calendar.getInstance().getTime()) + File.separator;
  }
}