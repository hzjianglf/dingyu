package lmd.extend.wso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="MonitorMessFlow")
@Table(name="MONITOR_MESSAGEFLOW")
public class MonitorMessFlow 
{
  private static final long serialVersionUID = 4618202183561878283L;

  @Id()
  private String monitorid;
//  @Basic(optional = false)
  private String remoteip;
//  @Basic(optional = false)
  private String wsurl;  
  @Column(columnDefinition="timestamp default sysdate")
//  @Temporal(TemporalType.TIMESTAMP) 
  private Date startdate;
  @Column(columnDefinition="timestamp default sysdate")
//@Temporal(TemporalType.TIMESTAMP) 
  private Date enddate;
  private String attribute; //属于哪个系统的服务
  private String resstatus; //返回状态500服务器端错误，200成功
  private String requesting;  //权限控制
	public String getMonitorid() {
		return monitorid;
	}
	
	public String getRemoteip() {
		return remoteip;
	}
	
	public String getWsurl() {
		return wsurl;
	}
	
		
	public void setMonitorid(String monitorid) {
		this.monitorid = monitorid;
	}
	
	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}
	
	public void setWsurl(String wsurl) {
		this.wsurl = wsurl;
	}
	

	public Date getStartdate() {
		return startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getResstatus() {
		return resstatus;
	}

	public void setResstatus(String resstatus) {
		this.resstatus = resstatus;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getRequesting() {
		return requesting;
	}

	public void setRequesting(String requesting) {
		this.requesting = requesting;
	}


}