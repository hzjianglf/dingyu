package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.RollUtil;
import com.wxpt.common.RollUtil1;
import com.wxpt.common.RollUtil2;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.Prize;
import com.wxpt.site.service.ImageService;
import com.wxpt.site.service.MoveService;

public class RollAction extends ActionSupport {
	private List<Imageroll> ImagerollList;
	private List<Imageroll> ImagerollList1;
	private List<Imageroll> ImagerollList2;
	ImageService imageservice;
	List<Prize> prizeList;
	List<CheckIn> checklist;
	List<Prize> prizeList1;
	Prize prize;
	private String fromusername1;

	private int enterId;
	final HttpServletRequest final_request = ServletActionContext.getRequest();
	final HttpServletResponse final_response = ServletActionContext
			.getResponse();

	// 获取当前系统时间
	String CurrentTime = TimeUtil.getTime();
	String year = CurrentTime.substring(0, 4);
	String month = CurrentTime.substring(5, 7);

	List<CheckIn> checklistTime = null;
	List<CheckIn> checklistTime2 = null;
	List<CheckIn> checklistTime3 = null;

	public void check() {
		String CurrentTime = TimeUtil.getTime();
		System.out.println(CurrentTime);
		String year = CurrentTime.substring(0, 4);
		System.out.println(year);
		String month = CurrentTime.substring(5, 7);
		System.out.println(month);
		checklistTime = imageservice.queryAllCheckByNameAndTime(enterId,
				"oIMbDjhOnycSf4RJ8L91Zn6_Ic6A", year, month);
		checklistTime2 = imageservice.queryAllCheckByNameAndTime2(enterId,
				"oIMbDjhOnycSf4RJ8L91Zn6_Ic6A", year, month);
		checklistTime3 = imageservice.queryAllCheckByNameAndTime3(enterId,
				"oIMbDjhOnycSf4RJ8L91Zn6_Ic6A", year, month);
		if (checklistTime.size() > 0) {// 证明签到天数在5-15天
			String msgType = "news";
			String url = "www.uniqyw.com/cityin/site/roll!roll";
			System.out.println("111111111111111111111111111");
			/*
			 * String resultStr = textTpl1.format(textTpl1,
			 * 
			 * fromUsername, toUsername, TimeUtil.getTime(), msgType, 1,
			 * "签到页面连续并且可以参与第一次抽奖哦", "回复qd进行今天签到哦",
			 * "http://www.uniqyw.com/cityin/manager/images/qiandao.jpg"
			 * ,url+"?fromusername="+fromUsername);
			 * 
			 * this.print(resultStr);
			 */
		} else if (checklistTime2.size() > 0) {
			String msgType = "news";
			System.out.println("2222222222222222222222222");
			/*
			 * String url="www.uniqyw.com/cityin/site/roll!roll1"; String
			 * resultStr = textTpl1.format(textTpl1,
			 * 
			 * fromUsername, toUsername, TimeUtil.getTime(), msgType, 1,
			 * "签到页面连续并且可以参与第一次抽奖哦", "回复qd完成今天签到哦",
			 * "http://www.uniqyw.com/cityin/manager/images/qiandao.jpg"
			 * ,url+"?fromusername="+fromUsername);
			 * 
			 * this.print(resultStr);
			 */

		} else if (checklistTime3.size() > 0) {

			String msgType = "news";
			System.out.println("3333333333333333333333333");
			/*
			 * String url="www.uniqyw.com/cityin/site/roll!roll3"; String
			 * resultStr = textTpl1.format(textTpl1,
			 * 
			 * fromUsername, toUsername, TimeUtil.getTime(), msgType, 1,
			 * "签到连续并且可以参与第二次抽奖哦", "回复qd完成今天签到哦",
			 * "http://www.uniqyw.com/cityin/manager/images/qiandao.jpg"
			 * ,url+"?fromusername="+fromUsername);
			 * 
			 * this.print(resultStr);
			 */
		} else {
			System.out.println("000000000000000000000000");

		}
	}

	public List<CheckIn> getChecklistTime() {
		return checklistTime;
	}

	public void setChecklistTime(List<CheckIn> checklistTime) {
		this.checklistTime = checklistTime;
	}

	public List<CheckIn> getChecklistTime2() {
		return checklistTime2;
	}

	public void setChecklistTime2(List<CheckIn> checklistTime2) {
		this.checklistTime2 = checklistTime2;
	}

	public List<CheckIn> getChecklistTime3() {
		return checklistTime3;
	}

	public void setChecklistTime3(List<CheckIn> checklistTime3) {
		this.checklistTime3 = checklistTime3;
	}

