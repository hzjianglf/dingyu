package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.service.FileService;
import net.risesoft.soa.filecube.service.FolderService;
import net.risesoft.soa.filecube.util.GlobalInfo;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.framework.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;

public class FolderAction extends BaseAction
{

  @Autowired
  private FolderService folderService;

  @Autowired
  private FileService fileService;
  private String batchUids;
  private FileFolder folder;
  private String folderUid;
  private String folderTreeType;

  public String preAdd()
  {
    ActionContext.getContext().getContextMap().put("parentFolderUid", this.folder.getParentUid());
    return "preAdd";
  }

  public void add()
  {
    FileFolder fileFolder = new FileFolder();
    fileFolder.setFolderUid(UUID.randomUUID().toString());
    fileFolder.setName(this.folder.getName());
    fileFolder.setDescription(this.folder.getDescription());
    fileFolder.setCreateDate(new Date());
    fileFolder.setDeleted(Boolean.valueOf(false));
    fileFolder.setParentUid(this.folder.getParentUid());
    fileFolder.setCreatorUid(this.sessionUser.getUserUID());
    fileFolder.setItemType(this.folderTreeType);
    if (this.folderTreeType.equals("personnel"))
      this.folderService.save(fileFolder);
    else {
      this.folderService.saveFolderAndResource(fileFolder);
    }
    saveSystemLog(OperationType.FC_ADD.name(), "保存文件夹", "文件夹标识:" + fileFolder.getFolderUid());
    printJson("success", "保存成功！");
  }

  public String preEdit() {
    FileFolder fileFolder = this.folderService.findById(this.folder.getFolderUid());
    ActionContext.getContext().getContextMap().put("fileFolder", fileFolder);
    return "preEdit";
  }

  public void edit()
  {
    FileFolder fileFolder = this.folderService.findById(this.folder.getFolderUid());
    fileFolder.setName(this.folder.getName());
    fileFolder.setDescription(this.folder.getDescription());
    fileFolder.setModifiedDate(new Date());
    if (this.folderTreeType.equals("personnel"))
      this.folderService.update(fileFolder);
    else {
      this.folderService.updateFolderAndResource(fileFolder);
    }
    saveSystemLog(OperationType.FC_MODIFY.name(), "修改文件夹", "唯一标识:" + this.folder.getFolderUid());
    printJson("success", "修改成功！");
  }

  public void logicDelete()
  {
    this.folderService.logicDelete(this.folder.getFolderUid());
    saveSystemLog(OperationType.FC_DELETE.name(), "文件夹放入回收站", "文件夹唯一标识：" + this.folder.getFolderUid());
    printJson("success", "删除成功！");
  }

  public void recoverFolder()
  {
    FileFolder f = this.folderService.findById(this.folder.getFolderUid());
    f.setDeleted(Boolean.valueOf(false));
    String folderTreeType = f.getItemType();
    if ((folderTreeType != null) && (folderTreeType.equals("personnel")))
      this.folderService.update(f);
    else {
      this.folderService.updateFolderAndResource(f);
    }
    saveSystemLog(OperationType.FC_DELETE.name(), "还原文件夹", "文件夹唯一标识：" + this.folder.getFolderUid());
    printJson("success", "恢复成功！");
  }

  public void delete()
  {
    if (this.folderTreeType.equals("personnel"))
      this.folderService.delete(this.folder.getFolderUid());
    else {
      this.folderService.deleteFolderAndResource(this.folder.getFolderUid());
    }

    saveSystemLog(OperationType.FC_DELETE.name(), "删除文件夹", "文件夹唯一标识：" + this.folder.getFolderUid());
    printJson("success", "删除成功！");
  }

  public void getGlobalFolderTreeRootUid()
  {
    FileFolder fileFolder = this.folderService.findByResourceUid(GlobalInfo.getInstance().getGlobalResourceUid());
    printJson("folderUid", fileFolder.getFolderUid());
  }

  public void globalFolderTree()
  {
    printJsonArray(this.folderService.globalFolderTree());
  }

  public void departMentFolderTree()
  {
    String userUID = this.sessionUser.getUserUID();
    if (this.sessionUser.getOrgType().equals("Admin")) {
      Map rtnMap = this.folderService.allDepartMentFolderTree();
      printJsonArray(rtnMap);
      return;
    }
    Map rtnMap = this.folderService.departMentFolderTree(userUID);
    printJsonArray(rtnMap);
  }

  public void personnelFolderTree()
  {
    Map fileFolders = null;
    if ((this.folderUid != null) && (this.folderUid.equals(this.sessionUser.getUserUID()))) {
      fileFolders = this.folderService.personnelFolderTree(this.sessionUser.getUserUID(), null);
      if (fileFolders != null)
        printJsonArray(fileFolders.get("children"));
    } else {
      fileFolders = this.folderService.personnelFolderTree(null, this.folderUid);
      printJsonArray(fileFolders == null ? "[]" : fileFolders.get("children"));
    }
  }

  public void getPersonnelFolderTreeRootUid()
  {
    Map fileFolder = this.folderService.personnelFolderTree(this.sessionUser.getUserUID(), null);

    String fuid = fileFolder.get("id").toString();
    FileFolder folder = this.folderService.findById(fuid);
    if (folder.getName() == null) {
      folder.setName("我的文件");
      this.folderService.save(folder);
    }
    printJson("uid", fuid);
  }

  public String preMoveFiles()
  {
    ActionContext.getContext().getContextMap().put("folderTreeType", this.folderTreeType);
    ActionContext.getContext().getContextMap().put("batchUids", this.batchUids);
    return "preMoveFiles";
  }

  public void moveFiles()
  {
    this.fileService.moves(this.batchUids, this.folder);
    this.folder = this.folderService.findById(this.folder.getFolderUid());
    if (this.folderTreeType.equals("personnel"))
      this.folderService.moves(this.batchUids, this.folder);
    else {
      this.folderService.moveFolderAndResource(this.batchUids, this.folder);
    }

    saveSystemLog(OperationType.FC_MODIFY.name(), "移动文件或文件夹", 
      "将标识为" + this.batchUids + "的文件夹或者文件，移动到文件夹:" + this.folder.getFolderUid() + "下");
    printJson("success", "移动位置成功！");
  }

  public void batchDeleteFiles()
  {
    this.fileService.batchDelete(this.batchUids);
    this.folderService.batchDelete(this.batchUids);
    saveSystemLog(OperationType.FC_DELETE.name(), "清空回收站", "");
    printJson("success", "成功清除回收站！");
  }

  public void getResourceUid()
  {
    FileFolder fileFolder = this.folderService.findById(this.folderUid);
    String resourceUid = fileFolder.getResourceUid();
    if ((resourceUid == null) || (resourceUid.equals(""))) {
      this.folderService.createResource(fileFolder);
      resourceUid = fileFolder.getResourceUid();
    }
    printJson("resourceUid", resourceUid);
  }

  public FileFolder getFolder() {
    return this.folder;
  }

  public void setFolder(FileFolder folder) {
    this.folder = folder;
  }
  public String getFolderTreeType() {
    return this.folderTreeType;
  }

  public void setFolderTreeType(String folderTreeType) {
    this.folderTreeType = folderTreeType;
  }

  public String getBatchUids() {
    return this.batchUids;
  }

  public void setBatchUids(String batchUids) {
    this.batchUids = batchUids;
  }

  public String getFolderUid() {
    return this.folderUid;
  }

  public void setFolderUid(String folderUid) {
    this.folderUid = folderUid;
  }
}