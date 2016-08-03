package cn.com.qimingx.utils;

public class SQLTypeUtils
{
  public static int getJdbcType(String jdbcTypeName)
  {
    int type = 0;
    if ("ARRAY".equalsIgnoreCase(jdbcTypeName))
      type = 2003;
    else if ("BIGINT".equalsIgnoreCase(jdbcTypeName))
      type = -5;
    else if ("BINARY".equalsIgnoreCase(jdbcTypeName))
      type = -2;
    else if ("BIT".equalsIgnoreCase(jdbcTypeName))
      type = -7;
    else if ("BLOB".equalsIgnoreCase(jdbcTypeName))
      type = 2004;
    else if ("BOOLEAN".equalsIgnoreCase(jdbcTypeName))
      type = 16;
    else if ("CHAR".equalsIgnoreCase(jdbcTypeName))
      type = 1;
    else if ("CLOB".equalsIgnoreCase(jdbcTypeName))
      type = 2005;
    else if ("DATE".equalsIgnoreCase(jdbcTypeName))
      type = 91;
    else if ("DATALINK".equalsIgnoreCase(jdbcTypeName))
      type = 70;
    else if ("DECIMAL".equalsIgnoreCase(jdbcTypeName))
      type = 3;
    else if ("DISTINCT".equalsIgnoreCase(jdbcTypeName))
      type = 2001;
    else if ("DOUBLE".equalsIgnoreCase(jdbcTypeName))
      type = 8;
    else if ("FLOAT".equalsIgnoreCase(jdbcTypeName))
      type = 6;
    else if ("INTEGER".equalsIgnoreCase(jdbcTypeName))
      type = 4;
    else if ("JAVA_OBJECT".equalsIgnoreCase(jdbcTypeName))
      type = 2000;
    else if ("LONGVARBINARY".equalsIgnoreCase(jdbcTypeName))
      type = -4;
    else if ("LONGVARCHAR".equalsIgnoreCase(jdbcTypeName))
      type = -1;
    else if ("NULL".equalsIgnoreCase(jdbcTypeName))
      type = 0;
    else if ("NUMERIC".equalsIgnoreCase(jdbcTypeName))
      type = 2;
    else if ("OTHER".equalsIgnoreCase(jdbcTypeName))
      type = 1111;
    else if ("REAL".equalsIgnoreCase(jdbcTypeName))
      type = 7;
    else if ("REF".equalsIgnoreCase(jdbcTypeName))
      type = 2006;
    else if ("SMALLINT".equals(jdbcTypeName))
      type = 5;
    else if ("STRUCT".equalsIgnoreCase(jdbcTypeName))
      type = 2002;
    else if ("TIME".equalsIgnoreCase(jdbcTypeName))
      type = 92;
    else if ("TIMESTAMP".equalsIgnoreCase(jdbcTypeName))
      type = 93;
    else if ("TINYINT".equalsIgnoreCase(jdbcTypeName))
      type = -6;
    else if ("VARBINARY".equalsIgnoreCase(jdbcTypeName))
      type = -3;
    else if ("VARCHAR".equalsIgnoreCase(jdbcTypeName)) {
      type = 12;
    }

    return type;
  }

  public static boolean isNumberType(int jdbcType)
  {
    boolean result = false;
    switch (jdbcType)
    {
    case -6:
    case -5:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 8:
      result = true;
      break;
    case -4:
    case -3:
    case -2:
    case -1:
    case 0:
    case 1:
    case 7:
    default:
      result = false;
    }
    return result;
  }

  public static boolean isDateType(int jdbcType)
  {
    boolean result = false;
    switch (jdbcType)
    {
    case 91:
    case 92:
    case 93:
      result = true;
      break;
    default:
      result = false;
    }
    return result;
  }

  public static boolean isClobType(int jdbcType)
  {
    boolean result = false;
    switch (jdbcType)
    {
    case -1:
    case 2005:
      result = true;
      break;
    default:
      result = false;
    }
    return result;
  }

  public static boolean isBlobType(int jdbcType)
  {
    boolean result = false;
    switch (jdbcType)
    {
    case -4:
    case -3:
    case -2:
    case 2004:
      result = true;
      break;
    default:
      result = false;
    }
    return result;
  }

  public static boolean isStringType(int jdbcType)
  {
    boolean result = false;
    switch (jdbcType)
    {
    case 1:
    case 12:
      result = true;
      break;
    default:
      result = false;
    }
    return result;
  }

  public static boolean isLongType(int jdbcType)
  {
    boolean result = false;

    result = (!(isDateType(jdbcType))) && (!(isStringType(jdbcType))) && 
      (!(isNumberType(jdbcType))) && (jdbcType != 16);

    return result;
  }

  public static String getJdbcTypeName(int jdbcType)
  {
    String typeName = "";
    switch (jdbcType)
    {
    case 2003:
      typeName = "ARRAY";
      break;
    case 16:
      typeName = "BOOLEAN";
      break;
    case -5:
      typeName = "BIGINT";
      break;
    case -2:
      typeName = "BINARY";
      break;
    case -7:
      typeName = "BIT";
      break;
    case 2004:
      typeName = "BLOB";
      break;
    case 1:
      typeName = "CHAR";
      break;
    case 2005:
      typeName = "CLOB";
      break;
    case 70:
      typeName = "DATALINK";
      break;
    case 91:
      typeName = "DATE";
      break;
    case 3:
      typeName = "DECIMAL";
      break;
    case 2001:
      typeName = "DISTINCT";
      break;
    case 8:
      typeName = "DOUBLE";
      break;
    case 6:
      typeName = "FLOAT";
      break;
    case 4:
      typeName = "INTEGER";
      break;
    case 2000:
      typeName = "JAVA_OBJECT";
      break;
    case -4:
      typeName = "LONGVARBINARY";
      break;
    case -1:
      typeName = "LONGVARCHAR";
      break;
    case 0:
      typeName = "NULL";
      break;
    case 2:
      typeName = "NUMERIC";
      break;
    case 1111:
      typeName = "OTHER";
      break;
    case 7:
      typeName = "REAL";
      break;
    case 2006:
      typeName = "REF";
      break;
    case 5:
      typeName = "SMALLINT";
      break;
    case 2002:
      typeName = "STRUCT";
      break;
    case 92:
      typeName = "TIME";
      break;
    case 93:
      typeName = "TIMESTAMP";
      break;
    case -6:
      typeName = "TINYINT";
      break;
    case -3:
      typeName = "VARBINARY";
      break;
    case 12:
      typeName = "VARCHAR";
    }

    return typeName;
  }
}