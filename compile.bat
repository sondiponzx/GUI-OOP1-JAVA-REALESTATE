@echo off
REM Script to compile the Real Estate application

echo Compiling Real Estate Management System...

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile all Java files
javac -d bin -sourcepath src src\realestate\gui\RealEstateApp.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo Run the application with: run.bat
) else (
    echo Compilation failed!
    exit /b 1
)
