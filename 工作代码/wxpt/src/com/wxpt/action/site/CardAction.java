package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.RollUtil;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Card;
import com.wxpt.site.entity.CardRecords;
import com.wxpt.site.entity.Move;
import com.wxpt.site.service.CardReordsService;
import com.wxpt.site.service.CardService;
import com.wxpt.site.service.MoveService;
import com.opensymphony.xwork2.ActionSupport;
@Results({
	
	@Result(name = "prize2", location = "card-prize2.jsp") })
public class CardAction extends ActionSupport{
	private String fileName="card-nopwd.jpg";
	private String fromUsername;
	private int luck;
	private int cardRecordsCount1=0;
	private int cardRecordsCount2=0;
	private int cardRecordsCount3=0;
	private int cardRecordsCount4=0;
	private int cardRecordsCount5=0;
	private int cardRecordsCount6=0;
	private int cardRecordsCount7=0;
	private int cardRecordsCount8=0;
	private int cardRecordsCount9=0;
	private int cardRecordsCount10=0;
	private int cardCount1=0;//一等奖卡片的数量
	private int cardCount2=0;
	private int cardCount3=0;
	private int cardCount4=0;
	private int cardCount5=0;
	private int cardCount6=0;
	private int cardCount7=0;
	private int cardCount8=0;
	private int cardCount9=0;
	private int cardCount10=0;
	private int maxCardType=0;
	private String prvFileName;
public String getPrvFileName() {
		return prvFileName;
	}
	public void setPrvFileName(String prvFileName) {
		this.prvFileName = prvFileName;
	}
	//	GetCookie cookies=new GetCookie();
//	int enterId=cookies.getCookie();
	private int enterId;
	@Autowired
	CardReordsService  cardReordsService;
	@Autowired
	CardService cardService;
	@Autowired
	MoveService moveService;
  public String getLuck1(){
	  Move move=moveService.findByMoveName("刮刮乐",enterId).get(0);
		String moveStartTime=move.getMoveStartTime();
	  maxCardType=cardService.getByMaxType(enterId);
	  int count=cardReordsService.getBycardUserName(fromUsername,enterId,moveStartTime);
	  String countStr=move.getGailv();
	  String strcount[] = countStr.split(";");
	  cardCount1=Integer.parseInt(strcount[0]);
	  cardCount2=Integer.parseInt(strcount[1]);
	  cardCount3=Integer.parseInt(strcount[2]);
	  cardCount4=Integer.parseInt(strcount[3]);
	  cardCount5=Integer.parseInt(strcount[4]);
	  cardCount6=Integer.parseInt(strcount[5]);
	  cardCount7=Integer.parseInt(strcount[6]);
	  cardCount8=Integer.parseInt(strcount[7]);
	  cardCount9=Integer.parseInt(strcount[8]);
	  cardCount10=Integer.parseInt(strcount[9]);
	  
	  String probStr=move.getProbability();
	  String strPro[] = probStr.split(";");
	  Double rate0=Double.parseDouble(strPro[0]);
	  Double rate1=Double.parseDouble(strPro[1]);
	  Double rate2=Double.parseDouble(strPro[2]);
	  Double rate3=Double.parseDouble(strPro[3]);
	  Double rate4=Double.parseDouble(strPro[4]);
	  Double rate5=Double.parseDouble(strPro[5]);
	  Double rate6=Double.parseDouble(strPro[6]);
	  Double rate7=Double.parseDouble(strPro[7]);
	  Double rate8=Double.parseDouble(strPro[8]);
	  Double rate9=Double.parseDouble(strPro[9]);
	  try {
		cardRecordsCount1=cardReordsService.getByCardType(1,enterId,moveStartTime);
		cardRecordsCount2=cardReordsService.getByCardType(2,enterId,moveStartTime);
		cardRecordsCount3=cardReordsService.getByCardType(3,enterId,moveStartTime);
		cardRecordsCount4=cardReordsService.getByCardType(4,enterId,moveStartTime);
		cardRecordsCount5=cardReordsService.getByCardType(5,enterId,moveStartTime);
		cardRecordsCount6=cardReordsService.getByCardType(6,enterId,moveStartTime);
		cardRecordsCount7=cardReordsService.getByCardType(7,enterId,moveStartTime);
		cardRecordsCount8=cardReordsService.getByCardType(8,enterId,moveStartTime);
		cardRecordsCount9=cardReordsService.getByCardType(9,enterId,moveStartTime);
		cardRecordsCount10=cardReordsService.getByCardType(10,enterId,moveStartTime);
		
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
	  luck = RollUtil.PercentageRandom(rate0, rate1, rate2, rate3, rate4, rate5, rate6, rate7, rate8, rate9);
	  if(count==0){
		  if(luck>0){
			 
			  String cardPrizeDesc="";
			  String cardUserName="";
			  if(luck==1){
				  if(cardRecordsCount1>=cardCount1&&cardCount1!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize1.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="一等奖";
				  }
				  
			 }
			  if(luck==2){
				  if(cardRecordsCount2>=cardCount2&&cardCount2!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize2.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="二等奖";
				  }
				  
			 }
			  if(luck==3){
				  if(cardRecordsCount3>=cardCount3&&cardCount3!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize3.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="三等奖";
				  }
				  
			 }
			  if(luck==4){
				  if(cardRecordsCount4>=cardCount4&&cardCount4!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize4.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="四等奖";
				  }
				  
			 }
			  if(luck==5){
				  if(cardRecordsCount5>=cardCount5&&cardCount5!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize5.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="五等奖";
				  }
				  
			 }
			  if(luck==6){
				  if(cardRecordsCount6>=cardCount6&&cardCount6!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize6.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="六等奖";
				  }
				  
			 }
			  if(luck==7){
				  if(cardRecordsCount7>=cardCount7&&cardCount7!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize7.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="七等奖";
				  }
				  
			 }
			  if(luck==8){
				  if(cardRecordsCount8>=cardCount8&&cardCount8!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize8.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="八等奖";
				  }
				  
			 }
			  if(luck==9){
				  if(cardRecordsCount9>=cardCount9&&cardCount9!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize9.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="九等奖";
				  }
				  
			 }
			  if(luck==10){
				  if(cardRecordsCount10>=cardCount10&&cardCount10!=-1&&maxCardType<luck){
					  fileName=enterId+"card-prize0.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="未中奖";
				  }else{
					  fileName=enterId+"card-prize10.jpg";
					  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
					  cardPrizeDesc="十等奖";
				  }
				  
			 }
				 
				  try {
					  
						  int cardId=cardService.findByCardImage(fileName,enterId);
						  Card card = cardService.findByCardId(cardId,enterId);
						  CardRecords cardRecords= new CardRecords();
						  cardRecords.setCardGetTime(TimeUtil.getTime());
						  cardRecords.setCardUserName(fromUsername);
						  cardRecords.setCardPrizeDesc(cardPrizeDesc);
						  cardRecords.setCard(card);
						  cardReordsService.saveCardRecords(cardRecords,enterId);
					 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "prize2";
				}
		  }else{
			  
			  fileName=enterId+"card-prize0.jpg";//未中奖的也存上
			  int cardId=cardService.findByCardImage(fileName,enterId);
			  Card card = cardService.findByCardId(cardId,enterId);
			  CardRecords cardRecords= new CardRecords();
			  cardRecords.setCardGetTime(TimeUtil.getTime());
			  cardRecords.setCardUserName(fromUsername);
			  cardRecords.setCard(card);
			  cardRecords.setCardPrizeDesc("未中奖");
			  
			  cardReordsService.saveCardRecords(cardRecords,enterId);
		  }
	  }else{
		  fileName=enterId+"card-prize-1.jpg";//重复刮奖的不需要存了
		  prvFileName="/wxpt/web/images/"+enterId+"/"+fileName;
		  return "prizeHas";
	  }
	  
	  
	  
	return "prize2";
	  
  }
  public String getLuck2(){
		return "prize1";
		  
	  }
  public String getLuck3(){
		return "prize2";
		  
	  }
  
  
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public int getLuck() {
	return luck;
}
public void setLuck(int luck) {
	this.luck = luck;
}
public String getFromUsername() {
	return fromUsername;
}
public void setFromUsername(String fromUsername) {
	this.fromUsername = fromUsername;
}
public int getMaxCardType() {
	return maxCardType;
}
public void setMaxCardType(int maxCardType) {
	this.maxCardType = maxCardType;
}
public int getEnterId() {
	return enterId;
}
public void setEnterId(int enterId) {
	this.enterId = enterId;
}

  
}
