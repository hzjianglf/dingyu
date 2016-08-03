package net.risesoft.soa.info.util;

import freemarker.cache.TemplateLoader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TemplateLoaderImpl
  implements TemplateLoader
{
  private byte[] template;

  public TemplateLoaderImpl(byte[] template)
  {
    this.template = template;
  }

  public void closeTemplateSource(Object arg0) throws IOException
  {
  }

  public Object findTemplateSource(String arg0) throws IOException {
    return this.template;
  }

  public long getLastModified(Object arg0) {
    return 0L;
  }

  public Reader getReader(Object arg0, String arg1) throws IOException {
    byte[] c = (byte[])arg0;
    try
    {
      return new InputStreamReader(new ByteArrayInputStream(c));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}