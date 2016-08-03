package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.risesoft.soa.filecube.model.Dimension;
import net.risesoft.soa.filecube.service.DimensionService;
import net.risesoft.soa.filecube.util.DimensionType;
import net.risesoft.soa.filecube.util.FileType;
import net.risesoft.soa.filecube.util.OperationType;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class DimensionAction extends BaseAction
{

  @Autowired
  private DimensionService dimensionService;
  private String dimensionStr;
  private String dimensionSql;
  private int start;
  private int rows;

  public String preDimension()
  {
    return "preDimension";
  }

  public String preChoose()
  {
    return "preChoose";
  }

  public void choose()
  {
    JSONArray dimension = JSONArray.fromObject(this.dimensionStr);
    List dimensions = new ArrayList();
    for (Iterator localIterator = dimension.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
      JSONObject jsonObject = (JSONObject)obj;
      Dimension d = new Dimension();
      d.setId(jsonObject.get("id").toString());
      d.setDepth(Integer.parseInt(jsonObject.get("depth").toString()));
      dimensions.add(d);
    }
    saveSystemLog(OperationType.FC_VIEW.toString(), "维度选择", "选择的维度方式" + this.dimensionStr);
    printJsonArray(this.dimensionService.getDimensionTree(dimensions));
  }

  public void getDimension()
  {
    String dimension = Arrays.toString(DimensionType.values());
    try {
      this.response.setCharacterEncoding("utf-8");
      this.response.getWriter().print(dimension);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String file()
  {
    JSONArray sqlArray = JSONArray.fromObject(this.dimensionSql);
    String sqlConditions = "";
    int i = 0;
    for (Iterator localIterator = sqlArray.iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
      JSONObject jsonObject = (JSONObject)obj;
      sqlConditions = sqlConditions + jsonObject.get("columnName") + " like '%";
      Object columnValue = jsonObject.get("value");
      String columnValueTmp = (String)FileType.TypesCNToEnMap.get(columnValue);
      if (columnValueTmp != null)
        sqlConditions = sqlConditions + columnValueTmp + "%'";
      else {
        sqlConditions = sqlConditions + columnValue + "%'";
      }

      if (i < sqlArray.size() - 1) {
        sqlConditions = sqlConditions + " AND ";
      }
      i++;
    }
    Map m = this.dimensionService.getDimensionFiles(sqlConditions, this.start, this.rows);
    List fileInfos = (List)m.get("datas");
    ActionContext.getContext().getContextMap().put("fileInfos", fileInfos);
    ActionContext.getContext().getContextMap().put("totalCount", m.get("totalCount"));
    ActionContext.getContext().getContextMap().put("start", Integer.valueOf(this.start));
    ActionContext.getContext().getContextMap().put("rows", Integer.valueOf(this.rows));
    ActionContext.getContext().getContextMap().put("dimensionSql", this.dimensionSql);
    return "file";
  }
  public Object getDimensionStr() {
    return this.dimensionStr;
  }

  public void setDimensionStr(String dimensionStr) {
    this.dimensionStr = dimensionStr;
  }
  public String getDimensionSql() {
    return this.dimensionSql;
  }
  public void setDimensionSql(String dimensionSql) {
    this.dimensionSql = dimensionSql;
  }
  public int getStart() {
    return this.start;
  }
  public void setStart(int start) {
    this.start = start;
  }
  public int getRows() {
    return this.rows;
  }
  public void setRows(int rows) {
    this.rows = rows;
  }
}