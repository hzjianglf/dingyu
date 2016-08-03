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
/*    */ @Table(name="FC_Share")
/*    */ public class FileShare
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String shareUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="folderUid", referencedColumnName="folderUid")
/*    */   private FileFolder fileFolder;
/*    */   private String orgUid;
/*    */   private String orgType;
/*    */   private String userUid;
/*    */   private String userName;
/*    */   private boolean cancelShareMe;
/*    */   private Date shareDate;
/*    */ 
/*    */   public String getShareUid()
/*    */   {
/* 38 */     return this.shareUid;
/*    */   }
/*    */   public void setShareUid(String shareUid) {
/* 41 */     this.shareUid = shareUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 44 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 47 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public String getUserUid() {
/* 50 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 53 */     this.userUid = userUid;
/*    */   }
/*    */   public Date getShareDate() {
/* 56 */     return this.shareDate;
/*    */   }
/*    */   public void setShareDate(Date shareDate) {
/* 59 */     this.shareDate = shareDate;
/*    */   }
/*    */   public FileFolder getFileFolder() {
/* 62 */     return this.fileFolder;
/*    */   }
/*    */   public void setFileFolder(FileFolder fileFolder) {
/* 65 */     this.fileFolder = fileFolder;
/*    */   }
/*    */   public String getOrgUid() {
/* 68 */     return this.orgUid;
/*    */   }
/*    */   public void setOrgUid(String orgUid) {
/* 71 */     this.orgUid = orgUid;
/*    */   }
/*    */   public String getOrgType() {
/* 74 */     return this.orgType;
/*    */   }
/*    */   public void setOrgType(String orgType) {
/* 77 */     this.orgType = orgType;
/*    */   }
/*    */   public String getUserName() {
/* 80 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 83 */     this.userName = userName;
/*    */   }
/*    */   public boolean isCancelShareMe() {
/* 86 */     return this.cancelShareMe;
/*    */   }
/*    */   public void setCancelShareMe(boolean cancelShareMe) {
/* 89 */     this.cancelShareMe = cancelShareMe;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileShare
 * JD-Core Version:    0.6.1
 */