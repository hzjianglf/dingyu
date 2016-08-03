package com.wxpt.action.site;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wxpt.common.Httpclient;
import com.wxpt.site.entity.pojo.YuPiao;


public class ManageYuPiaoAction extends ParentAction {
	Httpclient httpclient=new Httpclient();
	//private String url;
	List<YuPiao>piaoList=new ArrayList<YuPiao>();
	private String chufa;
	private String daoda;
	private String time;		

	public String getChufa() {
		return chufa;
	}
	public void setChufa(String chufa) {
		this.chufa = chufa;
	}
	public String getDaoda() {
		return daoda;
	}
	public void setDaoda(String daoda) {
		this.daoda = daoda;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getYuPiao(){
		String url="http://dynamic.12306.cn/otsquery/query/queryRemanentTicketAction.do?method=queryLeftTicket&orderRequest.train_date="+time+"&orderRequest.from_station_telecode="+chufa+"&orderRequest.to_station_telecode="+daoda+"&orderRequest.train_no=&trainPassType=QB&trainClass=QB%23D%23Z%23T%23K%23QT%23&includeStudent=00&seatTypeAndNum=&orderRequest.start_time_str=00:00--24:00";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String [] list = null;
		try {
			System.out.println(url);
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(
					url);
			try {
				HttpResponse response = httpclient.execute(httpget);
			
		//	String[] urls =  httpget.getRequestLine().toString().split(" ");
			HttpClient client = new HttpClient();
			// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
			HttpMethod method = new GetMethod(url);
			// 使用POST方法
			// HttpMethod method = new PostMethod("http://java.sun.com";);
			client.executeMethod(method);
			// 打印服务器返回的状态
			// 打印返回的信息
			 // 读取内容
			String span=getneirong(url,"span");
			String [] ccc=span.split(";");
			//System.out.println(span);
			String s="";
			 String html = getHtmlByUrl(url); 
			 //System.out.println(html);
			// html = new String(html.getBytes("iso-8859-1"), "utf-8");
			 String htmls=html.replace("{", "").replace("}", "").replace("&nbsp;", "").replace("<br>", "").replace("<font color='darkgray'>","").replace("<\\/font>", "").replace("<img src='/otsquery/images/tips/first.gif'>", "").replace("\\", "").replace("<img src='/otsquery/images/tips/last.gif'>", "");
			//System.out.println(htmls);
			String []ss=htmls.split(",");
			String cc="";
			String []dd = null;
			String ff = null;
			String gg="";
			
			for(int i=1;i<ss.length;i++){
				
				cc+=ss[i]+",";
				//System.out.println(i%16);
				if(i%16==0){
					String bb="n"+i/16+",";
					ff=cc.replace(",n"+i/16+",", "").replace("\",", "");
					String[] oo=ff.split(",");
					gg=ff.replace(oo[0], ccc[i/16-1]);
					
					System.out.println(gg);
					String [] shi=gg.split(",");
					YuPiao yupiao=new YuPiao();
					yupiao.setCheci(shi[0]);
					
					String regEx="[^0-9]";   
					Pattern   p   =   Pattern.compile(regEx);      
					Matcher   m   =   p.matcher(shi[1]);
					String no=m.replaceAll("").trim();
					StringBuffer str = new StringBuffer(no);
					String strInsert = ":";
					str.insert(2,strInsert);
					yupiao.setCftime(str.toString());
					//String sas=shi[1].replace(str.toString(), "");
					yupiao.setChufa(shi[1].replace(str.toString(), ""));
					
					
					Matcher   m2   =   p.matcher(shi[2]);
					String no2=m2.replaceAll("").trim();
					StringBuffer str2 = new StringBuffer(no2);
					String strInsert2 = ":";
					str2.insert(2,strInsert2);
					yupiao.setDdtime(str2.toString());					
					yupiao.setDaozhan(shi[2].replace(str2.toString(), ""));
					yupiao.setLishi(shi[3]);
					yupiao.setShangwu(shi[4]);
					yupiao.setTedeng(shi[5]);
					yupiao.setYi(shi[6]);
					yupiao.setEr(shi[7]);
					yupiao.setGao(shi[8]);
					yupiao.setRuan(shi[9]);
					yupiao.setYing(shi[10]);
					yupiao.setRuanzuo(shi[11]);
					yupiao.setYingzuo(shi[12]);
					yupiao.setWuzuo(shi[13]);
					yupiao.setQita(shi[14]);
					piaoList.add(yupiao);
					cc="";
				}
				
			}
			System.out.println(piaoList.size()+"大小");
			method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} 
		} finally {

			httpclient.getConnectionManager().shutdown();

		}
		return "piao";
	
	}
	public static  String  getneirong(String url ,String biaoqian) throws Exception{
		String s="";
		 String html = getHtmlByUrl(url);
		 html=html.replace("\\", "");
		// html = new String(html.getBytes("iso-8859-1"), "utf-8");
		// System.out.println(html);
	        if (html!=null&&!"".equals(html)) {  
	            Document doc = Jsoup.parse(html);  
	            Elements linksElements = doc.select(biaoqian);
	            
	            for (Element ele:linksElements) { 
						String title2;
						try {
							 String title = ele.text().trim()+";";	
							 
							  s=s+title;
							//  System.out.println(title);
							title2 = new String(title.getBytes("iso-8859-1"), "gb2312");
							
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
	            }  
	          
	        }
	        
	        return s;
	    
	}
	public static String getHtmlByUrl(String url){  
	    String html = null;  
	    DefaultHttpClient httpClient = new DefaultHttpClient();//创建httpClient对象  
	    HttpGet httpget = new HttpGet(url);//以get方式请求该URL  
	    try {  
	        HttpResponse responce = httpClient.execute(httpget);//得到responce对象  
	        int resStatu = responce.getStatusLine().getStatusCode();//返回码  
	        if (resStatu==HttpStatus.SC_OK) {//200正常  其他就不对            
	            HttpEntity entity = responce.getEntity();  
	            if (entity!=null) {  
	                html = EntityUtils.toString(entity);//获得html源代码                
	            }  
	        }  
	    } catch (Exception e) {  
	        System.out.println("访问【"+url+"】出现异常!");  
	        e.printStackTrace();  
	    } finally {  
	        httpClient.getConnectionManager().shutdown();  
	    }  
	    return html;  
	}  
	
	public List<YuPiao> getPiaoList() {
		return piaoList;
	}
	public void setPiaoList(List<YuPiao> piaoList) {
		this.piaoList = piaoList;
	}
}
