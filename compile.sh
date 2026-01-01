#!/bin/bash
# Script to compile the Real Estate application

echo "Compiling Real Estate Management System..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin -sourcepath src src/realestate/gui/RealEstateApp.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Run the application with: ./run.sh"
else
    echo "Compilation failed!"
    exit 1
fi
