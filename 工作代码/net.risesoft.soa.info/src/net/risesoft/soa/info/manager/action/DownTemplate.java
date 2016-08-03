package net.risesoft.soa.info.manager.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import net.risesoft.soa.info.action.BaseAction;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.tools.InformationList;

public class DownTemplate extends BaseAction
{
  private static final long serialVersionUID = -4554722078890043879L;
  private String node;
  private Information information;

  public String getNode()
  {
    return this.node;
  }

  public void setNode(String node) {
    this.node = node;
  }

  public String execute() throws Exception
  {
    return "success";
  }

  public InputStream getInputStream() throws Exception
  {
    InputStream is = null;
    try {
      this.information = InformationList.get(this.node);
      byte[] template = this.information.getTemplate();

      is = new ByteArrayInputStream(template);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return is;
  }

  public String getFileName()
  {
    return this.information.getName() + ".html";
  }
}