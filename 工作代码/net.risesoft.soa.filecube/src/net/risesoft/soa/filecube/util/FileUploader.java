/*     */ package net.risesoft.soa.filecube.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.tika.metadata.Metadata;
/*     */ import org.apache.tika.parser.AutoDetectParser;
/*     */ import org.apache.tika.parser.ParseContext;
/*     */ import org.apache.tika.parser.Parser;
/*     */ import org.apache.tika.sax.BodyContentHandler;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.xml.sax.ContentHandler;
/*     */ 
/*     */ public class FileUploader
/*     */ {
/*  22 */   private static final Logger log = LoggerFactory.getLogger(FileUploader.class);
/*     */ 
/*     */   public static void createFile(String pathName, InputStream fis)
/*     */   {
/*  29 */     FileOutputStream fos = null;
/*     */     try {
/*  31 */       fos = new FileOutputStream(pathName);
/*  32 */       IOUtils.copy(fis, fos);
/*     */     } catch (FileNotFoundException e) {
/*  34 */       log.error("创建文件失败！", e);
/*     */       try
/*     */       {
/*  40 */         if (fis != null) {
/*  41 */           fis.close();
/*     */         }
/*  43 */         if (fos != null)
/*  44 */           fos.close();
/*     */       }
/*     */       catch (IOException e) {
/*  47 */         log.error("创建文件失败！", e);
/*     */       }
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*  36 */       log.error("创建文件失败！", e);
/*     */       try
/*     */       {
/*  40 */         if (fis != null) {
/*  41 */           fis.close();
/*     */         }
/*  43 */         if (fos != null)
/*  44 */           fos.close();
/*     */       }
/*     */       catch (IOException e) {
/*  47 */         log.error("创建文件失败！", e);
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  40 */         if (fis != null) {
/*  41 */           fis.close();
/*     */         }
/*  43 */         if (fos != null)
/*  44 */           fos.close();
/*     */       }
/*     */       catch (IOException e) {
/*  47 */         log.error("创建文件失败！", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void convertFile(String pathName)
/*     */   {
/*  56 */     String extension = pathName.substring(pathName.lastIndexOf(".") + 1);
/*  57 */     if (pathName.endsWith(".pdf"))
/*     */     {
/*  59 */       PDFToSWF pdfToSWF = new PDFToSWF(pathName);
/*  60 */       pdfToSWF.convert();
/*  61 */     } else if (FileType.acOpenOfficeFormatFile(extension)) {
/*  62 */       DocumentToSWF documentToSWF = new DocumentToSWF(pathName);
/*  63 */       documentToSWF.docToswf();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String fileToTxt(File file)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       FileInputStream is = new FileInputStream(file);
/*  74 */       return fileToTxt(is);
/*     */     } catch (FileNotFoundException e) {
/*  76 */       e.printStackTrace();
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public static String fileToTxt(InputStream is)
/*     */   {
/*     */     try
/*     */     {
/*  88 */       Parser parser = new AutoDetectParser();
/*     */ 
/*  91 */       ContentHandler handler = new BodyContentHandler();
/*  92 */       ParseContext context = new ParseContext();
/*  93 */       context.set(Parser.class, parser);
/*  94 */       parser.parse(is, handler, new Metadata(), context);
/*     */ 
/*  98 */       return handler.toString().replaceAll("\n", "").replace("\t", "").replace(" ", "");
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       log.error("获取文件内容失败！", e);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.FileUploader
 * JD-Core Version:    0.6.1
 */