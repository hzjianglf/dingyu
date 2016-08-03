/*    */ package net.risesoft.soa.filecube.model;
/*    */ 
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_searchLog")
/*    */ public class FileSearchLog
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String logUid;
/*    */   private String keyWords;
/*    */   private long searchTimes;
/*    */ 
/*    */   public String getLogUid()
/*    */   {
/* 20 */     return this.logUid;
/*    */   }
/*    */   public void setLogUid(String logUid) {
/* 23 */     this.logUid = logUid;
/*    */   }
/*    */   public String getKeyWords() {
/* 26 */     return this.keyWords;
/*    */   }
/*    */   public void setKeyWords(String keyWords) {
/* 29 */     this.keyWords = keyWords;
/*    */   }
/*    */   public long getSearchTimes() {
/* 32 */     return this.searchTimes;
/*    */   }
/*    */   public void setSearchTimes(long searchTimes) {
/* 35 */     this.searchTimes = searchTimes;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileSearchLog
 * JD-Core Version:    0.6.1
 */