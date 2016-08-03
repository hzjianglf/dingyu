package com.wxpt.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CreatePic {
	BufferedImage image;
	String filepath = ServletActionContext.getServletContext()
			.getRealPath("web") + "/images/";
	void createImage(String fileLocation) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void graphicsGeneration(String dengji,String cardID,String jifen,String save,String endTime,
			String imgurl) {
		int imageWidth = 640;// 图片的宽度
		int imageHeight = 320;// 图片的高度
		Font f = new Font("宋体",Font.BOLD ,24); 
		image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setFont(f);
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.BLACK);
		graphics.drawLine(0,250,640,250);
		graphics.drawString(dengji,300, 50);
		graphics.drawString("卡号 : " + cardID,50, 280);
		graphics.drawString("积分 : " + jifen, 270, 100);
		graphics.drawString("储值 : " + save, 270, 140);
		graphics.drawString("有效期 : " + endTime, 270, 180);
		// 改成这样:
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(new File(imgurl));
			//new File("C:/Users/Administrator/Desktop/testImage/bs.jpg");
		} catch (Exception e) {
		}
		if (bimg != null)
			graphics.drawImage(bimg, 30, 30, null);
		graphics.dispose();
		createImage(filepath+cardID+"3"+".jpg");
	}

/*	public static void main(String[] args) {
		CreatePic cg = new CreatePic();
		try {
			cg.graphicsGeneration("ewew", "1", "12",
					"C:/Users/Administrator/Desktop/testImage/sky1.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}