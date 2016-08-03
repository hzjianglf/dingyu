package com.wxpt.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

import com.wxpt.site.entity.pojo.Weather;

public class Httpclient {
	public static void main(String[] args) {
		// String url =
		// "http://api.k780.com/?app=phone.get&phone=15005316648&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml";
		// getPhoneAddress(url);
		// getZuoBiao("济南");
	}

	public List<Weather> getTianqi(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		List<Weather> list = new ArrayList<Weather>();
		// Weather weather=new Weather();

		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				String[] urls = httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(urls[1]);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");
				String a = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>date");
				String b = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>dayPictureUrl");
				String c = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>nightPictureUrl");
				String d = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>weather");
				String e = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>wind");
				String f = getneirong(urls[1],
						"CityWeatherResponse>results>weather_data>temperature");
				String[] a1 = a.split(";");
				/* String [] b1=b.split(";"); */
				String[] c1 = c.split(";");
				String[] d1 = d.split(";");
				String[] e1 = e.split(";");
				String[] f1 = f.split(";");
				for (int i = 0; i < 4; i++) {
					Weather weather = new Weather();
					weather.setDaty(a1[i]);
					weather.setImage(c1[i]);
					weather.setYubao(d1[i]);
					weather.setFeng(e1[i]);
					weather.setWendu(f1[i]);
					list.add(weather);
				}
				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}
		return list;
	}

	// 手机归属地查询
	public static Weather getPhoneAddress(String url) {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		Weather weather = new Weather();
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String quhao = getPhone(url, "html>body>root>result");
				String youbian = getPhone(url, "html>body>root>result>postno");
				String diqu = getPhone(url, "html>body>root>result>att");
				String leixing = getPhone(url, "html>body>root>result>ctype");
				String[] qu = quhao.split(",");
				System.out.println(qu[0]);
				String b = qu[0].substring(11, 15);

				weather.setDaty(b);// 区号
				weather.setWendu(leixing);// 类型
				weather.setFeng(diqu);// 地区
				weather.setImage(youbian);// 邮编

				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");

				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}

		return weather;

	}

	// 身份证号
	public static Weather getCarId(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		List<Weather> list = new ArrayList<Weather>();
		Weather weather = new Weather();
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				
				String csd = getCarInformation(url, "body>root>result>idcard");
				String dq = getCarInformation(url, "body>root>result>att");
				String sr = getCarInformation(url, "body>root>result>born");
				String xb = getCarInformation(url, "body>root>result>sex");
				
				weather.setDaty(dq);// 出生地
				weather.setFeng(sr);// 生日
				weather.setImage(xb);// 性别
				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");

				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}
		return weather;

	}

	// 获取内容
	public static String getneirong(String url, String biaoqian) {
		String s = "";
		String html = getHtmlByUrl(url);
		if (html != null && !"".equals(html)) {
			Document doc = Jsoup.parse(html);
			Elements linksElements = doc.select(biaoqian);

			for (Element ele : linksElements) {
				String title2;
				try {
					String title = ele.text().trim() + ";";
					s = s + title;
					title2 = new String(title.getBytes("iso-8859-1"), "gb2312");

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return s;

	}
	public static String getCarInformation(String url, String biaoqian){
		String s="";
		String html = getHtmlByUrl(url);
		try {
			html = new String(html.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (html != null && !"".equals(html)) {
			Document doc = Jsoup.parse(html);
			Elements linksElements = doc.select(biaoqian);

			for (Element ele : linksElements) {
				String title2;
				try {
					String title = ele.text().trim() + ";";
					s = s + title;
					title2 = new String(title.getBytes("iso-8859-1"), "gb2312");

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return s;

	}

	// 提交url
	public static String getHtmlByUrl(String url) {
		String html = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();// 创建httpClient对象
		HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
		try {
			HttpResponse responce = httpClient.execute(httpget);// 得到responce对象
			int resStatu = responce.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
				HttpEntity entity = responce.getEntity();
				if (entity != null) {
					html = EntityUtils.toString(entity);// 获得html源代码
				}
			}
		} catch (Exception e) {
			System.out.println("访问【" + url + "】出现异常!");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}

	// ======================================================================================
	public static String getPhone(String url, String biaoqian) throws Exception {
		String s = "";
		String title = "";
		String html = getHtmlByUrl(url);
		html = new String(html.getBytes("iso-8859-1"), "utf-8");
		// System.out.println(html);
		if (html != null && !"".equals(html)) {
			Document doc = Jsoup.parse(html);
			Elements linksElements = doc.select(biaoqian);

			for (Element ele : linksElements) {
				String title2;
				try {
					title = ele.text().trim();

					System.out.println(title);
					title2 = new String(title.getBytes("iso-8859-1"), "gb2312");

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return title;

	}

	// 域名
	public static Weather getYuMing(String url) {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		List<Weather> list = new ArrayList<Weather>();
		Weather weather = new Weather();
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String ym = getneirong(url, "root>result>domain");
				String xkz = getneirong(url, "root>result>icpno");
				String sy = getneirong(url, "root>result>webhome");
				String zbf = getneirong(url, "root>result>organizers");
				String lx = getneirong(url, "root>result>organizers_type");
				String sj = getneirong(url, "root>result>exadate");
				String jc = getneirong(url, "root>result>sitenm");
				weather.setDaty(ym);// 域名
				weather.setFeng(xkz);// 许可证
				weather.setImage(sy);// 首页
				weather.setWendu(zbf);// 主办方
				weather.setYubao(lx);// 类型
				weather.setDate(sj);// 时间
				weather.setJiancheng(jc);// 简称
				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");

				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}

		return weather;

	}

	// 快递
	public static String[] kuaidi(String url) {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		String[] list = null;
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String s = "";
				String html = getHtmlByUrl(url);
				String htmls = html.replace("{", "").replace("}", "")
						.replace("[", "").replace("]", "").replace("\"", "")
						.replace("zone", "地区").replace("remark", "备注")
						.replace("list:", "").replace("datetime", "时间")
						.replace("no", "订单号").replace("result:company", "快递");
				list = htmls.split(",");
				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}
		return list;
	}

	// 空气
	// 空气
	public static String[] pm(String url) {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		String[] list = null;
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);
			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String s = "";
				String html = getHtmlByUrl(url);
				// html = new String(html.getBytes("iso-8859-1"), "utf-8");
				String htmls = html.replace("{", "").replace("}", "")
						.replace("[", "").replace("]", "").replace("\"", "")
						.replace("aqi", "空气质量指数").replace("area", "城市")
						.replace("pm2_5", "颗粒物(1/时)")
						.replace("pm2_5_24h", "颗粒物(24/时)")
						.replace("quality", "空气质量指数类别")
						.replace("primary_pollutant", "首要污染物")
						.replace("time_point", "数据发布的时间");
				list = htmls.split(",");
				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");

				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}

		return list;

	}

	// 身高体重
	public static Double getShen(String shengao, String tizhong) {
		double height = Double.parseDouble(shengao);
		double weight = Double.parseDouble(tizhong);
		System.out.println(height / 100);
		Double s = (double) (weight / ((height / 100) * (height / 100)));
		return s;
	}

	// 获取坐标
	public static String getZuoBiao(String city) {
		String url = "http://api.map.baidu.com/geocoder/v2/?address="
				+ city
				+ "&output=xml&ak=ECe2b50ae093fe82dff717edec0e41e3&callback=showLocation";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		String[] list = null;
		String x = null;
		String y = null;
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);

			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String s = "";
				String html = getHtmlByUrl(url);
				// html = new String(html.getBytes("iso-8859-1"), "utf-8");

				// System.out.println(method.getResponseBodyAsString());
				// 释放连接
				// getneirong(urls[1],"CityWeatherResponse>results>weather_data");
				x = getneirong(url,
						"GeocoderSearchResponse>result>location>lat");
				y = getneirong(url,
						"GeocoderSearchResponse>result>location>lng").replace(
						";", "");
				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}

		return x + y;
	}

	public static String getKuaiDi(String no) {
		String url = "http://nnlife.cdn.duapp.com/kuaidi.php?key=" + no + "";

		// String
		// url="http://api.map.baidu.com/geocoder/v2/?address="+city+"&output=xml&ak=ECe2b50ae093fe82dff717edec0e41e3&callback=showLocation";

		DefaultHttpClient httpclient = new DefaultHttpClient();
		String[] list = null;
		String html = null;
		try {
			// 注：如果参数值为中文的话，提交过去后可能会是乱码
			HttpGet httpget = new HttpGet(url);

			try {
				HttpResponse response = httpclient.execute(httpget);

				// String[] urls =
				// httpget.getRequestLine().toString().split(" ");
				HttpClient client = new HttpClient();
				// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
				HttpMethod method = new GetMethod(url);
				// 使用POST方法
				// HttpMethod method = new PostMethod("http://java.sun.com";);
				client.executeMethod(method);
				// 打印服务器返回的状态
				// 打印返回的信息
				// 读取内容
				String s = "";
				html = getHtmlByUrl(url);
				method.releaseConnection();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} finally {

			httpclient.getConnectionManager().shutdown();

		}

		return html;
	}
}
