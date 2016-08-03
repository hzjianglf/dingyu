<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page import="lmd.extend.wso.dao.*" %>
<%@ page import="java.net.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String chartSWF = "../falsh/Charts/FCF_Line.swf"; //request.getParameter("chartSWF");
	String strURL ="";// request.getParameter("strURL");
	String strXML = URLDecoder.decode(request.getParameter("strXML"));
	String chartId ="curchart2"; //request.getParameter("chartId");
	String chartWidthStr = "600";//request.getParameter("chartWidth");
	String chartHeightStr ="450";// request.getParameter("chartHeight");
	String debugModeStr= "false";//request.getParameter("debugMode"); 
	String registerWithJSStr= "false";//request.getParameter("registerWithJS"); 
	
	int chartWidth = 600;
	int chartHeight = 300;
	boolean debugMode=false;
	boolean registerWithJS=false;
	int debugModeInt = 0;
	int regWithJSInt = 0;
	
	
	if (null != chartWidthStr && !chartWidthStr.equals("")) {
		chartWidth = Integer.parseInt(chartWidthStr);
	}
	if (null != chartHeightStr && !chartHeightStr.equals("")) {
		chartHeight = Integer.parseInt(chartHeightStr);
	}
	if(null!=debugModeStr && !debugModeStr.equals("")){
		debugMode = new Boolean(debugModeStr);
		debugModeInt=boolToNum(debugMode);
	}
	if(null!=registerWithJSStr && !registerWithJSStr.equals("")){
		registerWithJS = new Boolean(registerWithJSStr);
		regWithJSInt=boolToNum(registerWithJS);
	}
	
	//System.out.println("sdfsdffffffdsfdhjhjhhj>>>>>>>>"+strXML);	
%>
<%!
    /**
     * Converts a Boolean value to int value<br>
     * 
     * @param bool Boolean value which needs to be converted to int value 
     * @return int value correspoding to the boolean : 1 for true and 0 for false
     */
   public int boolToNum(Boolean bool) {
	int num = 0;
	if (bool.booleanValue()) {
	    num = 1;
	}
	return num;
    }
%>
<html>
	<head>
		<title>${param.colName}</title>
		<script type="text/javascript" src="/extjs/bootstrap3.4.js"></script>
		<link rel="stylesheet" type="text/css" href="/extjs/3.4/ux/css/LockingGridView.css" />
		<script type="text/javascript" src="/extjs/3.4/ux/LockingGridView.js"></script>
		<script type="text/javascript" src="/jquery/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="../falsh/Charts/FusionCharts.js"></script>
	</head>
	<body >
	    <center>
        <table class="detailcontenttable fixedlayout" border="0" cellpadding="0" cellspacing="0" id="detailcontenttable">  
			<tr>   	
			    <td colspan="20" class="nopad">     			
			      	<div class="editlinks" id="editlinks">      	
			        	<span id="connector1">&nbsp;&#x202a;&#x202c;&nbsp;</span>
			        	<span id="connector2">&nbsp;&#x202a;&#x202c;&nbsp;</span>	   
			      	</div>
			    </td>
		    </tr>   
			<tr>
				<td colspan="20">
			        <div>
			          	<input type="hidden" name="itemType" value="">
			          	<input type="hidden" name="parentUri" value="">
			        </div>				
					<div class="propertiessection" id="propertiessection">
<!--						<div class="sectiondivider" id="propSection1">
							<table class="sectiontitletable" border="0" cellpadding="0" cellspacing="0">
								<tr>
					                <td width="1%">
						                  <a href="javascript:toggleSection('propSection1')" class="blacknounderline" tabindex="1">
						                    <img id="img_propSection1" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
						                  </a>
					                </td>
					                <td width="99%">
						                  <div class="sectiontitle">
						                    折线图
						                  </div>
					                </td>
								</tr>
							</table>
						</div>  --><!---->    
						<div>
							<br/>
						</div>                   
						<div class="expandablesection expanded" id="child_propSection1">
 							<div align="center">								     
								   <!-- START Script Block for Chart <%=chartId%> -->
									<div id='<%=chartId %>Div' align='center'>Chart.</div>
									<script type='text/javascript'>
									var chart_<%=chartId%> = new FusionCharts("<%=chartSWF %>", "<%=chartId%>", "<%=chartWidth %>", "<%= chartHeight%>", "<%= debugModeInt%>", "<%= regWithJSInt%>");
									
									<%	
										if (strXML.equals("")) {
									%>
										    <!-- Set the dataURL of the chart-->
										    chart_<%= chartId%>.setDataURL("<%= strURL%>");									
									<%} else {%>
										    chart_<%= chartId%>.setDataXML("<%= strXML%>");
									<%}%>
										<!-- Finally, render the chart.-->
										chart_<%=chartId%>.render("<%=chartId%>Div");
									</script>
									<!--END Script Block for Chart <%=chartId%> -->																
							</div>         						    
						</div>	
					</div>
			     </td>     
			</tr>   
		</table> 
		<center>
     </body>
</html>