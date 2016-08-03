/*     */ package net.risesoft.soa.filecube.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.Table;
/*     */ import net.risesoft.soa.filecube.util.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="FC_fileinfo")
/*     */ public class FileInfo
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   private String fileUid;
/*     */   private String resourceUid;
/*     */   private String itemType;
/*     */   private String importUid;
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="folderUid", referencedColumnName="folderUid")
/*     */   private FileFolder fileFolder;
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="adapterUid", referencedColumnName="adapterUid")
/*     */   private FileAdapter adapter;
/*     */   private String name;
/*     */ 
/*     */   @Lob
/*     */   @Basic(fetch=FetchType.LAZY)
/*     */   private String content;
/*     */   private String type;
/*     */   private String extension;
/*     */   private String creatorUid;
/*     */   private String creatorName;
/*     */   private String lastModifiedUserUid;
/*     */   private String departmentUid;
/*     */   private String departmentName;
/*     */   private Date createdate;
/*     */   private Date modifieddate;
/*     */   private String keyWords;
/*     */   private String directory;
/*     */ 
/*     */   @Column(name="fileSize")
/*     */   private Long size;
/*     */   private String app;
/*     */   private String subApp;
/*     */   private String description;
/*     */   private String remarks;
/*     */ 
/*     */   @Lob
/*     */   private String smallIcon;
/*     */   private String largeIcon;
/*     */   private String essences;
/*     */   private Boolean deleted;
/*     */   private Integer downLoadTimes;
/*     */   private Integer openTimes;
/*     */   private String year;
/*     */   private String kind;
/*     */   private String extend1;
/*     */   private String extend2;
/*     */   private String extend3;
/*     */   private String extendFolderUids;
/*     */ 
/*     */   @OneToMany(mappedBy="fileInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileCommentary> fileCommentaries;
/*     */ 
/*     */   @OneToOne(mappedBy="fileInfo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
/*     */   private FileMetaDoc fileMetaDoc;
/*     */ 
/*     */   @OneToOne(mappedBy="fileInfo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
/*     */   private FileMetaImage fileMetaImage;
/*     */ 
/*     */   @OneToOne(mappedBy="fileInfo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
/*     */   private FileMetaVideo fileMetaVideo;
/*     */ 
/*     */   @OneToOne(mappedBy="fileInfo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
/*     */   private FileMetaAudio fileMetaAudio;
/*     */ 
/*     */   @OneToMany(mappedBy="fileInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileShare> fileShares;
/*     */ 
/*     */   @OneToMany(mappedBy="fileInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileAttachment> fileAttachments;
/*     */ 
/*     */   @OneToMany(mappedBy="fileInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileFavorites> fileFavoritesAlls;
/*     */ 
/*     */   @OneToOne(mappedBy="fileInfo", fetch=FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL})
/*     */   private FileOfficialDocument fod;
/*     */ 
/*     */   @OneToMany(mappedBy="fileInfo", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileOpenHistory> fileOpenHistories;
/*     */   private boolean zipDoc;
/*     */   private String url;
/*     */ 
/*     */   public String getFileUid()
/*     */   {
/* 117 */     return this.fileUid;
/*     */   }
/*     */   public void setFileUid(String fileUid) {
/* 120 */     this.fileUid = fileUid;
/*     */   }
/*     */   public FileFolder getFileFolder() {
/* 123 */     return this.fileFolder;
/*     */   }
/*     */   public void setFileFolder(FileFolder fileFolder) {
/* 126 */     this.fileFolder = fileFolder;
/*     */   }
/*     */   public FileAdapter getAdapter() {
/* 129 */     return this.adapter;
/*     */   }
/*     */   public void setAdapter(FileAdapter adapter) {
/* 132 */     this.adapter = adapter;
/*     */   }
/*     */   public String getName() {
/* 135 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/* 138 */     this.name = name;
/*     */   }
/*     */   public String getContent() {
/* 141 */     return StringUtils.nullStr(this.content);
/*     */   }
/*     */   public void setContent(String content) {
/* 144 */     this.content = content;
/*     */   }
/*     */   public String getType() {
/* 147 */     return StringUtils.nullStr(this.type);
/*     */   }
/*     */   public void setType(String type) {
/* 150 */     this.type = type;
/*     */   }
/*     */   public String getExtension() {
/* 153 */     return StringUtils.nullStr(this.extension);
/*     */   }
/*     */   public void setExtension(String extension) {
/* 156 */     this.extension = extension;
/*     */   }
/*     */   public String getCreatorUid() {
/* 159 */     return StringUtils.nullStr(this.creatorUid);
/*     */   }
/*     */   public void setCreatorUid(String creatorUid) {
/* 162 */     this.creatorUid = creatorUid;
/*     */   }
/*     */   public Date getCreatedate() {
/* 165 */     return this.createdate;
/*     */   }
/*     */   public void setCreatedate(Date createdate) {
/* 168 */     this.createdate = createdate;
/*     */   }
/*     */   public Date getModifieddate() {
/* 171 */     return this.modifieddate;
/*     */   }
/*     */   public void setModifieddate(Date modifieddate) {
/* 174 */     this.modifieddate = modifieddate;
/*     */   }
/*     */   public String getKeyWords() {
/* 177 */     return StringUtils.nullStr(this.keyWords);
/*     */   }
/*     */   public void setKeyWords(String keyWords) {
/* 180 */     this.keyWords = keyWords;
/*     */   }
/*     */   public String getDirectory() {
/* 183 */     return StringUtils.nullStr(this.directory);
/*     */   }
/*     */   public void setDirectory(String directory) {
/* 186 */     this.directory = directory;
/*     */   }
/*     */   public Long getSize() {
/* 189 */     if ((this.size == null) || (this.size.equals("null"))) {
/* 190 */       return Long.valueOf(0L);
/*     */     }
/* 192 */     return this.size;
/*     */   }
/*     */   public void setSize(Long size) {
/* 195 */     this.size = size;
/*     */   }
/*     */   public String getApp() {
/* 198 */     return StringUtils.nullStr(this.app);
/*     */   }
/*     */   public void setApp(String app) {
/* 201 */     this.app = app;
/*     */   }
/*     */   public String getSubApp() {
/* 204 */     return StringUtils.nullStr(this.subApp);
/*     */   }
/*     */   public void setSubApp(String subApp) {
/* 207 */     this.subApp = subApp;
/*     */   }
/*     */   public String getDescription() {
/* 210 */     return StringUtils.nullStr(this.description);
/*     */   }
/*     */   public void setDescription(String description) {
/* 213 */     this.description = description;
/*     */   }
/*     */   public String getRemarks() {
/* 216 */     return StringUtils.nullStr(this.remarks);
/*     */   }
/*     */   public void setRemarks(String remarks) {
/* 219 */     this.remarks = remarks;
/*     */   }
/*     */   public String getSmallIcon() {
/* 222 */     return StringUtils.nullStr(this.smallIcon);
/*     */   }
/*     */   public void setSmallIcon(String smallIcon) {
/* 225 */     this.smallIcon = smallIcon;
/*     */   }
/*     */   public String getLargeIcon() {
/* 228 */     return StringUtils.nullStr(this.largeIcon);
/*     */   }
/*     */   public void setLargeIcon(String largeIcon) {
/* 231 */     this.largeIcon = largeIcon;
/*     */   }
/*     */   public String getEssences() {
/* 234 */     return StringUtils.nullStr(this.essences);
/*     */   }
/*     */   public void setEssences(String essences) {
/* 237 */     this.essences = essences;
/*     */   }
/*     */   public Boolean getDeleted() {
/* 240 */     return this.deleted;
/*     */   }
/*     */   public void setDeleted(Boolean deleted) {
/* 243 */     this.deleted = deleted;
/*     */   }
/*     */   public Integer getDownLoadTimes() {
/* 246 */     if ((this.downLoadTimes == null) || ("null".equals(this.downLoadTimes))) {
/* 247 */       return Integer.valueOf(0);
/*     */     }
/* 249 */     return this.downLoadTimes;
/*     */   }
/*     */   public void setDownLoadTimes(Integer downLoadTimes) {
/* 252 */     this.downLoadTimes = downLoadTimes;
/*     */   }
/*     */   public Integer getOpenTimes() {
/* 255 */     if ((this.openTimes == null) || ("null".equals(this.openTimes))) {
/* 256 */       return Integer.valueOf(0);
/*     */     }
/* 258 */     return this.openTimes;
/*     */   }
/*     */   public void setOpenTimes(Integer openTimes) {
/* 261 */     this.openTimes = openTimes;
/*     */   }
/*     */   public String getExtend1() {
/* 264 */     return StringUtils.nullStr(this.extend1);
/*     */   }
/*     */   public void setExtend1(String extend1) {
/* 267 */     this.extend1 = extend1;
/*     */   }
/*     */   public String getExtend2() {
/* 270 */     return StringUtils.nullStr(this.extend2);
/*     */   }
/*     */   public void setExtend2(String extend2) {
/* 273 */     this.extend2 = extend2;
/*     */   }
/*     */   public String getExtend3() {
/* 276 */     return StringUtils.nullStr(this.extend3);
/*     */   }
/*     */   public void setExtend3(String extend3) {
/* 279 */     this.extend3 = extend3;
/*     */   }
/*     */   public Set<FileCommentary> getFileCommentaries() {
/* 282 */     return this.fileCommentaries;
/*     */   }
/*     */   public void setFileCommentaries(Set<FileCommentary> fileCommentaries) {
/* 285 */     this.fileCommentaries = fileCommentaries;
/*     */   }
/*     */   public FileMetaDoc getFileMetaDoc() {
/* 288 */     return this.fileMetaDoc;
/*     */   }
/*     */   public void setFileMetaDoc(FileMetaDoc fileMetaDoc) {
/* 291 */     this.fileMetaDoc = fileMetaDoc;
/*     */   }
/*     */   public FileMetaImage getFileMetaImage() {
/* 294 */     return this.fileMetaImage;
/*     */   }
/*     */   public void setFileMetaImage(FileMetaImage fileMetaImage) {
/* 297 */     this.fileMetaImage = fileMetaImage;
/*     */   }
/*     */   public FileMetaVideo getFileMetaVideo() {
/* 300 */     return this.fileMetaVideo;
/*     */   }
/*     */   public void setFileMetaVideo(FileMetaVideo fileMetaVideo) {
/* 303 */     this.fileMetaVideo = fileMetaVideo;
/*     */   }
/*     */   public FileMetaAudio getFileMetaAudio() {
/* 306 */     return this.fileMetaAudio;
/*     */   }
/*     */   public void setFileMetaAudio(FileMetaAudio fileMetaAudio) {
/* 309 */     this.fileMetaAudio = fileMetaAudio;
/*     */   }
/*     */ 
/*     */   public Set<FileShare> getFileShares() {
/* 313 */     return this.fileShares;
/*     */   }
/*     */   public void setFileShares(Set<FileShare> fileShares) {
/* 316 */     this.fileShares = fileShares;
/*     */   }
/*     */   public Set<FileAttachment> getFileAttachments() {
/* 319 */     return this.fileAttachments;
/*     */   }
/*     */   public void setFileAttachments(Set<FileAttachment> fileAttachments) {
/* 322 */     this.fileAttachments = fileAttachments;
/*     */   }
/*     */   public Set<FileFavorites> getFileFavoritesAlls() {
/* 325 */     return this.fileFavoritesAlls;
/*     */   }
/*     */   public void setFileFavoritesAlls(Set<FileFavorites> fileFavoritesAlls) {
/* 328 */     this.fileFavoritesAlls = fileFavoritesAlls;
/*     */   }
/*     */   public Set<FileOpenHistory> getFileOpenHistories() {
/* 331 */     return this.fileOpenHistories;
/*     */   }
/*     */   public void setFileOpenHistories(Set<FileOpenHistory> fileOpenHistories) {
/* 334 */     this.fileOpenHistories = fileOpenHistories;
/*     */   }
/*     */   public String getDepartmentUid() {
/* 337 */     return this.departmentUid;
/*     */   }
/*     */   public void setDepartmentUid(String departmentUid) {
/* 340 */     this.departmentUid = departmentUid;
/*     */   }
/*     */   public String getDepartmentName() {
/* 343 */     return this.departmentName;
/*     */   }
/*     */   public void setDepartmentName(String departmentName) {
/* 346 */     this.departmentName = departmentName;
/*     */   }
/*     */   public FileOfficialDocument getFod() {
/* 349 */     return this.fod;
/*     */   }
/*     */   public void setFod(FileOfficialDocument fod) {
/* 352 */     this.fod = fod;
/*     */   }
/*     */   public String getCreatorName() {
/* 355 */     return StringUtils.nullStr(this.creatorName);
/*     */   }
/*     */   public void setCreatorName(String creatorName) {
/* 358 */     this.creatorName = creatorName;
/*     */   }
/*     */   public String getYear() {
/* 361 */     return this.year;
/*     */   }
/*     */   public void setYear(String year) {
/* 364 */     this.year = year;
/*     */   }
/*     */   public String getKind() {
/* 367 */     return StringUtils.nullStr(this.kind);
/*     */   }
/*     */   public void setKind(String kind) {
/* 370 */     this.kind = kind;
/*     */   }
/*     */   public String getResourceUid() {
/* 373 */     return this.resourceUid;
/*     */   }
/*     */   public void setResourceUid(String resourceUid) {
/* 376 */     this.resourceUid = resourceUid;
/*     */   }
/*     */   public String getImportUid() {
/* 379 */     return this.importUid;
/*     */   }
/*     */   public void setImportUid(String importUid) {
/* 382 */     this.importUid = importUid;
/*     */   }
/*     */   public String getLastModifiedUserUid() {
/* 385 */     return StringUtils.nullStr(this.lastModifiedUserUid);
/*     */   }
/*     */   public void setLastModifiedUserUid(String lastModifiedUserUid) {
/* 388 */     this.lastModifiedUserUid = lastModifiedUserUid;
/*     */   }
/*     */   public String getItemType() {
/* 391 */     return this.itemType;
/*     */   }
/*     */   public void setItemType(String itemType) {
/* 394 */     this.itemType = itemType;
/*     */   }
/*     */   public boolean isZipDoc() {
/* 397 */     return this.zipDoc;
/*     */   }
/*     */   public void setZipDoc(boolean zipDoc) {
/* 400 */     this.zipDoc = zipDoc;
/*     */   }
/*     */   public String getUrl() {
/* 403 */     return this.url;
/*     */   }
/*     */   public void setUrl(String url) {
/* 406 */     this.url = url;
/*     */   }
/*     */   public String getExtendFolderUids() {
/* 409 */     return this.extendFolderUids;
/*     */   }
/*     */   public void setExtendFolderUids(String extendFolderUids) {
/* 412 */     this.extendFolderUids = extendFolderUids;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileInfo
 * JD-Core Version:    0.6.1
 */