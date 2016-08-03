/*     */ package net.risesoft.soa.filecube.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="FC_Attachment")
/*     */ public class FileAttachment
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   private String attachmentUid;
/*     */   private String importUid;
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*     */   private FileInfo fileInfo;
/*     */   private String name;
/*     */ 
/*     */   @Column(name="fileSize")
/*     */   private Long size;
/*     */   private String directory;
/*     */ 
/*     */   @Lob
/*     */   @Basic(fetch=FetchType.LAZY)
/*     */   private String content;
/*     */   private String extension;
/*     */   private String type;
/*     */   private String creatorUid;
/*     */   private String creatorName;
/*     */   private Date createDate;
/*     */   private String modifierUid;
/*     */   private Date modifiedDate;
/*     */   private String adapterUid;
/*     */   private String remarks;
/*     */   private boolean deleted;
/*     */   private String smallIcon;
/*     */   private String largeIcon;
/*     */   private long downLoadTimes;
/*     */   private boolean zipDoc;
/*     */   private String extend1;
/*     */   private String extend2;
/*     */   private String extend3;
/*     */ 
/*     */   public String getAttachmentUid()
/*     */   {
/*  64 */     return this.attachmentUid;
/*     */   }
/*     */   public void setAttachmentUid(String attachmentUid) {
/*  67 */     this.attachmentUid = attachmentUid;
/*     */   }
/*     */   public FileInfo getFileInfo() {
/*  70 */     return this.fileInfo;
/*     */   }
/*     */   public void setFileInfo(FileInfo fileInfo) {
/*  73 */     this.fileInfo = fileInfo;
/*     */   }
/*     */   public String getName() {
/*  76 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  79 */     this.name = name;
/*     */   }
/*     */   public Long getSize() {
/*  82 */     return this.size;
/*     */   }
/*     */   public void setSize(Long size) {
/*  85 */     this.size = size;
/*     */   }
/*     */   public String getDirectory() {
/*  88 */     return this.directory;
/*     */   }
/*     */   public void setDirectory(String directory) {
/*  91 */     this.directory = directory;
/*     */   }
/*     */   public String getContent() {
/*  94 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/*  97 */     this.content = content;
/*     */   }
/*     */   public String getExtension() {
/* 100 */     return this.extension;
/*     */   }
/*     */   public void setExtension(String extension) {
/* 103 */     this.extension = extension;
/*     */   }
/*     */   public String getType() {
/* 106 */     return this.type;
/*     */   }
/*     */   public void setType(String type) {
/* 109 */     this.type = type;
/*     */   }
/*     */   public String getCreatorUid() {
/* 112 */     return this.creatorUid;
/*     */   }
/*     */   public void setCreatorUid(String creatorUid) {
/* 115 */     this.creatorUid = creatorUid;
/*     */   }
/*     */   public Date getCreateDate() {
/* 118 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/* 121 */     this.createDate = createDate;
/*     */   }
/*     */   public String getModifierUid() {
/* 124 */     return this.modifierUid;
/*     */   }
/*     */   public void setModifierUid(String modifierUid) {
/* 127 */     this.modifierUid = modifierUid;
/*     */   }
/*     */   public Date getModifiedDate() {
/* 130 */     return this.modifiedDate;
/*     */   }
/*     */   public void setModifiedDate(Date modifiedDate) {
/* 133 */     this.modifiedDate = modifiedDate;
/*     */   }
/*     */   public String getAdapterUid() {
/* 136 */     return this.adapterUid;
/*     */   }
/*     */   public void setAdapterUid(String adapterUid) {
/* 139 */     this.adapterUid = adapterUid;
/*     */   }
/*     */   public String getRemarks() {
/* 142 */     return this.remarks;
/*     */   }
/*     */   public void setRemarks(String remarks) {
/* 145 */     this.remarks = remarks;
/*     */   }
/*     */   public boolean getDeleted() {
/* 148 */     return this.deleted;
/*     */   }
/*     */   public void setDeleted(boolean deleted) {
/* 151 */     this.deleted = deleted;
/*     */   }
/*     */   public String getSmallIcon() {
/* 154 */     return this.smallIcon;
/*     */   }
/*     */   public void setSmallIcon(String smallIcon) {
/* 157 */     this.smallIcon = smallIcon;
/*     */   }
/*     */   public String getLargeIcon() {
/* 160 */     return this.largeIcon;
/*     */   }
/*     */   public void setLargeIcon(String largeIcon) {
/* 163 */     this.largeIcon = largeIcon;
/*     */   }
/*     */   public long getDownLoadTimes() {
/* 166 */     return this.downLoadTimes;
/*     */   }
/*     */   public void setDownLoadTimes(long downLoadTimes) {
/* 169 */     this.downLoadTimes = downLoadTimes;
/*     */   }
/*     */   public String getExtend1() {
/* 172 */     return this.extend1;
/*     */   }
/*     */   public void setExtend1(String extend1) {
/* 175 */     this.extend1 = extend1;
/*     */   }
/*     */   public String getExtend2() {
/* 178 */     return this.extend2;
/*     */   }
/*     */   public void setExtend2(String extend2) {
/* 181 */     this.extend2 = extend2;
/*     */   }
/*     */   public String getExtend3() {
/* 184 */     return this.extend3;
/*     */   }
/*     */   public void setExtend3(String extend3) {
/* 187 */     this.extend3 = extend3;
/*     */   }
/*     */   public String getCreatorName() {
/* 190 */     return this.creatorName;
/*     */   }
/*     */   public void setCreatorName(String creatorName) {
/* 193 */     this.creatorName = creatorName;
/*     */   }
/*     */   public String getImportUid() {
/* 196 */     return this.importUid;
/*     */   }
/*     */   public void setImportUid(String importUid) {
/* 199 */     this.importUid = importUid;
/*     */   }
/*     */   public boolean isZipDoc() {
/* 202 */     return this.zipDoc;
/*     */   }
/*     */   public void setZipDoc(boolean zipDoc) {
/* 205 */     this.zipDoc = zipDoc;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileAttachment
 * JD-Core Version:    0.6.1
 */