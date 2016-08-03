package com.wxpt.action.site.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.action.site.GetCookie;
import com.wxpt.site.dao.MemberDao;
import com.wxpt.site.entity.Activity;
import com.wxpt.site.entity.Coupons;
import com.wxpt.site.entity.Integrals;
import com.wxpt.site.entity.MamberGrade;
import com.wxpt.common.PageBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.pojo.Coupons2;
import com.wxpt.site.entity.pojo.Integrals2;
import com.wxpt.site.entity.pojo.Messagepojo;
import com.wxpt.site.entity.Message;
import com.wxpt.site.entity.pojo.Member2;
import com.wxpt.site.service.MemberService;

public class VipWebAction extends ActionSupport {
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	MemberService memberService;
	@Autowired
	MemberDao memberDao;
	private int enterId;
	//int enterID = Integer.parseInt(enterId);
	Message message;
	static final int PAGE_SIZE = 10;// 每页数据条数
	private int listCount; // 服务总数据条数
	private int pageCount;// 总页数
	private int currentpage = 1;// 当前页
	private int listCount1; // 产品总数据条数
	private Member member;
	private Member2 member2;
	private String xingming;
	private String xiangxidizhi;
	private String shoujihao;
	private int xingbie;
	private int nianling;
	private int id;
	private String password;
	private String password1;
	private String password2;
	private String cardId;
	private List<Integrals> inte;
	private Integrals2 integ;
	private String dtime;
	private List<Message> listMessage;
	private List<Message> listMessage2;
	private List<Activity> listActivity;
	private List<Activity> listActivity2;
	private List<Integrals2> inte2;
	private List<Coupons2> listCoupons2;
	Coupons2 coupons2;
	private List<Coupons> listCoupons;
	private String memberId="72";
	private int jifen;
	private String chuzhi;
	List<MamberGrade> gradeList=new ArrayList<MamberGrade>();
	public String getChuzhi() {
		return chuzhi;
	}
	public void setChuzhi(String chuzhi) {
		this.chuzhi = chuzhi;
	}
	public int getJifen() {
		return jifen;
	}
	public void setJifen(int jifen) {
		this.jifen = jifen;
	}
	private int intid;
	public int getIntid() {
		return intid;
	}
	public void setIntid(int intid) {
		this.intid = intid;
	}
	public List<Integrals2> getInte2() {
		return inte2;
	}
	public void setInte2(List<Integrals2> inte2) {
		this.inte2 = inte2;
	}
	Activity activity;
	
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getListCount1() {
		return listCount1;
	}
	public void setListCount1(int listCount1) {
		this.listCount1 = listCount1;
	}
	private int pageSize = 8;
	private int curPage = 1;
	private int page;

