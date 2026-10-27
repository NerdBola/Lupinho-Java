@echo off
setlocal
pushd "%~dp0"
if errorlevel 1 (
    echo ERROR: Could not open the game folder.
    exit /b 1
)

set "OUTPUT=%~dp0..\dist\adventure.jupi"
set "TEMP_OUTPUT=%OUTPUT%.zip"

if not exist "game.java" (
    echo ERROR: game.java was not found.
    popd
    exit /b 1
)
if not exist "hardware.properties" (
    echo ERROR: hardware.properties was not found.
    popd
    exit /b 1
)
if not exist "%~dp0..\dist" mkdir "%~dp0..\dist"

powershell -NoProfile -ExecutionPolicy Bypass -Command "Compress-Archive -Path 'game.java','hardware.properties' -DestinationPath '%TEMP_OUTPUT%' -Force"
if errorlevel 1 goto failed

move /Y "%TEMP_OUTPUT%" "%OUTPUT%" >nul
if errorlevel 1 goto failed

echo Created: "%OUTPUT%"
popd
exit /b 0

:failed
echo ERROR: Could not create the .jupi cartridge.
if exist "%TEMP_OUTPUT%" del "%TEMP_OUTPUT%"
popd
exit /b 1
