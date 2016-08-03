package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.model.FileOpenHistory;
import net.risesoft.soa.filecube.service.OpenHistoryService;
import net.risesoft.soa.filecube.util.StringUtils;
import net.risesoft.soa.framework.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class OpenHistoryAction extends BaseAction
{

  @Autowired
  private OpenHistoryService openHistoryService;
  private FileInfo fileInfo;
  private String fileUid;
  private FileOpenHistory fileOpenHistory;

  public void relationOpenHistory()
  {
    List<FileOpenHistory> fileOpenHistories = this.openHistoryService.findRelationOpenHistory(
      this.fileInfo.getFileUid(), this.sessionUser.getUserUID());
    List rtnList = new ArrayList();

    for (FileOpenHistory openHistory : fileOpenHistories) {
      Map rtnMap = new HashMap();
      rtnMap.put("fileUid", openHistory.getFileInfo().getFileUid());
      String fileName = openHistory.getFileInfo().getName();
      rtnMap.put("fileName", fileName);
      rtnMap.put("fileShortName", StringUtils.getShortStr(fileName, 10));
      rtnMap.put("fileExtension", openHistory.getFileInfo().getExtension());
      rtnList.add(rtnMap);
    }
    printJsonArray(rtnList);
  }

  public String preList()
  {
    List fileOpenHistories = 
      this.openHistoryService.findByUserUid(this.sessionUser.getUserUID());
    ActionContext.getContext().getContextMap().put("fileOpenHistories", fileOpenHistories);
    return "preList";
  }

  public void getCount()
  {
    long count = this.openHistoryService.findCountByFileUid(this.fileUid);
    printJson("openCount", count+"");
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }
  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }
  public FileOpenHistory getFileOpenHistory() {
    return this.fileOpenHistory;
  }
  public void setFileOpenHistory(FileOpenHistory fileOpenHistory) {
    this.fileOpenHistory = fileOpenHistory;
  }
  public String getFileUid() {
    return this.fileUid;
  }
  public void setFileUid(String fileUid) {
    this.fileUid = fileUid;
  }
}