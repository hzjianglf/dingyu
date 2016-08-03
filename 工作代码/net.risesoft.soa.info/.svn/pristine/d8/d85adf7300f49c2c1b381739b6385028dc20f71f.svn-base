package net.risesoft.soa.info.manager.template;

import java.util.List;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class TextAreaTag
{
  public static void template(Element element, List<String> formNameList)
    throws Exception
  {
    String name = element.attr("name");
    if (name == "") {
      throw new Exception("INPUT元素缺少name属性!");
    }
    if (formNameList.contains(name)) {
      element.append("${" + name + "}");
    }
    String position = element.attr("data-prompt-position");
    if (position == "")
      element.attributes().put("data-prompt-position", "topRight:-70");
  }

  public static void showTemplate(Element element, List<String> formNameList) throws Exception
  {
    String name = element.attr("name");
    if (formNameList.contains(name))
      element.replaceWith(new TextNode("${" + name + "}", ""));
    else
      element.replaceWith(new TextNode("", ""));
  }
}