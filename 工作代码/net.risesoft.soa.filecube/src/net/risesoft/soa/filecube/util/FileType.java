/*     */ package net.risesoft.soa.filecube.util;
/*     */ 
/*     */ import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FileType
/*     */ {
/*   9 */   public static Map<String, String> TypesMap = new HashMap();
/*  10 */   public static Map<String, String> TypesCNToEnMap = new HashMap();
/*     */ 
/*  12 */   static { TypesMap.put("document", "文档");
/*  13 */     TypesMap.put("image", "图片");
/*  14 */     TypesMap.put("video", "视频");
/*  15 */     TypesMap.put("audio", "音频");
/*  16 */     TypesMap.put("other", "其它");
/*     */ 
/*  19 */     TypesCNToEnMap.put("文档", "document");
/*  20 */     TypesCNToEnMap.put("图片", "image");
/*  21 */     TypesCNToEnMap.put("视频", "video");
/*  22 */     TypesCNToEnMap.put("音频", "audio");
/*  23 */     TypesCNToEnMap.put("其它", "other");
/*     */   }
/*     */ 
/*     */   public static boolean acOpenOfficeFormatFile(String fileExtension)
/*     */   {
/*  81 */     fileExtension = fileExtension.toUpperCase();
/*  82 */     boolean rtn = true;
/*  83 */     DefaultDocumentFormatRegistry ddf = new DefaultDocumentFormatRegistry();
/*  84 */     if (ddf.getFormatByFileExtension(fileExtension) == null) {
/*  85 */       rtn = false;
/*     */     }
/*  87 */     return rtn;
/*     */   }
/*     */ 
/*     */   public static String getFileType(String fileExtension)
/*     */   {
/*  96 */     fileExtension = fileExtension.toUpperCase();
/*     */     try
/*     */     {
/*  99 */       return AUDIO.valueOf(fileExtension);
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException) {
/*     */       try {
/* 103 */         return VIDEO.valueOf(fileExtension);
/*     */       }
/*     */       catch (Exception localException1) {
/*     */         try {
/* 107 */           return DOCUMENT.valueOf(fileExtension);
/*     */         }
/*     */         catch (Exception localException2) {
/*     */           try {
/* 111 */             return IMAGE.valueOf(fileExtension); } catch (Exception localException3) {  }  } 
/*     */       }
/*     */     }
/* 113 */     return "other";
/*     */   }
/*     */ 
/*     */   public static enum AUDIO
/*     */   {
/*  56 */     MP3, WAV;
/*     */ 
/*     */     public static final String NAME_EN = "audio";
/*     */     public static final String NAME_CN = "音频";
/*     */ 
/*  59 */     public String toString() { return "audio"; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static enum DOCUMENT
/*     */   {
/*  29 */     XLS, XLSX, PPT, PPTX, DOC, DOCX, PDF, HTM, HTML, TXT, INF, RTF, ODF, WPS;
/*     */ 
/*     */     public static final String NAME_EN = "document";
/*     */     public static final String NAME_CN = "文档";
/*     */ 
/*  32 */     public String toString() { return "document"; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static enum IMAGE
/*     */   {
/*  38 */     PNG, BMP, GIF, TIF, TIFF, PCX, TGA, ICO, JPEG, JPG, PSD, EMF, DXF, SVG, WMF;
/*     */ 
/*     */     public static final String NAME_EN = "image";
/*     */     public static final String NAME_CN = "图片";
/*     */ 
/*  41 */     public String toString() { return "image"; }
/*     */ 
/*     */   }
/*     */ 
/*     */   public static enum OTHER
/*     */   {
/*     */     public static final String NAME_EN = "other";
/*     */     public static final String NAME_CN = "其它";
/*     */ 
/*     */     public String toString()
/*     */     {
/*  68 */       return "other";
/*     */     }
/*     */   }
/*     */ 
/*     */   public static enum VIDEO
/*     */   {
/*  47 */     MPEG, MPG, WMV, MP4, RMVB, AVI, MOV, FLV, F4V, VOB;
/*     */ 
/*     */     public static final String NAME_EN = "video";
/*     */     public static final String NAME_CN = "视频";
/*     */ 
/*  50 */     public String toString() { return "video"; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.FileType
 * JD-Core Version:    0.6.1
 */