@echo off
rem Licensed Materials - Property of IBM;
rem 5724-A82
rem (c) Copyright IBM Corp. 2005,2008;
rem All Rights Reserved;
rem US Government Users Restricted Rights - use,
rem duplication or disclosure restricted by GSA
rem ADP Schedule Contract with IBM Corp.;
rem @(#) 45 1.24.6.1@(#)

set PROSPECTIVE_MQSI_FILEPATH=C:\IBM\MQSI\7.0
if ["%MQSI_FILEPATH%"] NEQ [] (
  if {"%MQSI_FILEPATH%"} == {"%PROSPECTIVE_MQSI_FILEPATH%"} (
     if {"%1"} == {"silent"} (
       rem we are called from mqsicommandconsole
       echo.
       echo MQSI %MQSI_VERSION%
       echo "%MQSI_FILEPATH%"
       echo.
     ) else (
       echo mqsiprofile repetition disallowed
     )
     set PROSPECTIVE_MQSI_FILEPATH=
     exit /b 0
  )
)
set MQSI_FILEPATH=%PROSPECTIVE_MQSI_FILEPATH%
set PROSPECTIVE_MQSI_FILEPATH=

set MQSI_WORKPATH=C:\Documents and Settings\All Users\Application Data\IBM\MQSI

set MQSI_VERSION=7.0.0.1
set MQSI_VERSION_V=7
set MQSI_VERSION_R=0
set MQSI_VERSION_M=0
set MQSI_VERSION_F=1

set MQSI_FAD=5

set PATH=%MQSI_FILEPATH%\bin;%MQ_JAVA_INSTALL_PATH%\lib;%PATH%;

set LIB=%MQSI_FILEPATH%\lib;%LIB%;

set INCLUDE=%MQSI_FILEPATH%\include;%INCLUDE%;

set MQSI_CONSOLE_NLSPATH=%MQSI_FILEPATH%\messages\
set MQSI_DEVELOPMENT=%MQSI_WORKPATH%\registry
set MQSI_REGISTRY=%MQSI_WORKPATH%
set MQSI_JARPATH=%MQSI_FILEPATH%\classes;%MQSI_FILEPATH%\messages;%MQSI_JARPATH%
set MQSI_LILPATH=%MQSI_FILEPATH%\bin;%MQSI_FILEPATH%\jplugin;%MQSI_LILPATH%
set MQSI_SECURITY_PROVIDER_PATH=%MQSI_FILEPATH%\bin;%MQSI_SECURITY_PROVIDER_PATH%
set MQSI_EXMLTCONFIGPATH=%MQSI_FILEPATH%\exmltConfig
set MQSI_CATALINA_HOME=%MQSI_FILEPATH%\catalina

rem Allow Config Manager Proxy applications to be built and run in this environment
set CLASSPATH=%MQSI_FILEPATH%\classes\brokerutil.jar;%CLASSPATH%
set CLASSPATH=%MQSI_FILEPATH%\classes\ConfigManagerProxy.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.commonservices.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.headers.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.jmqi.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\com.ibm.mq.pcf.jar;%CLASSPATH%
set CLASSPATH=%MQ_JAVA_INSTALL_PATH%\lib\connector.jar;%CLASSPATH%

set CLASSPATH=%MQSI_FILEPATH%\classes;%CLASSPATH%
set CLASSPATH=%MQSI_FILEPATH%\messages;%CLASSPATH%
set CLASSPATH=%CLASSPATH%;%MQSI_WORKPATH%\common\wsrr

set ICU_DATA=%MQSI_FILEPATH%\icudata;%ICU_DATA%

set MIBDIRS=%MQSI_FILEPATH%\snmp-mib;%MIBDIRS%

@FOR %%I IN ("%MQSI_WORKPATH%\common\profiles\*.cmd") DO @(@(@CALL "%%I"))

rem JRE path overrides in custom profiles are not allowed from 7.0 onwards
set MQSI_JREPATH=%MQSI_FILEPATH%\jre16
set PATH=%MQSI_JREPATH%\bin;%PATH%

echo.
echo MQSI %MQSI_VERSION%
echo %MQSI_FILEPATH%
echo.

mqsistart %1