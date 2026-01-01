@echo off
REM Script to run tests for the Real Estate application

echo Running Real Estate Management System Tests...
echo.

REM Compile the test
javac -d bin -sourcepath src src\realestate\test\TestRealEstate.java

if %errorlevel% neq 0 (
    echo Test compilation failed!
    exit /b 1
)

REM Run the test
java -cp bin realestate.test.TestRealEstate

if %errorlevel% equ 0 (
    echo.
    echo All tests passed successfully!
    REM Clean up test data
    del /f test_properties.dat 2>nul
) else (
    echo.
    echo Some tests failed!
    exit /b 1
)
