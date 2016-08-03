package cn.com.qimingx.dbe.service;

import java.sql.Connection;
import java.util.List;

public abstract interface DBInfoService
{
  public abstract void setDBConnection(Connection paramConnection);

  public abstract Connection getDBConnection();

  public abstract List<String> getTables(String paramString);

  public abstract List<Object[]> getTableColumnInfo(String paramString1, String paramString2);
}