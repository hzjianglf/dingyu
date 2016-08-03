set CUR_DIR=%cd%

cd /d "%~dp0.."
cd /d "%CUR_DIR%"

set APP_LIB=%CUR_DIR%\lib
set JAVA_HOME=D:\IBM\MQSI\7.0\jre16
set APP_PATH=%CUR_DIR%\lib

set PATH=%PATH%;%APP_PATH%

set CLASSPATH=%APP_LIB%\mqsimonitor.jar;%APP_LIB%\sigar.jar;%APP_LIB%\com.ibm.mq.commonservices.jar;%APP_LIB%\dhbcore.jar;%APP_LIB%\jms.jar;%APP_LIB%\xstream-1.3.1.jar;%APP_LIB%\connector.jar;%APP_LIB%\com.ibm.mqjms.jar;%APP_LIB%\com.ibm.mq.jmqi.jar;%APP_LIB%\jta.jar;%APP_LIB%\dom4j-1.6.1.jar;%APP_LIB%\log4j-1.2.14.jar

start %JAVA_HOME%\bin\javaw "com.ibm.sys.BrokerTop" localhost 2414 MB7QMGR

start %JAVA_HOME%\bin\javaw "com.ibm.sys.BrokerCommand" localhost 2414 MB7QMGR