package com.wxpt.action.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.Member;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.UserService;

public class CeShiAction extends ActionSupport{
  int enterId=1;
  @Autowired
	MemberService memberService;
  String fromUsername="oAAybjrCwpFlsFb-1x-25yeMSKW0";
  @Autowired
	UserService userService;
  String keyword="微商城";
public String execute() throws Exception {
	// TODO Auto-generated method stub
	  


		/*boolean f = pidui(168, enterId);
		if (f == false) {
			String contentStr = "亲。。。不好意思，此功能正在开发中！！";
			String msgType = "text";
			String resultStr = textTpl.format(textTpl,
					fromUsername, toUsername, time, msgType,
					contentStr);
			this.print(resultStr);
		}*/
	System.out.println(memberService.checkMember(enterId, fromUsername).size());
		if (memberService.checkMember(enterId, fromUsername).size() == 0) {
			String contentStr = "";
			String msgType = "text";
			contentStr = "您目前不是会员，请输入'hy'》》》注册会员！！";
			/*String resultStr = textTpl.format(textTpl,
					fromUsername, toUsername, time, msgType,
					contentStr);
			this.print(resultStr);*/
		}

		Member m = memberService.checkMember(enterId, fromUsername)
				.get(0);
		System.out.println(m.getUsername());
		if (m.getUsername() == null) {
			String contentStr = "";
			String msgType = "text";
			contentStr = "微商城欢迎您，输入'hy'》》》完善资料！！";
			/*String resultStr = textTpl.format(textTpl,
					fromUsername, toUsername, time, msgType,
					contentStr);
			this.print(resultStr);*/
		}
		System.out.println(m.getMemberFreeze());
		if (m.getMemberFreeze() == 0) {
			String contentStr = "";
			String msgType = "text";
			contentStr = "微商城欢迎您，您的会员状态已被冻结！！";
			/*String resultStr = textTpl.format(textTpl,
					fromUsername, toUsername, time, msgType,
					contentStr);
			this.print(resultStr);*/
		}
		Keywords kwords = userService.getKeyBysendContent(enterId,
				keyword);
		if (kwords != null) {
			String title = "";
			String imageUrl = "";
			String ecoution = "";
			List<Keywordexplicit> w = userService
					.getkExplicitByEkey(enterId,
							kwords.getKeywordId(), 0);
			System.out.println(w.size());
			if (w.size() != 0) {
				Keywordexplicit wp = w.get(0);
				title = wp.getTitle();
				imageUrl = wp.getEcontent();
				ecoution = wp.getEinstruction();
			}

			String msgType1 = "news";
			String url1 = "www.uniqyw.com/wxpt/site/web/index?memberName="
					+ m.getUsername()
					+ "&memberId="
					+ m.getMemberId() + "&enterId=" + enterId;
			/*String resultStr1 = textTpl1.format(textTpl1,
					fromUsername, toUsername, TimeUtil.getTime(),
					msgType1, 1, title, ecoution,
					"http://www.uniqyw.com/wxpt/web/images/"
							+ imageUrl, url1);
			this.print(resultStr1);*/

		}

	
	return super.execute();
}	

}
