package com.test.util;

import com.test.client.SmsManagerLocator;
import com.test.client.SmsManagerSoapBindingStub;

public class Test {

	public static void main(String[] args) {
		try {
//			ServiceImplServiceLocator iService = new ServiceImplServiceLocator();
//			ServiceImplServiceSoapBindingStub service = (ServiceImplServiceSoapBindingStub) iService.getServiceImplPort();
			SmsManagerLocator sms = new SmsManagerLocator();
			SmsManagerSoapBindingStub service = (SmsManagerSoapBindingStub) sms.getSmsManagerPort();
//			String status=service.sendNotify(receivers, title, msg, delayTime, arg4)fy("zhaoliang", "紧急通知测试", "紧急通知信息紧急通知信息紧急通知信息紧急通知信息", "0", "5000");
			String str=service.sendByLoginName("baijianqun", "dsfsdf");
			System.out.println(str+"  ---------------------");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
