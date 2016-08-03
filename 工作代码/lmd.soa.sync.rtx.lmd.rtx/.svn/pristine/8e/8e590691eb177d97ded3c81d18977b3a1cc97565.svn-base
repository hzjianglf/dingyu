set Axis_Lib=D:\lumeidaspace\lmd.soa.sync.rtx.lmd.rtx\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Output_Path=.
set Package=com.lmd.sync.rtx.service.client
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java http://10.10.10.133:8080/rtxservice/RtxService?wsdl -o%Output_Path% -p%Package%

pause