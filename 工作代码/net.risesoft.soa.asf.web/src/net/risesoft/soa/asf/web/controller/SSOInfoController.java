package net.risesoft.soa.asf.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.risesoft.soa.asf.web.helper.SSOInfoHelper;
import net.risesoft.soa.framework.bizlog.PageRecord;
import net.risesoft.soa.framework.service.sso.log.LoginLog;
import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.TicketState;
import org.jasig.cas.ticket.registry.TicketRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/ssoinfo"})
public class SSOInfoController
{

  @Autowired
  private SSOInfoHelper helper;

  @Autowired
  private TicketRegistry ticketRegistry;

  @RequestMapping
  public String index()
  {
    return "redirect:ssoinfo/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index"})
  public void index(HttpServletRequest req, Map<String, String> model) {
    model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"loginLogs.do"})
  public ModelAndView loginLogsView(HttpServletRequest req) {
    ModelAndView mav = new ModelAndView("ssoinfo/loginlog");
    mav.addObject("AppRoot", req.getContextPath());
    return mav;
  }

  @RequestMapping({"loginLogs.json"})
  @ResponseBody
  public PageRecord<LoginLog> loginLogs(@RequestParam(value="keyword", defaultValue="") String keyword, @RequestParam(value="start", defaultValue="0") int start, @RequestParam(value="limit", defaultValue="20") int limit)
  {
    return this.helper.findLoginLogAsPage(keyword, start, limit);
  }
  @RequestMapping({"onlineUsers.json"})
  @ResponseBody
  public List<Object> onlineUsers(@RequestParam(value="keyword", defaultValue="") String keyword) {
    List model = new ArrayList();
    System.out.println("---------------------111111111");
    Collection<Ticket> tickets = this.ticketRegistry.getTickets();
    System.out.println("------------------------222222222222");
    int index = 0;
    for (Ticket ticket : tickets)
      if ((ticket instanceof TicketGrantingTicket)) {
        TicketGrantingTicket tgt = (TicketGrantingTicket)ticket;
        if (!tgt.isExpired())
        {
          Map attributes = tgt.getAuthentication().getPrincipal().getAttributes();
          String loginName = tgt.getAuthentication().getPrincipal().getId();
          String DN = (String)this.helper.decode(attributes.get("DN"));
          String NAME = (String)this.helper.decode(attributes.get("NAME"));
          if (this.helper.matches(keyword, new String[] { loginName, DN, NAME })) {
            Map map = new HashMap();
            map.put("id", tgt.getId());
            map.put("loginName", loginName);
            if ((ticket instanceof TicketState)) {
              TicketState ts = (TicketState)ticket;
              map.put("lastTimeUsed", Long.valueOf(ts.getLastTimeUsed()));
            } else {
              map.put("lastTimeUsed", Long.valueOf(tgt.getCreationTime()));
            }
            map.put("loginTime", Long.valueOf(tgt.getCreationTime()));
            map.put("DN", DN);
            map.put("NAME", NAME);
            map.put("IDNUM", attributes.get("IDNUM"));
            map.put("logonServices", this.helper.getLogonServices(tgt));
            map.put("index", Integer.valueOf(++index));
            model.add(map);
          }
        }
      }
    return model;
  }
  @RequestMapping({"logout.do"})
  @ResponseBody
  public String logout(@RequestParam(value="ticketIds", defaultValue="") String ticketIds) {
    String[] ticketIdArray = ticketIds.split(",");
    for (String id : ticketIdArray) {
      Ticket ticket = this.ticketRegistry.getTicket(id);
      if ((ticket != null) && ((ticket instanceof TicketGrantingTicket))) {
        TicketGrantingTicket tgt = (TicketGrantingTicket)ticket;
        tgt.expire();
        this.ticketRegistry.deleteTicket(id);
      }
    }
    return "{success:true, msg:'ok'}";
  }
}