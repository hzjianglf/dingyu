package net.risesoft.soa.info.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.service.InfoFileManager;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import net.risesoft.soa.info.util.FileContentType;

public class InfoFileList extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String id;
  private List<String[]> list;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<String[]> getList() {
    return this.list;
  }

  public void setList(List<String[]> list) {
    this.list = list;
  }

  public String execute() throws Exception
  {
    if ((this.operation.equals("create")) || (this.operation.equals("edit"))) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
      List ifs = ifm.find(this.id, 0, "file");
      this.list = new ArrayList();
      try {
        for (int i = 0; i < ifs.size(); i++) {
          String[] string = new String[5];
          InformationFile information = (InformationFile)ifs.get(i);
          string[0] = information.getId();
          string[1] = information.getFileName();
          string[2] = (information.getFileLength() / 1024L + "K");
          string[3] = information.getCreaterName();
          string[4] = sdf.format(information.getCreateDateTime());
          this.list.add(string);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return "success";
    }
    if (this.operation.equals("list"))
    {
      InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
      List ifs = ifm.find(this.id, 0, "file");
      this.list = new ArrayList();
      for (int i = 0; i < ifs.size(); i++) {
        InformationFile informationFile = (InformationFile)ifs.get(i);
        String ext = informationFile.getFileNameExt();

        String[] string = new String[5];
        string[0] = informationFile.getId();
        string[1] = informationFile.getFileName();
        if (ext == null) {
          string[2] = "other";
        }
        else if (FileContentType.isImgType(ext.toLowerCase())) {
          string[2] = "image";
          string[3] = ("/info/infoImgShow.action?id=" + string[0]);
        } else if ((FileContentType.isOfficeType(ext.toLowerCase())) || (FileContentType.isPdfType(ext.toLowerCase()))) {
          string[2] = "office";
          string[3] = FileContentType.getIcon(ext.toLowerCase());
        } else {
          string[2] = "other";
          string[3] = FileContentType.getIcon(ext.toLowerCase());
        }

        string[4] = informationFile.getFileNameExt();
        this.list.add(string);
      }

      return this.operation;
    }
    if ("remove".equals(this.operation)) {
      HttpServletResponse response = getServletResponse();
      response.setContentType("text/html;charset=UTF-8");
      InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
      InformationFile informationFile = ifm.get(this.id);
      if (informationFile != null) {
        try {
          String path = ConfigUtil.filePath + informationFile.getRealPath() + informationFile.getId() + informationFile.getFileNameExt();
          File file = new File(path);
          if (file.exists()) {
            file.delete();
          }
          String ext = informationFile.getFileNameExt();
          if (FileContentType.isOfficeType(ext)) {
            path = ConfigUtil.filePath + informationFile.getRealPath() + informationFile.getId() + ".swf";
            file = new File(path);
            if (file.exists())
              file.delete();
          }
        }
        catch (Exception localException1) {
          response.getWriter().write("{\"success\":false,\"message\":\"移除附件失败!\"}");
          return "none";
        }
      }
      ifm.delete(this.id);
      response.getWriter().write("{\"success\":true,\"message\":\"移除附件成功!\"}");
    }
    if ("index".equals(this.operation)) {
      HttpServletRequest request = getServletRequest();
      String indexs = request.getParameter("indexs");
      if ((indexs == null) || (indexs.length() == 0)) {
        return "none";
      }
      InfoFileManager ifm = (InfoFileManager)SpringUtil.getBean("infoFileManager");
      String[] ids = indexs.split(",");
      for (int i = 0; i < ids.length; i++) {
        InformationFile informationFile = ifm.get(ids[i]);
        informationFile.setTabIndex(i + 1);
        ifm.update(informationFile);
      }
    }
    return "none";
  }
}