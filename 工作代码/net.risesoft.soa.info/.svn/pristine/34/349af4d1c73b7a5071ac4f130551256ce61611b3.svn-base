<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.model.InformationFile"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.info.service.InfoFileManager"%>
<%@page import="net.risesoft.soa.info.util.ConfigUtil"%>
<%
String id = request.getParameter("id");
String type = request.getParameter("type");
%>
<%
if (type.equals("html")){
	String value = "";
	InfoFileManager ifm = SpringUtil.getBean("infoFileManager");
	List<InformationFile> ifs = ifm.find(id, 0, "html");
	if (ifs.size() > 0){
		InformationFile informationFile = ifs.get(0);
		byte[] bytes = informationFile.getContent();
		if (bytes.length > 0){
			value = new String(bytes, "UTF-8");
		}
	}
	System.out.print(value+"{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}");
	out.print(value);
}
if (type.equals("doc")){
	InfoFileManager ifm = SpringUtil.getBean("infoFileManager");
	List<InformationFile> ifs = ifm.find(id, 0, "doc");
	if (ifs.size() > 0){
		InformationFile informationFile = ifs.get(0);
		//String path = ConfigUtil.filePath + informationFile.getRealPath() + informationFile.getId() + ".swf";
		//File file = new File(path);
		boolean isExist = true;
		//if (file.exists()){
		//	isExist = true;
		//}
%>
<script type="text/javascript">
	jQuery('#docDownload_span').html("<a href='/info/infoFileShow.action?id=<%=informationFile.getId()%>&type=docDown'>正文下载 </a>");
    jQuery('#infoHtmlDiv').load('/info/display/docShow.jsp',{'id':'<%=informationFile.getId()%>','operation':'doc', 'isExist': <%=isExist%>});
</script>
<%	
	}
}
%>


