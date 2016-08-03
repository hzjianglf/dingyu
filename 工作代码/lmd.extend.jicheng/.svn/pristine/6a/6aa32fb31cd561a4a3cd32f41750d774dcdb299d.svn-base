package lmd.extend.jicheng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class GetDaiBan {

	
	public static String getLoginjp(String username){
		String loginjp = username;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@10.10.10.207:1521:orcl";
			//Connection conn = new GetConnValue().getConnection();
			Connection conn = DriverManager.getConnection(url, "rc7", "11111111");
			Statement stmt = conn.createStatement();
			ResultSet rs =null;
			String sql="select staff_name from org_yusi_dy a,org_person b where a.sn=b.idnum and b.loginname='"+username+"'";	
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				loginjp=rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginjp;
	}

	
	
	
	public static ArrayList getShenpiAndDaibanx(String username){
		String shenpi = "0";
		String daibanx = "0";
		ArrayList list = new ArrayList();
		String value1 = "";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.8:8180/axis2/services/sdzwws?wsdl");
		Options options = new Options();
		options.setAction("getYWCount");//调用接口方法  
		options.setTo(endpointref);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://yw.com","");  
			OMElement method = fac.createOMElement("getYWCount", omNs);  
			OMElement name = fac.createOMElement("name", omNs);//设置入参名称  
			name.setText(username);//设置入参值 
			method.addChild(name);
			method.build();
			System.out.println("method：" + method.toString());// print:<getUserById xmlns="http://service.hsinghsu.com"><id>1</id></getUserById>  
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				OMElement ome1 = (OMElement)iterator.next();
				value1=ome1.getText();
			}
		}catch (AxisFault e){  
			 System.out.println("返回待办件数量错误！");
		}
		if(value1.indexOf("不存在")<0){
			try{
				String db[] = value1.split(",");
				if(db.length>=2){
					shenpi = Integer.toString(Integer.parseInt(db[0]));
					daibanx = Integer.toString(Integer.parseInt(db[1]));
				}
			}catch(Exception e){
				e.printStackTrace();
				shenpi = "0";
				daibanx = "0";
			}
		}
		list.add(shenpi);
		list.add(daibanx);
		return list;
	}
	
	public static String getJsydDb(String username){
		String value1 = "0";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.29/sdydbp_Data/getflowctrlcountByuseid.asmx?wsdl");
        
		Options options = new Options();
    	
		options.setAction("http://tempuri.org/GetFlowsCount");//调用接口方法  
		options.setTo(endpointref);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/GetFlowsCount","");  
			OMElement method = fac.createOMElement("GetFlowsCount", omNs);  
			OMElement userId = fac.createOMElement("userId", omNs);//设置入参名称  
			userId.setText(username);//设置入参值 
			method.addChild(userId);
			method.build();
			System.out.println("method：" + method.toString());// print:<getUserById xmlns="http://service.hsinghsu.com"><id>1</id></getUserById>  
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				System.out.println("aaaaaa");
				OMElement ome1 = (OMElement)iterator.next();
				System.out.println(ome1.getLocalName()+"--------"+ome1.getText());
				value1 = ome1.getText();
			}
			System.out.println("response:" + response);  
			if(value1==null){
                value1="0";
			}
			if(value1.indexOf(",")>0){
				value1 = value1.substring(0,value1.indexOf(","));
			}
			value1 = Integer.toString(Integer.parseInt(value1));
		}catch (AxisFault e){  
			System.out.println("建设用地错误！");
            value1 = "-1";
		}
		return value1;
	}
	
	public static String getGhbjDb(String username){
		String value2 = "0";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.29/IGetData/GetFlowCtrlCountByUseID.asmx?wsdl");
        
		Options options = new Options();
    	
		options.setAction("http://tempuri.org/GetFlowsWaitCount");//调用接口方法  
		options.setTo(endpointref);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/","");  
			OMElement method = fac.createOMElement("GetFlowsWaitCount", omNs);  
			OMElement identityKey = fac.createOMElement("identityKey", omNs);//设置入参名称  
			OMElement type = fac.createOMElement("type", omNs);//设置入参名称  
			identityKey.setText(username);//设置入参值 
			type.setText("DZZW");//设置入参值 
			method.addChild(identityKey);
			method.addChild(type);
			method.build();
			System.out.println("method：" + method.toString());// print:<getUserById xmlns="http://service.hsinghsu.com"><id>1</id></getUserById>  
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				System.out.println("aaaaaa");
				OMElement ome1 = (OMElement)iterator.next();
				System.out.println(ome1.getLocalName()+"--------"+ome1.getText());
				value2=ome1.getText();
			}
			System.out.println("response:" + response);  
			if(value2==null){
                value2="0";
			}
			
			System.out.println("value2----"+value2);
			
			value2 = Integer.toString(Integer.parseInt(value2));
		}catch (AxisFault e){  
			System.out.println("规划边界调整待办件获取错误!");
            value2 = "-1";
		}
		return value2;
	}
	public static String getXDzzwDb(String username){
		String value3 = "0";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.29/IGetData/GetFlowCtrlCountByUseID.asmx?wsdl");
        
		Options options = new Options();
    	
		options.setAction("http://tempuri.org/GetFlowsWaitCount");//调用接口方法  
		options.setTo(endpointref);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/","");  
			OMElement method = fac.createOMElement("GetFlowsWaitCount", omNs);  
			OMElement identityKey = fac.createOMElement("identityKey", omNs);//设置入参名称  
			OMElement type = fac.createOMElement("type", omNs);//设置入参名称  
			identityKey.setText(username);//设置入参值 
			type.setText("DZZW");//设置入参值 
			method.addChild(identityKey);
			method.addChild(type);
			method.build();
			System.out.println("method：" + method.toString());// print:<getUserById xmlns="http://service.hsinghsu.com"><id>1</id></getUserById>  
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				System.out.println("aaaaaa");
				OMElement ome1 = (OMElement)iterator.next();
				System.out.println(ome1.getLocalName()+"--------"+ome1.getText());
				value3=ome1.getText();
			}
			System.out.println("response:" + response);  
			
		}catch (AxisFault e){  
			 System.out.println("新电子政务系统错误！");
			 value3 = "-1";
		}
		return value3;
	}
	public static String getGdbhDb(String username){
		String value4 = "0";
		EndpointReference endpointref4 = new EndpointReference("http://10.10.10.135/Service/OrganizationalStructure.asmx?wsdl");
		Options options = new Options();
		options.setAction("http://tempuri.org/GetEmpWorksNum");//调用接口方法  
		options.setTo(endpointref4);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/","");  
			OMElement method = fac.createOMElement("GetEmpWorksNum", omNs);  
			OMElement empno = fac.createOMElement("empno", omNs);//设置入参名称  
			empno.setText(username);//设置入参值 
			method.addChild(empno);
			method.build();
			System.out.println("method：" + method.toString());// print:<getUserById xmlns="http://service.hsinghsu.com"><id>1</id></getUserById>  
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				System.out.println("aaaaaa");
				OMElement ome1 = (OMElement)iterator.next();
				System.out.println(ome1.getLocalName()+"--------"+ome1.getText());
				value4=ome1.getText();
			}
			System.out.println("response:" + response);  
			if(value4==null){
                value4="0";
			}
			value4 = Integer.toString(Integer.parseInt(value4));
			
		}catch (AxisFault e){  
			 System.out.println("测绘院代办事项错误！");
	            value4 = "-1";
		}
		return value4;
	}
	
	public static String getZjggDb(String username){
		String value5 = "0";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.29/sdzjggbp_Data/getflowctrlcountByuseid.asmx?wsdl");
		Options options = new Options();
		options.setAction("http://tempuri.org/GetFlowsCount");//调用接口方法  
		options.setTo(endpointref);
		ServiceClient sender = null;
		try {
			sender = new ServiceClient();
			sender.setOptions(options);  
			OMFactory fac = OMAbstractFactory.getOMFactory();  
			OMNamespace omNs = fac.createOMNamespace("http://tempuri.org/","");  
			OMElement method = fac.createOMElement("GetFlowsCount", omNs);  
			OMElement userId = fac.createOMElement("userId", omNs);//设置入参名称  
			userId.setText(username);//设置入参值 
			method.addChild(userId);
			method.build();
			OMElement response = sender.sendReceive(method);  
			Iterator iterator = response.getChildElements();
			if(iterator.hasNext()){
				OMElement ome1 = (OMElement)iterator.next();
				value5=ome1.getText();
			}
			if(value5==null||value5.indexOf("不存在此用户！")>=0){
                value5="0";
			}
			value5 = Integer.toString(Integer.parseInt(value5));
		}catch (AxisFault e){  
			System.out.println("增加挂钩错误！");
            value5 = "-1";
		}
		return value5;
	}
	public static void main(String []args) {
		System.out.println(getShenpiAndDaibanx("zhenggan1"));
	}
}
