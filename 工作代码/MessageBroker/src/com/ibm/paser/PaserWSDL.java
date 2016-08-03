package com.ibm.paser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate.InterfaceService;
import com.ibm.hibernate.InterfaceServiceDAO;

public class PaserWSDL {
	
	private static final Logger log = LoggerFactory.getLogger(PaserWSDL.class);
	InterfaceServiceDAO dao = new InterfaceServiceDAO();
	
	public String paser(File wsdlFile, String servicename) {		
		SAXReader saxreader = new SAXReader();
		
		HashMap<String, String> xmlMap = new HashMap<String, String>();  
        xmlMap.put("wsdl", "http://schemas.xmlsoap.org/wsdl/");    
        
		try {
			Document document = saxreader.read(wsdlFile);
			List list = document.selectNodes("/wsdl:definitions/wsdl:portType/wsdl:operation");
			
            for(int i=0; i < list.size(); i++){                    
                Element operation = (Element)list.get(i);//ת��ΪElement  
                InterfaceService ifs = new InterfaceService();
                UUID uuid = UUID.randomUUID();
                ifs.setUuid(uuid.toString());
                ifs.setServicename(servicename);
                ifs.setOperation(operation.attributeValue("name"));
                ifs.setDescription("");
                ifs.setMappingname(operation.attributeValue("name"));
                try {
                	dao.save(ifs);
                } catch (Exception e) {
                	return "����operationʧ�ܣ�������!";
                }
            } 		
			            
		} catch (DocumentException e) {
			log.error("Pasering error", e);
			return "����WSDL�ļ�ʧ�ܣ������ԣ�";
		}
		
		return "Success";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PaserWSDL p = new PaserWSDL();
		p.paser(new File("C:/csb2agp.wsdl"), "");
	}
}
