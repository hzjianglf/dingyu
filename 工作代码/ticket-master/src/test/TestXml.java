package test;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TestXml {

public static void main(String[] args) {
	String xml="<response>" +
			"<head>" +
			"<code>0</code>" +
			"<message></message>" +
			"</head>" +
			"<body>" +
			"<test>" +
			"<no>1</no>" +
			"<addr>123</addr>" +
			"</test>" +
			"<test>" +
			"<no>2</no>" +
			"<addr>124</addr>" +
			"</test>" +
			"</body>" +
			"</response>";
	try {
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> dataList = root
				.selectNodes("//body/test");
		for (Element e2 : dataList) {
			String no=e2.elementText("no");
			String addr=e2.elementText("addr");
			System.out.println(no+"     "+addr);
		}
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

