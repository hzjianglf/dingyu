package Demo;

//import javax.xml.ws.EndpointReference;
//import org.apache.axiom.om.OMAbstractFactory;
//import org.apache.axiom.om.OMElement;
//import org.apache.axiom.om.OMFactory;
//import org.apache.axiom.om.OMNamespace;
//import org.apache.axis2.AxisFault;
//import org.apache.axis2.client.ServiceClient;
//
//import com.sun.tools.jxc.apt.Options;

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
public class AsixBlockClient {
	private static EndpointReference targetEPR = new EndpointReference("http://10.10.10.108:8280/services/ceshi01?wsdl");//接口地址  
    
	public static void main(String[] args){
		System.out.println(getShenpiAndDaibanx("admin"));
		
	} 
//     public
	
	public static String getXDzzwDb(String username){
		String value3 = "0";
		EndpointReference endpointref = new EndpointReference("http://10.10.10.29/IGetData/GetFlowCtrlCountByUseID.asmx?wsdl");
        
		Options options = new Options();
    	
		options.setAction("GetFlowsWaitCount");//调用接口方法  
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
			}
			System.out.println("response:" + response);  
			
		}catch (AxisFault e){  
			 System.out.println("新电子政务系统错误！");
			 value3 = "-1";
		}
		return value3;
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
	
	
	
     
}
