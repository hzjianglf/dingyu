/*     */ package net.risesoft.soa.filecube.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.OneToOne;
/*     */ import javax.persistence.Table;
/*     */ import net.risesoft.soa.filecube.util.StringUtils;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="FC_Official_document")
/*     */ public class FileOfficialDocument
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   private String odUid;
/*     */ 
/*     */   @OneToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*     */   private FileInfo fileInfo;
/*     */   private String fileNumber;
/*     */   private String keyWord;
/*     */   private String year;
/*     */   private String kind;
/*     */   private String departmentName;
/*     */   private String departmentUid;
/*     */   private Date issueDate;
/*     */   private String enmergency;
/*     */   private String secretLevel;
/*     */   private String secretLife;
/*     */   private Integer pagination;
/*     */   private String underSigned;
/*     */   private String unitSender;
/*     */   private String mainReciever;
/*     */   private String copyReciever;
/*     */   private String issuedAuthority;
/*     */   private Integer copies;
/*     */   private Date issuedDate;
/*     */   private Date submittedDate;
/*     */   private String handlingUnits;
/*     */   private Date processedLife;
/*     */   private String transactor;
/*     */   private String extend1;
/*     */   private String extend2;
/*     */   private String extend3;
/*     */ 
/*     */   public String getOdUid()
/*     */   {
/*  75 */     return this.odUid;
/*     */   }
/*     */   public void setOdUid(String odUid) {
/*  78 */     this.odUid = odUid;
/*     */   }
/*     */   public FileInfo getFileInfo() {
/*  81 */     return this.fileInfo;
/*     */   }
/*     */   public void setFileInfo(FileInfo fileInfo) {
/*  84 */     this.fileInfo = fileInfo;
/*     */   }
/*     */   public String getFileNumber() {
/*  87 */     return this.fileNumber;
/*     */   }
/*     */   public void setFileNumber(String fileNumber) {
/*  90 */     this.fileNumber = fileNumber;
/*     */   }
/*     */   public String getKeyWord() {
/*  93 */     return StringUtils.nullStr(this.keyWord);
/*     */   }
/*     */   public void setKeyWord(String keyWord) {
/*  96 */     this.keyWord = keyWord;
/*     */   }
/*     */   public String getKind() {
/*  99 */     return StringUtils.nullStr(this.kind);
/*     */   }
/*     */   public void setKind(String kind) {
/* 102 */     this.kind = kind;
/*     */   }
/*     */   public Date getIssueDate() {
/* 105 */     return this.issueDate;
/*     */   }
/*     */   public void setIssueDate(Date issueDate) {
/* 108 */     this.issueDate = issueDate;
/*     */   }
/*     */   public String getEnmergency() {
/* 111 */     return StringUtils.nullStr(this.enmergency);
/*     */   }
/*     */   public void setEnmergency(String enmergency) {
/* 114 */     this.enmergency = enmergency;
/*     */   }
/*     */   public String getSecretLevel() {
/* 117 */     return StringUtils.nullStr(this.secretLevel);
/*     */   }
/*     */   public void setSecretLevel(String secretLevel) {
/* 120 */     this.secretLevel = secretLevel;
/*     */   }
/*     */   public String getSecretLife() {
/* 123 */     return StringUtils.nullStr(this.secretLife);
/*     */   }
/*     */   public void setSecretLife(String secretLife) {
/* 126 */     this.secretLife = secretLife;
/*     */   }
/*     */   public Integer getPagination() {
/* 129 */     return this.pagination;
/*     */   }
/*     */   public void setPagination(Integer pagination) {
/* 132 */     this.pagination = pagination;
/*     */   }
/*     */   public Integer getCopies() {
/* 135 */     return this.copies;
/*     */   }
/*     */   public void setCopies(Integer copies) {
/* 138 */     this.copies = copies;
/*     */   }
/*     */   public String getUnderSigned() {
/* 141 */     return StringUtils.nullStr(this.underSigned);
/*     */   }
/*     */   public void setUnderSigned(String underSigned) {
/* 144 */     this.underSigned = underSigned;
/*     */   }
/*     */   public String getUnitSender() {
/* 147 */     return StringUtils.nullStr(this.unitSender);
/*     */   }
/*     */   public void setUnitSender(String unitSender) {
/* 150 */     this.unitSender = unitSender;
/*     */   }
/*     */   public String getMainReciever() {
/* 153 */     return StringUtils.nullStr(this.mainReciever);
/*     */   }
/*     */   public void setMainReciever(String mainReciever) {
/* 156 */     this.mainReciever = mainReciever;
/*     */   }
/*     */   public String getCopyReciever() {
/* 159 */     return StringUtils.nullStr(this.copyReciever);
/*     */   }
/*     */   public void setCopyReciever(String copyReciever) {
/* 162 */     this.copyReciever = copyReciever;
/*     */   }
/*     */   public String getIssuedAuthority() {
/* 165 */     return StringUtils.nullStr(this.issuedAuthority);
/*     */   }
/*     */   public void setIssuedAuthority(String issuedAuthority) {
/* 168 */     this.issuedAuthority = issuedAuthority;
/*     */   }
/*     */   public Date getIssuedDate() {
/* 171 */     return this.issuedDate;
/*     */   }
/*     */   public void setIssuedDate(Date issuedDate) {
/* 174 */     this.issuedDate = issuedDate;
/*     */   }
/*     */   public Date getSubmittedDate() {
/* 177 */     return this.submittedDate;
/*     */   }
/*     */   public void setSubmittedDate(Date submittedDate) {
/* 180 */     this.submittedDate = submittedDate;
/*     */   }
/*     */   public String getHandlingUnits() {
/* 183 */     return StringUtils.nullStr(this.handlingUnits);
/*     */   }
/*     */   public void setHandlingUnits(String handlingUnits) {
/* 186 */     this.handlingUnits = handlingUnits;
/*     */   }
/*     */   public Date getProcessedLife() {
/* 189 */     return this.processedLife;
/*     */   }
/*     */   public void setProcessedLife(Date processedLife) {
/* 192 */     this.processedLife = processedLife;
/*     */   }
/*     */   public String getTransactor() {
/* 195 */     return StringUtils.nullStr(this.transactor);
/*     */   }
/*     */   public void setTransactor(String transactor) {
/* 198 */     this.transactor = transactor;
/*     */   }
/*     */   public String getExtend1() {
/* 201 */     return StringUtils.nullStr(this.extend1);
/*     */   }
/*     */   public void setExtend1(String extend1) {
/* 204 */     this.extend1 = extend1;
/*     */   }
/*     */   public String getExtend2() {
/* 207 */     return StringUtils.nullStr(this.extend2);
/*     */   }
/*     */   public void setExtend2(String extend2) {
/* 210 */     this.extend2 = extend2;
/*     */   }
/*     */   public String getExtend3() {
/* 213 */     return StringUtils.nullStr(this.extend3);
/*     */   }
/*     */   public void setExtend3(String extend3) {
/* 216 */     this.extend3 = extend3;
/*     */   }
/*     */   public String getYear() {
/* 219 */     return this.year;
/*     */   }
/*     */   public void setYear(String year) {
/* 222 */     this.year = year;
/*     */   }
/*     */   public String getDepartmentName() {
/* 225 */     return StringUtils.nullStr(this.departmentName);
/*     */   }
/*     */   public void setDepartmentName(String departmentName) {
/* 228 */     this.departmentName = departmentName;
/*     */   }
/*     */   public String getDepartmentUid() {
/* 231 */     return StringUtils.nullStr(this.departmentUid);
/*     */   }
/*     */   public void setDepartmentUid(String departmentUid) {
/* 234 */     this.departmentUid = departmentUid;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileOfficialDocument
 * JD-Core Version:    0.6.1
 */