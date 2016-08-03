<%@page import="com.handany.base.common.ComponentFactory"%>
<%@page import="com.handany.base.sequence.SerialNumberManager"%>
<%

SerialNumberManager sn = ComponentFactory.getBean("serialNumberManager",SerialNumberManager.class);

for(int i=0;i<21;i++){
	out.println(sn.nextSeqNo("pm_test"));
}
%>
<html>
<body>
<h2>Hello World!</h2>
</body>
</html>
