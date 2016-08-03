package com.handany.base.sequence;

public class SerialNumberConfig {

	private String sequenceName;
	
	private Formatter formatter;
	
	private int length;
	
	private String preffix;
	
	private String subffix;

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public Formatter getFormatter() {
		if(formatter == null){
			SimpleFormatter sform = new SimpleFormatter();
			sform.setLength(length);
			sform.setPreffix(preffix);
			sform.setSubffix(subffix);
			formatter = sform;			
		}
		
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPreffix() {
		return preffix;
	}

	public void setPreffix(String preffix) {
		this.preffix = preffix;
	}

	public String getSubffix() {
		return subffix;
	}

	public void setSubffix(String subffix) {
		this.subffix = subffix;
	}
	
	
}
