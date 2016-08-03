<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="_include/head.jsp" />
<script type="text/javascript">
ctx = '${AppRoot}';
var isConsole = ${console};
var isSysmgrTree = ${(sysmgrTree == '' || sysmgrTree == null) ? true : sysmgrTree};
var isServiceTree = ${(serviceTree == '' || serviceTree == null) ? true : serviceTree};
</script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/index.js"></script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/lib/share.js"></script>
<jsp:include page="_include/foot.jsp" />