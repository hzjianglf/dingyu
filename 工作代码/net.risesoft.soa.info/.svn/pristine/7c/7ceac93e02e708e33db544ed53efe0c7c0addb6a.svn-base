package net.risesoft.soa.info.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExAttrHelper
{
  private static final Type MAP_TYPE = new TypeToken() {  }
  .getType();

  private static final Type LIST_TYPE = new TypeToken() {  }
  .getType();

  private static final Gson GSON = new Gson();

  public static Map<String, String> map(String attrValue) {
    if ((attrValue == null) || (attrValue.length() == 0)) {
      return new HashMap();
    }
    return ((Map)GSON.fromJson(attrValue, MAP_TYPE));
  }

  public static List<String> list(String attrValue)
  {
    if ((attrValue == null) || (attrValue.length() == 0)) {
      return new ArrayList();
    }
    return ((List)GSON.fromJson(attrValue, LIST_TYPE));
  }
}