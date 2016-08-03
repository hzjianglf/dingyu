/*     */ package net.risesoft.soa.filecube.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PDFToSWF
/*     */ {
/*  13 */   private static final Logger log = LoggerFactory.getLogger(DocumentToSWF.class);
/*  14 */   private static final String OS = SystemInfo.OS_NAME;
/*     */   private String fileFullName;
/*     */   private File swfFile;
/*     */   private File pdfFile;
/*  28 */   private GlobalInfo globalInfo = GlobalInfo.getInstance();
/*     */ 
/*     */   public PDFToSWF(String fileFullName)
/*     */   {
/*  22 */     this.fileFullName = fileFullName;
/*  23 */     String filePathName = fileFullName.substring(0, fileFullName.lastIndexOf("."));
/*  24 */     this.pdfFile = new File(fileFullName);
/*  25 */     this.swfFile = new File(filePathName + ".swf");
/*     */   }
/*     */ 
/*     */   private void pdfToswf()
/*     */     throws Exception
/*     */   {
/*  33 */     Runtime r = Runtime.getRuntime();
/*  34 */     if (!this.swfFile.exists()) {
/*  35 */       if (this.pdfFile.exists()) {
/*  36 */         if (OS.toLowerCase().contains("window"))
/*     */           try
/*     */           {
/*  39 */             StringBuilder sb = new StringBuilder();
/*     */ 
/*  41 */             String swfToolsHome = this.globalInfo.getSwfToolsHome();
/*  42 */             if (swfToolsHome.charAt(swfToolsHome.length() - 1) != '/') {
/*  43 */               swfToolsHome = swfToolsHome + '/';
/*     */             }
/*  45 */             sb.append(swfToolsHome + "pdf2swf ");
/*     */ 
/*  47 */             sb.append(this.pdfFile.getPath());
/*     */ 
/*  49 */             sb.append(" -o ");
/*     */ 
/*  51 */             sb.append(this.swfFile.getPath() + " ");
/*     */ 
/*  53 */             sb.append(this.globalInfo.getSwfToolsCMDSParam());
/*     */ 
/*  55 */             log.info("=======转换命令：" + sb.toString() + "=======");
/*  56 */             Process p = Runtime.getRuntime().exec(sb.toString());
/*     */ 
/*  58 */             loadStream(p.getInputStream());
/*  59 */             loadStream(p.getErrorStream());
/*  60 */             log.info("=======swf转换成功，文件输出：" + this.swfFile.getPath() + "=======");
/*     */           }
/*     */           catch (Exception e) {
/*  63 */             e.printStackTrace();
/*  64 */             throw e;
/*     */           }
/*     */         else
/*     */           try {
/*  68 */             Process p = r.exec("pdf2swf " + 
/*  69 */               this.pdfFile.getPath() + " -o " + this.swfFile.getPath() + " -T 9 " + this.globalInfo.getSwfToolsCMDSParam());
/*  70 */             loadStream(p.getInputStream());
/*  71 */             loadStream(p.getErrorStream());
/*  72 */             log.info("=======swf转换成功，文件输出：" + this.swfFile.getPath() + "=======");
/*     */           } catch (Exception e) {
/*  74 */             e.printStackTrace();
/*  75 */             throw e;
/*     */           }
/*     */       }
/*     */       else
/*  79 */         log.error("=======pdf不存在，无法转换=======");
/*     */     }
/*     */     else
/*  82 */       log.info("=======swf已存在不需要转换=======");
/*     */   }
/*     */ 
/*     */   private static void loadStream(InputStream in) throws IOException
/*     */   {
/*  87 */     in = new BufferedInputStream(in);
/*  88 */     while (in.read() != -1);
/*     */   }
/*     */ 
/*     */   public boolean convert()
/*     */   {
/*  96 */     if (OS.toLowerCase().contains("windows"))
/*  97 */       log.info("=======swf转换器开始工作，当前设置运行环境windows=======");
/*     */     else
/*  99 */       log.info("=======swf转换器开始工作，当前设置运行环境linux=======");
/*     */     try
/*     */     {
/* 102 */       pdfToswf();
/*     */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       return false;
/*     */     }
/* 107 */     if (this.swfFile.exists()) {
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public void setOutputPath(String outputPath)
/*     */   {
/* 118 */     if (!new File(outputPath).exists()) {
/* 119 */       new File(outputPath).mkdirs();
/*     */     }
/* 121 */     if (!outputPath.equals("")) {
/* 122 */       String fileName = this.fileFullName.substring(this.fileFullName.lastIndexOf("/"), 
/* 123 */         this.fileFullName.lastIndexOf("."));
/* 124 */       if (outputPath.charAt(outputPath.length()) == '/')
/* 125 */         this.swfFile = new File(outputPath + fileName + ".swf");
/*     */       else
/* 127 */         this.swfFile = new File(outputPath + fileName + ".swf");
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.PDFToSWF
 * JD-Core Version:    0.6.1
 */