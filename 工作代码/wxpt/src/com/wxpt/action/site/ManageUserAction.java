package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.wxpt.site.entity.Move;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.JDBC_test;
import com.wxpt.common.Md5;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.CardReordsDao;
import com.wxpt.site.dao.MoveDao;
import com.wxpt.site.dao.impl.LuckDaoImpl;
import com.wxpt.site.dao.impl.UserDaoImpl;
import com.wxpt.site.entity.AnswerRecords;
import com.wxpt.site.entity.Card;
import com.wxpt.site.entity.CardRecords;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.pojo.CardPojo;
import com.wxpt.site.entity.pojo.LuckUserPoro;
import com.wxpt.site.entity.pojo.LuckanwerPoro;
import com.wxpt.site.entity.pojo.UserPojo;
import com.wxpt.site.service.CardReordsService;
import com.wxpt.site.service.CardService;
import com.wxpt.site.service.LuckService;
import com.wxpt.site.service.MoveService;
import com.wxpt.site.service.UserService;

@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class ManageUserAction extends ParentAction {
	public String getSave() {
		return "save";
	}

	private String userNum;
	private String userName;
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	@Autowired
	UserService userService;
	LuckDaoImpl luckDao=new LuckDaoImpl();
	public String save() throws IOException {
		/*User user = new User();
		user.setUserName(userName);
		user.setUserNum(userNum);*/
		
		userService.saveUser(enterId,userName,userNum);
		return "success";
	}

	public String update() {
		User user = userService.getUserByID(enterId,userId);
		user.setUserName(userName);
		user.setUserNum(userNum);
		String sql="update user set userName='"+userName+"',userNum='"+userNum+"' where userId="+userId+"";
		/*String sql="update user set userName=?,userNum=? where userId=?";*/
		/*userService.update(enterId,sql);*/
		//JDBC_test.update(enterId,userName,userNum,user.getUserId());
		UserDaoImpl userdao=new UserDaoImpl();
		userdao.update(enterId, sql);
		return "success";
	}

	private String userIds;

	public String delete() {
		String ids[] = userIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			userService.deleteByUserID(enterId,Integer.parseInt(ids[i]));
		}
		return "success";
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	private List<UserPojo> userPojoList;
	private List<User> userList;
	private String paixu;
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;

	public String getList() {
		this.pageList(page, rows);

		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(userPojoList);
			//System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		count = userService.getUserCountById("select * from wxpt"+enterId+".user");
	   // System.out.println(jsonls);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private String queryName;

	private void pageList(int page, int rows) {
		// TODO Auto-generated method stub
		userPojoList = new ArrayList<UserPojo>();
		//===================================================================================================================
		String sql = "select * FROM wxpt"+enterId+".user  ";
		if (queryName != null && !queryName.equals("")) {
			sql += "WHERE userName like'%" + queryName + "%'";
		}
		List<User> u = userService.getUserList(page, rows, sql);

		for (User user : u) {
			sql = "select * from wxpt"+enterId+".userCount where userId =" + user.getUserId();
			if (startTime != null && endTime != null
					&& !startTime.equals("点击此处选择时间")
					&& !endTime.equals("点击此处选择时间")) {
				startTime = startTime + " 00:00:00";
				endTime = endTime + " 23:59:59";
				sql += " and datediff(str_to_date(sendTime,'%Y/%m/%d %H:%i:%s'),str_to_date('"
						+ startTime
						+ "','%Y/%m/%d %H:%i:%s') )>=0 and datediff(str_to_date('"
						+ endTime
						+ "','%Y/%m/%d %H:%i:%s'),str_to_date(sendTime,'%Y/%m/%d %H:%i:%s') )>=0 ";
			}
			int count = userService.getuserCountCount(sql);
			UserPojo userPojo = new UserPojo();
			userPojo.setCount(count);
			userPojo.setUserId(user.getUserId());
			userPojo.setUserName(user.getUserName());
			userPojo.setUserNum(user.getUserNum());
			userPojoList.add(userPojo);
		}

		
	}

	/*刮刮乐中奖管理开始*/
	private List<CardRecords> cardRecordsList;
	private int cardRecordsId;
	private String cardRecordsIds;
	private int moveId;
	private String moveName;
	private String moveStartTime;
	private String moveEndTime;
	private String moveContent;
	private String moveIds;
	private String awardTime;
	private String endTimes;
	private String moveNames;
	@Autowired
	MoveService moveService;
	
	MoveDao moveDao;
	@Autowired
	CardReordsService cardReordsService;
	@Autowired
	CardReordsDao cardReordsDao;
	public String getCardLucker(){
		cardRecordsList=cardReordsService.findAllQuery(page, rows,enterId);
		for(int i=0;i<cardRecordsList.size();i++){
			System.out.println(cardRecordsList.get(i).getExchangeAwdTime()+":==========");
			if(cardRecordsList.get(i).getExchangeAwdTime().equals("null")||
					cardRecordsList.get(i).getExchangeAwdTime().equals("")	){
				cardRecordsList.get(i).setExchangeAwdTime("未兑奖");
			}
		}
	
		
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "card" });
			jsonls = JSONArray.fromObject(cardRecordsList,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = cardRecordsList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	
	}
	
	public String updatecardLuck() {
		CardRecords cardRecords =new CardRecords();
		try {
			cardRecords=cardReordsService.findByRecordsCardId(cardRecordsId,enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardRecords.setExchangeAwdTime(TimeUtil.getTime());
		cardReordsDao.update(cardRecords,enterId);
		return "success";
	}

	public String deleteCardLucker(){

		String ids[] = cardRecordsIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			cardReordsService.delete(Integer.parseInt(ids[i]),enterId);
		}
		return "success";
	
	}
	public String updateMove() throws Exception{
		Move move=new Move();
		move=moveService.getMoveByMoveId(moveId,enterId);
		if(move.getMoveName()=="微会员"||move.getMoveName().equals("微会员")){
			move.setMoveName(move.getMoveName());
			move.setMoveContent(moveContent);
			move.setMoveStartTime(moveStartTime);
			move.setMoveEndTime(moveEndTime);
			if(isFinish(moveStartTime,moveEndTime)){
				move.setMoveState(0);
				move.setMoveStateDesc("已开启");
			}else{
				move.setMoveState(1);
				move.setMoveStateDesc("已关闭");
			}
			move.setAwardTime(endTimes);
			//moveService.save(move, enterId);
			moveDao.update(move,enterId);
		}else{
			move.setMoveName(move.getMoveName());
			move.setMoveContent(moveContent);
			move.setMoveStartTime(moveStartTime);
			move.setMoveEndTime(moveEndTime);
			if(isFinish(moveStartTime,moveEndTime)){
				move.setMoveState(0);
				move.setMoveStateDesc("已开启");
			}else{
				move.setMoveState(1);
				move.setMoveStateDesc("已关闭");
			}
			move.setAwardTime(awardTime);
		//	moveService.save(move, enterId);
			moveDao.update(move,enterId);
		}
		
		/*Move move=new Move();
		move=moveService.getMoveByMoveId(moveId,enterId);
		move.setMoveName(moveName);
		move.setMoveStartTime(moveStartTime);
		move.setMoveState(0);
		move.setMoveStateDesc("已开启");
		move.setMoveEndTime(moveEndTime);
		move.setMoveContent(moveContent);*/
		
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
	public String deleteMove(){

		String ids[] = moveIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			
			moveService.deleteByMoveId(Integer.parseInt(ids[i]),enterId);
		}
		
		return "success";
	
	}
	
	/*刮刮乐中奖管理结束*/
	
	public String getQueryName() {
		return queryName;
	}

	public String getMoveIds() {
		return moveIds;
	}

	public void setMoveIds(String moveIds) {
		this.moveIds = moveIds;
	}

	public int getMoveId() {
		return moveId;
	}

	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
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

	public String getMoveContent() {
		return moveContent;
	}

	public void setMoveContent(String moveContent) {
		this.moveContent = moveContent;
	}

	public String getCardRecordsIds() {
		return cardRecordsIds;
	}

	public void setCardRecordsIds(String cardRecordsIds) {
		this.cardRecordsIds = cardRecordsIds;
	}

	public int getCardRecordsId() {
		return cardRecordsId;
	}

	public void setCardRecordsId(int cardRecordsId) {
		this.cardRecordsId = cardRecordsId;
	}

	public List<CardRecords> getCardRecordsList() {
		return cardRecordsList;
	}

	public void setCardRecordsList(List<CardRecords> cardRecordsList) {
		this.cardRecordsList = cardRecordsList;
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

	public String getLuckUser() {
		Random r = new Random();
		if (xzLuck == 0) {
			List<UserCount> uList = userService.getUserCounts(enterId);
			UserCount c = uList.get(r.nextInt(uList.size()));
			luckUser = new LuckUser();
			luckUser.setAddTime(TimeUtil.getTime());
			luckUser.setSendUser(c.getSendUser());
			luckUser.setState(0);
			luckUser.setSendTime(c.getSendTime());
			luckService.addLuckUser(enterId,luckUser);
			luckUserName = c.getSendUser();
		}
		if (xzLuck == 2) {
			// luckUserName = "";
			List<UserCount> uList = null;
			String sendUser = "";
			for (int i = 0; i < Integer.parseInt(luckNumber); i++) {
				if (sendUser != "") {
					uList = userService.getUserCounts(enterId,sendUser);
				} else {
					uList = userService.getUserCounts(enterId);
				}
				UserCount c = uList.get(r.nextInt(uList.size()));
				luckUser = new LuckUser();
				luckUser.setAddTime(TimeUtil.getTime());
				luckUser.setSendUser(c.getSendUser());
				luckUser.setState(0);
				luckUser.setSendTime(c.getSendTime());
				luckService.addLuckUser(enterId,luckUser);
				if (i == 0) {
					luckUserName = c.getSendUser();
					sendUser = "'" + c.getSendUser() + "'";
				} else {
					luckUserName += ", " + c.getSendUser();
					sendUser += ",'" + c.getSendUser() + "'";
				}

			}
		}
		if (xzLuck == 1) {
			userPojoList = userService.getUserPojo(enterId,"desc", 1,
					Integer.parseInt(luckNumber));
			String ids = "";
			for (int i = 0; i < userPojoList.size(); i++) {
				if ((i + 1) == userPojoList.size()) {
					ids += userPojoList.get(i).getUserId();
				} else {
					ids += userPojoList.get(i).getUserId() + " , ";
				}
			}
			List<UserCount> uList = userService.getUserCount(enterId,ids);
			UserCount c = uList.get(r.nextInt(uList.size()));

			luckUser = new LuckUser();
			luckUser.setAddTime(TimeUtil.getTime());
			luckUser.setSendUser(c.getSendUser());
			luckUser.setState(0);
			luckUser.setSendTime(c.getSendTime());
			luckService.addLuckUser(enterId,luckUser);
			luckUserName = c.getSendUser();

		}
		return "success";
	}

	private List<LuckUser> luckUserList;
	private List<LuckUserPoro> luckUserPoroList;

	public String getUserLuck() {
		luckUserPoroList = new ArrayList<LuckUserPoro>();
		luckUserList = luckService.getUserList(enterId,page, rows);
		/*for (LuckUser l : luckUserList) {*/
		for(int i=0;i<luckUserList.size();i++){
			int dd=luckUserList.get(i).getLuckId();
			String user=luckUserList.get(i).getSendUser();
			LuckUserPoro luckUser = new LuckUserPoro(luckUserList.get(i).getLuckId(),
					luckUserList.get(i).getSendUser(), luckUserList.get(i).getAddTime(), luckUserList.get(i).getState(),
					luckUserList.get(i).getSendTime());
			if (luckUserList.get(i).getState() == 0) {
				luckUser.setStateStr("未领取");
			} else {
				luckUser.setStateStr("已领取");
			}
			luckUserPoroList.add(luckUser);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(luckUserPoroList);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		/*count = userService.getUserCountById("select count(*) from wxpt"+enterId+".luckUser");*/
		count =luckService.getluckUserCount("select * from wxpt"+enterId+".luckUser");
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();

		return "success";
	}

	private int lucyId;
	private String message;

	public String updateLuckUser() {

		luckDao.updateLkUser(enterId,lucyId, 1);

		return "success";
	}

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

	public String deleLuckUser() {
		String ids[] = lucyIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			luckService.deleteLkUser(enterId,Integer.parseInt(ids[i]));
		}

		return "success";
	}

	private int type;

	public String getLuckAnswer() {
		Luckanwer luckAnswer = new Luckanwer();
		List<AnswerRecords> l = luckService.getAnswerRecords(enterId,xzLuck);
		System.out.println(l.size());
		Random r = new Random();
		System.out.println(xzLuck);
		AnswerRecords a = l.get(r.nextInt(l.size()));
		luckAnswer.setAddTime(TimeUtil.getTime());
		luckAnswer.setState(0);
		luckAnswer.setAnswerRecords(a);
		luckService.addLuckanwer(enterId,luckAnswer);
		int recordId= luckAnswer.getAnswerRecords().getRecordId();
		luckUserName=luckService.byRecordId(enterId,recordId).getAnswerUser();
		return "success";
	}

	private List<Luckanwer> luckAnwerList;
	private List<LuckanwerPoro> luckanwerPoros;

	public String getLuckanswerList() {
		luckAnwerList = luckService.getLuckanwer(enterId,page, rows);
		luckanwerPoros = new ArrayList<LuckanwerPoro>();
		for (Luckanwer l : luckAnwerList) {
			int recotdId=luckService.byluchAnwerId(enterId,l.getLuckanswerid());
			AnswerRecords ar=luckService.byRecordId(enterId,recotdId);
			LuckanwerPoro ll = new LuckanwerPoro(l.getLuckanswerid(), ar.getRecordId(), ar.getAnswerUser(), ar.getAnswerTime(), ar.getType(), l.getAddTime(),
					l.getState(), l.getUpdateTime());
			if (ar.getType() == 0) {
				ll.setTypeStr("三等奖");
			}
			if (ar.getType() == 1) {
				ll.setTypeStr("二等奖");
			}
			if (ar.getType() == 2) {
				ll.setTypeStr("一等奖");
			}
			if (l.getState() == 0) {
				ll.setStateStr("未领取");
			} else {
				ll.setStateStr("已领取");
			}
			luckanwerPoros.add(ll);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(luckanwerPoros);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	/*	count = userService.getUserCountById("selct count(*) from wxpt"+enterId+".luckanwer");*/
		count=luckService.getluckAnwerCount("select * from wxpt"+enterId+".luckanwer");
		
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private int luckanswerid;

	public String updateLuckanwer() {
		luckDao.updateLuckanwer(enterId,luckanswerid, 1);
		return "success";
	}
	private String luckanswerIds;

	
	
	
	public String getLuckanswerIds() {
		return luckanswerIds;
	}

	public void setLuckanswerIds(String luckanswerIds) {
		this.luckanswerIds = luckanswerIds;
	}

	public String deleteLuckanwer() {
		String ids[] = luckanswerIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			luckService.deleteLuckanwer(enterId,Integer.parseInt(ids[i]));
		}
		
		return "success";
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

	public List<Luckanwer> getLuckAnwerList() {
		return luckAnwerList;
	}

	public void setLuckAnwerList(List<Luckanwer> luckAnwerList) {
		this.luckAnwerList = luckAnwerList;
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
	private int userId;

	public String getUserCount() {
		
		userCountList = userService.getUserCountList(enterId,page, rows, userId);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(userCountList, jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		String sql="select * from wxpt"+enterId+".userCount where userId =" + userId;
		count = userService
				.getuserCountCount(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public List<UserCount> getUserCountList() {
		return userCountList;
	}

	public void setUserCountList(List<UserCount> userCountList) {
		this.userCountList = userCountList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUp() {
		String message = "";
		ManageUser user = userService.getManageUserByName(enterId,userName);
		if (user == null) {
			message = "0";
		} else if (user.getPasswrod().equals(Md5.GetMd5(pwd1)) == false) {

			message = "原始密码错误";
		} else {
			user.setPasswrod(Md5.GetMd5(pwd2));
			String sql="UPDATE webchat.manage_user SET `manage_user_name`='"+user.getManageUserName()+"',`passwrod`='"+user.getPasswrod()+"',`user_type`="+user.getUserType()+",`register_time`='"+user.getRegisterTime()+"',`user_parent_id`="+user.getUserParentId()+",`role_list`='"+user.getRoleList()+"',`status`="+user.getStatus()+" WHERE `manage_user_id`="+user.getManageUserId()+"";
			userService.update(enterId,sql);
			message = "1";
		}
		super.out.print("{\"name\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public void out() throws IOException {
		Cookie uniqyw = new Cookie("cityin", "");
		uniqyw.setMaxAge(-1);
		uniqyw.setPath("/");
		ServletActionContext.getResponse().addCookie(uniqyw);
		ServletActionContext.getResponse().sendRedirect("./wxpt");
		///ServletActionContext.getResponse().sendRedirect("login.html");
		/*Cookie wxpt = new Cookie("wxpt", "1");
		wxpt.setMaxAge(-1);
		wxpt.setPath("/");
		ServletActionContext.getResponse().addCookie(wxpt);
		ServletActionContext.getResponse().sendRedirect("./wxpt");*/
	}
	
	
	public void out2() throws IOException {
		Cookie wxpt = new Cookie("wxpt", "");
		wxpt.setMaxAge(-1);
		wxpt.setPath("/");
		ServletActionContext.getResponse().addCookie(wxpt);
		//ServletActionContext.getResponse().sendRedirect("./wxpt");
		ServletActionContext.getResponse().sendRedirect("/wxpt/site/manage!login");
		
	}

	private List<Card> cardList;
	 @Autowired
	 CardService cardService;
	 private List<CardPojo> cardPojoList;
	private StringBuffer sb;
	
	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	public List<CardPojo> getCardPojoList() {
		return cardPojoList;
	}

	public void setCardPojoList(List<CardPojo> cardPojoList) {
		this.cardPojoList = cardPojoList;
	}

	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	public void queryImage2() {
		// TODO Auto-generated method stub
		cardList=cardService.findAllQuery(page, rows,enterId);
		cardPojoList = new ArrayList<CardPojo>();
		int cardType=0;
		
		for (Card k : cardList) {
			CardPojo cp =new CardPojo();
			cp.setCardCount(k.getCardCount());
			cp.setCardId(k.getCardId());
			cp.setCardImage(k.getCardImage());
			cp.setCardName(k.getCardName());
			cp.setCardType(k.getCardType());
			cardType=k.getCardType();
			if(cardType==1){
				cp.setCardTypeDesc("一等奖");
			}
			if(cardType==2){
				cp.setCardTypeDesc("二等奖");
			}
			if(cardType==3){
				cp.setCardTypeDesc("三等奖");
			}
			if(cardType==4){
				cp.setCardTypeDesc("四等奖");
			}
			if(cardType==5){
				cp.setCardTypeDesc("五等奖");
			}
			if(cardType==6){
				cp.setCardTypeDesc("六等奖");
			}
			if(cardType==7){
				cp.setCardTypeDesc("七等奖");
			}
			if(cardType==8){
				cp.setCardTypeDesc("八等奖");
			}
			if(cardType==9){
				cp.setCardTypeDesc("九等奖");
			}
			if(cardType==10){
				cp.setCardTypeDesc("十等奖");
			}
			if(cardType==-1){
				cp.setCardTypeDesc("重复刮奖");
			}
			if(cardType==0){
				cp.setCardTypeDesc("未中奖");
			}
			cardPojoList.add(cp);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(cardPojoList);
			 sb = new StringBuffer();
			//循环遍历获取角色和部门
			String str =jsonls.toString();
			String strpath =ServletActionContext.getServletContext().getRealPath("/");
			for(int i=0;i<cardPojoList.size();i++){
				String temp = "";
				temp =str.substring(0,str.indexOf("}"));
				sb.append(temp);	
				if(cardPojoList.get(i).getCardImage()!=null){
					sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='\\\\wxpt\\\\web\\\\images\\\\"+cardList.get(i).getCardImage().replace("/", "\\\\\\\\")+"' ///>\"");
					}else{
						sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='\\\\wxpt\\\\web\\\\images\\\\"+"card-wutu.jpg"+"' ///>\"");
					}
				
				
				sb.append("}");
				
				str =str.substring(str.indexOf("}")+1);
			}
			sb.append("]");
			System.out.println(sb.toString());
			count = cardService.findQuery(enterId).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.out.print("{\"total\":" + count + ",\"rows\":" + sb.toString() + "}");
		super.out.flush();
		super.out.close();
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

	public MoveDao getMoveDao() {
		return moveDao;
	}

	public void setMoveDao(MoveDao moveDao) {
		this.moveDao = moveDao;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public String getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}

	public String getMoveNames() {
		return moveNames;
	}

	public void setMoveNames(String moveNames) {
		this.moveNames = moveNames;
	}
	
}
