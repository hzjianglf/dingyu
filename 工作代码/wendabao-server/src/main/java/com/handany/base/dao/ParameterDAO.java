package com.handany.base.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.handany.base.common.ComponentFactory;
import com.handany.base.model.Parameter;
import com.handany.base.model.ParameterClass;

@Repository("parameterDAO")
public class ParameterDAO extends BaseDAO{

	
	public List<Parameter> getParamList(String classId){
		return getMapper(ParameterMapper.class).getParamList(classId);
	}
	
	
	
	public Map<String,ParameterClass> getParameterClassCodeMap(){
		
		List<ParameterClass> paramList = getMapper(ParameterMapper.class).getParamClassList();
		
		
		Map<String,ParameterClass> paramMap = new ConcurrentHashMap<String,ParameterClass>();
		
		for(int i=0;i<paramList.size();i++){
			ParameterClass param = paramList.get(i);
			paramMap.put(param.getCode(), param);
		}
		
		return paramMap;
	}
	
	public Map<String,ParameterClass> getParameterClassIdMap(){
		
		List<ParameterClass> paramList = getMapper(ParameterMapper.class).getParamClassList();
		
		
		Map<String,ParameterClass> paramMap = new ConcurrentHashMap<String,ParameterClass>();
		
		for(int i=0;i<paramList.size();i++){
			ParameterClass param = paramList.get(i);
			paramMap.put(param.getId().trim(), param);
		}
		
		return paramMap;
	}
	
	public List<Parameter> getSubParams(String classCode,String parentCode){
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("parentCode", parentCode);
		map.put("classCode", classCode);
		
		return getMapper(ParameterMapper.class).querySubParamList(map);
	}
	
	public Parameter getParameter(String classCode,String paramCode){
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("paramCode", paramCode);
		map.put("classCode", classCode);
		
		return getMapper(ParameterMapper.class).queryParam(map);
	}
	
	
	public static void main(String[] args) {
		
		ParameterDAO dao = (ParameterDAO) ComponentFactory.getBean("parameterDAO");
		
		List<Parameter> list = dao.getSubParams("sys.region","110000");
		
		for(Parameter param : list){
			System.out.println(ParameterDAO.objToStr(param));
		}
	}
	
}
