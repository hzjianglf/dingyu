package com.ibm.struts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.UserInfo;
import com.ibm.hibernate.UserInfoDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserManageAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(UserManageAction.class);
	
	private static final long serialVersionUID = 1L;

	UserInfoDAO dao = new UserInfoDAO();
	List<UserInfo> pageUserList = new ArrayList<UserInfo>();

	private int rowsPerPage = 20;// 每页显示几条  
    private int page = 1; // 默认当前页    
    private int totalPage = 0;// 总共多少页    
    private int planNum = 0;// 总过多少条 
    
    private String comment;    
    private String username;    
    private String password;    
    private String confirmpassword;    
    private String groups;    
    private String email;    
    private String selectedItemIds;    
    private String newpassword;    
    private String color;
    
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(String selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserInfo> getPageUserList() {
		return pageUserList;
	}

	public void setPageUserList(List<UserInfo> pageUserList) {
		this.pageUserList = pageUserList;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPlanNum() {
		return planNum;
	}

	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	/**
	 * @return
	 */
	public String list() {
		pageUserList = dao.findUserInfoByPage(page, rowsPerPage);  
        totalPage = dao.getUserInfoTotalPage(rowsPerPage);  
        planNum = dao.getUserInfoNum();
        
		return SUCCESS;
	}
	
	public String userreg() {
		
		return SUCCESS;
	}
	
	public String registry() {
		if (groups.equalsIgnoreCase("select")) {
			comment = "用户组不能为空。";
			
			return ERROR;
		}
		
		if (password.equals(confirmpassword)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(username);
			userInfo.setPassword(password);
			userInfo.setGroups(groups);
			userInfo.setEmail(email);
			userInfo.setStatus("0");
			userInfo.setFailcount(new Integer(0));
			userInfo.setLastlogintime("N/A");
			if (dao.save(userInfo)) {
				comment = "用户 " + username +  " 注册成功，所属用户组为 " + groups;

				pageUserList = dao.findUserInfoByPage(page, rowsPerPage);  
		        totalPage = dao.getUserInfoTotalPage(rowsPerPage);  
		        planNum = dao.getUserInfoNum();	
		        
		        log.info(comment);
		        return SUCCESS;
			} else {
				comment = "用户名 " + username + " 已存在。";
				log.error(comment);
				return ERROR;
			}				       
			
		} else {
			comment = "用户 " + username +  " 密码和确认密码不一致，请重新输入。";
			log.error(comment);
			return ERROR;
		}			
	}
	
	public String delete() {	

		String userNames[] = selectedItemIds.split(", ");
		for (int i=0; i< userNames.length;i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(userNames[0]);
			dao.delete(userInfo);
		}
		
		pageUserList = dao.findUserInfoByPage(page, rowsPerPage);  
        totalPage = dao.getUserInfoTotalPage(rowsPerPage);  
        planNum = dao.getUserInfoNum();	 
        
		return SUCCESS;
	}
	
	public String modifypassword(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		username = (String)session.get("username");
		
		UserInfo uinfo = new UserInfo();
		for(Iterator<UserInfo> i = dao.findByUsername(username).iterator(); i.hasNext();) {
			uinfo = new UserInfo();
			uinfo = (UserInfo)i.next();			
		}
		
		if (password.equals(uinfo.getPassword())) {
			
			if (!newpassword.equalsIgnoreCase("")) {
				if (newpassword.equals(confirmpassword)) {
					uinfo.setPassword(newpassword);
					dao.update(uinfo);
					comment = "密码修改成功！";
					color = "Green";
					
					return SUCCESS;
				} else {
					comment = "新密码与确认密码不一致，请重新输入！";
					color = "Red";
					
					return ERROR;
				}
				
			} else {
				comment = "新密码不能为空！";
				color = "Red";
				
				return ERROR;				
			}
		} else {
			comment = "当前密码错误，请重新输入！";
			color = "Red";
			
			return ERROR;
		}
		
	}
	
	public String modpwd(){
		
		return SUCCESS;
	}

}