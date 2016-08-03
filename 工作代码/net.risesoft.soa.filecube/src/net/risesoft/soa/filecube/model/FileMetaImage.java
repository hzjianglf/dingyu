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
/*    */ @Table(name="FC_Meta_Image")
/*    */ public class FileMetaImage
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String metaImageUid;
/*    */   private String importUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */   private Integer width;
/*    */   private Integer height;
/*    */   private Double saturation;
/*    */   private Double contrast;
/*    */   private String extend1;
/*    */   private String extend2;
/*    */   private String extend3;
/*    */ 
/*    */   public String getMetaImageUid()
/*    */   {
/* 32 */     return this.metaImageUid;
/*    */   }
/*    */   public void setMetaImageUid(String metaImageUid) {
/* 35 */     this.metaImageUid = metaImageUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 38 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 41 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public Integer getWidth() {
/* 44 */     return this.width;
/*    */   }
/*    */   public void setWidth(Integer width) {
/* 47 */     this.width = width;
/*    */   }
/*    */   public Integer getHeight() {
/* 50 */     return this.height;
/*    */   }
/*    */   public void setHeight(Integer height) {
/* 53 */     this.height = height;
/*    */   }
/*    */   public Double getSaturation() {
/* 56 */     return this.saturation;
/*    */   }
/*    */   public void setSaturation(Double saturation) {
/* 59 */     this.saturation = saturation;
/*    */   }
/*    */   public Double getContrast() {
/* 62 */     return this.contrast;
/*    */   }
/*    */   public void setContrast(Double contrast) {
/* 65 */     this.contrast = contrast;
/*    */   }
/*    */   public String getExtend1() {
/* 68 */     return this.extend1;
/*    */   }
/*    */   public void setExtend1(String extend1) {
/* 71 */     this.extend1 = extend1;
/*    */   }
/*    */   public String getExtend2() {
/* 74 */     return this.extend2;
/*    */   }
/*    */   public void setExtend2(String extend2) {
/* 77 */     this.extend2 = extend2;
/*    */   }
/*    */   public String getExtend3() {
/* 80 */     return this.extend3;
/*    */   }
/*    */   public void setExtend3(String extend3) {
/* 83 */     this.extend3 = extend3;
/*    */   }
/*    */   public String getImportUid() {
/* 86 */     return this.importUid;
/*    */   }
/*    */   public void setImportUid(String importUid) {
/* 89 */     this.importUid = importUid;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileMetaImage
 * JD-Core Version:    0.6.1
 */