package com.wxpt.common;


/**
 * 第二次抽奖*/
public class RollUtil1 {
	/** 
     * 0出现的概率为%1 
     */  
 public static double rate0 = 0.05;  
 /** 
     * 1出现的概率为%5 
     */  
 public static double rate1 = 0.15;  
 /** 
     * 2出现的概率为%10 
     */  
 public static double rate2 = 0.30;  
 /** 
     * 3出现的概率为%15 
     */  
 public static double rate3 = 0.50;  
  
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
	  else if (randomNumber >= rate0 + rate1 + rate2  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3)  
	  {  
	   return 4;  
	  }  
	  return -1;  
	 } 
 	public static double toLuck1(double min,double max){
         double temp = Math.round(Math.random()*(max-min)+min); 
         System.out.println(temp); 
 		return temp;
 	}
}
