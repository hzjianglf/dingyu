package com.whcyz.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCodeUtil {
	/**
	 * 生成制定的二维码数据
	 * @param encodeddata
	 * @param filePath
	 */
	private static void genQrCodeImg(String encodeddata, String filePath) {
		int times=2;
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(7);
		byte[] d=null;
		try {
			d = encodeddata.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedImage bi = new BufferedImage(139*times, 139*times, BufferedImage.TYPE_INT_RGB);
		// createGraphics
		Graphics2D g = bi.createGraphics();
		// set background
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 139*times, 139*times);
		g.setColor(Color.BLACK);

		if (d.length > 0 && d.length < 123) {
			boolean[][] b = qrcode.calQrcode(d);
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j * 3*times + 2, i * 3*times + 2, 3*times, 3*times);
					}
				}
			}
		}

		g.dispose();
		bi.flush();
		FileOutputStream sos=null;
	        try {
	        	sos=new FileOutputStream(filePath);
				ImageIO.write(bi, "png",sos);
			} catch (Exception e) {
						throw new RuntimeException(e);
			}finally{
				if(sos!=null){
					try {
						sos.close();
					} catch (IOException e1) {
						throw new RuntimeException(e1);
					}
				}
			}
	}
}