	PageBean pagebean = new PageBean();
	List<Messagepojo> listMj;
	private Integrals integr;
	public Integrals getIntegr() {
		return integr;
	}
	public void setIntegr(Integrals integr) {
		this.integr = integr;
	}
	public List<Messagepojo> getListMj() {
		return listMj;
	}
	public void setListMj(List<Messagepojo> listMj) {
		this.listMj = listMj;
	}
	public PageBean getPagebean() {
		return pagebean;
	}
	public void setPagebean(PageBean pagebean) {
		this.pagebean = pagebean;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public Integrals2 getInteg() {
		return integ;
	}
	public void setInteg(Integrals2 integ) {
		this.integ = integ;
	}
	public List<Integrals> getInte() {
		return inte;
	}
	public void setInte(List<Integrals> inte) {
		this.inte = inte;
	}
	private String title;
	private String content;
	private String fromUsername;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Member2 getMember2() {
		return member2;
	}
	public void setMember2(Member2 member2) {
		this.member2 = member2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		member=new Member();
		member=memberService.getOneCardid(cardId,enterId);
		int b=member.getMemberId();
		jifen=memberService.getJifen(b, enterId);
		memberDao.getChangejifen(b, enterId, jifen);
		try {
			chuzhi=memberService.chuzhi(b, enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		memberService.getChangechuzhi(b, enterId, chuzhi);
		member2=new Member2();
	//	int i=member.getMemberGrade();
		Date date=member.getEndTime();
		String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
		member2.setEndTime(time);
		if(chuzhi!=null){
			member2.setSaveMoney(chuzhi);
		}else{
			member2.setSaveMoney("0.0");
		}
		String sql0="SELECT * FROM wxpt"+enterId+".mamber_grade  order by `grade_jifen` asc";
		gradeList=memberService.getList(sql0);
		for(int i=0;i<gradeList.size();i++){
			if(jifen<gradeList.get(0).getGradeJifen()){
				member2.setMemberGrade(gradeList.get(0).getMemberGradeName());
				break;
			}
			if(i>0&&gradeList.size()>0){
				if(gradeList.get(i-1).getGradeJifen() <= jifen && jifen< gradeList.get(i).getGradeJifen()){
					member2.setMemberGrade(gradeList.get(i).getMemberGradeName());
					break ;
				}
			}
			if(jifen>=gradeList.get(gradeList.size()-1).getGradeJifen()){
				member2.setMemberGrade(gradeList.get(gradeList.size()).getMemberGradeName());
				break ;
			}
		}
		
		return "vipweb";
	}
	public void getUp(){
		try {
//			member=new Member();
//			member=memberService.getOne(id,enterId);
//			member.setAddress(xiangxidizhi);
//			member.setAge(nianling);
//			member.setPhone(shoujihao);
//			member.setSex(xingbie);
//			member.setUsername(xingming);
//			memberService.getChange(member);
			memberService.getChange(id,xiangxidizhi,nianling,shoujihao,xingbie,xingming,enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void edit(){
		try {
			member=new Member();
			member=memberService.getOne(id,enterId);
			member.setPassword(password1);
			//memberService.getChange(member);
			memberService.getChangePassword(id, enterId, password1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getList(){
		
		inte=new ArrayList<Integrals>();
		try {
			inte=memberService.getListIntegrals(id,enterId);
			listCount=inte.size();
			inte=new ArrayList<Integrals>();
			this.PageList((currentpage-1)*PAGE_SIZE);
		//	System.out.println(inte.get(0).getIntegralsBusiness());
			inte2=new ArrayList<Integrals2>();
			for(int i=0;i<inte.size();i++){
				integ=new Integrals2();
				integ.setIntegralsId(inte.get(i).getIntegralsId());
				integ.setIntegralsBusiness(inte.get(i).getIntegralsBusiness());
				integ.setIntegralsOne(inte.get(i).getIntegralsOne());
				integ.setIntegralsRemark(inte.get(i).getIntegralsRemark());
				Date dd=inte.get(i).getIntegralsTime();
				String time = new SimpleDateFormat("yyyy-MM-dd").format(dd);
				integ.setIntegralsTime(time);
				integ.setMemberId(inte.get(i).getMember().getMemberId());
				inte2.add(integ);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "viplist";
	}
	public void PageList(int statePage){
		inte = memberService.getFenye(id,enterId,statePage,PAGE_SIZE);
        if (listCount%PAGE_SIZE==0) {
			pageCount=listCount/PAGE_SIZE;
		}
		else {
			pageCount=listCount/PAGE_SIZE+1;
		}
	}
	public String getOne(){
		integ=new Integrals2();
		try {
			integ=memberService.getOneIntegrals(id,enterId);
			dtime=df.format(new Date());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "vipone";
	}
	//意见建议添加页面
	public String setMessages()
	{
		return "message";
	}
	//意见添加方法
	public void addMessage()
	{
	
		try {
			message = new Message();
			message.setMember(memberService.getOneCardid(cardId,enterId));
			message.setMessageTitle(title);
			message.setMessageContent(content);
			message.setTime(TimeUtil.getTime());
			message.setMessageType(0);
			message.setMessageParentid(0);
			memberService.addMessage(enterId,message);
			
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//回复信息
	public String setOneMessage()
	{
		pagebean = memberService.showMessage(enterId, curPage, pageSize, memberService.getOneCardid(cardId,enterId).getMemberId());
		listMessage = pagebean.getList();
		
		return "onemessage";
	}
	public String xiangxi(){
		integr=new Integrals();
		try {
			integr=memberService.getxiang(intid, enterId);
			Date date=integr.getIntegralsTime();
			dtime=df.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "vipxiang";
	}
	//回复详细信息
	public String backMessage()
	{
		listMessage2 = memberService.getByMessageId(enterId, id, 1);
		listMj = new ArrayList<Messagepojo>();
		int i = 0;
		for(;i<listMessage2.size();i++)
		{
			Messagepojo mj = new Messagepojo();
			message = memberService.getByMessageIds(enterId, listMessage2.get(i).getMessageParentid(), 0);
			mj.setMessageContents(message.getMessageContent());
			listMj.add(mj);
		}
		
		return "ajax";
	}
	//处销活动
	public String showActivity()
	{
		pagebean = memberService.getByIdActivity(enterId, curPage, pageSize); 
		listCount = 1;
		listActivity = pagebean.getList();
		return "activity";
	}
	//活动优惠券
	public String showTicket()
	{
		pagebean = memberService.getByMemberId(Integer.parseInt(memberId), enterId, curPage, pageSize);
		listCoupons = pagebean.getList();
		listCoupons2 = new ArrayList<Coupons2>();
		int i = 0;
		for(;i<listCoupons.size();i++)
		{
			coupons2 = new Coupons2();
			coupons2.setCouponsId(listCoupons.get(i).getCouponsId());
			coupons2.setCouponsTitle(listCoupons.get(i).getCouponsTitle());
			coupons2.setCouponsContent(listCoupons.get(i).getCouponsContent());
			coupons2.setCouponsRemark(listCoupons.get(i).getCouponsRemark());
			coupons2.setActivityTitle(listCoupons.get(i).getActivity().getActivityTitle());
			coupons2.setActivityStarttime(listCoupons.get(i).getActivity().getActivityStarttime());
			coupons2.setActivityEndtime(listCoupons.get(i).getActivity().getActivityEndtime());
			listCoupons2.add(coupons2);
		}
		return "ticket";
	}
	public String showCoupons2()
	{
		listCoupons = memberService.getCouponsById(id, enterId);
		listCoupons2 = new ArrayList<Coupons2>();
		coupons2 = new Coupons2();
		coupons2.setActivityTitle(listCoupons.get(0).getActivity().getActivityTitle());
		coupons2.setCouponsTitle(listCoupons.get(0).getCouponsTitle());
		coupons2.setCouponsContent(listCoupons.get(0).getCouponsContent());
		coupons2.setActivityStarttime(listCoupons.get(0).getActivity().getActivityStarttime());
		coupons2.setActivityEndtime(listCoupons.get(0).getActivity().getActivityEndtime());
		coupons2.setCouponsRemark(listCoupons.get(0).getCouponsRemark());
		listCoupons2.add(coupons2);
		return "ajaxcoupons";
	}

	public String backActivity()
	{
		listActivity2 = memberService.getActivity(enterId, id);
		return "ajaxactivity";
	}
	public String getXingming() {
		return xingming;
	}
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	public String getXiangxidizhi() {
		return xiangxidizhi;
	}
	public void setXiangxidizhi(String xiangxidizhi) {
		this.xiangxidizhi = xiangxidizhi;
	}
	public String getShoujihao() {
		return shoujihao;
	}
	public void setShoujihao(String shoujihao) {
		this.shoujihao = shoujihao;
	}
	public int getXingbie() {
		return xingbie;
	}
	public void setXingbie(int xingbie) {
		this.xingbie = xingbie;
	}
	public int getNianling() {
		return nianling;
	}
	public void setNianling(int nianling) {
		this.nianling = nianling;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public List<Message> getListMessage() {
		return listMessage;
	}
	public void setListMessage(List<Message> listMessage) {
		this.listMessage = listMessage;
	}
	public List<Message> getListMessage2() {
		return listMessage2;
	}
	public void setListMessage2(List<Message> listMessage2) {
		this.listMessage2 = listMessage2;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public List<Activity> getListActivity() {
		return listActivity;
	}
	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public List<Activity> getListActivity2() {
		return listActivity2;
	}
	public void setListActivity2(List<Activity> listActivity2) {
		this.listActivity2 = listActivity2;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getEnterId() {
		return enterId;
	}
	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}
	public List<Coupons2> getListCoupons2() {
		return listCoupons2;
	}
	public void setListCoupons2(List<Coupons2> listCoupons2) {
		this.listCoupons2 = listCoupons2;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public List<Coupons> getListCoupons() {
		return listCoupons;
	}
	public void setListCoupons(List<Coupons> listCoupons) {
		this.listCoupons = listCoupons;
	}
}
