package com.handany.base.common;


import com.handany.base.container.CacheContainer;
import com.handany.base.dao.ParameterDAO;
import com.handany.base.model.Parameter;

/**
 * 参数管理器
 * @author lhb
 *
 */
public class ParamManager {
	
	public static final String CACHE_PARAM="Param:";
	
	/**
	 * 返回参数值
	 * @param type
	 * @param code
	 * @return
	 */
	public static String getParameterName(String type,String code){
		
		Parameter param = getParameter(type,code);
		
		if(param != null){
			return param.getName();
		}
		
		return "";
	}
	

	public static Parameter getParameter(String type,String code){
		
		Parameter param = CacheContainer.get(CACHE_PARAM+type+":"+code, Parameter.class);
		
		if(param == null){
			ParameterDAO dao = (ParameterDAO) ComponentFactory.getBean("parameterDAO");
			param = dao.getParameter(type, code);
			if(param != null){
				 CacheContainer.put(CACHE_PARAM+type+":"+code,param,Constants.SECONDS_DAY);
			}
		}
		
		return param;
		
	}



	
	
	



}
