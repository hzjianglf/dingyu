package net.risesoft.soa.info.manager;

import java.io.StringWriter;
import net.risesoft.soa.framework.util.json.JSONException;
import net.risesoft.soa.framework.util.json.JSONWriter;

public class MenuItem
{
  private JSONWriter json;
  private StringWriter stringWriter;

  public MenuItem(JSONWriter json, StringWriter stringWriter)
  {
    this.json = json;
    this.stringWriter = stringWriter;
  }

  public void addMenuItem(String text) {
    try {
      if ((text != null) && (text.length() > 0))
        this.json.value(text);
    }
    catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void addMenuItem(String id, String text, String tooltip, String iconCls, String handler) {
    try {
      this.json.object();
      if ((id != null) && (id.length() > 0)) {
        this.json.key("id").value(id);
      }
      if ((text != null) && (text.length() > 0)) {
        this.json.key("text").value(text);
      }
      if ((tooltip != null) && (tooltip.length() > 0)) {
        this.json.key("tooltip").value(tooltip);
      }
      if ((iconCls != null) && (iconCls.length() > 0)) {
        this.json.key("iconCls").value(iconCls);
      }
      if ((handler != null) && (handler.length() > 0)) {
        this.stringWriter.append(",\"handler\":" + handler);
      }
      this.json.endObject();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void addMenuItem(String text, String iconCls, String[][] menuItems) {
    try {
      this.json.object();
      if ((text != null) && (text.length() > 0)) {
        this.json.key("text").value(text);
      }
      if ((iconCls != null) && (iconCls.length() > 0)) {
        this.json.key("iconCls").value(iconCls);
      }
      this.json.key("menu").array();
      for (String[] menuItem : menuItems) {
        this.json.object();
        if ((menuItem[0] != null) && (menuItem[0].length() > 0)) {
          this.json.key("id").value(menuItem[0]);
        }
        if ((menuItem[1] != null) && (menuItem[1].length() > 0)) {
          this.json.key("text").value(menuItem[1]);
        }
        if ((menuItem[2] != null) && (menuItem[2].length() > 0)) {
          this.json.key("tooltip").value(menuItem[2]);
        }
        if ((menuItem[3] != null) && (menuItem[3].length() > 0)) {
          this.json.key("iconCls").value(menuItem[3]);
        }
        if ((menuItem[4] != null) && (menuItem[4].length() > 0)) {
          this.stringWriter.append(",\"handler\":" + menuItem[4]);
        }
        this.json.endObject();
      }
      this.json.endArray();
      this.json.endObject();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}