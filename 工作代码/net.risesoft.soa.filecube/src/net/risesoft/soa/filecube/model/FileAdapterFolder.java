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
/*    */ @Table(name="FC_Adapter_Folder")
/*    */ public class FileAdapterFolder
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String adapterInstanceUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="folderUid", referencedColumnName="folderUid")
/*    */   private FileFolder fileFolder;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="adapterUid", referencedColumnName="adapterUid")
/*    */   private FileAdapter adapter;
/*    */   private Date xpath;
/*    */ 
/*    */   public String getAdapterInstanceUid()
/*    */   {
/* 28 */     return this.adapterInstanceUid;
/*    */   }
/*    */   public void setAdapterInstanceUid(String adapterInstanceUid) {
/* 31 */     this.adapterInstanceUid = adapterInstanceUid;
/*    */   }
/*    */   public FileFolder getFileFolder() {
/* 34 */     return this.fileFolder;
/*    */   }
/*    */   public void setFileFolder(FileFolder fileFolder) {
/* 37 */     this.fileFolder = fileFolder;
/*    */   }
/*    */   public FileAdapter getAdapter() {
/* 40 */     return this.adapter;
/*    */   }
/*    */   public void setAdapter(FileAdapter adapter) {
/* 43 */     this.adapter = adapter;
/*    */   }
/*    */   public Date getXpath() {
/* 46 */     return this.xpath;
/*    */   }
/*    */   public void setXpath(Date xpath) {
/* 49 */     this.xpath = xpath;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileAdapterFolder
 * JD-Core Version:    0.6.1
 */