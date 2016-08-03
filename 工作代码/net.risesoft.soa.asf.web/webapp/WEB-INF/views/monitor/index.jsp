<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../_include/head.jsp" />
<script type="text/javascript">
    ctx = '${AppRoot}';
</script>
<script src="${AppRoot}/js/jolokia/jolokia.js"></script>
<script src="${AppRoot}/js/jolokia/jolokia-simple.js"></script>
<script src="${AppRoot}/js/jolokia/json2.js"></script>
<script type="text/javascript" src="${AppRoot}/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${AppRoot }/js/lib/jquery.base64.js"></script>
<script language="javascript" type="text/javascript" src=${AppRoot}/js/monitor.js></script>
<div id="update-delay-div" >
      更新间隔: 
      <select id="update-delay-setup" >
         <option value="10">10</option>
         <option value="15" selected="selected">15</option>
         <option value="20">20</option>
         <option value="25">25</option>
         <option value="30">30</option>
      </select>
       秒<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="force-gc-button" type="button" value="强制GC" /> -->
</div>
<div id="memory-chart-container" style="height: 160px;"></div>
<div id="cpu-chart-container" style="height: 160px;"></div>
<div id="dspool-chart-container" style="height: 190px;"></div>
<jsp:include page="../_include/foot.jsp" />