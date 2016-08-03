package lmd.mas.sms.util;

import java.util.Random;

import com.jasson.im.api.APIClient;

public class SmsSendUtil {

	
	
//	private static String tmpMobile = "13615319882,16767889878,899677899";
	public static void main(String[] args) {
		System.out.println(sendSm("13615319882", "ces测试333 "));
	}
	
	
	public static void usage()
	{
		System.out.println("Usage : java ApiTestDemo [-h host] [-n name] [-p password] [-i apiCode]");
		System.out.println("\t-h host        信息机地址");
		System.out.println("\t-n name        API登陆名");
		System.out.println("\t-p password    API登陆密码");
		System.out.println("\t-i apiCode     API编码");

	}
	
	
	
	public static int  sendSm(String mobiles,String content){
		String host = ProperUtil.getProperty("host");
		String dbName = ProperUtil.getProperty("dbName");
		String apiId = ProperUtil.getProperty("apiId");
		String name = ProperUtil.getProperty("name");
		String pwd = ProperUtil.getProperty("pwd");
		System.out.println("!!!!!!!!!!------host:"+host+" dbName"+dbName+" apiId"+apiId+" name"+name+" pwd"+pwd);
		Random rand = new Random();
		APIClient handler = new APIClient();
		int result=0;
		int connectRe = handler.init(host, name, pwd, apiId,dbName);
//		int connectRe = handler.init("10.10.1.12", "zhfwglpt", "zhfwglpt", "zhfwglpt","mas");
//		int connectRe = handler.init("10.10.1.12", "zhglfwpt", "zhglfwpt", "zhglfwpt","mas");
		if(connectRe == APIClient.IMAPI_SUCC){
			System.out.println("初始化成功");
//			 result=handler.sendSM(mobiles, content, rand.nextInt(99999999));
			 result=handler.sendSM(mobiles.split(","), content, 10, 10, "", "");
			if(result == APIClient.IMAPI_SUCC)
	        {            
	        	System.out.println("发送成功\n");
	        	handler.release();
//	        	Thread.currentThread().interrupt();
	        }
	        else if(result == APIClient.IMAPI_INIT_ERR){
	        	System.out.println("未初始化");
	        }
	        else if(result == APIClient.IMAPI_CONN_ERR){
	        	System.out.println("数据库连接失败");
	        }
	        else if(result == APIClient.IMAPI_DATA_ERR){
	        	System.out.println("参数错误");
	        }
	        else if(result == APIClient.IMAPI_DATA_TOOLONG){
	        	System.out.println("消息内容太长");
	        }
	        else if(result == APIClient.IMAPI_INS_ERR){
	        	System.out.println("数据库插入错误");
	        }
	        else{
	        	System.out.println("出现其他错误");
	        }
			}
        else if(connectRe == APIClient.IMAPI_CONN_ERR){
        	result=APIClient.IMAPI_CONN_ERR;
        	System.out.println("连接失败");
        	}
        else if(connectRe == APIClient.IMAPI_API_ERR){
        	result=APIClient.IMAPI_API_ERR;
        	System.out.println("apiID不存在");
        	}
		
		return result;
	}
	
	
}