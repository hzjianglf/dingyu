package net.risesoft.soa.info.manager.template;

import java.util.List;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FormTag
{
  public static void template(Element form, List<String> formNameList)
    throws Exception
  {
    String id = form.attr("id");
    if (id == "") {
      id = "form1";
      form.attributes().put("id", "form1");
    }
    Element script = form.parent().appendElement("script");
    script.attributes().put("type", "text/javascript");

    script.appendText("jQuery('#" + id + "').validationEngine();");

    Element hidden = form.appendElement("input");
    hidden.attr("type", "hidden");
    hidden.attr("value", "${id}");
    hidden.attr("name", "id");
    hidden = form.appendElement("input");
    hidden.attr("type", "hidden");
    hidden.attr("value", "${infoID}");
    hidden.attr("name", "infoID");

    Elements elements = form.getAllElements();
    for (Element element : elements) {
      element.attributes().remove("hidden");
      String tagName = element.tagName();
      if (tagName.equals("input")) {
        InputTag.template(element, form, formNameList);
      }
      if (tagName.equals("textarea"))
        TextAreaTag.template(element, formNameList);
    }
  }
}