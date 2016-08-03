package test;

import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Login {
	static GetMethod getMethod2;
	static HttpClient httpClient = new HttpClient();
	static long ct = System.currentTimeMillis();
	static String t = String.valueOf(ct);
	public static String cookie;
	static int statusCode;
	static String html;

	public static void main(String[] args) {
		get2();
	}

	public static void get2() {
		// 构造HttpClient的实例

		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(
				"http://113.105.64.194:8000/b2blogin.shtml?method=unspecified&bh=SDJNHD18&kl=hdadmin406&rempwd=&newtime="
						+ t + "");
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		try {
			try {
				statusCode = httpClient.executeMethod(getMethod);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取cookie
			org.apache.commons.httpclient.Cookie[] cookies = httpClient
					.getState().getCookies();
			if (cookies.length == 0) {
				System.out.println("cookies:None");
			} else {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i].toString();
				}
				String[] cookies1 = cookie.split("=");
			}
			// 打印出返回数据，检验一下是否成功
			/*
			 * String text = getMethod.getResponseBodyAsString();
			 * System.out.println(text);
			 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			// 读取内容
			/* byte[] responseBody = getMethod.getResponseBody(); */

			try {
				html = getMethod.getResponseBodyAsString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 输出网页内容
			dd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dd() {

		try {

			getMethod2 = new GetMethod(
					"http://113.105.64.194:8000/pay/pay_forward.shtml?pt=B2B&ywType=1&yymk=312013404&ddbh=747172805087140211");
			getMethod2.setFollowRedirects(false);
			getMethod2.getParams().setHttpElementCharset("UTF-8");
			getMethod2.setRequestHeader(
					"Hm_lpvt_d2a375fc3ce005fb418335f8ae096cad", t);
			getMethod2.setRequestHeader("JSESSIONID", cookie);
			getMethod2.setRequestHeader("Host", "113.105.64.194");
			// 每次访问需授权的网址时需带上前面的 cookie 作为通行证
			getMethod2.setRequestHeader("Secure", "No");

			// 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
			getMethod2.setRequestHeader("HttpOnly", "No");

			int statuscode = httpClient.executeMethod(getMethod2);
			html = getMethod2.getResponseBodyAsString();
			System.out.println(html);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
