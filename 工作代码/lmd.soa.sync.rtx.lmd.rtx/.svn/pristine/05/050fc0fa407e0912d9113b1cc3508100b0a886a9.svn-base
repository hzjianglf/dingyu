package com.lmd.sync.rtx;

import com.lmd.sync.rtx.service.client.RtxServiceImplServiceLocator;
import com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub;

public class Test {

	public static void main(String[] args) {
		try {
//			ServiceImplServiceLocator iService = new ServiceImplServiceLocator();
//			ServiceImplServiceSoapBindingStub service = (ServiceImplServiceSoapBindingStub) iService.getServiceImplPort();
			RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
			RtxServiceImplServiceSoapBindingStub service = (RtxServiceImplServiceSoapBindingStub) rs.getRtxServiceImplPort();
//			String status=service.sendNotify(receivers, title, msg, delayTime, arg4)fy("zhaoliang", "紧急通知测试", "紧急通知信息紧急通知信息紧急通知信息紧急通知信息", "0", "5000");
			String status=service.addUser("zhaoliangqq", "0", "赵亮", "111111");
			System.out.println(status+"  ---------------------");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