	public String roll() {
		checklist = imageservice.queryAllChenckByName(enterId, fromusername);
		if (checklist.size() != 0) {
			checkin = checklist.get(0);
		}
		ImagerollList = imageservice.getImageList(enterId);

		return "roll";
	}

	public String roll1() {
		checklist = imageservice.queryAllChenckByName(enterId, fromusername);
		if (checklist.size() != 0) {
			checkin = checklist.get(0);
		}
		ImagerollList = imageservice.getImageList(enterId);

		ImagerollList1 = imageservice.getImageList1(enterId);

		return "roll1";
	}

	public String roll2() {
		checklist = imageservice.queryAllChenckByName(enterId, fromusername);
		if (checklist.size() != 0) {
			checkin = checklist.get(0);
		}
		ImagerollList = imageservice.getImageList(enterId);

		ImagerollList2 = imageservice.getImageList2(enterId);
		return "roll2";
	}

	/*--------------------------------概率-------------------------------------*/

	public List<Prize> getPrizeList() {
		return prizeList;
	}

	public void setPrizeList(List<Prize> prizeList) {
		this.prizeList = prizeList;
	}

	Document document;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	@Autowired
	MoveService moveService;
	public void toLuck1() {  
		Move move = moveService.findByMoveName("签到",enterId).get(0);
		String moveStartTime=move.getMoveStartTime();
		
		String galv=move.getGailv();
		String str[] = galv.split(";");
		//活动预计中奖人数
		int onePrizeCount=Integer.parseInt(str[0]);
		int twoPrizeCount=Integer.parseInt(str[1]);
		int threePrizeCount=Integer.parseInt(str[2]);
		int fourPrizeCount=Integer.parseInt(str[3]);
		int fivePrizeCount=Integer.parseInt(str[4]);
		int sixPrizeCount=Integer.parseInt(str[5]);
		int sevenPrizeCount=Integer.parseInt(str[6]);
		int eightPrizeCount=Integer.parseInt(str[7]);
		int ninePrizeCount=Integer.parseInt(str[8]);
		int tenPrizeCount=Integer.parseInt(str[9]);
	//	double countPrize=onePrizeCount+twoPrizeCount+threePrizeCount+fourPrizeCount+fivePrizeCount+sixPrizeCount+sevenPrizeCount+eightPrizeCount+ninePrizeCount+tenPrizeCount;
		//概率计算
		String probability=move.getProbability();
		 String str1[] =probability.split("mm");
		 String[] strpro1=str1[1].split(";");
		double rate0=Double.parseDouble(strpro1[0]);
		double rate1=Double.parseDouble(strpro1[1]);
		double rate2=Double.parseDouble(strpro1[2]);
		double rate3=Double.parseDouble(strpro1[3]);
		double rate4=Double.parseDouble(strpro1[4]);
		double rate5=Double.parseDouble(strpro1[5]);
		double rate6=Double.parseDouble(strpro1[6]);
		double rate7=Double.parseDouble(strpro1[7]);
		double rate8=Double.parseDouble(strpro1[8]);
		double rate9=Double.parseDouble(strpro1[9]);
		//活动实际中奖人数
		int shiOneCount=imageservice.queryByNameByTimeCount(enterId,1,year, month);
		int shiTwoCount=imageservice.queryByNameByTimeCount(enterId,2,year, month);
		int shiThreeCount=imageservice.queryByNameByTimeCount(enterId,3,year, month);
		int shiFourCount=imageservice.queryByNameByTimeCount(enterId,4,year, month);
		int shiFiveCount=imageservice.queryByNameByTimeCount(enterId,5,year, month);
		int shiSixCount=imageservice.queryByNameByTimeCount(enterId,6,year, month);
		int shiSevenCount=imageservice.queryByNameByTimeCount(enterId,7,year, month);
		int shiEightCount=imageservice.queryByNameByTimeCount(enterId,8,year, month);
		int shiNineCount=imageservice.queryByNameByTimeCount(enterId,9,year, month);
		int shiTenCount=imageservice.queryByNameByTimeCount(enterId,10,year, month);
		  HttpServletResponse hs=ServletActionContext.getResponse();
		  hs.setContentType(
			     "text/html;charset=utf-8");
		 try {
			PrintWriter out = hs.getWriter();	
		   //prizeList1=imageservice.queryByNameByTime(enterId,year, month);
			luck = RollUtil.PercentageRandom(rate0, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9);
	         double num = 0;
			if (luck == 1) {
				if(shiOneCount>=onePrizeCount&&onePrizeCount!=-1){				
					luck=2;
					num = RollUtil.toLuck(60, 120);				
				}else{
					num = RollUtil.toLuck(0, 60);
				}
				System.out.println(luck);
			} 
			if (luck == 2) {
				if(shiTwoCount>=twoPrizeCount&&twoPrizeCount!=-1){
					luck=3;
				}else{
					num = RollUtil.toLuck(60, 120);
				}
			} 
			if (luck == 3) {
				if(shiThreeCount>=threePrizeCount&&threePrizeCount!=-1){
					luck=4;
				}else{
				num = RollUtil.toLuck(120, 180);
				}
			} 
			if (luck == 4) {
				if(shiFourCount>=fourPrizeCount&&fourPrizeCount!=-1){
					luck=5;
				}else{
				num = RollUtil.toLuck(180, 240);
				}
			} 
			if (luck == 5) {
				if(shiFiveCount>=fivePrizeCount&&fivePrizeCount!=-1){
					luck=6;
				}else{
				num = RollUtil.toLuck(240, 300);
				}
			} 
			if (luck == 6) {
				num = RollUtil.toLuck(300, 360);
			}
			Prize prize= new Prize();
			prizeList=imageservice.queryByName(enterId,fromusername);
		if(prizeList.size()>0){
			prize=prizeList.get(0);
			 state=prize.getState();		
		}else{
			state=0;
		}
		if(state==1){//今日已经抽奖
			state=1;
		}else if(state==0){
			
			//保存数据
			prize.setPrizeTime(TimeUtil.getTime());
			prize.setPrizenum("1");
			prize.setPrizegrade(luck);
			prize.setState(1);
			prize.setPrizeuser(fromusername);
			imageservice.addPrize(enterId,prize); 
			state=0;
		}	
		data = String.valueOf(num);
		System.out.println(state);
		flag="true";
		String ss= state+","+flag+","+data+","+luck;
		System.out.println(ss);
		 out.print(ss);
		 ImagerollList = imageservice.getImageList(enterId);
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}

	public void toLuck2() {
		Move move = moveService.findByMoveName("签到",enterId).get(0);
		String moveStartTime=move.getMoveStartTime();
		
		String galv=move.getGailv();
		String str[] = galv.split(";");
		//活动预计中奖人数
		int onePrizeCount=Integer.parseInt(str[0]);
		int twoPrizeCount=Integer.parseInt(str[1]);
		int threePrizeCount=Integer.parseInt(str[2]);
		int fourPrizeCount=Integer.parseInt(str[3]);
		int fivePrizeCount=Integer.parseInt(str[4]);
		int sixPrizeCount=Integer.parseInt(str[5]);
		int sevenPrizeCount=Integer.parseInt(str[6]);
		int eightPrizeCount=Integer.parseInt(str[7]);
		int ninePrizeCount=Integer.parseInt(str[8]);
		int tenPrizeCount=Integer.parseInt(str[9]);
		//double countPrize=onePrizeCount+twoPrizeCount+threePrizeCount+fourPrizeCount+fivePrizeCount+sixPrizeCount+sevenPrizeCount+eightPrizeCount+ninePrizeCount+tenPrizeCount;
		//概率计算
		String probability=move.getProbability();
		 String str1[] =probability.split("mm");
		 String[] strpro1=str1[2].split(";");
		double rate0=Double.parseDouble(strpro1[0]);
		double rate1=Double.parseDouble(strpro1[1]);
		double rate2=Double.parseDouble(strpro1[2]);
		double rate3=Double.parseDouble(strpro1[3]);
		double rate4=Double.parseDouble(strpro1[4]);
		double rate5=Double.parseDouble(strpro1[5]);
		double rate6=Double.parseDouble(strpro1[6]);
		double rate7=Double.parseDouble(strpro1[7]);
		double rate8=Double.parseDouble(strpro1[8]);
		double rate9=Double.parseDouble(strpro1[9]);
		//活动实际中奖人数
		int shiOneCount=imageservice.queryByNameByTimeCount(enterId,1,year, month);
		int shiTwoCount=imageservice.queryByNameByTimeCount(enterId,2,year, month);
		int shiThreeCount=imageservice.queryByNameByTimeCount(enterId,3,year, month);
		int shiFourCount=imageservice.queryByNameByTimeCount(enterId,4,year, month);
		int shiFiveCount=imageservice.queryByNameByTimeCount(enterId,5,year, month);
		int shiSixCount=imageservice.queryByNameByTimeCount(enterId,6,year, month);
		int shiSevenCount=imageservice.queryByNameByTimeCount(enterId,7,year, month);
		int shiEightCount=imageservice.queryByNameByTimeCount(enterId,8,year, month);
		int shiNineCount=imageservice.queryByNameByTimeCount(enterId,9,year, month);
		int shiTenCount=imageservice.queryByNameByTimeCount(enterId,10,year, month);
		HttpServletResponse hs=ServletActionContext.getResponse();
		  hs.setContentType(
			     "text/html;charset=utf-8");
		 try {
			PrintWriter out = hs.getWriter();
		
		
		System.out.println(fromusername);
		//prizeList1=imageservice.queryByNameByTime(enterId,year, month);
		luck = RollUtil.PercentageRandom(rate0, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9);
		double num = 0;
		if (luck == 1) {
			if(shiOneCount>=onePrizeCount&&onePrizeCount!=-1){				
				luck=2;
				num = RollUtil.toLuck(90, 180);				
			}else{
				num = RollUtil.toLuck(0, 90);
			}
			//System.out.println(luck);
		}
	      if (luck == 2) {
	    	 if(shiTwoCount>=twoPrizeCount&&twoPrizeCount!=-1){
	    		 luck=3;
	    	 }else{
	    		 num = RollUtil.toLuck(90, 180);
	    	 }
		} 
	      	if (luck == 3) {
	      		if(shiThreeCount>=threePrizeCount&&threePrizeCount!=-1){
	       		 luck=4;
	       	 }else{
			num = RollUtil.toLuck(180, 270);
	       	 }
		} 
	      	if (luck == 4) {
	      	num = RollUtil.toLuck(270, 360);
	   } 
		
		Prize prize= new Prize();
		prizeList=imageservice.queryByName(enterId,fromusername);
		if(prizeList.size()>0){
			prize=prizeList.get(0);
			 state=prize.getState();		
		}else{
			state=0;
		}
		if(state==1){//今日已经抽奖
			state=1;
		}else if(state==0){
			
			//保存数据
			prize.setPrizeTime(TimeUtil.getTime());
			prize.setPrizenum("1");
			prize.setPrizegrade(luck);
			prize.setState(1);
			prize.setPrizeuser(fromusername);
			imageservice.addPrize(enterId,prize); 
			state=0;
		}

		
		data = String.valueOf(num);
		System.out.println(state);
		flag="true";
		String ss= state+","+flag+","+data+","+luck;
		System.out.println(ss);
		 out.print(ss);
		ImagerollList1 = imageservice.getImageList1(enterId);
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

}

	public void toLuck3() {
		Move move = moveService.findByMoveName("签到",enterId).get(0);
		String moveStartTime=move.getMoveStartTime();
		
		String galv=move.getGailv();
		String str[] = galv.split(";");
		//活动预计中奖人数
		int onePrizeCount=Integer.parseInt(str[0]);
		int twoPrizeCount=Integer.parseInt(str[1]);
		int threePrizeCount=Integer.parseInt(str[2]);
		int fourPrizeCount=Integer.parseInt(str[3]);
		int fivePrizeCount=Integer.parseInt(str[4]);
		int sixPrizeCount=Integer.parseInt(str[5]);
		int sevenPrizeCount=Integer.parseInt(str[6]);
		int eightPrizeCount=Integer.parseInt(str[7]);
		int ninePrizeCount=Integer.parseInt(str[8]);
		int tenPrizeCount=Integer.parseInt(str[9]);
		//double countPrize=onePrizeCount+twoPrizeCount+threePrizeCount+fourPrizeCount+fivePrizeCount+sixPrizeCount+sevenPrizeCount+eightPrizeCount+ninePrizeCount+tenPrizeCount;
		//概率计算
		String probability=move.getProbability();
		 String str1[] =probability.split("mm");
		 String[] strpro1=str1[3].split(";");
		double rate0=Double.parseDouble(strpro1[0]);
		double rate1=Double.parseDouble(strpro1[1]);
		double rate2=Double.parseDouble(strpro1[2]);
		double rate3=Double.parseDouble(strpro1[3]);
		double rate4=Double.parseDouble(strpro1[4]);
		double rate5=Double.parseDouble(strpro1[5]);
		double rate6=Double.parseDouble(strpro1[6]);
		double rate7=Double.parseDouble(strpro1[7]);
		double rate8=Double.parseDouble(strpro1[8]);
		double rate9=Double.parseDouble(strpro1[9]);
		//活动实际中奖人数
		int shiOneCount=imageservice.queryByNameByTimeCount(enterId,1,year, month);
		int shiTwoCount=imageservice.queryByNameByTimeCount(enterId,2,year, month);
		int shiThreeCount=imageservice.queryByNameByTimeCount(enterId,3,year, month);
		int shiFourCount=imageservice.queryByNameByTimeCount(enterId,4,year, month);
		int shiFiveCount=imageservice.queryByNameByTimeCount(enterId,5,year, month);
		int shiSixCount=imageservice.queryByNameByTimeCount(enterId,6,year, month);
		int shiSevenCount=imageservice.queryByNameByTimeCount(enterId,7,year, month);
		int shiEightCount=imageservice.queryByNameByTimeCount(enterId,8,year, month);
		int shiNineCount=imageservice.queryByNameByTimeCount(enterId,9,year, month);
		int shiTenCount=imageservice.queryByNameByTimeCount(enterId,10,year, month);
		HttpServletResponse hs=ServletActionContext.getResponse();
		  hs.setContentType(
			     "text/html;charset=utf-8");
		  try {
			PrintWriter out = hs.getWriter();
		
		prizeList1=imageservice.queryByNameByTime(enterId,year, month);	
		luck = RollUtil.PercentageRandom(rate0, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9);
		 double num = 0;
		 if (luck == 1) {
			 if(shiOneCount>=onePrizeCount&&onePrizeCount!=-1){				
					luck=2;
					num = RollUtil.toLuck(120, 240);				
				}else{
					num = RollUtil.toLuck(0, 120);
				}
				System.out.println(luck);
			}
	        
		 if (luck == 2) {
			 if(shiTwoCount>=twoPrizeCount&&twoPrizeCount!=-1){
				 luck=3;
			 }else{
				 num = RollUtil.toLuck(120, 240);
			 }
			} 
		 if (luck == 3) {
				num = RollUtil.toLuck(240, 360);
			} 
		 
		 Prize prize= new Prize();
			prizeList=imageservice.queryByName(enterId,fromusername);
			if(prizeList.size()>0){
				prize=prizeList.get(0);
				 state=prize.getState();		
			}else{
				state=0;
			}
			if(state==1){//今日已经抽奖
				state=1;
			}else if(state==0){
				
				//保存数据
				prize.setPrizeTime(TimeUtil.getTime());
				prize.setPrizenum("1");
				prize.setPrizegrade(luck);
				prize.setState(1);
				prize.setPrizeuser(fromusername);
				imageservice.addPrize(enterId,prize); 
				state=0;
			}
		
		data = String.valueOf(num);
		flag="true";
		String ss= state+","+flag+","+data+","+luck;
		 out.print(ss);
		ImagerollList2 = imageservice.getImageList2(enterId);
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}

	private int luck;

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ImageService getImageservice() {
		return imageservice;
	}

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}

	public List<Imageroll> getImagerollList() {
		return ImagerollList;
	}

	public void setImagerollList(List<Imageroll> imagerollList) {
		ImagerollList = imagerollList;
	}

	//
	private String fromusername;
	private String tempString;
	private CheckIn checkin;

	public void getJson() {
		HttpServletResponse hsp = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = hsp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hsp.setContentType("text/html;charset=utf-8");

		// List<CheckIn> list=
		// imageservice.queryAllChenckByName("oIMbDjhOnycSf4RJ8L91Zn6_Ic6A");
		List<CheckIn> list = imageservice.queryAllChenckByName(enterId,
				fromusername);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append("");
			sb.append(list.get(i).getChecTime().substring(0, 4));
			sb.append(list.get(i).getChecTime().substring(5, 7));
			sb.append(list.get(i).getChecTime().substring(8, 10));

			if (i != (list.size() - 1)) {
				sb.append(",");
			}

		}

		tempString = sb.toString();
		System.out.println(tempString);
		out.write(tempString);
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public CheckIn getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}

	public String getFromusername1() {
		return fromusername1;
	}

	public void setFromusername1(String fromusername1) {
		this.fromusername1 = fromusername1;
	}

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
	}

	public List<Imageroll> getImagerollList1() {
		return ImagerollList1;
	}

	public void setImagerollList1(List<Imageroll> imagerollList1) {
		ImagerollList1 = imagerollList1;
	}

	public List<Imageroll> getImagerollList2() {
		return ImagerollList2;
	}

	public void setImagerollList2(List<Imageroll> imagerollList2) {
		ImagerollList2 = imagerollList2;
	}

	public List<Prize> getPrizeList1() {
		return prizeList1;
	}

	public void setPrizeList1(List<Prize> prizeList1) {
		this.prizeList1 = prizeList1;
	}

	public List<CheckIn> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<CheckIn> checklist) {
		this.checklist = checklist;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

}
