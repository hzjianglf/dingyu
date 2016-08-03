package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

public interface BmBusiParamMapper {

	public int save(Map<String,Object> map) throws Exception;
	
	public List<Map<String,Object>> selectAll() throws Exception;
	
	public Map<String,Object> getParam(String code) throws Exception;

}
