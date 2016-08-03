package net.risesoft.soa.asf.web.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

@Component
public class ZipHelper
{
  public void zipFolder(String zipPath, String filePath)
    throws Exception
  {
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
    File f = new File(filePath);
    zipFiles(out, f, "");
    out.close();
  }

  public void zipFiles(String zipPath, File[] files) throws Exception {
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
    for (File f : files) {
      out.putNextEntry(new ZipEntry(f.getName()));
      FileInputStream in = new FileInputStream(f);
      int b;
      while ((b = in.read()) != -1) {
        out.write(b);
      }
      in.close();
    }
    out.close();
  }

  public void zipFile(String zipPath, String filePath)
    throws Exception
  {
    File f = new File(filePath);
    FileInputStream fis = new FileInputStream(f);
    BufferedInputStream bis = new BufferedInputStream(fis);
    byte[] buf = new byte[1024];

    FileOutputStream fos = new FileOutputStream(zipPath);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ZipOutputStream zos = new ZipOutputStream(bos);
    ZipEntry ze = new ZipEntry(f.getName());
    zos.putNextEntry(ze);
    int len;
    while ((len = bis.read(buf)) != -1) {
      zos.write(buf, 0, len);
      zos.flush();
    }
    bis.close();
    zos.close();
  }

  private void zipFiles(ZipOutputStream out, File f, String base)
    throws Exception
  {
    if (f.isDirectory()) {
      File[] fl = f.listFiles();
      out.putNextEntry(new ZipEntry(base + "/"));
      base = base + "/";
      for (int i = 0; i < fl.length; ++i)
        zipFiles(out, fl[i], base + fl[i].getName());
    }
    else {
      out.putNextEntry(new ZipEntry(base));
      FileInputStream in = new FileInputStream(f);
      int b;
      while ((b = in.read()) != -1) {
        out.write(b);
      }
      in.close();
    }
  }

  public static void main(String[] args)
  {
    System.out.println("Zip File Begin");

    ZipHelper zipHelper = new ZipHelper();
    String zipPath = "E:\\test.zip";
    String filePath = "E:\\index.pdf";
    try {
      zipHelper.zipFile(zipPath, filePath);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    System.out.println("Zip File Done");
  }
}