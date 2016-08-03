package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.model.FileCommentary;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.service.CommentaryService;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.framework.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class CommentaryAction extends BaseAction
{

  @Autowired
  private CommentaryService commentaryService;
  private FileCommentary commentary;
  private FileInfo fileInfo;

  public void add()
  {
    this.commentary.setUserUid(this.sessionUser.getUserUID());
    this.commentary.setCreateDate(new Date());
    this.commentary.setFileInfo(this.fileInfo);
    this.commentary.setUserName(this.sessionUser.getUserName());
    FileCommentary cFileCommentary = this.commentaryService.save(this.commentary);
    saveSystemLog(OperationType.FC_ADD.name(), "添加评论", 
      "添加成功！文件标识:" + this.commentary.getFileInfo().getFileUid() + 
      ",评论标识:" + this.commentary.getCommentaryUid());
    printJson(cFileCommentary);
  }

  public void scoreAndUserCount()
  {
    List<FileCommentary> commentaries = this.commentaryService.findByFileUid(this.fileInfo.getFileUid());

    int commentariesCount = commentaries.size();
    for (FileCommentary fileCommentary : commentaries) {
      fileCommentary.getScore();
    }
    Map rtnMap = new HashMap();
    DecimalFormat df = new DecimalFormat("#.0");
    if (commentariesCount == 0)
      rtnMap.put("score", Integer.valueOf(0));
    else {
      rtnMap.put("score", df.format(commentariesCount));
    }
    rtnMap.put("userCount", Integer.valueOf(this.commentaryService.findUserCount(this.fileInfo.getFileUid()).size()));
    printJson(rtnMap);
  }

  public void delete()
  {
    this.commentaryService.delete(this.commentary.getCommentaryUid());
    saveSystemLog(OperationType.FC_DELETE.name(), "删除共享记录", 
      "删除成功! 共享标识：" + this.commentary.getCommentaryUid());
    printJson("success", "删除成功！");
  }

  public String preList()
  {
    List commentaries = this.commentaryService.findByFileUid(this.fileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("fileUid", this.fileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("commentaries", commentaries);
    return "preList";
  }

  public FileCommentary getCommentary() {
    return this.commentary;
  }
  public void setCommentary(FileCommentary commentary) {
    this.commentary = commentary;
  }
  public FileInfo getFileInfo() {
    return this.fileInfo;
  }
  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }
}