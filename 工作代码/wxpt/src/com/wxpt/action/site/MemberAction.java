package com.wxpt.action.site;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.PageBean;

import com.opensymphony.xwork2.ActionSupport;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.PageBean;
import com.wxpt.common.QuickMark;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.MemberDao;
import com.wxpt.site.entity.Activity;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Message;

import com.wxpt.site.entity.pojo.Member2;
import com.wxpt.site.entity.pojo.Message2;
import com.wxpt.site.service.MemberService;

/*@SuppressWarnings("serial")
 @Results({
 @Result(name = "success", type = "json", params = { "root", "result" })
 })
 @ParentPackage("json-default")*/
public class MemberAction extends ActionSupport {
	final HttpServletRequest final_request = ServletActionContext.getRequest();
	final HttpServletResponse final_response = ServletActionContext
			.getResponse();
	@Autowired
	MemberService memberService;
	MemberDao memberDao;
	Activity activityPro;
	List<Activity> listActivity;
	PageBean pageBean = new PageBean();
	GetCookie cookies = new GetCookie();
	int enterId2 = cookies.getCookie();
	private String activityTitle;
	private String activityContent;
	private String activityStartTime;
	private String activityEndTime;
	private String imageUrl;
	private String activityId;
	// 会员注册 测试
	JSONArray result;
	protected HttpServletResponse response = ServletActionContext.getResponse();
	private String page;// 当前第几页
	private int listCount; // 总数据条数
	private int pageCount;// 总页数
	private int currentpage = 1;// 当前页
	static int PAGE_SIZE = 5;// 每页数据条数
	static int PAGE_SIZE2 = 5;// 每页数据条数
	private String rows;// 每页显示的记录数
	String filepath = ServletActionContext.getServletContext().getRealPath(
			"web")
			+ "/images/";

	/*
	 * @Override public String execute() throws Exception { // TODO
	 * Auto-generated method stub return "huiyuan"; }
	 */
	public String huiyuan() {
		return "huiyuan";
	}

	public void addMember() {
		String addTime = TimeUtil.getTimes();
		String[] Time = addTime.split("-");
		String a = new SimpleDateFormat("MM-dd").format(new Date());
		String endTime = (Integer.parseInt(Time[0]) + 3) + "-" + a;
		String cardId = "NO."
				+ new SimpleDateFormat("yyyyMMddHHss").format(new Date());
		int i = memberService.addMember(enterId2, cardId, "566565",
				"dsjhdjshdjsh", addTime, endTime, 0);
		if (i == 1) {
			System.out.println("注册成功");
			QuickMark.createQuickMaik(cardId, filepath, cardId);
			System.out.println(QuickMark.analysisQuickMark(filepath + cardId
					+ ".jpg"));
		} else {
			System.out.println("注册失败");
		}
	}

	public String jump() {
		return "huiyuan";
	}

	// 取会员数据全部
	Member2 member2;
	List<Member> listm = new ArrayList<Member>();

	List<Member2> listm2 = new ArrayList<Member2>();

	public String getmemberall() {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {

			out = response.getWriter();

		} catch (IOException e) {

			e.printStackTrace();
		}

		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page); // 当前第几页 //每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// 每页显示条数
		int start = (intPage - 1) * number;

		listCount = memberService.getmembercount(enterId2);

