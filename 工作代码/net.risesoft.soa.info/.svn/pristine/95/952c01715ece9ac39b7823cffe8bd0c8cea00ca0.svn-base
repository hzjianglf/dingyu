package net.risesoft.soa.info.util;

import java.io.UnsupportedEncodingException;

public class StringUtil
{
  public static String subString(String str, int maxByteLen)
  {
    int len = maxByteLen;
    if ((str == null) || (maxByteLen <= 0)) {
      return "";
    }
    try
    {
      byte[] bStr = str.getBytes("GBK");
      if (maxByteLen >= bStr.length) {
        return str;
      }
      String cStr = new String(bStr, maxByteLen - 1, 2, "GBK");
      if ((cStr.length() == 1) && (str.contains(cStr))) {
        --len;
      }
      return new String(bStr, 0, len, "GBK") + "..";
    } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
      throw new RuntimeException("substring fail ");
    }
  }
}