package lmd.jcg.gsm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.internal.runtime.Log;

/** 
* HTTP工具箱 
* 
* @author leizhimin 2009-6-19 16:36:18 
*/ 
public final class HttpTool { 

        /** 
         * 执行一个HTTP GET请求，返回请求响应的HTML 
         * 
         * @param url                 请求的URL地址 
         * @param queryString 请求的查询参数,可以为null 
         * @param charset         字符集 
         * @param pretty            是否美化 
         * @return 返回请求响应的HTML 
         */ 
        public static String doGet(String url, String queryString, String charset, boolean pretty) { 
                StringBuffer response = new StringBuffer(); 
                HttpClient client = new HttpClient(); 
                HttpMethod method = new GetMethod(url); 
                try { 
                        if (StringUtils.isNotBlank(queryString)) 
                                //对get请求参数做了http请求默认编码，好像没有任何问题，汉字编码后，就成为%式样的字符串 
                                method.setQueryString(URIUtil.encodeQuery(queryString)); 
                        client.executeMethod(method); 
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset)); 
                                String line; 
                                while ((line = reader.readLine()) != null) { 
                                        if (pretty) 
                                                response.append(line).append(System.getProperty("line.separator"));
                                        else 
                                                response.append(line); 
                                } 
                                reader.close(); 
                        } 
                } catch (URIException e) { 
                        //log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e); 
                } catch (IOException e) { 
                        //log.error("执行HTTP Get请求" + url + "时，发生异常！", e); 
                } finally { 
                        method.releaseConnection(); 
                } 
                return response.toString(); 
        } 

        /** 
         * 执行一个HTTP POST请求，返回请求响应的HTML 
         * 
         * @param url         请求的URL地址 
         * @param params    请求的查询参数,可以为null 
         * @param charset 字符集 
         * @param pretty    是否美化 
         * @return 返回请求响应的HTML 
         */ 
        public static String doPost(String url, Map<String, String> params, String charset, boolean pretty) { 
                StringBuffer response = new StringBuffer(); 
                HttpClient client = new HttpClient(); 
                HttpMethod method = new PostMethod(url); 
                //设置Http Post数据 
                if (params != null) { 
                        HttpMethodParams p = new HttpMethodParams(); 
                        for (Map.Entry<String, String> entry : params.entrySet()) { 
                                p.setParameter(entry.getKey(), entry.getValue()); 
                        } 
                        method.setParams(p); 
                } 
                try { 
                        client.executeMethod(method); 
                        if (method.getStatusCode() == HttpStatus.SC_OK) { 
//                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset)); 
                                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
                                String line; 
                                while ((line = reader.readLine()) != null) { 
                                        if (pretty) 
                                                response.append(line).append(System.getProperty("line.separator"));
                                        else 
                                                response.append(line); 
                                } 
                                reader.close(); 
                        } 
                } catch (IOException e) { 
                        //log.error("执行HTTP Post请求" + url + "时，发生异常！", e); 
                } finally { 
                        method.releaseConnection(); 
                } 
//                System.out.println(response);
                return response.toString(); 
        } 

        public static void main(String[] args) { 
                String y = doPost("http://10.10.10.16:13013/cgi-bin/sendsms?username=jcg&password=jincang&to=13615319882&text=TestingSMS&from=test", null, "GBK", true); 
//                System.out.println(y); 
        } 
        
        public static int sendSm(String mobile,String content){
        	int status=0;
        	try {
				String result = doPost("http://10.10.10.16:13013/cgi-bin/sendsms?username=jcg&password=jincang&to="+mobile+"&text="+dealContent(content)+"&from=test", null, "GBK", true); 
				System.out.println(result+"  pppppppppppppppppppppppppppppp");
				if(result.contains("0:Accepted for delivery")){
					status=1;
				}else{
					status=0;
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
        	return status;
        }
        
        public static String dealContent(String content){
        	String returnStr = content.replaceAll("%", "%25");
        	returnStr = returnStr.replaceAll(" ", "%20");
        	returnStr = returnStr.replaceAll("/", "%2F");
        	returnStr = returnStr.replaceAll("\\?", "%3F");
        	returnStr = returnStr.replaceAll("\\+", "%2B");
        	returnStr = returnStr.replaceAll("#", "%23");
        	returnStr = returnStr.replaceAll("&", "%26");
        	returnStr = returnStr.replaceAll("=", "%3D");
        	return returnStr;
        }
}