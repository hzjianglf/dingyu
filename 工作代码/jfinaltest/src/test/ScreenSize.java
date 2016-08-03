package test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class ScreenSize
{
   private int screenWidth;
   private int screenHeight;
   public  void setScreenWidth(int screenWidth)
    {
        this.screenWidth=screenWidth;
    }
   public void setScreenHeight(int screenHeight)
  {
       this.screenHeight=screenHeight;
   }
   public int getScreenWidth()
   {
          setScreenWidth((int)Toolkit.getDefaultToolkit().getScreenSize().width);
          return screenWidth;
    }
    public int getScreenHeight()
    {
        setScreenHeight((int)Toolkit.getDefaultToolkit().getScreenSize().height);
          return screenHeight;
     }
    
//    public static void main(String[] args)
//    {
//           int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
//           int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
//          
//           System.out.println(screenWidth);
//           System.out.println(screenHeight);
//           System.out.println(screenWidth+""+screenHeight);
//           
//           Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
//           int width = (int)screensize.getWidth();
//           int height = (int)screensize.getHeight();
//           System.out.println(width);
//           System.out.println(height);
//           
//           String str= "abcd(efg(higk)oo)";
//           
//           System.out.println();
//     }
    
    
    public static void main(String args[]){ 
       String str="111(222(333(444(555)333)222)1111)"; 
    	System.out.println(getStr(str,"")+"   0000000000000");
    }
    
    
    public static String getStr(String str,String ary){
    	 
        //String str="您好{options{abc}}评论了您的{options{def}}"; 
        ArrayList<String>  word=new ArrayList<String>(); 
        int m=0,n=0; 
        int count=0; 
        for(int i=0;i<str.length();i++){ 
            if(str.charAt(i)=='('){ 
                if(count==0){ 
                    m=i; 
                } 
                count++; 
            } 
            if(str.charAt(i)==')'){ 
                count--; 
                if(count==0){ 
                    n=i; 
                    word.add(str.substring(m,n+1)); 
                } 
            } 
                     } 
        String strs=word.toString();
        System.out.println(strs.substring(2, strs.length()-2));
        strs=strs.substring(2, strs.length()-2);
        if(ary.length()==0){
        	 ary =  ary+strs+";";
        }
        if(strs.contains("(")){
        	 ary =  ary+getStr(strs,ary)+";";
    	}
        
    return ary;
    }
}
