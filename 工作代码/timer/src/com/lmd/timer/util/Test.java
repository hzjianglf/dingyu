package com.lmd.timer.util;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import test.QueryManagerLocator;
import test.QueryManagerSoapBindingStub;

public class Test {

	
	public static void main(String[] args) throws InterruptedException {
     
		
		
		for(int i=0;i<300;i++){
			Thread t = new Thread();
			t.sleep(1000);
			String sql="select count(*) count from info_main_ggmb";
			try {
//				GsmManagerLocator gs = new GsmManagerLocator();
//				GsmManagerSoapBindingStub service = (GsmManagerSoapBindingStub) gs.getGsmManagerPort();
//				smsStatus=service.sendByLoginName(username, content);
				QueryManagerLocator qm = new QueryManagerLocator();
				QueryManagerSoapBindingStub service = (QueryManagerSoapBindingStub) qm.getQueryManagerPort();
				String str = service.queryBySql(sql);
				System.out.println(str);
			} catch (ServiceException e) {
				System.out.println("短信发送消息失败");
				e.printStackTrace();
			}catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	
	}
	
}
