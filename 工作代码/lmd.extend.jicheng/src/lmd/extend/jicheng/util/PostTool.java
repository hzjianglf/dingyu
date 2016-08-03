package lmd.extend.jicheng.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
 
public class PostTool {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
 
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    public static void main(String[] args) {
    	String content="科 技大厦//空房 间是#国家级讽德&诵+功空%间来看发送=到空间了恐+惧感第三个大三受?打击感";
    	
//    	System.out.println(dealContent(content));
//    	函数调用时填入URL和参数（参数非必须）就可以获取返回的数据，发送post请求调用示例
//    	String result=PostTool.sendGet("http://10.10.10.16:13013/cgi-bin/sendsms?username=jcg&password=jincang&to=13615319882&text=TestingSMS&from=test","");
    	String result=PostTool.sendPost("http://10.10.10.207:8000","");
    		System.out.println(result);
	
    }
    
    public static int sendSm(String mobile,String content){
    	int status=0;
    	try {
			String result = sendGet("http://10.10.10.16:13013/cgi-bin/sendsms?username=jcg&password=jincang&to="+mobile+"&text="+dealContent(content)+"&from=test&charset=GBK&coding=2", ""); 
			//System.out.println(result+"  pppppppppppppppppppppppppppppp");
			if(result.contains("0:Accepted for delivery")){
				status=1;
			}
			if(result.contains("3: Queued for later delivery")){
				status=0;
//				System.out.println("++++++++++++++++++");
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


