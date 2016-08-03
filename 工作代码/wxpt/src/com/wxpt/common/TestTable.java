package com.wxpt.common;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class TestTable {

	BufferedImage image;

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

	public void graphicsGeneration() {
	
		//实际数据行数+标题+备注
		
		int totalrow = 6;
		
		int totalcol = 7;
		
		int imageWidth = 1024;
		int imageHeight = totalrow*40+40;
		int rowheight = 40;
		int startHeight = 10;
		int startWidth = 10;
		int colwidth = (int)((imageWidth-40)/totalcol);
	
		image = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.pink);
		graphics.fillRect(0,0, imageWidth, imageHeight);
		graphics.setColor(new Color(220,240,240));
		//画横线
		for(int j=0;j<6;j++){
				graphics.setColor(Color.white);
				int num =-10;
			
				
				graphics.drawLine(startWidth, startHeight+(j+1)*rowheight+num, imageWidth-7, startHeight+(j+1)*rowheight+num);
		}
		//末行
		graphics.setColor(Color.white);
		graphics.drawLine(startWidth, imageHeight-30+28, imageWidth-7, imageHeight-30+28);
		//画竖线
		for(int k=0;k<7;k++){ 
		graphics.setColor(Color.white);
		
		graphics.drawLine(startWidth+k*colwidth, startHeight+rowheight-10, startWidth+k*colwidth, imageHeight-50+60-10);
		}
		//末列
		graphics.setColor(Color.white);
		graphics.drawLine(imageWidth-7,startHeight+rowheight-10,imageWidth-7, imageHeight-50+60-10);
		graphics.setColor(Color.BLACK);
		//设置字体
		Font font = new Font("华文楷体",Font.BOLD,20);
		graphics.setFont(font);
		//写标题
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String title = ""+sdf.format(new Date());
		graphics.drawString(title, imageWidth/3+startWidth+120, startHeight+rowheight-10-10);
		font = new Font("华文楷体",Font.BOLD,18);
		graphics.setFont(font);
		//写入表头
		String[] headCells = {"一","二","三","四","五","六","日"};
		for(int m=0;m<7;m++){ 
		graphics.drawString(headCells[m].toString(), startWidth+colwidth*m+5, startHeight+rowheight*2-10-10);
		}
		//设置字体
		font = new Font("华文楷体",Font.PLAIN,16);
		graphics.setFont(font);
		Date d = new Date();
		
	
		String s_date = sdf.format(d).substring(0,8)+"01";
		
	
		List ls1 = new ArrayList();
		List ls2 = new ArrayList();
		List ls3 = new ArrayList();
		List ls4 = new ArrayList();
		List ls5 = new ArrayList();
		try {
			int xingqi =sdf.parse(s_date).getDay();//月初的是周几
			
			
			for(int i=0;i<xingqi;i++){
				//System.out.println(getPrevMonth()-i);
				font = new Font("华文楷体",Font.BOLD,18);
				graphics.setFont(font);
				graphics.setColor(Color.GRAY);
				graphics.drawString((getPrevMonth()-i)+"", startWidth+colwidth*(7-xingqi-i+2)+5, startHeight+rowheight*(0+3)-10-10);
			}
			for(int i=0;i<7-xingqi;i++){
				font = new Font("华文楷体",Font.BOLD,18);
				graphics.setFont(font);
				graphics.setColor(Color.BLACK);
				graphics.drawString(i+1+"", startWidth+colwidth*(7-xingqi+i+3)+5, startHeight+rowheight*(0+3)-10-10);
			
			}
			for(int i=0;i<7;i++){
				ls2.add(7-xingqi+(i+1));
			}
			for(int i=0;i<7;i++){
				ls3.add(7-xingqi+(i+8));
			}
			for(int i=0;i<7;i++){
				ls4.add(7-xingqi+(i+15));
			}
		
			int endDay =getEndDayofMonth(Integer.parseInt(sdf.format(d).substring(0,4)),d.getMonth());
			
			for(int i=0;i<(endDay-(7-xingqi+21));i++){
				
				ls5.add(7-xingqi+22+i);
				
			}
			
			for(int i=0;i<=(7-ls5.size());i++){
			
				ls5.add(i+1);
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//写入内容
		for(int n=0;n<5;n++){ 
	
		for(int l=0;l<7;l++){
			if(n==0){
			//	graphics.drawString(ls1.get(l).toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10);
			}
			if(n==1){
				graphics.drawString(ls2.get(l).toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10-10);
			}
			if(n==2){
				graphics.drawString(ls3.get(l).toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10-10);
			}
			if(n==3){
				graphics.drawString(ls4.get(l).toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10-10);
			}
			if(n==4){
				graphics.drawString(ls5.get(l).toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10-10);
			}
		}
		}
		font = new Font("华文楷体",Font.BOLD,18);
		graphics.setFont(font);
		graphics.setColor(Color.RED);
		//写备注
		String remark = "连续签到五天可以抽奖哦";
		
		//graphics.drawString(remark, startWidth, imageHeight-30);
		createImage("c:\\1.jpg");	
	}
	public int getEndDayofMonth(int sYear,int sMonth){
		  Calendar c = Calendar.getInstance(); 
		String tEnddate = "";
		c.set(c.YEAR,sYear); 
		c.set(c.MONTH,sMonth);
		tEnddate = String.valueOf(sYear)+"-"+String.valueOf(sMonth)+"-"+c.getActualMaximum(c.DAY_OF_MONTH);

		return c.getActualMaximum(c.DAY_OF_MONTH);

		}


	public int getPrevMonth(){
		  Calendar cal = Calendar.getInstance();      
		  Date date = new Date();  
		
		  cal.setTime(date);       
		  int year = 0;       
		  int month = cal.get(Calendar.MONTH); // 上个月月份   
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		  int a=getEndDayofMonth(Integer.parseInt(sdf.format(date).substring(0,4)),month-1);

		  cal.setTime(date);       
    // int day1 = cal.getActualMinimum(Calendar.DAY_OF_MONTH);//起始天数        
		  int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 结束天
		  return a;
	}
	public static void main(String[] args) {
		TestTable cg = new TestTable();
		try {
		cg.graphicsGeneration();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}

