package net.risesoft.soa.info.manager.template;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class HeadTag
{
  public static void template(Element element)
    throws Exception
  {
    Element link = element.appendElement("link");

    link.attributes().put("type", "text/css");
    link.attributes().put("href", "/jquery/css/validationEngine.jquery.css");
    link.attributes().put("rel", "stylesheet");
    Element script = element.appendElement("script");

    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/js/jquery.form.js");

    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/js/jquery-ui-timepicker-addon.js");
    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/js/jquery.validationEngine.js");
    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/languages/jquery-ui-datepicker-zh-CN.js");
    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/languages/jquery-ui-timepicker-zh-CN.js");
    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    script.attributes().put("src", "/jquery/languages/jquery.validationEngine-zh_CN.js");
    script = element.appendElement("script");
    script.attributes().put("type", "text/javascript");
    StringBuffer sb = new StringBuffer();
    sb.append("jQuery('#form1').validationEngine();");
    sb.append("var options = {");

    sb.append("  success:function (data)  {");
    sb.append("    var success = data.success;");
    sb.append("    if (success){$('#message_success_div').html(data.message);$('#dialog_success_message').dialog('open');returnHome();}else{$('#message_cancel_div').html(data.message);$('#dialog_cancel_message').dialog('open');}");
    sb.append("  },");
    sb.append("  url:'/info/infoSave.action',");
    sb.append("  type:'post',");
    sb.append("  dataType:'json'");
    sb.append("};");
    sb.append("$(document).ready(function() {$('#form1').ajaxForm(options);});");
    sb.append("function infoFormSubmit(){if (CKEDITOR.instances['infoHtml']){$('#infoHtml').attr('value',CKEDITOR.instances['infoHtml'].getData());};$('#form1').submit();}");
    script.appendText(sb.toString());
  }

  public static void showTemplate(Element element)
    throws Exception
  {
  }
}