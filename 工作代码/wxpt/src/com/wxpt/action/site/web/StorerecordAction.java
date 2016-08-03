package com.wxpt.action.site.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.MyTaskFile;
import com.wxpt.common.PageBean;
import com.wxpt.site.entity.Storerecord;
import com.wxpt.site.service.MemberService;

public class StorerecordAction extends ActionSupport  {
	@Autowired
	MemberService memberService;
	private  List<Storerecord> listStorerecord=new ArrayList<Storerecord>();
	private int memberId;
	private int enterId;
    private int enterpriseId;//企业的id
    private String carId;

	private int curPage = 1;
	private PageBean pageBean = new PageBean();
	private int pageSize = 5;
	private int nextPage;
	public  String storerecordMessage(){

		try {
			pageBean=memberService.spiltPageStorerecord(enterId, curPage, pageSize,memberId);
			 nextPage=pageBean.getNextPage();
			return "listStorerecord";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listStorerecord";
	}
	
	
	
	
	private int idStorerecord;
	
	private Storerecord storerecord;
	
	
	public Storerecord getStorerecord() {
		return storerecord;
	}


	public void setStorerecord(Storerecord storerecord) {
		this.storerecord = storerecord;
	}


	public String singleStorerecord(){
		storerecord=memberService.findStorerecordByid(idStorerecord, enterId);
		return "storerecordSigle";
	}
	
	
	public int getIdStorerecord() {
		return idStorerecord;
	}


	public void setIdStorerecord(int idStorerecord) {
		this.idStorerecord = idStorerecord;
	}


	public String getCarId() {
		return carId;
	}
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	public int getEnterId() {
		return enterId;
	}
	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}
	public List<Storerecord> getListStorerecord() {
		return listStorerecord;
	}
	public void setListStorerecord(List<Storerecord> listStorerecord) {
		this.listStorerecord = listStorerecord;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	
	
	
}
