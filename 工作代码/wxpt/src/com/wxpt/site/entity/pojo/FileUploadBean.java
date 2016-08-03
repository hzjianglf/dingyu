package com.wxpt.site.entity.pojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.bridge.StringBridge;

public class FileUploadBean {
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String uploadMessage;
	private String name;
	private String address;
	
	// 上传的图片的方法。
	public void upLoad(String name, File upload) {
		try {

			String filepath = ServletActionContext.getServletContext()
					.getRealPath("web") + "/images/";
			System.out.println("filepath:" + filepath);
			File directory = new File(filepath);
			
			if (!directory.exists()) {
				FileUtils.forceMkdir(directory);
			}
			File save = new File(directory.getCanonicalPath() + '/' + name);
			save.delete();
			try {
				FileUtils.moveFile(upload, save);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			// /
			StringBuffer buffer = new StringBuffer();
			buffer.append("images/" + name).toString();
			address = buffer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
