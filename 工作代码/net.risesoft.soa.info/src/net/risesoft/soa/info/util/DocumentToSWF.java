package net.risesoft.soa.info.util;

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

  private static final String OS = System.getProperty("os.name");
  private String fileFullName;
  private String fileTitle;
  private String fileExtName;

  public DocumentToSWF(String fileFullName)
  {
    this.fileFullName = fileFullName;
    this.fileTitle = fileFullName.substring(0, fileFullName.lastIndexOf("."));
    this.fileExtName = fileFullName.substring(fileFullName.lastIndexOf("."));
  }

  private void docTopdf()
    throws Exception
  {
    File doc = new File(this.fileFullName);
    if (doc.exists()) {
      File pdf = new File(this.fileTitle + ".pdf");
      if (!(pdf.exists())) {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
          ConfigUtil.openOffice_ip, ConfigUtil.openOffice_port);
        try {
          connection.connect();
          DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
          converter.convert(doc, pdf);
          connection.disconnect();
          log.info("=======pdf转换成功，PDF输出：" + pdf.getPath() + "=======");
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
    } else {
      log.error("=======swf转换器异常，需要转换的文档不存在，无法转换=======");
    }
  }

  public void pdfToswf(boolean isDelete)
    throws Exception
  {
    Runtime r = Runtime.getRuntime();
    File swf = new File(this.fileTitle + ".swf");
    if (!(swf.exists())) {
      File pdf = new File(this.fileTitle + ".pdf");
      if (pdf.exists()) {
        String SWFTools_params = ConfigUtil.SWFTools_params;
        if ((".ppt".equals(this.fileExtName.toLowerCase())) || (".pptx".equals(this.fileExtName.toLowerCase()))) {
          SWFTools_params = SWFTools_params.replaceAll("storeallcharacters", "poly2bitmap");
        }
        if (OS.toLowerCase().contains("window"))
          try
          {
            StringBuilder sb = new StringBuilder();

            String swfToolsHome = ConfigUtil.SWFTools_home;
            if (swfToolsHome.charAt(swfToolsHome.length() - 1) != '/') {
              swfToolsHome = swfToolsHome + '/';
            }
            sb.append(swfToolsHome + "pdf2swf ");

            sb.append(pdf.getPath());

            sb.append(" -o ");

            sb.append(swf.getPath() + " ");

            sb.append(SWFTools_params);

            log.info("=======转换命令：" + sb.toString() + "=======");
            Process p = Runtime.getRuntime().exec(sb.toString());

            loadStream(p.getInputStream());
            loadStream(p.getErrorStream());
            log.info("=======swf转换成功，文件输出：" + swf.getPath() + "=======");
            if ((pdf.exists()) && (isDelete))
              pdf.delete();
          }
          catch (Exception e) {
            e.printStackTrace();
            throw e;
          }
        else
          try {
            String sb = "pdf2swf " + pdf.getPath() + " -o " + swf.getPath() + " " + SWFTools_params;
            log.info("=======转换命令：" + sb.toString() + "=======");
            Process p = r.exec(sb);
            loadStream(p.getInputStream());
            loadStream(p.getErrorStream());
            log.info("=======swf转换成功，文件输出：" + swf.getPath() + "=======");
            if ((pdf.exists()) && (isDelete))
              pdf.delete();
          }
          catch (Exception e) {
            e.printStackTrace();
            throw e;
          }
      }
      else {
        log.error("=======pdf不存在，无法转换=======");
      }
    } else {
      log.info("=======swf已存在不需要转换=======");
    }
  }

  private static void loadStream(InputStream in) throws IOException {
    in = new BufferedInputStream(in);
    while (in.read() != -1);
  }

  public boolean docToswf()
  {
    if (OS.toLowerCase().contains("windows"))
      log.info("=======swf转换器开始工作，当前设置运行环境windows=======");
    else
      log.info("=======swf转换器开始工作，当前设置运行环境linux=======");
    try
    {
      docTopdf();
      pdfToswf(true);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static void main(String[] s) {
    DocumentToSWF d = new DocumentToSWF("D:\\file\\信息发布/海淀区政府/内部期刊/政府系统信息/环保局/环保信息/2012-12-03/30(陈梓漪)方法变更信息 302._Fkud4T0YEeKw1q-Q-eh5TA.doc");
    d.docToswf();
  }
}