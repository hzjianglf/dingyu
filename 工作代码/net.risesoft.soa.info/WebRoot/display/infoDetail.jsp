<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.dao.InformationIndexDao"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.info.model.Information"%> 
<%@page import="net.risesoft.soa.info.tools.InformationList"%>
<%@page import="net.risesoft.soa.info.model.InformationIndex"%>
<%@page import="net.risesoft.soa.framework.session.SessionUser"%>
<%@page import="net.risesoft.soa.framework.session.SessionConst"%>
<%@page import="net.risesoft.soa.framework.session.SessionUtil"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%@page import="net.risesoft.soa.info.action.infoname"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
String id = request.getParameter("id");
InformationIndexDao informationIndexDao = SpringUtil.getBean("informationIndexDao");
InformationIndex informationIndex = informationIndexDao.findOne(id);
String infoID = informationIndex.getInfoID();
Information information = InformationList.get(infoID);
String path=request.getContextPath();

String acid = "";
try{
acid=request.getParameter("acid");
}catch(Exception e){}


infoname infos=new infoname();
String infoshouname="";
try{
	infoshouname=infos.getinfoname(acid);
}catch(Exception e){}
if("null".equals(infoshouname)||null==infoshouname){
	
	infoshouname="";
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<META content="IE=5.0000" http-equiv="X-UA-Compatible">
<META http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<script type="text/javascript" src="/jquery/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="jquerysession.js"></script>
<TITLE></TITLE> 
<META name="GENERATOR" content="MSHTML 11.00.9600.18231">
<style>
body {
	margin: 0px; text-align: center;
}
td {
	 font-size: 12px;
}
.border {
	border: 1px solid rgb(186, 209, 235); border-image: none;
}

.texttitle {
	color: rgb(204, 0, 0); font-family: "????"; font-size: 24px; font-weight: bold;
}

.border2 {
	border: 1px solid rgb(186, 209, 235); border-image: none; background-color: rgb(255, 255, 255);
}
.text {
	line-height: 200%; font-family: "????"; font-size: 14px;
}
</style>
</HEAD> 
<BODY style="border: 1px solid rgb(186, 209, 235); border-image: none;">
<TABLE width="1000" align="center" border="0" cellspacing="0" cellpadding="0">
  <TBODY>
  <TR>
    <TD align="center" class="textbg" valign="top">
      <TABLE width="851" height="543" class="border2" border="0" cellspacing="0" 
      cellpadding="0">
        <TBODY>
        <TR>
          <TD height="292" align="center" valign="top">
            <TABLE width="750" border="0" cellspacing="0" cellpadding="0">
              <TBODY>
              <TR>
                <TD height="56" align="center" class="texttitle" style="line-height: 200%;">${root.bt }</TD></TR>
              <TR>
                <TD align="center" style="font-size: 14px; font-weight: bold; text-decoration: none;"></TD></TR>
              <TR>
                <TD height="26" align="center" 
                  bgcolor="#d9ecff">发表日期:${root.fbsj }　信息来源:${root.fbz }</TD></TR></TBODY></TABLE>
            <TABLE width="100%" height="57" border="0" cellspacing="0" 
            cellpadding="0">
              <TBODY>
              <TR>
                <TD>&nbsp;</TD></TR></TBODY></TABLE>
            <TABLE width="750" border="0" cellspacing="0" cellpadding="0">
              <TBODY>
              <TR>
                <TD class="text">
                <div id="infoHtmlDiv"></div>
                <%
List<String[]> list = (List<String[]>)request.getAttribute("list");
System.out.print(list.size()+"++++++++++++++++++++++");
			for(int i=0;i<list.size();i++){
				String[] string = list.get(i);
				if (string[2].equals("image")){
					%>
					<P>&nbsp;</P>
                  <P style="text-align: center;"><SPAN 
                  style="font-family: 宋体;"><SPAN style="font-size: 12pt;"><a class="fancybox" href="/info/infoImgShow.action?id=<%=string[0]%>&type=ImageShow" title="<%=string[1]%>"><IMG 
                   alt="" src='<%=string[3]%>'></a></SPAN></SPAN></P>
                  <P style="text-align: center;">&nbsp;</P>
					<%
				}
			}
%>

                  
				  
                 </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
      <TABLE width="100%" height="57" border="0" cellspacing="0" 
cellpadding="0">
        <TBODY>
        <TR>
          <TD>&nbsp;</TD>
        </TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
</BODY>
<script language="javascript">
jQuery('#infoHtmlDiv').load('/info/display/htmlDisplay.jsp',{'id':'<%=id%>','infoID':'${infoID}','type':'html'});
</script>
</HTML>


