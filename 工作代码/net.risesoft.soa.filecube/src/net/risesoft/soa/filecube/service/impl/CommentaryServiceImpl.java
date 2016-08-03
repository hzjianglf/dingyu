/*    */ package net.risesoft.soa.filecube.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.risesoft.soa.filecube.dao.CommentaryDao;
/*    */ import net.risesoft.soa.filecube.model.FileCommentary;
/*    */ import net.risesoft.soa.filecube.service.CommentaryService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Transactional
/*    */ @Service("commentaryService")
/*    */ public class CommentaryServiceImpl
/*    */   implements CommentaryService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CommentaryDao commentaryDao;
/*    */ 
/*    */   public FileCommentary save(FileCommentary commentary)
/*    */   {
/* 24 */     return (FileCommentary)this.commentaryDao.save(commentary);
/*    */   }
/*    */ 
/*    */   public List<FileCommentary> findByFileUid(String fileUid)
/*    */   {
/* 32 */     return this.commentaryDao.findByFileUid(fileUid);
/*    */   }
/*    */ 
/*    */   public void delete(String commentaryUid)
/*    */   {
/* 39 */     this.commentaryDao.delete(commentaryUid);
/*    */   }
/*    */ 
/*    */   public FileCommentary findById(String commentaryUid)
/*    */   {
/* 46 */     return (FileCommentary)this.commentaryDao.findOne(commentaryUid);
/*    */   }
/*    */ 
/*    */   public List<FileCommentary> findUserCount(String fileUid) {
/* 50 */     return this.commentaryDao.findUserCount(fileUid);
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.CommentaryServiceImpl
 * JD-Core Version:    0.6.1
 */