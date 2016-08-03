package lmd.extend.wso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="AsfUploadbundles")
@Table(name="ASF_UPLOADBUNDLES")
public class AsfUploadbundles {//bundles上传信息存储
    @Id()
  	private String id;
    @Column(length=500)
  	private String name;
    @Column(length=500)
  	private String path;  
  	@Column(length=4000)
  	private String description;
	@Column(length=10)
  	private String isinstall; 
	@Column(length=20)
  	private String bundleid; 
	@Column(length=40)
  	private String bugid;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public String getDescription() {
		return description;
	}
	public String getIsinstall() {
		return isinstall;
	}
	public String getBundleid() {
		return bundleid;
	}
	public String getBugid() {
		return bugid;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setIsinstall(String isinstall) {
		this.isinstall = isinstall;
	}
	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}
	public void setBugid(String bugid) {
		this.bugid = bugid;
	} 
}