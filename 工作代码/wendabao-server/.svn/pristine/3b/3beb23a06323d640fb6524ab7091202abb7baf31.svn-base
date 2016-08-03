package com.handany.base.sequence;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatter implements Formatter{

	private String serialFormate;
	
	private String dateFormate;
	
	private int seqNoLength;
	
	@Override
	public String format(long seqNo) {
		// TODO Auto-generated method stub
	
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
		
		String dateStr = sdf.format(Calendar.getInstance().getTime());
		
		String seqNoStr = "";
		for(int i=0;i<seqNoLength-(seqNo+"").length();i++){
			seqNoStr+="0";
		}
		seqNoStr += seqNo;
		
		String rtnStr = serialFormate.replace("{date}", dateStr).replace("{seqNo}", seqNoStr);
		
		
		
		return rtnStr;
	}

	public String getSerialFormate() {
		return serialFormate;
	}

	public void setSerialFormate(String serialFormate) {
		this.serialFormate = serialFormate;
	}

	public String getDateFormate() {
		return dateFormate;
	}

	public void setDateFormate(String dateFormate) {
		this.dateFormate = dateFormate;
	}
	

	public int getSeqNoLength() {
		return seqNoLength;
	}

	public void setSeqNoLength(int seqNoLength) {
		this.seqNoLength = seqNoLength;
	}

	public static void main(String[] args) {
		DateFormatter df = new DateFormatter();
		df.setDateFormate("yyyyMMdd");
		df.setSerialFormate("YX_{date}_{seqNo}");
		df.setSeqNoLength(5);
		System.out.println(df.format(200));
	}
}
