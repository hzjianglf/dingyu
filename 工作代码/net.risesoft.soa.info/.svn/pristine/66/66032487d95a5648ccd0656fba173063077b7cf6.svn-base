package net.risesoft.soa.info.manager.template;

import java.util.List;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class InputTag
{
  public static void template(Element element, Element form, List<String> formNameList)
    throws Exception
  {
    String name = element.attr("name");
    String id = element.attr("id");
    String type = element.attr("type");
    if ((type.equals("date")) || (type.equals("dateTime")) || (type.equals("time"))) {
      if (name == "") {
        throw new Exception("INPUT元素缺少name属性!");
      }
      if (id == "") {
        element.attributes().put("id", name);
      }
      String dateFormat = element.attr("dateFormat");
      if (dateFormat.length() > 0)
      {
        Element script;
        if (type.equals("date")) {
          script = form.parent().appendElement("script");
          script.attributes().put("type", "text/javascript");
          dateFormat = dateFormat.replaceAll("yyyy", "yy");
          dateFormat = dateFormat.replaceAll("MM", "mm");
          script.appendText("$('#" + name + "').datepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, dateFormat: '" + dateFormat + "'});");
        }
        if (type.equals("dateTime")) {
          script = form.parent().appendElement("script");
          script.attributes().put("type", "text/javascript");
          dateFormat = dateFormat.replaceAll("yyyy", "yy");
          dateFormat = dateFormat.replaceAll("MM", "mm");
          String timeFormat = element.attr("timeFormat");
          timeFormat = timeFormat.replaceAll("HH", "hh");
          if (timeFormat.indexOf("ss") > 0)
            script.appendText("$('#" + name + "').datetimepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, minuteGrid: 10, secondGrid:10, showSecond: true, dateFormat: '" + dateFormat + "', timeFormat: '" + timeFormat + "'});");
          else {
            script.appendText("$('#" + name + "').datetimepicker({showButtonPanel: true, showWeek: true, numberOfMonths: 2, showOtherMonths: true, selectOtherMonths: true, minuteGrid: 10, dateFormat: '" + dateFormat + "', timeFormat: '" + timeFormat + "'});");
          }
        }
      }
      if (formNameList.contains(name)) {
        element.removeAttr("value");
        element.attr("value", "${" + name + "}");
      }
    } else if ((((type.equals("text")) || (type.equals("")) || (type.equals("password")))) && 
      (formNameList.contains(name))) {
      element.removeAttr("value");
      element.attr("value", "${" + name + "}");
    }

    String position = element.attr("data-prompt-position");
    if (position == "")
      element.attributes().put("data-prompt-position", "topRight:-70");
  }

  public static void showTemplate(Element element, List<String> temp, List<String> formNameList) throws Exception
  {
    String name = element.attr("name");
    String type = element.attr("type");
    if ((type.equals("text")) || (type.equals("")) || (type.equals("password"))) {
      if (name == "") {
        throw new Exception("INPUT元素缺少name属性!");
      }
      if (formNameList.contains(name))
        element.replaceWith(new TextNode("${" + name + "}", ""));
      else
        element.replaceWith(new TextNode("", ""));
    }
    else if ((type.equals("radio")) || (type.equals("checkbox"))) {
      if (name == "")
        throw new Exception("INPUT元素缺少name属性!");
      Node node;
      if (temp.contains(name)) {
        node = element.nextSibling();
        if (node instanceof TextNode) {
          node.remove();
        }
        element.remove();
      } else {
        node = element.nextSibling();
        if (node instanceof TextNode) {
          node.remove();
        }
        if (formNameList.contains(name))
          element.replaceWith(new TextNode("${" + name + "}", ""));
        else {
          element.replaceWith(new TextNode("", ""));
        }
        temp.add(name);
      }
    } else if ((type.equals("date")) || (type.equals("dateTime")) || (type.equals("time"))) {
      if (formNameList.contains(name))
        element.replaceWith(new TextNode("${" + name + "}", ""));
      else
        element.replaceWith(new TextNode("", ""));
    }
    else {
      element.remove();
    }
  }
}