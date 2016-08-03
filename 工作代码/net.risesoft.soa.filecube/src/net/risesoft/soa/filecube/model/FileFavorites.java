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
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="FC_Favorites")
/*    */ public class FileFavorites
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String favoritesUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="fileUid", referencedColumnName="fileUid")
/*    */   private FileInfo fileInfo;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="folderUid", referencedColumnName="folderUid")
/*    */   private FileFolder fileFolder;
/*    */   private Date favoriteDate;
/*    */   private String userUid;
/*    */ 
/*    */   public String getFavoritesUid()
/*    */   {
/* 29 */     return this.favoritesUid;
/*    */   }
/*    */   public void setFavoritesUid(String favoritesUid) {
/* 32 */     this.favoritesUid = favoritesUid;
/*    */   }
/*    */   public FileInfo getFileInfo() {
/* 35 */     return this.fileInfo;
/*    */   }
/*    */   public void setFileInfo(FileInfo fileInfo) {
/* 38 */     this.fileInfo = fileInfo;
/*    */   }
/*    */   public FileFolder getFileFolder() {
/* 41 */     return this.fileFolder;
/*    */   }
/*    */   public void setFileFolder(FileFolder fileFolder) {
/* 44 */     this.fileFolder = fileFolder;
/*    */   }
/*    */   public Date getFavoriteDate() {
/* 47 */     return this.favoriteDate;
/*    */   }
/*    */   public void setFavoriteDate(Date favoriteDate) {
/* 50 */     this.favoriteDate = favoriteDate;
/*    */   }
/*    */   public String getUserUid() {
/* 53 */     return this.userUid;
/*    */   }
/*    */   public void setUserUid(String userUid) {
/* 56 */     this.userUid = userUid;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileFavorites
 * JD-Core Version:    0.6.1
 */