package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.filecube.model.FileFavorites;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.model.FileOfficialDocument;
import net.risesoft.soa.filecube.model.FileOpenHistory;
import net.risesoft.soa.filecube.model.FileQueryHistory;
import net.risesoft.soa.filecube.service.FavoritesService;
import net.risesoft.soa.filecube.service.FileService;
import net.risesoft.soa.filecube.service.FolderService;
import net.risesoft.soa.filecube.service.OpenHistoryService;
import net.risesoft.soa.filecube.service.QueryHistoryService;
import net.risesoft.soa.filecube.util.FileSize;
import net.risesoft.soa.filecube.util.FileType;
import net.risesoft.soa.filecube.util.GlobalInfo;
import net.risesoft.soa.filecube.util.OperationType;
import net.risesoft.soa.filecube.util.StringUtils;
import net.risesoft.soa.filecube.util.SystemInfo;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.framework.util.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class FileAction extends BaseAction
{
  private static final Logger log = LoggerFactory.getLogger(FileAction.class);

  @Autowired
  private FolderService folderService;

  @Autowired
  private FileService fileService;

  @Autowired
  private OpenHistoryService openHistoryService;

  @Autowired
  private FavoritesService favoritesService;

  @Autowired
  private QueryHistoryService queryHistoryService;
  private File uploadFile;
  private String uploadFileName;
  private String folderUid;
  private String fileUid;
  private int start;
  private int pageSize;
  private String itemType;
  private String showFile;
  private FileInfo acFileInfo;
  private String batchUids;
  private String viewProject;
  private String searchStr;
  private String sortStr;

  public String preUpload() { 
	  
	  return "preUpload"; }


  public void upload()
  {
    HttpServletRequest request = ServletActionContext.getRequest();

    FileInfo fileInfo = new FileInfo();
    String fileName = this.uploadFileName.substring(0, this.uploadFileName.lastIndexOf("."));
    fileInfo.setName(fileName);
    fileInfo.setItemType(this.itemType);

    String fileType = this.uploadFileName.substring(this.uploadFileName.lastIndexOf(".") + 1);

    fileType = fileType.toLowerCase();
    fileInfo.setExtension(fileType);
    fileInfo.setFileFolder(this.folderService.findById(this.folderUid));
    fileInfo.setDeleted(Boolean.valueOf(false));

    fileInfo.setZipDoc(false);
    fileInfo.setCreatorUid(this.sessionUser.getUserUID());
    fileInfo.setCreatorName(this.sessionUser.getUserName());

    if (this.uploadFile == null) {
      InputStream fileIs = null;
      try {
        fileIs = request.getInputStream();
      } catch (IOException e) {
        e.printStackTrace();
      }

      this.fileService.save(fileInfo, fileIs);
    }
    else {
      this.fileService.save(fileInfo, this.uploadFile);
    }
    this.response.setCharacterEncoding("utf-8");

    saveSystemLog(OperationType.FC_ADD.name(), "上传文件", 
      "上传成功！文件标识:" + fileInfo.getFileUid());
    Map rtnData = new HashMap();
    rtnData.put("id", fileInfo.getFileUid());
    rtnData.put("name", fileInfo.getName());
    rtnData.put("extension", fileInfo.getExtension());
    rtnData.put("size", FileSize.size(fileInfo.getSize()));
    rtnData.put("deleteFun", "deleteFile('" + fileInfo.getFileUid() + "')");
    rtnData.put("success", "true");
    rtnData.put("type", "file");
    rtnData.put("folderUid", fileInfo.getFileFolder().getFolderUid());
    printJson(rtnData);
  }

  public void download()
  {
    try
    {
      FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());

      int curDownLoadTimes = fileInfo.getDownLoadTimes() == null ? 0 : fileInfo.getDownLoadTimes().intValue();
      fileInfo.setDownLoadTimes(Integer.valueOf(curDownLoadTimes + 1));
      this.fileService.update(fileInfo);
      String fileName = fileInfo.getFileUid() + "." + fileInfo.getExtension();

      String uploadDir = GlobalInfo.getInstance().getUploadRoot();
      uploadDir = uploadDir + (uploadDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);

      File f = new File(uploadDir + fileInfo.getDirectory() + fileName);
      if (!f.exists()) {
        this.response.sendError(404, "文件不存在！");
        saveSystemLog(OperationType.FC_DOWNLOAD.name(), "下载文件", 
          "文件未找到！文件标识:" + fileInfo.getFileUid());
        return;
      }
      this.response.setCharacterEncoding("utf-8");
      BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
      byte[] buf = new byte[1024];
      int len = 0;

      fileName = new String(fileInfo.getName().getBytes("gb2312"), "ISO-8859-1");
      fileName = fileName + "." + fileInfo.getExtension();
      this.response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
      OutputStream out = this.response.getOutputStream();
      while ((len = br.read(buf)) > 0)
        out.write(buf, 0, len);
      br.close();
      out.close();
      saveSystemLog(OperationType.FC_DOWNLOAD.name(), "下载文件", 
        "文件标识:" + fileInfo.getFileUid());
    } catch (FileNotFoundException e) {
      log.error("下载文件失败！", e);
    } catch (IOException e) {
      log.error("下载文件失败！", e);
    }
  }

  public String showFile()
  {
    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
    saveSystemLog(OperationType.FC_VIEW.toString(), "视图切换", "选择的视图" + this.showFile);
    return "showFile";
  }
  public String showFile2()
  {
    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
    saveSystemLog(OperationType.FC_VIEW.toString(), "视图切换", "选择的视图" + this.showFile);
    return "showFile2";
  }

  public String preListView() {
    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
    ActionContext.getContext().getContextMap().put("folderUid", this.folderUid);

    List navFolders = this.folderService.findParentsByUid(this.folderUid);
    navFolders.add(this.folderService.findById(this.folderUid));
    List navBar = new ArrayList();
    if (navFolders.size() > 3) {
      FileFolder folder = new FileFolder();
      folder.setName("overThree");
      navBar.add(folder);
      navBar.addAll(navFolders.subList(navFolders.size() - 3, navFolders.size()));
      ActionContext.getContext().getContextMap().put("navigationBar", navBar);
    } else {
      ActionContext.getContext().getContextMap().put("navigationBar", navFolders);
    }

    return "preListView";
  }
  
  public String preListView2() {
	    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
	    ActionContext.getContext().getContextMap().put("folderUid", this.folderUid);

	    List navFolders = this.folderService.findParentsByUid(this.folderUid);
	    navFolders.add(this.folderService.findById(this.folderUid));
	    List navBar = new ArrayList();
	    if (navFolders.size() > 3) {
	      FileFolder folder = new FileFolder();
	      folder.setName("overThree");
	      navBar.add(folder);
	      navBar.addAll(navFolders.subList(navFolders.size() - 3, navFolders.size()));
	      ActionContext.getContext().getContextMap().put("navigationBar", navBar);
	    } else {
	      ActionContext.getContext().getContextMap().put("navigationBar", navFolders);
	    }

	    return "preListView2";
	  }

  public void listView()
  {
    if ((this.searchStr != null) && (!"".equals(this.searchStr))) {
      FileQueryHistory queryHistory = new FileQueryHistory();
      queryHistory.setQueryContent(this.searchStr);
      queryHistory.setUserUid(this.sessionUser.getUserUID());
      queryHistory.setQueryDate(new Date());
      this.queryHistoryService.save(queryHistory);
    }
    List<Map<String,Object>> files = this.folderService.listFiles(this.sessionUser.getUserUID(), 
      this.start, this.pageSize, this.folderUid, this.searchStr, this.sortStr);
    long totalSize = 0L;
    List<Map<String,Object>> rtnList = new ArrayList();
    String uids = "";
    String totalCount = "";
    String name;
    for (Map m : files) {
      totalCount = m.get("totalCount").toString();
      uids = uids + m.get("id") + ",";
      totalSize += Long.parseLong(m.get("longSize").toString());
      name = m.get("name").toString();
      m.put("shortName", StringUtils.getShortStr(name, 14));
      rtnList.add(m);
    }
    List<FileFavorites> favoritesAlls = this.favoritesService.findByUserAndFile(this.sessionUser.getUserUID(), uids + "1");

    for (Map map : rtnList)
    {
      String favoriteUid = "";
      for (FileFavorites fileFavoritesAll : favoritesAlls) {
        String uid = "";
        if (fileFavoritesAll.getFileFolder() != null)
          uid = fileFavoritesAll.getFileFolder().getFolderUid();
        else {
          uid = fileFavoritesAll.getFileInfo().getFileUid();
        }
        if (map.get("id").equals(uid)) {
          favoriteUid = fileFavoritesAll.getFavoritesUid();
          break;
        }
      }
      if (favoriteUid.equals("")) {
        map.put("favorite", "off");
      } else {
        map.put("favorite", "on");
        map.put("favoriteUid", favoriteUid);
      }

    }

    Object map = new HashMap();
    ((Map)map).put("total", totalCount);
    ((Map)map).put("totalSize", FileSize.size(Long.valueOf(totalSize)));
    ((Map)map).put("root", rtnList);

    printJson(map);
  }

  public String preImgView()
  {
    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
    ActionContext.getContext().getContextMap().put("folderUid", this.folderUid);
    ActionContext.getContext().getContextMap().put("start", Integer.valueOf(this.start));
    ActionContext.getContext().getContextMap().put("pageSize", Integer.valueOf(this.pageSize));
    List<Map<String,Object>> files = this.folderService.listFiles(this.sessionUser.getUserUID(), 
      this.start, this.pageSize, this.folderUid, this.searchStr, this.sortStr);
    List<Map<String,Object>> rtnList = new ArrayList();
    String uids = "";
    String totalCount = "";
    long totalSize = 0L;
    String name;
    for (Map m : files) {
      totalCount = m.get("totalCount").toString();
      uids = uids + m.get("id") + ",";
      name = m.get("name").toString();
      totalSize += Long.parseLong(m.get("longSize").toString());
      m.put("shortName", StringUtils.getShortStr(name, 7));
      rtnList.add(m);
    }
    ActionContext.getContext().getContextMap().put("totalCount", totalCount);
    ActionContext.getContext().getContextMap().put("totalSize", FileSize.size(Long.valueOf(totalSize)));
    List<FileFavorites> favoritesAlls = this.favoritesService.findByUserAndFile(this.sessionUser.getUserUID(), uids + "1");
    for (Map map : rtnList) {
      String favoriteUid = "";
      for (FileFavorites fileFavoritesAll : favoritesAlls) {
        String uid = "";
        if (fileFavoritesAll.getFileFolder() != null)
          uid = fileFavoritesAll.getFileFolder().getFolderUid();
        else {
          uid = fileFavoritesAll.getFileInfo().getFileUid();
        }
        if (map.get("id").equals(uid)) {
          favoriteUid = fileFavoritesAll.getFavoritesUid();
          break;
        }
      }
      if (favoriteUid.equals("")) {
        map.put("favorite", "off");
      } else {
        map.put("favorite", "on");
        map.put("favoriteUid", favoriteUid);
      }
    }

    ActionContext.getContext().getContextMap().put("files", rtnList);

    List navFolders = this.folderService.findParentsByUid(this.folderUid);
    navFolders.add(this.folderService.findById(this.folderUid));
    List navBar = new ArrayList();
    if (navFolders.size() > 3) {
      FileFolder folder = new FileFolder();
      folder.setName("overThree");
      navBar.add(folder);
      navBar.addAll(navFolders.subList(navFolders.size() - 3, navFolders.size()));
      ActionContext.getContext().getContextMap().put("navigationBar", navBar);
    } else {
      ActionContext.getContext().getContextMap().put("navigationBar", navFolders);
    }

    return "preImgView";
  }

  public void smallImg()
  {
    FileInfo fileInfo = this.fileService.findById(this.fileUid);
    if (fileInfo.getSmallIcon() == null) return;
    byte[] bts = Base64.decode(fileInfo.getSmallIcon());
    ByteArrayInputStream bais = new ByteArrayInputStream(bts);
    try {
      this.response.reset();
      this.response.setHeader("Content-Disposition", "filename=" + 
        fileInfo.getName() + "." + fileInfo.getExtension());
      OutputStream os = this.response.getOutputStream();
      IOUtils.copy(bais, os);
      bais.close();
      os.flush();
    } catch (IOException e) {
      log.error("文件缩略图读取错误！", e);
    }
  }

  public String preEdit() { FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("fileInfo", fileInfo);
    return "preEdit";
  }

  public void edit()
  {
    FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());
    fileInfo.setName(this.acFileInfo.getName());
    fileInfo.setDescription(this.acFileInfo.getDescription());
    fileInfo.setDepartmentUid(this.acFileInfo.getDepartmentUid());
    fileInfo.setDepartmentName(this.acFileInfo.getDepartmentName());
    fileInfo.setModifieddate(new Date());
    fileInfo.setYear(this.acFileInfo.getYear());
    fileInfo.setKind(this.acFileInfo.getKind());

    FileOfficialDocument fod = fileInfo.getFod();
    if (fod == null);
    fod = new FileOfficialDocument();
    fod.setYear(this.acFileInfo.getYear());
    fod.setKind(this.acFileInfo.getKind());
    fod.setDepartmentUid(this.acFileInfo.getDepartmentUid());
    fod.setDepartmentName(this.acFileInfo.getDepartmentName());
    fod.setSecretLevel(this.acFileInfo.getFod().getSecretLevel());
    fod.setEnmergency(this.acFileInfo.getFod().getEnmergency());
    fod.setFileInfo(fileInfo);
    fileInfo.setFod(fod);

    this.fileService.update(fileInfo);
    saveSystemLog(OperationType.FC_MODIFY.name(), "修改文件", "文件唯一标识!" + fileInfo.getFileUid());
    printJson("success", "修改成功！");
  }

  public String preViewFile()
  {
    ActionContext.getContext().getContextMap().put("showFile", this.showFile);
    ActionContext.getContext().getContextMap().put("viewProject", this.viewProject);
    FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());

    boolean canOpen = FileType.acOpenOfficeFormatFile(fileInfo.getExtension());
    if (!canOpen) {
      download();
      return "success";
    }

    FileOpenHistory openHistory = new FileOpenHistory();
    openHistory.setFileInfo(fileInfo);
    openHistory.setOpenDate(new Date());
    openHistory.setUserUid(this.sessionUser.getUserUID());
    this.openHistoryService.save(openHistory);

    int openTimes = fileInfo.getOpenTimes().intValue();
    fileInfo.setOpenTimes(Integer.valueOf(openTimes + 1));
    this.fileService.update(fileInfo);

    ActionContext.getContext().getContextMap().put("fileInfo", fileInfo);
    if (this.viewProject != null) {
      return "preViewFile";
    }

    return "preViewFile";
  }

  public String preMetadata()
  {
    FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());
    ActionContext.getContext().getContextMap().put("fileInfo", fileInfo);
    return "preMetadata";
  }

  public void convertFile()
  {
    this.fileService.convertFile(this.fileUid);
    printJson("success", "success");
  }

  public void viewFile()
  {
    log.debug("获取文件流,文件唯一标识" + this.fileUid);
    FileInfo fileInfo = this.fileService.findById(this.fileUid);
    try {
      String uploadDir = GlobalInfo.getInstance().getUploadRoot();
      uploadDir = uploadDir + (uploadDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);

      String absolutePath = uploadDir + fileInfo.getDirectory() + fileInfo.getFileUid() + ".swf";
      if (fileInfo.isZipDoc())
      {
        absolutePath = new File(new StringBuilder(String.valueOf(uploadDir)).append(fileInfo.getDirectory()).toString()).getParent() + 
          SystemInfo.SEPARATOR_FILE + fileInfo.getFileUid() + ".swf";
      }
      FileInputStream fis = new FileInputStream(new File(absolutePath));
      OutputStream os = this.response.getOutputStream();
      IOUtils.copy(fis, os);
      os.flush();
    } catch (FileNotFoundException e) {
      log.error("文件流获取失败!", e);
    } catch (IOException e) {
      log.error("文件流获取失败!", e);
    }
  }

  public void viewImgFile()
  {
    log.debug("查看图片文件！");
    FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());

    FileOpenHistory openHistory = new FileOpenHistory();
    openHistory.setFileInfo(fileInfo);
    openHistory.setOpenDate(new Date());
    openHistory.setUserUid(this.sessionUser.getUserUID());
    this.openHistoryService.save(openHistory);

    String uploadDir = GlobalInfo.getInstance().getUploadRoot();
    uploadDir = uploadDir + (uploadDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);

    String absolutePath = uploadDir + fileInfo.getDirectory() + fileInfo.getFileUid() + "." + fileInfo.getExtension();
    HttpServletResponse response = ServletActionContext.getResponse();
    try {
      File file = new File(absolutePath);
      response.setCharacterEncoding("utf-8");
      if (!file.exists()) {
        response.sendError(404, "找不到指定的文件！");
        return;
      }
      FileInputStream fis = new FileInputStream(file);
      response.reset();
      response.setHeader("Content-Disposition", "filename=" + 
        fileInfo.getName() + "." + fileInfo.getExtension());
      OutputStream os = response.getOutputStream();
      IOUtils.copy(fis, os);
      os.flush();
      saveSystemLog(OperationType.FC_VIEW.toString(), "查看图片文件", "文件标识：" + this.acFileInfo.getFileUid());
    } catch (IOException e) {
      log.error("图片获取失败！", e);
    }
  }

  public void logicDelete()
  {
    this.fileService.logicDelete(this.acFileInfo.getFileUid());
    saveSystemLog(OperationType.FC_DELETE.name(), "放入回收站！", "文件唯一标识:" + this.acFileInfo.getFileUid());
    printJson("success", "删除成功！");
  }

  public void recoverFile()
  {
    FileInfo f = this.fileService.findById(this.acFileInfo.getFileUid());
    f.setDeleted(Boolean.valueOf(false));
    this.fileService.save(f);
    printJson("success", "恢复成功！");
  }

  public void batchLogicDeleteFiles()
  {
    this.fileService.batchLogicDelete(this.batchUids);
    this.folderService.batchLogicDelete(this.batchUids);
    saveSystemLog(OperationType.FC_DELETE.name(), "批量放入回收站", "");
    printJson("success", "成功放入回收站！");
  }

  public void delete()
  {
    this.fileService.delete(this.acFileInfo.getFileUid());
    saveSystemLog(OperationType.FC_DELETE.name(), "删除文件", "文件标识:" + this.acFileInfo.getFileUid());
    printJson("success", "删除成功！");
  }

  public String preRecycleBin()
  {
    List l = this.fileService.findDeleteFileByUserUid(this.sessionUser.getUserUID());
    ActionContext.getContext().getContextMap().put("deleteFiles", l);
    return "preRecycleBin";
  }

  public void getResourceUid()
  {
    FileInfo fileInfo = this.fileService.findById(this.acFileInfo.getFileUid());
    String resourceUid = fileInfo.getResourceUid();
    if ((resourceUid == null) || (resourceUid.equals(""))) {
      this.fileService.createResource(fileInfo);
      resourceUid = fileInfo.getResourceUid();
    }
    printJson("resourceUid", resourceUid);
  }

  public void getFileItemType()
  {
    FileInfo file = this.fileService.findById(this.fileUid);
    if (file == null)
      log.error("获取文件的数据库记录失败！");
    else
      printJson("itemType", file.getItemType());
  }

  public String getFolderUid()
  {
    return this.folderUid;
  }

  public void setFolderUid(String folderUid) {
    this.folderUid = folderUid;
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
  public int getStart() {
    return this.start;
  }
  public void setStart(int start) {
    this.start = start;
  }
  public int getPageSize() {
    return this.pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public String getShowFile() {
    return this.showFile;
  }
  public void setShowFile(String showFile) {
    this.showFile = showFile;
  }
  public FileInfo getAcFileInfo() {
    return this.acFileInfo;
  }
  public void setAcFileInfo(FileInfo acFileInfo) {
    this.acFileInfo = acFileInfo;
  }
  public String getBatchUids() {
    return this.batchUids;
  }
  public void setBatchUids(String batchUids) {
    this.batchUids = batchUids;
  }
  public String getViewProject() {
    return this.viewProject;
  }
  public void setViewProject(String viewProject) {
    this.viewProject = viewProject;
  }
  public String getSearchStr() {
    return this.searchStr;
  }
  public void setSearchStr(String searchStr) {
    this.searchStr = searchStr;
  }
  public String getSortStr() {
    return this.sortStr;
  }
  public void setSortStr(String sortStr) {
    this.sortStr = sortStr;
  }
  public String getItemType() {
    return this.itemType;
  }
  public void setItemType(String itemType) {
    this.itemType = itemType;
  }
  public String getFileUid() {
    return this.fileUid;
  }
  public void setFileUid(String fileUid) {
    this.fileUid = fileUid;
  }
}