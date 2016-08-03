package net.risesoft.soa.info.util;

import java.util.HashMap;
import java.util.Map;

public class FileContentType
{
  private static Map<String, String> map;

  public static boolean isImgType(String ext)
  {
    boolean isImgType = false;
    if (".bmp".equals(ext))
      isImgType = true;
    else if ((".jpg".equals(ext)) || (".jpeg".equals(ext)))
      isImgType = true;
    else if (".gif".equals(ext))
      isImgType = true;
    else if ((".pcx".equals(ext)) || (".pci".equals(ext)))
      isImgType = true;
    else if (".png".equals(ext)) {
      isImgType = true;
    }
    return isImgType;
  }

  public static boolean isOfficeType(String ext) {
    boolean isImgType = false;
    if ((".doc".equals(ext)) || (".docx".equals(ext)))
      isImgType = true;
    else if ((".xls".equals(ext)) || (".xlsx".equals(ext)))
      isImgType = true;
    else if ((".ppt".equals(ext)) || (".pptx".equals(ext))) {
      isImgType = true;
    }
    return isImgType;
  }

  public static boolean isPdfType(String ext) {
    boolean isPdfType = false;
    if (".pdf".equals(ext)) {
      isPdfType = true;
    }
    return isPdfType;
  }

  public static String getIcon(String ext) {
    if (map == null) {
      map = new HashMap();
      map.put(".avi", "/info/css/file/avi.png");
      map.put(".bpm", "/info/css/file/bpm.png");
      map.put(".doc", "/info/css/file/docx_win.png");
      map.put(".docx", "/info/css/file/docx_win.png");
      map.put(".eml", "/info/css/file/eml.png");
      map.put(".fla", "/info/css/file/fla.png");
      map.put(".gif", "/info/css/file/gif.png");
      map.put(".html", "/info/css/file/html.png");
      map.put(".jpeg", "/info/css/file/jpeg.png");
      map.put(".jpg", "/info/css/file/jpeg.png");
      map.put(".pcx", "/info/css/file/bpm.png");
      map.put(".pci", "/info/css/file/bpm.png");
      map.put(".mp3", "/info/css/file/mp3.png");
      map.put(".mpeg", "/info/css/file/mpeg.png");
      map.put(".pdf", "/info/css/file/pdf.png");
      map.put(".png", "/info/css/file/png.png");
      map.put(".ppt", "/info/css/file/pptx_win.png");
      map.put(".pptx", "/info/css/file/pptx_win.png");
      map.put(".psd", "/info/css/file/psd.png");
      map.put(".rar", "/info/css/file/rar.png");
      map.put(".txt", "/info/css/file/text.png");
      map.put(".tiff", "/info/css/file/tiff.png");
      map.put(".tif", "/info/css/file/tiff.png");
      map.put(".vsd", "/info/css/file/vsd.png");
      map.put(".wav", "/info/css/file/wav.png");
      map.put(".wma", "/info/css/file/wma.png");
      map.put(".wmv", "/info/css/file/wmv.png");
      map.put(".xlsx", "/info/css/file/xlsx_win.png");
      map.put(".xls", "/info/css/file/xlsx_win.png");
      map.put(".zip", "/info/css/file/zip.png");
    }
    String icon = (String)map.get(ext);
    if (icon == null) {
      icon = "/info/css/file/blank.png";
    }
    return icon;
  }
}