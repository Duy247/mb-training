@echo off
setlocal

call "%~dp0gradlew.bat" openTestIde
set EXIT_CODE=%ERRORLEVEL%

endlocal & exit /b %EXIT_CODE%
