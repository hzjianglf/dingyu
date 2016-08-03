package com.handany.base.dao;


import java.util.List;
import java.util.Map;

import com.handany.base.model.Parameter;
import com.handany.base.model.ParameterClass;

public interface ParameterMapper {

	public List<Parameter> getParamList(String classId);
	
	public List<ParameterClass> getParamClassList();
	
	public List<Parameter> querySubParamList(Map <String,String> map);
	
	public Parameter queryParam(Map <String,String> map);
	
}
