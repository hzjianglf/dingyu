package net.risesoft.soa.filecube.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentToSWF
{
  private static final Logger log = LoggerFactory.getLogger(DocumentToSWF.class);
  private static final String OS = SystemInfo.OS_NAME;
  private String fileFullName;
  private File pdfFile;
  private File swfFile;
  private File docFile;
  private GlobalInfo globalInfo = GlobalInfo.getInstance();

  public DocumentToSWF(String fileFullName)
  {
    this.fileFullName = fileFullName;
    String filePathName = fileFullName.substring(0, fileFullName.lastIndexOf("."));
    this.docFile = new File(fileFullName);
    this.pdfFile = new File(filePathName + ".pdf");
    this.swfFile = new File(filePathName + ".swf");
  }

  private void docTopdf()
    throws Exception
  {
    if (this.docFile.exists()) {
      if (!this.pdfFile.exists()) {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
          this.globalInfo.getOpenOfficeIP(), this.globalInfo.getOpenOfficePort());
        try {
          connection.connect();
          DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
          converter.convert(this.docFile, this.pdfFile);
          connection.disconnect();
          log.info("=======pdf转换成功，PDF输出：" + this.pdfFile.getPath() + "=======");
        } catch (ConnectException e) {
          e.printStackTrace();
          log.error("=======swf转换异常，openoffice服务未启动！=======");
          throw e;
        } catch (OpenOfficeException e) {
          e.printStackTrace();
          log.error("=======swf转换器异常，读取转换文件失败=======");
          throw e;
        } catch (Exception e) {
          e.printStackTrace();
          throw e;
        }
      } else {
        log.info("=======已经转换为pdf，不需要再进行转化=======");
      }
    }
    else log.error("=======swf转换器异常，需要转换的文档不存在，无法转换=======");
  }

  private void pdfToswf()
    throws Exception
  {
    Runtime r = Runtime.getRuntime();
    if (!this.swfFile.exists()) {
      if (this.pdfFile.exists()) {
        if (OS.toLowerCase().contains("window"))
          try
          {
            StringBuilder sb = new StringBuilder();

            String swfToolsHome = this.globalInfo.getSwfToolsHome();
            if (swfToolsHome.charAt(swfToolsHome.length() - 1) != '/') {
              swfToolsHome = swfToolsHome + '/';
            }
            sb.append(swfToolsHome + "pdf2swf ");

            sb.append("\"" + this.pdfFile.getPath() + "\"");

            sb.append(" -o ");

            sb.append("\"" + this.swfFile.getPath() + "\" ");

            sb.append(this.globalInfo.getSwfToolsCMDSParam());

            log.info("=======转换命令：" + sb.toString() + "=======");
            Process p = Runtime.getRuntime().exec(sb.toString());

            loadStream(p.getInputStream());
            loadStream(p.getErrorStream());
            log.info("=======swf转换成功，文件输出：" + this.swfFile.getPath() + "=======");
            if (!this.pdfFile.exists()) return;
            this.pdfFile.delete();
          }
          catch (Exception e) {
            e.printStackTrace();
            throw e;
          }
        else
          try {
            Process p = r.exec("pdf2swf " + 
              this.pdfFile.getPath() + " -o " + this.swfFile.getPath() + " -T 9 " + this.globalInfo.getSwfToolsCMDSParam());
            loadStream(p.getInputStream());
            loadStream(p.getErrorStream());
            log.info("=======swf转换成功，文件输出：" + this.swfFile.getPath() + "=======");
            if (!this.pdfFile.exists()) return;
            this.pdfFile.delete();
          }
          catch (Exception e) {
            e.printStackTrace();
            throw e;
          }
      }
      else
        log.error("=======pdf不存在，无法转换=======");
    }
    else
      log.info("=======swf已存在不需要转换=======");
  }

  private static void loadStream(InputStream in) throws IOException
  {
    in = new BufferedInputStream(in);
    while (in.read() != -1);
  }

  public boolean docToswf()
  {
    if (OS.toLowerCase().contains("windows"))
      log.info("=======swf转换器开始工作，当前设置运行环境windows=======");
    else {
      log.info("=======swf转换器开始工作，当前设置运行环境linux=======");
    }
    try
    {
      if (this.swfFile.exists())
        return true;
      docTopdf();
      pdfToswf();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    if (this.swfFile.exists()) {
      return true;
    }
    return false;
  }

  public String getswfPath()
  {
    if (this.swfFile.exists()) {
      String tempString = this.swfFile.getPath();
      tempString = tempString.replaceAll("\\\\", "/");
      return tempString;
    }
    return "";
  }

  public void setOutputPath(String outputPath)
  {
    if (!new File(outputPath).exists()) {
      new File(outputPath).mkdirs();
    }
    if (!outputPath.equals("")) {
      String fileName = this.fileFullName.substring(this.fileFullName.lastIndexOf("/"), 
        this.fileFullName.lastIndexOf("."));
      if (outputPath.charAt(outputPath.length()) == '/')
        this.swfFile = new File(outputPath + fileName + ".swf");
      else
        this.swfFile = new File(outputPath + fileName + ".swf");
    }
  }

  public static void main(String[] s)
  {
    DocumentToSWF d = new DocumentToSWF("E:/有生产品部通讯录.xls");
    d.docToswf();
  }
}