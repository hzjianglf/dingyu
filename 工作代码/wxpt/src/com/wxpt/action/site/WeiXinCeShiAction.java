package com.wxpt.action.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.QuickMark;
import com.wxpt.common.ReplyStr;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.UserService;

public class WeiXinCeShiAction extends ActionSupport {
	final String TOKEN = "zhunimenhaoyun";
	final HttpServletRequest final_request = ServletActionContext.getRequest();
	final HttpServletResponse final_response = ServletActionContext
			.getResponse();
	String filepath = ServletActionContext.getServletContext().getRealPath(
			"web")
			+ "/images/";
	@Autowired
	MemberService memberService;
	private int enterId;

	public void valid() {
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
	@SuppressWarnings("static-access")
	public void responseMsg() {
		String postStr = null;
		try {
			postStr = this.readStreamParameter(final_request.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			String Location_X = root.elementTextTrim("Location_X");
			String Location_Y = root.elementTextTrim("Location_Y");
			String Scale = root.elementTextTrim("Scale");
			String MsgType = root.elementTextTrim("MsgType");
			String event = root.elementTextTrim("Event");
			String keyButton = root.elementTextTrim("EventKey");
			String time = new Date().getTime() + "";
			String textTpl3Content = "";
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
			String textTpl3 = "<xml>"
					+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
					+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
					+ "<CreateTime>%3$s</CreateTime>"
					+ "<MsgType><![CDATA[%4$s]]></MsgType>"
					+ "<ArticleCount>%5$s</ArticleCount>%6$s"
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
			if (MsgType.equals("event")) {
				if (event.equals("subscribe")) {

					String contentStr = "发送“姓名+微信号+部门名称”，进行绑定";
					String msgType = "text";
					String resultStr = textTpl.format(textTpl, fromUsername,
							toUsername, time, msgType, contentStr);
					this.print(resultStr);
				} else if (event.equals("unsubscribe")) {
					String hql = "delete from wxpt" + enterId
							+ ".member where weixin_id = '" + fromUsername
							+ "'";
					memberService.delMember(hql);
				}

			}
			if (MsgType.equals("text")) {
				if (null != keyword && !keyword.equals("")) {
					String contentStr;
					if(keyword.contains("+")){
						String [] key=keyword.split("\\+");
						String cardId = "NO."
								+ new SimpleDateFormat(
										"yyyyMMddHHss")
										.format(new Date());
						
						String sql="INSERT INTO wxpt"+enterId+".member(`card_id`, `password`, `weixin_id`, `business_id`, `username`,  `add_time`, `end_time`,  `weixin_hao`,`bumen`)" +
								" VALUES ('"+cardId+"','888888','"+fromUsername+"','"+toUsername+"','"+key[0]+"','"+ TimeUtil.getTimes()+"','2013-12-26','"+key[1]+"','"+key[2]+"')";
						try {
							memberService.addGrade(sql);
							contentStr="绑定成功~~~~";
						} catch (Exception e) {
							// TODO Auto-generated catch block
							contentStr="绑定失败~~~~";
						}
						
						String msgType = "text";
						String resultStr = textTpl.format(textTpl,
								fromUsername, toUsername, time, msgType,
								contentStr);
						this.print(resultStr);
						
					}else{
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
													+ k.getEcontent(), k.getUrl()+"&fromUserId="+fromUsername+"#http://mp.weixin.qq.com/");
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
											+ k.getUrl()+"&fromUserId="+fromUsername+"#http://mp.weixin.qq.com/" + "]]></Url>" + "</item>";
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

			}
		}
	}

	// 微信接口验证
	public boolean checkSignature() {
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

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

}
