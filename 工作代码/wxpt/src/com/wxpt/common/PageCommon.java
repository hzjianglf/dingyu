package com.wxpt.common;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PageCommon {
	public static String getPageStr(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			
			Element mediaElement = doc.getElementById("media");
			Elements imageElement = mediaElement.getElementsByTag("img");
			for (Element element : imageElement) {
				element.attr("src", element.attr("data-src"));
				element.removeAttr("data-src");
			}
			String htmlStr = doc.toString();
			String xianzhi ="is_weixin();\n"+
"function is_weixin(){\n"+
"var linkStr='';\n"+
"var ua = navigator.userAgent.toLowerCase();\n"+
"if(ua.match(/MicroMessenger/i)==\"micromessenger\") {\n"+
"return true;\n"+
"} else {\n"+
"var urlStr = window.location.href;\n"+
"if(urlStr.indexOf(\"!\") != -1){\n"+
"var badyUrl = urlStr.split(\"!\");\n"+
"linkStr=badyUrl[0]+\"!getTest?\"+badyUrl[1].split(\"?\")[1].split(\"&\")[0];\n"+
"}else{\n"+
"var badyUrl = urlStr.split(\"?\");\n"+
"linkStr=badyUrl[0]+\"!getTest?\"+badyUrl[1];\n"+
"}\n"+
"window.location.href=linkStr;\n"+
"}\n" +
"}\n" +
"document.domain = \"qq.com\";";
			
			htmlStr = htmlStr.replaceAll("document.domain = \"qq.com\";", xianzhi);
			int startIndex = htmlStr.indexOf("function report(link, fakeid, action_type){");
			int endIndex = htmlStr.indexOf("function reportTimeOnPage(){");
			String fHmtStr = htmlStr.substring(0,startIndex);
			String tHmlStr = htmlStr.substring(startIndex,endIndex);
			String trHmlStr = htmlStr.substring(endIndex,htmlStr.length());
			int trHmlStrStartIndex = trHmlStr.indexOf("WeixinJSBridge.on('menu:share:appmessage', function(argv)");
			int trHmlStrEndIndex = trHmlStr.indexOf("if( document.addEventListener )");
			tHmlStr = tHmlStr.replaceAll("url : '/mp/appmsg/show'", "url:((window.location.href).replace('?', '!wTongji?')).replace('#http://mp.weixin.qq.com/', '&type='+action_type)");
			StringBuffer htmlBuffer = new StringBuffer();
			String wechatStr = "WeixinJSBridge.on('menu:share:appmessage', function(argv){\n"
            
+"WeixinJSBridge.invoke('sendAppMessage',{\n"
            
+"\"appid\"      : appId,\n"
            
+"\"img_url\"    : imgUrl,\n"
            
+"\"img_width\"  : \"640\",\n"
            
+"\"img_height\" : \"640\",\n"

+"\"link\"       : share_scene(link, 1),\n"

+"\"desc\"       : desc,\n"

+"\"title\"      : title\n"

+"}, function(res) {report(link, fakeid, 1);\n"
            
+"if(res.err_msg==\"send_app_msg:ok\"){\n"
            
+"tongji(1);\n"
            
+"};\n"
            
+"});\n"
            
+"});"
            
+"\n// 分享到朋友圈;\n"
            
+"WeixinJSBridge.on('menu:share:timeline', function(argv){\n"
            
+"report(link, fakeid, 2);\n"
            
+"tongji(2);\n"
            
+"WeixinJSBridge.invoke('shareTimeline',{\n"
            
+"\"img_url\"    : imgUrl,\n"
            
+"\"img_width\"  : \"640\",\n"

+"\"img_height\" : \"640\",\n"

+"\"link\"       : share_scene(link, 2),\n"

+"\"desc\"       : desc,\n"

+"\"title\"      : title\n"
            
+"}, function(res) {\n"
            
+"});\n"
            
+"});\n"
            
+"\n// 分享到微博;\n"
            
+"var weiboContent = '';\n"
            
+"WeixinJSBridge.on('menu:share:weibo', function(argv){\n"
            
+"WeixinJSBridge.invoke('shareWeibo',{\n"
            
+"\"content\" : title + share_scene(link, 3),\n"
            
+"\"url\"     : share_scene(link, 3) \n"
            
+"}, function(res) {report(link, fakeid, 3);\n"
            
+"if(res.err_msg==\"share_weibo:ok\"){\n"
            
+"tongji(3);\n"
            
+"};\n"
            
+"});\n"
            
+"});\n"
            
+"\n// 分享到Facebook\n"
            
+"WeixinJSBridge.on('menu:share:facebook', function(argv){\n"
            
+"report(link, fakeid, 4);\n"
            
+"WeixinJSBridge.invoke('shareFB',{\n"
            
+"\"img_url\"    : imgUrl,\n"
            
+"\"img_width\"  : \"640\",\n"
            
+"\"img_height\" : \"640\","
            
+"\"link\"       : share_scene(link, 4),\n"
            
+"\"desc\"       : desc,\n"
            
+"\"title\"      : title\n"
            
+"}, function(res) {} );\n"
            
+"});\n"
            
+"\n// 新的接口\n"
            
+"WeixinJSBridge.on('menu:general:share', function(argv){\n"
            
+"var scene = 0;\n"
            
+"switch(argv.shareTo){\n"
            
+"case 'friend'  : scene = 1; break;\n"
            
+"case 'timeline': scene = 2; break;\n"
            
+"case 'weibo'   : scene = 3; break;\n"
            
+"}\n"
            
+"argv.generalShare({\n"
            
+"\"appid\"      : appId,\n"
            
+"\"img_url\"    : imgUrl,\n"
            
+"\"img_width\"  : \"640\",\n"
            
+"\"img_height\" : \"640\",\n"
            
+"\"link\"       : link,\n"
            
+"\"desc\"       : desc,\n"
            
+"\"title\"      : title\n"
            
+"}, function(res){report(link, fakeid, scene);\n"
            
+"if(res.err_msg==\"general_share:ok\"){\n"
            
+"switch(argv.shareTo){\n"
            
+"case 'friend'  : tongji(1); break;\n"
            
+"case 'timeline': tongji(2); break;\n"
            
+"case 'weibo'   : tongji(3); break;\n"
            
+"}\n"
            
+"}\n"
            
+"});\n"
            
+"});\n"
            
+"}\n";
			htmlBuffer.append(fHmtStr).append(tHmlStr).append("\nfunction " +
					"tongji(action_type){\n " +
					"ajax({ \n" +
					"url : ((window.location.href).replace('?\', '!tongji?')).replace('#http://mp.weixin.qq.com/', '&type='+action_type),\n" +
					"type: 'POST',\n" +
					"timeout: 2000 \n" +
					"});\n" +
					"}\n").append(trHmlStr.substring(0,trHmlStrStartIndex)).append(wechatStr).append(trHmlStr.substring(trHmlStrEndIndex, trHmlStr.length()));
			return htmlBuffer.toString().replaceAll("data-src", "src");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	/**
	 * 获取标题
	 * @param url
	 * @return
	 */
	public static String getTitle(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			return doc.title();
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
	/**
	 * 获取分享图片
	 * @param url
	 * @return
	 */
	public static String getImage(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			Element mediaElement = doc.getElementById("media");
			Elements imageElement = mediaElement.getElementsByTag("img");
			return imageElement.get(0).attr("data-src");
		} catch (Exception e) {
			// TODO: handle exception
			Element mediaElement = doc.getElementsByClass("text").first();
			Elements imageElement = mediaElement.getElementsByTag("img");
			return imageElement.get(0).attr("data-src");
		}
		
	}
	/**
	 * 获取内容
	 * @param url
	 * @return
	 */
	public static String getContent(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			Element mediaElement = doc.getElementById("media");
			Elements imageElement = mediaElement.getElementsByTag("img");
			for (Element element : imageElement) {
				element.attr("src", element.attr("data-src"));
				element.removeAttr("data-src");
			}
			return (doc.getElementsByClass("page-bizinfo").toString()+doc.getElementsByClass("page-content").toString() );//.replaceAll("data-src", "src");
		} catch (Exception e) {
			// TODO: handle exception
			Element mediaElement = doc.getElementsByClass("text").first();
			Elements imageElement = mediaElement.getElementsByTag("img");
			for (Element element : imageElement) {
				element.attr("src", element.attr("data-src"));
				element.removeAttr("data-src");
			}
			return (doc.getElementsByClass("page-bizinfo").toString()+doc.getElementsByClass("page-content").toString() );//.replaceAll("data-src", "src");
		}
	}
	/**
	 * 获取原始ID
	 * @param url
	 * @return
	 */
	public static String getWeixinID(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			Elements scriptElement = doc.getElementsByTag("script");
			String scriptStr = scriptElement.get(1).toString();
			/*System.out.println(scriptStr);
			Pattern pattern = Pattern.compile("gh[^\']*");
			Matcher m = pattern.matcher(scriptStr);
			String numberId = "";
			if(m.find()){
				System.out.println(m.group(0));
				numberId = m.group(0);
			}
			return numberId;*/
			return scriptStr.substring(scriptStr.indexOf("WeixinJSBridge.invoke('profile',{"),scriptStr.indexOf("WeixinJSBridge.on('menu:share:appmessage', function(argv){")).split(":")[1].split(",")[0].split("'")[1];
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	/**
	 * 获取概述
	 * @param url
	 * @return
	 */
	public static String getMedail(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			Elements scriptElement = doc.getElementsByTag("script");
			String scriptStr = scriptElement.get(1).toString();
			System.out.println(scriptStr);
			String descStr = scriptStr.substring(scriptStr.indexOf("desc   = htmlDecode(\""), scriptStr.indexOf("fakeid = \"\";"));
			String[] desc = descStr.split("\"");
			System.out.println(desc.length);
			System.out.println(desc[1]);
			System.out.println(descStr);
			return desc[1].replaceAll("&nbsp;", " ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	/**
	 * 获取账号名称
	 * @param url
	 * @return
	 */
	public static String getNoName(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			Element mediaElement = doc.getElementsByClass("text-ellipsis").first();
			return mediaElement.text();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	/**
	 * 获取作者
	 * @param url
	 * @return
	 */
	public static String getAutorName(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			if(doc.getElementsByClass("activity-meta").size()>2){
				return doc.getElementsByClass("activity-meta").get(1).text();
			}else{
				return "";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	/**
	 *  获取分享链接
	 * @param url
	 * @return
	 */
	public static String getUrl(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			Elements scriptElement = doc.getElementsByTag("script");
			String scriptStr = scriptElement.get(1).toString();
			String[] urlStr = scriptStr.substring(scriptStr.indexOf("var sourceurl = trim('"),scriptStr.indexOf("'.replace(/&lt;/g, '<').replace(/&gt;/g, '>'));")).split("'");
			if(urlStr.length > 1){
				return urlStr[1];
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	/**
	 * 获取添加时间
	 * @param url
	 * @return
	 */
	public static String getPageTime(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			return doc.getElementsByClass("activity-meta").get(0).text();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
}
