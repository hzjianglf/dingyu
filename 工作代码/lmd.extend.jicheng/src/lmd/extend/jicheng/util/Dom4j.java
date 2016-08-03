package lmd.extend.jicheng.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4j {
 
	
	public ArrayList<Map<String, String>> test(String xml,String[] sysId){
		  
		ArrayList<Map<String, String>> array = new ArrayList<Map<String, String>>();
		try {
				
			// 创建saxReader对象
			SAXReader reader = new SAXReader();
			// 通过read方法读取一个文件 转换成Document对象
			//解析xml文件
			//Document document = reader.read(new File("src/dom4j/sida.xml"));
			//解析xml字符串
			Document document = DocumentHelper.parseText(xml);   
			//获取根节点元素对象
			Element node = document.getRootElement();
			
			Iterator<Element> its = node.elementIterator(); 

			int num_i = 0;
			
			//while (its.hasNext()&&num_i<num) {//遍历二级节点
			while (its.hasNext()) {//遍历二级节点
				num_i++; 
				Map<String, String> map = new HashMap<String, String>();
				node = its.next(); 
				 
				System.out.println(node.getText()); 

				Iterator<Element> it = node.elementIterator();
				// 遍历子节点
				while (it.hasNext()) {
					// 获取某个子节点对象
					Element element = it.next();  

					for (int i = 0; i < sysId.length; i++) {
						
						if (element.getName().equals(sysId[i])) {
							System.out.println(sysId[i]+":"+element.getText());
							map.put(sysId[i], element.getText());
						} 
					}
				}
				array.add(map);
			}
			
			//遍历所有的元素节点
			//xml2 = listNodes(node,xml2);  
			
		} catch (Exception e) {
			 System.out.println("error:"+e.getMessage());
			 e.printStackTrace();
		}
		return array;
	}
 
public static void main(String[] args) {
	
	try {
		new Dom4j().test("<root><mail><mid>1tbiAQAHCVaPNWoABgAAsP</mid><msid>1</msid><fid>1</fid><flag>1073742040</flag><from>postmaster@sddlr.gov.cn</from><to>foo@sddlr.gov.cn</to><subject>欢迎使用Coremail电子邮件系统/Welcome to the Coremail e-mail system</subject><size>51755</size><date>2016-01-27 09:53:58</date></mail><mail><mid>1tbiAQAHCVaPNWoABgABsO</mid><msid>1</msid><fid>1</fid><flag>24</flag><from>\"冰·若释·飞语\" &lt;811204266@qq.com&gt;</from><to>\"foo\" &lt;foo@sddlr.gov.cn&gt;</to><subject>11212</subject><size>3121</size><date>2016-01-27 09:18:48</date></mail></root>",new String[10]);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}