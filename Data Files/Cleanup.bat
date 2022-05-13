
:: BAT file for Automaiton cleanup
@echo cleanup started
TASKKILL /IM chrome.exe /F
@echo chrome browsers closed******************
TASKKILL /IM firefox.exe /F
@echo Firefox browsers closed******************
TASKKILL /IM iexplore.exe /F
@echo Internet Explorer browsers closed******************
TASKKILL /IM chromedriver.exe /F
@echo Chrome Driver closed******************
TASKKILL /IM IEDriverServer.exe /F
@echo IE Driver closed******
TASKKILL /IM geckodriver.exe /F
@echo Gecko Driver closed******

TASKKILL /IM cscript.exe /F
@echo lock script closed******




::set backupFilename=tasklist_%date:~-4%_%date:~7,2%_%date:~4,2%__%time:~0,2%_%time:~3,2%_%time:~6,2%
::tasklist >>E:\CATSDEV\CATS-6.1.0\output\TaskList\%backupFilename%.txt


IF ERRORLEVEL 1 GOTO NOT-THERE
ECHO EXE EXISTS
PAUSE
EXIT 
:NOT-THERE
ECHO EXE Does NOT EXIST
PAUSE
EXIT /b 0
