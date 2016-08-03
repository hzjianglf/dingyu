<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../_include/head.jsp" />
<script type="text/javascript">
    ctx = '${AppRoot}';
    OSGI_DEV_MODE = ${osgiDev};
</script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/system.js"></script>
<jsp:include page="../_include/foot.jsp" />