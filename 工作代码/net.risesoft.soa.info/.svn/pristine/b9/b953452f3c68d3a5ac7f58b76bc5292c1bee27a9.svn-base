<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="net.risesoft.soa.info.model.InformationFile"%>
<%@page import="java.util.List"%>
<%@page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@page import="net.risesoft.soa.info.service.InfoFileManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String id = request.getParameter("id");
String operation = request.getParameter("operation");
String value = "";
if (operation.equals("edit")){
	InfoFileManager ifm = SpringUtil.getBean("infoFileManager");
	List<InformationFile> ifs = ifm.find(id, 0, "html");
	if (ifs.size() > 0){
		InformationFile informationFile = ifs.get(0);
		byte[] bytes = informationFile.getContent();
		if (bytes.length > 0){
			value = new String(bytes, "UTF-8");
			//value = new String(bytes);
		}
	}
}
%>
<input type="hidden" name="infoHtmlType" value="html"></input>
<textarea id="infoHtml" name="infoHtml"><%=value%></textarea>
<script type="text/javascript">
var instance = CKEDITOR.instances['infoHtml'];
if(instance)
{
    CKEDITOR.remove(instance);
}
CKEDITOR.replace( 'infoHtml' );
</script>
