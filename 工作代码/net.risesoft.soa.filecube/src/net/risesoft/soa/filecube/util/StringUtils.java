/*    */ package net.risesoft.soa.filecube.util;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*    */   public static String getShortStr(String s, int length)
/*    */   {
/* 10 */     if (s == null) return "";
/* 11 */     if ((s.length() > length) && (s.getBytes().length > s.length()))
/* 12 */       s = s.substring(0, length) + "……";
/* 13 */     else if (s.length() > 2 * length - 2) {
/* 14 */       s = s.substring(0, 2 * length - 2) + "……";
/*    */     }
/* 16 */     return s;
/*    */   }
/*    */ 
/*    */   public static String nullStr(String str)
/*    */   {
/* 24 */     if ((str == null) || (str.equals("null"))) {
/* 25 */       return null;
/*    */     }
/* 27 */     return str;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.StringUtils
 * JD-Core Version:    0.6.1
 */