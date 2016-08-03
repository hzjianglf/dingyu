package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EnterpriseBean;
import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.Md5;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.MoveDao;
import com.wxpt.site.entity.AnswerRecords;

import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.Role;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.pojo.LuckUserPoro;
import com.wxpt.site.entity.pojo.LuckanwerPoro;
import com.wxpt.site.entity.pojo.Movep;
import com.wxpt.site.entity.pojo.UserPojo;

import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.LuckService;
import com.wxpt.site.service.MoveService;
import com.wxpt.site.service.RoleService;
import com.wxpt.site.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Results({ 
	@Result(name = "success", type = "json", params = { "root", "result" }),
	@Result(name="successs" ,location="/WEB-INF/content/site/successs.jsp")
})
@ParentPackage("json-default")
public class ManageMoveAction extends ParentAction {
	public String getSave() {
		return "save";
	}

	private String userNum;
	private String userName;
	private String moveName;
	private String moveContent;
	private String moveStartTime;
	private String moveEndTime;
	private String moveState;
	private String moveStateDesc;
	private String awardTime;
	private String endTimes;
	private List<Move> moveList;
	private List<Movep> mpList = new ArrayList<Movep>();
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	ManageAction manage;
	@Autowired
	UserService userService;
	@Autowired
	MoveService moveService;
	@Autowired
	UserService uservice;
	ManageUser user;
	@Autowired
	RoleService roleService;
	Role role;
	@Autowired
	EnterService enterService;
	EnterInfor enter;

	public String execute() {
		return "move";
	}
	public String save() throws Exception {
		System.out.println(moveName);
	if(moveName=="微会员"||moveName.equals("微会员")){
		Move move = new Move();
		move.setMoveName(moveName);
		move.setMoveContent(moveContent);
		move.setMoveStartTime(moveStartTime);
		move.setMoveEndTime(moveEndTime);
		move.setProbability("0;0;0;0;0;0;0;0;0;0");
		if(isFinish(moveStartTime,moveEndTime)){
			move.setMoveState(0);
			move.setMoveStateDesc("已开启");
		}else{
			move.setMoveState(1);
			move.setMoveStateDesc("已关闭");
		}
		move.setAwardTime(endTimes);
		moveService.save(move, enterId);
	} else if(moveName.equals("签到")){
		Move move = new Move();
		move.setMoveName(moveName);
		move.setMoveContent(moveContent);
		move.setMoveStartTime(moveStartTime);
		move.setMoveEndTime(moveEndTime);
		move.setProbability("5;15;30;mm0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;mm0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;mm0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0");
		System.out.println(move.getProbability());
		if(isFinish(moveStartTime,moveEndTime)){
			move.setMoveState(0);
			move.setMoveStateDesc("已开启");
		}else{
			move.setMoveState(1);
			move.setMoveStateDesc("已关闭");
		}
		move.setAwardTime(endTimes);
		moveService.save(move, enterId);
	}else{
		Move move = new Move();
		move.setMoveName(moveName);
		move.setMoveContent(moveContent);
		move.setMoveStartTime(moveStartTime);
		move.setMoveEndTime(moveEndTime);
		move.setProbability("0;0;0;0;0;0;0;0;0;0");
		if(isFinish(moveStartTime,moveEndTime)){
			move.setMoveState(0);
			move.setMoveStateDesc("已开启");
		}else{
			move.setMoveState(1);
			move.setMoveStateDesc("已关闭");
		}
		move.setAwardTime(awardTime);
		moveService.save(move, enterId);
	}
		return "success";
	}
	
