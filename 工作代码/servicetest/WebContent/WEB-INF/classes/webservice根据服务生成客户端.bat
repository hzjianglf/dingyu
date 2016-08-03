set Axis_Lib=D:\lumeidaspace\servicetest\WebContent\WEB-INF\lib
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib%
set Output_Path=.
set Package=com.test.client
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java http://192.168.1.131:8000/services/smsManager/SmsManager?wsdl -o%Output_Path% -p%Package%

pause