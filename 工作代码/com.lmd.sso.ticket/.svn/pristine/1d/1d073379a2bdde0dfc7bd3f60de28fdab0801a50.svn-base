package com.lmd.sso.ticket.client;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jasig.cas.client.validation.Assertion;
public class SSOClient {

	
	public static String getTicket( String server, String username, String password, String service){
		notNull(server,"server must not be null");
		notNull(username,"username must not be null");
		notNull(password,"password must not be null");
		notNull(service,"service must not be null");
		return getServiceTicket(server,getTicketGrantingTicket(server,username,password),service);
	}
	
	public static String getServiceTicket( String server, String ticketGrantingTicket, String service){
		if(ticketGrantingTicket==null){
			return null;
		}
		 HttpClient client = new HttpClient();
		 PostMethod post = new PostMethod(server+"/"+ticketGrantingTicket);
		 post.setRequestHeader("aaa","bbb");
		post.setRequestBody(new NameValuePair[]{new NameValuePair("service",service)});
		try{
			client.executeMethod(post);
			 String response = post.getResponseBodyAsString();
			switch(post.getStatusCode()){
			case 200:
				return response;
				
			default:
				break;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			post.releaseConnection();
		}
		return null;
	}
	
	public static String getTicketGrantingTicket( String server, String username, String password){
		 HttpClient client = new HttpClient();
		 PostMethod post = new PostMethod(server);
		post.setRequestBody(new NameValuePair[]{new NameValuePair("username",username),new NameValuePair("password",password)});
		try{
			client.executeMethod(post);
			 String response = post.getResponseBodyAsString();
//			System.out.println("response------------"+response);
//			System.out.println("statuscode----------"+post.getStatusCode());
			switch(post.getStatusCode()){
			    case 201:{
			    	//Pattern ptn = Pattern.compile(".*action=\".*/(.*?)\".*");
				    Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(response);
				    //System.out.println("matcher.group----------"+matcher.group(1));
				    if(matcher.matches()){
				    	//System.out.println("matches========="+matcher.group(1));
				    	return matcher.group(1);
				    }
				    break;
			    }
			    default:
			    	break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return null;
	}
	
	public static void notNull( Object object, String message){
		if(object==null){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void main( String[]args){
		
		//�� server �е�localhost���ɷ�������IP��ַ
		 String server = "http://10.10.10.207:8000/sso/v2/tickets";
		
		 String username = "caizhenxiang";
		 String password = "111111";
		
		//�� service ����Ҫ���ʵ�ҵ��ϵͳ�ĵ�ַ
		// String service = "http://localhost/sso2/Account/Login.aspx";
		 String service = "http://localhost/DBService/account/Login.aspx";
		System.out.println(getTicket(server,username,password,service));
	}
}
