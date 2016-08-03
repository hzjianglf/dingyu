package net.risesoft.soa.info.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity(name="InformationFile")
@Table(name="info_main_file")
public class InformationFile
  implements Serializable
{
  private static final long serialVersionUID = 1847292205002283452L;

  @Id
  private String id;
  private String fileName;
  private String fileNameExt;
  private String title;
  private int version;
  private String module;
  private String instanceID;
  private String infoID;
  private String creater;
  private String createrName;
  private Date createDateTime;
  private Date updateDateTime;
  private String realPath;
  private String fileType;
  private long fileLength;
  private int tabIndex;
  private int status;

  @Lob
  private byte[] content;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileNameExt() {
    return this.fileNameExt;
  }

  public void setFileNameExt(String fileNameExt) {
    this.fileNameExt = fileNameExt;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getModule() {
    return this.module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getInstanceID() {
    return this.instanceID;
  }

  public void setInstanceID(String instanceID) {
    this.instanceID = instanceID;
  }

  public String getInfoID() {
    return this.infoID;
  }

  public void setInfoID(String infoID) {
    this.infoID = infoID;
  }

  public String getCreater() {
    return this.creater;
  }

  public void setCreater(String creater) {
    this.creater = creater;
  }

  public String getCreaterName() {
    return this.createrName;
  }

  public void setCreaterName(String createrName) {
    this.createrName = createrName;
  }

  public Date getCreateDateTime() {
    return this.createDateTime;
  }

  public void setCreateDateTime(Date createDateTime) {
    this.createDateTime = createDateTime;
  }

  public Date getUpdateDateTime() {
    return this.updateDateTime;
  }

  public void setUpdateDateTime(Date updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public String getRealPath() {
    if (this.realPath != null) {
      char pathFile = File.separatorChar;
      String tmp = this.realPath.replace('\\', pathFile);
      tmp = tmp.replace('/', pathFile);
      return tmp;
    }
    return this.realPath;
  }

  public void setRealPath(String realPath) {
    this.realPath = realPath;
  }

  public String getFileType() {
    return this.fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public long getFileLength() {
    return this.fileLength;
  }

  public void setFileLength(long fileLength) {
    this.fileLength = fileLength;
  }

  public int getTabIndex() {
    return this.tabIndex;
  }

  public void setTabIndex(int tabIndex) {
    this.tabIndex = tabIndex;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public byte[] getContent() {
    return this.content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }
}