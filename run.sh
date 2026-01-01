#!/bin/bash
# Script to run the Real Estate application

echo "Starting Real Estate Management System..."

# Check if compiled
if [ ! -d "bin" ]; then
    echo "Application not compiled. Running compile.sh first..."
    ./compile.sh
fi

# Run the application
java -cp bin realestate.gui.RealEstateApp
