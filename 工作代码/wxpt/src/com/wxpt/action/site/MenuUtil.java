package com.wxpt.action.site;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;


public class MenuUtil {

	/**
	 * 获得ACCESS_TOKEN
	 * 
	 * @Title: getAccess_token
	 * @Description: 获得ACCESS_TOKEN
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		String appId = "";
		String appSecret = "";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appId + "&secret=" + appSecret;
		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = new JSONObject(message);
			accessToken = demoJson.getString("access_token");

			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	 private static class TrustAnyTrustManager implements X509TrustManager {
		    
	        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }
	    
	        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }
	    
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[]{};
	        }
	    }
	public static String getAccess_token(String appId, String appSecret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appId + "&secret=" + appSecret;
		String accessToken = null;
		try { 
            URL urlGet = new URL(url);
            HttpsURLConnection  http = (HttpsURLConnection ) urlGet.openConnection();    
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            ((HttpsURLConnection) http).setSSLSocketFactory(sc.getSocketFactory());
            http.setRequestMethod("GET");      //必须是get方式请求    
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            http.setDoOutput(true);        
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒

            http.connect();
         
            InputStream is =http.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
             
            JSONObject demoJson = new JSONObject(message);
            accessToken = demoJson.getString("access_token");
             
            System.out.println(message);
            } catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	/**
	 * 创建Menu
	 * 
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return int 返回类型
	 * @throws
	 */

	/**
	 * 删除当前Menu
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除当前Menu
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	// public static String deleteMenu()
	// {
	// String access_token= getAccess_token();
	// String action =
	// "https://api.weixin.qq.com/cgi-bin/menu/delete? access_token="+access_token;
	// try {
	// URL url = new URL(action);
	// HttpURLConnection http = (HttpURLConnection) url.openConnection();
	//
	// http.setRequestMethod("GET");
	// http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	// http.setDoOutput(true);
	// http.setDoInput(true);
	// System.setProperty("sun.net.client.defaultConnectTimeout",
	// "30000");//连接超时30秒
	// System.setProperty("sun.net.client.defaultReadTimeout", "30000");
	// //读取超时30秒
	//
	// http.connect();
	// OutputStream os= http.getOutputStream();
	// os.flush();
	// os.close();
	//
	// InputStream is =http.getInputStream();
	// int size =is.available();
	// byte[] jsonBytes =new byte[size];
	// is.read(jsonBytes);
	// String message=new String(jsonBytes,"UTF-8");
	// return "deleteMenu返回信息:"+message;
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return "deleteMenu 失败";
	// }

}