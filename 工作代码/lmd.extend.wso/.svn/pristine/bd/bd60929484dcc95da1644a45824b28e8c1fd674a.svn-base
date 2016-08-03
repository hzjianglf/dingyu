package lmd.extend.wso.util;

public class SbJsonUtil {


public static String toJason(StringBuffer s){
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < s.toString().length(); i++) {
	                char c = s.toString().toCharArray()[i];
	                switch (c) {
	                    case '\"': sb.append("\\\""); break;
	                    case '\\': sb.append("\\\\"); break;
	                    case '/': sb.append("\\/"); break;
	                    case '\b': sb.append("\\b"); break;
	                    case '\f': sb.append("\\f"); break;
	                    case '\n': sb.append("\\n"); break;
	                    case '\r': sb.append("\\r"); break;
	                    case '\t': sb.append("\\t"); break;
	                    default: sb.append(c); break;
	                }
	            }
	            return sb.toString();
	        }
}
