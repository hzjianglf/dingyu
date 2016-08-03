/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.EntityManagerFactory;
/*    */ import javax.persistence.Query;
/*    */ import net.risesoft.soa.filecube.dao.QueryHistoryDao;
/*    */ import net.risesoft.soa.filecube.model.FileQueryHistory;
/*    */ import net.risesoft.soa.filecube.service.QueryHistoryService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("queryHistoryService")
/*    */ public class QueryHistoryServiceImpl
/*    */   implements QueryHistoryService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private QueryHistoryDao dao;
/*    */ 
/*    */   @Autowired
/*    */   private EntityManagerFactory emf;
/*    */ 
/*    */   public void save(FileQueryHistory queryHistory)
/*    */   {
/* 28 */     this.dao.save(queryHistory);
/*    */   }
/*    */ 
/*    */   public List<FileQueryHistory> findByUserUid(String userUid)
/*    */   {
/* 33 */     EntityManager em = this.emf.createEntityManager();
/* 34 */     Query query = em.createQuery("from FileQueryHistory f where f.userUid = ?1");
/* 35 */     query.setParameter(1, userUid);
/* 36 */     query.setFirstResult(0);
/* 37 */     query.setMaxResults(10);
/* 38 */     return query.getResultList();
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.QueryHistoryServiceImpl
 * JD-Core Version:    0.6.1
 */