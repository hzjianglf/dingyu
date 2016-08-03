package com.wxpt.action.site.web.template;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.action.site.ParentAction;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.FandsGreetingCard;
import com.wxpt.site.entity.GreetingCard;
import com.wxpt.site.entity.GreetingCardProterty;
import com.wxpt.site.entity.GreetingCardTemplate;
import com.wxpt.site.entity.GreetingCardType;
import com.wxpt.site.entity.pojo.GreetingCardPojo;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.GreetingCardService;
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "error", location = "/sendCard/error.jsp"),
		@Result(name = "test", location = "/sendCard/test.jsp"),
		@Result(name="guanzhu" , location = "/sendCard/guanzhu.jsp"),
		@Result(name = "sendCard1", location = "/sendCard/card1.jsp"),
		@Result(name="show1" , location = "/sendCard/showCard1.jsp"),
		@Result(name = "sendCard2", location = "/sendCard/card2.jsp"),
		@Result(name="show2" , location = "/sendCard/showCard2.jsp"),
		@Result(name = "sendCard3", location = "/sendCard/card3.jsp"),
		@Result(name="show3" , location = "/sendCard/showCard3.jsp"),
		@Result(name = "sendCard4", location = "/sendCard/card4.jsp"),
		@Result(name="show4" , location = "/sendCard/showCard4.jsp"),
		@Result(name = "sendCard5", location = "/sendCard/card5.jsp"),
		@Result(name="show5" , location = "/sendCard/showCard5.jsp"),
		@Result(name = "sendCard6", location = "/sendCard/card6.jsp"),
		@Result(name="show6" , location = "/sendCard/showCard6.jsp"),
		@Result(name = "sendCard7", location = "/sendCard/card7.jsp"),
		@Result(name="show7" , location = "/sendCard/showCard7.jsp"),
		@Result(name = "sendCard8", location = "/sendCard/card8.jsp"),
		@Result(name="show8" , location = "/sendCard/showCard8.jsp"),
		@Result(name = "sendCard9", location = "/sendCard/card9.jsp"),
		@Result(name="show9" , location = "/sendCard/showCard9.jsp"),
		@Result(name = "sendCard10", location = "/sendCard/card10.jsp"),
		@Result(name="show10" , location = "/sendCard/showCard10.jsp"),
		@Result(name = "sendCard10", location = "/sendCard/card10.jsp"),
		@Result(name="show10" , location = "/sendCard/showCard10.jsp"),
		@Result(name = "sendCard11", location = "/sendCard/card11.jsp"),
		@Result(name="show11" , location = "/sendCard/showCard11.jsp"),
		@Result(name = "sendCard12", location = "/sendCard/card12.jsp"),
		@Result(name="show12" , location = "/sendCard/showCard12.jsp"),
		@Result(name = "sendCard13", location = "/sendCard/card13.jsp"),
		@Result(name="show13" , location = "/sendCard/showCard13.jsp"),
		@Result(name = "sendCard14", location = "/sendCard/card14.jsp"),
		@Result(name="show14" , location = "/sendCard/showCard14.jsp")})
public class CardAction extends ParentAction {
	private int enterID;
	private int cardTypeId;
	private int cardId;
	private String fromUser;
	private String enterName;
	private GreetingCard card;
	private GreetingCardType cardType;
	private GreetingCardPojo cardPojo;
	private GreetingCardProterty cardProterty;
	private GreetingCardTemplate cardTemplate;
	private String sql ;
	private String time;
	@Autowired
	EnterService enterService;
	@Autowired
	GreetingCardService gCardService;
	private String cardsContent;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		enterName = enterService.getById(enterID).getEnterName();
		time = TimeUtil.getDayTime();
		if(cardId == 0){
			sql = "select * from wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty";
			try {
				cardProterty = (GreetingCardProterty) gCardService.getCardProterty(sql).get(0);
				sql = "select * from wxpt" + enterID
						+ ".greeting_card where `card_id` =  " + cardProterty.getCardId() ;
			} catch (Exception e) {
				// TODO: handle exception
				return "error";
			}
		}else{
			sql = "select * from wxpt" + enterID
					+ ".greeting_card where `card_id` =  " + cardId ;
		}
		
