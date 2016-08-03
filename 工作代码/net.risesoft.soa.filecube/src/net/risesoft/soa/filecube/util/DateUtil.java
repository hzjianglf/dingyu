package net.risesoft.soa.filecube.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil
{
  private static Logger log = LoggerFactory.getLogger(DateUtil.class);
  public static final String parStr = "yyyy-MM-dd HH:mm:ss";

  public static String getDate(String str, Date date)
  {
    if (date == null) return "";
    SimpleDateFormat format = new SimpleDateFormat(str);
    return format.format(date);
  }

  public static String getDate(Date date)
  {
    return getDate("yyyy-MM-dd HH:mm:ss", date);
  }

  public static Date parse(String pattern, String dateStr)
  {
    if ((dateStr == null) || ("".equals(dateStr))) return null;
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      date = sdf.parse(dateStr);
    } catch (ParseException e) {
      log.error("日期转换出错！", e);
    }
    return date;
  }

  public static Date parse(String dateStr)
  {
    return parse("yyyy-MM-dd HH:mm:ss", dateStr);
  }
}