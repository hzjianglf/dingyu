/*    */ package net.risesoft.soa.filecube.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_Open_History")
/*    */ public class FileOpenHistory
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String OHUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */   private String userUid;
/*    */   private Date openDate;
/*    */ 
/*    */   public String getOHUid()
/*    */   {
/* 27 */     return this.OHUid;
/*    */   }
/*    */   public void setOHUid(String oHUid) {
/* 30 */     this.OHUid = oHUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 33 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 36 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public String getUserUid() {
/* 39 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 42 */     this.userUid = userUid;
/*    */   }
/*    */   public Date getOpenDate() {
/* 45 */     return this.openDate;
/*    */   }
/*    */   public void setOpenDate(Date openDate) {
/* 48 */     this.openDate = openDate;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileOpenHistory
 * JD-Core Version:    0.6.1
 */