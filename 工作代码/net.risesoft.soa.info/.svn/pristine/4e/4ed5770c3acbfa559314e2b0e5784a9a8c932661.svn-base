package cn.com.qimingx.dbe.service.impl;

import cn.com.qimingx.dbe.service.DBInfoService;
import cn.com.qimingx.utils.SQLTypeUtils;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDBInfoService
  implements DBInfoService
{
  protected Connection conn;
  protected DatabaseMetaData meta;
  protected HelperDBInfoServiceBase baseHelper;

  public void setDBConnection(Connection conn)
  {
    this.conn = conn;
    try
    {
      this.meta = conn.getMetaData();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    this.baseHelper = new HelperDBInfoServiceBase(this);
  }

  public Connection getDBConnection() {
    return this.conn;
  }

  public List<String> getTables(String schema) {
    List list = new ArrayList();
    try {
      ResultSet rs = this.meta.getTables(null, schema, null, new String[] { "TABLE" });
      while (rs.next()) {
        String name = rs.getString("TABLE_NAME");

        if (name.indexOf(47) > -1) continue; if (name.indexOf(36) > -1) {
          continue;
        }
        list.add(name);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<Object[]> getTableColumnInfo(String schema, String tableName) {
    List list = new ArrayList();
    try
    {
      ResultSet rs = this.meta.getColumns(null, schema, tableName, null);

      while (rs.next()) {
        Object[] string = new Object[4];
        string[0] = rs.getString("COLUMN_NAME");
        int data_type = rs.getInt("DATA_TYPE");
        string[1] = Integer.valueOf(data_type);
        string[2] = SQLTypeUtils.getJdbcTypeName(data_type);
        string[3] = rs.getString("COLUMN_DEF");
        list.add(string);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
}