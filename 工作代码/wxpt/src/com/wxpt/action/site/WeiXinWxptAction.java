package com.wxpt.action.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.HttpRequest;
import com.wxpt.common.Httpclient;
import com.wxpt.common.QuickMark;
import com.wxpt.common.ReplyStr;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.CustomersDao;
import com.wxpt.site.dao.FansDao;
import com.wxpt.site.dao.LuckDao;
import com.wxpt.site.dao.QuestionDao;
import com.wxpt.site.entity.Answer;
import com.wxpt.site.entity.AnswerRecords;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.CheckIn;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansUser;
import com.wxpt.site.entity.Idiom;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.LuckUser;
import com.wxpt.site.entity.Luckanwer;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.Prize;
import com.wxpt.site.entity.Question;
import com.wxpt.site.entity.QuestionRule;
import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.entity.QuestionType;
import com.wxpt.site.entity.Role;
import com.wxpt.site.entity.Shiyong;
import com.wxpt.site.entity.Survey;
import com.wxpt.site.entity.TrainNumber;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.pojo.Weather;
import com.wxpt.site.service.AreaService;
import com.wxpt.site.service.CardReordsService;
import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.FansService;
import com.wxpt.site.service.ImageService;
import com.wxpt.site.service.IndustryService;
import com.wxpt.site.service.LuckService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.MoveService;
import com.wxpt.site.service.QuestionService;
import com.wxpt.site.service.RadiusService;
import com.wxpt.site.service.ReplyService;
import com.wxpt.site.service.RoleService;
import com.wxpt.site.service.SurveyService;
import com.wxpt.site.service.UserService;

public class WeiXinWxptAction extends ActionSupport {
	ImageService imageservice;
	CheckIn checkin = null;
	List<CheckIn> checklist;
	List<CheckIn> checklist1;
	List<CheckIn> checklistTime = null;
	List<CheckIn> checklistTime2 = null;
	List<CheckIn> checklistTime3 = null;
	List<CheckIn> checklistByName;
	List<Prize> prizelistByName = null;
	EnterService enterService = null;
	private int enterId;
	Move move;
	Member member;
	QuestionDao questionDao;
	List<QuestionRule> ruleList = null;
	List<QuestionTishi> tishiList = null;
	List<Shiyong>shiyongList=null;
	/* final String TOKEN = "wxt"; */
	final HttpServletRequest final_request = ServletActionContext.getRequest();
	final HttpServletResponse final_response = ServletActionContext
			.getResponse();
	String filepath = ServletActionContext.getServletContext().getRealPath(
			"web")
			+ "/images/" + enterId + "/";

	public void valid() throws Exception {
		String echostr = final_request.getParameter("echostr");
		if (null == echostr || echostr.isEmpty()) {
			responseMsg();
		} else {
			if (this.checkSignature()) {
				this.print(echostr);
			} else {
				this.print("error");
			}
		}

	}

	@Autowired
	UserService userService;
	@Autowired
	MemberService memberService;
	@Autowired
	QuestionService questionService;
	@Autowired
	LuckService luckService;
	LuckDao luckDao;
	@Autowired
	MoveService moveService;
	@Autowired
	CardReordsService cardReordsService;
	@Autowired
	ReplyService replyService;
	@Autowired
	RadiusService radiusService;
	@Autowired
	FansService fansService;
	FansDao fansDao;
	@Autowired
	CustomersService customersService;
	CustomersDao customersDao;
	@Autowired
	AreaService areaService;
	@Autowired
	IndustryService industryService;
	@Autowired
	SurveyService surveyService;

