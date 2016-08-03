package com.handany.base.sequence.impl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SequenceMapper {

	public void synDb(String seqName) throws Exception;
	
	public void syncDb(@Param("seqName") String seqName, @Param("startVal") long startVal);
	
	public void updateAll() throws Exception;
	
	public List<Map<String,String>> sequenceInfo() throws Exception;
        
        public Map<String, String> getSequenceInfo(String sequenceName);
}
