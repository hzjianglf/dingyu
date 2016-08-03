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
/*    */ @Table(name="FC_Log")
/*    */ public class FileLog
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String logUid;
/*    */   private String userUid;
/*    */   private String userName;
/*    */   private String ip;
/*    */   private Date logDate;
/*    */   private String logType;
/*    */   private String title;
/*    */   private String description;
/*    */ 
/*    */   public String getLogUid()
/*    */   {
/* 26 */     return this.logUid;
/*    */   }
/*    */   public void setLogUid(String logUid) {
/* 29 */     this.logUid = logUid;
/*    */   }
/*    */   public String getUserUid() {
/* 32 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 35 */     this.userUid = userUid;
/*    */   }
/*    */   public String getIp() {
/* 38 */     return this.ip;
/*    */   }
/*    */   public void setIp(String ip) {
/* 41 */     this.ip = ip;
/*    */   }
/*    */   public Date getLogDate() {
/* 44 */     return this.logDate;
/*    */   }
/*    */   public void setLogDate(Date logDate) {
/* 47 */     this.logDate = logDate;
/*    */   }
/*    */   public String getLogType() {
/* 50 */     return this.logType;
/*    */   }
/*    */   public void setLogType(String logType) {
/* 53 */     this.logType = logType;
/*    */   }
/*    */   public String getTitle() {
/* 56 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 59 */     this.title = title;
/*    */   }
/*    */   public String getDescription() {
/* 62 */     return this.description;
/*    */   }
/*    */   public void setDescription(String description) {
/* 65 */     this.description = description;
/*    */   }
/*    */   public String getUserName() {
/* 68 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 71 */     this.userName = userName;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileLog
 * JD-Core Version:    0.6.1
 */