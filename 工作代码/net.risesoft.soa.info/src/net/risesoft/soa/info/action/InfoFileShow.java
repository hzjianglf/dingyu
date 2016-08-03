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
import net.risesoft.soa.info.util.DocumentToSWF;
import net.risesoft.soa.info.util.FileContentType;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

public class InfoFileShow extends BaseAction
{
  private static final long serialVersionUID = -2416532006922433626L;
  private String id;
  private String type;
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

  public String execute() throws Exception
  {
    InformationFileDao informationFileDao = (InformationFileDao)SpringUtil.getBean("informationFileDao");
    HttpServletResponse response = ServletActionContext.getResponse();
    FileInputStream fis = null;
    OutputStream os = null;
    String path = "";
    try {
      if ("docDown".equals(this.type)) {
        this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.informationFile.getId() + this.informationFile.getFileNameExt();
      } else {
        this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
        String ext = this.informationFile.getFileNameExt();
        if (FileContentType.isOfficeType(ext.toLowerCase())) {
          path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + ".swf";
          File file = new File(path);
          if (!file.exists()) {
            String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
            DocumentToSWF docToSWF = new DocumentToSWF(tempPath);
            docToSWF.docToswf();
          }
        }
        else if (FileContentType.isPdfType(ext.toLowerCase())) {
          path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + ".swf";
          File file = new File(path);
          if (!file.exists()) {
            String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
            DocumentToSWF docToSWF = new DocumentToSWF(tempPath);
            docToSWF.pdfToswf(false);
          }
        } else {
          path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
        }
      }
      File file = new File(path);
      if (file.exists()) {
        fis = new FileInputStream(file);
        response.reset();
        response.setHeader("Content-Type", "application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment;filename=" + 
          new String(this.informationFile.getFileName().getBytes("gb2312"), "iso-8859-1"));
        os = response.getOutputStream();
        IOUtils.copy(fis, os);
        os.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
      try
      {
        if (fis != null) {
          fis.close();
        }
        if (os != null)
          os.close();
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
    finally
    {
      try
      {
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
      if ("docDown".equals(this.type)) {
        this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
        String path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.informationFile.getId() + this.informationFile.getFileNameExt();
        File file = new File(path);
        if (file.exists());
        return new FileInputStream(file);
      }

      this.informationFile = ((InformationFile)informationFileDao.findOne(this.id));
      String ext = this.informationFile.getFileNameExt();
      String path = "";
      if (FileContentType.isOfficeType(ext.toLowerCase())) {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + ".swf";
        File file = new File(path);
        if (!file.exists()) {
          String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
          DocumentToSWF docToSWF = new DocumentToSWF(tempPath);
          docToSWF.docToswf();
        }
      }
      else if (FileContentType.isPdfType(ext.toLowerCase())) {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + ".swf";
        File file = new File(path);
        if (!file.exists()) {
          String tempPath = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
          DocumentToSWF docToSWF = new DocumentToSWF(tempPath);
          docToSWF.pdfToswf(false);
        }
      } else {
        path = ConfigUtil.filePath + this.informationFile.getRealPath() + this.id + this.informationFile.getFileNameExt();
      }
      File file = new File(path);
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