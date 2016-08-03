package lmd.jcg.gsm.util;


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
		
		
		return 1;
	}
	
	
}