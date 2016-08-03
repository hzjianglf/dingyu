package com.wxpt.common;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxpt.action.site.GetCookie;
import com.wxpt.site.entity.Surquestion;
import com.wxpt.site.service.SurquestionService;

public class SurQuestionTag extends TagSupport {
	private String check;
	private String selectid;

	/**
	 * 遇到标签开始时会呼叫的方法
	 * @return  合法的返回值是 EVAL_BODY_INCLUDE:不显示标签间的文字和SKIP_BODY:显示标签间的文字
	 * @throws JspException 页面输出不正确，报此异常
	 */
	@SuppressWarnings("all")
	public int doStartTag()  {
		GetCookie cookies = new GetCookie();
		int enterId = cookies.getCookie();
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		ApplicationContext ctx = (ApplicationContext) WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext()); 
		SurquestionService surquestionServices=(SurquestionService) ctx.getBean("surquestionService");
		try{
			
			List<Surquestion> list =surquestionServices.findAll(enterId);
			
			//list.add();
			if(check.equals("search")){
				sb.append("<select  class='ww' id='select_parentID' name='"+selectid+"'>");
			for(int i=0;i<list.size();i++){
				Surquestion squestion = (Surquestion) list.get(i);
				sb.append("<option value="+squestion.getSurquestionId()+">");
				sb.append(squestion.getSurquestionContent());
				sb.append("</option>");
			}		
			sb.append("</select>");
			out.print(sb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return this.EVAL_PAGE;
	}
	



	private Object getRequestContext() {
		
		return null;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getSelectid() {
		return selectid;
	}
	public void setSelectid(String selectid) {
		this.selectid = selectid;
	}
	
	
}
