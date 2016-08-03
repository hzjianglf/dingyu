package com.wxpt.common;

import java.util.List;

import com.wxpt.site.entity.Prize;
import com.wxpt.site.service.ImageService;

/**
 * 第一次抽奖*/
public class RollUtil {
	ImageService imageservice;
	List<Prize> prizeList;
	/** 
     * 0出现的概率为%1 
     */  
 public static double rate0 = 0.01;  
 /** 
     * 1出现的概率为%5 
     */  
 public static double rate1 = 0.05;  
 /** 
     * 2出现的概率为%10 
     */  
 public static double rate2 = 0.10;  
 /** 
     * 3出现的概率为%15 
     */  
 public static double rate3 = 0.15;  
 /** 
     * 4出现的概率为%25
     */  
 public static double rate4 = 0.25;  
 /** 
     * 5出现的概率为%44
     */  
 public static double rate5 = 0.44;  
  
 /** 
  * Math.random()产生一个double型的随机数，判断一下 
  * 例如0出现的概率为%50，则介于0到0.50中间的返回0 
     * @return int 
     * 
     */  
 public static  int PercentageRandom()  
 {  
	  double randomNumber;  
	  randomNumber = Math.random();
		
	 if (randomNumber >= 0 && randomNumber <= rate0)  
		  {  
		   return 1;  
		  }	
		
	
	
			   
	  else if (randomNumber >= rate0  && randomNumber <= rate0 + rate1)  
	  {  
	   return 2;  
	  }  
	  else if (randomNumber >= rate0 + rate1  
	    && randomNumber <= rate0 + rate1 + rate2)  
	  {  
	   return 3;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3)  
	  {  
	   return 4;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4)  
	  {  
	   return 5;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4  
	      + rate5)  
	  {  
	   return 6;  
	  }  
	  return -1;  
	 } 
 
 
 public static  int PercentageRandom(double rate0,double rate1,double rate2,double rate3,
		 double rate4,double rate5,double rate6,double rate7,double rate8,double rate9)  {  
	  double randomNumber;  
	  randomNumber = Math.random();
		
	 if (randomNumber >= 0 && randomNumber <=rate0)  
		  {  
		 
		   return 1;  
		  }	
		else if (randomNumber >= rate0  && randomNumber <= rate0 + rate1)  
	  {  
	   return 2;  
	  }  
	  else if (randomNumber >= rate0 + rate1  
	    && randomNumber <= rate0 + rate1 + rate2)  
	  {  
	   return 3;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3)  
	  {  
	   return 4;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4)  
	  {  
	   return 5;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3  +rate4
			    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4+rate5)  
			  {  
			   return 6;  
			  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4+rate5  
			    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6)  
			  {  
			   return 7;  
			  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6  
			    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6+rate7)  
			  {  
			   return 8;  
			  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6+rate7  
			    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6+rate7+rate8)  
			  {  
			   return 9;  
			  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6+rate7+rate8  
			    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4+rate5+rate6+rate7+rate8+rate9)  
			  {  
			   return 10;  
			  }  
	 
	   
	  return -1;  
	 } 
 

 	public static double toLuck(double min,double max){
         double temp = Math.round(Math.random()*(max-min)+min); 
         System.out.println(temp); 
 		return temp;
 	}



	public ImageService getImageservice() {
		return imageservice;
	}



	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}



	public List<Prize> getPrizeList() {
		return prizeList;
	}



	public void setPrizeList(List<Prize> prizeList) {
		this.prizeList = prizeList;
	}



	

 	
 	
}
