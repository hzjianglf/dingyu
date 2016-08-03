package com.wxpt.action.site.web;
/**
 * ��ƣ�֧����֤��
 * ���ܣ�������֤�����Ϣ������֧����ATN���ؽ��
 * �ӿ���ƣ���׼ʵ��˫�ӿ�
 * �汾��2.0
 * ���ڣ�2008-12-25
 * ���ߣ�֧������˾���۲�����֧���Ŷ�
 * ��ϵ��0571-26888888
 * ��Ȩ��֧������˾
 * */
import java.net.*;
import java.io.*;


public class CheckURL {
	   /**
     * ���ַ����MD5����
	 * @param myUrl 
     *
     * @param url
     *
     * @return ��ȡurl����
     */
  public static String check(String urlvalue ) {
	 
	 
	  String inputLine="";
	  
		try{
				URL url = new URL(urlvalue);
				
				HttpURLConnection urlConnection  = (HttpURLConnection)url.openConnection();
				
				BufferedReader in  = new BufferedReader(
			            new InputStreamReader(
			            		urlConnection.getInputStream()));
			
				inputLine = in.readLine().toString();
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(inputLine);  ϵͳ��ӡ��ץȡ����֤���
			
	    return inputLine;
  }


  }