	private boolean isFinish(String moveStartTime,String moveEndTime ) throws ParseException {
		boolean isFinish=false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		 Date date = new Date();
		 String curTime = df.format(date);
		 String strNow=curTime.replaceAll("/", "-");
		 String strStart =moveStartTime.replaceAll("/", "-")+" 00:00:00";
		 String strEnd=moveEndTime.replaceAll("/", "-")+" 00:00:00";
		 Date d1 = df.parse(strNow);
	     Date d2 = df.parse(strEnd);
	     Date d3=df.parse(strStart);
	    System.out.println(d1);
	    System.out.println(d2);
	     long diff = d1.getTime() - d2.getTime();
	     long diff2 =d1.getTime()-d3.getTime();
//	     long hours= diff/(1000*60*60);
//	     long mins= diff/(1000*60);
//	     long days = diff / (1000 * 60 * 60 * 24);
	     if(d1.getTime()<=d2.getTime()&&d1.getTime()>=d3.getTime()){
	    	 isFinish=true;
	     }else{
	    	 isFinish=false;
	     }
		return isFinish;
	}

	private String userIds;

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	private List<UserPojo> userPojoList;
	private List<User> userList;
	private String paixu;
	private int eatLuckerId;
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;

	// ----------------------------------------------------------------------------
	public String getList() {
		this.pageList(page, rows);

		try {
			JsonConfig jsonConfig = new JsonConfig();
			// jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(moveList);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		count = moveList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private String queryName;

	private void pageList(int page, int rows) {
		// TODO Auto-generated method stub
		moveList = new ArrayList<Move>();
		String sql = "select * from wxpt" + enterId + ".move  ";
		if (queryName != null && !queryName.equals("")) {
			sql += "WHERE move_name like'%" + queryName + "%'";
		}
		moveList = moveService.getMoveList(page, rows, sql, enterId);
		int count = moveService.getMoveById(sql);
	}

	public String ss() {
		ManageAction manage = new ManageAction();
		if (pidui(51, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("答题");
			mpList.add(mp);
		}
		if (pidui(156, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("刮刮乐");
			mpList.add(mp);
		}
		if (pidui(62, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("签到");
			mpList.add(mp);
		}
		if (pidui(39, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("投票活动");
			mpList.add(mp);
		}
		if (pidui(79, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("微会员");
			mpList.add(mp);
		}
		if (pidui(203, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("摄影大赛");
			mpList.add(mp);
		}if (pidui(206, enterId)) {
			Movep mp = new Movep();
			mp.setMoveName("调研活动");
			mpList.add(mp);
		}
		return "list";

	}
	
	private String move_name;
	private int movezhuangtai;
	private int onePrizeCount;
	private int twoPrizeCount;
	private int threePrizeCount;
	private int fourPrizeCount;
	private int fivePrizeCount;
	private int sixPrizeCount;
	private int sevenPrizeCount;
	private int eightPrizeCount;
	private int ninePrizeCount;
	private int tenPrizeCount;
	
	private double oneProbability;
	private double twoProbability;
	private double threeProbability;
	private double fourProbability;
	private double fiveProbability;
	private double sixProbability;
	private double sevenProbability;
	private double eightProbability;
	private double nineProbability;
	private double tenProbability;
	
	//签到大转盘
	private double oneProbability2;
	private double twoProbability2;
	private double threeProbability2;
	private double fourProbability2;
	private double fiveProbability2;
	private double sixProbability2;
	private double sevenProbability2;
	private double eightProbability2;
	private double nineProbability2;
	private double tenProbability2;
	
	private double oneProbability3;
	private double twoProbability3;
	private double threeProbability3;
	private double fourProbability3;
	private double fiveProbability3;
	private double sixProbability3;
	private double sevenProbability3;
	private double eightProbability3;
	private double nineProbability3;
	private double tenProbability3;
	
	private String zhuanpan1;
	private String zhuanpan2;
	private String zhuanpan3;
	
	
	private int state;
	/*检测活动时间是否结束*/
	public String checkMoveTime(){
		try {
			String name=new String(move_name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(name);	
			Move move =moveService.findByMoveName(name,enterId).get(0);
			//System.out.println(move);
			movezhuangtai=move.getMoveState();
			state=movezhuangtai;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			state=movezhuangtai;
			e.printStackTrace();
		}
		return "successs";
	}
	//设置活动中奖人数
	@Autowired
	MoveDao moveDao;
	
	public MoveDao getMoveDao() {
		return moveDao;
	}
	public void setMoveDao(MoveDao moveDao) {
		this.moveDao = moveDao;
	}
	public String setPrizeChance() throws UnsupportedEncodingException{
		System.out.println(move_name);
		
		Move move =moveService.findByMoveName(move_name,enterId).get(0);
		if(move_name.equals("签到")){
			move.setGailv(onePrizeCount+";"+twoPrizeCount+";"+threePrizeCount+";"+fourPrizeCount+";"+fivePrizeCount
					+";"+sixPrizeCount+";"+sevenPrizeCount+";"+eightPrizeCount+";"+ninePrizeCount+";"+tenPrizeCount);
		    move.setProbability(zhuanpan1+";"+zhuanpan2+";"+zhuanpan3+";mm"+oneProbability+";"+twoProbability+";"+threeProbability+";"+fourProbability
		    		+";"+fiveProbability+";"+sixProbability+";"+sevenProbability+";"+eightProbability
		    		+";"+nineProbability+";"+tenProbability+";mm"+oneProbability2+";"+twoProbability2+";"+threeProbability2+";"+fourProbability2
		    		+";"+fiveProbability2+";"+sixProbability2+";"+sevenProbability2+";"+eightProbability2
		    		+";"+nineProbability2+";"+tenProbability2+";mm"+oneProbability3+";"+twoProbability3+";"+threeProbability3+";"+fourProbability3
		    		+";"+fiveProbability3+";"+sixProbability3+";"+sevenProbability3+";"+eightProbability3
		    		+";"+nineProbability3+";"+tenProbability3);
		}else{
			move.setGailv(onePrizeCount+";"+twoPrizeCount+";"+threePrizeCount+";"+fourPrizeCount+";"+fivePrizeCount
					+";"+sixPrizeCount+";"+sevenPrizeCount+";"+eightPrizeCount+";"+ninePrizeCount+";"+tenPrizeCount);
		    move.setProbability(oneProbability+";"+twoProbability+";"+threeProbability+";"+fourProbability
		    		+";"+fiveProbability+";"+sixProbability+";"+sevenProbability+";"+eightProbability
		    		+";"+nineProbability+";"+tenProbability);
		}

		moveDao.update(move,enterId);
		return "successs";
	}
	public boolean pidui(int pId, int enterId) {
		enter = enterService.getById(enterId);
		String sql0 = "SELECT * FROM webchat.manage_user WHERE `enterid`="
				+ enterId;
		user = userService.getByEnterId(sql0);
		String sql = "SELECT * FROM webchat.role WHERE `role_id`="
				+ user.getRoleList();
		role = roleService.getById(sql);
		String roles = role.getPrivilegeList();
		String[] prie = roles.split(",");
		for (int i = 0; i < prie.length; i++) {
			if (Integer.parseInt(prie[i]) == pId) {
				return true;
			}
		}
		return false;
	}


	public String getZhuanpan1() {
		return zhuanpan1;
	}
	public void setZhuanpan1(String zhuanpan1) {
		this.zhuanpan1 = zhuanpan1;
	}
	public String getZhuanpan2() {
		return zhuanpan2;
	}
	public void setZhuanpan2(String zhuanpan2) {
		this.zhuanpan2 = zhuanpan2;
	}
	public String getZhuanpan3() {
		return zhuanpan3;
	}
	public void setZhuanpan3(String zhuanpan3) {
		this.zhuanpan3 = zhuanpan3;
	}
	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public int getMovezhuangtai() {
		return movezhuangtai;
	}
	public void setMovezhuangtai(int movezhuangtai) {
		this.movezhuangtai = movezhuangtai;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private String startTime;
	private String endTime;

	public String getLuck() {
		return "luck";
	}

	private int xzLuck;
	private String luckNumber;

	@Autowired
	LuckService luckService;

	private LuckUser luckUser;
	private String luckUserName;
	private String numberCount;

	private List<LuckUser> luckUserList;
	private List<LuckUserPoro> luckUserPoroList;

	private int lucyId;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String lucyIds;

	public String getLucyIds() {
		return lucyIds;
	}

	public void setLucyIds(String lucyIds) {
		this.lucyIds = lucyIds;
	}

	private int type;
	private int luckanswerid;
	private String luckanswerIds;
	private String eatLuckerIds;

	public String getLuckanswerIds() {
		return luckanswerIds;
	}

	public void setLuckanswerIds(String luckanswerIds) {
		this.luckanswerIds = luckanswerIds;
	}

	public String deleteLuckanwer() {
		return eatLuckerIds;
	}

	public String deleteEatLucker() {
		return eatLuckerIds;
	}

	public int getLuckanswerid() {
		return luckanswerid;
	}

	public void setLuckanswerid(int luckanswerid) {
		this.luckanswerid = luckanswerid;
	}

	public String luckAnswerEdit() {
		xzLuck = 0;
		return "luckanswer";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLucyId() {
		return lucyId;
	}

	public void setLucyId(int lucyId) {
		this.lucyId = lucyId;
	}

	public List<LuckUser> getLuckUserList() {
		return luckUserList;
	}

	public void setLuckUserList(List<LuckUser> luckUserList) {
		this.luckUserList = luckUserList;
	}

	public String getNumberCount() {
		return numberCount;
	}

	public void setNumberCount(String numberCount) {
		this.numberCount = numberCount;
	}

	public String getLuckUserName() {
		return luckUserName;
	}

	public void setLuckUserName(String luckUserName) {
		this.luckUserName = luckUserName;
	}

	public void setLuckUser(LuckUser luckUser) {
		this.luckUser = luckUser;
	}

	public int getXzLuck() {
		return xzLuck;
	}

	public void setXzLuck(int xzLuck) {
		this.xzLuck = xzLuck;
	}

	public String getMove_name() {
		return move_name;
	}
	public void setMove_name(String move_name) {
		this.move_name = move_name;
	}
	public String getLuckNumber() {
		return luckNumber;
	}

	public void setLuckNumber(String luckNumber) {
		this.luckNumber = luckNumber;
	}

	public String getPaixu() {
		return paixu;
	}

	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}

	private List<UserCount> userCountList;

	public String getUserCount() {
		return eatLuckerIds;
	}

	public List<UserCount> getUserCountList() {
		return userCountList;
	}

	public void setUserCountList(List<UserCount> userCountList) {
		this.userCountList = userCountList;
	}


	public List<UserPojo> getUserPojoList() {
		return userPojoList;
	}

	public void setUserPojoList(List<UserPojo> userPojoList) {
		this.userPojoList = userPojoList;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String pwd2;
	private String pwd1;
	private int un;

	public void out() throws IOException {
		Cookie uniqyw = new Cookie("wxpts", "1");
		uniqyw.setMaxAge(-1);
		uniqyw.setPath("/");
		ServletActionContext.getResponse().addCookie(uniqyw);
		ServletActionContext.getResponse().sendRedirect(".wxpts");
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUn() {
		return un;
	}

	public void setUn(int un) {
		this.un = un;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public int getEatLuckerId() {
		return eatLuckerId;
	}

	public void setEatLuckerId(int eatLuckerId) {
		this.eatLuckerId = eatLuckerId;
	}

	public String getEatLuckerIds() {
		return eatLuckerIds;
	}

	public void setEatLuckerIds(String eatLuckerIds) {
		this.eatLuckerIds = eatLuckerIds;
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public String getMoveContent() {
		return moveContent;
	}

	public void setMoveContent(String moveContent) {
		this.moveContent = moveContent;
	}

	public String getMoveStartTime() {
		return moveStartTime;
	}

	public void setMoveStartTime(String moveStartTime) {
		this.moveStartTime = moveStartTime;
	}

	public String getMoveEndTime() {
		return moveEndTime;
	}

	public void setMoveEndTime(String moveEndTime) {
		this.moveEndTime = moveEndTime;
	}

	public String getMoveState() {
		return moveState;
	}

	public void setMoveState(String moveState) {
		this.moveState = moveState;
	}

	public String getMoveStateDesc() {
		return moveStateDesc;
	}

	public void setMoveStateDesc(String moveStateDesc) {
		this.moveStateDesc = moveStateDesc;
	}

	public List<Move> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<Move> moveList) {
		this.moveList = moveList;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public List<Movep> getMpList() {
		return mpList;
	}

	public void setMpList(List<Movep> mpList) {
		this.mpList = mpList;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public MoveService getMoveService() {
		return moveService;
	}

	public void setMoveService(MoveService moveService) {
		this.moveService = moveService;
	}

	public ManageAction getManage() {
		return manage;
	}

	public void setManage(ManageAction manage) {
		this.manage = manage;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ManageUser getUser() {
		return user;
	}

	public void setUser(ManageUser user) {
		this.user = user;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setState(int state) {
		this.state = state;
	}
	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public EnterInfor getEnter() {
		return enter;
	}

	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}
	public String getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}
	public int getOnePrizeCount() {
		return onePrizeCount;
	}
	public void setOnePrizeCount(int onePrizeCount) {
		this.onePrizeCount = onePrizeCount;
	}
	public int getTwoPrizeCount() {
		return twoPrizeCount;
	}
	public void setTwoPrizeCount(int twoPrizeCount) {
		this.twoPrizeCount = twoPrizeCount;
	}
	public int getThreePrizeCount() {
		return threePrizeCount;
	}
	public void setThreePrizeCount(int threePrizeCount) {
		this.threePrizeCount = threePrizeCount;
	}
	public int getFourPrizeCount() {
		return fourPrizeCount;
	}
	public void setFourPrizeCount(int fourPrizeCount) {
		this.fourPrizeCount = fourPrizeCount;
	}
	public int getFivePrizeCount() {
		return fivePrizeCount;
	}
	public void setFivePrizeCount(int fivePrizeCount) {
		this.fivePrizeCount = fivePrizeCount;
	}
	public int getSixPrizeCount() {
		return sixPrizeCount;
	}
	public void setSixPrizeCount(int sixPrizeCount) {
		this.sixPrizeCount = sixPrizeCount;
	}
	public int getSevenPrizeCount() {
		return sevenPrizeCount;
	}
	public void setSevenPrizeCount(int sevenPrizeCount) {
		this.sevenPrizeCount = sevenPrizeCount;
	}
	public int getEightPrizeCount() {
		return eightPrizeCount;
	}
	public void setEightPrizeCount(int eightPrizeCount) {
		this.eightPrizeCount = eightPrizeCount;
	}
	public int getNinePrizeCount() {
		return ninePrizeCount;
	}
	public void setNinePrizeCount(int ninePrizeCount) {
		this.ninePrizeCount = ninePrizeCount;
	}
	public int getTenPrizeCount() {
		return tenPrizeCount;
	}
	public void setTenPrizeCount(int tenPrizeCount) {
		this.tenPrizeCount = tenPrizeCount;
	}
	public double getOneProbability() {
		return oneProbability;
	}
	public void setOneProbability(double oneProbability) {
		this.oneProbability = oneProbability;
	}
	public double getTwoProbability() {
		return twoProbability;
	}
	public void setTwoProbability(double twoProbability) {
		this.twoProbability = twoProbability;
	}
	public double getThreeProbability() {
		return threeProbability;
	}
	public void setThreeProbability(double threeProbability) {
		this.threeProbability = threeProbability;
	}
	public double getFourProbability() {
		return fourProbability;
	}
	public void setFourProbability(double fourProbability) {
		this.fourProbability = fourProbability;
	}
	public double getFiveProbability() {
		return fiveProbability;
	}
	public void setFiveProbability(double fiveProbability) {
		this.fiveProbability = fiveProbability;
	}
	public double getSixProbability() {
		return sixProbability;
	}
	public void setSixProbability(double sixProbability) {
		this.sixProbability = sixProbability;
	}
	public double getSevenProbability() {
		return sevenProbability;
	}
	public void setSevenProbability(double sevenProbability) {
		this.sevenProbability = sevenProbability;
	}
	public double getEightProbability() {
		return eightProbability;
	}
	public void setEightProbability(double eightProbability) {
		this.eightProbability = eightProbability;
	}
	public double getNineProbability() {
		return nineProbability;
	}
	public void setNineProbability(double nineProbability) {
		this.nineProbability = nineProbability;
	}
	public double getTenProbability() {
		return tenProbability;
	}
	public void setTenProbability(double tenProbability) {
		this.tenProbability = tenProbability;
	}
	public int getState() {
		return state;
	}
	public double getOneProbability2() {
		return oneProbability2;
	}
	public void setOneProbability2(double oneProbability2) {
		this.oneProbability2 = oneProbability2;
	}
	public double getTwoProbability2() {
		return twoProbability2;
	}
	public void setTwoProbability2(double twoProbability2) {
		this.twoProbability2 = twoProbability2;
	}
	public double getThreeProbability2() {
		return threeProbability2;
	}
	public void setThreeProbability2(double threeProbability2) {
		this.threeProbability2 = threeProbability2;
	}
	public double getFourProbability2() {
		return fourProbability2;
	}
	public void setFourProbability2(double fourProbability2) {
		this.fourProbability2 = fourProbability2;
	}
	public double getFiveProbability2() {
		return fiveProbability2;
	}
	public void setFiveProbability2(double fiveProbability2) {
		this.fiveProbability2 = fiveProbability2;
	}
	public double getSixProbability2() {
		return sixProbability2;
	}
	public void setSixProbability2(double sixProbability2) {
		this.sixProbability2 = sixProbability2;
	}
	public double getSevenProbability2() {
		return sevenProbability2;
	}
	public void setSevenProbability2(double sevenProbability2) {
		this.sevenProbability2 = sevenProbability2;
	}
	public double getEightProbability2() {
		return eightProbability2;
	}
	public void setEightProbability2(double eightProbability2) {
		this.eightProbability2 = eightProbability2;
	}
	public double getNineProbability2() {
		return nineProbability2;
	}
	public void setNineProbability2(double nineProbability2) {
		this.nineProbability2 = nineProbability2;
	}
	public double getTenProbability2() {
		return tenProbability2;
	}
	public void setTenProbability2(double tenProbability2) {
		this.tenProbability2 = tenProbability2;
	}
	public double getOneProbability3() {
		return oneProbability3;
	}
	public void setOneProbability3(double oneProbability3) {
		this.oneProbability3 = oneProbability3;
	}
	public double getTwoProbability3() {
		return twoProbability3;
	}
	public void setTwoProbability3(double twoProbability3) {
		this.twoProbability3 = twoProbability3;
	}
	public double getThreeProbability3() {
		return threeProbability3;
	}
	public void setThreeProbability3(double threeProbability3) {
		this.threeProbability3 = threeProbability3;
	}
	public double getFourProbability3() {
		return fourProbability3;
	}
	public void setFourProbability3(double fourProbability3) {
		this.fourProbability3 = fourProbability3;
	}
	public double getFiveProbability3() {
		return fiveProbability3;
	}
	public void setFiveProbability3(double fiveProbability3) {
		this.fiveProbability3 = fiveProbability3;
	}
	public double getSixProbability3() {
		return sixProbability3;
	}
	public void setSixProbability3(double sixProbability3) {
		this.sixProbability3 = sixProbability3;
	}
	public double getSevenProbability3() {
		return sevenProbability3;
	}
	public void setSevenProbability3(double sevenProbability3) {
		this.sevenProbability3 = sevenProbability3;
	}
	public double getEightProbability3() {
		return eightProbability3;
	}
	public void setEightProbability3(double eightProbability3) {
		this.eightProbability3 = eightProbability3;
	}
	public double getNineProbability3() {
		return nineProbability3;
	}
	public void setNineProbability3(double nineProbability3) {
		this.nineProbability3 = nineProbability3;
	}
	public double getTenProbability3() {
		return tenProbability3;
	}
	public void setTenProbability3(double tenProbability3) {
		this.tenProbability3 = tenProbability3;
	}
	public List<LuckUserPoro> getLuckUserPoroList() {
		return luckUserPoroList;
	}
	public void setLuckUserPoroList(List<LuckUserPoro> luckUserPoroList) {
		this.luckUserPoroList = luckUserPoroList;
	}
	
	

}
