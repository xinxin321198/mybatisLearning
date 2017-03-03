REM task kill /F /IM "java.exe"
@rem @for /F "delims=" %%I in ("%~dp0") do echo %%~fI
@echo off
chcp 936
rem @setlocal
set JAVA_HOME=e:\jdk1.8
set SVN_HOME=c:\Program Files\TortoiseSVN
set GRADLE_HOME=E:\gradle3.3
set MAVEN_HOME=E:\maven3.3.9
set classpath=.;.\classes\;.\target\test-classes\;.\target\classes\;%JAVA_HOME%\jre\lib\rt.jar;%JAVA_HOME%\lib\tools.jar;
set APR_ICONV_PATH=%SVN_HOME%\iconv
set LANG=zh_CN.UTF8
set path=.;%JAVA_HOME%\bin;%GRADLE_HOME%\bin;%MAVEN_HOME%\bin;%SVN_HOME%\bin;%MYSQL_HOME%\bin;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem

