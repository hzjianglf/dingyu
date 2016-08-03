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
/*    */ @Table(name="FC_Meta_Audio")
/*    */ public class FileMetaAudio
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String metaAudioUid;
/*    */   private String importUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */   private Integer length;
/*    */   private String extend1;
/*    */   private String extend2;
/*    */   private String extend3;
/*    */ 
/*    */   public String getMetaAudioUid()
/*    */   {
/* 30 */     return this.metaAudioUid;
/*    */   }
/*    */   public void setMetaAudioUid(String metaAudioUid) {
/* 33 */     this.metaAudioUid = metaAudioUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 36 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 39 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public Integer getLength() {
/* 42 */     return this.length;
/*    */   }
/*    */   public void setLength(Integer length) {
/* 45 */     this.length = length;
/*    */   }
/*    */   public String getExtend1() {
/* 48 */     return this.extend1;
/*    */   }
/*    */   public void setExtend1(String extend1) {
/* 51 */     this.extend1 = extend1;
/*    */   }
/*    */   public String getExtend2() {
/* 54 */     return this.extend2;
/*    */   }
/*    */   public void setExtend2(String extend2) {
/* 57 */     this.extend2 = extend2;
/*    */   }
/*    */   public String getExtend3() {
/* 60 */     return this.extend3;
/*    */   }
/*    */   public void setExtend3(String extend3) {
/* 63 */     this.extend3 = extend3;
/*    */   }
/*    */   public String getImportUid() {
/* 66 */     return this.importUid;
/*    */   }
/*    */   public void setImportUid(String importUid) {
/* 69 */     this.importUid = importUid;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileMetaAudio
 * JD-Core Version:    0.6.1
 */