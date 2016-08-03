/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.EntityManagerFactory;
/*    */ import javax.persistence.Query;
/*    */ import net.risesoft.soa.filecube.dao.OpenHistoryDao;
/*    */ import net.risesoft.soa.filecube.model.FileInfo;
/*    */ import net.risesoft.soa.filecube.model.FileOpenHistory;
/*    */ import net.risesoft.soa.filecube.service.OpenHistoryService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("openHistoryService")
/*    */ public class OpenHistoryServiceImpl
/*    */   implements OpenHistoryService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private OpenHistoryDao dao;
/*    */ 
/*    */   @Autowired
/*    */   private EntityManagerFactory emf;
/*    */ 
/*    */   public void save(FileOpenHistory openHistory)
/*    */   {
/* 29 */     FileOpenHistory fileOpenHistory = this.dao.findByUserAndFile(openHistory.getUserUid(), openHistory.getFileInfo().getFileUid());
/* 30 */     if (fileOpenHistory != null) {
/* 31 */       fileOpenHistory.setOpenDate(new Date());
/* 32 */       this.dao.save(fileOpenHistory);
/*    */     } else {
/* 34 */       this.dao.save(openHistory);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<FileOpenHistory> findByUserUid(String userUid)
/*    */   {
/* 40 */     EntityManager em = this.emf.createEntityManager();
/* 41 */     Query query = em.createQuery("from FileOpenHistory f where f.userUid = ?1 order by f.openDate desc");
/* 42 */     query.setParameter(1, userUid);
/* 43 */     query.setFirstResult(0);
/* 44 */     query.setMaxResults(10);
/* 45 */     return query.getResultList();
/*    */   }
/*    */ 
/*    */   public List<FileOpenHistory> findRelationOpenHistory(String fileUid, String userUid)
/*    */   {
/* 51 */     EntityManager em = this.emf.createEntityManager();
/* 52 */     Query query = em.createQuery("select ff from  FileOpenHistory ff where ff.userUid in (select f.userUid from FileOpenHistory f where f.fileInfo.fileUid = ?1 ) and ff.fileInfo.fileUid != ?1 and ff.fileInfo.type = ?2 order by ff.openDate desc");
/*    */ 
/* 58 */     query.setParameter(1, fileUid);
/* 59 */     query.setParameter(2, "document");
/* 60 */     query.setFirstResult(0);
/* 61 */     query.setMaxResults(10);
/* 62 */     return query.getResultList();
/*    */   }
/*    */ 
/*    */   public long findCountByFileUid(String fileUid)
/*    */   {
/* 67 */     return this.dao.findCountByFileUid(fileUid);
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.OpenHistoryServiceImpl
 * JD-Core Version:    0.6.1
 */