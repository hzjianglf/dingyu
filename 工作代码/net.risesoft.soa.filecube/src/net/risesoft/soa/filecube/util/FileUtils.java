/*     */ package net.risesoft.soa.filecube.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class FileUtils
/*     */ {
/*  18 */   private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
/*     */   private static final int BUFFER = 1024;
/*     */ 
/*     */   public static byte[] fileContentBytes(String filePath)
/*     */   {
/*  28 */     FileInputStream fis = null;
/*     */     try {
/*  30 */       fis = new FileInputStream(filePath);
/*     */     } catch (FileNotFoundException e) {
/*  32 */       log.error("获取文件byte数组出现异常！", e);
/*     */       try
/*     */       {
/*  35 */         if (fis != null)
/*  36 */           fis.close();
/*     */       }
/*     */       catch (IOException e) {
/*  39 */         log.error("获取文件byte数组出现异常！", e);
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  35 */         if (fis != null)
/*  36 */           fis.close();
/*     */       }
/*     */       catch (IOException e) {
/*  39 */         log.error("获取文件byte数组出现异常！", e);
/*     */       }
/*     */     }
/*     */ 
/*  43 */     return fileContentBytes(fis);
/*     */   }
/*     */ 
/*     */   public static byte[] fileContentBytes(InputStream is)
/*     */   {
/*  51 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     try {
/*  53 */       BufferedInputStream bis = new BufferedInputStream(is);
/*  54 */       byte[] buffer = new byte[1024];
/*     */       int count;
/*  56 */       while ((count = bis.read(buffer, 0, 1024)) != -1)
/*     */       {
/*     */         int count;
/*  57 */         baos.write(buffer, 0, count);
/*     */       }
/*  59 */       bis.close();
/*     */     } catch (FileNotFoundException e) {
/*  61 */       log.error("获取文件byte数组出现异常！", e);
/*     */     } catch (IOException e) {
/*  63 */       log.error("获取文件byte数组出现异常！", e);
/*     */     }
/*  65 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */   public static void extractZipFile(String filePath)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       BufferedOutputStream dest = null;
/*  74 */       File zipFile = new File(filePath);
/*  75 */       FileInputStream fis = new FileInputStream(zipFile);
/*  76 */       ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
/*     */ 
/*  79 */       String outPath = zipFile.getParent() + "/" + 
/*  80 */         zipFile.getName().substring(0, zipFile.getName().lastIndexOf(".")) + "/";
/*     */ 
/*  82 */       File outFile = new File(outPath);
/*  83 */       if (!outFile.exists())
/*  84 */         outFile.mkdir();
/*     */       ZipEntry entry;
/*  86 */       while ((entry = zis.getNextEntry()) != null)
/*     */       {
/*     */         ZipEntry entry;
/*  87 */         System.out.println("正在解压: " + entry);
/*     */ 
/*  89 */         byte[] buffer = new byte[1024];
/*  90 */         FileOutputStream fos = new FileOutputStream(outPath + entry.getName());
/*     */ 
/*  92 */         dest = new BufferedOutputStream(fos, 1024);
/*     */         int count;
/*  93 */         while ((count = zis.read(buffer, 0, 1024)) != -1)
/*     */         {
/*     */           int count;
/*  94 */           dest.write(buffer, 0, count);
/*     */         }
/*  96 */         dest.flush();
/*  97 */         dest.close();
/*     */       }
/*  99 */       System.out.println("解压完成！");
/* 100 */       zis.close();
/*     */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static byte[] zipEntityBytes(String filePath, String entryName)
/*     */   {
/*     */     try
/*     */     {
/* 114 */       FileInputStream fis = new FileInputStream(filePath);
/* 115 */       return zipEntityBytes(fis, entryName);
/*     */     } catch (FileNotFoundException e) {
/* 117 */       log.error("获取zip文件中指定的文件的byte数组出现异常！", e);
/*     */     }
/*     */ 
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public static byte[] zipEntityBytes(InputStream is, String entryName)
/*     */   {
/*     */     try
/*     */     {
/* 130 */       ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
/*     */       ZipEntry entry;
/* 132 */       while ((entry = zis.getNextEntry()) != null)
/*     */       {
/*     */         ZipEntry entry;
/* 133 */         if (entry.getName().equals(entryName)) {
/* 134 */           return fileContentBytes(zis);
/*     */         }
/*     */       }
/* 137 */       zis.closeEntry();
/* 138 */       zis.close();
/*     */     } catch (FileNotFoundException e) {
/* 140 */       log.error("获取zip文件中指定的文件的byte数组出现异常！", e);
/*     */     } catch (IOException e) {
/* 142 */       log.error("获取zip文件中指定的文件的byte数组出现异常！", e);
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 148 */     String filePath = "e:/{09A4AF9B-FFFF-FFFF-997B-C52A000140DD}.1.1.9月6日征求意见函.doc.zip";
/* 149 */     byte[] datas = zipEntityBytes(filePath, "risedata");
/*     */     try {
/* 151 */       FileOutputStream fos = new FileOutputStream("e:/qq.doc");
/* 152 */       BufferedOutputStream br = new BufferedOutputStream(fos);
/* 153 */       br.write(datas);
/*     */     } catch (FileNotFoundException e) {
/* 155 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/* 157 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.FileUtils
 * JD-Core Version:    0.6.1
 */