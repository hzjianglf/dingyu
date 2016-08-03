package com.handany.bm.service;

import java.util.List;
import java.util.Map;

public interface BmBusiParamService {

	public List<Map<String,Object>> selectAll() throws Exception;
	
	public int save(Map<String,Object> map) throws Exception;
	
	public Map<String,Object> getParam(String code) throws Exception;
}
