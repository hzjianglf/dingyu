package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansImage;


public class FansPojo {
	private Fans fans;
	private List<FansImage> fansImageList;
	public Fans getFans() {
		return fans;
	}
	public void setFans(Fans fans) {
		this.fans = fans;
	}
	public List<FansImage> getFansImageList() {
		return fansImageList;
	}
	public void setFansImageList(List<FansImage> fansImageList) {
		this.fansImageList = fansImageList;
	}
	

}
