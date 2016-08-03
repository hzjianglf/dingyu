package com.ibm.sigar;

public class BrokerProcCount {
	
	private int brokerProcNum = 0;
	
	private int wmqProcNum = 0;
	
	public int getBrokerProcNum() {
		return brokerProcNum;
	}
	
	public void setBrokerProcNum(int brokerProcNum) {
		this.brokerProcNum = brokerProcNum;
	}
	
	public int getWmqProcNum() {
		return wmqProcNum;
	}
	
	public void setWmqProcNum(int wmqProcNum) {
		this.wmqProcNum = wmqProcNum;
	}
		
}
