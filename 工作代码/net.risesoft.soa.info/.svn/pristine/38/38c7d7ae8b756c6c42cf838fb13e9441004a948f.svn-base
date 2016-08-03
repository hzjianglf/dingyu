package net.risesoft.soa.info.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocTransferThread extends Thread
{
  private static final Logger log = LoggerFactory.getLogger(DocTransferThread.class);
  private String tempPath;
  private String ext;

  public DocTransferThread(String tempPath, String ext)
  {
    this.tempPath = tempPath;
    this.ext = ext;
  }

  public void run() {
    try {
      DocumentToSWF docToSWF = new DocumentToSWF(this.tempPath);
      if (FileContentType.isOfficeType(this.ext.toLowerCase())) {
        docToSWF.docToswf();
      }
      if (FileContentType.isPdfType(this.ext.toLowerCase()))
        docToSWF.pdfToswf(false);
    }
    catch (Exception ex) {
      log.error("转换文档失败!", ex);
    }
  }
}