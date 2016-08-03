package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.GreetingCard;
import com.wxpt.site.entity.GreetingCardProterty;
import com.wxpt.site.entity.GreetingCardTemplate;
import com.wxpt.site.entity.GreetingCardType;
import com.wxpt.site.entity.pojo.GreetingCardPojo;
import com.wxpt.site.entity.pojo.GreetingCardTemplatePojo;
import com.wxpt.site.entity.pojo.GreetingCardTypePojo;
import com.wxpt.site.service.GreetingCardService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "page", location = "success.jsp") })
public class ManageGreetingCardAction extends ParentAction {
	JSONArray jsonls;
	private int count;
	private int page;
	private int rows;
	private List<GreetingCardType> cardTypeList;
	private List<GreetingCard> cardList;
	@Autowired
	GreetingCardService gCardService;
	private List<GreetingCardPojo> cardPojoList;
	private List<GreetingCardTypePojo> cardTypePojoList;
	private List<GreetingCardTemplate> cardTemplateList;
	private List<GreetingCardTemplatePojo> cardTemplatePojoList;
	private String sql;
	private int result;
	FileUploadBean bean = new FileUploadBean();

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card";
		cardList = this.getCards(sql, page, rows);
		count = gCardService.getCountBySql(sql);
		cardPojoList = new ArrayList<GreetingCardPojo>();
		for (GreetingCard greetingCard : cardList) {
			String stateStr = "否";
			GreetingCardTemplate cardTemplate = gCardService.getCardTemplate(
					"select * from webchat.greeting_card_template where card_template_id = "
							+ greetingCard.getCardTemplateId()).get(0);
			sql = "select * from wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty";

			try {
				cardProterty = gCardService.getCardProterty(sql).get(0);
				if (cardProterty.getCardId() == greetingCard.getCardId()) {
					stateStr = "是";
				}
			} catch (Exception e) {
				// TODO: handle exception
				stateStr = "否";
			}

			GreetingCardPojo cardPojo = new GreetingCardPojo(
					greetingCard.getCardId(), cardTemplate.getCardTemplateId(),
					cardTemplate.getCardTemplateName(),
					cardTemplate.getCardTemplateState(),
					greetingCard.getCardValue(), "<img src =\"../web/images/"
							+ super.getCookieByEnterID() + "/sendCard/"
							+ greetingCard.getCardBgImage()
							+ "\" style=\"width:200px ; height:100px ;\"/>",
					"<img src =\"../web/images/" + super.getCookieByEnterID()
							+ "/sendCard/" + greetingCard.getCardTxtImage()
							+ "\" style=\"width:200px ; height:100px ;\"/>",
					stateStr);
			cardPojo.setCardTitleImage("<img src =\"../web/images/"
					+ super.getCookieByEnterID() + "/sendCard/"
					+ greetingCard.getCardTitleImage()
					+ "\" style=\"width:100px ; height:100px ;\"/>");
			cardPojo.setCardName(greetingCard.getCardName());
			cardPojoList.add(cardPojo);
		}
		jsonls = JSONArray.fromObject(cardPojoList);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 获取所有贺卡模板
	 * 
	 * @return
	 */
	public String cardTemplateEdit() {
		sql = "select * from webchat.greeting_card_template";
		cardTemplateList = this.getCardTemplates(sql,page,rows);
		cardTemplatePojoList = new ArrayList<GreetingCardTemplatePojo>();
		for (GreetingCardTemplate cardTemplate : cardTemplateList) {
			GreetingCardTemplatePojo cardTemplatePojo = new GreetingCardTemplatePojo();
			cardTemplatePojo
					.setCardTemplateId(cardTemplate.getCardTemplateId());
			cardTemplatePojo.setCardTemplateName(cardTemplate
					.getCardTemplateName());
			cardTemplatePojo
					.setCardTemplatePath("<img src =\"../sendCard/images/"
							+ cardTemplate.getCardTemplatePath()
							+ "\" style=\"width:200px ; height:300px ;\"/>");
			cardTemplatePojo.setCardTemplateState(cardTemplate
					.getCardTemplateState());
			if (cardTemplate.getCardTemplateState() == 0) {
				cardTemplatePojo.setStateStr("否");
			}
			if (cardTemplate.getCardTemplateState() == 1) {
				cardTemplatePojo.setStateStr("默认");
			}
			cardTemplatePojoList.add(cardTemplatePojo);
		}
		jsonls = JSONArray.fromObject(cardTemplatePojoList);
		count = gCardService.getCountBySql(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public List<GreetingCardTemplate> getCardTemplates(String sql) {
		return gCardService.getCardTemplate(sql);
	}

	public List<GreetingCardTemplate> getCardTemplates(String sql, int page,
			int rows) {
		return gCardService.getCardTemplate(sql, page, rows);
	}

	public String cardTypeEdit() {
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_type";
		cardTypeList = this.getCardTypes(sql, page, rows);
		cardTypePojoList = new ArrayList<GreetingCardTypePojo>();
		for (GreetingCardType cardType : cardTypeList) {
			String stateStr = "否";
			if (cardType.getCardTypeState() == 1) {
				stateStr = "是";
			}
			GreetingCardTypePojo cardTypePojo = new GreetingCardTypePojo(
					cardType.getCardTypeId(), cardType.getCardTypeName(),
					cardType.getCardTypeState(), stateStr);
			cardTypePojoList.add(cardTypePojo);
		}
		jsonls = JSONArray.fromObject(cardTypePojoList);
		count = gCardService.getCountBySql(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public String editCardType() {
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_type";
		cardTypeList = this.getCardTypes(sql);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "greetingCards",
					"fandsGreetingCards" });
			jsonls = JSONArray.fromObject(cardTypeList, jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.out.print(jsonls);
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 获取所有贺卡数据
	 * 
	 * @param sql
	 * @return
	 */
	public List<GreetingCard> getCards(String sql) {
		cardList = gCardService.getCard(sql);
		return cardList;
	}

	/**
	 * 获取所有贺卡类型
	 * 
	 * @param sql
	 * @return
	 */
	public List<GreetingCardType> getCardTypes(String sql) {
		cardTypeList = gCardService.getCardType(sql);
		return cardTypeList;
	}

	/**
	 * 分页显示贺卡类型
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GreetingCardType> getCardTypes(String sql, int page, int rows) {
		cardTypeList = gCardService.getCardType(sql, page, rows);
		return cardTypeList;
	}

	/**
	 * 分页显示贺卡数据
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GreetingCard> getCards(String sql, int page, int rows) {
		cardList = gCardService.getCard(sql, page, rows);
		return cardList;
	}

	private String cardTypeName;
	private int cardTypeId;
	private String cardName;
	/**
	 * 添加贺卡类型
	 * 
	 * @return
	 */
	public String addCardType() {
		sql = "insert into wxpt"
				+ super.getCookieByEnterID()
				+ ".greeting_card_type (`card_type_name`, `card_type_state`) VALUES ('"
				+ cardTypeName + "',0)";
		result = gCardService.executeSql(sql);

		super.out.print("{\"state\":\"" + result + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 修改贺卡类型
	 * 
	 * @return
	 */
	public String updateCardType() {
		sql = "update wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_type set `card_type_name` = '" + cardTypeName
				+ "' where `card_type_id` = " + cardTypeId;
		result = gCardService.executeSql(sql);
		super.out.print("{\"state\":\"" + result + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 删除贺卡类型
	 * 
	 * @return
	 */
	public String deleteCardType() {
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card where `card_type_Id` = " + cardTypeId;
		cardList = this.getCards(sql);
		for (GreetingCard card : cardList) {
			bean.deletefile(super.getCookieByEnterID() + "/" + "sendCard/"
					+ card.getCardBgImage());
			bean.deletefile(super.getCookieByEnterID() + "/" + "sendCard/"
					+ card.getCardTxtImage());
		}
		sql = "delete FROM  wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_type where `card_type_id` = " + cardTypeId;
		result = gCardService.executeSql(sql);
		super.out.print("{\"state\":\"" + result + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * 检查该类型下是否可以添加贺卡
	 * 
	 * @return
	 */
	public String checkCard() {
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card where `card_type_Id` = " + cardTypeId;
		count = gCardService.getCountBySql(sql);
		super.out.print("{\"state\":" + count + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private String cardValue;
	private int cardTypeID;
	private File cardBgImage;
	private File cardTxtImage;
	private String message;
	private int cardId;
	private File cardTitleImage;
	private int cardTemplateId;

	/**
	 * 添加贺卡
	 * 
	 * @return
	 */
	public String addCard() {
		String cardBgImageStr = "bg_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".jpg";
		bean.upLoad(cardBgImageStr, cardBgImage, super.getCookieByEnterID()
				+ "/" + "sendCard/");
		String cardTxtImageStr = "txt_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".jpg";
		bean.upLoad(cardTxtImageStr, cardTxtImage, super.getCookieByEnterID()
				+ "/" + "sendCard/");
		String cardTitleImageStr = "title_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".png";
		bean.upLoad(cardTitleImageStr, cardTitleImage,
				super.getCookieByEnterID() + "/" + "sendCard/");
		sql = "INSERT INTO wxpt"
				+ super.getCookieByEnterID()
				+ ".greeting_card (`card_template_id`, `card_value`, `card_bg_image`, `card_txt_image`,`card_title_image`,`card_name`) VALUES ("
				+ cardTemplateId + ",'" + cardValue + "','" + cardBgImageStr
				+ "','" + cardTxtImageStr + "','" + cardTitleImageStr + "','"+cardName+"')";
		result = gCardService.executeSql(sql);
		if (result == 0) {
			message = "添加失败";
		} else {
			message = "添加成功";
		}
		return "page";
	}

	/**
	 * 修改贺卡
	 * 
	 * @return
	 */
	public String updateCard() {
		GreetingCard card = this.getCards(
				"select * from wxpt" + super.getCookieByEnterID()
						+ ".greeting_card where `card_id` = " + cardId).get(0);
		sql = "update wxpt" + super.getCookieByEnterID()
				+ ".greeting_card set `card_value` ='"
				+ cardValue.replace("\"", "\\\"") + "'";
		String cardBgImageStr = "bg_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".jpg";

		String cardTxtImageStr = "txt_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".jpg";

		String cardTitleImageStr = "title_" + super.getCookieByEnterID() + "_"
				+ cardTypeID + "_" + TimeUtil.getImagesTime() + ".png";

		if (cardBgImage != null) {
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardBgImage());
			bean.upLoad(cardBgImageStr, cardBgImage, super.getCookieByEnterID()
					+ "/" + "sendCard/");
			sql += ", `card_bg_image` = '" + cardBgImageStr + "'";
		}
		if (cardTxtImage != null) {
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardTxtImage());
			bean.upLoad(cardTxtImageStr, cardTxtImage,
					super.getCookieByEnterID() + "/" + "sendCard/");
			sql += ",`card_txt_image` = '" + cardTxtImageStr + "'";
		}
		if (cardTitleImage != null) {
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardTitleImage());
			bean.upLoad(cardTitleImageStr, cardTitleImage,
					super.getCookieByEnterID() + "/" + "sendCard/");
			sql += ",`card_title_image` = '" + cardTitleImageStr + "'";
		}
		sql += ",card_template_id = " + cardTemplateId + ",`card_name` = '"+cardName+"' where `card_id` = "
				+ cardId;
		result = gCardService.executeSql(sql);
		if (result == 0) {
			message = "修改失败";
		} else {
			message = "修改成功";
		}
		return "page";
	}

	/*
	 * 删除贺卡
	 */
	private String cardIds;

	public String deleteCard() {
		cardList = this.getCards("select * from wxpt"
				+ super.getCookieByEnterID()
				+ ".greeting_card where `card_id` in (" + cardIds + ")");
		for (GreetingCard card : cardList) {
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardBgImage());
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardTxtImage());
			bean.deletefile(super.getCookieByEnterID() + "/sendCard/"
					+ card.getCardTitleImage());
		}
		sql = "DELETE FROM wxpt" + super.getCookieByEnterID()
				+ ".greeting_card where `card_id` in (" + cardIds + ")";
		result = gCardService.executeSql(sql);
		if (result == 0) {
			message = "删除失败";
		} else {
			message = "删除成功";
		}
		return "success";
	}

	/*
	 * 设置默认贺卡
	 */

	private GreetingCardProterty cardProterty;

	public String sehZhi() {
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_proterty";
		try {
			cardProterty = gCardService.getCardProterty(sql).get(0);
			sql = "update wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty set `card_id` = " + cardId
					+ " where `card_proterty_id` = "
					+ cardProterty.getCardProtertyId();
			result = gCardService.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			sql = "insert into wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty(`card_id`) values(" + cardId
					+ ")";
			result = gCardService.executeSql(sql);
		}
		super.out.print("{\"state\":\"" + result + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	public String getProterty(){
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_proterty";
		String url = "";
		try {
			cardProterty = gCardService.getCardProterty(sql).get(0);
			url = cardProterty.getGuideUrl();
			if(url == null){
				url = "http://";
			}
		} catch (Exception e) {
			// TODO: handle exception
			url="http://";
			
		}
		super.out.print("{\"url\":\"" + url + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	private String errorUrlStr;
	
	public String udpateProperty(){
		sql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".greeting_card_proterty";
		try {
			cardProterty = gCardService.getCardProterty(sql).get(0);
			sql = "update wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty set `guide_url` = '" + errorUrlStr
					+ "' where `card_proterty_id` = "
					+ cardProterty.getCardProtertyId();
			result = gCardService.executeSql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			sql = "insert into wxpt" + super.getCookieByEnterID()
					+ ".greeting_card_proterty(`guide_url`) values(" + errorUrlStr
					+ ")";
			result = gCardService.executeSql(sql);
		}
		super.out.print("{\"state\":\"" + result + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	public String getCardIds() {
		sql = "update from  ";
		return cardIds;
	}

	public void setCardIds(String cardIds) {
		this.cardIds = cardIds;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public int getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getCardValue() {
		return cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

	public int getCardTypeID() {
		return cardTypeID;
	}

	public void setCardTypeID(int cardTypeID) {
		this.cardTypeID = cardTypeID;
	}

	public File getCardBgImage() {
		return cardBgImage;
	}

	public void setCardBgImage(File cardBgImage) {
		this.cardBgImage = cardBgImage;
	}

	public File getCardTxtImage() {
		return cardTxtImage;
	}

	public void setCardTxtImage(File cardTxtImage) {
		this.cardTxtImage = cardTxtImage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public File getCardTitleImage() {
		return cardTitleImage;
	}

	public void setCardTitleImage(File cardTitleImage) {
		this.cardTitleImage = cardTitleImage;
	}

	public int getCardTemplateId() {
		return cardTemplateId;
	}

	public void setCardTemplateId(int cardTemplateId) {
		this.cardTemplateId = cardTemplateId;
	}

	public String getErrorUrlStr() {
		return errorUrlStr;
	}

	public void setErrorUrlStr(String errorUrlStr) {
		this.errorUrlStr = errorUrlStr;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

}
