package net.risesoft.soa.info.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.info.dao.InformationFileDao;
import net.risesoft.soa.info.model.InformationFile;
import net.risesoft.soa.info.tools.SpringUtil;
import net.risesoft.soa.info.util.ConfigUtil;
import net.risesoft.soa.info.util.ResizeImage;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

public class InfoImgShow extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String id;
  private String type;
  private int width = 200;

  private int height = 200;
  private InformationFile informationFile;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getWidth() {
    return this.width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return this.height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String execute() throws Exception
  {
    HttpServletResponse response = ServletActionContext.getResponse();
    InformationFileDao informationFileDao = (InformationFileDao)SpringUtil.getBean("informationFileDao");
    OutputStream os = null;
    FileInputStream fis = null;
    try {
      this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
      String path;
      if ((this.type != null) && (this.type.length() > 0)) {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
      }
      else {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + "_small" + this.informationFile.getFileNameExt();
      }
      File file = new File(path);
      if (!(file.exists())) {
        String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
        ResizeImage.resize(tempPath, path, this.width, this.height);
        file = new File(path);
      }
      fis = new FileInputStream(file);
      response.reset();
      response.setHeader("Content-Type", "application/x-msdownload");
      response.setHeader("Content-Disposition", "attachment;filename=" + 
        new String(this.informationFile.getFileName().getBytes("gb2312"), "iso-8859-1"));
      os = response.getOutputStream();
      IOUtils.copy(fis, os);
      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fis != null) {
          fis.close();
        }
        if (os != null)
          os.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }

    return "none";
  }

  public InputStream getInputStream() throws Exception
  {
    InformationFileDao informationFileDao = (InformationFileDao)SpringUtil.getBean("informationFileDao");
    InputStream is = null;
    try {
      this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
      String path;
      if ((this.type != null) && (this.type.length() > 0)) {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
      }
      else {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + "_small" + this.informationFile.getFileNameExt();
      }
      File file = new File(path);
      if (!(file.exists())) {
        String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
        ResizeImage.resize(tempPath, path, this.width, this.height);
        file = new File(path);
      }
      if (file.exists())
        is = new FileInputStream(file);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return is;
  }

  public String getFileName()
  {
    String fileName = this.informationFile.getFileName();
    if (fileName != null)
      try {
        fileName = URLEncoder.encode(fileName, "utf-8");
      }
      catch (Exception localException)
      {
      }
    return fileName;
  }
}