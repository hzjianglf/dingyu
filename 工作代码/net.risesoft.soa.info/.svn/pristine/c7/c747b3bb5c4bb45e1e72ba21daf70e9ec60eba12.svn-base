package net.risesoft.soa.info.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Iterator;
import net.risesoft.soa.framework.service.config.Config;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigUtil
{
  public static String filePath;
  public static int openOffice_port = 8100;

  public static String openOffice_ip = "localhost";

  public static String SWFTools_home = "D://SWFTools";

  public static String SWFTools_params = "-s storeallcharacters -T 9";

  static {
    try {
      InputStream in = new BufferedInputStream(Config.getConfigFileAsURL("info/config.xml").openStream());
      SAXReader reader = new SAXReader();
      Document doc = reader.read(in);
      Element root = doc.getRootElement();
      for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
        Element foo = (Element)iter.next();
        String name = foo.getName();

        if (name.equals("filePath")) {
          String type = foo.attributeValue("type");
          if (System.getProperty("os.name").contains(type)) {
            filePath = foo.getTextTrim();
            File dir = new File(filePath);
            if (!(dir.isDirectory()))
              dir.mkdirs();
          }
        }
        Iterator itera;
        Element sub;
        String subName;
        if (name.equals("openOffice")) {
          for (itera = foo.elementIterator(); itera.hasNext(); ) {
            sub = (Element)itera.next();
            subName = sub.getName();
            if (subName.equals("port")) {
              openOffice_port = Integer.parseInt(sub.getTextTrim());
            }
            if (subName.equals("ip")) {
              openOffice_ip = sub.getTextTrim();
            }
          }
        }
        if (name.equals("SWFTools"))
          for (itera = foo.elementIterator(); itera.hasNext(); ) {
            sub = (Element)itera.next();
            subName = sub.getName();
            if (subName.equals("home")) {
              SWFTools_home = sub.getTextTrim();
            }
            if (subName.equals("params"))
              SWFTools_params = sub.getTextTrim();
          }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println(System.getProperty("os.name"));
  }
}