package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.service.PermissionService;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.framework.session.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class PermissionAction extends BaseAction
{
  private static final long serialVersionUID = 7255557874672425659L;
  private static final Logger log = LoggerFactory.getLogger(PermissionAction.class);
  private String resourceUid;
  private String operationType;
  private String folderUid;
  private String folderName;
  private String fileUid;

  @Autowired
  private PermissionService permissionService;

  public void getOperationByResourceUid()
  {
    String userUid = this.sessionUser.getUserUID();
    List ops = this.permissionService.getOperations(userUid, this.resourceUid);
    printJsonArray(ops);
  }

  public String preSubResourcesTree()
  {
    ActionContext.getContext().getContextMap().put("folderUid", this.folderUid);
    return "preSubResourcesTree";
  }

  public void getSubResourcesTree()
  {
    if ((this.folderName != null) && (!"".equals(this.folderName))) {
      printJsonArray(this.permissionService.findFolder(this.sessionUser.getUserUID(), 
        this.folderName, this.folderUid));
      return;
    }
    printJsonArray(this.permissionService.getSubResourcesTree(this.sessionUser.getUserUID(), 
      OperationType.FC_VIEW.toString(), this.folderUid));
  }

  public void getResourceRoots()
  {
    printJsonArray(this.permissionService.getResourceRoots(this.sessionUser.getUserUID()));
  }

  public void hasPermission()
  {
    boolean val = this.permissionService.hasPermission(this.sessionUser.getUserUID(), 
      this.resourceUid, this.operationType).booleanValue();

    printJson("allow", val+"");
  }

  public void getPermissionByFileUid() {
    boolean b = this.permissionService.getPermissionByFileUid(this.sessionUser.getUserUID(), 
      this.fileUid, this.operationType);
    printJson("allowed", b+"");
  }

  public void getPermissionByFolderUid() {
    boolean b = this.permissionService.getPermissionByFolderUid(this.sessionUser.getUserUID(), 
      this.folderUid, this.operationType);
    printJson("allowed", b+"");
  }

  public String getOperationType() {
    return this.operationType;
  }
  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }
  public String getResourceUid() {
    return this.resourceUid;
  }
  public void setResourceUid(String resourceUid) {
    this.resourceUid = resourceUid;
  }
  public String getFolderName() {
    return this.folderName;
  }
  public void setFolderName(String folderName) {
    this.folderName = folderName;
  }
  public String getFolderUid() {
    return this.folderUid;
  }
  public void setFolderUid(String folderUid) {
    this.folderUid = folderUid;
  }
  public String getFileUid() {
    return this.fileUid;
  }
  public void setFileUid(String fileUid) {
    this.fileUid = fileUid;
  }
}