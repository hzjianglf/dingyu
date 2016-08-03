package com.handany.base.sequence;

public class SimpleFormatter implements Formatter{
	
	private int length;
	
	private String preffix;
	
	private String subffix;


	public String format(long seqNo) {

		String holder = "";
		
		int holderLength = length - (getPreffix()+getSubffix()+seqNo).length();
		
		
		for(int i=0;i<holderLength;i++){
			holder +="0";
		}
			
			
		return preffix+holder+seqNo+subffix;
	}
	

	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public String getPreffix() {
		
		if(preffix == null){
			preffix = "";
		}
		return preffix;
	}



	public void setPreffix(String preffix) {
		this.preffix = preffix;
	}



	public String getSubffix() {
		
		if(subffix == null){
			subffix = "";
		}
		
		return subffix;
	}



	public void setSubffix(String subffix) {
		this.subffix = subffix;
	}

	
}
