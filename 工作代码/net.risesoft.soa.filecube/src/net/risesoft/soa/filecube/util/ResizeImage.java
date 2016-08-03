/*    */ package net.risesoft.soa.filecube.util;
/*    */ 
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.ColorModel;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.io.File;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class ResizeImage
/*    */ {
/*    */   public static BufferedImage resize(BufferedImage source, int targetW, int targetH)
/*    */   {
/* 21 */     int type = source.getType();
/* 22 */     BufferedImage target = null;
/* 23 */     double sx = targetW / source.getWidth();
/* 24 */     double sy = targetH / source.getHeight();
/*    */ 
/* 33 */     if (type == 0) {
/* 34 */       ColorModel cm = source.getColorModel();
/* 35 */       WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
/* 36 */       boolean alphaPremultiplied = cm.isAlphaPremultiplied();
/* 37 */       target = new BufferedImage(cm, raster, alphaPremultiplied, null);
/*    */     } else {
/* 39 */       target = new BufferedImage(targetW, targetH, type);
/*    */     }
/* 41 */     Graphics2D g = target.createGraphics();
/* 42 */     g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/* 43 */     g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
/* 44 */     g.dispose();
/* 45 */     return target;
/*    */   }
/*    */ 
/*    */   public static void resize(String srcFile, String targetFile, int width, int hight)
/*    */     throws Exception
/*    */   {
/* 58 */     String imgType = "JPEG";
/* 59 */     if (srcFile.toLowerCase().endsWith(".png")) {
/* 60 */       imgType = "PNG";
/*    */     }
/* 62 */     File saveFile = new File(targetFile);
/* 63 */     File fromFile = new File(srcFile);
/* 64 */     BufferedImage srcImage = ImageIO.read(fromFile);
/* 65 */     if ((width > 0) || (hight > 0)) {
/* 66 */       srcImage = resize(srcImage, width, hight);
/*    */     }
/* 68 */     ImageIO.write(srcImage, imgType, saveFile);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception {
/* 72 */     resize("E:/JavaScript/一些特效/jQuery带缩略图左右自动播放相册特效/photo0920/images/ink0920_01.jpg", "E:/JavaScript/一些特效/jQuery带缩略图左右自动播放相册特效/photo0920/images/1.jpg", 120, 120);
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.util.ResizeImage
 * JD-Core Version:    0.6.1
 */