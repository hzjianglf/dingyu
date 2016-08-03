/*    */ package net.risesoft.soa.filecube.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_Query_History")
/*    */ public class FileQueryHistory
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String QHUid;
/*    */   private String userUid;
/*    */   private Date queryDate;
/*    */   private String queryContent;
/*    */ 
/*    */   public String getQHUid()
/*    */   {
/* 25 */     return this.QHUid;
/*    */   }
/*    */   public void setQHUid(String qHUid) {
/* 28 */     this.QHUid = qHUid;
/*    */   }
/*    */   public String getUserUid() {
/* 31 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 34 */     this.userUid = userUid;
/*    */   }
/*    */   public Date getQueryDate() {
/* 37 */     return this.queryDate;
/*    */   }
/*    */   public void setQueryDate(Date queryDate) {
/* 40 */     this.queryDate = queryDate;
/*    */   }
/*    */   public String getQueryContent() {
/* 43 */     return this.queryContent;
/*    */   }
/*    */   public void setQueryContent(String queryContent) {
/* 46 */     this.queryContent = queryContent;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileQueryHistory
 * JD-Core Version:    0.6.1
 */