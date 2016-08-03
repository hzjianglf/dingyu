package com.ibm.struts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.hibernate.GroupInfo;
import com.ibm.hibernate.GroupInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class GroupManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	List<GroupInfo> pageGroupList = new ArrayList<GroupInfo>();
	GroupInfoDAO dao = new GroupInfoDAO();
	private int planNum = 0; 
	
	public int getPlanNum() {
		return planNum;
	}

	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	public List<GroupInfo> getPageGroupList() {
		return pageGroupList;
	}

	public void setPageGroupList(List<GroupInfo> pageGroupList) {
		this.pageGroupList = pageGroupList;
	}

	/**
	 * @return
	 */
	public String list() {
		
		pageGroupList = dao.findAll();
		planNum = pageGroupList.size();
		
		return SUCCESS;
	}
}