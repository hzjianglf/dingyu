package net.risesoft.soa.info.manager.action;

import com.opensymphony.xwork2.ActionContext;
import java.io.File;
import java.util.Map;
import net.risesoft.soa.framework.session.SessionUser;
import net.risesoft.soa.info.action.BaseAction;
import net.risesoft.soa.info.manager.util.InformationUtil;
import net.risesoft.soa.info.model.Information;
import net.risesoft.soa.info.tools.SpringUtil;

public class InformationAction extends BaseAction
{
  private static final long serialVersionUID = -6735921431313980312L;
  private String node;
  private Information information;
  private String elements;
  private File file;
  private String contentType;

  public final String getNode()
  {
    return this.node;
  }

  public final void setNode(String node) {
    this.node = node;
  }

  public Information getInformation() {
    return this.information;
  }

  public void setInformation(Information information) {
    this.information = information;
  }

  public String getElements() {
    return this.elements;
  }

  public void setElements(String elements) {
    this.elements = elements;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFileContentType() {
    return this.contentType;
  }

  public void setFileContentType(String contentType) {
    this.contentType = contentType;
  }

  public String execute()
    throws Exception
  {
    SessionUser su = (SessionUser)ActionContext.getContext().getSession().get("session.User");

    InformationUtil iu = (InformationUtil)SpringUtil.getBean("informationUtil");

    if ((this.operation.equals("display")) || (this.operation.equals("update"))) {
      this.information = iu.get(this.node);
      return this.operation;
    }

    if (this.operation.equals("create")) {
      this.information = new Information();
      return this.operation;
    }

    if (this.operation.equals("save")) {
      String id = this.information.getId();
      if (id.length() == 0) {
        if (this.file == null) {
          setJson(false, "请上传模板文件!");
          return "json";
        }
        if ((this.contentType == null) || (!(this.contentType.equals("text/html")))) {
          setJson(false, "请上传HTML文件!");
          return "json";
        }
      }
      try {
        iu.save(this.information, this.node, this.file, this.elements, su);
      } catch (Exception e) {
        e.printStackTrace();
        setJson(false, "保存失败!");
      }
    }

    if ("delete".equalsIgnoreCase(this.operation)) {
      try {
        iu.delete(this.node, su);
        super.setJson(true, "删除成功!");
      } catch (Exception localException1) {
        super.setJson(false, "删除失败!");
      }
    }

    return "json";
  }
}