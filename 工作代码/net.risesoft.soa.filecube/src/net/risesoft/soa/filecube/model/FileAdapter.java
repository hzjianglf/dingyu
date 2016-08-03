/*     */ package net.risesoft.soa.filecube.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="FC_Adapter")
/*     */ public class FileAdapter
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   private String adapterUid;
/*     */   private String runType;
/*     */   private String autoPeriod;
/*     */   private String description;
/*     */   private String importerClasName;
/*     */   private String name;
/*     */   private String parameters;
/*     */   private String adapterType;
/*     */   private String status;
/*     */ 
/*     */   @OneToMany(mappedBy="adapter", fetch=FetchType.LAZY)
/*     */   private Set<FileInfo> fileinfos;
/*     */ 
/*     */   @OneToMany(mappedBy="adapter", fetch=FetchType.LAZY)
/*     */   private Set<FileAdapterLog> fileAdapterLogs;
/*     */ 
/*     */   @OneToMany(mappedBy="adapter", fetch=FetchType.LAZY)
/*     */   private Set<FileAdapterFolder> fileAdapterFolders;
/*     */ 
/*     */   public String getAdapterUid()
/*     */   {
/*  32 */     return this.adapterUid;
/*     */   }
/*     */   public void setAdapterUid(String adapterUid) {
/*  35 */     this.adapterUid = adapterUid;
/*     */   }
/*     */   public String getRunType() {
/*  38 */     return this.runType;
/*     */   }
/*     */   public void setRunType(String runType) {
/*  41 */     this.runType = runType;
/*     */   }
/*     */   public String getAutoPeriod() {
/*  44 */     return this.autoPeriod;
/*     */   }
/*     */   public void setAutoPeriod(String autoPeriod) {
/*  47 */     this.autoPeriod = autoPeriod;
/*     */   }
/*     */   public String getDescription() {
/*  50 */     return this.description;
/*     */   }
/*     */   public void setDescription(String description) {
/*  53 */     this.description = description;
/*     */   }
/*     */   public String getImporterClasName() {
/*  56 */     return this.importerClasName;
/*     */   }
/*     */   public void setImporterClasName(String importerClasName) {
/*  59 */     this.importerClasName = importerClasName;
/*     */   }
/*     */   public String getName() {
/*  62 */     return this.name;
/*     */   }
/*     */   public void setName(String name) {
/*  65 */     this.name = name;
/*     */   }
/*     */   public String getParameters() {
/*  68 */     return this.parameters;
/*     */   }
/*     */   public void setParameters(String parameters) {
/*  71 */     this.parameters = parameters;
/*     */   }
/*     */   public String getAdapterType() {
/*  74 */     return this.adapterType;
/*     */   }
/*     */   public void setAdapterType(String adapterType) {
/*  77 */     this.adapterType = adapterType;
/*     */   }
/*     */   public String getStatus() {
/*  80 */     return this.status;
/*     */   }
/*     */   public void setStatus(String status) {
/*  83 */     this.status = status;
/*     */   }
/*     */   public Set<FileInfo> getFileinfos() {
/*  86 */     return this.fileinfos;
/*     */   }
/*     */   public void setFileinfos(Set<FileInfo> fileinfos) {
/*  89 */     this.fileinfos = fileinfos;
/*     */   }
/*     */   public Set<FileAdapterLog> getFileAdapterLogs() {
/*  92 */     return this.fileAdapterLogs;
/*     */   }
/*     */   public void setFileAdapterLogs(Set<FileAdapterLog> fileAdapterLogs) {
/*  95 */     this.fileAdapterLogs = fileAdapterLogs;
/*     */   }
/*     */   public Set<FileAdapterFolder> getFileAdapterFolders() {
/*  98 */     return this.fileAdapterFolders;
/*     */   }
/*     */   public void setFileAdapterFolders(Set<FileAdapterFolder> fileAdapterFolders) {
/* 101 */     this.fileAdapterFolders = fileAdapterFolders;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileAdapter
 * JD-Core Version:    0.6.1
 */