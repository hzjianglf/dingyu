package net.risesoft.soa.filecube.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor
  implements JsonValueProcessor
{
  private String dateFormatStr = "yyyy-MM-dd";

  public DateJsonValueProcessor() {
  }
  public DateJsonValueProcessor(String dateFormatStr) {
    this.dateFormatStr = dateFormatStr;
  }

  public Object processArrayValue(Object value, JsonConfig jsonConfig) {
    String[] obj = new String[0];
    if ((value instanceof Date[])) {
      SimpleDateFormat sf = new SimpleDateFormat(this.dateFormatStr);
      Date[] dates = (Date[])value;
      obj = new String[dates.length];
      for (int i = 0; i < dates.length; i++) {
        if (dates[i] != null)
          obj[i] = sf.format(dates[i]);
      }
    }
    return obj;
  }

  public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
    if (value == null) return "";
    if ((value instanceof Date)) {
      String str = new SimpleDateFormat(this.dateFormatStr).format((Date)value);
      return str;
    }
    return value.toString();
  }

  public String getDateFormatStr() {
    return this.dateFormatStr;
  }

  public void setDateFormatStr(String dateFormatStr) {
    this.dateFormatStr = dateFormatStr;
  }
}