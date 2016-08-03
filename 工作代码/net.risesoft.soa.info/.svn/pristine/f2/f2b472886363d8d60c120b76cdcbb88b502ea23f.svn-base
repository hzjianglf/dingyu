package cn.com.qimingx.dbe.service.impl;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class HelperDBInfoServiceBase
{
  private AbstractDBInfoService service;

  public HelperDBInfoServiceBase(AbstractDBInfoService service)
  {
    this.service = service;
  }

  public List<String> getElements(String schema, String[] types)
  {
    List list = new ArrayList();
    if (types.length == 0)
      types = new String[] { "table" };
    try
    {
      ResultSet rs = this.service.meta.getTables(null, schema, null, types);
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
}