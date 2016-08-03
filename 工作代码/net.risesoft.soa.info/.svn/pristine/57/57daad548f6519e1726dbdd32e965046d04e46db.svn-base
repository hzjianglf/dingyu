package net.risesoft.soa.info.manager.template;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ShowTemplate
{
  private List<String> temp;

  public Document transform(Document document, List<String> formNameList)
    throws Exception
  {
    this.temp = new ArrayList();
    Elements shows = document.getElementsByAttribute("isHidden");
    for (Element element : shows) {
      element.remove();
    }
    Elements scripts = document.getElementsByTag("script");
    for (Element script : scripts) {
      String remove = script.attr("remove");
      if (!("true".equals(remove))) {
        script.remove();
      }
    }
    Object heads = document.getElementsByTag("head");
    if (((List)heads).size() == 0) {
      document.appendElement("head");
    }
    Elements elements = document.getAllElements();
    for (Element element : elements) {
      String tagName = element.tagName();

      if (tagName.equals("input")) {
        InputTag.showTemplate(element, this.temp, formNameList);
      }
      if (tagName.equals("textarea")) {
        TextAreaTag.showTemplate(element, formNameList);
      }
      if (tagName.equals("select")) {
        SelectTag.showTemplate(element, formNameList);
      }
    }
    return ((Document)(Document)document);
  }
}