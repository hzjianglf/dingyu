package com.wxpt.common;

import java.util.Random;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String textTpl1 = "<xml>"
				+ "<ToUserName><![CDATA[%1$s]]></ToUserName>"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>"
				+ "<CreateTime>%3$s</CreateTime>"
				+ "<MsgType><![CDATA[%4$s]]></MsgType>"
				+ "<ArticleCount>%5$s</ArticleCount>" + "<Articles>"
				+ "<item>" + "<Title><![CDATA[%6$s]]></Title>"
				+ "<Description><![CDATA[%7$s]]></Description>"
				+ "<PicUrl><![CDATA[%8$s]]></PicUrl>"
				+ "<Url><![CDATA[%9$s]]></Url>" + "</item>" + "<item>"
				+ "<Title><![CDATA[%10$s]]></Title>"
				+ "<Description><![CDATA[%11$s]]></Description>"
				+ "<PicUrl><![CDATA[%12$s]]></PicUrl>"
				+ "<Url><![CDATA[%13$s]]></Url>" + "</item>" + "</Articles>"
				+ "<FuncFlag>%14$s</FuncFlag>" + "</xml> ";
		String msgType = "news";
		String resultStr = textTpl1
				.format(textTpl1,
						"laobi ",
						"dddd",
						TimeUtil.getImagesTime(),
						msgType,
						2,
						"谭文萌",
						"谭文萌",
						"http://mmsns.qpic.cn/mmsns/HH18bAib3zI19ibcrqmSX57SfmDVVBRDUwRvZFr4nAIS3CE5hff1s2iaw/0",
						"http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5OTU3NTI3Mg==&appmsgid=10000196&itemidx=1&sign=4ff7be8c71edf86f197035c5e5b8097a#wechat_redirect",
						"谭文萌",
						"谭文萌",
						"http://mmsns.qpic.cn/mmsns/HH18bAib3zI19ibcrqmSX57SfmDVVBRDUwRvZFr4nAIS3CE5hff1s2iaw/0",
						"http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5OTU3NTI3Mg==&appmsgid=10000196&itemidx=1&sign=4ff7be8c71edf86f197035c5e5b8097a#wechat_redirect",
						1);
		System.out.println(resultStr);
		String a = "<xml>"
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
		System.out.println(a
							.format(a,
									"a",
									"d",
									TimeUtil.getWeixinTime(),
									msgType,
									1,
									"谭文萌",
									"城色模特——谭文萌",
									"http://mmsns.qpic.cn/mmsns/HH18bAib3zI19ibcrqmSX57SfmDVVBRDUwhYOLpmxofAuwfXFHBksR3Q/0",
									"http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5OTU3NTI3Mg==&appmsgid=10000196&itemidx=1&sign=4ff7be8c71edf86f197035c5e5b8097a#wechat_redirect"));
	
	
		Random i = new Random();
		System.out.println(i.nextInt(10));
		switch(2) 
		{ case 1:System.out.println("you are the first!"); 
		case 2:System.out.println("you are the second!"); 
		case 3:System.out.println("you are the third!"); 		
		default:System.out.println("you are the last!"); 
		}
		System.out.println("b".equalsIgnoreCase("a"));
		String d = null;
		System.out.println(d.equals("a"));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
