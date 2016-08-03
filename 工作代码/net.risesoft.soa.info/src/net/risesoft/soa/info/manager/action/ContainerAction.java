package net.risesoft.soa.info.manager.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import net.risesoft.soa.ac.model.Resource;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.info.action.BaseAction;
import net.risesoft.soa.info.manager.util.ContainerUtil;

public class ContainerAction extends BaseAction
{
  private static final long serialVersionUID = -4838071067740077538L;
  private String node;
  private Resource resource;

  public final String getNode()
  {
    return this.node;
  }

  public final void setNode(String node) {
    this.node = node;
  }

  public final Resource getResource() {
    return this.resource;
  }

  public final void setResource(Resource resource) {
    this.resource = resource;
  }

  public String execute()
    throws Exception
  {
    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");

    ContainerUtil cu = new ContainerUtil();

    if ("display".equalsIgnoreCase(this.operation)) {
      setResource(cu.get(this.node));
      return this.operation;
    }

    if ("update".equalsIgnoreCase(this.operation)) {
      setResource(cu.get(this.node));
      return this.operation;
    }

    if ("save".equalsIgnoreCase(this.operation)) {
      try {
        cu.save(this.resource, this.node, su);
      } catch (Exception e) {
        e.printStackTrace();
        super.setJson(false);
      }
    }

    if ("delete".equalsIgnoreCase(this.operation)) {
      try {
        cu.delete(this.node, su);
        super.setJson(true, "删除资源成功");
      } catch (Exception localException1) {
        super.setJson(false, "删除资源失败");
      }
    }
    return "json";
  }
}