		listm = memberService.getmemberall(enterId2, start, number);
		if (listm.size() == 0) {
			out.print("{\"total\":" + 0 + ",\"rows\":" + "" + "}");
		} else {
			for (int i = 0; i < listm.size(); i++) {
				member2 = new Member2();

				member2.setAddress(listm.get(i).getAddress());
				String addtime = sdf.format(listm.get(i).getAddTime());
				member2.setAddTime(addtime);
				try {
					if (listm.get(i).getAge() != 0) {

						String age = Integer.toString(listm.get(i).getAge());

						member2.setAge(age);

					} else {

						member2.setAge("0");

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				member2.setCardId(listm.get(i).getCardId());
				String endtime = sdf.format(listm.get(i).getEndTime());
				member2.setEndTime(endtime);
				try {
					if (listm.get(i).getMemberFreeze() == 0) {

						member2.setMemberFreeze("冻结");

					} else {

						member2.setMemberFreeze("正常（未冻结）");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (listm.get(i).getMemberPoints() >= 0
						&& listm.get(i).getMemberPoints() <= 200) {

					member2.setMemberGrade("普通卡");

				} else if (listm.get(i).getMemberPoints() <= 400) {

					member2.setMemberGrade("银卡");

				} else if (listm.get(i).getMemberPoints() <= 600) {

					member2.setMemberGrade("金卡");

				} else {

					member2.setMemberGrade("贵宾卡");
				}

				member2.setMemberId(listm.get(i).getMemberId());

				String memberpoint = Integer.toString(listm.get(i)
						.getMemberPoints());
				member2.setMemberPoints(memberpoint);

				member2.setPassword(listm.get(i).getPassword());
				member2.setPhone(listm.get(i).getPhone());

				member2.setSaveMoney(listm.get(i).getSaveMoney());

				if (listm.get(i).getSex() == 1) {
					member2.setSex("女");

				} else if (listm.get(i).getSex() == 2) {

					member2.setSex("男");
				} else {

					member2.setSex("未填写");

				}

				member2.setUsername(listm.get(i).getUsername());
				member2.setWeixinId(listm.get(i).getWeixinId());

				listm2.add(member2);

			}

			JsonConfig jsonConfig = new JsonConfig();
			JSONArray jsonArrayFromList = JSONArray.fromObject(listm2);
			out.print("{\"total\":" + listCount + ",\"rows\":"
					+ jsonArrayFromList.toString() + "}");

		}

		out.flush();
		out.close();
		return SUCCESS;
	}

	private List<Member> listMember = new ArrayList<Member>();
	private int member_freeze = -1;
	private int member_grade;
	private String memberName;
	private int number;
	private int start;
	private String add_time;
	private String weixin_id;

	// /解冻
	private String memberId;
	private int jifen1;
	Member member = new Member();;
	Member memberr;

	public void jiedong() {
		String value = "";
		try {
			int memberIdint = Integer.parseInt(memberId);
			member = memberService.jiedong(enterId2, memberIdint);
			System.out.println(member.getMemberFreeze());
			member.setMemberFreeze(1);
			// System.out.println(member.getMemberFreeze()+"=====111111111111111111");

			memberService.update(enterId2, member);
			value = "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 冻结
	public void dongjie() {

		String value = "";
		try {
			int memberIdint = Integer.parseInt(memberId);
			member = memberService.jiedong(enterId2, memberIdint);
			System.out.println(member.getMemberFreeze());
			member.setMemberFreeze(0);
			// System.out.println(member.getMemberFreeze()+"=====111111111111111111");

			memberService.update(enterId2, member);
			value = "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void findMember() {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {

			out = response.getWriter();

		} catch (IOException e) {

			e.printStackTrace();
		}
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page); // 当前第几页 //每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "5"
				: rows);// 每页显示条数
		int start = (intPage - 1) * number;
		listm = memberService.findMember(member_freeze, -1, add_time,
				weixin_id, memberName, start, number, enterId2);
		for (int i = 0; i < listm.size(); i++) {

			member2 = new Member2();
			member2.setAddress(listm.get(i).getAddress());
			String addtime = sdf.format(listm.get(i).getAddTime());
			member2.setAddTime(addtime);
			try {
				if (listm.get(i).getAge() != 0) {

					String age = Integer.toString(listm.get(i).getAge());

					member2.setAge(age);

				} else {

					member2.setAge("0");

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			member2.setCardId(listm.get(i).getCardId());
			String endtime = sdf.format(listm.get(i).getEndTime());
			member2.setEndTime(endtime);
			try {
				if (listm.get(i).getMemberFreeze() == 0) {

					member2.setMemberFreeze("冻结");

				} else {

					member2.setMemberFreeze("正常（未冻结）");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (listm.get(i).getMemberPoints() >= 0
					&& listm.get(i).getMemberPoints() <= 200) {

				member2.setMemberGrade("普通卡");

			} else if (listm.get(i).getMemberPoints() <= 400) {

				member2.setMemberGrade("银卡");

			} else if (listm.get(i).getMemberPoints() <= 600) {

				member2.setMemberGrade("金卡");

			} else {

				member2.setMemberGrade("贵宾卡");
			}

			member2.setMemberId(listm.get(i).getMemberId());

			String memberpoint = Integer.toString(listm.get(i)
					.getMemberPoints());
			member2.setMemberPoints(memberpoint + "点");

			member2.setPassword(listm.get(i).getPassword());
			member2.setPhone(listm.get(i).getPhone());

			member2.setSaveMoney(listm.get(i).getSaveMoney());

			if (listm.get(i).getSex() == 1) {
				member2.setSex("女");

			} else if (listm.get(i).getSex() == 2) {

				member2.setSex("男");
			} else {

				member2.setSex("未填写");

			}

			member2.setUsername(listm.get(i).getUsername());
			member2.setWeixinId(listm.get(i).getWeixinId());

			listm2.add(member2);

		}
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArrayFromList = JSONArray.fromObject(listm2);
		listCount = memberService.MemberCount(member_freeze, -1, add_time,
				weixin_id, memberName, start, 10, enterId2);
		;
		out.print("{\"total\":" + listCount + ",\"rows\":"
				+ jsonArrayFromList.toString() + "}");
		out.flush();
		out.close();

	}

	// ///////取留言

	List<Message> listmessage = new ArrayList<Message>();
	List<Message> listhui = new ArrayList<Message>();
	List<Message2> listmessage2 = new ArrayList<Message2>();

	public List<Message2> getListmessage2() {
		return listmessage2;
	}

	public void setListmessage2(List<Message2> listmessage2) {
		this.listmessage2 = listmessage2;
	}

	PageBean pb = new PageBean();
	int memberIdint;

	public Member getMemberr() {
		return memberr;
	}

	public void setMemberr(Member memberr) {
		this.memberr = memberr;
	}

	public String quliuyan() {

		memberIdint = Integer.parseInt(memberId);

		try {

			pb = memberService.quliuyan(enterId2, currentpage, PAGE_SIZE2,
					memberIdint);

			/*
			 * for(int i=0;i<listmessage.size();i++){
			 * 
			 * Message2 message2 = new Message2();
			 * 
			 * listhui =
			 * memberService.quhuifuliuyan(listmessage.get(i).getMessageId());
			 * message2.setListhui(listhui);
			 * message2.setMember(listmessage.get(i).getMember());
			 * message2.setMessageContent
			 * (listmessage.get(i).getMessageContent());
			 * message2.setMessageId(listmessage.get(i).getMessageId());
			 * message2
			 * .setMessageParentid(listmessage.get(i).getMessageParentid());
			 * message2.setMessageTitle(listmessage.get(i).getMessageTitle());
			 * message2.setTime(listmessage.get(i).getTime());
			 * message2.setMessageType(listmessage.get(i).getMessageType());
			 * 
			 * listmessage2.add(message2); }
			 */

			for (int i = 0; i < pb.getList().size(); i++) {
				Message message = (Message) pb.getList().get(i);
				Message2 message2 = new Message2();

				listhui = memberService.quhuifuliuyan(enterId2,
						message.getMessageId());

				message2.setListhui(listhui);
				message2.setMember(message.getMember());
				message2.setMessageContent(message.getMessageContent());
				message2.setMessageId(message.getMessageId());
				message2.setMessageParentid(message.getMessageParentid());
				message2.setMessageTitle(message.getMessageTitle());
				message2.setTime(message.getTime());
				message2.setMessageType(message.getMessageType());

				listmessage2.add(message2);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "ajax";

	}

	public String quliuyan2() {
		int messageidint = Integer.parseInt(messageid);
		// int memberIdint = Integer.parseInt(memberId);

		try {

			Message mes = new Message();

			mes = memberService.getmessagebyid(enterId2, messageidint);

			pb = memberService.quliuyan(enterId2, currentpage, PAGE_SIZE2, mes
					.getMember().getMemberId());

			/*
			 * for(int i=0;i<listmessage.size();i++){
			 * 
			 * Message2 message2 = new Message2();
			 * 
			 * listhui =
			 * memberService.quhuifuliuyan(listmessage.get(i).getMessageId());
			 * message2.setListhui(listhui);
			 * message2.setMember(listmessage.get(i).getMember());
			 * message2.setMessageContent
			 * (listmessage.get(i).getMessageContent());
			 * message2.setMessageId(listmessage.get(i).getMessageId());
			 * message2
			 * .setMessageParentid(listmessage.get(i).getMessageParentid());
			 * message2.setMessageTitle(listmessage.get(i).getMessageTitle());
			 * message2.setTime(listmessage.get(i).getTime());
			 * message2.setMessageType(listmessage.get(i).getMessageType());
			 * 
			 * listmessage2.add(message2); }
			 */

			for (int i = 0; i < pb.getList().size(); i++) {
				Message message = (Message) pb.getList().get(i);
				Message2 message2 = new Message2();

				listhui = memberService.quhuifuliuyan(enterId2,
						message.getMessageId());

				message2.setListhui(listhui);
				message2.setMember(message.getMember());
				message2.setMessageContent(message.getMessageContent());
				message2.setMessageId(message.getMessageId());
				message2.setMessageParentid(message.getMessageParentid());
				message2.setMessageTitle(message.getMessageTitle());
				message2.setTime(message.getTime());
				message2.setMessageType(message.getMessageType());

				listmessage2.add(message2);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "ajax";

	}

	// ///////存储回复留言
	private String huifuneirong;
	private String messageid;

	public String huifuliuyan() {

		int messageidint = Integer.parseInt(messageid);
		try {
			Message mes = new Message();

			mes = memberService.getmessagebyid(enterId2, messageidint);

			Message mes2 = new Message();
			mes2.setMember(mes.getMember());
			mes2.setMessageTitle(mes.getMessageTitle());
			mes2.setTime(TimeUtil.getTime());
			mes2.setMessageContent(huifuneirong);
			mes2.setMessageParentid(messageidint);
			mes2.setMessageType(1);

			memberService.addMessage(enterId2, mes2);

			// String memberId = mes.getMember().getMemberId();

			int memberIdint = mes.getMember().getMemberId();

			try {

				pb = memberService.quliuyan(enterId2, currentpage, PAGE_SIZE2,
						memberIdint);// ////

				// for(int i=0;i<listmessage.size();i++){
				//
				// Message2 message2 = new Message2();
				//
				// listhui =
				// memberService.quhuifuliuyan(listmessage.get(i).getMessageId());
				// message2.setListhui(listhui);
				// message2.setMember(listmessage.get(i).getMember());
				// message2.setMessageContent(listmessage.get(i).getMessageContent());
				// message2.setMessageId(listmessage.get(i).getMessageId());
				// message2.setMessageParentid(listmessage.get(i).getMessageParentid());
				// message2.setMessageTitle(listmessage.get(i).getMessageTitle());
				// message2.setTime(listmessage.get(i).getTime());
				// message2.setMessageType(listmessage.get(i).getMessageType());
				//
				// listmessage2.add(message2);
				// }

				for (int i = 0; i < pb.getList().size(); i++) {
					Message message = (Message) pb.getList().get(i);
					Message2 message2 = new Message2();

					listhui = memberService.quhuifuliuyan(enterId2,
							message.getMessageId());

					message2.setListhui(listhui);
					message2.setMember(message.getMember());
					message2.setMessageContent(message.getMessageContent());
					message2.setMessageId(message.getMessageId());
					message2.setMessageParentid(message.getMessageParentid());
					message2.setMessageTitle(message.getMessageTitle());
					message2.setTime(message.getTime());
					message2.setMessageType(message.getMessageType());

					listmessage2.add(message2);

				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ajax";
	}

	// /验证一条
	public void yanzhengyitiao() {
		String value = "";
		int messageidint = Integer.parseInt(messageid);

		try {

			listhui = memberService.quhuifuliuyan(enterId2, messageidint);

			if (listhui == null || listhui.size() == 0) {

				value = "1";// 可以恢复

			} else {

				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");

				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();

				out.print(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 促销活动页面
	public String showActivity() {
		return "activity";
	}

	// 促销活动查询
	public void selectActivity() {

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {

			out = response.getWriter();

		} catch (IOException e) {

			e.printStackTrace();
		}
		pageBean = memberService.getByIdActivity(enterId2,
				Integer.parseInt(page), Integer.parseInt(rows));

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "activity", "couponses" });
		JSONArray jsonArrayFromList = JSONArray.fromObject(pageBean.getList(),
				jsonConfig);
		String json = jsonArrayFromList.toString();
		listCount = pageBean.getRecordCnt();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < pageBean.getList().size(); i++) {
			activityPro = (Activity) pageBean.getList().get(i);

			String jsons = "";
			jsons = json.substring(0, json.indexOf("}"));
			sb.append(jsons);
			sb.append(",\"imageTemp\":\"<img  width='80' height='80' src='\\\\wxpt\\\\web\\\\images\\\\"+enterId2+"\\\\"
					+ activityPro.getImageUrl().replace("/", "\\\\\\\\")
					+ "' ///>\"");
			sb.append("}");
			json = json.substring(json.indexOf("}") + 1);

		}
		sb.append("]");
		System.out.println(sb.toString());
		out.print("{\"total\":" + listCount + ",\"rows\":" + sb.toString()
				+ "}");
		out.flush();
		out.close();

	}

	// 促销活动添加
	public void addActivity() {

		try {

			/*
			 * String s1 =s.substring(0,4); String s2 =s.substring(5,7); String
			 * s3 =s.substring(8,10);
			 */

			String activityStartTimeg1 = activityStartTime.substring(0, 4);
			String activityStartTimeg2 = activityStartTime.substring(5, 7);
			String activityStartTimeg3 = activityStartTime.substring(8, 10);

			String activityEndTime1 = activityEndTime.substring(0, 4);
			String activityEndTime2 = activityEndTime.substring(5, 7);
			String activityEndTime3 = activityEndTime.substring(8, 10);

			activityPro = new Activity();
			activityPro.setActivityTitle(activityTitle);
			activityPro.setActivityContent(activityContent);
			activityPro.setActivityStarttime(activityStartTimeg1 + "-"
					+ activityStartTimeg2 + "-" + activityStartTimeg3);
			activityPro.setActivityEndtime(activityEndTime1 + "-"
					+ activityEndTime2 + "-" + activityEndTime3);
			activityPro.setImageUrl("1.png");
			memberService.addActivity(enterId2, activityPro);
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 促销活动update
	public void updateActivity() {
		try {

			activityPro = new Activity();
			activityPro.setActivityTitle(activityTitle);
			activityPro.setActivityContent(activityContent);

			String activityStartTimeg1 = activityStartTime.substring(0, 4);
			String activityStartTimeg2 = activityStartTime.substring(5, 7);
			String activityStartTimeg3 = activityStartTime.substring(8, 10);

			String activityEndTime1 = activityEndTime.substring(0, 4);
			String activityEndTime2 = activityEndTime.substring(5, 7);
			String activityEndTime3 = activityEndTime.substring(8, 10);

			activityPro.setActivityStarttime(activityStartTimeg1 + "-"
					+ activityStartTimeg2 + "-" + activityStartTimeg3);
			activityPro.setActivityEndtime(activityEndTime1 + "-"
					+ activityEndTime2 + "-" + activityEndTime3);
			// activityPro.setImageUrl("images/url.jpg");
			memberService.updateActivity(enterId2, activityPro,
					Integer.parseInt(activityId));
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FileUploadBean uploadBean = new FileUploadBean();
	private File upload;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void uploadfile() throws IOException {
		HttpServletResponse hs = ServletActionContext.getResponse();
		hs.setContentType("text/html;charset=utf-8");
		PrintWriter out = hs.getWriter();
		try {

			String name = "card.jpg";
			uploadBean.upLoad(name, upload,enterId2+"");

			out.print(1);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
		}
		out.flush();
		out.close();

	}

	// 删除活动

	public void delactivity() {
		String value = "";
		int activityidint = Integer.parseInt(activityId);
		try {

			Activity aac = memberService.getactivitybyid(enterId2,
					activityidint);
			String acurl = aac.getImageUrl();
			if (acurl.equals("1.png")) {

				memberService.delactivity(enterId2, activityidint);
			} else {
				memberService.delactivity(enterId2, activityidint);
				int di = uploadBean.deletefile(enterId2+"/"+acurl);

			}

			value = "1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			value = "-1";
			e.printStackTrace();
		} finally {

			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);

				out.flush();
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 上传海报

	FileUploadBean upload2 = new FileUploadBean();
	private File haibao;

	Activity ac1 = new Activity();

	public void shangchuanhaibao() {
		String value = "";
		try {

			if (haibao == null || haibao.equals("")) {

				value = "0";

			} else {
				String shijian = new SimpleDateFormat("yyyyMMddHHss")
						.format(new Date());
				String name = activityId + shijian + ".jpg";
				int activityidint = Integer.parseInt(activityId);
				ac1 = memberService.getactivitybyid(enterId2, activityidint);
				ac1.setImageUrl(name);
				memberDao.updateActivity(enterId2, ac1, activityidint);

				uploadBean.upLoad(name, haibao,enterId2+"");

				value = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void updateJiFen() {
		System.out.println(memberId);
		System.out.println(jifen1);
		String sql = "update wxpt" + enterId2 + ".member set `member_points`="
				+ jifen1 + " where `member_id`=" + memberId;
		try {
			memberService.updateMember(sql, enterId2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// set get

	public File getHaibao() {
		return haibao;
	}

	public void setHaibao(File haibao) {
		this.haibao = haibao;
	}

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public String getHuifuneirong() {
		return huifuneirong;
	}

	public void setHuifuneirong(String huifuneirong) {
		this.huifuneirong = huifuneirong;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public List<Message> getListmessage() {
		return listmessage;
	}

	public void setListmessage(List<Message> listmessage) {
		this.listmessage = listmessage;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<Member2> getListm2() {
		return listm2;
	}

	public void setListm2(List<Member2> listm2) {
		this.listm2 = listm2;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Member2 getMember2() {
		return member2;
	}

	public void setMember2(Member2 member2) {
		this.member2 = member2;
	}

	public List<Member> getListm() {
		return listm;
	}

	public void setListm(List<Member> listm) {
		this.listm = listm;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	public int getMember_freeze() {
		return member_freeze;
	}

	public void setMember_freeze(int member_freeze) {
		this.member_freeze = member_freeze;
	}

	public int getMember_grade() {
		return member_grade;
	}

	public void setMember_grade(int member_grade) {
		this.member_grade = member_grade;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getWeixin_id() {
		return weixin_id;
	}

	public void setWeixin_id(String weixin_id) {
		this.weixin_id = weixin_id;
	}

	public static void setPageSize(int pageSize) {
		PAGE_SIZE = pageSize;
	}

	public Activity getActivityPro() {
		return activityPro;
	}

	public void setActivityPro(Activity activityPro) {
		this.activityPro = activityPro;
	}

	public List<Activity> getListActivity() {
		return listActivity;
	}

	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public String getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	public String getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public int getJifen1() {
		return jifen1;
	}

	public void setJifen1(int jifen1) {
		this.jifen1 = jifen1;
	}

}
