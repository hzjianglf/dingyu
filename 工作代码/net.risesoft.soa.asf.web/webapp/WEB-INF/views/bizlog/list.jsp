<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../_include/head.jsp" />
<script type="text/javascript">
ctx = '${AppRoot}';
App.Context['currentApp'] = '${app}';
</script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/lib/jquery.dateFormat-1.0.js"></script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/bizlog.js""></script>
<div id="bizlog-grid-panel" ></div>
<jsp:include page="../_include/foot.jsp" />