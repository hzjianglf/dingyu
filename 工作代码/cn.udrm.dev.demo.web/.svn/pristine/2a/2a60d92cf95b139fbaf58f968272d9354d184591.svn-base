package cn.udrm.dev.demo.web.action;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ZhaoBin
 *
 * @version $Revision: 1.3 $ $Date: 2009-08-11 08:42:00 $
 */
public abstract class BaseAction  extends ActionSupport implements ServletRequestAware, ServletResponseAware, Serializable {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest servletRequest;
	
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    protected HttpServletRequest getServletRequest() {
        return this.servletRequest;
    }
	
	private HttpServletResponse servletResponse;
	
	public void setServletResponse(HttpServletResponse response){
		this.servletResponse = response;
	}
	
	public HttpServletResponse getServletResponse(){
		return this.servletResponse;
	}
	
	public static final String JSON = "json";
	
	private String json;

	public String getJson() {
		return json;
	}

	public String setJson(String json) {
		this.json = json;
		return JSON;
	}
	
	public String setJson(boolean success, String message) {
		return setJson(success, message, null);
	}
	
	public String setJson(boolean success, String message, Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("{success:").append(success);
		sb.append(",message:'" + StringEscapeUtils.escapeJavaScript(message) + "'");
		if (params != null){
			for(Entry<String, String> entry : params.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				sb.append("," + key + ":'" + StringEscapeUtils.escapeJavaScript(value) + "'");
			}
		}
		sb.append("}");
		this.json = sb.toString();
		return JSON;
	}
	
}
