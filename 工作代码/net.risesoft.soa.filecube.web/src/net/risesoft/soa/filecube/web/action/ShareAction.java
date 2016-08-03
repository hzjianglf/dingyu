package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.model.FileShare;
import net.risesoft.soa.filecube.service.ShareService;
import net.risesoft.soa.filecube.service.util.OrgTreeUtil;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.framework.session.SessionUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class ShareAction extends BaseAction
{
  private static final Logger log = LoggerFactory.getLogger(ShareAction.class);

  @Autowired
  private ShareService shareService;

  @Autowired
  private OrgTreeUtil orgTreeUtil;
  private FileShare fileShare;
  private String node;
  private String searchValue;
  private String orgType;
  private String shareDataJson;
  private int start;
  private int rows;

  public void delete() { this.shareService.delete(this.fileShare.getShareUid());
    saveSystemLog(OperationType.FC_DELETE.name(), "删除共享记录", "记录标识：" + this.fileShare.getShareUid());
    printJson("success", "OK");
  }

  public void deleteShareToMe()
  {
    this.shareService.deleteOne(this.fileShare.getShareUid());
    printJson("success", "OK");
  }

  public String preList()
  {
    Map fileShares = this.shareService.findByUserUid(this.sessionUser.getUserUID(), this.start, this.rows);
    ActionContext.getContext().getContextMap().put("fileShares", fileShares.get("datas"));
    ActionContext.getContext().getContextMap().put("start", Integer.valueOf(this.start));
    ActionContext.getContext().getContextMap().put("rows", Integer.valueOf(this.rows));
    ActionContext.getContext().getContextMap().put("totalCount", fileShares.get("totalCount"));
    saveSystemLog(OperationType.FC_VIEW.toString(), "获取共享记录", "获取共享记录");
    return "preList";
  }

  public void getShareOrgUid()
  {
    String uid = "";
    if ((this.fileShare.getFileFolder() != null) && (!"".equals(this.fileShare.getFileFolder().getFolderUid())))
      uid = this.fileShare.getFileFolder().getFolderUid();
    else {
      uid = this.fileShare.getFileInfo().getFileUid();
    }
    List<FileShare> fileShares = this.shareService.findByUserUidAndFileUid(this.sessionUser.getUserUID(), uid);
    List strings = new ArrayList();
    for (FileShare fileShare : fileShares) {
      strings.add(fileShare.getOrgUid());
    }
    printJsonArray(strings);
  }

  public void orgTree()
  {
    String json = "";
    if ((this.searchValue != null) && (this.searchValue.length() > 0) && (this.node.equals("root"))) {
      json = this.orgTreeUtil.getSearchJson(this.searchValue);
    }
    else if ((this.node != null) && (this.node.equals("root")))
      json = this.orgTreeUtil.getFirstTree();
    else {
      json = this.orgTreeUtil.getSubTree(this.node, this.orgType);
    }
    try
    {
      this.response.setCharacterEncoding("utf-8");
      this.response.getWriter().write(json);
    } catch (IOException localIOException) {
      log.error("返回组织机构树失败！");
    }
  }

  public String preShare()
  {
    ActionContext.getContext().getContextMap().put("fileShare", this.fileShare);
    return "preShare";
  }

  public void share()
  {
    JSONArray shareData = JSONArray.fromObject(this.shareDataJson);
    List fileShares = new ArrayList();

    Date shareDate = new Date();
    String fileUid = "";
    for (Iterator localIterator = shareData.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
      JSONObject jsonObject = (JSONObject)obj;
      FileShare fileShare = new FileShare();

      fileUid = (String) jsonObject.get("fileUid");
      FileInfo fileInfo = new FileInfo();
      fileInfo.setFileUid(fileUid);
      fileShare.setFileInfo(fileInfo);
      if (fileUid.equals("")) {
        fileShare.setFileInfo(null);
      }

      String folderUid = (String) jsonObject.get("folderUid");
      FileFolder fileFolder = new FileFolder();
      fileFolder.setFolderUid(folderUid);
      fileShare.setFileFolder(fileFolder);
      if (folderUid.equals("")) {
        fileShare.setFileFolder(null);
      }

      fileShare.setUserUid(this.sessionUser.getUserUID());
      fileShare.setUserName(this.sessionUser.getUserName());
      fileShare.setShareDate(shareDate);
      fileShare.setOrgUid((String) jsonObject.get("orgUid"));
      fileShare.setOrgType((String) jsonObject.get("orgType"));
      fileShares.add(fileShare);
    }
    this.shareService.save(fileShares);
    saveSystemLog(OperationType.FC_ADD.name(), "共享文件", "文件唯一标识:" + fileUid);
    printJson("success", "共享成功！");
  }

  public String preOtherToMeList() {
    Map m = this.shareService.shareToMeList(this.sessionUser.getUserUID(), this.start, this.rows);
    ActionContext.getContext().getContextMap().put("otherToMeList", m.get("datas"));
    ActionContext.getContext().getContextMap().put("totalCount", m.get("totalCount"));
    ActionContext.getContext().getContextMap().put("start", Integer.valueOf(this.start));
    ActionContext.getContext().getContextMap().put("rows", Integer.valueOf(this.rows));
    return "preOtherToMeList";
  }

  public FileShare getFileShare() {
    return this.fileShare;
  }
  public void setFileShare(FileShare fileShare) {
    this.fileShare = fileShare;
  }

  public final String getNode()
  {
    return this.node;
  }

  public final void setNode(String node) {
    this.node = node;
  }

  public final String getSearchValue() {
    return this.searchValue;
  }

  public final void setSearchValue(String searchValue) {
    this.searchValue = searchValue;
  }

  public final String getOrgType() {
    return this.orgType;
  }

  public final void setOrgType(String orgType) {
    this.orgType = orgType;
  }
  public String getShareDataJson() {
    return this.shareDataJson;
  }
  public void setShareDataJson(String shareDataJson) {
    this.shareDataJson = shareDataJson;
  }
  public int getStart() {
    return this.start;
  }
  public void setStart(int start) {
    this.start = start;
  }
  public int getRows() {
    return this.rows;
  }
  public void setRows(int rows) {
    this.rows = rows;
  }
}