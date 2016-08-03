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
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_commentary")
/*    */ public class FileCommentary
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String commentaryUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */   private String userUid;
/*    */   private String userName;
/*    */   private float score;
/*    */   private String content;
/*    */   private Date createDate;
/*    */ 
/*    */   @OneToMany(mappedBy="commentary", fetch=FetchType.EAGER)
/*    */   public String getCommentaryUid()
/*    */   {
/* 33 */     return this.commentaryUid;
/*    */   }
/*    */   public void setCommentaryUid(String commentaryUid) {
/* 36 */     this.commentaryUid = commentaryUid;
/*    */   }
/*    */   public String getUserUid() {
/* 39 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 42 */     this.userUid = userUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 45 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 48 */     this.fileInfo = fileInfo;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 52 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 55 */     this.content = content;
/*    */   }
/*    */   public Date getCreateDate() {
/* 58 */     return this.createDate;
/*    */   }
/*    */   public void setCreateDate(Date createDate) {
/* 61 */     this.createDate = createDate;
/*    */   }
/*    */   public float getScore() {
/* 64 */     return this.score;
/*    */   }
/*    */   public void setScore(float score) {
/* 67 */     this.score = score;
/*    */   }
/*    */   public String getUserName() {
/* 70 */     return this.userName;
/*    */   }
/*    */   public void setUserName(String userName) {
/* 73 */     this.userName = userName;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileCommentary
 * JD-Core Version:    0.6.1
 */