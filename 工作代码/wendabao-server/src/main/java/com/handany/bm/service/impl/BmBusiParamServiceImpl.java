package com.handany.bm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.bm.dao.BmBusiParamMapper;
import com.handany.bm.service.BmBusiParamService;

@Service
public class BmBusiParamServiceImpl implements BmBusiParamService{

	@Autowired
	private BmBusiParamMapper busiParamMapper;
	
	@Override
	public List<Map<String, Object>> selectAll() throws Exception {
		return busiParamMapper.selectAll();
	}

	@Override
	public int save(Map<String, Object> map) throws Exception {
		return busiParamMapper.save(map);
	}
	
	public Map<String, Object> getParam(String code) throws Exception{
		return busiParamMapper.getParam(code);
	}

}
