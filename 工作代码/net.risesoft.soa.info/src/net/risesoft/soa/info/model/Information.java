package net.risesoft.soa.info.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Information")
@Table(name="info_main_define")
public class Information
  implements Serializable
{
  private static final long serialVersionUID = -6455909472632662894L;

  @Id
  private String id;
  private String dn;
  private String name;
  private String icon;
  private String url;
  private String tableName;
  private String titleField;
  private String sortField;
  private String module;
  private String infoType;
  private String showStyle;
  private String listShowStyle;
  private int pageSize = 10;

  @Lob
  private byte[] template;

  @Lob
  private byte[] editTemplate;

  @Lob
  private byte[] showTemplate;

  @Transient
  private List<InformationDesc> elements;

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDn() {
    return this.dn;
  }

  public void setDn(String dn) {
    this.dn = dn;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return this.icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTableName() {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTitleField() {
    return this.titleField;
  }

  public void setTitleField(String titleField) {
    this.titleField = titleField;
  }

  public String getSortField() {
    return this.sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public String getModule() {
    return this.module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getInfoType() {
    return this.infoType;
  }

  public void setInfoType(String infoType) {
    this.infoType = infoType;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public byte[] getTemplate() {
    return this.template;
  }

  public void setTemplate(byte[] template) {
    this.template = template;
  }

  public byte[] getEditTemplate() {
    return this.editTemplate;
  }

  public void setEditTemplate(byte[] editTemplate) {
    this.editTemplate = editTemplate;
  }

  public byte[] getShowTemplate() {
    return this.showTemplate;
  }

  public void setShowTemplate(byte[] showTemplate) {
    this.showTemplate = showTemplate;
  }

  public List<InformationDesc> getElements() {
    return this.elements;
  }

  public void setElements(List<InformationDesc> elements) {
    this.elements = elements;
  }

  public String getShowStyle() {
    return this.showStyle;
  }

  public void setShowStyle(String showStyle) {
    this.showStyle = showStyle;
  }

  public String getListShowStyle() {
    return this.listShowStyle;
  }

  public void setListShowStyle(String listShowStyle) {
    this.listShowStyle = listShowStyle;
  }
}