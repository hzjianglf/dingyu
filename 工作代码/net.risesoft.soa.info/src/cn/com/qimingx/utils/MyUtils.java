package cn.com.qimingx.utils;

public class MyUtils
{
  public static Object newObjectOfClassName(String clsName)
  {
    try
    {
      return Class.forName(clsName).newInstance();
    } catch (Exception e) {
      e.printStackTrace(); }
    return null;
  }
}