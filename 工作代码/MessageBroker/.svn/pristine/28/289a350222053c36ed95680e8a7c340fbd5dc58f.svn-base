package com.ibm.struts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.ibm.hibernate.MonitorMainDAO;
import com.opensymphony.xwork2.ActionSupport;

public class GetHistoryChartsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private String chartBasePie3D = null;

	private String chartBaseColumn3D = null;
	
	public String getChartBasePie3D() {
		return chartBasePie3D;
	}

	public void setChartBasePie3D(String chartBasePie3D) {
		this.chartBasePie3D = chartBasePie3D;
	}

	public String getChartBaseColumn3D() {
		return chartBaseColumn3D;
	}

	public void setChartBaseColumn3D(String chartBaseColumn3D) {
		this.chartBaseColumn3D = chartBaseColumn3D;
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		// TODO Auto-generated method stub
		MonitorMainDAO dao = new MonitorMainDAO();
		long success = dao.getHistoryResultNumber("0");		
		long failure = dao.getHistoryResultNumber("1");
		
		Calendar day = Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");			
		String date = sdf.format(day.getTime());
		
		String strPie3DXML = "";
		strPie3DXML = "<graph caption='历史消息流数据统计' subCaption='" + date + "之前不含当日' decimalPrecision='0' showNames='1' baseFontSize='12'" 
			          + " numVDivLines='10' showAlternateVGridColor='1' AlternateVGridColor='e1f5ff' divLineColor='e1f5ff' vdivLineColor='e1f5ff' bgColor='E9E9E9' canvasBorderThickness='0'>";
		strPie3DXML = strPie3DXML + "<set name='成功' value='" + success + "' color='006F00' />";
		strPie3DXML = strPie3DXML + "<set name='失败' value='" + failure + "' color='FF0000' link='searchfailure.action' hoverText='失败(点击查看具体信息)' />";        
		strPie3DXML = strPie3DXML + "</graph>";
		
		Date d = new Date();
		String strColumn3DXML = "";
		strColumn3DXML = "<graph caption='前五天消息流统计' subcaption='" + getDateBefore(d, 1) + "至" + getDateBefore(d, 5) + "'  yAxisName='Transactions' xAxisName='统计日期' hovercapbg='DEDEBE' " 
						+ "hovercapborder='889E6D' rotateNames='0' yAxisMaxValue='100' numdivlines='9' divLineColor='CCCCCC' divLineAlpha='80' decimalPrecision='0' baseFontSize='12' " 
						+ "showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC'>";
		
		strColumn3DXML = strColumn3DXML + "<categories font='Arial' fontSize='12' fontColor='000000'>";
		
		for(int i=1; i < 6; i++) {
			strColumn3DXML = strColumn3DXML + "<category name='" + getDateBefore(d, i) + "'/>";
		}
		
		strColumn3DXML = strColumn3DXML + "</categories><dataset seriesname='成功' color='56B9F9'>";
		Map map0 = dao.getSearchDateCount(5, "0");		
		for(int i=1; i < 6; i++) {
			strColumn3DXML = strColumn3DXML + "<set value='" + map0.get(getDateBefore(d, i)) + "'/>";
		}
		strColumn3DXML = strColumn3DXML + "</dataset><dataset seriesname='失败' color='C9198D'>";
		Map map1 = dao.getSearchDateCount(5, "1");		
		for(int i=1; i < 6; i++) {
			strColumn3DXML = strColumn3DXML + "<set value='" + map1.get(getDateBefore(d, i)) + "'/>";
		}				
		strColumn3DXML = strColumn3DXML + "</dataset></graph>";
		
		//System.out.println(strColumn3DXML);
		setChartBasePie3D(strPie3DXML);       
        setChartBaseColumn3D(strColumn3DXML);
        
		return SUCCESS;
	}
	
	public String getDateBefore(Date d, int day) {   
		Calendar now = Calendar.getInstance();   
	    now.setTime(d);   
	    now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.format(now.getTime());   
	}
}