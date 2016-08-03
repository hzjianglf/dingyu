package com.wxpt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CopyAction extends ActionSupport{
	private String oldPath;
	private String newPath;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String path = ServletActionContext.getServletContext().getRealPath("/");
		this.copyFile(path+"/"+oldPath+"/", path+"/"+newPath+"/");
		return SUCCESS;
	}
	public void copyFile(String oldPath,String newPath){
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFile(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public void delet(){
		String path = ServletActionContext.getServletContext().getRealPath("/");
		this.deleteDir(new File(path+"/"+oldPath+"/"));
	}
	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					System.out.println("shibai");
					return false;
				} else {
					System.out.println("chenggong");
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	public String getOldPath() {
		return oldPath;
	}
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	public String getNewPath() {
		return newPath;
	}
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}
	
}
