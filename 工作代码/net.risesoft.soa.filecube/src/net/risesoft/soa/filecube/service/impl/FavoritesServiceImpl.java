/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.EntityManagerFactory;
/*    */ import javax.persistence.Query;
/*    */ import net.risesoft.soa.filecube.dao.FavoritesDao;
/*    */ import net.risesoft.soa.filecube.model.FileFavorites;
/*    */ import net.risesoft.soa.filecube.service.FavoritesService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("favoritesService")
/*    */ public class FavoritesServiceImpl
/*    */   implements FavoritesService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private FavoritesDao dao;
/*    */ 
/*    */   @Autowired
/*    */   private EntityManagerFactory emf;
/*    */ 
/*    */   public FileFavorites save(FileFavorites favorites)
/*    */   {
/* 30 */     return (FileFavorites)this.dao.save(favorites);
/*    */   }
/*    */ 
/*    */   public void delete(String uid) {
/* 34 */     this.dao.delete(uid);
/*    */   }
/*    */ 
/*    */   public Map<String, Object> findByUserUid(String userUid, int start, int rows) {
/* 38 */     EntityManager em = this.emf.createEntityManager();
/* 39 */     Query query = em.createQuery(" from FileFavorites f where f.userUid = ?1");
/* 40 */     query.setParameter(1, userUid);
/* 41 */     int totalCount = query.getResultList().size();
/* 42 */     query.setFirstResult(start);
/* 43 */     query.setMaxResults(rows);
/* 44 */     Map rtnDate = new HashMap();
/* 45 */     rtnDate.put("totalCount", Integer.valueOf(totalCount));
/* 46 */     rtnDate.put("datas", query.getResultList());
/* 47 */     return rtnDate;
/*    */   }
/*    */ 
/*    */   public List<FileFavorites> findByUserAndFile(String userUid, String uids) {
/* 51 */     List list = new ArrayList();
/* 52 */     for (String str : uids.split(",")) {
/* 53 */       list.add(str);
/*    */     }
/* 55 */     return this.dao.findByUserAndFile(userUid, list);
/*    */   }
/*    */ 
/*    */   public List<FileFavorites> findRelationFavorite(String fileUid, String userUid)
/*    */   {
/* 61 */     EntityManager em = this.emf.createEntityManager();
/* 62 */     Query query = em.createQuery("select ff from  FileFavorites ff, FileInfo file  where ff.userUid in ( select f.userUid  from FileFavorites f  where f.fileInfo.fileUid = ?1) and file.fileUid != ?1 and file.type = ?2 and ff.fileInfo.fileUid = file.fileUid order by ff.favoriteDate desc");
/*    */ 
/* 69 */     query.setParameter(1, fileUid);
/* 70 */     query.setParameter(2, "document");
/* 71 */     query.setFirstResult(0);
/* 72 */     query.setMaxResults(10);
/* 73 */     return query.getResultList();
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.FavoritesServiceImpl
 * JD-Core Version:    0.6.1
 */