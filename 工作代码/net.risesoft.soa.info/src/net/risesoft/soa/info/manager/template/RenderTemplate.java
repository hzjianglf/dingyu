package net.risesoft.soa.info.manager.template;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.risesoft.soa.info.model.Information;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RenderTemplate
{
  public static byte[] create(Information information)
    throws Exception
  {
    byte[] template = null;
    try {
      Document doc = Jsoup.parse(new ByteArrayInputStream(information.getEditTemplate()), "UTF-8", "");
      List heads = doc.getElementsByTag("head");
      HeadTag.template((Element)heads.get(0));
      Elements divs = doc.getElementsByAttributeValue("id", "infoHtmlDiv");
      for (Element element : divs) {
        String tagName = element.tagName();
        if (tagName.equals("div")) {
          String type = element.attr("type");
          if (type == "") {
            type = "all";
          }
          Element script = element.parent().appendElement("script");
          script.attributes().put("type", "text/javascript");
          String width = element.attr("width");
          if (width == "")
            script.appendText("jQuery('#infoHtmlDiv').load('/info/display/htmlEdit.jsp',{'id':'${id}','infoID':'${infoID}','type':'" + type + "','operation':'create'});");
          else {
            script.appendText("jQuery('#infoHtmlDiv').load('/info/display/htmlEdit.jsp',{'id':'${id}','infoID':'${infoID}','type':'" + type + "','width':'" + width + "','operation':'create'});");
          }
        }
      }
      template = doc.html().getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return template;
  }

  public static byte[] update(Information information, Map<String, String> root) throws Exception {
    byte[] template = null;
    try {
      Document doc = Jsoup.parse(new ByteArrayInputStream(information.getEditTemplate()), "UTF-8", "");
      List heads = doc.getElementsByTag("head");
      HeadTag.template((Element)heads.get(0));
      Elements divs = doc.getElementsByAttributeValue("id", "infoHtmlDiv");
      String tagName;
      for (Element element : divs) {
        tagName = element.tagName(); 
        if (tagName.equals("div")) {
          Element script = element.parent().appendElement("script");
          script.attributes().put("type", "text/javascript");
          script.appendText("jQuery('#infoHtmlDiv').load('/info/display/htmlEdit.jsp',{'id':'${id}','infoID':'${infoID}','type':'${infoHtmlType}','operation':'edit'});");
        }
      }
      Elements elements = doc.getElementsByAttributeValue("type", "radio");
      String name;
      for (Element element : elements) {
        element.removeAttr("checked");
        name = element.attr("name");
        String value = element.attr("value");
        if (value.equals(root.get(name))) {
          element.attributes().put("checked", "true");
        }
      }
      Elements checks = doc.getElementsByAttributeValue("type", "checkbox");
      for (Element element : checks) {
        element.removeAttr("checked");
        name = element.attr("name");
        String value = element.attr("value");
        if (value.equals(root.get(name))) {
          element.attributes().put("checked", "true");
        }
      }
      Elements selects = doc.getElementsByTag("select");
      Iterator localIterator2;
      for (Iterator<Element> name1 = selects.iterator(); name1.hasNext(); 
        localIterator2.hasNext())
      {
        Element element = (Element)name1.next();
        String name11 = element.attr("name"); 
        List options = element.getElementsByTag("option");
        localIterator2 = options.iterator(); continue;
      }

      template = doc.html().getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return template;
  }

  public static byte[] display(Information information, Map<String, String> root) throws Exception {
    byte[] template = null;
    try {
      Document doc = Jsoup.parse(new ByteArrayInputStream(information.getShowTemplate()), "UTF-8", "");
      List heads = doc.getElementsByTag("head");
      HeadTag.showTemplate((Element)heads.get(0));
      Elements divs = doc.getElementsByAttributeValue("id", "infoHtmlDiv");
      for (Element element : divs) {
        String tagName = element.tagName();
        if (tagName.equals("div")) {
          Element script = element.parent().appendElement("script");
          script.attributes().put("type", "text/javascript");
          script.appendText("jQuery('#infoHtmlDiv').load('/info/display/htmlDisplay.jsp',{'id':'${id}','infoID':'${infoID}','type':'${infoHtmlType}'});");
        }
      }
      template = doc.html().getBytes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return template;
  }
}