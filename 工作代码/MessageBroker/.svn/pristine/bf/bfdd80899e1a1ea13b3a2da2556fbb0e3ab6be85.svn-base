package com.ibm.struts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.UserInfo;
import com.ibm.hibernate.UserInfoDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
	
	
public class LoginAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	private static final long serialVersionUID = 1L;	
	private String j_username;	
	private String j_password;	
	private String comment;	
    private String choiceButton;
	UserInfoDAO dao = new UserInfoDAO();

	public String getChoiceButton() {
		return choiceButton;
	}

	public void setChoiceButton(String choiceButton) {
		this.choiceButton = choiceButton;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String jUsername) {
		j_username = jUsername;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String jPassword) {
		j_password = jPassword;
	}
	
	public String login() {
		
		return SUCCESS;
	}
	/**
	 * @return
	 */
	public String check() {		
		UserInfo uinfo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		for(Iterator<UserInfo> i = dao.findByUsername(j_username).iterator(); i.hasNext();) {
			uinfo = new UserInfo();
			uinfo = (UserInfo)i.next();			
		}

		String datapath = ServletActionContext.getServletContext().getRealPath("/data");				
		System.getProperties().setProperty("WMB_MONITOR_DATA", datapath);
		String context = ServletActionContext.getServletContext().getContextPath();
		System.getProperties().setProperty("WMB_MONITOR_CONTEXTPATH", context);
		
		if (uinfo == null) {
			comment = "用户 " + j_username + " 不存在，请确认后重新登录。";
			log.error(comment);
			return ERROR;
		} 		
		
		int failure = uinfo.getFailcount().intValue();
		if (failure == -1) {
			
			Date lockDate;
			try {
				lockDate = sdf.parse(uinfo.getLocktime());			  
				Calendar g1 = Calendar.getInstance();   
				g1.setTime(lockDate);
				
				g1.add(Calendar.MINUTE, 30); 
				Date lockTime = g1.getTime();   
				
				Calendar g2 = Calendar.getInstance();
				Date curTime = g2.getTime();
				if (curTime.compareTo(lockTime) < 0) {								
					comment = "用户 " + j_username + " 被锁定，请等待30分钟后再次登录。";	
					log.error(comment);
					return ERROR;
				} else {
					
					uinfo.setLocktime("");
					uinfo.setFailcount(new Integer(0));
					dao.update(uinfo);
				}
				
			} catch (ParseException e) {
				//e.printStackTrace();
				log.error(e.getMessage());
			} 				
			
		}
		
		if (j_password.equals("")) {
			comment = "密码不能为空，请输入密码后登录。";
			log.error("用户 " + j_username + " " + comment);
			return ERROR;
		} 
		
		if (!j_password.equals(uinfo.getPassword())) {
			
			if (!j_username.equalsIgnoreCase("admin")) {
				
				if (failure >= 4) {
					uinfo.setFailcount(new Integer(-1));
					
					Calendar day = Calendar.getInstance();							
					String locktime = sdf.format(day.getTime());
					
					uinfo.setLocktime(locktime);
				} else {
					uinfo.setFailcount(new Integer(failure + 1));
				}
				
				dao.update(uinfo);
			}
			
			comment = "密码错误，请确认后重新登录。";	
			log.error("用户 " + j_username + " " + comment);
			return ERROR;			
		}
				
		if (j_password.equals(uinfo.getPassword())) {
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			if (session.get("username") == null) {
				session.put("username",j_username);
				session.put("groups",uinfo.getGroups());
				
				uinfo.setStatus("1");
				uinfo.setFailcount(new Integer(0));
				
				Calendar day = Calendar.getInstance();									
				String lastLogintime = sdf.format(day.getTime());
				uinfo.setLastlogintime(lastLogintime);
				dao.update(uinfo);
								
				comment = "用户 " + j_username + " 登录成功";
				log.info(comment);
				return SUCCESS;
			} else {
				if (((String)session.get("username")).equals(j_username)) {
					
					return "transfer";
				} else {
					session.put("username",j_username);
					session.put("groups",uinfo.getGroups());
					
					uinfo.setStatus("1");
					
					Calendar day = Calendar.getInstance();										
					String lastLogintime = sdf.format(day.getTime());
					uinfo.setLastlogintime(lastLogintime);
					dao.update(uinfo);
					
					comment = "用户 " + j_username + "登录成功";
					log.info(comment);
					return SUCCESS;
				}
			}											
		}
		
		return SUCCESS;	
	}
	
	
	public String transfer(){
		String datapath = ServletActionContext.getServletContext().getRealPath("/data");
		System.getProperties().setProperty("WMB_MONITOR_DATA", datapath);					
		String context = ServletActionContext.getServletContext().getContextPath();
		System.getProperties().setProperty("WMB_MONITOR_CONTEXTPATH", context);	
		
		if (choiceButton.endsWith("logoutOtherUser")) {
				
			return SUCCESS;
		} else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.clear();
			
			return ERROR;
		}		
	}
}