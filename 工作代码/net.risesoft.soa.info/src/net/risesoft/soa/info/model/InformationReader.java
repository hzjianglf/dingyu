package net.risesoft.soa.info.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="InformationReader")
@Table(name="info_main_reader")
public class InformationReader
{

  @Id
  private String id;
  private String userID;
  private String userName;
  private String departmentName;
  private String departmentDN;
  private String userIP;
  private Date readTime;
  private String instance;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserID() {
    return this.userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDepartmentName() {
    return this.departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getDepartmentDN() {
    return this.departmentDN;
  }

  public void setDepartmentDN(String departmentDN) {
    this.departmentDN = departmentDN;
  }

  public String getUserIP() {
    return this.userIP;
  }

  public void setUserIP(String userIP) {
    this.userIP = userIP;
  }

  public Date getReadTime() {
    return this.readTime;
  }

  public void setReadTime(Date readTime) {
    this.readTime = readTime;
  }

  public String getInstance() {
    return this.instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }
}