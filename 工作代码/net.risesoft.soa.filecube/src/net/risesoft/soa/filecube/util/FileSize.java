/*    */ package net.risesoft.soa.filecube.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class FileSize
/*    */ {
/*    */   public static final long SIZE_BT = 1024L;
/*    */   public static final long SIZE_KB = 1048576L;
/*    */   public static final long SIZE_MB = 1073741824L;
/*    */   public static final long SIZE_GB = 1099511627776L;
/*    */   public static final long SIZE_TB = 1125899906842624L;
/*    */   public static final int SACLE = 2;
/*    */   private static long longSize;
/*    */ 
/*    */   public static String size(File file)
/*    */     throws RuntimeException, IOException
/*    */   {
/* 25 */     longSize = 0L;
/* 26 */     if ((file.exists()) && (file.isFile()))
/* 27 */       longSize = file.length();
/* 28 */     else if ((file.exists()) && (file.isDirectory()))
/* 29 */       getSize(file);
/*    */     else {
/* 31 */       throw new RuntimeException("指定文件不存在!");
/*    */     }
/* 33 */     return size(Long.valueOf(longSize));
/*    */   }
/*    */ 
/*    */   private static void getSize(File file)
/*    */     throws RuntimeException, IOException
/*    */   {
/* 43 */     File[] fileArray = file.listFiles();
/*    */ 
/* 45 */     if ((fileArray != null) && (fileArray.length != 0))
/*    */     {
/* 47 */       for (int i = 0; i < fileArray.length; i++) {
/* 48 */         File fileSI = fileArray[i];
/*    */ 
/* 50 */         if (fileSI.isDirectory())
/*    */         {
/* 52 */           getSize(fileSI);
/*    */         }
/*    */ 
/* 55 */         if (fileSI.isFile()) {
/* 56 */           longSize += fileSI.length();
/*    */         }
/*    */       }
/*    */     }
/*    */     else
/* 61 */       longSize += 0L;
/*    */   }
/*    */ 
/*    */   public static String size(Long size)
/*    */   {
/* 70 */     if ((size == null) || (size.equals("null"))) {
/* 71 */       return "0KB";
/*    */     }
/* 73 */     if ((size.longValue() >= 0L) && (size.longValue() < 1024L))
/* 74 */       return size + "B";
/* 75 */     if ((size.longValue() >= 1024L) && (size.longValue() < 1048576L))
/* 76 */       return size.longValue() / 1024L + "KB";
/* 77 */     if ((size.longValue() >= 1048576L) && (size.longValue() < 1073741824L))
/* 78 */       return size.longValue() / 1048576L + "MB";
/* 79 */     if ((size.longValue() >= 1073741824L) && (size.longValue() < 1099511627776L)) {
/* 80 */       BigDecimal longs = new BigDecimal(Double.valueOf(size).toString());
/* 81 */       BigDecimal sizeMB = new BigDecimal(Double.valueOf("1073741824").toString());
/* 82 */       String result = longs.divide(sizeMB, 2, 4).toString();
/* 83 */       return result + "GB";
/*    */     }
/* 85 */     BigDecimal longs = new BigDecimal(Double.valueOf(size).toString());
/* 86 */     BigDecimal sizeMB = new BigDecimal(Double.valueOf("1099511627776").toString());
/* 87 */     String result = longs.divide(sizeMB, 2, 4).toString();
/* 88 */     return result + "TB";
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.FileSize
 * JD-Core Version:    0.6.1
 */