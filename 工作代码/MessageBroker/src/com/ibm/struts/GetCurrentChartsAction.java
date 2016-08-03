package com.ibm.struts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.ibm.hibernate.MonitorMainDAO;
import com.opensymphony.xwork2.ActionSupport;

public class GetCurrentChartsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private String chartArea2D = null;
	
	private String chartLine2D = null;
	
	private String chartPie3D = null;
		
	public String getChartPie3D() {
		return chartPie3D;
	}

	public void setChartPie3D(String chartPie3D) {
		this.chartPie3D = chartPie3D;
	}

	public String getChartLine2D() {
		return chartLine2D;
	}

	public void setChartLine2D(String chartLine2D) {
		this.chartLine2D = chartLine2D;
	}

	public String getChartArea2D() {
		return chartArea2D;
	}

	public void setChartArea2D(String chartArea2D) {
		this.chartArea2D = chartArea2D;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		// TODO Auto-generated method stub
		MonitorMainDAO dao = new MonitorMainDAO();
		
		Calendar day = Calendar.getInstance();				   			   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");			
		String date = sdf.format(day.getTime());
		
		String strArea2D = null;
		strArea2D = "<graph caption='实时消息流数据区域图' subCaption='" + date + "' xAxisName='时间(按小时统计)' yAxisName='Transactions' decimalPrecision='0' formatNumberScale='0' " 
			+ "baseFontSize='12' color='B1D1DC' areaAlpha='60' showAreaborder='1' areaBorderThickness='1' areaBorderColor='7B9D9D' " 
			+ "numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff' divLineColor='e1f5ff' vdivLineColor='e1f5ff' "
			+ "bgColor='E9E9E9' canvasBorderThickness='0' >"; 
		
		Map map = dao.getSearchHourCount();
		for(int i=0; i <24; i++) {			
			strArea2D = strArea2D +  "<set name='" + i + "' value='" + map.get(i).toString() + "' color='AFD8F8' hoverText='" + i + "点到" + (i+1) + "点'/>";
		}		
		strArea2D = strArea2D + "</graph>";
		
		setChartArea2D(strArea2D);
		
		String strLine2D = null;
		strLine2D = "<graph caption='实时消息流数据折线图' subcaption='" + date + "' xAxisName='时间(按小时统计)' yAxisMinValue='0' yAxisName='Transactions' decimalPrecision='0' " 
					+ "formatNumberScale='0' numberPrefix='' showNames='1' showValues='0' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' " 
					+ "divLineAlpha='20' alternateHGridAlpha='5' baseFontSize='12' bgColor='E9E9E9'>";
		for(int i=0; i <24; i++) {			
			strLine2D = strLine2D +  "<set name='" + i + "' value='" + map.get(i).toString() + "' hoverText='" + i + "点到" + (i+1) + "点'/>";
		}				
		strLine2D = strLine2D + "</graph>";
		setChartLine2D(strLine2D);
		
		long success = dao.getCurrentResultNumber("0");	
		long failure = dao.getCurrentResultNumber("1");

		String strPie3D = "";
		strPie3D = "<graph caption='实时消息流数据3D饼图' subCaption='" + date + "' decimalPrecision='0' showNames='1' baseFontSize='12' bgColor='E9E9E9' " 
			          + " numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff' divLineColor='e1f5ff' vdivLineColor='e1f5ff' canvasBorderThickness='0'>";
		strPie3D = strPie3D + "<set name='成功' value='" + success + "' color='006F00'/>";
		strPie3D = strPie3D + "<set name='失败' value='" + failure + "' color='FF0000' link='searchfailure.action' hoverText='失败(点击查看具体信息)' />";        
		strPie3D = strPie3D + "</graph>";
		setChartPie3D(strPie3D);
		
		return SUCCESS;
	}
	
	public String getHourBefore(int hours) {   
		Calendar day = Calendar.getInstance();		
		day.setTime(new Date());   
		day.set(Calendar.HOUR, day.get(Calendar.HOUR) - hours);	    
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH");			
		String date = sdf.format(day.getTime());
		return date;
	}

}