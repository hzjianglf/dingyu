package com.wxpt.action.site;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;



import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Menu;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.MenuService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MenuAction extends ActionSupport{
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	@Autowired
	MenuService menuService;
	@Autowired
	EnterService enterService;

//public String  execute() throws Exception {
//	try {
//		EnterInfor enterInfor = enterService.getById(enterId);
//		String appId=enterInfor.getAppId();
//		String appSecret=enterInfor.getAppSecret();
//		//String str=MenuUtil.getAccess_token(appId,appSecret);
//		String str=enterInfor.getAccessToken();//********暂未使用请误删***********//
//		String s1=getMenus();
//		createMenus(s1, str);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return "error";
//	}
//	return "success";
//}

public void createMenu(){

	try {
		EnterInfor enterInfor = enterService.getById(enterId);
		String appId=enterInfor.getAppId();
		String appSecret=enterInfor.getAppSecret();
		String str=MenuUtil.getAccess_token(appId,appSecret);
		//String str=enterInfor.getAccessToken();//********暂未使用请误删***********//
		String s1=getMenus();
		createMenus(s1, str);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//return "error";
	}
	//return "success";

}


public String getMenus(){
	List<Menu> menuZhuList=new ArrayList<Menu>();
	List<Menu> menuItem1List=new ArrayList<Menu>();
	List<Menu> menuItem2List=new ArrayList<Menu>();
	List<Menu> menuItem3List=new ArrayList<Menu>();
	menuZhuList=menuService.zfindAll(enterId);//获取主菜单集合
	int menuId1=menuZhuList.get(0).getMenuId();
	int menuId2=menuZhuList.get(1).getMenuId();
	int menuId3=menuZhuList.get(2).getMenuId();
	menuItem1List=menuService.zfindByMenuParentId(enterId,menuId1);//获取第一个主菜单下子菜单的集合
	menuItem2List=menuService.zfindByMenuParentId(enterId,menuId2);//获取第二个主菜单下子菜单的集合
	menuItem3List=menuService.zfindByMenuParentId(enterId,menuId3);//获取第三个主菜单下子菜单的集合
	Map<String ,Object> map=new HashMap<String , Object >();//最外层map
	Map<String ,Object> zhuMap1=new HashMap<String , Object >();//主map
	Map<String ,Object> zhuMap2=new HashMap<String , Object >();//主map
	Map<String ,Object> zhuMap3=new HashMap<String , Object >();//主map
	List<Object> list=new ArrayList<Object>();//最外层list
	Map<String, Object>mapItem1 =new HashMap<String , Object >();
	Map<String, Object>mapItem2 =new HashMap<String , Object >();
	Map<String, Object>mapItem3 =new HashMap<String , Object >();
	Menu menu;
	List<Object> itemList1=new ArrayList<Object>();
	List<Object> itemList2=new ArrayList<Object>();
	List<Object> itemList3=new ArrayList<Object>();
	for(int i=0;i<menuItem1List.size();i++){
		menu=new Menu();
		menu=menuItem1List.get(i);
		mapItem1=new HashMap<String , Object >();
		if(menu.getMenuType().equals("click")){
			mapItem1.put("type", menu.getMenuType());
			mapItem1.put("name", menu.getMenuName());
			mapItem1.put("key", menu.getMenuKey());
		}
		else if(menu.getMenuType().equals("view")){
			mapItem1.put("type", menu.getMenuType());
			mapItem1.put("name", menu.getMenuName());
			mapItem1.put("url", menu.getMenuUrl());
		}
		itemList1.add(mapItem1);
	}
	
	for(int i=0;i<menuItem2List.size();i++){
		menu=new Menu();
		menu=menuItem2List.get(i);
		mapItem2=new HashMap<String , Object >();
		if(menu.getMenuType().equals("click")){
			mapItem2.put("type", menu.getMenuType());
			mapItem2.put("name", menu.getMenuName());
			mapItem2.put("key", menu.getMenuKey());
		}
		else if(menu.getMenuType().equals("view")){
			mapItem2.put("type", menu.getMenuType());
			mapItem2.put("name", menu.getMenuName());
			mapItem2.put("url", menu.getMenuUrl());
		}
		itemList2.add(mapItem2);
	}
	
	for(int i=0;i<menuItem3List.size();i++){
		menu=new Menu();
		menu=menuItem3List.get(i);
		mapItem3=new HashMap<String , Object >();
		if(menu.getMenuType().equals("click")){
			mapItem3.put("type", menu.getMenuType());
			mapItem3.put("name", menu.getMenuName());
			mapItem3.put("key", menu.getMenuKey());
		}
		else if(menu.getMenuType().equals("view")){
			mapItem3.put("type", menu.getMenuType());
			mapItem3.put("name", menu.getMenuName());
			mapItem3.put("url", menu.getMenuUrl());
		}
		itemList3.add(mapItem3);
	}
	
	Menu menu1=new Menu();
	Menu menu2=new Menu();
	Menu menu3=new Menu();
		
		menu1=menuZhuList.get(0);
		menu2=menuZhuList.get(1);
		menu3=menuZhuList.get(2);
		zhuMap1.put("name", menu1.getMenuName());
		zhuMap1.put("sub_button", itemList1);
		
		zhuMap2.put("name", menu2.getMenuName());
		zhuMap2.put("sub_button", itemList2);
		
		zhuMap3.put("name", menu3.getMenuName());
		zhuMap3.put("sub_button", itemList3);
		list.add(zhuMap1);
		list.add(zhuMap2);
		list.add(zhuMap3);
	map.put("button", list);
	JSONObject jo = JSONObject.fromObject(map);
	String str=jo.toString();
	System.out.println(str);
	return str;
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
public void createMenus(String params,String accessToken ) {
	String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
      try {
         URL url = new URL(action);
         HttpURLConnection http =   (HttpURLConnection) url.openConnection();    
         SSLContext sc = SSLContext.getInstance("SSL");
         sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
         ((HttpsURLConnection) http).setSSLSocketFactory(sc.getSocketFactory());
         http.setRequestMethod("POST");        
         http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
         http.setDoOutput(true);        
         http.setDoInput(true);
         System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
         System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒

         http.connect();
         OutputStream os= http.getOutputStream();    
         os.write(params.getBytes("UTF-8"));//传入参数    
         os.flush();
         os.close();
      
         InputStream is =http.getInputStream();
         int size =is.available();
         byte[] jsonBytes =new byte[size];
         is.read(jsonBytes);
         String message=new String(jsonBytes,"UTF-8");
         System.out.println( "返回信息"+message);
         } catch (MalformedURLException e) {
             e.printStackTrace();
             System.out.println("createMenu 失败"); 
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("createMenu 失败"); 
         }    
      
 }

}