		try {
			card = (GreetingCard) gCardService.getCard(sql).get(0);
			cardTemplate = (GreetingCardTemplate) gCardService.getCardTemplate(
					"select * from webchat.greeting_card_template where card_template_id = "
							+ card.getCardTemplateId()).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
		return "sendCard"+cardTemplate.getCardTemplateName();
	}
	private int fandCardId;
	public String addFansCard(){
		try {
			sql = "insert into wxpt" + enterID
					+ ".fands_greeting_card (`fromUser`, `card_id`, `card_value`) VALUES ('"+fromUser+"',"+cardId+",'"+cardsContent+"')";
			gCardService.executeSql(sql);
			fandCardId = gCardService.getFansCard("select * from wxpt" + enterID
					+ ".fands_greeting_card where `fands_card_id` = (select max(fands_card_id) from wxpt" + enterID
					+ ".fands_greeting_card where `fromUser` = '"+fromUser+"')  ").getFandsCardId();
			super.out.print("{\"state\":\"1\",\"fandCardId\":\""+gCardService.executeSql(sql,enterID)+"\"}");
		} catch (Exception e) {
			// TODO: handle exception
			super.out.print("{\"state\":\"0\"}");
		}
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	public String getFandCard(){
		cardPojo = new GreetingCardPojo();
		FandsGreetingCard fanCard = gCardService.getFansCard("select * from wxpt" + enterID
					+ ".fands_greeting_card where `fands_card_id` = " + fandCardId);
		card = (GreetingCard) gCardService.getCard("select * from wxpt" + enterID
				+ ".greeting_card where `card_id` = " + fanCard.getGreetingCard().getCardId()).get(0);
		
		cardPojo.setCardValue(fanCard.getCardValue());
		cardPojo.setCardBgImage(card.getCardBgImage());
		cardPojo.setCardId(card.getCardId());
		cardPojo.setCardTxtImage(card.getCardTxtImage());
		cardPojo.setCardTypeId(card.getCardTemplateId());
		cardPojo.setCardTypeName(card.getCardName());
		enterName = enterService.getById(enterID).getEnterName();
		fromUser = fanCard.getFromUser();
		time = TimeUtil.getDayTime();
		cardTemplate = (GreetingCardTemplate) gCardService.getCardTemplate(
				"select * from webchat.greeting_card_template where card_template_id = "
						+ card.getCardTemplateId()).get(0);
		return "show"+cardTemplate.getCardTemplateName();
	}
	
	public String getGuanZhu(){
		enterName = enterService.getById(enterID).getEnterName();
		return "guanzhu";
	}
	
	public String getError(){
		enterName = enterService.getById(enterID).getEnterName();
		return "error";
	}
	
	public String getTest(){
		enterName = enterService.getById(enterID).getEnterName();
		return "test";
	}
	
	public GreetingCardPojo getCardPojo() {
		return cardPojo;
	}

	public void setCardPojo(GreetingCardPojo cardPojo) {
		this.cardPojo = cardPojo;
	}

	public int getEnterID() {
		return enterID;
	}
	public void setEnterID(int enterID) {
		this.enterID = enterID;
	}
	
	public int getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	public GreetingCardType getCardType() {
		return cardType;
	}
	public void setCardType(GreetingCardType cardType) {
		this.cardType = cardType;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getEnterName() {
		return enterName;
	}
	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}
	public GreetingCard getCard() {
		return card;
	}
	public void setCard(GreetingCard card) {
		this.card = card;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCardsContent() {
		return cardsContent;
	}
	public void setCardsContent(String cardsContent) {
		this.cardsContent = cardsContent;
	}

	public int getFandCardId() {
		return fandCardId;
	}

	public void setFandCardId(int fandCardId) {
		this.fandCardId = fandCardId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public GreetingCardProterty getCardProterty() {
		return cardProterty;
	}

	public void setCardProterty(GreetingCardProterty cardProterty) {
		this.cardProterty = cardProterty;
	}

	public GreetingCardTemplate getCardTemplate() {
		return cardTemplate;
	}

	public void setCardTemplate(GreetingCardTemplate cardTemplate) {
		this.cardTemplate = cardTemplate;
	}
	
}
