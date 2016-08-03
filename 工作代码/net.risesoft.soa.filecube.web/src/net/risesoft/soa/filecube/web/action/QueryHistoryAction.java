package net.risesoft.soa.filecube.web.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.List;
import java.util.Map;
import net.risesoft.soa.filecube.service.QueryHistoryService;
import net.risesoft.soa.framework.session.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope("prototype")
@Controller
public class QueryHistoryAction extends BaseAction
{

  @Autowired
  private QueryHistoryService queryHistoryService;

  public String preList()
  {
    List queryHistories = this.queryHistoryService.findByUserUid(this.sessionUser.getUserUID());
    ActionContext.getContext().getContextMap().put("queryHistories", queryHistories);
    return "preList";
  }
}