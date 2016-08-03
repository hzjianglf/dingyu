package com.handany.base.model;


public class PmSysInfo {
    private String version;

    /**
     * 客户端类型：3 安卓学生端 4 苹果学生端 5.安卓教师端 6.苹果教师端 7.安卓管理端 8.苹果管理端
     **/
    private String deviceType;

    private String downloadUrl;

    private String forceUpdate;

    private String serverUrl;

    private String imageUrl;

    private String custServicePhone;

    private String custServiceMail;

    private String startupImgUrl;

    private String publishDate;


    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setDevicetype(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicetype() {
        return deviceType;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setCustServicePhone(String custServicePhone) {
        this.custServicePhone = custServicePhone;
    }

    public String getCustServicePhone() {
        return custServicePhone;
    }

    public void setCustServiceMail(String custServiceMail) {
        this.custServiceMail = custServiceMail;
    }

    public String getCustServiceMail() {
        return custServiceMail;
    }

    public void setStartupImgUrl(String startupImgUrl) {
        this.startupImgUrl = startupImgUrl;
    }

    public String getStartupImgUrl() {
        return startupImgUrl;
    }

    public void setPublishdate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishdate() {
        return publishDate;
    }

}
