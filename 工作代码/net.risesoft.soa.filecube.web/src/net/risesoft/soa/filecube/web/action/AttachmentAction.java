package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.filecube.model.FileAttachment;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.service.AttachmentService;
import net.risesoft.soa.filecube.service.FileService;
import net.risesoft.soa.filecube.util.FileSize;
import net.risesoft.soa.filecube.util.FileType;
import net.risesoft.soa.filecube.util.GlobalInfo;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.framework.session.SessionUser;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class AttachmentAction extends BaseAction
{
  private static final Logger log = LoggerFactory.getLogger(AttachmentAction.class);
  private FileInfo fileInfo;
  private FileAttachment attachment;
  private File uploadFile;
  private String uploadFileName;

  @Autowired
  private AttachmentService attachmentService;

  @Autowired
  private FileService fileService;
  private String attachmentUid;
  private int viewFileWidth;
  private int viewFileHeight;

  public void upload()
  {
    try
    {
      FileAttachment fileAttachment = new FileAttachment();
      String fileName = this.uploadFileName.substring(0, this.uploadFileName.lastIndexOf("."));
      fileAttachment.setName(fileName);
      fileAttachment.setCreateDate(new Date());
      fileAttachment.setDeleted(false);
      String extension = this.uploadFileName.substring(this.uploadFileName.lastIndexOf(".") + 1);

      extension = extension.toLowerCase();
      fileAttachment.setExtension(extension);
      fileAttachment.setFileInfo(this.fileInfo);
      fileAttachment.setDownLoadTimes(0L);
      fileAttachment.setCreatorUid(this.sessionUser.getUserUID());
      fileAttachment.setCreatorName(this.sessionUser.getUserName());
      ServletActionContext.getResponse().setCharacterEncoding("utf-8");
      if (this.uploadFile == null) {
        InputStream fileIs = null;
        try {
          HttpServletRequest request = ServletActionContext.getRequest();
          fileIs = request.getInputStream();
        } catch (IOException e) {
          e.printStackTrace();
        }

        this.attachmentService.save(fileAttachment, fileIs);
      }
      else {
        FileInputStream fis = new FileInputStream(this.uploadFile);
        this.attachmentService.save(fileAttachment, fis);
      }
      try {
        log.info("上传成功！");
        saveSystemLog(OperationType.FC_ADD.name(), "文件附件上传", "上传成功！唯一标识" + fileAttachment.getAttachmentUid());
      } catch (Exception e) {
        saveSystemLog(OperationType.FC_ADD.name(), "文件附件上传", "上传失败！");
        log.error("文件附件上传失败！", e);
      }
      this.response.setCharacterEncoding("utf-8");
      Map rtnData = new HashMap();
      rtnData.put("success", "true");
      rtnData.put("id", fileAttachment.getAttachmentUid());
      rtnData.put("name", fileAttachment.getName());
      rtnData.put("extension", fileAttachment.getExtension());
      rtnData.put("size", FileSize.size(fileAttachment.getSize()));
      rtnData.put("deleteFun", "deleteAttachment('" + fileAttachment.getAttachmentUid() + "')");
      rtnData.put("type", "attachment");
      rtnData.put("fileUid", fileAttachment.getFileInfo().getFileUid());
      printJson(rtnData);
    } catch (Exception e) {
      log.error("上传附件失败！", e);
    }
  }

  public static void main(String[] args) {
    try { System.out.println(new String("Java.Bug妯″紡璇﹁В".getBytes("iso-8859-1"), "utf-8"));
    } catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
  }

  public void download()
  {
    try
    {
      FileAttachment attachment = this.attachmentService.findById(this.attachmentUid);

      long curDownLoadTimes = attachment.getDownLoadTimes();
      attachment.setDownLoadTimes(curDownLoadTimes + 1L);
      this.attachmentService.update(attachment);
      String fileName = attachment.getAttachmentUid() + "." + attachment.getExtension();
      File f = new File(GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + fileName);
      if (!f.exists()) {
        this.response.sendError(404, "附件不存在！");
        saveSystemLog(OperationType.FC_DOWNLOAD.name(), "下载附件失败", 
          "附件未找到！附件标识:" + attachment.getAttachmentUid());
        return;
      }
      BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
      byte[] buf = new byte[1024];
      int len = 0;
      fileName = new String(attachment.getName().getBytes("gb2312"), "ISO-8859-1");
      fileName = fileName + "." + attachment.getExtension();
      this.response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
      OutputStream out = this.response.getOutputStream();
      while ((len = br.read(buf)) > 0)
        out.write(buf, 0, len);
      br.close();
      out.close();
      saveSystemLog(OperationType.FC_DOWNLOAD.name(), "下载附件", 
        "附件标识:" + attachment.getAttachmentUid());
    } catch (FileNotFoundException e) {
      log.error("下载文件失败！", e);
    } catch (IOException e) {
      log.error("下载文件失败！", e);
    }
  }

  public String preList()
  {
    List attachments = this.attachmentService.findByFileUid(this.fileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("attachments", attachments);
    FileInfo info = this.fileService.findById(this.fileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("fileInfo", info);
    saveSystemLog(OperationType.FC_VIEW.toString(), "查看附件列表", "文件标识:" + info.getFileUid());
    return "preList";
  }

  public void delete()
  {
    FileAttachment atta = this.attachmentService.findById(this.attachmentUid);
    saveSystemLog(OperationType.FC_DELETE.name(), "文件附件删除", 
      "删除成功！唯一标识" + atta.getAttachmentUid() + "  所属文件标识 " + atta.getFileInfo().getFileUid());
    this.attachmentService.delete(this.attachmentUid);
    printJson("success", "删除成功！");
  }

  public String preViewFile() {
    FileAttachment attachment = this.attachmentService.findById(this.attachmentUid);
    try
    {
      String ex = attachment.getExtension();
      String fileName = attachment.getAttachmentUid() + "." + attachment.getExtension();
      File f = new File(GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + fileName);

      if (!FileType.acOpenOfficeFormatFile(ex)) {
        fileName = new String(attachment.getName().getBytes("UTF-8"), "ISO8859_1");
        this.response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "." + attachment.getExtension());
        OutputStream out = this.response.getOutputStream();
        FileInputStream fis = new FileInputStream(f);
        IOUtils.copy(fis, out);
        fis.close();
        out.flush();
        return null;
      }
    } catch (Exception e) {
      log.error("", e);

      ActionContext.getContext().getContextMap().put("attrUid", this.attachmentUid);
      ActionContext.getContext().getContextMap().put("viewFileWidth", Integer.valueOf(this.viewFileWidth));
      ActionContext.getContext().getContextMap().put("viewFileHeight", Integer.valueOf(this.viewFileHeight));
    }return "preViewFile";
  }

  public void convertFile()
  {
    this.attachmentService.convertFile(this.attachmentUid);
    printJson("success", "success");
  }

  public void viewFile() {
    FileAttachment attachment = this.attachmentService.findById(this.attachmentUid);
    try
    {
      String ex = attachment.getExtension();
      String fileName = attachment.getAttachmentUid() + "." + attachment.getExtension();
      File f = new File(GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + fileName);

      if (!FileType.acOpenOfficeFormatFile(ex)) {
        fileName = new String(attachment.getName().getBytes("UTF-8"), "ISO8859_1");
        this.response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "." + attachment.getExtension());
        OutputStream out = this.response.getOutputStream();
        FileInputStream fis = new FileInputStream(f);
        IOUtils.copy(fis, out);
        fis.close();
        out.close();
        return;
      }
      String absolutePath = GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + attachment.getAttachmentUid() + ".swf";
      FileInputStream fis = new FileInputStream(new File(absolutePath));
      OutputStream os = this.response.getOutputStream();
      IOUtils.copy(fis, os);
      os.flush();
      fis.close();
      os.close();
      saveSystemLog(OperationType.FC_VIEW.toString(), "查看附件", "附件唯一标识:" + this.attachmentUid);
    } catch (FileNotFoundException e) {
      log.error("文件流获取失败!", e);
    } catch (IOException e) {
      log.error("文件流获取失败!", e);
    }
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }
  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }
  public FileAttachment getAttachment() {
    return this.attachment;
  }
  public void setAttachment(FileAttachment attachment) {
    this.attachment = attachment;
  }
  public File getUploadFile() {
    return this.uploadFile;
  }
  public void setUploadFile(File uploadFile) {
    this.uploadFile = uploadFile;
  }
  public String getUploadFileName() {
    return this.uploadFileName;
  }
  public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
  }
  public int getViewFileWidth() {
    return this.viewFileWidth;
  }
  public void setViewFileWidth(int viewFileWidth) {
    this.viewFileWidth = viewFileWidth;
  }
  public int getViewFileHeight() {
    return this.viewFileHeight;
  }
  public void setViewFileHeight(int viewFileHeight) {
    this.viewFileHeight = viewFileHeight;
  }
  public String getAttachmentUid() {
    return this.attachmentUid;
  }
  public void setAttachmentUid(String attachmentUid) {
    this.attachmentUid = attachmentUid;
  }
}