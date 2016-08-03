package net.risesoft.soa.filecube.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class LogAction extends BaseAction
{

  @Autowired
  private LogService logService;
  private int start;
  private int rows;

  public String preSystemLog()
  {
    return "preSystemLog";
  }

  public void systemLog() {
    List fileLogs = this.logService.findAll(this.start, this.rows);
    long total = this.logService.count();
    Map map = new HashMap();
    map.put("total", Long.valueOf(total));
    map.put("fileLogs", fileLogs);
    printJson(map);
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