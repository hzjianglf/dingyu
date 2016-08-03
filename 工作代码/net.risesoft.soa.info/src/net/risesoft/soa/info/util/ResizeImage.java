package net.risesoft.soa.info.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResizeImage
{
  private static final Logger log = LoggerFactory.getLogger(ResizeImage.class);

  public static BufferedImage resize(BufferedImage source, int targetW, int targetH)
  {
    int type = source.getType();
    BufferedImage target = null;
    double sx = targetW / source.getWidth();
    double sy = targetH / source.getHeight();

    if (type == 0) {
      ColorModel cm = source.getColorModel();
      WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
      boolean alphaPremultiplied = cm.isAlphaPremultiplied();
      target = new BufferedImage(cm, raster, alphaPremultiplied, null);
    } else {
      target = new BufferedImage(targetW, targetH, type);
    }
    Graphics2D g = target.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
    g.dispose();
    return target;
  }

  public static void resize(String srcFile, String targetFile, int width, int hight)
    throws Exception
  {
    BufferedImage srcImage = null;
    String imgType = "JPEG";
    if (srcFile.toLowerCase().endsWith(".png")) {
      imgType = "PNG";
    }
    File saveFile = new File(targetFile);
    File fromFile = new File(srcFile);
    try {
      srcImage = ImageIO.read(fromFile);
    }
    catch (IIOException e) {
      log.error("获取图片时发生javax.imageio.IIOException错误。fromFile=" + fromFile, e);
      throw e;
    }
    if ((width > 0) || (hight > 0)) {
      srcImage = resize(srcImage, width, hight);
    }
    ImageIO.write(srcImage, imgType, saveFile);
  }

  public static void main(String[] args) throws Exception {
    resize("D:/1.jpg", "D:/1_small.jpg", 120, 120);
  }
}