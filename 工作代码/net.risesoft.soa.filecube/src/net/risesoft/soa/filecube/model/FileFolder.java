/*     */ package net.risesoft.soa.filecube.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="FC_Folder")
/*     */ public class FileFolder
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Id
/*     */   private String folderUid;
/*     */   private String importUid;
/*     */   private String name;
/*     */   private String description;
/*     */   private String resourceUid;
/*     */   private String itemType;
/*     */   private Date createDate;
/*     */   private Date modifiedDate;
/*     */   private Integer tabIndex;
/*     */   private String parentUid;
/*     */   private String creatorUid;
/*     */   private String modifiedUid;
/*     */   private Boolean deleted;
/*     */   private String deletedUid;
/*     */   private String folderLevel;
/*     */   private String url;
/*     */ 
/*     */   @OneToMany(mappedBy="fileFolder", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileFavorites> fileFavoritesAlls;
/*     */ 
/*     */   @OneToMany(mappedBy="fileFolder", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileInfo> fileInfos;
/*     */ 
/*     */   @OneToMany(mappedBy="fileFolder", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileAdapterFolder> adapterFolder;
/*     */ 
/*     */   @OneToMany(mappedBy="fileFolder", fetch=FetchType.LAZY, cascade={javax.persistence.CascadeType.ALL})
/*     */   private Set<FileShare> fileShares;
/*     */ 
/*     */   public String getFolderUid()
/*     */   {
/*  55 */     return this.folderUid;
/*     */   }
/*     */   public void setFolderUid(String folderUid) {
/*  58 */     this.folderUid = folderUid;
/*     */   }
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  64 */     this.name = name;
/*     */   }
/*     */   public String getDescription() {
/*  67 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  70 */     this.description = description;
/*     */   }
/*     */   public Date getCreateDate() {
/*  73 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/*  76 */     this.createDate = createDate;
/*     */   }
/*     */   public Date getModifiedDate() {
/*  79 */     return this.modifiedDate;
/*     */   }
/*     */   public void setModifiedDate(Date modifiedDate) {
/*  82 */     this.modifiedDate = modifiedDate;
/*     */   }
/*     */ 
/*     */   public String getParentUid() {
/*  86 */     return this.parentUid;
/*     */   }
/*     */   public void setParentUid(String parentUid) {
/*  89 */     this.parentUid = parentUid;
/*     */   }
/*     */   public String getCreatorUid() {
/*  92 */     return this.creatorUid;
/*     */   }
/*     */   public void setCreatorUid(String creatorUid) {
/*  95 */     this.creatorUid = creatorUid;
/*     */   }
/*     */   public String getModifiedUid() {
/*  98 */     return this.modifiedUid;
/*     */   }
/*     */   public void setModifiedUid(String modifiedUid) {
/* 101 */     this.modifiedUid = modifiedUid;
/*     */   }
/*     */   public String getDeletedUid() {
/* 104 */     return this.deletedUid;
/*     */   }
/*     */   public void setDeletedUid(String deletedUid) {
/* 107 */     this.deletedUid = deletedUid;
/*     */   }
/*     */   public String getUrl() {
/* 110 */     return this.url;
/*     */   }
/*     */   public void setUrl(String url) {
/* 113 */     this.url = url;
/*     */   }
/*     */   public Set<FileFavorites> getFileFavoritesAlls() {
/* 116 */     return this.fileFavoritesAlls;
/*     */   }
/*     */   public void setFileFavoritesAlls(Set<FileFavorites> fileFavoritesAlls) {
/* 119 */     this.fileFavoritesAlls = fileFavoritesAlls;
/*     */   }
/*     */   public Integer getTabIndex() {
/* 122 */     return this.tabIndex;
/*     */   }
/*     */   public void setTabIndex(Integer tabIndex) {
/* 125 */     this.tabIndex = tabIndex;
/*     */   }
/*     */   public Boolean getDeleted() {
/* 128 */     return this.deleted;
/*     */   }
/*     */   public void setDeleted(Boolean deleted) {
/* 131 */     this.deleted = deleted;
/*     */   }
/*     */   public String getFolderLevel() {
/* 134 */     return this.folderLevel;
/*     */   }
/*     */   public void setFolderLevel(String folderLevel) {
/* 137 */     this.folderLevel = folderLevel;
/*     */   }
/*     */   public Set<FileInfo> getFileInfos() {
/* 140 */     return this.fileInfos;
/*     */   }
/*     */   public void setFileInfos(Set<FileInfo> fileInfos) {
/* 143 */     this.fileInfos = fileInfos;
/*     */   }
/*     */   public Set<FileAdapterFolder> getAdapterFolder() {
/* 146 */     return this.adapterFolder;
/*     */   }
/*     */   public void setAdapterFolder(Set<FileAdapterFolder> adapterFolder) {
/* 149 */     this.adapterFolder = adapterFolder;
/*     */   }
/*     */   public String getResourceUid() {
/* 152 */     return this.resourceUid;
/*     */   }
/*     */   public void setResourceUid(String resourceUid) {
/* 155 */     this.resourceUid = resourceUid;
/*     */   }
/*     */   public Set<FileShare> getFileShares() {
/* 158 */     return this.fileShares;
/*     */   }
/*     */   public void setFileShares(Set<FileShare> fileShares) {
/* 161 */     this.fileShares = fileShares;
/*     */   }
/*     */   public String getItemType() {
/* 164 */     return this.itemType;
/*     */   }
/*     */   public void setItemType(String itemType) {
/* 167 */     this.itemType = itemType;
/*     */   }
/*     */   public String getImportUid() {
/* 170 */     return this.importUid;
/*     */   }
/*     */   public void setImportUid(String importUid) {
/* 173 */     this.importUid = importUid;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileFolder
 * JD-Core Version:    0.6.1
 */