package lmd.extend.jicheng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lmd.extend.jicheng.dao.DaoImp;
import lmd.extend.jicheng.util.GetDaiBan;
import lmd.extend.jicheng.util.PostTool;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egov.appservice.ac.exception.AccessControlException;
import egov.appservice.asf.exception.ServiceClientException;
@Controller
@RequestMapping
public class JichengController{
	@Autowired
	  private SessionFactory sessionFactory;
	
/*  @RequestMapping({"/index.do"})
  public void index(HttpServletResponse res, HttpServletRequest req){
    try{
      res.sendRedirect("/jsp/index.jsp");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/
 
 
  
  
  @RequestMapping({"/email.html"})
  public ModelAndView getResource(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  System.out.println("emailList---================================"); 
	  ModelAndView mv = new ModelAndView("../../jicheng/emailList"); 
	   return mv;
  }
  @RequestMapping({"/getemails.html"})
  public void email(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		DaoImp daoImp = new DaoImp(); 
		String status = req.getParameter("status");
		String name = req.getParameter("name");
		if (!(name==null||name.equals("null"))) {
			if(name.contains("/")){
				name = name.split("/")[1];
			}
			String urlName = "";
			int emailNum = 0;

			try {
				urlName = daoImp.getUrlName(name);
				
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			
			PostTool.sendGet("http://10.10.10.6/coremail/main.jsp?sid="+urlName, "");
			
			if (status!=null&&status.equals("emailNum")) {
				
				try {
					
					emailNum = daoImp.getNum(name);
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				out.println(emailNum);
				return;
			}
			
			
			try {
				
				urlName = daoImp.getUrlName(name);
				
			} catch (Exception e) {

				e.printStackTrace();
				out.println("字符串解析出错");
				return;
			}
			
			res.sendRedirect("http://10.10.10.6/coremail/main.jsp?sid="+urlName); 
			out.flush();
			out.close();
			return;
		}
		out.println("字符串解析出错"); 
	
  }
  @RequestMapping({"/daiban.html"})
  public ModelAndView daiban(HttpServletResponse res, HttpServletRequest req) throws ServiceClientException, IOException, AccessControlException{
	  System.out.println("daiban---================================"); 
	  String userLogin = req.getParameter("name");
	    //System.out.println("userLogin------"+userLogin);
	    if(userLogin.indexOf("/")>0){
			userLogin=userLogin.substring(userLogin.indexOf("/")+1,userLogin.length());
			
		}
	  
	  String loginjp = GetDaiBan.getLoginjp(userLogin);
	   System.out.println(loginjp+"====================");
	       String xdzzwdb = GetDaiBan.getXDzzwDb(loginjp);
	       System.out.println(xdzzwdb+"=--------------=============-----------");
	       String jsyddb = GetDaiBan.getJsydDb(loginjp);
	       String ghbjdb = GetDaiBan.getGhbjDb(loginjp);
	       String gdbhdb = GetDaiBan.getGdbhDb(loginjp);
	       String zjggdb = GetDaiBan.getZjggDb(loginjp);
	       ArrayList shenpiAndDaiban = GetDaiBan.getShenpiAndDaibanx(loginjp);
	       String shenpi = (String)shenpiAndDaiban.get(0);
	       String daiban = (String)shenpiAndDaiban.get(1);
	  ModelAndView mv = new ModelAndView("../../jicheng/daiban");
	  mv.addObject("xdzzwdb", xdzzwdb);
	  mv.addObject("loginjp", loginjp);
	  mv.addObject("userLogin", userLogin);
	  mv.addObject("jsyddb", jsyddb);
	  mv.addObject("ghbjdb", ghbjdb);
	  mv.addObject("gdbhdb", gdbhdb);
	  mv.addObject("zjggdb", zjggdb);
	  mv.addObject("shenpi", shenpi);
	  mv.addObject("daiban", daiban);
	   return mv;
  }
  
}