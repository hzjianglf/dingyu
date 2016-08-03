package com.wxpt.action.site;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.site.service.MemberService;

public class AbcAction extends ActionSupport{
	@Autowired
	MemberService memberService;
	public String execute() throws Exception {
		memberService.checkMember(2, "oAAybjrCwpFlsFb-1x-25yeMSKW0");
		return null;
	};
	
}
