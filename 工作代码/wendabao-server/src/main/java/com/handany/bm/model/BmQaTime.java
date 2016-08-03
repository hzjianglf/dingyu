package com.handany.bm.model;

import java.util.Date;
import java.math.BigDecimal;

public class BmQaTime {
    private String id;

    private String name;

    private BigDecimal time;

    private BigDecimal price;

    private String region1;

    private String region2;

    private String region3;

    /**
     *  1. 暂存
     *  2. 生效
     *  3. 停止
     *  0. 删除
     */
    private String status;

    private Date lastModified;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion3(String region3) {
        this.region3 = region3;
    }

    public String getRegion3() {
        return region3;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

}
