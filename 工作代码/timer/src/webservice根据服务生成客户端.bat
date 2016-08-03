set Axis_Lib=D:\lumeidaspace\timer\WebContent\WEB-INF\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Output_Path=.
set Package=test
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java http://10.10.10.207:8000/services/resCountManager/queryManager?wsdl -o%Output_Path% -p%Package%

pause