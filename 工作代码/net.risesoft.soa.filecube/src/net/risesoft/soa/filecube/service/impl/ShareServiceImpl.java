/*     */ package net.risesoft.soa.filecube.service.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import javax.persistence.Query;
/*     */ import net.risesoft.soa.filecube.dao.ShareDao;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.model.FileShare;
/*     */ import net.risesoft.soa.filecube.service.FileService;
/*     */ import net.risesoft.soa.filecube.service.FolderService;
/*     */ import net.risesoft.soa.filecube.service.PermissionService;
/*     */ import net.risesoft.soa.filecube.service.ShareService;
/*     */ import net.risesoft.soa.filecube.util.OperationType;
/*     */ import net.risesoft.soa.org.manager.OrgUnitManager;
/*     */ import net.risesoft.soa.org.model.OrgUnit;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional
/*     */ @Service("shareService")
/*     */ public class ShareServiceImpl
/*     */   implements ShareService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private ShareDao dao;
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*     */   @Autowired
/*     */   private FileService fileService;
/*     */ 
/*     */   @Autowired
/*     */   private FolderService folderService;
/*     */ 
/*     */   @Autowired
/*     */   private OrgUnitManager orgUnitManager;
/*     */ 
/*     */   @Autowired
/*     */   private PermissionService permissionService;
/*     */ 
/*     */   public void save(FileShare share)
/*     */   {
/*  46 */     this.dao.save(share);
/*     */   }
/*     */ 
/*     */   public void delete(String shareUid)
/*     */   {
/*  51 */     FileShare fileShare = (FileShare)this.dao.findOne(shareUid);
/*  52 */     String fileUid = "";
/*  53 */     if (fileShare.getFileFolder() != null)
/*  54 */       fileUid = fileShare.getFileFolder().getFolderUid();
/*     */     else {
/*  56 */       fileUid = fileShare.getFileInfo().getFileUid();
/*     */     }
/*  58 */     this.dao.delete(this.dao.findByUserUidAndFileUid(fileShare.getUserUid(), fileUid));
/*     */   }
/*     */ 
/*     */   public Map<String, Object> findByUserUid(String userUid, int start, int rows)
/*     */   {
/*  63 */     List fileShares = new ArrayList();
/*  64 */     List<FileShare> daoFileShares = this.dao.findByUserUid(userUid);
/*  65 */     String fileUid = "";
/*  66 */     String folderUid = "";
/*  67 */     for (FileShare fileShare : daoFileShares) {
/*  68 */       if (((fileShare.getFileInfo() == null) || (fileUid != fileShare.getFileInfo().getFileUid())) && (
/*  69 */         (fileShare.getFileFolder() == null) || (folderUid != fileShare.getFileFolder().getFolderUid())))
/*     */       {
/*  73 */         if (fileShare.getFileInfo() != null)
/*  74 */           fileUid = fileShare.getFileInfo().getFileUid();
/*  75 */         if (fileShare.getFileFolder() != null)
/*  76 */           folderUid = fileShare.getFileFolder().getFolderUid();
/*  77 */         fileShares.add(fileShare);
/*     */       }
/*     */     }
/*  80 */     Map rtnData = new HashMap();
/*  81 */     rtnData.put("totalCount", Integer.valueOf(fileShares.size()));
/*  82 */     int shareCount = fileShares.size();
/*  83 */     if (shareCount < start + rows)
/*  84 */       rtnData.put("datas", fileShares.subList(start, shareCount));
/*     */     else {
/*  86 */       rtnData.put("datas", fileShares.subList(start, start + rows));
/*     */     }
/*  88 */     return rtnData;
/*     */   }
/*     */ 
/*     */   public void save(List<FileShare> fileShares) {
/*  92 */     String fileUid = "";
/*  93 */     if (((FileShare)fileShares.get(0)).getFileFolder() != null)
/*  94 */       fileUid = ((FileShare)fileShares.get(0)).getFileFolder().getFolderUid();
/*     */     else {
/*  96 */       fileUid = ((FileShare)fileShares.get(0)).getFileInfo().getFileUid();
/*     */     }
/*  98 */     this.dao.delete(findByUserUidAndFileUid(((FileShare)fileShares.get(0)).getUserUid(), fileUid));
/*  99 */     this.dao.save(fileShares);
/*     */ 
/* 101 */     List userUids = new ArrayList();
/* 102 */     List resourceUids = new ArrayList();
/* 103 */     for (FileShare fileShare : fileShares) {
/* 104 */       userUids.add(fileShare.getUserUid());
/* 105 */       FileFolder folder = fileShare.getFileFolder();
/* 106 */       if (folder == null) {
/* 107 */         FileInfo fileInfo = fileShare.getFileInfo();
/* 108 */         String fileResourceUid = fileInfo.getResourceUid();
/* 109 */         if ((fileResourceUid == null) || ("".equals(fileResourceUid))) {
/* 110 */           if ((fileInfo.getItemType() == null) || (!fileInfo.getItemType().equals("personnel")))
/* 111 */             this.fileService.createResource(fileInfo);
/* 112 */           fileResourceUid = fileInfo.getResourceUid();
/*     */         }
/* 114 */         resourceUids.add(fileResourceUid);
/*     */       }
/*     */       else {
/* 117 */         String folderResourceUid = folder.getResourceUid();
/* 118 */         if ((folderResourceUid == null) || ("".equals(folderResourceUid))) {
/* 119 */           if ((folder.getItemType() == null) || (!folder.getItemType().equals("personnel")))
/* 120 */             this.folderService.createResource(folder);
/* 121 */           folderResourceUid = folder.getResourceUid();
/*     */         } else {
/* 123 */           resourceUids.add(folderResourceUid);
/*     */         }
/*     */       }
/*     */     }
/* 127 */     String[] uids = new String[userUids.size()];
/* 128 */     uids = (String[])userUids.toArray(uids);
/*     */ 
/* 130 */     String[] rUids = new String[resourceUids.size()];
/* 131 */     rUids = (String[])resourceUids.toArray(rUids);
/*     */ 
/* 133 */     String[] optKeys = { OperationType.FC_VIEW.toString() };
/* 134 */     this.permissionService.grantPermission(uids, rUids, optKeys);
/*     */   }
/*     */ 
/*     */   public List<FileShare> findByUserUidAndFileUid(String userUid, String fileUid)
/*     */   {
/* 139 */     return this.dao.findByUserUidAndFileUid(userUid, fileUid);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> shareToMeList(String userUid, int start, int rows)
/*     */   {
/* 144 */     EntityManager em = this.emf.createEntityManager();
/*     */ 
/* 146 */     List parentUids = new ArrayList();
/* 147 */     parentUids.add(userUid);
/* 148 */     findParentUids(userUid, parentUids);
/* 149 */     Query query = em.createQuery(" from FileShare f where f.orgUid in (?1) and f.cancelShareMe = ?2");
/*     */ 
/* 151 */     query.setParameter(1, parentUids);
/* 152 */     query.setParameter(2, Boolean.valueOf(false));
/* 153 */     int totalCount = query.getResultList().size();
/* 154 */     query.setFirstResult(start);
/* 155 */     query.setMaxResults(rows);
/* 156 */     Map rtnDate = new HashMap();
/* 157 */     rtnDate.put("totalCount", Integer.valueOf(totalCount));
/* 158 */     rtnDate.put("datas", query.getResultList());
/* 159 */     return rtnDate;
/*     */   }
/*     */ 
/*     */   private void findParentUids(String userUid, List<String> parentUids)
/*     */   {
/* 168 */     OrgUnit orgUnit = this.orgUnitManager.getParent(userUid);
/* 169 */     if (orgUnit == null)
/* 170 */       return;
/* 171 */     parentUids.add(orgUnit.getUID());
/* 172 */     findParentUids(orgUnit.getUID(), parentUids);
/*     */   }
/*     */ 
/*     */   public void deleteOne(String shareUid)
/*     */   {
/* 177 */     FileShare share = (FileShare)this.dao.findOne(shareUid);
/* 178 */     share.setCancelShareMe(true);
/* 179 */     this.dao.save(share);
/*     */   }
/*     */ 
/*     */   public boolean shareToMe(String userUid, String fileUid)
/*     */   {
/* 184 */     FileShare fileShare = this.dao.shareToMe(userUid, fileUid, false);
/* 185 */     if (fileShare == null) {
/* 186 */       return false;
/*     */     }
/* 188 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\椴佺編杈惧伐浣淺椴佺編杈炬闈闇�淇敼鐨刯ar鍖匼net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.ShareServiceImpl
 * JD-Core Version:    0.6.1
 */