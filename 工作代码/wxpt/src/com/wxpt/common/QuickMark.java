package com.wxpt.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;


public class QuickMark {

	
	//生成二维码
	public static void createQuickMaik(String cardId,String filepath,String endTime)
	{
		try {
            
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Hashtable hints = new Hashtable();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(cardId, BarcodeFormat.QR_CODE, 200, 200,hints);
		     File file1 = new File(filepath,cardId+".jpg");
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		    /* CreatePic cp = new CreatePic();
		     cp.graphicsGeneration("普通卡",cardId, "0", "0",endTime, filepath+cardId+".jpg");*/
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	//解析二维码
	public static String analysisQuickMark(String filePath)
	{
		String name = "";
		 try {
             MultiFormatReader formatReader = new MultiFormatReader();
			 File file = new File(filePath);
			 BufferedImage image = ImageIO.read(file);;
			 LuminanceSource source = new BufferedImageLuminanceSource(image);
			 Binarizer  binarizer = new HybridBinarizer(source);
			 BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			 Hashtable hints = new Hashtable();
			 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			 Result result = formatReader.decode(binaryBitmap,hints);
 
             System.out.println("result = "+ result.toString());
			 System.out.println("resultFormat = "+ result.getBarcodeFormat());
			 System.out.println("resultText = "+ result.getText());
			 name = result.toString();
			} catch (Exception e) {
			 e.printStackTrace();
			}
		 return name;
	}
	
}
