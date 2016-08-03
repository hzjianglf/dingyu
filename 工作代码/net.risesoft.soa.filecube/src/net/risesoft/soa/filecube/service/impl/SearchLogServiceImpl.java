/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import javax.persistence.Query;
/*    */ import net.risesoft.soa.filecube.dao.SearchLogDao;
/*    */ import net.risesoft.soa.filecube.model.FileSearchLog;
/*    */ import net.risesoft.soa.filecube.service.SearchLogService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("searchLogService")
/*    */ public class SearchLogServiceImpl
/*    */   implements SearchLogService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SearchLogDao dao;
/*    */ 
/*    */   @PersistenceContext(name="net.risesoft.soa.filecube.model.FileSearchLog")
/*    */   private EntityManager em;
/*    */ 
/*    */   public List<FileSearchLog> findHotKeyWords()
/*    */   {
/* 26 */     Query query = this.em.createQuery("select s.keyWords from FileSearchLog s  where s.keyWords is not null and s.keyWords != 'null'order by s.searchTimes desc");
/*    */ 
/* 29 */     query.setFirstResult(0);
/* 30 */     query.setMaxResults(10);
/* 31 */     return query.getResultList();
/*    */   }
/*    */ 
/*    */   public void save(FileSearchLog wordsLog) {
/* 35 */     this.dao.save(wordsLog);
/*    */   }
/*    */ 
/*    */   public List<FileSearchLog> findByKeyWords(String keyWords) {
/* 39 */     return this.dao.findByKeyWords(keyWords);
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.SearchLogServiceImpl
 * JD-Core Version:    0.6.1
 */