/*     */ package net.risesoft.soa.filecube.service.impl;
/*     */ 
/*     */ import egov.appservice.ac.model.Resource;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import javax.persistence.Query;
/*     */ import net.risesoft.soa.filecube.dao.FileDao;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.model.FileOfficialDocument;
/*     */ import net.risesoft.soa.filecube.service.FileService;
/*     */ import net.risesoft.soa.filecube.service.util.AcManager;
/*     */ import net.risesoft.soa.filecube.util.DateUtil;
/*     */ import net.risesoft.soa.filecube.util.FileSize;
/*     */ import net.risesoft.soa.filecube.util.FileType;
/*     */ import net.risesoft.soa.filecube.util.FileType.IMAGE;
/*     */ import net.risesoft.soa.filecube.util.FileUploader;
/*     */ import net.risesoft.soa.filecube.util.FileUtils;
/*     */ import net.risesoft.soa.filecube.util.GlobalInfo;
/*     */ import net.risesoft.soa.filecube.util.ResizeImage;
/*     */ import net.risesoft.soa.filecube.util.SystemInfo;
/*     */ import net.risesoft.soa.framework.util.Base64;
/*     */ import net.risesoft.soa.org.manager.PersonManager;
/*     */ import net.risesoft.soa.org.model.OrgUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional
/*     */ @Service("fileInfoService")
/*     */ public class FileServiceImpl
/*     */   implements FileService
/*     */ {
/*  49 */   private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   public FileDao fileDao;
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*     */   @Autowired
/*     */   private AcManager acManager;
/*     */ 
/*     */   @Autowired
/*     */   private PersonManager personManager;
/*     */ 
/*  63 */   public List<FileInfo> findByFolderUid(String folderUid) { return this.fileDao.findByFolderUid(folderUid); }
/*     */ 
/*     */   public void convertFile(String fileUid)
/*     */   {
/*  67 */     FileInfo fileInfo = findById(fileUid);
/*  68 */     String rootDir = GlobalInfo.getInstance().getUploadRoot();
/*  69 */     String filePath = rootDir + (rootDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE) + fileInfo.getDirectory();
/*  70 */     if (fileInfo.isZipDoc()) {
/*  71 */       String zipFilePath = filePath + ".zip";
/*  72 */       File path = new File(zipFilePath);
/*  73 */       File file = new File(path.getParent() + SystemInfo.SEPARATOR_FILE + 
/*  74 */         fileInfo.getFileUid() + "." + fileInfo.getExtension());
/*     */ 
/*  76 */       if (!path.exists()) {
/*  77 */         path.mkdirs();
/*     */       }
/*     */ 
/*  80 */       if ((!new File(zipFilePath).exists()) || (file.exists())) {
/*  81 */         return;
/*     */       }
/*     */ 
/*  84 */       byte[] fileBytes = FileUtils.zipEntityBytes(zipFilePath, "risedata");
/*  85 */       FileOutputStream fos = null;
/*     */       try {
/*  87 */         fos = new FileOutputStream(file);
/*  88 */         fos.write(fileBytes);
/*  89 */         FileUploader.convertFile(file.getAbsolutePath());
/*     */       }
/*     */       catch (IOException e) {
/*  92 */         e.printStackTrace();
/*     */         try
/*     */         {
/*  95 */           if (fos == null) return;
/*  96 */           fos.close();
/*     */         } catch (IOException e) {
/*  98 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/*  95 */           if (fos != null)
/*  96 */             fos.close();
/*     */         } catch (IOException e) {
/*  98 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/*  95 */         if (fos == null) return;
/*  96 */         fos.close();
/*     */       } catch (IOException e) {
/*  98 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 103 */       String pathName = filePath + fileInfo.getFileUid() + "." + fileInfo.getExtension();
/* 104 */       FileUploader.convertFile(pathName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public FileInfo save(FileInfo file)
/*     */   {
/* 114 */     if (file.isZipDoc()) {
/* 115 */       String zipFilePath = GlobalInfo.getInstance().getHD_fileShareDir() + file.getDirectory() + ".zip";
/* 116 */       File f = new File(zipFilePath);
/* 117 */       if (f.exists()) {
/* 118 */         file.setSize(Long.valueOf(f.length()));
/*     */       }
/* 120 */       file.setDirectory(GlobalInfo.getInstance().getHD_fileShareDir() + file.getDirectory());
/*     */     }
/*     */     try
/*     */     {
/* 124 */       if (file.getResourceUid() != null) {
/* 125 */         Resource resource = this.acManager.getFolderResource(file.getResourceUid());
/* 126 */         if (resource == null) {
/* 127 */           createResource(file);
/* 128 */           return file;
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/* 132 */       log.error("创建文件的资源失败！", e);
/*     */ 
/* 134 */       FileInfo fileInfo = (FileInfo)this.fileDao.save(file);
/* 135 */       return (FileInfo)this.fileDao.findOne(fileInfo.getFileUid());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void logicDelete(String uid)
/*     */   {
/* 142 */     FileInfo fileInfo = findById(uid);
/* 143 */     fileInfo.setDeleted(Boolean.valueOf(true));
/*     */     try {
/* 145 */       if (fileInfo.getResourceUid() != null)
/* 146 */         this.acManager.deleteFolderResource(fileInfo.getResourceUid());
/*     */     }
/*     */     catch (Exception e) {
/* 149 */       log.error("删除文件的资源失败！", e);
/*     */     }
/* 151 */     save(fileInfo);
/*     */   }
/*     */ 
/*     */   public void delete(String uid)
/*     */   {
/* 158 */     FileInfo ff = findById(uid);
/* 159 */     String rootDir = GlobalInfo.getInstance().getUploadRoot();
/* 160 */     rootDir = rootDir + rootDir + (rootDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);
/* 161 */     if (ff.getDirectory().endsWith(SystemInfo.SEPARATOR_FILE))
/* 162 */       rootDir = rootDir + ff.getDirectory();
/*     */     else
/* 164 */       rootDir = rootDir + ff.getDirectory() + SystemInfo.SEPARATOR_FILE;
/*     */     try
/*     */     {
/* 167 */       File f = new File(rootDir + ff.getFileUid() + "." + ff.getExtension());
/* 168 */       f.delete();
/* 169 */       f = new File(rootDir + ff.getFileUid() + ".swf");
/* 170 */       f.delete();
/*     */     }
/*     */     catch (Exception localException1) {
/*     */     }
/*     */     try {
/* 175 */       if (ff.getResourceUid() != null)
/* 176 */         this.acManager.deleteFolderResource(ff.getResourceUid());
/*     */     }
/*     */     catch (Exception e) {
/* 179 */       log.error("删除文件的资源失败！", e);
/*     */     }
/* 181 */     if (findById(uid) != null)
/* 182 */       this.fileDao.delete(uid);
/*     */   }
/*     */ 
/*     */   public FileInfo findById(String uid)
/*     */   {
/* 191 */     return (FileInfo)this.fileDao.findOne(uid);
/*     */   }
/*     */ 
/*     */   public void update(FileInfo file)
/*     */   {
/* 198 */     this.fileDao.save(file);
/*     */   }
/*     */   public FileInfo save(FileInfo fileInfo, File uploadFile) {
/*     */     try {
/* 202 */       FileInputStream is = new FileInputStream(uploadFile);
/* 203 */       save(fileInfo, is);
/*     */     } catch (FileNotFoundException e) {
/* 205 */       log.error("文件创建失败", e);
/*     */     }
/* 207 */     return fileInfo;
/*     */   }
/*     */ 
/*     */   public FileInfo save(FileInfo fileInfo, InputStream is) {
/* 211 */     String extension = fileInfo.getExtension();
/*     */ 
/* 213 */     String departMentUid = null;
/* 214 */     String departMentName = null;
/* 215 */     if (("".equals(fileInfo.getDepartmentUid())) || (fileInfo.getDepartmentUid() == null)) {
/* 216 */       OrgUnit orgUnit = this.personManager.getParent(fileInfo.getCreatorUid());
/* 217 */       departMentUid = orgUnit.getUID();
/* 218 */       departMentName = orgUnit.getName();
/* 219 */       fileInfo.setDepartmentUid(departMentUid);
/* 220 */       fileInfo.setDepartmentName(departMentName);
/*     */     } else {
/* 222 */       departMentUid = fileInfo.getDepartmentUid();
/* 223 */       departMentName = fileInfo.getDepartmentName();
/*     */     }
/* 225 */     String uploadDir = fileInfo.getDirectory();
/* 226 */     if (uploadDir == null) {
/* 227 */       uploadDir = GlobalInfo.getInstance().getUploadPath(fileInfo.getDepartmentName());
/*     */     }
/* 229 */     uploadDir = uploadDir + (uploadDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);
/*     */ 
/* 231 */     fileInfo.setType(FileType.getFileType(extension));
/* 232 */     fileInfo.setDirectory(uploadDir);
/* 233 */     fileInfo.setSize(Long.valueOf(0L));
/*     */ 
/* 236 */     String year = "";
/* 237 */     if (fileInfo.getCreatedate() == null) {
/* 238 */       Date currDate = new Date();
/* 239 */       fileInfo.setCreatedate(currDate);
/* 240 */       year = DateUtil.getDate("yyyy", currDate);
/*     */     } else {
/* 242 */       year = DateUtil.getDate("yyyy", fileInfo.getCreatedate());
/*     */     }
/*     */ 
/* 245 */     fileInfo.setYear(year);
/*     */ 
/* 247 */     String kind = "未分配";
/* 248 */     if ((!"".equals(fileInfo.getKind())) && (fileInfo.getKind() != null)) {
/* 249 */       kind = fileInfo.getKind();
/*     */     }
/* 251 */     fileInfo.setKind(kind);
/*     */ 
/* 253 */     fileInfo = save(fileInfo);
/*     */ 
/* 255 */     String rootDir = GlobalInfo.getInstance().getUploadRoot();
/* 256 */     rootDir = rootDir + (rootDir.endsWith(SystemInfo.SEPARATOR_FILE) ? "" : SystemInfo.SEPARATOR_FILE);
/*     */ 
/* 258 */     String path = rootDir + uploadDir;
/* 259 */     File pFile = new File(path);
/* 260 */     if (!pFile.exists()) {
/* 261 */       pFile.mkdirs();
/*     */     }
/* 263 */     String filePath = path + fileInfo.getFileUid() + "." + extension;
/*     */ 
/* 265 */     String pathName = filePath;
/* 266 */     FileUploader.createFile(pathName, is);
/*     */ 
/* 268 */     File f = new File(filePath);
/* 269 */     if (FileType.acOpenOfficeFormatFile(extension)) {
/* 270 */       String content = FileUploader.fileToTxt(f);
/* 271 */       FileOfficialDocument fod = fileInfo.getFod();
/* 272 */       if (fod == null) {
/* 273 */         fod = new FileOfficialDocument();
/*     */       }
/* 275 */       fileInfo.setFod(fod);
/* 276 */       fod.setKind(kind);
/* 277 */       fod.setDepartmentName(departMentName);
/* 278 */       fod.setDepartmentUid(departMentUid);
/* 279 */       fod.setYear(year);
/* 280 */       fod.setFileInfo(fileInfo);
/* 281 */       fileInfo.setContent(content);
/*     */     } else {
/*     */       try {
/* 284 */         FileType.IMAGE.valueOf(extension.toUpperCase());
/* 285 */         String smallFilePath = GlobalInfo.getInstance().getUploadRoot() + uploadDir + fileInfo.getFileUid() + "_small." + extension;
/* 286 */         ResizeImage.resize(f.getAbsolutePath(), smallFilePath, 80, 80);
/* 287 */         fileInfo.setSmallIcon(Base64.encodeFromFile(smallFilePath));
/*     */ 
/* 289 */         new File(smallFilePath).delete();
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/*     */     }
/* 294 */     fileInfo.setSize(Long.valueOf(f.length()));
/* 295 */     save(fileInfo);
/*     */ 
/* 297 */     return fileInfo;
/*     */   }
/*     */ 
/*     */   public List<Map<String, Object>> findDeleteFileByUserUid(String userUID) {
/* 301 */     EntityManager em = this.emf.createEntityManager();
/* 302 */     Query query = em.createNativeQuery("select f.fileuid,f.name,f.fileSize,f.type,f.extension from fc_fileinfo f where f.deleted = ?  union select ff.folderUid,ff.name,0 fileSize,'folder' type, 'folder' extension from fc_Folder ff  where ff.deleted = ? ");
/*     */ 
/* 308 */     query.setParameter(1, Boolean.valueOf(true));
/* 309 */     query.setParameter(2, Boolean.valueOf(true));
/*     */ 
/* 311 */     List result = query.getResultList();
/* 312 */     List rtnData = new ArrayList();
/* 313 */     for (Iterator localIterator = result.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/* 314 */       Object[] objs = (Object[])object;
/* 315 */       Map map = new HashMap();
/* 316 */       map.put("uid", objs[0]);
/* 317 */       map.put("name", objs[1]);
/* 318 */       long size = 0L;
/*     */       try {
/* 320 */         size = Long.parseLong(objs[2]);
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException) {
/*     */       }
/* 324 */       map.put("fileSize", FileSize.size(Long.valueOf(size)));
/* 325 */       map.put("type", objs[3]);
/* 326 */       map.put("extension", objs[4]);
/* 327 */       rtnData.add(map);
/*     */     }
/* 329 */     return rtnData;
/*     */   }
/*     */ 
/*     */   public void batchDelete(String batchUids) {
/* 333 */     List fileInfos = new ArrayList();
/* 334 */     String[] uids = batchUids.split(",");
/* 335 */     for (String fileUid : uids) {
/* 336 */       FileInfo fileInfo = findById(fileUid);
/* 337 */       if (fileInfo != null)
/*     */       {
/* 339 */         fileInfos.add(fileInfo);
/*     */         try {
/* 341 */           if (fileInfo.getResourceUid() != null)
/* 342 */             this.acManager.deleteFolderResource(fileInfo.getResourceUid());
/*     */         }
/*     */         catch (Exception e) {
/* 345 */           log.error("删除文件的资源失败！", e);
/*     */         }
/*     */       }
/*     */     }
/* 348 */     this.fileDao.flush();
/* 349 */     this.fileDao.delete(fileInfos);
/*     */   }
/*     */ 
/*     */   public void moves(String fileUids, FileFolder fileFolder) {
/* 353 */     String[] uids = fileUids.split(",");
/* 354 */     List ids = new ArrayList();
/* 355 */     for (String fileUid : uids) {
/* 356 */       ids.add(fileUid);
/*     */     }
/* 358 */     Iterable fileInfos = this.fileDao.findAll(ids);
/* 359 */     for (FileInfo fileInfo : fileInfos) {
/* 360 */       fileInfo.setFileFolder(fileFolder);
/*     */     }
/* 362 */     this.fileDao.save(fileInfos);
/*     */   }
/*     */ 
/*     */   public void batchLogicDelete(String fileUids) {
/* 366 */     String[] uids = fileUids.split(",");
/* 367 */     List ids = new ArrayList();
/* 368 */     for (String fileUid : uids) {
/* 369 */       ids.add(fileUid);
/*     */     }
/* 371 */     Iterable fileInfos = this.fileDao.findAll(ids);
/* 372 */     for (FileInfo fileInfo : fileInfos)
/*     */     {
/* 381 */       fileInfo.setDeleted(Boolean.valueOf(true));
/*     */       try {
/* 383 */         if (fileInfo.getResourceUid() != null)
/* 384 */           this.acManager.deleteFolderResource(fileInfo.getResourceUid());
/*     */       }
/*     */       catch (Exception e) {
/* 387 */         log.error("删除文件的资源失败！", e);
/*     */       }
/*     */     }
/* 390 */     this.fileDao.save(fileInfos);
/*     */   }
/*     */ 
/*     */   public List<FileInfo> findByProperty(int start, int rows, String propertyName, Object value)
/*     */   {
/* 396 */     EntityManager em = this.emf.createEntityManager();
/* 397 */     String sql = " from FileInfo f where f." + propertyName + " = :propertyValue ";
/* 398 */     Query query = em.createQuery(sql);
/* 399 */     query.setParameter("propertyValue", value);
/* 400 */     query.setFirstResult(start);
/* 401 */     query.setMaxResults(rows);
/* 402 */     return query.getResultList();
/*     */   }
/*     */ 
/*     */   public List<FileInfo> findByProperties(int start, int rows, Map<String, Object> propertyValues)
/*     */   {
/* 408 */     EntityManager em = this.emf.createEntityManager();
/* 409 */     String sql = " from FileInfo f where ";
/* 410 */     int i = 0;
/* 411 */     Map params = new HashMap();
/* 412 */     for (String key : propertyValues.keySet()) {
/* 413 */       sql = sql + " f." + key + " = :propertyValue" + i;
/* 414 */       if (i < propertyValues.size() - 1) {
/* 415 */         sql = sql + " and ";
/*     */       }
/* 417 */       params.put("propertyValue" + i, propertyValues.get(key));
/* 418 */       i++;
/*     */     }
/*     */ 
/* 421 */     Query query = em.createQuery(sql);
/* 422 */     for (int j = 0; j < params.size(); j++) {
/* 423 */       query.setParameter("propertyValue" + j, params.get("propertyValue" + j));
/*     */     }
/* 425 */     query.setFirstResult(start);
/* 426 */     query.setMaxResults(rows);
/* 427 */     return query.getResultList();
/*     */   }
/*     */ 
/*     */   public void createResource(FileInfo fileInfo) {
/*     */     try {
/* 432 */       Resource resource = this.acManager.createResource(fileInfo.getName(), "注册文件资源", 
/* 433 */         fileInfo.getFileFolder().getResourceUid(), "文件");
/* 434 */       fileInfo.setResourceUid(resource.getUID());
/* 435 */       this.fileDao.save(fileInfo);
/*     */     } catch (Exception e) {
/* 437 */       log.error("注册资源失败", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteImportFile(String app, String importUid) {
/* 442 */     this.fileDao.deleteImportFile(app, importUid);
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.FileServiceImpl
 * JD-Core Version:    0.6.1
 */