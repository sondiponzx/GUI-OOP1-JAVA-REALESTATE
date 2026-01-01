@echo off
REM Script to run the Real Estate application

echo Starting Real Estate Management System...

REM Check if compiled
if not exist bin (
    echo Application not compiled. Running compile.bat first...
    call compile.bat
)

REM Run the application
java -cp bin realestate.gui.RealEstateApp
