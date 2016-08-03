/*    */ package net.risesoft.soa.filecube.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_Meta_Doc")
/*    */ public class FileMetaDoc
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String metaDocUid;
/*    */   private String importUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */   private String title;
/*    */   private String summary;
/*    */   private String keyWord;
/*    */   private String remarks;
/*    */   private String extend1;
/*    */   private String extend2;
/*    */   private String extend3;
/*    */ 
/*    */   public String getMetaDocUid()
/*    */   {
/* 33 */     return this.metaDocUid;
/*    */   }
/*    */   public void setMetaDocUid(String metaDocUid) {
/* 36 */     this.metaDocUid = metaDocUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 39 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 42 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public String getTitle() {
/* 45 */     return this.title;
/*    */   }
/*    */   public void setTitle(String title) {
/* 48 */     this.title = title;
/*    */   }
/*    */   public String getSummary() {
/* 51 */     return this.summary;
/*    */   }
/*    */   public void setSummary(String summary) {
/* 54 */     this.summary = summary;
/*    */   }
/*    */   public String getKeyWord() {
/* 57 */     return this.keyWord;
/*    */   }
/*    */   public void setKeyWord(String keyWord) {
/* 60 */     this.keyWord = keyWord;
/*    */   }
/*    */   public String getRemarks() {
/* 63 */     return this.remarks;
/*    */   }
/*    */   public void setRemarks(String remarks) {
/* 66 */     this.remarks = remarks;
/*    */   }
/*    */   public String getExtend1() {
/* 69 */     return this.extend1;
/*    */   }
/*    */   public void setExtend1(String extend1) {
/* 72 */     this.extend1 = extend1;
/*    */   }
/*    */   public String getExtend2() {
/* 75 */     return this.extend2;
/*    */   }
/*    */   public void setExtend2(String extend2) {
/* 78 */     this.extend2 = extend2;
/*    */   }
/*    */   public String getExtend3() {
/* 81 */     return this.extend3;
/*    */   }
/*    */   public void setExtend3(String extend3) {
/* 84 */     this.extend3 = extend3;
/*    */   }
/*    */   public String getImportUid() {
/* 87 */     return this.importUid;
/*    */   }
/*    */   public void setImportUid(String importUid) {
/* 90 */     this.importUid = importUid;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileMetaDoc
 * JD-Core Version:    0.6.1
 */