package com.wxpt.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FileViewer {

	public static void main(String[] args) {
		List arrayList = FileViewer
				.getListFiles("D:/tool/apache-tomcat/webapps/wxpt/1");

		if (arrayList.isEmpty()) {
			System.out.println("没有符号要求的文件");
		} else {
			String message = "";
			message += "符号要求的文件数：" + arrayList.size() + "\r\n";
			System.out.println(message);

			for (Iterator i = arrayList.iterator(); i.hasNext();) {
				String temp = (String) i.next();
				System.out.println(temp);
				message += temp + "\r\n";
			}

			appendMethod("d:/ajax/menu.txt", message);
		}
	}

	public static List<String> fileList = new ArrayList<String>();

	/**
	 * 
	 * @param path
	 *            文件路径
	 * @param suffix
	 *            后缀名
	 * @param isdepth
	 *            是否遍历子目录
	 * @return
	 */
	public static List getListFiles(String path) {
		System.out.println("当前企业文件路径："+path);
		File file = new File(path);
		return FileViewer.listFile(file);
	}

	private static FileUploadBean bean = new FileUploadBean();

	public static List listFile(File f) {
		// 是目录，同时需要遍历子目录
		if (f.isDirectory()) {
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++) {
				listFile(t[i]);
			}
		} else {
			String filePath = f.getAbsolutePath();

			int begIndex = filePath.lastIndexOf(".");// 最后一个.(即后缀名前面的.)的索引

			if (begIndex == -1)// 防止是文件但却没有后缀名结束的文件
			{
				bean.deletefileByAllPath(filePath);
			}else{
				fileList.add(filePath);
			}
		}

		return fileList;
	}

	/**
	 * 方法追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void appendMethod(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content + "\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取文本里面的所有文件
	 * @param value
	 * @return
	 */
	public List getFilePath(String value) {
		List<String> l = new ArrayList<String>();
		Document doc = (Document) Jsoup.parse(value);
		System.out.println(doc.getElementsByTag("img").size());
		for (int i = 0; i < doc.getElementsByTag("img").size(); i++) {
			String src = doc.getElementsByTag("img").get(i).attr("src");
			if (!src.startsWith("/")) {
				continue;
			}
			l.add(src.split("/wxpt/")[1]);
		}
		for (int i = 0; i < doc.getElementsByTag("a").size(); i++) {
			String src = doc.getElementsByTag("a").get(i).attr("href");
			if (src.startsWith("http://")) {
				continue;
			}
			l.add(src.split("/wxpt/")[1]);
		}
		return l;
	}
}
