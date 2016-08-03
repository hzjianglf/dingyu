<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../_include/head.jsp" />
<script type="text/javascript">
ctx = '${AppRoot}';
</script>
<script type="text/javascript" src="${AppRoot}/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${AppRoot}/js/highcharts/extjs-adapter.src.js"></script> 
<script type="text/javascript" src="${AppRoot}/js/highcharts/Ext.ux.HighChart.js"></script>
<script language="javascript" type="text/javascript" src=${AppRoot}/js/service.log.js></script>
<script type="text/javascript">
Ext.onReady(App.Service.Log.init);
</script>
<div id="logs-grid-div"></div>
<div id="monitor-win-div"></div>
<jsp:include page="../_include/foot.jsp" />