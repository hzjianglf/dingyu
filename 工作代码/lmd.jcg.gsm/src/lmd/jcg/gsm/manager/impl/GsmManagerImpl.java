package lmd.jcg.gsm.manager.impl;

import lmd.jcg.gsm.manager.GsmManager;
import lmd.jcg.gsm.util.PostTool;

import org.springframework.stereotype.Service;

import egov.appservice.asf.service.ServiceClient;
import egov.appservice.asf.service.ServiceClientFactory;
import egov.appservice.org.model.Person;
import egov.appservice.org.service.PersonManager;
@Service("gsmManager")
public class GsmManagerImpl implements GsmManager{
	@Override
	public String sendByLoginName(String username,String content) {
		// TODO 自动生成的方法存根
		String[] usernameary=null;
		String returnStr="";
		String mobileStr="";
		if(username.length()!=0){
			 usernameary = username.split(",");
		}
		 //System.out.println("1111ooooooooooooooooooo");
		  ServiceClient sc = ServiceClientFactory.getServiceClient();
		  String mobile=new String();
		    try {
		      PersonManager pm = (PersonManager)sc.getServiceByName("org.PersonManager");
		     boolean bnfs=false;//true 时不能发送
		      for(int i=0;i<usernameary.length;i++){
		    	  //System.out.println(i+"   1111ooooooooooooooooooo");
		    	  Person person =  pm.getPersonByLoginName(usernameary[i]);
			      if(null!=person){
			    	  mobile = person.getMobile();
//				      System.out.println(mobile+"  ooooooooooooooooooo");
				      if("".equals(mobile)||null==mobile||"null".equals(mobile)){
				    	  returnStr+=usernameary[i]+":0" ; //表示用户手机号码不存在或错误
				    	  bnfs=true;
				    	  break;
				      }else{
//				    	  returnStr+=usernameary[i]+":1,";  //表示发送成功
				    	  mobileStr+=mobile+",";
				      }  
			      }else{
			    	  returnStr+=usernameary[i]+":-1" ; //表示用户不存在
			    	  bnfs=true;
			    	 break;
			      }  
		      }
//		      returnStr = changeStr(returnStr);
		      mobileStr = changeStr(mobileStr);
		     //System.out.println(mobileStr);
		      //发送短信开始
		     if(bnfs){//是true就不能发送
		    	 
		     }else{
		    	 int result=PostTool.sendSm(mobileStr, content);
			      if(1==result){
			    	  returnStr="1";//发送成功
			      }else{
			    	  returnStr=result+"";
			      } 
		     }
		      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return returnStr;
	}

	@Override
	public String sendByMobile(String mobiles, String content) {
		// TODO 自动生成的方法存根
		String returnStr="";
		int result=PostTool.sendSm(mobiles, content);
	      if(1==result){
	    	  returnStr="1";//发送成功
	      }else{
	    	  returnStr=result+"";
	      } 
		return returnStr;
	}
	//如果最后一个字符是逗号  就去掉
	public static String changeStr(String str){
		if(",".equals(str.subSequence(str.length()-1,str.length()))){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	

}
