package net.risesoft.soa.filecube.service.impl;

import egov.appservice.ac.model.Operation;
import egov.appservice.ac.model.Resource;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.risesoft.soa.filecube.model.FileFolder;
import net.risesoft.soa.filecube.model.FileInfo;
import net.risesoft.soa.filecube.service.FileService;
import net.risesoft.soa.filecube.service.FolderService;
import net.risesoft.soa.filecube.service.PermissionService;
import net.risesoft.soa.filecube.service.ShareService;
import net.risesoft.soa.filecube.service.util.AcManager;
import net.risesoft.soa.filecube.util.FrameServerJCEProvicer;
import net.risesoft.soa.filecube.util.GlobalInfo;
import net.risesoft.soa.filecube.util.OperationType;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("permissionService")
public class PermissionServiceImpl
  implements PermissionService
{
  private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

  @Autowired
  private AcManager acManager;

  @Autowired
  private FolderService folderService;

  @Autowired
  private FileService fileService;

  @Autowired
  private ShareService shareService;
  private static int checkLicenseCount;
  private static List<String> shareFolderUids = new ArrayList();

  public List<Map<String, Object>> getOperations(String userUID, String resourceUID)
  {
    try
    {
      Operation[] operation = this.acManager.getOperations(userUID, resourceUID, null);
      List ops = new ArrayList();
      for (Operation op : operation) {
        Map map = new HashMap();
        map.put("id", op.getId());
        map.put("name", op.getName());
        ops.add(map);
      }
      return ops;
    } catch (Exception e) {
      log.error("获取操作类型失败！操作用户唯一标识:" + userUID, e);
    }return null;
  }

  public Boolean hasPermission(String userUID, String resouceUID, String operationKey)
  {
    try
    {
      return this.acManager.hasPermission(userUID, resouceUID, operationKey);
    } catch (Exception e) {
      log.error("判断权限失败！操作用户唯一标识:" + userUID + ";操作类型:" + operationKey, 
        e);
    }return null;
  }

  public List<Map<String, Object>> getSubResourcesTree(String actorUID, String operationKey, String folderUid)
  {
    List rtnMaps = new ArrayList();
    String resourceUID = this.folderService.findById(folderUid).getResourceUid();
    try {
      List<Resource> resources = this.acManager.getSubResources(actorUID, operationKey, resourceUID, null);
      for (Resource resource : resources) {
        String type = resource.getType();

        if (!type.equals("文件"))
        {
          Map rtnMap = new HashMap();
          rtnMap.put("id", getFolder(resource, folderUid).getFolderUid());
          rtnMap.put("text", resource.getName());
          rtnMap.put("leaf", Boolean.valueOf(false));
          rtnMaps.add(rtnMap);
        }
      }
    } catch (Exception e) { log.error("获取子资源目录失败", e); }

    return rtnMaps;
  }

  private boolean checkLicense(int total)
  {
    BundleContext context = Platform.getBundle("system.bundle").getBundleContext();
    ServiceReference sr = context.getServiceReference("frameserver.license.CheckLicense");
    boolean pass = false;
    if (sr != null) {
      Object check = context.getService(sr);
      try {
        Method getLimitMethod = check.getClass().getMethod("getLimit", new Class[] { String.class });
        int limit = ((Integer)getLimitMethod.invoke(check, new Object[] { "ORG" })).intValue();
        if ((limit != 0) && (limit < total)) {
          System.out.println("[FATAL] CHECK LICENSE ERROR:ORG中的人员数超过license中的许可。total=" + total);
          return false;
        }
        Method checkMethod = check.getClass().getMethod("check", new Class[] { String.class });
        pass = (((Boolean)checkMethod.invoke(check, new Object[] { "ORG" })).booleanValue()) && ((checkLicenseCount++ % 55 != 0) || (checkLicenseJar(sr))); } catch (Throwable localThrowable) {
      }
    }
    return pass;
  }

  private boolean checkLicenseJar(ServiceReference sr) {
    Bundle serviceBundle = sr.getBundle();
    if (serviceBundle.getBundleId() == 0L) {
      Bundle[] fragments = Platform.getFragments(serviceBundle);
      for (Bundle bundle : fragments) {
        String exportPackages = (String)bundle.getHeaders().get("Export-Package");
        if ((exportPackages != null) && (exportPackages.contains("frameserver.license"))) {
          return FrameServerJCEProvicer.isBundleJarSigned(bundle);
        }
      }
    }
    return false;
  }

  public List<Map<String, Object>> getResourceRoots(String userUid)
  {
    if (!checkLicense(0))
      return Collections.EMPTY_LIST;
    try
    {
      List<Resource> resources = this.acManager.getSubResources(userUid, OperationType.FC_VIEW.toString(), 
        GlobalInfo.getInstance().getResourceRoot(), null);
      List rtnMaps = new ArrayList();
      for (Resource resource : resources) {
        Map rtnMap = new HashMap();
        FileFolder fileFolder = getFolder(resource, "");
        rtnMap.put("id", fileFolder.getFolderUid());
        rtnMap.put("name", fileFolder.getName());
        rtnMaps.add(rtnMap);
      }
      return rtnMaps;
    } catch (Exception e) {
      log.error("获取根节点失败！", e);
    }
    return null;
  }

  private FileFolder getFolder(Resource resource, String parentFolderUid)
  {
    String resourceUid = resource.getUID();
    FileFolder fileFolder = this.folderService.findByResourceUid(resourceUid);
    if (fileFolder == null) {
      fileFolder = new FileFolder();
      fileFolder.setFolderUid(UUID.randomUUID().toString());
      try {
        fileFolder.setName(this.acManager.getFolderResource(resourceUid).getName());
      } catch (Exception e) {
        log.error("获取资源失败！", e);
      }
      fileFolder.setCreateDate(new Date());
      fileFolder.setDeleted(Boolean.valueOf(false));
      fileFolder.setResourceUid(resourceUid);
      fileFolder.setParentUid(parentFolderUid);
      this.folderService.save(fileFolder);
    }
    return fileFolder;
  }

  public List<Map<String, Object>> findFolder(String userUID, String folderName, String parnetUid) {
    FileFolder parentFolder = this.folderService.findById(parnetUid);
    List<FileFolder> folders = this.folderService.findByName(folderName, parentFolder.getName());

    List levels = new ArrayList();

    List acFolders = new ArrayList();

    for (FileFolder fileFolder : folders) {
      boolean acView = hasPermission(userUID, fileFolder.getResourceUid(), 
        OperationType.FC_VIEW.toString()).booleanValue();

      if (acView) {
        acFolders.add(fileFolder);
        levelToPFolder(1, fileFolder.getFolderLevel() + fileFolder.getName() + "\\", levels);
      }
    }
    List rtnData = new ArrayList();
    Iterator localIterator2;
    for (boolean acView = levels.iterator(); acView.hasNext(); 
      localIterator2.hasNext())
    {
      Map levelMap = (Map)acView.next();
      String level = (String)levelMap.get("level");
      if (level.split("\\\\").length == 2) {
        rtnData.add(levelMap);
      }
      localIterator2 = rtnData.iterator(); continue; Map map = (Map)localIterator2.next();
      levelTree(level, map, acFolders);
    }

    return rtnData;
  }
  private void levelTree(String level, Map<String, Object> pmap, List<FileFolder> fileFolders) {
    String levelP = (String)pmap.get("level");
    List folders = new ArrayList();
    for (FileFolder fileFolder : fileFolders) {
      if (fileFolder.getFolderLevel().equals(level)) {
        folders.add(fileFolder);
      }
    }

    pmap.put("leaf", Boolean.valueOf(false));
    List childP = (List)pmap.get("children");
    if (childP == null) {
      childP = createSearchFolderTree(folders);
      pmap.put("children", childP);
    }
    if ((!level.contains(levelP)) || (level.substring(levelP.length()).split("\\\\").length == 1) || (level.contains(levelP)))
      for (Map map : childP)
        if (level.contains(map.get("level")))
          levelTree(level, map, fileFolders);
  }

  private List<Map<String, Object>> createSearchFolderTree(List<FileFolder> folders)
  {
    List childs = new ArrayList();
    for (FileFolder fileFolder : folders) {
      Map map = new HashMap();
      map.put("id", fileFolder.getFolderUid());
      map.put("text", fileFolder.getName());
      map.put("leaf", Boolean.valueOf(true));
      map.put("level", fileFolder.getFolderLevel() + fileFolder.getName() + "\\");
      childs.add(map);
    }
    return childs;
  }

  private void levelToPFolder(int levelCount, String level, List<Map<String, Object>> ls)
  {
    String[] levels = level.substring(0, level.lastIndexOf("\\")).split("\\\\");
    String userLevel = "";
    if (levelCount >= levels.length) return;
    for (int i = 0; i < levelCount; i++) {
      userLevel = userLevel + levels[i] + "\\";
    }
    String thisFolderName = levels[levelCount];
    FileFolder folder = this.folderService.findByNameAndPath(thisFolderName, userLevel);

    if (folder == null) {
      return;
    }
    Map m = new HashMap();
    m.put("id", folder.getFolderUid());
    m.put("text", thisFolderName);
    m.put("level", userLevel + thisFolderName + "\\");
    m.put("leaf", Boolean.valueOf(true));

    if (!ls.contains(m))
      ls.add(m);
    levelToPFolder(++levelCount, level, ls);
  }

  public boolean getPermissionByFileUid(String userUID, String fileUid, String operationKey)
  {
    FileInfo fileInfo = this.fileService.findById(fileUid);
    if ((operationKey == null) || ("".equals(operationKey))) {
      operationKey = OperationType.FC_VIEW.toString();
    }
    String resourceUid = fileInfo.getResourceUid();
    if (resourceUid != null) {
      return hasPermission(userUID, resourceUid, operationKey).booleanValue();
    }
    return getPermissionByFolderUid(userUID, fileInfo.getFileFolder().getFolderUid(), operationKey);
  }

  public boolean getPermissionByFolderUid(String userUID, String folderUid, String operationKey)
  {
	  System.out.println(userUID+" "+folderUid+" "+operationKey);
    FileFolder fileFolder = this.folderService.findById(folderUid);
    String resourceUid = fileFolder.getResourceUid();
    try {
//      Resource resource = this.acManager.getFolderResource(fileFolder.getResourceUid());
//      if ((resourceUid == null) || (resourceUid.equals("")) || (resource == null)) {
//        fileFolder.setResourceUid(null);
//        this.folderService.createResource(fileFolder);
//        resourceUid = fileFolder.getResourceUid();
//      }

    	 if (operationKey.equals(OperationType.FC_VIEW.toString())) {
    	        boolean shared = this.shareService.shareToMe(userUID, folderUid);
    	        if (!shared) {
    	          shareFolderUids.add(folderUid);
    	          return true;
    	        }
    	        if (!shareFolderUids.isEmpty()) {
    	          for (String shareFolderUid : shareFolderUids) {
    	            FileFolder folder = this.folderService.findById(shareFolderUid);
    	            String level = folder.getFolderLevel() + folder.getName();
    	            String curLevel = fileFolder.getFolderLevel();
    	            if (curLevel == null)
    	              return hasPermission(userUID, resourceUid, operationKey).booleanValue();
    	            if (curLevel.startsWith(level)) {
    	              shareFolderUids.add(folderUid);
    	              return true;
    	            }
    	          }
    	        }
    	      }

      return hasPermission(userUID, resourceUid, operationKey).booleanValue();
    } catch (Exception e) {
      log.error("判断文件夹权限失败！", e);
    }return false;
  }

  public List<Resource> getSubResources(String actorUID, String operationKey, String resourceUID, Map<String, String> properties)
  {
    try
    {
      return this.acManager.getSubResources(actorUID, operationKey, resourceUID, properties);
    } catch (Exception e) {
      log.error("获取子资源失败！", e);
    }
    return null;
  }

  public void grantPermission(String[] userUids, String[] resouceUIDs, String[] operationKeys)
  {
    this.acManager.grantPermission(userUids, resouceUIDs, operationKeys);
  }

  public void grantPermission(String userUid, String resouceUID, String operationKey)
  {
    this.acManager.grantPermission(userUid, resouceUID, operationKey);
  }

  public void grantPermission(String userUid, String resouceUID, String[] operationKeys)
  {
    this.acManager.grantPermission(userUid, resouceUID, operationKeys);
  }
}