@echo off & setlocal enabledelayedexpansion

TITLE ncl-report-sevice[东莞寮步社区sqlims数据同步到云康LIS]

set JAVA_INSTALL=C:\jdk\jdk1.8.0_45

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

%JAVA_INSTALL%\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=128M -classpath ..\conf;%LIB_JARS% com.daanhealth.datax.SimpleApplication
goto end

:debug
%JAVA_INSTALL%\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -classpath ..\conf;%LIB_JARS% com.daanhealth.datax.SimpleApplication
goto end

:jmx
%JAVA_INSTALL%\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -classpath ..\conf;%LIB_JARS% com.daanhealth.datax.SimpleApplication

:end
pause
