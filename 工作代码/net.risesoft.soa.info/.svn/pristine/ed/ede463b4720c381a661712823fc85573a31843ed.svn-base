package cn.com.qimingx.dbe;

import cn.com.qimingx.dbe.service.DBInfoService;
import cn.com.qimingx.dbe.service.impl.DefaultDBInfoService;
import cn.com.qimingx.utils.MyUtils;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionState
{
  private static final long serialVersionUID = 8886397095815863532L;
  private Connection dbConnection;
  private DBInfoService service;

  public DBConnectionState(Connection conn)
  {
    this.dbConnection = conn;
  }

  public DBInfoService getDBInfoService(String clsName)
  {
    if (this.service == null) {
      String name = DBEConfig.getProperty(clsName);
      Object o = MyUtils.newObjectOfClassName(name);
      if (o != null)
        this.service = ((DBInfoService)o);
      else {
        this.service = new DefaultDBInfoService();
      }
      this.service.setDBConnection(this.dbConnection);
    }
    return this.service;
  }

  public DBInfoService getDBInfoService()
  {
    if (this.service == null) {
      this.service = new DefaultDBInfoService();
      this.service.setDBConnection(this.dbConnection);
    }
    return this.service;
  }

  public void destroy() {
    if (this.dbConnection == null) return;
    try {
      if (!(this.dbConnection.isClosed()))
        this.dbConnection.close();
    }
    catch (SQLException localSQLException)
    {
    }
  }
}