package lmd.extend.wso.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringtoListMap {

	public static String toListMap(String proStr){
		String strReturn="{";
		String str = proStr.substring(1, proStr.length()-1).replaceAll("\"", "").trim();//去掉{}
		String[] ary = str.split(",");
		boolean has = proStr.contains("visitCount");//扩展属性key
		if(ary.length>0){
			for(int i=0;i<ary.length;i++){
				String[] eachAry = ary[i].split(":");
				if("visitCount".equals(eachAry[0])){
					strReturn += "\""+eachAry[0]+"\":\""+(Integer.parseInt(eachAry[1])+1)+"\"";
				}else{
					strReturn += "\""+eachAry[0]+"\":\""+eachAry[1]+"\"";
				}
				if(i!=ary.length-1){
					strReturn += ",";
				}
			}
			if(!has){
				strReturn += ",\"visitCount\":\"1\"";//扩展属性
				has=true;
			}
			strReturn += "}";
		}
		return strReturn;
	}
}
