package com.handany.base.sequence;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SerialNumberManager {


	private static Log logger = LogFactory.getLog(SerialNumberManager.class) ;
	
	
	private Map<String,SerialNumberConfig> sequenceMap;

	private Sequence sequenceGenerator;
	

	public void setSequenceMap(Map<String, SerialNumberConfig> sequenceMap) {
		this.sequenceMap = sequenceMap;
	}



	public String nextSeqNo(String name) throws Exception{
		
		SerialNumberConfig config = sequenceMap.get(name);
		
		if(config == null){
			logger.error("未配置序列"+name+"的生成规则");
			throw new NullPointerException("未配置序列"+name+"的生成规则");
		}
		
		if(sequenceGenerator == null){
			logger.error("未配置sequence");
			throw new NullPointerException("未配置sequence");
		}
		
		long seqNo = sequenceGenerator.nextVal(config.getSequenceName());
		
						
		String formatedSeq = config.getFormatter().format(seqNo);	
		
		
		
		return formatedSeq;
	}
	


	public void setSequenceGenerator(Sequence sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}
	
}
