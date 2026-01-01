#!/bin/bash
# Script to run tests for the Real Estate application

echo "Running Real Estate Management System Tests..."
echo ""

# Compile the test
javac -d bin -sourcepath src src/realestate/test/TestRealEstate.java

if [ $? -ne 0 ]; then
    echo "Test compilation failed!"
    exit 1
fi

# Run the test
java -cp bin realestate.test.TestRealEstate

if [ $? -eq 0 ]; then
    echo ""
    echo "All tests passed successfully!"
    # Clean up test data
    rm -f test_properties.dat
else
    echo ""
    echo "Some tests failed!"
    exit 1
fi
