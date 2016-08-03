/*    */ package net.risesoft.soa.filecube.util;
/*    */ 
/*    */ import com.artofsolving.jodconverter.DocumentConverter;
/*    */ import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
/*    */ import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
/*    */ import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
/*    */ import java.io.File;
/*    */ import java.net.ConnectException;
/*    */ 
/*    */ public class DocumentToPDF extends Thread
/*    */ {
/*    */   private File sourceFile;
/*    */   private File destFile;
/* 17 */   private GlobalInfo globalInfo = GlobalInfo.getInstance();
/*    */ 
/* 19 */   public DocumentToPDF(File sourceFile, File destFile) { this.sourceFile = sourceFile;
/* 20 */     this.destFile = destFile;
/*    */   }
/*    */ 
/*    */   public void convert()
/*    */   {
/* 25 */     OpenOfficeConnection connection = new SocketOpenOfficeConnection(
/* 26 */       this.globalInfo.getOpenOfficeIP(), this.globalInfo.getOpenOfficePort());
/*    */     try
/*    */     {
/* 29 */       connection.connect();
/*    */ 
/* 31 */       DocumentConverter converter = new OpenOfficeDocumentConverter(
/* 32 */         connection);
/* 33 */       converter.convert(this.sourceFile, this.destFile);
/*    */     }
/*    */     catch (ConnectException e) {
/* 36 */       e.printStackTrace();
/*    */     }
/*    */     finally {
/* 39 */       if (connection != null) {
/* 40 */         connection.disconnect();
/* 41 */         connection = null;
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public File getSourceFile()
/*    */   {
/* 48 */     return this.sourceFile;
/*    */   }
/*    */ 
/*    */   public void setSourceFile(File sourceFile) {
/* 52 */     this.sourceFile = sourceFile;
/*    */   }
/*    */ 
/*    */   public File getDestFile() {
/* 56 */     return this.destFile;
/*    */   }
/*    */ 
/*    */   public void setDestFile(File destFile) {
/* 60 */     this.destFile = destFile;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 65 */     convert();
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.DocumentToPDF
 * JD-Core Version:    0.6.1
 */