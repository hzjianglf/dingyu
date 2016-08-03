/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.risesoft.soa.filecube.dao.AdapterDao;
/*    */ import net.risesoft.soa.filecube.model.FileAdapter;
/*    */ import net.risesoft.soa.filecube.service.AdapterService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("adapterService")
/*    */ public class AdapterServiceImpl
/*    */   implements AdapterService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private AdapterDao adapterDao;
/*    */ 
/*    */   public FileAdapter findByName(String name)
/*    */   {
/* 20 */     return this.adapterDao.findByName(name);
/*    */   }
/*    */ 
/*    */   public FileAdapter findByUid(String adapterUid) {
/* 24 */     return (FileAdapter)this.adapterDao.findOne(adapterUid);
/*    */   }
/*    */ 
/*    */   public List<FileAdapter> findAll() {
/* 28 */     return this.adapterDao.findAll();
/*    */   }
/*    */ 
/*    */   public void save(FileAdapter fileAdapter) {
/* 32 */     this.adapterDao.save(fileAdapter);
/*    */   }
/*    */ 
/*    */   public void delete(FileAdapter fileAdapter) {
/* 36 */     this.adapterDao.delete(fileAdapter);
/*    */   }
/*    */ 
/*    */   public void deleteByUid(String adapterUid) {
/* 40 */     this.adapterDao.delete(adapterUid);
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.AdapterServiceImpl
 * JD-Core Version:    0.6.1
 */