	@SuppressWarnings({ "static-access", "unused" })
	public void responseMsg() throws Exception {
		String postStr = null;
		try {
			postStr = this.readStreamParameter(final_request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(postStr);
		if (null != postStr && !postStr.isEmpty()) {
			Document document = null;
			try {
				document = DocumentHelper.parseText(postStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null == document) {
				this.print("");
				return;
			}
			Element root = document.getRootElement();
			String fromUsername = root.elementText("FromUserName");
			String toUsername = root.elementText("ToUserName");
			String keyword = root.elementTextTrim("Content");
			String time = new Date().getTime() + "";
			String event = root.elementTextTrim("Event");
			String MsgType = root.elementTextTrim("MsgType");
			String Location_X = root.elementTextTrim("Location_X");
			String Location_Y = root.elementTextTrim("Location_Y");
			String keyButton = root.elementTextTrim("EventKey");
			String picUrl = root.elementTextTrim("PicUrl");
			String textTpl = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>"
					+ "<Content><![CDATA[%5$s]]></Content>"
					+ "<FuncFlag>0</FuncFlag>" + "</xml>";
			String textTpl1 = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>"
					+ "<ArticleCount>%5$s</ArticleCount>" + "<Articles>"
					+ "<item>" + "<Title><![CDATA[%6$s]]></Title>"
					+ "<Description><![CDATA[%7$s]]></Description>"
					+ "<PicUrl><![CDATA[%8$s]]></PicUrl>"
					+ "<Url><![CDATA[%9$s]]></Url>" + "</item>" + "</Articles>"
					+ "<FuncFlag>1</FuncFlag>" + "</xml> ";
			// 图片
			String textTplpic = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>"
					+ "<ArticleCount>%5$s</ArticleCount>" + "<Articles>"
					+ "<item>" + "<Title><![CDATA[%6$s]]></Title>"
					+ "<Description><![CDATA[%7$s]]></Description>"
					+ "<PicUrl><![CDATA[%8$s]]></PicUrl>"
					+ "<Url><![CDATA[%9$s]]></Url>" + "</item>" + "</Articles>"
					+ "<FuncFlag>1</FuncFlag>" + "</xml> ";
			String textTpl3Content = "";
			String textTpl3 = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>"
					+ "<ArticleCount>%5$s</ArticleCount>%6$s"
					+ "<FuncFlag>1</FuncFlag>" + "</xml> ";
			String music = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>" + "<Music>"
					+ "<Title><![CDATA[%5$s]]></Title>"
					+ "<Description><![CDATA[%6$s]]></Description>"
					+ "<MusicUrl><![CDATA[%7$s]]></MusicUrl>"
					+ "<HQMusicUrl><![CDATA[%8$s]]></HQMusicUrl>" + "</Music>"
					+ "<FuncFlag>0</FuncFlag>" + "</xml>";
			String sql0 = "SELECT * FROM wxpt"
					+ enterId
					+ ".answer WHERE answerid in(SELECT MAX(answerid)FROM  wxpt"
					+ enterId + ".answer WHERE 1)";
			String sqlLuch = "SELECT * FROM wxpt"
					+ enterId
					+ ".answer_records WHERE recordId in (SELECT max(recordId) FROM wxpt"
					+ enterId + ".answer_records WHERE 1)";
			String shiyong="SELECT * FROM wxpt"+enterId+".shiyong WHERE 1";
			shiyongList=enterService.getall(shiyong);
			int MaxId;
			int type = 0;
			/* 菜单部分开始 */
			if (MsgType.equals("event")) {
				if (event.equals("subscribe")) {

				} else if (event.equals("CLICK")) {
					keyword = keyButton;
					Keywords kwords = userService.getKeyBysendContent(enterId,
							keyword);
					if (kwords != null) {
						List<Keywordexplicit> w = userService
								.getkExplicitByEkey(enterId,kwords.getKeywordId(), 0);
						if (w.size() != 0) {
							Random i = new Random();
							Keywordexplicit k = w.get(i.nextInt(w.size()));
							if (k.getEkey().equals("word")) {
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, k.getEcontent());
								this.print(resultStr);
							}
							if (k.getEkey().equals("vidio")) {
								String msgType = "music";
								String resultStr = music.format(
										music,
										fromUsername,
										toUsername,
										TimeUtil.getImagesTime(),
										msgType,
										k.getTitle(),
										k.getEinstruction(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent());
								this.print(resultStr);
							}
							if (k.getEkey().equals("images")) {
								String msgType = "news";
								String resultStr = textTpl1.format(
										textTpl1,
										fromUsername,
										toUsername,
										k.getAddTime(),
										msgType,
										1,
										k.getTitle(),
										k.getEinstruction(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent(), k.getUrl());
								this.print(resultStr);
							}
						}

						List<Keywordexplicit> imageList = userService
								.getkExplicitByEkey(enterId,
										kwords.getKeywordId(), "images", 1);

						if (imageList.size() > 0) {
							String d = "<Articles>";
							for (Keywordexplicit k : imageList) {
								d += "<item>"
										+ "<Title><![CDATA["
										+ k.getTitle()
										+ "]]></Title>"
										+ "<Description><![CDATA["
										+ k.getEinstruction()
										+ "]]></Description>"
										+ "<PicUrl><![CDATA["
										+ "http://www.uniqyw.com/wxpt/web/images/"
										+ enterId + "/" + k.getEcontent()
										+ "]]></PicUrl>" + "<Url><![CDATA["
										+ k.getUrl() + "]]></Url>" + "</item>";
							}

							d += "</Articles>";
							textTpl3Content = d;
							String msgType = "news";
							String resultStr = textTpl3.format(textTpl3,
									fromUsername, toUsername,
									TimeUtil.getImagesTime(), msgType,
									imageList.size(), textTpl3Content);
							this.print(resultStr);
						}

					}

				}

			}
			/* 菜单部分结束 */

			/* 光向移植开始 */
			if (MsgType.equals("location")) {
				int radius = radiusService.getRadiusList(enterId).get(0)// 半径
						.getRadiusContent();
				String centerName = radiusService.getRadiusList(enterId).get(0)
						.getCenterName();
				// String hql =
				// "from Customers where 1=1 and locationX != '' and locationY != '' ";
				// String
				// hql="select * from wxpt"+enterId+".customers where where 1=1 and location_x != '' and location_y != '' ";
				String contentStr = "";
				String areaStr = "";
				String industryStr = "";
				String msgType = "text";
				Fans fans = fansService.getFansByName(enterId, fromUsername);
				if (fans != null && fans.getFansValue().contains("jiansuo")) {
					String sql = "update wxpt" + enterId
							+ ".fans set `fans_value`='suo:"
							+ fans.getFansValue().split(":")[1] + ":x:"
							+ Location_X + ":y:" + Location_Y
							+ " ' where `fans_id`=" + fans.getFansId();
					fansService.updateFans(enterId, sql);
					contentStr = "输入检索的类型如：银行、酒店、超市、、、、";
					msgType = "text";
					String resultStr = (ReplyStr.getTextTpl()).format(
							ReplyStr.getTextTpl(), fromUsername, toUsername,
							time, msgType, contentStr);
					this.print(resultStr);
				}
				if (fans == null || !fans.getFansValue().contains("dtsave")
						|| fans.getFansValue().contains("radius:")) {
					if (Location_X != null || Location_Y != null) {
						if (fans != null) {
							if (fans.getFansValue().contains("radius:")) {
								String[] s = fans.getFansValue().split(":");
								radius = Integer.parseInt(s[1]);
								fansService.deleteFans(enterId, fans);
							}
						}

						List<Customers> customersList = customersService
								.getCustomersListByXY(enterId);
						String ids = "";
						String markers = "";
						String markerStyles = "";
						int j = 1;
						for (int i = 0; i < customersList.size(); i++) {
							Customers customers = customersList.get(i);

							double distance = HttpRequest
									.GetDistance(
											Double.parseDouble(Location_X),
											Double.parseDouble(Location_Y),
											Double.parseDouble(customers
													.getLocationX()), Double
													.parseDouble(customers
															.getLocationY()));
							if (distance < radius * 1000 && distance >= 0) {

								if ((i + 1) == customersList.size()) {
									ids += customers.getCustomersId();
								} else {
									ids += customers.getCustomersId() + ",";
								}
								if ((i + 1) == customersList.size() || i == 100) {
									markers += customers.getLocationY() + ","
											+ customers.getLocationX();
									break;
								} else {
									markers += customers.getLocationY() + ","
											+ customers.getLocationX()
											+ ReplyStr.getStr("|");
								}
								if ((i + 1) == customersList.size()) {
									markerStyles += "l" + "," + j;
								} else {
									markerStyles += "l" + "," + j
											+ ReplyStr.getStr("|");
								}
								j++;
							}

						}
						if (ids.equals("")) {
							contentStr = "方圆" + radius + "公里附近没有客户";
							msgType = "text";
							String resultStr = (ReplyStr.getTextTpl()).format(
									ReplyStr.getTextTpl(), fromUsername,
									toUsername, time, msgType, contentStr);
							this.print(resultStr);
						} else {
							if (markers.substring(markers.length() - 1,
									markers.length()).equals(
									ReplyStr.getStr("|"))) {
								markers = markers.substring(0,
										markers.length() - 1);
							}
							String cityName = radiusService
									.getRadiusList(enterId).get(0)
									.getCenterName();
							try {
								cityName = ReplyStr.getStr(cityName);
							} catch (Exception e) {
							}
							msgType = "news";
							String resultStr = (ReplyStr.getImgTpl()).format(
									ReplyStr.getImgTpl(), fromUsername,
									toUsername, time, msgType, 1, "附近" + radius
											+ "公里客户", "共有"
											+ (ids.split(",")).length + "客户",
									"http://api.map.baidu.com/staticimage?center="
											+ Location_Y + "," + Location_X
											+ "&width=640&height=320&markers="
											+ markers + "&markerStyles="
											+ markerStyles,
									// "http://www.uniqyw.com/wxpt/site/web/web-weixin!queryRadius?enterId=62&userXY=117.134900,36.679020&type=1&radius=1000"
									"http://www.uniqyw.com/wxpt/site/web/web-weixin!queryRadius?enterId="
											+ enterId + "&userXY=" + Location_Y
											+ "," + Location_X
											+ "&type=1&radius=" + radius);
							this.print(resultStr);
						}
					}
				} else {
					String[] value = fans.getFansValue().split(":");
					Customers customers = customersService.getCustomersByNo(
							enterId, value[1]);
					/*
					 * customers.setLocationX(Location_X);
					 * customers.setLocationY(Location_Y);
					 */
					customersDao.updateCustomer(enterId, Location_X,
							Location_Y, customers.getCustomersId());
					fansService.deleteFans(enterId, fans);
					contentStr = "客户信息已更新,发送你的位置,可检索到该客户信息!";
					msgType = "text";
					String resultStr = (ReplyStr.getTextTpl()).format(
							ReplyStr.getTextTpl(), fromUsername, toUsername,
							time, msgType, contentStr);
					this.print(resultStr);
				}
			}
			if (MsgType.equals("text")) {
				String sqll = "SELECT * FROM wxpt" + enterId
						+ ".fans WHERE `fans_name`='" + fromUsername
						+ "'  and  `fans_value`='sy'";
				Fans fan = fansService.getFansByNameValue(sqll);
				if (fan != null) {
					if (fan.getFansValue().equals("sy")) {
						String msgType = "text";
						if (keyword.equals("取消")) {
							String sql = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql);
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									"你已取消上传，还想上传照片输入“摄影”即可~~~");
							this.print(resultStr);
						} else if (keyword.equals("sy")) {
							String sql = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql);
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									"请上传参赛图片");
							this.print(resultStr);
						} else {
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									"误操作了？没关系，只需要上传参赛照片就可以了哦~~~");
							this.print(resultStr);
						}
					} else {
						/*
						 * this.reply(fromUsername, toUsername, time, 0,
						 * textTpl, music, textTpl3, textTpl1);
						 */
						this.reply(fromUsername, toUsername, time, 1);
					}
				}
				// keyword="济南";
				String sqld = "SELECT * FROM wxpt" + enterId
						+ ".fans WHERE `fans_name`='" + fromUsername
						+ "'  and  `fans_value`='daohang'";
				fan = fansService.getFansByNameValue(sqld);
				if (fan != null) {
					String sql = "update wxpt" + enterId
							+ ".fans set `fans_value`='xy:" + keyword
							+ "' where `fans_id`=" + fan.getFansId();
					fansService.updateFans(enterId, sql);
					String contentStr = "输入起始、目的地~~~~";
					String msgType = "text";
					String resultStr = textTpl.format(textTpl, fromUsername,
							toUsername, time, msgType, contentStr);
					this.print(resultStr);
				}
				// 公交
				String sqlg = "SELECT * FROM wxpt" + enterId
						+ ".fans WHERE `fans_name`='" + fromUsername
						+ "'  and  `fans_value`='gongjiao'";
				fan = fansService.getFansByNameValue(sqlg);
				if (fan != null) {
					String sql = "update wxpt" + enterId
							+ ".fans set `fans_value`='gongjiao:" + keyword
							+ "' where `fans_id`=" + fan.getFansId();
					fansService.updateFans(enterId, sql);
					String contentStr = "输入起始、目的地~~~~";
					String msgType = "text";
					String resultStr = textTpl.format(textTpl, fromUsername,
							toUsername, time, msgType, contentStr);
					this.print(resultStr);
				}
				String sqls = "SELECT * FROM wxpt" + enterId
						+ ".fans WHERE `fans_name`='" + fromUsername + "'";
				fan = fansService.getFansByNameValue(sqls);
				if (fan != null && fan.getFansValue().contains("xy:")) {
					if (keyword.equals("取消")) {
						String msgType = "text";
						String sql = "DELETE FROM wxpt" + enterId
								+ ".fans WHERE `fans_id`=" + fan.getFansId();
						fansService.deleFans(sql);
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								"你已取消导航查询，还想查询输入“导航”即可~~~");
						this.print(resultStr);
					} else {
						Httpclient httpclient = new Httpclient();
						String center = fan.getFansValue();
						String[] zhu = center.split(":");
						String zhuxy = httpclient.getZuoBiao(zhu[1]);
						String[] key = keyword.split(" ");
						String starxy = httpclient.getZuoBiao(key[0]);
						String endxy = httpclient.getZuoBiao(key[1]);
						String[] star = starxy.split(";");
						String[] end = starxy.split(";");
						if (star[0] == "" || star[0] == "") {
							String sql2 = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql2);
							String contentStr = "不好意思，输入的起始地点不存在~~~，如果继续导航，请重新输入“导航”~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else if (end[0] == "" || end[1] == "") {
							String sql2 = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql2);
							String contentStr = "不好意思，输入的终点不存在~~~如果继续导航，请重新输入“导航”~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else {
							String url = "http://www.uniqyw.com/wxpt/site/manage-location!daohang?starxy="
									+ starxy
									+ "&endxy="
									+ endxy
									+ "&zhuxy="
									+ zhuxy
									+ "&starName="
									+ key[0]
									+ "&endName=" + key[1] + "";
							String msgType1 = "news";
							String resultStr1 = textTpl1.format(
									textTpl1,
									fromUsername,
									toUsername,
									TimeUtil.getTime(),
									msgType1,
									1,
									keyword,
									"驾车导航~~~继续，则输入起始、目的地，反之，输入“取消”~~~",
									"http://api.map.baidu.com/staticimage?center="
											+ zhuxy.split(";")[1] + ","
											+ zhuxy.split(";")[0]
											+ "&width=300&height=200&zoom=13",
									url);
							this.print(resultStr1);

						}
					}
				} else if (fan != null
						&& fan.getFansValue().contains("jiansuo")) {
					String sql = "update wxpt" + enterId
							+ ".fans set `fans_value`='jiansuo:" + keyword
							+ "' where `fans_id`=" + fan.getFansId();
					fansService.updateFans(enterId, sql);
					String contentStr3 = "请发送你的位置，点击左下方\"+\"，点击\"位置\"";
					String msgType3 = "text";
					String resultStr = (ReplyStr.getTextTpl()).format(
							ReplyStr.getTextTpl(), fromUsername, toUsername,
							time, msgType3, contentStr3);
					this.print(resultStr);
				} else if (fan != null && fan.getFansValue().contains("suo")) {
					if (keyword.equals("取消")) {
						String msgType = "text";
						String sql = "DELETE FROM wxpt" + enterId
								+ ".fans WHERE `fans_id`=" + fan.getFansId();
						fansService.deleFans(sql);
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								"你已取消周边检索，还想检索输入“检索”即可~~~");
						this.print(resultStr);
					} else {
						// 0 suo 1 jinan 2 x 3 y
						String url = "www.uniqyw.com/wxpt/site/manage-search!getSearch?city="
								+ fan.getFansValue().split(":")[1]
								+ "&x="
								+ fan.getFansValue().split(":")[3]
								+ "&y="
								+ fan.getFansValue().split(":")[5]
								+ "&matter="
								+ keyword + "";
						// www.uniqyw.com/wxpt/site
						String msgType1 = "news";
						String resultStr1 = textTpl1.format(
								textTpl1,
								fromUsername,
								toUsername,
								TimeUtil.getTime(),
								msgType1,
								1,
								keyword,
								"周边" + keyword + "信息，如继续查询，直接输入查询内容，反之输入“取消”",
								"http://api.map.baidu.com/staticimage?center="
										+ fan.getFansValue().split(":")[5]
												.trim()
										+ ","
										+ fan.getFansValue().split(":")[3]
												.trim()
										+ "&width=300&height=200&zoom=13", url);
						this.print(resultStr1);
					}
				} else if (fan != null && fan.getFansValue().contains("gongjiao:")) {
					if (keyword.equals("取消")) {
						String msgType = "text";
						String sql = "DELETE FROM wxpt" + enterId
								+ ".fans WHERE `fans_id`=" + fan.getFansId();
						fansService.deleFans(sql);
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								"你已取消公交查询，还想查询输入“导航”即可~~~");
						this.print(resultStr);
					} else if(keyword.indexOf(" ")==-1){
						Httpclient httpclient = new Httpclient();
						String center = fan.getFansValue();
						String[] zhu = center.split(":");
						String zhuxy = httpclient.getZuoBiao(zhu[1]);
						String url = "http://www.uniqyw.com/wxpt/site/manage-gong!xian?zhuxy="+zhuxy+"&city="+zhu[1]+"&che="+keyword+"";
								String msgType1 = "news";
						String resultStr1 = textTpl1.format(
								textTpl1,
								fromUsername,
								toUsername,
								TimeUtil.getTime(),
								msgType1,
								1,
								keyword+"路公交",
								"公交查询~~~继续，则输入xx公交，反之，输入“取消”~~~",
								/*"http://api.map.baidu.com/staticimage?center="
										+ zhuxy.split(";")[1] + ","
										+ zhuxy.split(";")[0]
										+ "&width=300&height=200&zoom=13",*/
								"http://www.uniqyw.com/wxpt/web/images/gongjiao.jpg",
								url);
						this.print(resultStr1);
					}else {
						Httpclient httpclient = new Httpclient();
						String center = fan.getFansValue();
						String[] zhu = center.split(":");
						String zhuxy = httpclient.getZuoBiao(zhu[1]);
						String[] key = keyword.split(" ");
						String starxy = httpclient.getZuoBiao(key[0]);
						String endxy = httpclient.getZuoBiao(key[1]);
						String[] star = starxy.split(";");
						String[] end = starxy.split(";");
						if (star[0] == "" || star[0] == "") {
							String sql2 = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql2);
							String contentStr = "不好意思，输入的起始地点不存在~~~，如果继续公交查询，请重新输入“公交”~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else if (end[0] == "" || end[1] == "") {
							String sql2 = "DELETE FROM wxpt" + enterId
									+ ".fans WHERE `fans_id`="
									+ fan.getFansId();
							fansService.deleFans(sql2);
							String contentStr = "不好意思，输入的终点不存在~~~如果继续公交查询，请重新输入“公交”~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else {
							String url = "http://www.uniqyw.com/wxpt/site/manage-gong!gong?starxy="
									+ starxy
									+ "&endxy="
									+ endxy
									+ "&zhuxy="
									+ zhuxy
									+ "&starName="
									+ key[0]
									+ "&endName=" + key[1] + "&city="+zhu[1]+"";
							String msgType1 = "news";
							String resultStr1 = textTpl1.format(
									textTpl1,
									fromUsername,
									toUsername,
									TimeUtil.getTime(),
									msgType1,
									1,
									keyword,
									"公交换乘~~~继续，则输入起始、目的地，反之，输入“取消”~~~",
									/*"http://api.map.baidu.com/staticimage?center="
											+ zhuxy.split(";")[1] + ","
											+ zhuxy.split(";")[0]
											+ "&width=300&height=200&zoom=13",*/
									"http://www.uniqyw.com/wxpt/web/images/gongjiao.jpg",
									url);
							this.print(resultStr1);

						}
					}

				}

				if (null != keyword && !keyword.equals("")) {
					// 调研开始

					if (keyword.equals("survey")) {
						boolean f = pidui(206, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("调研活动", enterId)
									.get(0);
						} catch (Exception e) {
							// TODO: handle exception
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

						if (move.getMoveState() == 1) {

							String contentStr = "很抱歉，活动已经结束~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						} else {
							Survey sur2 = surveyService.findByFromUsername(
									fromUsername, enterId);
							if (sur2 != null) {
								String contentStr = "谢谢您的支持，您已经参与过问卷调查了";
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, contentStr);
								this.print(resultStr);
							} else {
								String title = "";
								String imageUrl = "";
								String ecoution = "";
								Keywords kwords = userService
										.getKeyBysendContent(enterId, keyword);

								if (kwords != null) {
									List<Keywordexplicit> w = userService
											.getkExplicitByEkey(enterId,
													kwords.getKeywordId(), 0);
									if (w.size() != 0) {
										Keywordexplicit wp = w.get(0);
										title = wp.getTitle();
										imageUrl = wp.getEcontent();
										ecoution = wp.getEinstruction();
									}
									String msgType1 = "news";
									String url1 = "www.uniqyw.com/wxpt/site/web/survey?fromUsername="
											+ fromUsername
											+ "&enterId="
											+ enterId;
									String resultStr1 = textTpl1.format(
											textTpl1, fromUsername, toUsername,
											TimeUtil.getTime(), msgType1, 1,
											title, ecoution,
											"http://www.uniqyw.com/wxpt/web/images/"
													+ enterId + "/" + imageUrl,
											url1);
									this.print(resultStr1);
								}
							}

						}

					}
					// 调研结束
					// 实用功能
					if (keyword.contains("天气")) {
						if(Integer.parseInt(shiyongList.get(0).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						String[] key = keyword.split("天气");
						Httpclient httpclient = new Httpclient();
						String url = "http://api.map.baidu.com/telematics/v3/weather?location="
								+ keyword.replace("天气", "")
								+ "&output=html&ak=ECe2b50ae093fe82dff717edec0e41e3";
						// String
						// url="http://api.map.baidu.com/telematics/v3/weather?location=济南&output=html&ak=ECe2b50ae093fe82dff717edec0e41e3";
						Weather weather = new Weather();
						List<Weather> list = new ArrayList<Weather>();
						list = httpclient.getTianqi(url);
						String d = "<Articles>";
						for (int i = 0; i < list.size(); i++) {
							d += "<item>" + "<Title><![CDATA["
									+ list.get(i).getDaty()
									+ list.get(i).getYubao()
									+ list.get(i).getFeng()
									+ list.get(i).getWendu() + "]]></Title>"
									+ "<Description><![CDATA[]]></Description>"
									+ "<PicUrl><![CDATA["
									+ list.get(i).getImage() + "]]></PicUrl>"
									+ "<Url><![CDATA[]]></Url>" + "</item>";
						}

						d += "</Articles>";
						textTpl3Content = d;
						String msgType = "news";
						String resultStr = textTpl3.format(textTpl3,
								fromUsername, toUsername,
								TimeUtil.getImagesTime(), msgType, list.size(),
								textTpl3Content);
						this.print(resultStr);
					}
				}
					// 空气
					if (keyword.contains("空气")) {
						if(Integer.parseInt(shiyongList.get(1).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						Httpclient httpclient = new Httpclient();
						String[] key = keyword.split("空气");
						String url = "http://www.pm25.in/api/querys/pm2_5.json?city="
								+ keyword.replace("空气", "")
								+ "&token=1jUGds4SJNpCY1XNWZ83&stations=no";
						String[] list = httpclient.pm(url);
						String pm = "";
						for (int i = 0; i < list.length; i++) {
							pm += list[i] + "\n";
						}
						String contentStr = pm;
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
					}}
					// 身体健康
					if (keyword.contains("身高") || keyword.contains("体重")) {
						if(Integer.parseInt(shiyongList.get(11).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						Httpclient httpclient = new Httpclient();
						String[] num = keyword.split(" ");
						String regEx = "[^0-9]";
						Pattern p = Pattern.compile(regEx);
						Matcher m = p.matcher(num[0]);
						Matcher m1 = p.matcher(num[1]);
						Double s = httpclient.getShen(m.replaceAll("").trim(),
								m1.replaceAll("").trim());
						String health = "";
						if (s < 18.8) {
							health = "你是火柴吗~~~~";
						}
						if (18.8 <= s && s < 24.0) {
							health = "哇，有些骨感，您是不是女孩子，小心很多帅哥会爱上你哦~~~";
						}
						if (24.0 <= s && s < 27.0) {
							health = "您已经发福了~~~";
						}
						if (27.0 <= s && s < 30.0) {
							health = "您有点过于胖了~~~";
						}
						if (30.5 <= s && s < 35) {
							health = "您太胖了~~~";
						}
						if (s >= 35) {
							health = "您太太太太...胖了，您已经得病了，去检查吧！";
						}
						String contentStr = health;
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
					}}
					// 成语接龙
					String cy = "SELECT * FROM webchat.idiom WHERE `idiom_name`='"
							+ keyword + "'";
					boolean cyjl = false;
					cyjl = questionDao.getidiom(cy);
					if (cyjl) {
						if(Integer.parseInt(shiyongList.get(8).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						List<Idiom> idiomList = new ArrayList<Idiom>();
						String key = keyword.substring(keyword.length() - 1);
						String cy2 = "SELECT * FROM webchat.idiom WHERE left(`idiom_name`,1)='"
								+ key + "'";
						idiomList = questionDao.getIdiom(cy2);
						if (idiomList.size() != 0) {
							Random random = new Random();
							Idiom idiom = idiomList.get(random
									.nextInt(idiomList.size()));
							String contentStr = idiom.getIdiomName();
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else {
							String contentStr = "太厉害了~~系统答不上~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

					}}
					// 成语释义
					if (keyword.contains("释义")) {
						if(Integer.parseInt(shiyongList.get(9).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						List<Idiom> idiomList = new ArrayList<Idiom>();
						String[] key = keyword.split("释义");
						String cy3 = "SELECT * FROM webchat.idiom WHERE `idiom_name`='"
								+ keyword.replace("释义", "") + "'";
						boolean cyjl1 = questionService.getidiom(cy3);
						if (cyjl1) {
							idiomList = questionDao.getIdiom(cy3);
							String contentStr = idiomList.get(0)
									.getParaphrase().trim();
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else {
							String contentStr = "没有查到它的意思~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

					}}
					// keyword = "北京到济南2013-12-20";
					// 余票查询
					if (keyword.contains("到")) {
						if(Integer.parseInt(shiyongList.get(4).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						TrainNumber trainNumber = new TrainNumber();
						TrainNumber trainNumber2 = new TrainNumber();
						String[] key = keyword.split("到");
						String regEx = "[^0-9]";
						Pattern p = Pattern.compile(regEx);
						Matcher m = p.matcher(keyword);
						String no = m.replaceAll("").trim();
						StringBuffer str2 = new StringBuffer(no);
						String strInsert2 = "-";
						str2.insert(4, strInsert2);
						str2.insert(7, strInsert2);
						String sql;
						sql = "SELECT * FROM webchat.train_number WHERE `train_number_name`='"
								+ key[0] + "'";
						try {
							trainNumber = questionService.getTrain(sql);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							String contentStr = "格式或地点有误~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						sql = "SELECT * FROM webchat.train_number WHERE `train_number_name`='"
								+ key[1].replace(str2, "") + "'";
						try {
							trainNumber2 = questionService.getTrain(sql);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							String contentStr = "格式或地点有误有误~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						String url = "www.uniqyw.com/wxpt/site/manage-yu-piao!getYuPiao?chufa="
								+ trainNumber.getTrainNumberDai()
								+ "&daoda="
								+ trainNumber2.getTrainNumberDai()
								+ "&time="
								+ str2 + "";
						String msgType1 = "news";
						String resultStr1 = textTpl1
								.format(textTpl1,
										fromUsername,
										toUsername,
										TimeUtil.getTime(),
										msgType1,
										1,
										keyword,
										"最新余票查询",
										"http://www.uniqyw.com/wxpt/web/images/12306.jpg",
										url);
						this.print(resultStr1);
					}}
					// 驾车导航
					// keyword="导航";
					if (keyword.contains("导航")) {
						if(Integer.parseInt(shiyongList.get(5).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						String sql = "INSERT INTO wxpt" + enterId
								+ ".fans( `fans_name`, `fans_value`) VALUES ('"
								+ fromUsername + "','daohang')";
						fansService.addFans(sql);
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								"请输当前城市~~~");
						this.print(resultStr);
						// String [] key=keyword.split("到");
						// Httpclient httpclient = new Httpclient();
						// String x=httpclient.getZuoBiao(key[0].replace("驾车从",
						// ""));
						// String y=httpclient.getZuoBiao(key[1]);
					}}
					// 检索
					// keyword="检索";
					if (keyword.contains("检索")) {
						if(Integer.parseInt(shiyongList.get(6).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						String sql = "INSERT INTO wxpt" + enterId
								+ ".fans( `fans_name`, `fans_value`) VALUES ('"
								+ fromUsername + "','jiansuo')";
						fansService.addFans(sql);
						// String contentStr3 = "请发送你的位置，点击左下方\"+\"，点击\"位置\"";
						String contentStr3 = "输入您当前城市~~~";
						String msgType3 = "text";
						String resultStr = (ReplyStr.getTextTpl()).format(
								ReplyStr.getTextTpl(), fromUsername,
								toUsername, time, msgType3, contentStr3);
						this.print(resultStr);
					}}
					if (keyword.contains("快递")) {
						if(Integer.parseInt(shiyongList.get(3).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						String key = keyword.replace("快递", "");
						Httpclient httpclient = new Httpclient();
						String contentStr = httpclient.getKuaiDi(key);
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
					}}
					if (keyword.contains("公交")) {
						if(Integer.parseInt(shiyongList.get(7).getShiyongValue())==0){
							String contentStr = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}else{
						String sql = "INSERT INTO wxpt" + enterId
								+ ".fans( `fans_name`, `fans_value`) VALUES ('"
								+ fromUsername + "','gongjiao')";
						fansService.addFans(sql);
						// String contentStr3 = "请发送你的位置，点击左下方\"+\"，点击\"位置\"";
						String contentStr3 = "输入您当前城市~~~";
						String msgType3 = "text";
						String resultStr = (ReplyStr.getTextTpl()).format(
								ReplyStr.getTextTpl(), fromUsername,
								toUsername, time, msgType3, contentStr3);
						this.print(resultStr);
					}}
					// if (keyword.contains("顺丰")) {
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=sf&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					// }
					// if (keyword.contains("申通")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=sto&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }
					// if (keyword.contains("圆通")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=yt&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }
					// if (keyword.contains("韵达")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=yd&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }
					// if (keyword.contains("天天")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=tt&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }
					// if (keyword.contains("EMS") || keyword.contains("ems")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=ems&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }
					// if (keyword.contains("汇通")) {
					//
					// Httpclient httpclient = new Httpclient();
					// String regEx = "[^0-9]";
					// Pattern p = Pattern.compile(regEx);
					// Matcher m = p.matcher(keyword);
					// String no = m.replaceAll("").trim();
					// String url =
					// "http://v.juhe.cn/exp/index?key=b15ada817c8b8a10b7c46d0bd0ce95b5&com=ht&no="
					// + no + "";
					// String[] list = httpclient.kuaidi(url);
					// String kuaidi = "";
					// for (int i = 2; i < list.length - 1; i++) {
					// kuaidi += list[i] + "\n";
					// }
					// String contentStr = kuaidi;
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					//
					// }

					if (keyword.equals("摄影")) {
						boolean f = pidui(203, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("摄影大赛", enterId)
									.get(0);
						} catch (Exception e) {
							// TODO: handle exception
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

						if (move.getMoveState() == 1) {

							String contentStr = "很抱歉，活动已经结束~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						} else {
							String sql = "INSERT INTO wxpt"
									+ enterId
									+ ".fans( `fans_name`, `fans_value`) VALUES ('"
									+ fromUsername + "','sy')";
							fansService.addFans(sql);
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									"请上传参赛图片~~!");
							this.print(resultStr);
						}

					}
					if (keyword.equals("微摄影")) {
						boolean f = pidui(203, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						String title = "";
						String imageUrl = "";
						String ecoution = "";
						Keywords kwords = userService.getKeyBysendContent(
								enterId, keyword);

						if (kwords != null) {
							List<Keywordexplicit> w = userService
									.getkExplicitByEkey(enterId,
											kwords.getKeywordId(), 0);
							if (w.size() != 0) {
								Keywordexplicit wp = w.get(0);
								title = wp.getTitle();
								imageUrl = wp.getEcontent();
								ecoution = wp.getEinstruction();
							}
							String msgType1 = "news";
							String url1 = "www.uniqyw.com/wxpt/site/index!getFansAndFansImage?fansName="
									+ fromUsername + "&enterId2=" + enterId;
							String resultStr1 = textTpl1.format(textTpl1,
									fromUsername, toUsername,
									TimeUtil.getTime(), msgType1, 1, title,
									ecoution,
									"http://www.uniqyw.com/wxpt/web/images/"
											+ enterId + "/" + imageUrl, url1);
							this.print(resultStr1);
						}
					}
					String hql3 = "SELECT * FROM wxpt"
							+ enterId
							+ ".customers WHERE 1=1 and `location_x`!=''and `location_y`!='' ";
					String contentStr3 = "";
					String areaStr3 = "";
					String industryStr3 = "";
					String msgType3 = "text";
					Keywords kwords2 = userService.getKeyBysendContent(enterId,
							keyword);
					// String
					// sql01="SELECT * FROM `fans` WHERE `fans_name`='"+fromUsername+"'  and  `fans_value`='sy'";
					Fans fans = fansService
							.getFansByName(enterId, fromUsername);
					if (keyword.equals("dtsave")) {
						if (fans == null) {
							String sql = "INSERT INTO wxpt"
									+ enterId
									+ ".fans( `fans_name`, `fans_value`) VALUES ('"
									+ fromUsername + "','dtsave')";
							fansService.addFans(sql);
						}
						contentStr3 = "请输入客户编号";
						msgType3 = "text";
						String resultStr = (ReplyStr.getTextTpl()).format(
								ReplyStr.getTextTpl(), fromUsername,
								toUsername, time, msgType3, contentStr3);
						this.print(resultStr);
					} else {
						if (fans == null) {
							// fans = new fans();

							if (keyword.equals("dt21")) {
								int j = 0;
								String markers = "";
								String markerStyles = "";
								List<Customers> customersList = customersService
										.getCustomersListByXY(enterId);
								String ids = "";
								for (int i = 0; i < customersList.size(); i++) {
									Customers customers = customersList.get(i);

									if ((i + 1) == customersList.size()) {
										ids += customers.getCustomersId();
									} else {
										ids += customers.getCustomersId() + ",";
									}
									if ((i + 1) == customersList.size()
											|| i == 90) {
										markers += customers.getLocationY()
												+ ","
												+ customers.getLocationX();
										break;
									} else {
										markers += customers.getLocationY()
												+ ","
												+ customers.getLocationX()
												+ ReplyStr.getStr("|");
									}

									if ((i + 1) == customersList.size()) {
										markerStyles += "l" + "," + j;
									} else {
										markerStyles += "l" + "," + j
												+ ReplyStr.getStr("|");
									}
									j++;

								}
								String cityName = radiusService
										.getRadiusList(enterId).get(0)
										.getCenterName();
								try {
									cityName = ReplyStr.getStr(cityName);
								} catch (Exception e) {
								}
								// http://www.uniqyw.com/wxpt/site/web/web-weixin!getAllImg
								msgType3 = "news";
								String resultStr = (ReplyStr.getImgTpl())
										.format(ReplyStr.getImgTpl(),
												fromUsername,
												toUsername,
												time,
												msgType3,
												1,
												"附近所有客户",
												"共有" + customersList.size()
														+ "客户",
												"http://api.map.baidu.com/staticimage?center="
														+ cityName
														+ "&width=640&height=320&zoom=10&markers="
														+ markers
												/*
												 * + "&markerStyles=" +
												 * markerStyles
												 */
												// http://api.map.baidu.com/staticimage?center=济南&width=640&height=320&zoom=10&markers=34.260831108.930606
												,
												"http://www.uniqyw.com/wxpt/site/web/web-weixin!getList?enterId="
														+ enterId);
								this.print(resultStr);
							} else if (keyword.equals("dt22")) {
								List<Area> areaList = areaService
										.getAreaList(enterId);
								for (Area area : areaList) {
									areaStr3 += area.getAreaNo() + "."
											+ area.getAreaName() + "\n";
								}
								contentStr3 = areaStr3;
								String sql = "INSERT INTO wxpt"
										+ enterId
										+ ".fans( `fans_name`, `fans_value`) VALUES ('"
										+ fromUsername + "','dtqy')";
								fansService.addFans(sql);

								msgType3 = "text";
								String resultStr = (ReplyStr.getTextTpl())
										.format(ReplyStr.getTextTpl(),
												fromUsername, toUsername, time,
												msgType3, contentStr3);
								this.print(resultStr);
							} else if (keyword.equals("dt23")) {
								List<Industry> industryList = industryService
										.getIndustryList(enterId);
								for (Industry industry : industryList) {
									industryStr3 += industry.getIndustryNo()
											+ "." + industry.getIndustryName()
											+ "\n";
								}
								contentStr3 = industryStr3;
								String sql = "INSERT INTO wxpt"
										+ enterId
										+ ".fans( `fans_name`, `fans_value`) VALUES ('"
										+ fromUsername + "','dthy')";
								fansService.addFans(sql);
								msgType3 = "text";
								String resultStr = (ReplyStr.getTextTpl())
										.format(ReplyStr.getTextTpl(),
												fromUsername, toUsername, time,
												msgType3, contentStr3);
								this.print(resultStr);
							} else if (keyword.equals("dt24")) {
								String sql = "INSERT INTO wxpt"
										+ enterId
										+ ".fans( `fans_name`, `fans_value`) VALUES ('"
										+ fromUsername + "','radius')";
								fansService.addFans(sql);
								contentStr3 = "请输入你要检索的范围（单位:公里）";
								msgType3 = "text";
								String resultStr = (ReplyStr.getTextTpl())
										.format(ReplyStr.getTextTpl(),
												fromUsername, toUsername, time,
												msgType3, contentStr3);
								this.print(resultStr);
							}

						} else {
							String title = "";
							int count = 0;
							String url = "";
							if (fans.getFansValue().contains("dtsave")) {

								Customers customers = customersService
										.getCustomersByNo(enterId, keyword);
								if (customers == null) {
									fansService.deleteFans(enterId, fans);
									contentStr3 = "改客户不存在，如需继续保存，请重新输入dtsave：";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								} else {
									fans.setFansValue(fans.getFansValue() + ":"
											+ keyword);
									fansDao.updateFans(enterId, fans);
									contentStr3 = "请发送你的位置，点击左下方\"+\"，点击\"位置\"";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								}

							} else if (fans.getFansValue().contains("dtqy")) {

								Area area = areaService.getAreaByNo(enterId,
										keyword);
								if (area == null) {
									fansService.deleteFans(enterId, fans);
									contentStr3 = "该区域暂时没有客户资料";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								} else {
									hql3 += " and area_id = "
											+ area.getAreaId();
									count = customersService.getCustomersCount(
											enterId, hql3);
									if (count == 0) {
										fansService.deleteFans(enterId, fans);
										contentStr3 = "该区域暂时没有客户资料";
										String resultStr = (ReplyStr
												.getTextTpl()).format(
												ReplyStr.getTextTpl(),
												fromUsername, toUsername, time,
												msgType3, contentStr3);
										this.print(resultStr);
									} else {
										fansService.deleteFans(enterId, fans);
										List<Customers> customersList = customersService
												.getCustomersList(enterId, hql3);
										String ids = "";
										String markers = "";
										String markerStyles = "";
										int j = 0;
										for (int i = 0; i < customersList
												.size(); i++) {
											Customers customers = customersList
													.get(i);

											if ((i + 1) == customersList.size()) {
												ids += customers
														.getCustomersId();
											} else {
												ids += customers
														.getCustomersId() + ",";
											}
											if ((i + 1) == customersList.size()
													|| i == 90) {
												markers += customers
														.getLocationY()
														+ ","
														+ customers
																.getLocationX();

											} else {
												markers += customers
														.getLocationY()
														+ ","
														+ customers
																.getLocationX()
														+ ReplyStr.getStr("|");
											}
											if ((i + 1) == customersList.size()) {
												markerStyles += "l" + "," + j;
												break;
											} else {
												markerStyles += "l" + "," + j
														+ ReplyStr.getStr("|");
											}
											j++;
										}
										title = area.getAreaName() + "地方所有客户资料";
										// String s =
										// "http://www.uniqyw.com/wxpt/site/web/web-weixin!execute?areaId=6&userXY=117.134900,36.679020&type=1";
										url = "http://www.uniqyw.com/wxpt/site/web/web-weixin!execute?areaId="
												+ area.getAreaId()
												// + "&markers="
												// + markers
												+ "&userXY="
												+ area.getLocationY()
												+ ","
												+ area.getLocationX()
												+ "&type=1";
										msgType3 = "news";
										String resultStr = (ReplyStr
												.getImgTpl())
												.format(ReplyStr.getImgTpl(),
														fromUsername,
														toUsername,
														time,
														msgType3,
														1,
														title,
														"共有"
																+ customersList
																		.size()
																+ "客户 ",
														"http://api.map.baidu.com/staticimage?center="
																+ area.getLocationY()
																+ ","
																+ area.getLocationX()
																+ "&width=640&height=320&markers="
																+ markers
																+ "&markerStyles="
																+ markerStyles
																+ "&zoom=15",
														url);
										// String
										// str="http://api.map.baidu.com/staticimage?center=108.947386,34.259294&width=640&height=320&markers=,&markerStyles=|,0&zoom=15";

										this.print(resultStr);
									}
								}

							} else if (fans.getFansValue().contains("dthy")) {

								Industry industry = industryService
										.getIndustryByNo(enterId, keyword);
								if (industry == null) {
									fansService.deleteFans(enterId, fans);
									contentStr3 = "该行业暂时没有客户";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								} else {
									hql3 += " and industry_id = "
											+ industry.getIndustryId();
									count = customersService.getCustomersCount(
											enterId, hql3);
									if (count == 0) {
										fansService.deleteFans(enterId, fans);
										contentStr3 = "该行业暂时没有客户资料";
										msgType3 = "text";
										String resultStr = (ReplyStr
												.getTextTpl()).format(
												ReplyStr.getTextTpl(),
												fromUsername, toUsername, time,
												msgType3, contentStr3);
										this.print(resultStr);
									} else {
										fansService.deleteFans(enterId, fans);
										List<Customers> customersList = customersService
												.getCustomersList(enterId, hql3);
										String ids = "";
										String markers = "";
										String markerStyles = "";
										int j = 0;
										for (int i = 0; i < customersList
												.size(); i++) {
											Customers customers = customersList
													.get(i);

											if ((i + 1) == customersList.size()) {
												ids += customers
														.getCustomersId();
											} else {
												ids += customers
														.getCustomersId() + ",";
											}
											if ((i + 1) == customersList.size()
													|| i == 90) {
												markers += customers
														.getLocationY()
														+ ","
														+ customers
																.getLocationX();
												break;
											} else {
												markers += customers
														.getLocationY()
														+ ","
														+ customers
																.getLocationX()
														+ ReplyStr.getStr("|");
											}
											if ((i + 1) == customersList.size()) {
												markerStyles += "l" + "," + j;
											} else {
												markerStyles += "l" + "," + j
														+ ReplyStr.getStr("|");
											}
											j++;
										}

										title = industry.getIndustryName()
												+ "行业所有客户资料";
										String cityName = radiusService
												.getRadiusList(enterId).get(0)
												.getCenterName();
										try {
											cityName = ReplyStr
													.getStr(cityName);
										} catch (Exception e) {
										}
										url = "http://www.uniqyw.com/wxpt/site/web/web-weixin!execute?"
												+ "industryId="
												+ industry.getIndustryId()
												// + "&ids="
												// + ids
												// + "&markers="
												// + markers
												+ "&type=0";
										msgType3 = "news";
										String resultStr = (ReplyStr
												.getImgTpl())
												.format(ReplyStr.getImgTpl(),
														fromUsername,
														toUsername,
														time,
														msgType3,
														1,
														title,
														"共有"
																+ customersList
																		.size()
																+ "客户",
														// http://api.map.baidu.com/staticimage?center=
														// 济南&width=640&height=320&zoom=10&markers=100&markerStyles=100
														"http://api.map.baidu.com/staticimage?center="
																+ cityName
																+ "&width=640&height=320&zoom=10&markers="
																+ markers
																+ "&markerStyles="
																+ markerStyles,
														url);
										this.print(resultStr);
									}
								}

							} else if (fans.getFansValue().contains("radius")) {
								if (!ReplyStr.isNum(keyword)) {
									contentStr3 = "请输入正确的检索范围";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								} else {
									fans.setFansValue(fans.getFansValue() + ":"
											+ keyword);
									fansDao.updateFans(enterId, fans);
									contentStr3 = "请发送你的位置，点击左下方\"+\"，点击\"位置\"";
									msgType3 = "text";
									String resultStr = (ReplyStr.getTextTpl())
											.format(ReplyStr.getTextTpl(),
													fromUsername, toUsername,
													time, msgType3, contentStr3);
									this.print(resultStr);
								}

							} else {
								this.reply(fromUsername, toUsername, time, 0);
							}
						}
					}

					if (keyword.equalsIgnoreCase("luck")) {
						boolean f = pidui(51, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("答题", enterId)
									.get(0);
						} catch (Exception e) {
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}
						if (move.getMoveState() == 0) {
							String sql = "SELECT * FROM wxpt" + enterId
									+ ".question_tishi where type=1";
							List<QuestionTishi> tishiList = questionDao
									.getAllTishi(sql);
							// move = (Move) moveService.findByMoveName("答题",
							// enterId).get(0);
							/* String days = "25,26,27,28,29,30"; */
							String days = move.getAwardTime();

							if (!days.contains(TimeUtil.getDay())) {
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, "抽奖查询时间为每月" + days + "号");
								this.print(resultStr);
							} else {
								LuckUser u = luckService.getMaxLuckUser(
										enterId, fromUsername);
								Luckanwer la = luckService.getLuckanwer(
										enterId, fromUsername);
								if (u == null && la == null) {
									String msgType = "text";
									String resultStr = textTpl.format(textTpl,
											fromUsername, toUsername, time,
											msgType, tishiList.get(0)
													.getMeiJiang());
									this.print(resultStr);
								}
								if (u != null) {
									try {
										ruleList = questionDao
												.getRuleList(enterId);
									} catch (Exception e) {
										// TODO: handle exception
										String contentStr = "活动正在完善~~~";
										String msgType = "text";
										String resultStr = textTpl.format(
												textTpl, fromUsername,
												toUsername, time, msgType,
												contentStr);
										this.print(resultStr);
									}

									if (u.getState() == 1) {
										String msgType = "text";
										String resultStr = textTpl.format(
												textTpl, fromUsername,
												toUsername, time, msgType,
												tishiList.get(0).getMeiJiang());
										this.print(resultStr);
									}
									if (u.getState() == 0) {
										int id = la.getLuckanswerid();
										int reId = userService.getRecordId(
												enterId, id);
										AnswerRecords answerRecords = luckService
												.byRecordId(enterId, reId);
										type = answerRecords.getType();

										if (type == 0) {
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(0)
																	.getAwards());
											this.print(resultStr);
										}
										if (type == 1) {
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(1)
																	.getAwards());
											this.print(resultStr);
										}
										if (type == 2) {
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(2)
																	.getAwards());
											this.print(resultStr);
										}

									}
								}
								if (la != null) {
									try {
										ruleList = questionDao
												.getRuleList(enterId);
									} catch (Exception e) {
										// TODO: handle exception
										String contentStr = "活动正在完善~~~";
										String msgType = "text";
										String resultStr = textTpl.format(
												textTpl, fromUsername,
												toUsername, time, msgType,
												contentStr);
										this.print(resultStr);
									}
									String msgType = "text";
									String resultStr = "";
									if (la.getAnswerRecords().getType() == 0
											&& la.getState() == 0) {
										resultStr = textTpl.format(textTpl,
												fromUsername, toUsername, time,
												msgType, ruleList.get(0)
														.getAwards());
									}
									if (la.getAnswerRecords().getType() == 1
											&& la.getState() == 0) {
										resultStr = textTpl.format(textTpl,
												fromUsername, toUsername, time,
												msgType, ruleList.get(1)
														.getAwards());
									}
									if (la.getAnswerRecords().getType() == 2
											&& la.getState() == 0) {
										resultStr = textTpl.format(textTpl,
												fromUsername, toUsername, time,
												msgType, ruleList.get(2)
														.getAwards());
									}
									if (la.getState() == 1) {
										resultStr = textTpl.format(textTpl,
												fromUsername, toUsername, time,
												msgType, tishiList.get(0)
														.getChoujiang());
									}
									this.print(resultStr);
								}
							}
						} else {
							String contentStr = "很抱歉，活动已经结束";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

					}
					if (keyword.equalsIgnoreCase("dt")
							|| questionService.getMaxAnswer(enterId,
									fromUsername, 0) != null) {
						boolean f = pidui(51, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("答题", enterId)
									.get(0);
						} catch (Exception e) {
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}
						if (move.getMoveState() == 0) {
							int typeNum = 0;
							try {
								ruleList = questionDao.getRuleList(enterId);
								String sql = "SELECT * FROM wxpt" + enterId
										+ ".question_tishi where type=1";
								tishiList = questionDao.getAllTishi(sql);
							} catch (Exception e) {
								String contentStr = "活动正在完善~~~";
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, contentStr);
								this.print(resultStr);
							}

							List<Answer> answerList = questionDao.getDayAnswer(
									enterId, fromUsername);
							Answer an = questionDao.getMaxAnswer(enterId,
									fromUsername, 0);
							if (answerList.size() == 0) {
								// 简单
								typeNum = 0;
								QuestionType questionType = questionDao
										.getTypeByNum(enterId, typeNum);
								List<Question> questList = questionDao
										.getQuestionList(enterId,
												questionType.getTypeId());
								Random random = new Random();
								Question question = questList.get(random
										.nextInt(questList.size()));
								MaxId = questionService.getMaxId(sql0)
										.getAnswerid();
								questionDao.addAnswer(MaxId, enterId, 0,
										fromUsername, TimeUtil.getTime(),
										question.getQuestionid());

								if (question.getType() == 0) {
									String msgType = "music";
									String resultStr = music
											.format(music,
													fromUsername,
													toUsername,
													TimeUtil.getImagesTime(),
													msgType,
													question.getQuestionTitle(),
													"\nA" + question.getAnswerA()
															+ "\nB "+ question.getAnswerA()
															,
													"http://www.uniqyw.com/wxpt/web/images/"
															+ enterId
															+ "/"
															+ question
																	.getQustionContent(),
													"http://www.uniqyw.com/wxpt/web/images/"
															+ enterId
															+ "/"
															+ question
																	.getQustionContent());
									this.print(resultStr);
								}
								if (question.getType() == 1) {
									String msgType = "text";
									String resultStr = textTpl.format(
											textTpl,
											fromUsername,
											toUsername,
											time,
											msgType,
											question.getQustionContent()
													+ "\nA."
													+ question.getAnswerA()
													+ "\nB."
													+ question.getAnswerB()
									/*
									 * + "\nC." + question.getAnswerC() + "\nD."
									 * + question.getAnswerD()
									 */
									);
									this.print(resultStr);
								}
								if (question.getType() == 2) {
									String msgType = "news";

									String resultStr = textTplpic
											.format(textTplpic,
													fromUsername,
													toUsername,
													TimeUtil.getTime(),
													msgType,
													1,
													question.getQuestionTitle(),
													"\nA."
															+ question
																	.getAnswerA()
															+ "B."
															+ question
																	.getAnswerB()
															+ "\nC."
															+ question
																	.getAnswerC()
															+ "D."
															+ question
																	.getAnswerD(),
													"http://www.uniqyw.com/wxpt/web/images/"
															+ enterId
															+ "/"
															+ question
																	.getQustionContent(),
													"http://www.uniqyw.com/wxpt/web/images/"
															+ enterId
															+ "/"
															+ question
																	.getQustionContent());

									this.print(resultStr);
								}
							} else if ((keyword.equalsIgnoreCase("jx") && an
									.getSendAnswer() != null)
									|| (keyword.equalsIgnoreCase("fq") && an
											.getSendAnswer() != null)) {

								if (keyword.equalsIgnoreCase("jx")) {
									String questionIDs = "";
									if (answerList.size() >= ruleList.get(0)
											.getQuestionNum()
											&& answerList.size() < ruleList
													.get(1).getQuestionNum()) {
										typeNum = 1;

									}
									if (answerList.size() >= ruleList.get(1)
											.getQuestionNum()) {
										typeNum = 2;
									}

									for (int i = 0; i < answerList.size(); i++) {
										if ((i + 1) == answerList.size()) {
											int answerIdss = answerList.get(i)
													.getAnswerid();
											questionIDs += questionDao
													.getQuestionId(enterId,
															answerIdss);
										} else {
											int answerIds = answerList.get(i)
													.getAnswerid();
											questionIDs += questionDao
													.getQuestionId(enterId,
															answerIds)
													+ ",";
										}

									}

									QuestionType questionType = questionService
											.getTypeByNum(enterId, typeNum);
									List<Question> qList = questionService
											.getQtByIds(enterId, questionIDs,
													questionType.getTypeId());

									Random random = new Random();

									Question q = qList.get(random.nextInt(qList
											.size()));
									MaxId = questionService.getMaxId(sql0)
											.getAnswerid();
									questionDao.addAnswer(MaxId, enterId, 0,
											fromUsername, TimeUtil.getTime(),
											q.getQuestionid());

									if (q.getType() == 0) {
										String msgType = "music";
										String resultStr = "";
										if (typeNum == 2) {
											resultStr = music
													.format(music,
															fromUsername,
															toUsername,
															TimeUtil.getImagesTime(),
															msgType,
															q.getQuestionTitle(),
															"\nA:"
																	+ q.getAnswerA()
																	+ "\nB:"
																	+ q.getAnswerB()
																	+ "\nC:"
																	+ q.getAnswerC(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent());

										} else {
											/*resultStr = music
													.format(music,
															fromUsername,
															toUsername,
															TimeUtil.getImagesTime(),
															msgType,
															q.getQuestionTitle(),
															"A"
																	+ q.getAnswerA()
																	+ "\nB"
																	+ q.getAnswerB(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent());
											*/
											

											resultStr = music
													.format(music,
															fromUsername,
															toUsername,
															TimeUtil.getImagesTime(),
															msgType,
															q.getQuestionTitle(),
															"A:"
																	+ q.getAnswerA()
																	+ "\nB:"
																	+ q.getAnswerB(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent(),
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ q.getQustionContent());
										}
										this.print(resultStr);
									}
									if (q.getType() == 1) {
										String msgType = "text";
										String resultStr = "";
										if (typeNum == 2) {
											resultStr = textTpl.format(
													textTpl,
													fromUsername,
													toUsername,
													time,
													msgType,
													q.getQustionContent()
															+ "\nA."
															+ q.getAnswerA()
															+ "\nB."
															+ q.getAnswerB()
															+ "\nC."
															+ q.getAnswerC()
															+ "\nD."
															+ q.getAnswerD());
										} else {
											resultStr = textTpl.format(
													textTpl,
													fromUsername,
													toUsername,
													time,
													msgType,
													q.getQustionContent()
															+ "\nA."
															+ q.getAnswerA()
															+ "\nB."
															+ q.getAnswerB()
															+ "\nC."
															+ q.getAnswerC()
											// + "\nD." + q.getAnswerD()
													);
										}

										this.print(resultStr
												+ answerList.size());
									}

								}
								if (keyword.equalsIgnoreCase("fq")) {

									String message = "";
									List<Answer> anList = questionDao
											.getAnAnswerList(enterId,
													fromUsername);
									if (anList.size() == ruleList.get(2)
											.getQuestionNum()) {
										message = ruleList.get(2).getFangqi();
										type = 2;
										MaxId = luckService.getMaxId(sqlLuch)
												.getRecordId();
										luckDao.addAnswerRecords(MaxId,
												enterId, TimeUtil.getTime(),
												fromUsername, type);
									}
									if (anList.size() == ruleList.get(1)
											.getQuestionNum()) {
										message = ruleList.get(1).getFangqi();
										type = 1;
										MaxId = luckService.getMaxId(sqlLuch)
												.getRecordId();
										luckDao.addAnswerRecords(MaxId,
												enterId, TimeUtil.getTime(),
												fromUsername, type);
									}
									if (anList.size() == ruleList.get(0)
											.getQuestionNum()) {
										message = ruleList.get(0).getFangqi();
										type = 0;
										MaxId = luckService.getMaxId(sqlLuch)
												.getRecordId();
										luckDao.addAnswerRecords(MaxId,
												enterId, TimeUtil.getTime(),
												fromUsername, type);
									}
									questionDao.updateBySql(enterId,
											fromUsername);
									String msgType = "text";
									String resultStr = textTpl.format(textTpl,
											fromUsername, toUsername, time,
											msgType, message);
									this.print(resultStr);
								}
							} else {
								Answer answer = questionDao.getMaxAnswer(
										enterId, fromUsername, 0);
								if (answer == null) {
									questionDao.updateBySql(enterId,
											fromUsername);
									String msgType = "text";
									String resultStr = textTpl.format(textTpl,
											fromUsername, toUsername, time,
											msgType, tishiList.get(0)
													.getDaguo());
									this.print(resultStr);
								}
								answer = questionDao.getMaxAnswer(enterId,
										fromUsername);
								if (answer == null) {
									questionDao.updateBySql(enterId,
											fromUsername);
									String msgType = "text";
									String resultStr = textTpl.format(textTpl,
											fromUsername, toUsername, time,
											msgType, tishiList.get(0)
													.getChaoShi());
									this.print(resultStr);
								}
								if (answer != null) {
									int answerId = answer.getAnswerid();
									int questionId = questionDao.getQuestionId(
											enterId, answerId);
									Question question = questionDao
											.getQuestionByID(enterId,
													questionId);
									if (keyword.equalsIgnoreCase(question
											.getRightAnswer())) {
										List<Answer> anList = questionDao
												.getAnAnswerList(enterId,
														fromUsername);
										if (anList.size() == ruleList.get(2)
												.getQuestionNum() - 1) {
											type = 2;
											MaxId = luckService.getMaxId(
													sqlLuch).getRecordId();
											luckDao.addAnswerRecords(MaxId,
													enterId,
													TimeUtil.getTime(),
													fromUsername, type);
											answer.setSendAnswer(keyword);
											answer.setState(1);
											questionDao.updateAnswer(enterId,
													answer,
													question.getQuestionid());
											questionDao.updateBySql(enterId,
													fromUsername);
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(2)
																	.getQuestionTishi());
											this.print(resultStr);
										} else if (anList.size() == ruleList
												.get(1).getQuestionNum() - 1) {
											answer.setState(0);
											answer.setSendAnswer(keyword);
											questionDao.updateAnswer(enterId,
													answer,
													question.getQuestionid());
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(1)
																	.getQuestionTishi());
											this.print(resultStr);
										} else if (anList.size() == ruleList
												.get(0).getQuestionNum() - 1) {
											answer.setState(0);
											answer.setSendAnswer(keyword);
											questionDao.updateAnswer(enterId,
													answer,
													question.getQuestionid());
											String msgType = "text";
											String resultStr = textTpl
													.format(textTpl,
															fromUsername,
															toUsername,
															time,
															msgType,
															ruleList.get(0)
																	.getQuestionTishi());
											this.print(resultStr);
										} else {
											answer.setState(0);
											answer.setSendAnswer(keyword);
											questionDao.updateAnswer(enterId,
													answer,
													question.getQuestionid());
											String questionIDs = "";
											for (int i = 0; i < answerList
													.size(); i++) {
												if ((i + 1) == answerList
														.size()) {
													int answerIdss = answerList
															.get(i)
															.getAnswerid();
													questionIDs += questionDao
															.getQuestionId(
																	enterId,
																	answerIdss);
													/*
													 * questionIDs +=
													 * answerList.get(i)
													 * .getQuestion()
													 * .getQuestionid();
													 */
												} else {
													int answerIds = answerList
															.get(i)
															.getAnswerid();
													questionIDs += questionDao
															.getQuestionId(
																	enterId,
																	answerIds)
															+ ",";

													/*
													 * questionIDs +=
													 * answerList.get(i)
													 * .getQuestion()
													 * .getQuestionid() + ", ";
													 */
												}
											}
											if (answerList.size() >= ruleList
													.get(1).getQuestionNum()) {
												typeNum = 2;
											}
											if (answerList.size() >= ruleList
													.get(0).getQuestionNum()
													&& answerList.size() < ruleList
															.get(1)
															.getQuestionNum()) {
												typeNum = 1;
											}
											QuestionType questionType = questionDao
													.getTypeByNum(enterId,
															typeNum);
											List<Question> questList = questionDao
													.getQtByIds(
															enterId,
															questionIDs,
															questionType
																	.getTypeId());
											Random random = new Random();

											Question q = questList.get(random
													.nextInt(questList.size()));
											MaxId = questionService.getMaxId(
													sql0).getAnswerid();
											questionDao.addAnswer(MaxId,
													enterId, 0, fromUsername,
													TimeUtil.getTime(),
													q.getQuestionid());
											if (q.getType() == 0) {
												String msgType = "music";
												String resultStr = "";
												if (typeNum == 2) {
													resultStr = music
															.format(music,
																	fromUsername,
																	toUsername,
																	TimeUtil.getImagesTime(),
																	msgType,
																	q.getQuestionTitle(),
																	"A"
																			+ q.getAnswerA()
																			+ "B"
																			+ q.getAnswerB()
																			+ "C"
																			+ q.getAnswerC(),
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ q.getQustionContent(),
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ q.getQustionContent());
												} else {
													resultStr = music
															.format(music,
																	fromUsername,
																	toUsername,
																	TimeUtil.getImagesTime(),
																	msgType,
																	q.getQuestionTitle(),
																	"A"
																			+ q.getAnswerA()
																			+ "\nB"
																			+ q.getAnswerB(),
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ q.getQustionContent(),
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ q.getQustionContent());
												}

												this.print(resultStr);
											}
											if (q.getType() == 1) {
												String msgType = "text";
												String resultStr = "";
												switch (typeNum) {
												case 0:
													resultStr = textTpl
															.format(textTpl,
																	fromUsername,
																	toUsername,
																	time,
																	msgType,
																	q.getQustionContent()
																			+ "\nA."
																			+ q.getAnswerA()
																			+ "\nB."
																			+ q.getAnswerB()
															/*
															 * + "\nC." + q
															 * .getAnswerC() +
															 * "\nD." + q
															 * .getAnswerD()
															 */
															);
													break;
												case 1:
													resultStr = textTpl
															.format(textTpl,
																	fromUsername,
																	toUsername,
																	time,
																	msgType,
																	q.getQustionContent()
																			+ "\nA."
																			+ q.getAnswerA()
																			+ "\nB."
																			+ q.getAnswerB()
																			+ "\nC."
																			+ q.getAnswerC()
															// + "\nD."
															// + q.getAnswerD()
															);
													break;
												case 2:
													resultStr = textTpl
															.format(textTpl,
																	fromUsername,
																	toUsername,
																	time,
																	msgType,
																	q.getQustionContent()
																			+ "\nA."
																			+ q.getAnswerA()
																			+ "\nB."
																			+ q.getAnswerB()
																			+ "\nC."
																			+ q.getAnswerC()
																			+ "\nD."
																			+ q.getAnswerD());
													break;
												}

												if (typeNum == 2) {

												}

												this.print(resultStr);
											}
										}
									} else {
										answer.setSendAnswer(keyword);
										questionDao.updateAnswer(enterId,
												answer,
												question.getQuestionid());
										questionDao.updateBySql(enterId,
												fromUsername);

										String msgType = "text";
										String resultStr = textTpl.format(
												textTpl, fromUsername,
												toUsername, time, msgType,
												tishiList.get(0).getDaCuo());
										this.print(resultStr);
										questionDao.updateBySql2(enterId,
												fromUsername);
									}
								}
							}
						} else {
							String contentStr = "很抱歉，活动已经结束";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

					}
					// 签到开始

					if (keyword.equalsIgnoreCase("QD")) {
						boolean f = pidui(62, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中！！";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("签到", enterId)
									.get(0);
						} catch (Exception e) {
							String contentStr = "不好意思，暂时没有此活动";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}
						if (move.getMoveState() == 0) {
							String probability = move.getProbability();
							String str1[] = probability.split("mm");
							String[] tianshu1 = str1[0].split(";");
							/*
							 * String[] zhuanpan1=str1[1].split(";"); String[]
							 * zhuanpan2=str1[2].split(";"); String[]
							 * zhuanpan3=str1[3].split(";");
							 */
							int day1 = Integer.parseInt(tianshu1[0]);
							int day2 = Integer.parseInt(tianshu1[1]);
							int day3 = Integer.parseInt(tianshu1[2]);
							String title = "";
							String imageUrl = "";
							Keywords kwords = userService.getKeyBysendContent(
									enterId, keyword);
							if (kwords != null) {
								List<Keywordexplicit> w = userService
										.getkExplicitByEkey(enterId,
												kwords.getKeywordId(), 0);
								if (w.size() != 0) {
									Keywordexplicit wp = w.get(0);
									title = wp.getTitle();
									imageUrl = wp.getEcontent();
								}
								String currentTime = TimeUtil.getTime();
								checklist = imageservice.queryAllChenck(
										enterId, fromUsername);

								if (checklist.size() > 0) {

									// System.out.println("您今天已经签到");

									String msgType = "text";
									String resultStr = textTpl.format(textTpl,
											fromUsername, toUsername, time,
											msgType, "亲,不好意思,你今天已经签到,请明天再来哦。"

									/* + "输入‘签到’可以继续查看签到规则哦" */);
									this.print(resultStr);

								} else {
									// 如果没有设置 就不出现转盘只是签到
									if (day1 == 0 && day2 == 0 && day3 == 0) {
										checklistByName = imageservice
												.queryAllChenckByName(enterId,
														fromUsername);// 根据公众用户名查找签到表是否曾经签到
										checklist1 = imageservice
												.queryAllChenck1(enterId,
														fromUsername);
										if (checklistByName.size() > 0) {
											CheckIn checkNear = checklistByName
													.get(0);
											checkin = new CheckIn();
											checkin.setCheckuser(fromUsername);
											int sumScore = 10 + Integer
													.parseInt(checkNear
															.getScore());
											checkin.setScore(sumScore + "");
											checkin.setCheckNum("1");
											if (checklist1.size() == 1) {
												checkin.setCount(1 + checkNear
														.getCount());// 连续签到天数累

											} else {
												checkin.setCount(1);// 签到天数不连续，count从1开始计算
											}
											checkin.setChecTime(currentTime);
											imageservice.addCheck(enterId,
													checkin);
											// 出现图文
											String msgType1 = "news";
											String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
											String resultStr1 = textTpl1
													.format(textTpl1,

															fromUsername,
															toUsername,
															TimeUtil.getTime(),
															msgType1,
															1,
															title,
															"您已连续签到"
																	+ checkin
																			.getCount()
																	+ "天了,要想继续抽奖，请加油～～"
																	+ "恭喜您已拥有'"
																	+ checkin
																			.getScore()
																	+ "' 积分哦",
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ imageUrl,
															url1
																	+ "?fromusername="
																	+ fromUsername
																	+ "&enterId="
																	+ enterId);
											this.print(resultStr1);

										} else {
											// 第一次新用户签到
											checkin = new CheckIn();
											checkin.setCheckuser(fromUsername);
											checkin.setScore("10");
											checkin.setCheckNum("1");
											checkin.setCount(1);
											// SimpleDateFormat sdf = new
											// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
											checkin.setChecTime(currentTime);
											System.out.println(checkin == null);
											imageservice.addCheck(enterId,
													checkin);
											// System.out.println("新用户签到成功");
											// System.out.println("签到天数没达到抽奖条件连接");
											String msgType1 = "news";
											String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
											String resultStr1 = textTpl1
													.format(textTpl1,

															fromUsername,
															toUsername,
															TimeUtil.getTime(),
															msgType1,
															1,
															title,
															"欢迎新用户,您今天第"
																	+ checkin
																			.getCount()
																	+ "天签到"
																	+ "恭喜您已拥有'"
																	+ checkin
																			.getScore()
																	+ "' 积分哦",
															"http://www.uniqyw.com/wxpt/web/images/"
																	+ enterId
																	+ "/"
																	+ imageUrl,
															url1
																	+ "?fromusername="
																	+ fromUsername
																	+ "&enterId="
																	+ enterId);
											this.print(resultStr1);

										}

									} else if (day1 != 0 && day2 != 0
											&& day3 != 0) {
										checklistByName = imageservice
												.queryAllChenckByName(enterId,
														fromUsername);
										checklist1 = imageservice
												.queryAllChenck1(enterId,
														fromUsername);
										System.out.println(checklist1.size());
										if (checklistByName.size() > 0) {// 已经签到。积分累计

											CheckIn checkNear = checklistByName
													.get(0);
											System.out.println(checkNear);
											checkin = new CheckIn();
											System.out.println(checkin);
											checkin.setCheckuser(fromUsername);
											int sumScore = 10 + Integer
													.parseInt(checkNear
															.getScore());
											checkin.setScore(sumScore + "");
											checkin.setCheckNum("1");
											if (checklist1.size() == 1) {
												System.out.println(checkNear
														.getCount() + "44");
												if (checkNear.getCount() >= day3) {
													checkin.setCount(1);// 签到天数超过30天开始从1开始计算

												} else {
													checkin.setCount(1 + checkNear
															.getCount());// 连续签到天数累计
												}

											} else {
												checkin.setCount(1);// 签到天数不连续，count从1开始计算
											}
											checkin.setChecTime(currentTime);
											imageservice.addCheck(enterId,
													checkin);

											checklistByName = imageservice
													.queryAllChenckByName(
															enterId,
															fromUsername);
											CheckIn checkNearNew = checklistByName
													.get(0);

											// 获取最新对象签到天数
											int nearCount = checkNearNew
													.getCount();
											System.out.println(nearCount);
											if (nearCount >= day1
													&& nearCount < day2) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day1) {

													// System.out.println("链接到用第一次出现抽奖转盘手机输出");

													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得第一次抽奖机会～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,要想继续抽奖，请加油～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else if (nearCount >= day2
													&& nearCount < day3) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day2) {

													// System.out.println("链接到用第二次出现抽奖转盘手机输出");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll1";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得第二次抽奖机会～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else if (nearCount >= day3) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day3) {

													// System.out.println("链接到用第三次出现抽奖转盘手机输出");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll2";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得第三次抽奖机会，请今天务必抽奖，明天将清空哦～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,要想继续抽奖，请加油～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else {

												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"您已连续签到"
																		+ checkin
																				.getCount()
																		+ "天了,要想继续抽奖，请加油～～"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);
											}

										} else {
											//
											// 今天新用户第一次签到
											if (checklist.size() == 0) {
												checkin = new CheckIn();
												checkin.setCheckuser(fromUsername);
												checkin.setScore("10");
												checkin.setCheckNum("1");
												checkin.setCount(1);
												// SimpleDateFormat sdf = new
												// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
												checkin.setChecTime(currentTime);
												System.out
														.println(checkin == null);
												imageservice.addCheck(enterId,
														checkin);
												// System.out.println("新用户签到成功");
												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"欢迎新用户,您今天第"
																		+ checkin
																				.getCount()
																		+ "天签到"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);

											}
										}

										// 只设置了第一种，第二次和第三次不设置
									} else if (day1 != 0 && day2 == 0
											&& day3 == 0) {

										checklistByName = imageservice
												.queryAllChenckByName(enterId,
														fromUsername);
										checklist1 = imageservice
												.queryAllChenck1(enterId,
														fromUsername);
										System.out.println(checklist1.size());
										if (checklistByName.size() > 0) {// 已经签到。积分累计
											CheckIn checkNear = checklistByName
													.get(0);
											checkin = new CheckIn();
											checkin.setCheckuser(fromUsername);
											int sumScore = 10 + Integer
													.parseInt(checkNear
															.getScore());
											checkin.setScore(sumScore + "");
											checkin.setCheckNum("1");
											if (checklist1.size() == 1) {
												if (checkNear.getCount() >= day1) {
													checkin.setCount(1);// 签到天数超过第一次

												} else {
													checkin.setCount(1 + checkNear
															.getCount());// 连续签到天数累计
												}

											} else {
												checkin.setCount(1);// 签到天数不连续，count从1开始计算
											}
											checkin.setChecTime(currentTime);
											imageservice.addCheck(enterId,
													checkin);

											checklistByName = imageservice
													.queryAllChenckByName(
															enterId,
															fromUsername);
											System.out.println(checklistByName
													.size());
											CheckIn checkNearNew = checklistByName
													.get(0);

											// 获取最新对象签到天数
											int nearCount = checkNearNew
													.getCount();
											System.out.println(nearCount);
											if (nearCount >= day1) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day1) {

													// System.out.println("链接到用第一次出现抽奖转盘手机输出");

													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得抽奖机会～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,要想抽奖，请加油～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else {

												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"您已连续签到"
																		+ checkin
																				.getCount()
																		+ "天了,要想抽奖，请加油～～"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);
											}

										} else {
											//
											// 今天新用户第一次签到
											if (checklist.size() == 0) {
												checkin = new CheckIn();
												checkin.setCheckuser(fromUsername);
												checkin.setScore("10");
												checkin.setCheckNum("1");
												checkin.setCount(1);
												// SimpleDateFormat sdf = new
												// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
												checkin.setChecTime(currentTime);
												System.out
														.println(checkin == null);
												imageservice.addCheck(enterId,
														checkin);
												// System.out.println("新用户签到成功");
												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"欢迎新用户,您今天第"
																		+ checkin
																				.getCount()
																		+ "天签到"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);

											}
										}

									}// 只设置了第一种和第二中情况，第三次不设置

									else if (day1 != 0 && day2 != 0
											&& day3 == 0) {

										checklistByName = imageservice
												.queryAllChenckByName(enterId,
														fromUsername);
										checklist1 = imageservice
												.queryAllChenck1(enterId,
														fromUsername);
										System.out.println(checklist1.size());
										if (checklistByName.size() > 0) {// 已经签到。积分累计
											CheckIn checkNear = checklistByName
													.get(0);
											checkin = new CheckIn();
											checkin.setCheckuser(fromUsername);
											int sumScore = 10 + Integer
													.parseInt(checkNear
															.getScore());
											checkin.setScore(sumScore + "");
											checkin.setCheckNum("1");
											if (checklist1.size() == 1) {
												if (checkNear.getCount() >= day2) {
													checkin.setCount(1);// 签到天数超过30天开始从1开始计算

												} else {
													checkin.setCount(1 + checkNear
															.getCount());// 连续签到天数累计
												}

											} else {
												checkin.setCount(1);// 签到天数不连续，count从1开始计算
											}
											checkin.setChecTime(currentTime);
											imageservice.addCheck(enterId,
													checkin);

											checklistByName = imageservice
													.queryAllChenckByName(
															enterId,
															fromUsername);
											CheckIn checkNearNew = checklistByName
													.get(0);

											// 获取最新对象签到天数
											int nearCount = checkNearNew
													.getCount();
											System.out.println(nearCount);
											if (nearCount >= day1
													&& nearCount < day2) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day1) {

													// System.out.println("链接到用第一次出现抽奖转盘手机输出");

													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得第一次抽奖机会～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,要想继续抽奖，请加油～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else if (nearCount >= day2) {
												String prizeTime = "0000/00/00";
												String nearTime = checkNearNew
														.getChecTime();
												prizelistByName = imageservice
														.queryByNameOrder(
																enterId,
																fromUsername);
												// System.out.println(prizelistByName.size());
												if (prizelistByName.size() != 0) {
													Prize prize = prizelistByName
															.get(0);
													prizeTime = prize
															.getPrizeTime();
												}

												SimpleDateFormat df = new SimpleDateFormat(
														"yyyy/MM/dd");

												Date d1 = df.parse(nearTime);
												Date d2 = df.parse(prizeTime);
												long diff = d1.getTime()
														- d2.getTime();
												long hours = diff
														/ (1000 * 60 * 60);
												long mins = diff / (1000 * 60);
												long days = diff
														/ (1000 * 60 * 60 * 24);

												if (days > nearCount - day2) {

													// System.out.println("链接到用第二次出现抽奖转盘手机输出");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/roll!roll1";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了,恭喜您获得第二次抽奖机会，请今天务必抽奖，明天将清空哦～～～"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);

												} else {
													// System.out.println("链接到没有抽奖转盘");
													String msgType1 = "news";
													String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
													String resultStr1 = textTpl1
															.format(textTpl1,

																	fromUsername,
																	toUsername,
																	TimeUtil.getTime(),
																	msgType1,
																	1,
																	title,
																	"您已连续签到"
																			+ checkin
																					.getCount()
																			+ "天了"
																			+ "恭喜您已拥有'"
																			+ checkin
																					.getScore()
																			+ "' 积分哦",
																	"http://www.uniqyw.com/wxpt/web/images/"
																			+ enterId
																			+ "/"
																			+ imageUrl,
																	url1
																			+ "?fromusername="
																			+ fromUsername
																			+ "&enterId="
																			+ enterId);
													this.print(resultStr1);
												}

											} else {

												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"您已连续签到"
																		+ checkin
																				.getCount()
																		+ "天了,要想继续抽奖，请加油～～"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);
											}

										} else {
											//
											// 今天新用户第一次签到
											if (checklist.size() == 0) {
												checkin = new CheckIn();
												checkin.setCheckuser(fromUsername);
												checkin.setScore("10");
												checkin.setCheckNum("1");
												checkin.setCount(1);
												// SimpleDateFormat sdf = new
												// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
												checkin.setChecTime(currentTime);
												System.out
														.println(checkin == null);
												imageservice.addCheck(enterId,
														checkin);
												// System.out.println("新用户签到成功");
												// System.out.println("签到天数没达到抽奖条件连接");
												String msgType1 = "news";
												String url1 = "www.uniqyw.com/wxpt/site/check-in!start";
												String resultStr1 = textTpl1
														.format(textTpl1,

																fromUsername,
																toUsername,
																TimeUtil.getTime(),
																msgType1,
																1,
																title,
																"欢迎新用户,您今天第"
																		+ checkin
																				.getCount()
																		+ "天签到"
																		+ "恭喜您已拥有'"
																		+ checkin
																				.getScore()
																		+ "' 积分哦",
																"http://www.uniqyw.com/wxpt/web/images/"
																		+ enterId
																		+ "/"
																		+ imageUrl,
																url1
																		+ "?fromusername="
																		+ fromUsername
																		+ "&enterId="
																		+ enterId);
												this.print(resultStr1);

											}
										}
									}
								}
							}
						} else {
							String contentStr = "很抱歉，活动已经结束";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

					}
					// 签到结束
					// 会员卡++++++++++++++++++++++++++++++++++++++++
					if (keyword.equals("hy")) {
						boolean f = pidui(79, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中！！";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("微会员", enterId)
									.get(0);
						} catch (Exception e) {
							String contentStr = "不好意思，暂时没有此活动";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}
						if (move.getMoveState() == 0) {

							if (memberService
									.checkMember(enterId, fromUsername).size() == 0) {
								Keywords kwords = userService
										.getKeyBysendContent(enterId, keyword);
								if (kwords != null) {
									String title = "";
									String imageUrl = "";
									String ecoution = "";
									List<Keywordexplicit> w = userService
											.getkExplicitByEkey(enterId,
													kwords.getKeywordId(), 0);

									if (w.size() != 0) {
										Keywordexplicit wp = w.get(0);
										title = wp.getTitle();
										imageUrl = wp.getEcontent();
										ecoution = wp.getEinstruction();
									}
									String msgTypes = "news";
									String url = "www.uniqyw.com/wxpt/site/web/vip-web";
									// String title = "微信会员卡";
									String addTime = TimeUtil.getTimes();
									String[] Time = addTime.split("-");
									String a = new SimpleDateFormat("MM-dd")
											.format(new Date());
									System.out.println(move.getAwardTime());
									String endTime;
									if (move.getAwardTime() == ""
											|| move.getAwardTime().equals("")) {
										endTime = (Integer.parseInt(Time[0]) + 100)
												+ "-" + a;
									} else {
										endTime = (Integer.parseInt(Time[0]) + Integer
												.parseInt(move.getAwardTime()))
												+ "-" + a;
									}

									String cardId = "NO."
											+ new SimpleDateFormat(
													"yyyyMMddHHss")
													.format(new Date());
									String content = "尊敬的会员卡用户,您的会员卡号为"
											+ cardId + "会员尊享特权\n可点击进入查询";
									String pic = "http://www.uniqyw.com/wxpt/web/images/"
											+ enterId + "/" + imageUrl;
									QuickMark.createQuickMaik(cardId, filepath,
											endTime);
									memberService.addMember(enterId, cardId,
											fromUsername, toUsername, addTime,
											endTime, 0);
									/*
									 * contentStr =
									 * "你已经成为会员\n你可以享受我们为你提供的很多优惠哦\n" +
									 * "<a href= \"http://www.uniqyw.com/wxpt/site/web/vip-web?cardId="
									 * +cardId+"\">点击进入赶快体验吧!!!</a>";
									 */
									String resultStr = textTplpic.format(
											textTplpic, fromUsername,
											toUsername, TimeUtil.getTime(),
											msgTypes, 1, title, content, pic,
											url + "?enterId=" + enterId
													+ "&cardId=" + cardId + "");

									this.print(resultStr);
								}
							} else {
								Keywords kwords = userService
										.getKeyBysendContent(enterId, keyword);
								if (kwords != null) {
									String title = "";
									String imageUrl = "";
									String ecoution = "";
									List<Keywordexplicit> w = userService
											.getkExplicitByEkey(enterId,
													kwords.getKeywordId(), 0);
									if (w.size() != 0) {
										Keywordexplicit wp = w.get(0);
										title = wp.getTitle();
										imageUrl = wp.getEcontent();
										ecoution = wp.getEinstruction();
									}
									String msgTypes = "news";
									String url = "www.uniqyw.com/wxpt/site/web/vip-web";
									String pic = "http://www.uniqyw.com/wxpt/web/images/"
											+ enterId + "/" + imageUrl;
									// String title = "微信会员卡";
									String id = memberService
											.checkMember(enterId, fromUsername)
											.get(0).getCardId();
									String content = "您已成功成为会员,您的会员卡号为" + id
											+ "会员尊享特权\n可点击进入查询";
									System.out.println(url + "?enterId="
											+ enterId + "&cardId=" + id + "");
									String resultStr = textTplpic.format(
											textTplpic, fromUsername,
											toUsername, TimeUtil.getTime(),
											msgTypes, 1, title, content, pic,
											url + "?enterId=" + enterId
													+ "&cardId=" + id + "");
									this.print(resultStr);
								}
							}
						} else {
							String contentStr = "很抱歉，活动已经结束";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
					}

					if (keyword.equals("微商城")) {

						boolean f = pidui(168, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中！！";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						if (memberService.checkMember(enterId, fromUsername)
								.size() == 0) {
							String contentStr = "";
							String msgType = "text";
							contentStr = "您目前不是会员，请输入'hy'>>>>注册会员！！";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

						Member m = memberService.checkMember(enterId,
								fromUsername).get(0);
						if (m.getUsername() == null) {
							String contentStr = "";
							String msgType = "text";
							contentStr = "微商城欢迎您，输入'hy'>>>>完善资料！！";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						if (m.getMemberFreeze() == 0) {
							String contentStr = "";
							String msgType = "text";
							contentStr = "微商城欢迎您，您的会员状态已被冻结！！";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						Keywords kwords = userService.getKeyBysendContent(
								enterId, keyword);
						if (kwords != null) {
							String title = "";
							String imageUrl = "";
							String ecoution = "";
							List<Keywordexplicit> w = userService
									.getkExplicitByEkey(enterId,
											kwords.getKeywordId(), 0);
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
							String resultStr1 = textTpl1.format(textTpl1,
									fromUsername, toUsername,
									TimeUtil.getTime(), msgType1, 1, title,
									ecoution,
									"http://www.uniqyw.com/wxpt/web/images/"
											+ enterId + "/" + imageUrl, url1);
							this.print(resultStr1);

						}

					}

					// 会员卡++++++++++++++++++++++++++++++++++++++++
					// 刮刮卡活动开始-----------------------------------------------------------------------
					if (keyword.equals("刮刮乐")) {
						// 获取活动状态和时间
						boolean f = pidui(156, enterId);
						if (f == false) {
							String contentStr = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
						try {
							move = moveService.findByMoveName("刮刮乐", enterId)
									.get(0);
						} catch (Exception e) {
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}
						int state = move.getMoveState();
						String moveStartTime = move.getMoveStartTime();
						int hasCard = cardReordsService.getBycardUserName(
								fromUsername, enterId, moveStartTime);
						if (state == 0) {// 活动未结束
							if (hasCard == 0) {
								// 还未曾获取到刮刮卡
								// String contentStr =
								// "&lt;a href= &quot;http://www.uniqyw.com/wxpt/site/card!getLuck1?fromUsername="
								// +
								// fromUsername+"&quot;&gt;点击获取刮刮卡&lt;/a&gt;";
								// String msgType = "text";
								// String resultStr = textTpl.format(textTpl,
								// fromUsername, toUsername, time, msgType,
								// contentStr);
								// this.print(resultStr);
								Keywords kwords = userService
										.getKeyBysendContent(enterId, keyword);
								if (kwords != null) {
									String title = "";
									String imageUrl = "";
									String eintruction = "";
									List<Keywordexplicit> w = userService
											.getkExplicitByEkey(enterId,
													kwords.getKeywordId(), 0);
									if (w.size() != 0) {
										Keywordexplicit wp = w.get(0);
										title = wp.getTitle();
										imageUrl = wp.getEcontent();
										eintruction = wp.getEinstruction();
									}
									String msgType1 = "news";
									String url1 = "www.uniqyw.com/wxpt/site/card!getLuck1";
									String resultStr1 = textTpl1.format(
											textTpl1, fromUsername, toUsername,
											TimeUtil.getTime(), msgType1, 1,
											title, eintruction,
											"http://www.uniqyw.com/wxpt/web/images/"
													+ enterId + "/" + imageUrl,
											url1 + "?fromUsername="
													+ fromUsername
													+ "&enterId=" + enterId);
									this.print(resultStr1);

								}
							} else {
								String contentStr = "不好意思，您已经获取过刮刮卡，无法重复获取";
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, contentStr);
								this.print(resultStr);
							}
						} else {
							String contentStr = "很抱歉，活动已经结束~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}

						// 若活动已经取消或时间已过则提示用户
						// 如果活动仍在进行 则执行以下逻辑
						// 首先判断有没有已经领取刮刮卡
						// 如果已经领取 给出相应提示
						// 如果没有，获取用户名，
						// 执行封装好的提取刮刮卡的方法 获取奖品内容（在刮刮卡表里）
						// 将用户名，奖品内容 存入奖品存取表
					}
					// 刮刮卡活动结束------------------------------------------------------------------

					String contentStr = "";
					User user = userService.getUserByName(enterId, keyword);
					if (user != null) {
						boolean f = pidui(39, enterId);
						if (f == false) {
							String contentStr1 = "亲。。。不好意思，此功能正在开发中~~~";
							String msgType1 = "text";
							String resultStr1 = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType1,
									contentStr1);
							this.print(resultStr1);
						}

						UserCount uc = userService.getUserCountBySend(enterId,
								fromUsername);
						String sql = "SELECT * FROM wxpt" + enterId
								+ ".question_tishi where type=2";
						List<QuestionTishi> tishiList = questionService
								.getAllTishi(sql);
						if (userService.getUserCountBySend(enterId,
								fromUsername) == null
								|| ((uc.getSendTime().substring(0, 10))
										.equals(TimeUtil.getTime().substring(0,
												10))) == false) {
							/* List<Vote> votelist=userService.getAll(enterId); */
							try {
								move = moveService.findByMoveName("投票活动",
										enterId).get(0);
							} catch (Exception e) {
								String contentStr2 = "不好意思，暂时没有此活动~~~";
								String msgType2 = "text";
								String resultStr2 = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType2, contentStr2);
								this.print(resultStr2);
							}
							if (move.getMoveState() == 0) {

								/*
								 * UserCount uCount = new UserCount();
								 * uCount.setUser(user);
								 * uCount.setSendTime(TimeUtil.getTime());
								 * uCount.setSendUser(fromUsername);
								 */
								userService.saveUserCount(enterId,
										TimeUtil.getTime(), fromUsername,
										user.getUserId());
								contentStr = tishiList.get(0).getMeiJiang();
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, contentStr);
								this.print(resultStr);
							} else if (move.getMoveState() == 1) {
								contentStr = "很抱歉，活动已经结束~~~";
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, contentStr);
								this.print(resultStr);
							}

						} else {
							contentStr = tishiList.get(0).getChoujiang();
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						}
					}

					Keywords kwords = userService.getKeyBysendContent(enterId,
							keyword);
					if (kwords != null) {
						List<Keywordexplicit> w = userService
								.getkExplicitByEkey(enterId,
										kwords.getKeywordId(), 0);
						if (w.size() != 0) {
							Random i = new Random();
							Keywordexplicit k = w.get(i.nextInt(w.size()));
							if (k.getEkey().equals("word")) {
								String msgType = "text";
								String resultStr = textTpl.format(textTpl,
										fromUsername, toUsername, time,
										msgType, k.getEcontent());
								this.print(resultStr);
							}
							if (k.getEkey().equals("vidio")) {
								String msgType = "music";
								String resultStr = music.format(
										music,
										fromUsername,
										toUsername,
										TimeUtil.getImagesTime(),
										msgType,
										k.getTitle(),
										k.getEinstruction(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent());
								this.print(resultStr);
							}
							if (k.getEkey().equals("images")) {
								String msgType = "news";
								String resultStr = textTpl1.format(
										textTpl1,
										fromUsername,
										toUsername,
										k.getAddTime(),
										msgType,
										1,
										k.getTitle(),
										k.getEinstruction(),
										"http://www.uniqyw.com/wxpt/web/images/"
												+ enterId + "/"
												+ k.getEcontent(), k.getUrl());
								this.print(resultStr);
							}
						}
						List<Keywordexplicit> imageList = userService
								.getkExplicitByEkey(enterId,
										kwords.getKeywordId(), "images", 1);
						if (imageList.size() > 0) {
							String d = "<Articles>";
							for (Keywordexplicit k : imageList) {
								d += "<item>"
										+ "<Title><![CDATA["
										+ k.getTitle()
										+ "]]></Title>"
										+ "<Description><![CDATA["
										+ k.getEinstruction()
										+ "]]></Description>"
										+ "<PicUrl><![CDATA["
										+ "http://www.uniqyw.com/wxpt/web/images/"
										+ enterId + "/" + k.getEcontent()
										+ "]]></PicUrl>" + "<Url><![CDATA["
										+ k.getUrl() + "]]></Url>" + "</item>";
							}

							d += "</Articles>";
							textTpl3Content = d;
							String msgType = "news";
							String resultStr = textTpl3.format(textTpl3,
									fromUsername, toUsername,
									TimeUtil.getImagesTime(), msgType,
									imageList.size(), textTpl3Content);
							this.print(resultStr);
						}

					}
					Pattern patternSfzhm1 = Pattern
							.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
					Pattern patternSfzhm2 = Pattern
							.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
					Matcher matcherSfzhm1 = patternSfzhm1.matcher(keyword);
					Matcher matcherSfzhm2 = patternSfzhm2.matcher(keyword);

					if (matcherSfzhm1.find() || matcherSfzhm2.find()) {
						if(Integer.parseInt(shiyongList.get(10).getShiyongValue())==0){
							String contentStrs = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStrs);
							this.print(resultStr);
						}else{
						String url = "http://api.k780.com/?app=idcard.get&idcard="
								+ keyword
								+ "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";
						Httpclient httpclient = new Httpclient();
						Weather weather = new Weather();
						weather = httpclient.getCarId(url);
						contentStr = "生源地：" + weather.getDaty() + "\n 出生日期："
								+ weather.getFeng() + "\n性别："
								+ weather.getImage() + "";
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
					}}
					// 手机归属地
					Pattern p = Pattern
							.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
					Matcher m = p.matcher(keyword);
					System.out.println(keyword.length());
					if (keyword.length() == 11 && m.matches()) {
						if(Integer.parseInt(shiyongList.get(2).getShiyongValue())==0){
							String contentStrs = "我们正在努力开发~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStrs);
							this.print(resultStr);
						}else{
						Httpclient httpclient = new Httpclient();
						Weather weather = new Weather();
						String url = "http://api.k780.com/?app=phone.get&phone="
								+ keyword
								+ "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";
						weather = httpclient.getPhoneAddress(url);
						contentStr = "地区：" + weather.getFeng() + "\n 类型："
								+ weather.getWendu() + "\n区号："
								+ weather.getDaty() + "\n邮编："
								+ weather.getImage() + "";
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
					}}
					// 身份证
					// Pattern sfz = Pattern.compile("^\\d{17}[][0-9X]$");
					// Matcher sf = sfz.matcher(keyword);
					// System.out.println(sf.matches()+"---");
					// if(sf.matches()){
					// Httpclient httpclient=new Httpclient();
					// Weather weather=new Weather();
					// String
					// url="http://api.k780.com/?app=idcard.get&idcard="+keyword+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
					// weather=httpclient.getCarId(url);
					// contentStr =
					// "地区："+weather.getFeng()+"\n 类型："+weather.getWendu()+"\n区号："+weather.getDaty()+"\n邮编："+weather.getImage()+"";
					// String msgType = "text";
					// String resultStr = textTpl.format(textTpl,
					// fromUsername, toUsername, time, msgType,
					// contentStr);
					// this.print(resultStr);
					// }

					if (kwords == null && user == null) {
						// contentStr = "感谢您的回复，输入“0”就可以回到导航页面了么么哒~~~";
						// String msgType = "text";
						// String resultStr = textTpl.format(textTpl,
						// fromUsername,
						// toUsername, time, msgType, contentStr);
						// this.print(resultStr);
						this.reply(fromUsername, toUsername, time, 1);
					}

				}
			}
			if (MsgType.equals("image")) {
				String sqll = "SELECT * FROM wxpt" + enterId
						+ ".fans WHERE `fans_name`='" + fromUsername
						+ "'  and  `fans_value`='sy'";
				Fans fans = fansService.getFansByNameValue(sqll);
				if (fans != null) {
					if (fans.getFansValue().equals("sy")) {
						FansUser fansUser = fansService.getFansUserByName(
								enterId, fromUsername);
						if (fansUser == null) {
							String sql = "INSERT INTO wxpt"
									+ enterId
									+ ".fans_user( `fans_user_name`, `update_time`)"
									+ " VALUES ('" + fromUsername + "','"
									+ TimeUtil.getTimes() + "')";
							fansService.addFans(sql);

							fansUser = fansService.getFansUserByName(enterId,
									fromUsername);
						} else {
							String sql = "update wxpt" + enterId
									+ ".fans_user set `update_time`='"
									+ TimeUtil.getTimes()
									+ "' where `fans_user_id`="
									+ fansUser.getFansUserId();
							fansService.updateFans(enterId, sql);
						}
						String sql = "INSERT INTO wxpt"
								+ enterId
								+ ".fans_image( `fans_image_value`, `image_update_time`, `fans_user_id`, `check_state`)"
								+ " VALUES ('" + picUrl + "','"
								+ TimeUtil.getTime() + "',"
								+ fansUser.getFansUserId() + ",0)";
						fansService.addFans(sql);
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								"上传参赛照片，我们会以最快的速度将你的作品登入摄影大赛中~如想取消上传，输入“取消”即可");
						this.print(resultStr);
					} else {
						try {
							move = moveService.findByMoveName("摄影大赛", enterId)
									.get(0);
						} catch (Exception e) {
							// TODO: handle exception
							String contentStr = "不好意思，暂时没有此活动~~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);

						}

						if (move.getMoveState() == 1) {
							String contentStr = "很抱歉，活动已经结束~~";
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									contentStr);
							this.print(resultStr);
						} else {
							String msgType = "text";
							String resultStr = textTpl.format(textTpl,
									fromUsername, toUsername, time, msgType,
									"发送“摄影”二字,即可参加摄影大赛!");
							this.print(resultStr);
						}
					}
				}
			}
			if (event != null) {
				if (event.equals("subscribe")) {
					this.reply(fromUsername, toUsername, time, 0);
				} else if (event.equals("unsubscribe")) {
					String hql = "delete from wxpt" + enterId
							+ ".member where weixin_id = '" + fromUsername
							+ "'";
					memberService.delMember(hql);
				}

			}
			/*
			 * if (null == keyword || keyword.equals("")) { String contentStr =
			 * "感谢您的回复，输入“0”就可以回到导航页面了么么哒~~~" +
			 * (questionDao.getMaxAnswer(enterId, fromUsername) == null); String
			 * msgType = "text"; String resultStr = textTpl.format(textTpl,
			 * fromUsername, toUsername, time, msgType, contentStr);
			 * this.print(resultStr); }
			 */

		} else {
			this.print("");
		}

	}

	@SuppressWarnings("static-access")
	public void reply(String fromUsername, String toUsername, String time,
			int type) {
		String msgType = "";
		// Keywords kwords =
		// replyService.getReplyByType(enterId,type).getKeywords();

		int keywordsId = replyService.getReplyByType(enterId, type);
		List<Keywordexplicit> w = userService.getkExplicitByEkey(enterId,
				keywordsId, 0);
		if (w.size() != 0) {
			Random i = new Random();
			Keywordexplicit k = w.get(i.nextInt(w.size()));
			if (k.getEkey().equals("word")) {
				msgType = "text";
				String resultStr = ReplyStr.getTextTpl().format(
						ReplyStr.getTextTpl(), fromUsername, toUsername, time,
						msgType, k.getEcontent());
				this.print(resultStr);
			}
			if (k.getEkey().equals("vidio")) {
				msgType = "music";
				String resultStr = ReplyStr.getMusic().format(
						ReplyStr.getMusic(),
						fromUsername,
						toUsername,
						TimeUtil.getImagesTime(),
						msgType,
						k.getTitle(),
						k.getEinstruction(),
						"http://www.uniqyw.com/wxpt/web/images/" + enterId
								+ "/" + k.getEcontent(),
						"http://www.uniqyw.com/wxpt/web/images/" + enterId
								+ "/" + k.getEcontent());
				this.print(resultStr);
			}
			if (k.getEkey().equals("images")) {
				msgType = "news";
				String resultStr = ReplyStr.getImgTpl().format(
						ReplyStr.getImgTpl(),
						fromUsername,
						toUsername,
						k.getAddTime(),
						msgType,
						1,
						k.getTitle(),
						k.getEinstruction(),
						"http://www.uniqyw.com/wxpt/web/images/" + enterId
								+ "/" + k.getEcontent(), k.getUrl());
				this.print(resultStr);
			}
		}

		List<Keywordexplicit> imageList = userService.getkExplicitByEkey(
				enterId, keywordsId, "images", 1);

		if (imageList.size() > 0) {
			String d = "<Articles>";
			for (Keywordexplicit k : imageList) {
				d += "<item>" + "<Title><![CDATA[" + k.getTitle()
						+ "]]></Title>" + "<Description><![CDATA["
						+ k.getEinstruction() + "]]></Description>"
						+ "<PicUrl><![CDATA["
						+ "http://www.uniqyw.com/wxpt/web/images/" + enterId
						+ "/" + k.getEcontent() + "]]></PicUrl>"
						+ "<Url><![CDATA[" + k.getUrl() + "]]></Url>"
						+ "</item>";
			}

			d += "</Articles>";
			String textTpl3Content = d;
			msgType = "news";
			String resultStr = ReplyStr.getMoreImgTpl().format(
					ReplyStr.getMoreImgTpl(), fromUsername, toUsername,
					TimeUtil.getImagesTime(), msgType, imageList.size(),
					textTpl3Content);
			this.print(resultStr);
		}
	}

	// public void reply(String fromUsername, String toUsername, String time,
	// int type,String textTpl,String music,String textTpl3,String textTpl1) {
	// String msgType = "";
	//
	// Keywords kwords = userService.getKeyBysendContent(enterId,"0");
	// List<Keywordexplicit> w = userService.getkExplicitByEkey(enterId,
	// kwords.getKeywordId(), 0);
	// if (w.size() != 0) {
	// Random i = new Random();
	// Keywordexplicit k = w.get(i.nextInt(w.size()));
	// if (k.getEkey().equals("word")) {
	// msgType = "text";
	// String resultStr = textTpl.format(
	// textTpl, fromUsername, toUsername, time,
	// msgType, k.getEcontent());
	// this.print(resultStr);
	// }
	// if (k.getEkey().equals("vidio")) {
	// msgType = "music";
	// String resultStr = music.format(
	// music,
	// fromUsername,
	// toUsername,
	// TimeUtil.getImagesTime(),
	// msgType,
	// k.getTitle(),
	// k.getEinstruction(),
	// "http://www.uniqyw.com/wxpt/web/images/"
	// + k.getEcontent(),
	// "http://www.uniqyw.com/wxpt/web/images/"
	// + k.getEcontent());
	// this.print(resultStr);
	// }
	// if (k.getEkey().equals("images")) {
	// msgType = "news";
	// String resultStr = textTpl1.format(
	// textTpl1,
	// fromUsername,
	// toUsername,
	// k.getAddTime(),
	// msgType,
	// 1,
	// k.getTitle(),
	// k.getEinstruction(),
	// "http://www.uniqyw.com/wxpt/web/images/"
	// + k.getEcontent(), k.getUrl());
	// this.print(resultStr);
	// }
	// }
	//
	// List<Keywordexplicit> imageList = userService.getkExplicitByEkey(enterId,
	// kwords.getKeywordId(), "images", 1);
	//
	// if (imageList.size() > 0) {
	// String d = "<Articles>";
	// for (Keywordexplicit k : imageList) {
	// d += "<item>" + "<Title><![CDATA[" + k.getTitle()
	// + "]]></Title>" + "<Description><![CDATA["
	// + k.getEinstruction() + "]]></Description>"
	// + "<PicUrl><![CDATA["
	// + "http://www.uniqyw.com/wxpt/web/images/"
	// + k.getEcontent() + "]]></PicUrl>" + "<Url><![CDATA["
	// + k.getUrl() + "]]></Url>" + "</item>";
	// }
	//
	// d += "</Articles>";
	// String textTpl3Content = d;
	// msgType = "news";
	// String resultStr = textTpl3.format(
	// textTpl3, fromUsername, toUsername,
	// TimeUtil.getImagesTime(), msgType, imageList.size(),
	// textTpl3Content);
	// this.print(resultStr);
	// }
	// }
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() throws Exception {
		/*
		 * List<Vote> votelist=userService.getAll(enterId);
		 * System.out.println(votelist.size());
		 */
		return SUCCESS;
	}

	// 微信接口验证
	public boolean checkSignature() {
		String TOKEN = "";
		// 根据企业id读取企业tooken
		EnterInfor enter = enterService.getById2(enterId);
		if (enter != null) {
			TOKEN = enter.getToken();
		}

		String signature = final_request.getParameter("signature");
		String timestamp = final_request.getParameter("timestamp");
		String nonce = final_request.getParameter("nonce");
		String token = TOKEN;
		String[] tmpArr = { token, timestamp, nonce };
		Arrays.sort(tmpArr);
		String tmpStr = this.ArrayToString(tmpArr);
		tmpStr = this.SHA1Encode(tmpStr);
		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}

		/*
		 * String signature = final_request.getParameter("signature"); String
		 * timestamp = final_request.getParameter("timestamp"); String nonce =
		 * final_request.getParameter("nonce"); String token = TOKEN; String[]
		 * tmpArr = { token, timestamp, nonce }; Arrays.sort(tmpArr); String
		 * tmpStr = this.ArrayToString(tmpArr); tmpStr =
		 * this.SHA1Encode(tmpStr); if (tmpStr.equalsIgnoreCase(signature)) {
		 * return true; } else { return false; }
		 */

	}

	// 向请求端发送返回数据
	public void print(String content) {
		try {
			final_response.setContentType("text/html; charset=utf-8");
			final_response.getWriter().print(content);
			final_response.getWriter().flush();
			final_response.getWriter().close();
		} catch (Exception e) {

		}
	}

	// 数组转字符串
	public String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	// sha1加密
	public String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	// 从输入流读取post参数
	public String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}

	EnterInfor enter;
	ManageUser user;
	RoleService roleService;
	Role role;

	// 微商城判断方法
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

	public ImageService getImageservice() {
		return imageservice;
	}

	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}

	public CheckIn getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}

	public List<CheckIn> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<CheckIn> checklist) {
		this.checklist = checklist;
	}

	public List<CheckIn> getChecklist1() {
		return checklist1;
	}

	public void setChecklist1(List<CheckIn> checklist1) {
		this.checklist1 = checklist1;
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

	public List<CheckIn> getChecklistByName() {
		return checklistByName;
	}

	public void setChecklistByName(List<CheckIn> checklistByName) {
		this.checklistByName = checklistByName;
	}

	public List<Prize> getPrizelistByName() {
		return prizelistByName;
	}

	public void setPrizelistByName(List<Prize> prizelistByName) {
		this.prizelistByName = prizelistByName;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LuckDao getLuckDao() {
		return luckDao;
	}

	public void setLuckDao(LuckDao luckDao) {
		this.luckDao = luckDao;
	}

	public FansDao getFansDao() {
		return fansDao;
	}

	public void setFansDao(FansDao fansDao) {
		this.fansDao = fansDao;
	}

	public CustomersDao getCustomersDao() {
		return customersDao;
	}

	public void setCustomersDao(CustomersDao customersDao) {
		this.customersDao = customersDao;
	}

}
