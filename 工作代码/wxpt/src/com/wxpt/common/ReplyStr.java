package com.wxpt.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReplyStr {

	public static String getTextTpl() {
		return "<xml>" + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
				+ "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>"
				+ "<Content><![CDATA[%5$s]]></Content>"
				+ "<FuncFlag>0</FuncFlag>" + "</xml>";
	}

	public static String getImgTpl() {
		return "<xml>" + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
				+ "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>"
				+ "<ArticleCount>%5$s</ArticleCount>" + "<Articles>" + "<item>"
				+ "<Title><![CDATA[%6$s]]></Title>"
				+ "<Description><![CDATA[%7$s]]></Description>"
				+ "<PicUrl><![CDATA[%8$s]]></PicUrl>"
				+ "<Url><![CDATA[%9$s]]></Url>" + "</item>" + "</Articles>"
				+ "<FuncFlag>1</FuncFlag>" + "</xml> ";
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static String getStr(String url) throws UnsupportedEncodingException {
		return URLEncoder.encode(url, "UTF-8");
	}

	public static String getStrStr(String zhuanye)
			throws UnsupportedEncodingException {
		return java.net.URLDecoder.decode(zhuanye, "utf-8");
	}

	public static void main(String[] args) {
		try {
			System.out.println(ReplyStr.getStr("|"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String getMusic() {
		return "<xml>" + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
				+ "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>" + "<Music>"
				+ "<Title><![CDATA[%5$s]]></Title>"
				+ "<Description><![CDATA[%6$s]]></Description>"
				+ "<MusicUrl><![CDATA[%7$s]]></MusicUrl>"
				+ "<HQMusicUrl><![CDATA[%8$s]]></HQMusicUrl>" + "</Music>"
				+ "<FuncFlag>0</FuncFlag>" + "</xml>";
	}

	public static String getMoreImgTpl(){
		return "<xml>"
				+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
				+ "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>"
				+ "<ArticleCount>%5$s</ArticleCount>%6$s"
				+ "<FuncFlag>1</FuncFlag>" + "</xml> ";
	}
}	