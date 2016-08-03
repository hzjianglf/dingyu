package com.whcyz.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.whcyz.model.Account;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.permission.UnCheck;
import com.whcyz.util.DateUtil;
@Before(PermissionInterceptor.class)
public class UploadController extends BaseController {
	@UnCheck
	@Before(NoUrlPara.class)
	public void index(){
		
	}
	private String getTodayFileFoder(String folder){
		return "upload/images/"+DateUtil.format(new Date(),"yyyyMMdd")+"/"+folder+"/";
	}
	private void getTheFile(String name){
		String folder=getTodayFileFoder(name);
		String abfolder=PathKit.getWebRootPath()+"/"+folder;
		UploadFile file=getFile("imgUrl",abfolder);
		if(file==null){
			renderJsonFail("上传失败！");
			return;
		}
		File f = file.getFile();
		String extName = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1);
		String newName=new Date().getTime()+"."+extName;
		String newFilePath=abfolder+"/"+newName;
		File newFile=new File(newFilePath);
		f.renameTo(newFile);
		try {
			processImgThumb(newFilePath,150,100);
			processImg(newFilePath,630);
		} catch (IOException e) {
			e.printStackTrace();
			renderJsonFail("生成缩略图失败了！");
		}
		renderJsonData(true, folder+newName);
	}
	private void processImgThumb(String newFilePath,int w,int h) throws IOException {
		Thumbnails.of(newFilePath).size(w,h).outputQuality(0.7f).outputFormat("jpg").toFile(newFilePath+".thumb");
	}
	private void processImg(String newFilePath,int w) throws IOException {
		Thumbnails.of(newFilePath).width(w).outputQuality(0.8f).toFile(newFilePath);
	}
	@Permission(Account.PERMISSION_ALL)
	public void avatar(){
		String folder="upload/images/account/avatar/";
		String abfolder=PathKit.getWebRootPath()+"/"+folder;
		UploadFile file=getFile("imgUrl",abfolder);
		if(file==null){
			renderJsonFail("上传失败！");
			return;
		}
		renderJsonData(true, folder+file.getFileName());
	}
	
	@Permission(Account.PERMISSION_ALL)
	public void article(){
		getTheFile("article");
	}
	@Permission(Account.PERMISSION_ALL)
	public void person(){
		getTheFile("person");
	}
	@Permission(Account.PERMISSION_ALL)
	public void company(){
		getTheFile("company");
	}
}
