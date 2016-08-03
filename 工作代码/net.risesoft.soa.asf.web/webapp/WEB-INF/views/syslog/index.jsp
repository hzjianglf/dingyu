<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../_include/head.jsp" />
<script type="text/javascript">
    ctx = '${AppRoot}';
    OSGI_DEV_MODE = ${osgiDev};
</script>
<script src="${AppRoot}/js/jolokia/jolokia.js"></script>
<script src="${AppRoot}/js/jolokia/jolokia-simple.js"></script>
<script src="${AppRoot}/js/jolokia/json2.js"></script>
<script type="text/javascript" src="${AppRoot }/js/lib/jquery.base64.js"></script>
<script type="text/javascript" src="${AppRoot }/js/lib/jquery.download.js"></script>
<script language="javascript" type="text/javascript" src="${AppRoot}/js/syslog.js"></script>
<jsp:include page="../_include/foot.jsp" />