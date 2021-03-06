package net.risesoft.soa.info.manager.action;

import cn.com.qimingx.dbe.DBConnectionState;
import cn.com.qimingx.dbe.service.DBInfoService;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.risesoft.soa.framework.util.json.JSONException;
import net.risesoft.soa.framework.util.json.JSONWriter;
import net.risesoft.soa.info.action.BaseAction;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.model.InformationDesc;
import net.risesoft.soa.info.tools.InformationList;
import net.risesoft.soa.info.tools.SpringUtil;

import org.apache.commons.dbcp.BasicDataSource;

public class DBInfo extends BaseAction
{
  private static final long serialVersionUID = 2784830724321191954L;
  private String tableName;
  private String node;

  public final String getTableName()
  {
    return this.tableName;
  }

  public final void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getNode() {
    return this.node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String execute() throws Exception
  {
    if (this.operation.equals("tables")) {
      BasicDataSource dataSource = (BasicDataSource)SpringUtil.getBean("dataSource");
      DBConnectionState db = new DBConnectionState(dataSource.getConnection());
      DBInfoService service = db.getDBInfoService();
      List tables = service.getTables(dataSource.getUsername().toUpperCase());
      db.destroy();
      StringWriter sw = new StringWriter();
      try {
        JSONWriter json = new JSONWriter(sw);
        json.array();
        for (Object string : tables) {
          if ((((String) string).indexOf("INFO_") == 0) || (((String) string).indexOf("info") == 0)) {
            json.object();
            json.key("name").value(((String) string).toUpperCase());
            json.endObject();
          }
        }
        json.endArray();
      } catch (JSONException e) {
        e.printStackTrace();
      }
      this.json = sw.toString();
    }
    if (this.operation.equals("elements")) {
      Information information = null;
      if (this.node.length() > 0)
        information = InformationList.get(this.node);
      List columns;
      if ((information == null) || (!information.getTableName().equals(this.tableName))) {
        BasicDataSource dataSource = (BasicDataSource)SpringUtil.getBean("dataSource");
        DBConnectionState db = new DBConnectionState(dataSource.getConnection());
        DBInfoService service = db.getDBInfoService();
        columns = service.getTableColumnInfo(dataSource.getUsername().toUpperCase(), this.tableName);
        db.destroy();
        StringWriter sw = new StringWriter();
        try {
          JSONWriter json = new JSONWriter(sw);
          json.array();
          int i = 0;
          for (int j = 0; j < columns.size(); j++) {
        	  json.object();
              json.key("columnName").value(columns.get(0).toString().toUpperCase());
              json.key("columnType").value(columns.get(1));
              json.key("columnTypeName").value(columns.get(2));
              json.key("columnValue").value(columns.get(3));
              json.key("tabIndex").value(i);
              json.endObject();
              i++;
			
		}
          json.endArray();
        } catch (JSONException e) {
          e.printStackTrace();
        }
        this.json = sw.toString();
      } else {
    	  List<InformationDesc> informationDescs = information.getElements();
        Map map = new HashMap();
        for (InformationDesc informationDesc : informationDescs) {
          map.put(informationDesc.getColumnName(), informationDesc);
        }
        BasicDataSource dataSource = (BasicDataSource)SpringUtil.getBean("dataSource");
        DBConnectionState db = new DBConnectionState(dataSource.getConnection());
        DBInfoService service = db.getDBInfoService();
        List<Object[]> columns1 = service.getTableColumnInfo(dataSource.getUsername().toUpperCase(), this.tableName);
        db.destroy();
        StringWriter sw = new StringWriter();
        try {
          JSONWriter json = new JSONWriter(sw);
          json.array();
          for (Object[] string : columns1) {
            json.object();
            json.key("columnName").value(string[0].toString().toUpperCase());
            json.key("columnType").value(string[1]);
            json.key("columnTypeName").value(string[2]);
            json.key("columnValue").value(string[3]);

            if (map.get(string[0]) != null) {
              InformationDesc informationDesc = (InformationDesc)map.get(string[0]);
              json.key("isList").value(informationDesc.isList());
              json.key("listName").value(informationDesc.getListName());
              json.key("listLength").value(informationDesc.getListLength());
              json.key("textLength").value(informationDesc.getTextLength());
              json.key("textDec").value(informationDesc.getTextDec());
              json.key("defaultValue").value(informationDesc.getDefaultValue());
              json.key("isSearch").value(informationDesc.isSearch());
              json.key("searchType").value(informationDesc.getSearchType());
              json.key("tabIndex").value(informationDesc.getTabIndex());
            }
            json.endObject();
          }
          json.endArray();
        } catch (JSONException e) {
          e.printStackTrace();
        }
        this.json = sw.toString();
      }
    }
    return "json";
  }
}