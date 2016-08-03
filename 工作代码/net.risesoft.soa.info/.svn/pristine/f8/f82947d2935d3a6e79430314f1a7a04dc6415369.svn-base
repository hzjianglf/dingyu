package net.risesoft.soa.info.manager.template;

import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

public class SelectTag
{
  public static void showTemplate(Element element, List<String> formNameList)
    throws Exception
  {
    String name = element.attr("name");
    if (formNameList.contains(name))
      element.replaceWith(new TextNode("${" + name + "}", ""));
    else
      element.replaceWith(new TextNode("", ""));
  }
}