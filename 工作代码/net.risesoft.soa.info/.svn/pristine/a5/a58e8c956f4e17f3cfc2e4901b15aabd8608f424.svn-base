package net.risesoft.soa.info.manager.template;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Template
{
  public Document transform(Document document, List<String> formNameList)
    throws Exception
  {
    List forms = document.getElementsByTag("form");
    if (forms.size() == 0) {
      throw new Exception("需要FORM元素!");
    }
    if (forms.size() > 1) {
      throw new Exception("FORM元素只能一个!");
    }
    List heads = document.getElementsByTag("head");
    if (heads.size() == 0) {
      document.appendElement("head");
    }

    Element form = document.getElementsByTag("form").get(0);
    FormTag.template(form, formNameList);
    return document;
  }
}