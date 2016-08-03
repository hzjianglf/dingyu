/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.EntityManagerFactory;
/*    */ import javax.persistence.Query;
/*    */ import net.risesoft.soa.filecube.dao.LogDao;
/*    */ import net.risesoft.soa.filecube.model.FileLog;
/*    */ import net.risesoft.soa.filecube.service.LogService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("logService")
/*    */ public class LogServiceImpl
/*    */   implements LogService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private LogDao logDao;
/*    */ 
/*    */   @Autowired
/*    */   private EntityManagerFactory emf;
/*    */ 
/*    */   public void save(FileLog log)
/*    */   {
/* 24 */     this.logDao.save(log);
/*    */   }
/*    */   public List<FileLog> findAll() {
/* 27 */     return this.logDao.findAll();
/*    */   }
/*    */ 
/*    */   public List<FileLog> findAll(int start, int rows)
/*    */   {
/* 32 */     EntityManager em = this.emf.createEntityManager();
/* 33 */     Query query = em.createQuery(" from FileLog order by logDate desc ");
/* 34 */     query.setFirstResult(start);
/* 35 */     query.setMaxResults(rows);
/* 36 */     return query.getResultList();
/*    */   }
/*    */ 
/*    */   public long count() {
/* 40 */     return this.logDao.count();
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.LogServiceImpl
 * JD-Core Version:    0.6.1
 */