package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class RelevanceFileAction extends BaseAction
{

  @Autowired
  private FileService fileService;
  private FileInfo fileInfo;
  private String fileUid;

  public String preFileList()
  {
    FileInfo fileInfo = this.fileService.findById(this.fileUid);
    ActionContext.getContext().getContextMap().put("fileInfo", fileInfo);
    return "preFileList";
  }
  public FileInfo getFileInfo() {
    return this.fileInfo;
  }
  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }
  public String getFileUid() {
    return this.fileUid;
  }
  public void setFileUid(String fileUid) {
    this.fileUid = fileUid;
  }
}