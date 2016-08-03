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
/*    */ @Table(name="FC_Adapter_Log")
/*    */ public class FileAdapterLog
/*    */   implements Serializable
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   private String adapterLogUid;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="adapterUid", referencedColumnName="adapterUid")
/*    */   private FileAdapter adapter;
/*    */   private String logContent;
/*    */   private Date logTime;
/*    */ 
/*    */   public String getAdapterLogUid()
/*    */   {
/* 26 */     return this.adapterLogUid;
/*    */   }
/*    */   public void setAdapterLogUid(String adapterLogUid) {
/* 29 */     this.adapterLogUid = adapterLogUid;
/*    */   }
/*    */   public FileAdapter getAdapter() {
/* 32 */     return this.adapter;
/*    */   }
/*    */   public void setAdapter(FileAdapter adapter) {
/* 35 */     this.adapter = adapter;
/*    */   }
/*    */   public String getLogContent() {
/* 38 */     return this.logContent;
/*    */   }
/*    */   public void setLogContent(String logContent) {
/* 41 */     this.logContent = logContent;
/*    */   }
/*    */   public Date getLogTime() {
/* 44 */     return this.logTime;
/*    */   }
/*    */   public void setLogTime(Date logTime) {
/* 47 */     this.logTime = logTime;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.FileAdapterLog
 * JD-Core Version:    0.6.1
 */