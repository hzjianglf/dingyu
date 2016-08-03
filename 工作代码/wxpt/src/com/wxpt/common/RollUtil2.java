package com.wxpt.common;


/**
 * 第三次抽奖*/
public class RollUtil2 {
	/** 
     * 0出现的概率为%1 
     */  
 public static double rate0 = 0.10;  
 /** 
     * 1出现的概率为%5 
     */  
 public static double rate1 = 0.30;  
 /** 
     * 2出现的概率为%10 
     */  
 public static double rate2 = 0.60;  
 
  
 /** 
  * Math.random()产生一个double型的随机数，判断一下 
  * 例如0出现的概率为%50，则介于0到0.50中间的返回0 
     * @return int 
     * 
     */  
 public static int PercentageRandom()  
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
	  return -1;  
	 } 
 	public static double toLuck(double min,double max){
         double temp = Math.round(Math.random()*(max-min)+min); 
         System.out.println(temp); 
 		return temp;
 	}
}
