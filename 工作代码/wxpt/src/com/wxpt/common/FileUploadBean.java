package com.wxpt.common;

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
	
	
	// 上传的图片的方法。
	public void upLoadEnterId(int enterId,String name, File upload) {
		try {

			String filepath = ServletActionContext.getServletContext()
					.getRealPath("web") + "/images/"+enterId+"";
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
	
	public int uploads(File f,String fileName,String name){
		int msg;
		String path = ServletActionContext.getServletContext().getRealPath(
				"web")+"/images/"+name;//服务器的文件位置路径
		try {
//			if(!fileName.endsWith(".jpg")){
//				msg=2;
//			}
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream outputStream = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			msg = 0;
			return msg;
		}
		msg=1;
		return msg;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// 删除服务图片的方法
	public int deletefile(String path) {
		String filepath = ServletActionContext.getServletContext().getRealPath(
				"web")+"/images/"+path;//服务器的文件位置路径
				
		String fileRealPath = new StringBuffer(filepath).toString();
		File file = new File(fileRealPath);
		file.delete();
		return 1;
	}
	
	// 删除服务图片的方法
	public int deletefileByAllPath(String allPath) {
		String fileRealPath = new StringBuffer(allPath).toString();
		File file = new File(fileRealPath);
		file.delete();
		return 1;
	}
	
	/**
	 * 
	 * @param name上传文件名
	 * @param upload 需要上传的文件
	 * @param filePath 当前目录为web/images/+你需要下一级目录
	 */
	public void upLoad(String name, File upload,String filePath) {
		try {

			String filepath = ServletActionContext.getServletContext()
					.getRealPath("web") + "/images/"+filePath+"/";
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
