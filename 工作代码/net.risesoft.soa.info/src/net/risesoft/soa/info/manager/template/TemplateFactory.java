package net.risesoft.soa.info.manager.template;

public class TemplateFactory
{
  private static Template template;
  private static ShowTemplate showTemplate;

  public static Template getTemplate()
  {
    template = new Template();
    return template;
  }

  public static ShowTemplate getShowTemplate() {
    showTemplate = new ShowTemplate();
    return showTemplate;
  }
}