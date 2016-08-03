/*     */ package net.risesoft.soa.filecube.service.impl;
/*     */ 
/*     */ import egov.appservice.ac.model.Resource;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import javax.persistence.Query;
/*     */ import net.risesoft.soa.filecube.dao.FolderDao;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import net.risesoft.soa.filecube.service.FolderService;
/*     */ import net.risesoft.soa.filecube.service.PermissionService;
/*     */ import net.risesoft.soa.filecube.service.util.AcManager;
/*     */ import net.risesoft.soa.filecube.util.DateUtil;
/*     */ import net.risesoft.soa.filecube.util.FileSize;
/*     */ import net.risesoft.soa.filecube.util.GlobalInfo;
/*     */ import net.risesoft.soa.filecube.util.OperationType;
/*     */ import net.risesoft.soa.filecube.util.SystemInfo;
/*     */ import net.risesoft.soa.org.manager.DepartmentManager;
/*     */ import net.risesoft.soa.org.manager.PersonManager;
/*     */ import net.risesoft.soa.org.model.OrgUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional
/*     */ @Service("folderService")
/*     */ public class FolderServiceImpl
/*     */   implements FolderService
/*     */ {
/*  40 */   private static final Logger log = LoggerFactory.getLogger(FolderServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   public FolderDao folderDao;
/*     */ 
/*     */   @Autowired
/*     */   private AcManager acManager;
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*     */   @Autowired
/*     */   private DepartmentManager departmentManager;
/*     */ 
/*     */   @Autowired
/*     */   private PersonManager personManager;
/*     */ 
/*     */   @Autowired
/*     */   private PermissionService permissionService;
/*     */ 
/*  57 */   public List<FileFolder> findByParentUid(String parentUid) { return this.folderDao.findByParentUid(parentUid, false); }
/*     */ 
/*     */ 
/*     */   public FileFolder save(FileFolder folder)
/*     */   {
/*  64 */     List parents = new ArrayList();
/*  65 */     findParents(parents, folder.getParentUid());
/*  66 */     Collections.reverse(parents);
/*  67 */     StringBuilder sb = new StringBuilder();
/*  68 */     for (FileFolder fileFolder : parents) {
/*  69 */       sb.append(fileFolder.getName() + SystemInfo.SEPARATOR_FILE);
/*     */     }
/*  71 */     if (!"".equals(folder.getParentUid())) {
/*  72 */       sb.append(findById(folder.getParentUid()).getName() + SystemInfo.SEPARATOR_FILE);
/*     */     }
/*  74 */     folder.setFolderLevel(sb.toString());
/*  75 */     FileFolder f = this.folderDao.findByNameAndPath(folder.getName(), folder.getFolderLevel());
/*  76 */     if (f != null) {
/*  77 */       folder.setFolderUid(f.getFolderUid());
/*  78 */       folder.setDeleted(f.getDeleted());
/*  79 */       folder.setParentUid(f.getParentUid());
/*  80 */       folder.setResourceUid(f.getResourceUid());
/*  81 */       return folder;
/*     */     }
/*  83 */     return (FileFolder)this.folderDao.save(folder);
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> listFiles(String userUid, int start, int rows, String folderUid, String searchName, String sort)
/*     */   {
/*  89 */     EntityManager em = this.emf.createEntityManager();
/*  90 */     FileFolder folder = findById(folderUid);
/*     */ 
/*  92 */     List subResources = new ArrayList();
/*  93 */     if ((folder.getResourceUid() != null) && (!"".equals(folder.getResourceUid()))) {
/*  94 */       subResources = this.permissionService.getSubResources(userUid, 
/*  95 */         OperationType.FC_VIEW.toString(), folder.getResourceUid(), null);
/*     */     }
/*  97 */     List resourceUids = new ArrayList();
/*     */ 
/*  99 */     for (Resource resource : subResources) {
/* 100 */       resourceUids.add(resource.getUID());
/*     */     }
/* 102 */     StringBuilder sb = new StringBuilder();
/* 103 */     sb.append(" select ff.folderUid,ff.name,0 fileSize,'folder' type,");
/* 104 */     sb.append(" 'folder' extension, ff.createDate,ff.modifiedDate,'' extendFolderUids");
/* 105 */     sb.append(" from fc_Folder ff ");
/* 106 */     sb.append(" where ff.parentUid = ?2 and ff.deleted = ?1 and ff.name like ?3 ");
/* 107 */     if ((folder.getItemType() != null) && (!folder.getItemType().equals("personnel")) && (!resourceUids.isEmpty())) {
/* 108 */       sb.append(" and ff.resourceUid in (?4) ");
/*     */     }
/* 110 */     sb.append(" union ");
/* 111 */     sb.append(" select f.fileuid,f.name,f.fileSize,f.type,f.extension,f.createdate,f.modifieddate,f.extendFolderUids ");
/* 112 */     sb.append(" from fc_fileinfo f where (f.folderUid = ?2 or f.extendFolderUids like ?5) and f.deleted = ?1  and f.name like ?3");
/* 113 */     if ((sort == null) || ("".equals(sort)))
/* 114 */       sb.append(" order by createdate desc ");
/*     */     else {
/* 116 */       sb.append(" order by createdate " + sort);
/*     */     }
/* 118 */     Query query = em.createNativeQuery(sb.toString());
/* 119 */     if ((folder.getItemType() != null) && (!folder.getItemType().equals("personnel")) && (!resourceUids.isEmpty())) {
/* 120 */       query.setParameter(4, resourceUids);
/*     */     }
/* 122 */     query.setParameter(1, Boolean.valueOf(false));
/* 123 */     query.setParameter(2, folderUid);
/* 124 */     query.setParameter(5, "%" + folderUid + "%");
/* 125 */     if ((searchName == null) || ("".equals(searchName)))
/* 126 */       query.setParameter(3, "%%");
/*     */     else {
/* 128 */       query.setParameter(3, "%" + searchName + "%");
/*     */     }
/* 130 */     int totalCount = query.getResultList().size();
/* 131 */     query.setFirstResult(start);
/* 132 */     query.setMaxResults(rows);
/* 133 */     List result = query.getResultList();
/* 134 */     List rtnData = new ArrayList();
/* 135 */     for (Iterator localIterator2 = result.iterator(); localIterator2.hasNext(); ) { Object object = localIterator2.next();
/* 136 */       Object[] objs = (Object[])object;
/* 137 */       Map map = new HashMap();
/* 138 */       map.put("id", objs[0]);
/* 139 */       map.put("name", objs[1]);
/*     */       try {
/* 141 */         map.put("size", FileSize.size(Long.valueOf(Long.parseLong(objs[2]))));
/* 142 */         map.put("longSize", objs[2]);
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 144 */         map.put("size", Integer.valueOf(0));
/* 145 */         map.put("longSize", Integer.valueOf(0));
/*     */       }
/* 147 */       map.put("type", objs[3]);
/* 148 */       map.put("extension", objs[4]);
/* 149 */       map.put("createdate", DateUtil.getDate("yyyy-MM-dd", (Date)objs[5]));
/* 150 */       map.put("modifieddate", DateUtil.getDate("yyyy-MM-dd", (Date)objs[6]));
/* 151 */       map.put("totalCount", Integer.valueOf(totalCount));
/* 152 */       map.put("extendFolderUids", objs[7]);
/*     */ 
/* 154 */       rtnData.add(map);
/*     */     }
/* 156 */     em.close();
/* 157 */     return rtnData;
/*     */   }
/*     */ 
/*     */   public FileFolder findById(String id)
/*     */   {
/* 165 */     return (FileFolder)this.folderDao.findOne(id);
/*     */   }
/*     */ 
/*     */   public void logicDelete(String id)
/*     */   {
/* 173 */     FileFolder folder = findById(id);
/* 174 */     folder.setDeleted(Boolean.valueOf(true));
/* 175 */     save(folder);
/*     */     try {
/* 177 */       if (folder.getResourceUid() != null)
/* 178 */         this.acManager.deleteFolderResource(folder.getResourceUid());
/*     */     }
/*     */     catch (Exception e) {
/* 181 */       log.error("删除文件夹的资源失败！", e);
/*     */     }
/*     */   }
/*     */ 
/* 185 */   public void delete(String id) { FileFolder fileFolder = (FileFolder)this.folderDao.findOne(id);
/*     */     try {
/* 187 */       if (fileFolder.getResourceUid() != null)
/* 188 */         this.acManager.deleteFolderResource(fileFolder.getResourceUid());
/*     */     }
/*     */     catch (Exception e) {
/* 191 */       log.error("删除文件夹的资源失败！", e);
/*     */     }
/* 193 */     this.folderDao.delete(id);
/*     */   }
/*     */ 
/*     */   private FileFolder createRootByResourceUid(String resourceUid)
/*     */   {
/* 203 */     FileFolder fileFolder = new FileFolder();
/*     */     try {
/* 205 */       String folderName = this.acManager.getFolderResource(resourceUid).getName();
/* 206 */       fileFolder.setName(folderName);
/* 207 */       fileFolder.setFolderLevel(folderName + SystemInfo.SEPARATOR_FILE);
/*     */     } catch (Exception e) {
/* 209 */       log.error("创建资源失败！", e);
/*     */     }
/* 211 */     fileFolder.setFolderUid(UUID.randomUUID());
/* 212 */     fileFolder.setCreateDate(new Date());
/* 213 */     fileFolder.setDeleted(Boolean.valueOf(false));
/* 214 */     fileFolder.setResourceUid(resourceUid);
/* 215 */     return (FileFolder)this.folderDao.save(fileFolder);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> globalFolderTree()
/*     */   {
/* 225 */     Map map = new HashMap();
/* 226 */     String globalResourceUid = GlobalInfo.getInstance().getGlobalResourceUid();
/*     */ 
/* 228 */     FileFolder fileFolder = this.folderDao.findByResourceUid(globalResourceUid);
/* 229 */     if (fileFolder == null) {
/* 230 */       fileFolder = createRootByResourceUid(globalResourceUid);
/*     */     }
/* 232 */     folders(map, fileFolder.getFolderUid());
/* 233 */     return map;
/*     */   }
/*     */ 
/*     */   public FileFolder getDepartMentFolderRoot(String departMentName)
/*     */   {
/* 242 */     String departMentResourceRootUid = GlobalInfo.getInstance().getDepartMentResourceUid();
/*     */ 
/* 244 */     FileFolder departMentResourceRoot = this.folderDao.findByResourceUid(departMentResourceRootUid);
/* 245 */     if (departMentResourceRoot == null) {
/* 246 */       departMentResourceRoot = createRootByResourceUid(departMentResourceRootUid);
/*     */     }
/* 248 */     String rootLevel = departMentResourceRoot.getFolderLevel();
/* 249 */     rootLevel = rootLevel == null ? "" : rootLevel;
/*     */ 
/* 251 */     String curPath = rootLevel + departMentResourceRoot.getName() + SystemInfo.SEPARATOR_FILE;
/* 252 */     FileFolder curDepartMentResourceRoot = findByNameAndPath(departMentName, curPath);
/* 253 */     if (curDepartMentResourceRoot == null) {
/* 254 */       curDepartMentResourceRoot = new FileFolder();
/* 255 */       curDepartMentResourceRoot.setFolderUid(UUID.randomUUID());
/* 256 */       curDepartMentResourceRoot.setFolderLevel(curPath);
/* 257 */       curDepartMentResourceRoot.setName(departMentName);
/* 258 */       curDepartMentResourceRoot.setParentUid(departMentResourceRoot.getFolderUid());
/* 259 */       curDepartMentResourceRoot.setCreateDate(new Date());
/* 260 */       curDepartMentResourceRoot.setDeleted(Boolean.valueOf(false));
/* 261 */       this.folderDao.save(curDepartMentResourceRoot);
/*     */       try {
/* 263 */         Resource resource = this.acManager.createResource(departMentName, "部门文件根目录", 
/* 264 */           departMentResourceRootUid, "文件夹");
/* 265 */         curDepartMentResourceRoot.setResourceUid(resource.getUID());
/* 266 */         this.folderDao.save(curDepartMentResourceRoot);
/*     */       } catch (Exception e) {
/* 268 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 271 */     return curDepartMentResourceRoot;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> departMentFolderTree(String userUID)
/*     */   {
/* 280 */     Map map = new HashMap();
/* 281 */     OrgUnit orgUnit = this.personManager.getParent(userUID);
/* 282 */     String departMentUid = orgUnit.getUID();
/* 283 */     FileFolder curDepartMentResourceRoot = getDepartMentFolderRoot(departMentUid);
/* 284 */     folders(map, curDepartMentResourceRoot.getFolderUid());
/* 285 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> allDepartMentFolderTree()
/*     */   {
/* 293 */     Map map = new HashMap();
/* 294 */     folders(map, findByResourceUid(GlobalInfo.getInstance().getDepartMentResourceUid()).getFolderUid());
/* 295 */     return map;
/*     */   }
/*     */ 
/*     */   public Map<String, Object> personnelFolderTree(String userUID, String folderUid)
/*     */   {
/* 305 */     FileFolder userFolderRoot = null;
/* 306 */     if (userUID == null) {
/* 307 */       userFolderRoot = findById(folderUid);
/*     */     }
/* 309 */     else if (findByParentUid(userUID).isEmpty()) {
/* 310 */       userFolderRoot = new FileFolder();
/* 311 */       userFolderRoot.setFolderUid(UUID.randomUUID());
/* 312 */       userFolderRoot.setParentUid(userUID);
/* 313 */       userFolderRoot.setName("我的文件");
/* 314 */       userFolderRoot.setCreateDate(new Date());
/* 315 */       userFolderRoot.setDeleted(Boolean.valueOf(false));
/* 316 */       userFolderRoot.setCreatorUid(userUID);
/* 317 */       this.folderDao.save(userFolderRoot);
/*     */     } else {
/* 319 */       userFolderRoot = (FileFolder)findByParentUid(userUID).get(0);
/*     */     }
/*     */ 
/* 323 */     List fileFolders = findByParentUid(userFolderRoot.getFolderUid());
/*     */ 
/* 325 */     Map rtnMap = new HashMap();
/* 326 */     List rtnList = new ArrayList();
/* 327 */     for (FileFolder fileFolder : fileFolders) {
/* 328 */       Map map = new HashMap();
/* 329 */       map.put("id", fileFolder.getFolderUid());
/* 330 */       map.put("text", fileFolder.getName());
/* 331 */       map.put("leaf", Boolean.valueOf(false));
/* 332 */       rtnList.add(map);
/*     */     }
/* 334 */     rtnMap.put("id", userFolderRoot.getFolderUid());
/* 335 */     rtnMap.put("children", rtnList);
/* 336 */     return rtnMap;
/*     */   }
/*     */ 
/*     */   private Map<String, Object> folders(Map<String, Object> map, String folderUid)
/*     */   {
/* 345 */     List fileFolders = findByParentUid(folderUid);
/* 346 */     FileFolder fileFolder = findById(folderUid);
/* 347 */     if (fileFolder == null) return map;
/* 348 */     List rtnList = new ArrayList();
/* 349 */     map.put("id", fileFolder.getFolderUid());
/* 350 */     map.put("text", fileFolder.getName());
/* 351 */     map.put("children", rtnList);
/*     */ 
/* 355 */     for (FileFolder f : fileFolders) {
/* 356 */       Map tmp = new HashMap();
/* 357 */       tmp.put("id", f.getFolderUid());
/* 358 */       tmp.put("text", f.getName());
/* 359 */       tmp.put("cls", "folder");
/* 360 */       List tmpl = findByParentUid(f.getFolderUid());
/* 361 */       if (!tmpl.isEmpty()) {
/* 362 */         folders(tmp, f.getFolderUid());
/* 363 */         rtnList.add(tmp);
/*     */       }
/*     */       else {
/* 366 */         tmp.put("children", new ArrayList());
/* 367 */         rtnList.add(tmp);
/*     */       }
/*     */     }
/* 370 */     return map;
/*     */   }
/*     */ 
/*     */   public List<FileFolder> findParentsByUid(String folderUid)
/*     */   {
/* 378 */     List fileFolders = new ArrayList();
/* 379 */     findParents(fileFolders, folderUid);
/* 380 */     Collections.reverse(fileFolders);
/* 381 */     return fileFolders;
/*     */   }
/*     */ 
/*     */   public void findParents(List<FileFolder> fileFolders, String folderUid)
/*     */   {
/* 389 */     FileFolder fileFolder = findById(folderUid);
/* 390 */     if ((fileFolder == null) || (fileFolder.getParentUid() == null)) return;
/* 391 */     FileFolder parent = findById(fileFolder.getParentUid());
/* 392 */     if (parent == null) return;
/* 393 */     fileFolders.add(parent);
/* 394 */     findParents(fileFolders, fileFolder.getParentUid());
/*     */   }
/*     */ 
/*     */   public void batchDelete(String batchUids) {
/* 398 */     List fileFolders = new ArrayList();
/* 399 */     String[] uids = batchUids.split(",");
/* 400 */     for (String folderUid : uids) {
/* 401 */       FileFolder folder = findById(folderUid);
/* 402 */       if (folder != null)
/*     */       {
/* 404 */         fileFolders.add(folder);
/*     */         try {
/* 406 */           if (folder.getResourceUid() != null)
/* 407 */             this.acManager.deleteFolderResource(folder.getResourceUid());
/*     */         }
/*     */         catch (Exception e) {
/* 410 */           log.error("删除文件夹的资源失败！", e);
/*     */         }
/*     */       }
/*     */     }
/* 413 */     this.folderDao.delete(fileFolders);
/*     */   }
/*     */ 
/*     */   public void moves(String folderUids, FileFolder fileFolder) {
/* 417 */     String[] uids = folderUids.split(",");
/* 418 */     List ids = new ArrayList();
/* 419 */     for (String fileUid : uids) {
/* 420 */       ids.add(fileUid);
/*     */     }
/* 422 */     Iterable fileFolders = this.folderDao.findAll(ids);
/* 423 */     for (FileFolder folder : fileFolders) {
/* 424 */       folder.setParentUid(fileFolder.getFolderUid());
/*     */     }
/* 426 */     this.folderDao.save(fileFolders);
/*     */   }
/*     */ 
/*     */   public void moveFolderAndResource(String folderUids, FileFolder fileFolder) {
/* 430 */     moves(folderUids, fileFolder);
/* 431 */     String[] folderUidArray = folderUids.split(",");
/* 432 */     for (String folderUid : folderUidArray)
/*     */       try {
/* 434 */         this.acManager.moveFolderResource(findById(folderUid).getResourceUid(), 
/* 435 */           fileFolder.getResourceUid());
/*     */       } catch (Exception e) {
/* 437 */         log.error("移动资源出现异常！", e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void batchLogicDelete(String folderUids)
/*     */   {
/* 443 */     String[] uids = folderUids.split(",");
/* 444 */     List ids = new ArrayList();
/* 445 */     for (String fileUid : uids) {
/* 446 */       ids.add(fileUid);
/*     */     }
/* 448 */     Iterable fileFolders = this.folderDao.findAll(ids);
/* 449 */     for (FileFolder folder : fileFolders)
/*     */     {
/* 458 */       folder.setDeleted(Boolean.valueOf(true));
/*     */       try {
/* 460 */         if (folder.getResourceUid() != null)
/* 461 */           this.acManager.deleteFolderResource(folder.getResourceUid());
/*     */       }
/*     */       catch (Exception e) {
/* 464 */         log.error("删除文件夹的资源失败！", e);
/*     */       }
/*     */     }
/* 467 */     this.folderDao.save(fileFolders);
/*     */   }
/*     */ 
/*     */   public FileFolder findByResourceUid(String resourceUid) {
/* 471 */     return this.folderDao.findByResourceUid(resourceUid);
/*     */   }
/*     */ 
/*     */   public void saveFolderAndResource(FileFolder folder) {
/* 475 */     save(folder);
/* 476 */     createResource(folder);
/*     */   }
/*     */ 
/*     */   public void deleteFolderAndResource(String folderUid) {
/* 480 */     FileFolder fileFolder = (FileFolder)this.folderDao.findOne(folderUid);
/*     */     try {
/* 482 */       if (fileFolder.getResourceUid() != null)
/* 483 */         this.acManager.deleteFolderResource(fileFolder.getResourceUid());
/*     */     }
/*     */     catch (Exception e) {
/* 486 */       log.error("删除文件夹的资源失败！", e);
/*     */     }
/* 488 */     this.folderDao.delete(fileFolder);
/*     */   }
/*     */ 
/*     */   public void updateFolderAndResource(FileFolder fileFolder)
/*     */   {
/*     */     try {
/* 494 */       Resource resource = null;
/* 495 */       String resourceUid = fileFolder.getResourceUid();
/* 496 */       if ((resourceUid != null) && (this.acManager.getFolderResource(resourceUid) == null)) {
/* 497 */         String parentResourceUid = findById(fileFolder.getParentUid()).getResourceUid();
/* 498 */         resource = this.acManager.createResource(fileFolder.getName(), "注册文件资源", 
/* 499 */           parentResourceUid, "folder");
/*     */       } else {
/* 501 */         this.acManager.updateFolderResource(fileFolder);
/* 502 */         resource = this.acManager.getFolderResource(resourceUid);
/*     */       }
/* 504 */       fileFolder.setResourceUid(resource.getUID());
/* 505 */       this.folderDao.save(fileFolder);
/*     */     } catch (Exception e) {
/* 507 */       log.error("修改资源出现异常！", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void batchDeleteFolderAndResource(String folderUids)
/*     */   {
/* 514 */     String[] uidArray = folderUids.split(",");
/* 515 */     List uidList = new ArrayList();
/* 516 */     for (String uid : uidArray) {
/* 517 */       uidList.add(uid);
/*     */     }
/* 519 */     Iterable fileFolders = this.folderDao.findAll(uidList);
/*     */ 
/* 521 */     for (FileFolder fileFolder : fileFolders) {
/*     */       try {
/* 523 */         this.acManager.deleteFolderResource(fileFolder.getResourceUid());
/*     */       } catch (Exception e) {
/* 525 */         log.error("删除资源出现异常！", e);
/*     */       }
/*     */     }
/* 528 */     this.folderDao.delete(fileFolders);
/*     */   }
/*     */ 
/*     */   public void createResource(FileFolder folder) {
/*     */     try {
/* 533 */       Resource resource = null;
/* 534 */       String ruid = folder.getResourceUid();
/* 535 */       if (ruid == null)
/* 536 */         resource = this.acManager.createResource(folder.getName(), 
/* 537 */           folder.getDescription(), 
/* 538 */           findById(folder.getParentUid()).getResourceUid(), "文件夹");
/*     */       else {
/* 540 */         resource = this.acManager.getFolderResource(ruid);
/*     */       }
/* 542 */       folder.setResourceUid(resource.getUID());
/* 543 */       this.folderDao.save(folder);
/*     */     } catch (Exception e) {
/* 545 */       log.error("创建资源出现异常！", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void saveAndCreateResouces(List<FileFolder> fileFolders) {
/* 550 */     for (FileFolder fileFolder : fileFolders)
/* 551 */       saveFolderAndResource(fileFolder);
/*     */   }
/*     */ 
/*     */   public void update(FileFolder fileFolder)
/*     */   {
/* 556 */     this.folderDao.save(fileFolder);
/*     */   }
/*     */ 
/*     */   public FileFolder findByNameAndPath(String folderName, String path)
/*     */   {
/* 566 */     return this.folderDao.findByNameAndPath(folderName, path);
/*     */   }
/*     */ 
/*     */   public List<FileFolder> findByName(String name, String parentName) {
/* 570 */     return this.folderDao.findByNameAndPName("%" + name + "%", "%" + parentName + "%");
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.FolderServiceImpl
 * JD-Core Version:    0.6.1
 */