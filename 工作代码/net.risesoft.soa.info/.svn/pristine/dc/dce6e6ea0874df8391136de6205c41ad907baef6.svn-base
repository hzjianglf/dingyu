package cn.com.qimingx.dbe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public class DBEConfig
{
  public static final String CONF_FILE = "dbe_config.properties";
  private static Properties props;

  private static void init()
  {
    InputStream in = null;
    try {
      if (props == null) {
        in = DBEConfig.class.getResourceAsStream("dbe_config.properties");
        props = new Properties();
        props.load(in);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      try
      {
        if (in != null) in.close();
      }
      catch (IOException localIOException1)
      {
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      try {
        if (in != null) in.close();  } catch (IOException localIOException2) {  } } finally { try { if (in != null) in.close();  } catch (IOException localIOException3) {
      } }
  }

  public static String getProperty(String value) {
    init();
    return props.getProperty(value);
  }

  public static void main(String[] args) {
    System.out.println(getProperty("oracle.jdbc.driver.OracleDriver"));
  }
}