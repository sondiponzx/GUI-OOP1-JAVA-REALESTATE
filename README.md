# Real Estate Management System

My university JAVA (Object Oriented Programming) course project - Final version

A comprehensive GUI-based Real Estate Management System built with Java Swing that demonstrates core Object-Oriented Programming principles.

## Features

- **Property Management**: Add, delete, and manage different types of properties (Apartments, Houses, Commercial Properties)
- **Search & Filter**: Search properties by address, type, price range, and status
- **Data Persistence**: Save and load property data from files
- **Rich GUI**: User-friendly interface built with Java Swing
- **Property Details**: View detailed information about each property including tax calculations

## OOP Principles Demonstrated

### 1. Inheritance
- Base `Property` class extended by `Apartment`, `House`, and `CommercialProperty`
- Shared attributes and methods inherited from the base class

### 2. Polymorphism
- Abstract methods `getPropertyType()` and `calculateTax()` implemented differently in each subclass
- Runtime polymorphism through method overriding

### 3. Encapsulation
- Private fields with public getter/setter methods
- Data hiding and controlled access to class members

### 4. Abstraction
- Abstract `Property` class defining the contract for all property types
- Interface-like behavior through abstract methods

## Project Structure

```
GUI-OOP1-JAVA-REALESTATE/
├── src/
│   └── realestate/
│       ├── model/              # Model classes (Business logic)
│       │   ├── Property.java           # Abstract base class
│       │   ├── Apartment.java          # Apartment property type
│       │   ├── House.java              # House property type
│       │   ├── CommercialProperty.java # Commercial property type
│       │   └── PropertyManager.java    # Property management logic
│       └── gui/                # GUI classes (Presentation layer)
│           ├── RealEstateApp.java      # Main application window
│           └── AddPropertyDialog.java  # Dialog for adding properties
├── bin/                        # Compiled class files
├── compile.sh                  # Linux/Mac compilation script
├── compile.bat                 # Windows compilation script
├── run.sh                      # Linux/Mac run script
├── run.bat                     # Windows run script
└── README.md                   # This file
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 or higher

## How to Build and Run

### On Linux/Mac:

```bash
# Compile the application
./compile.sh

# Run the application
./run.sh
```

### On Windows:

```batch
# Compile the application
compile.bat

# Run the application
run.bat
```

### Manual Compilation and Execution:

```bash
# Compile
javac -d bin -sourcepath src src/realestate/gui/RealEstateApp.java

# Run
java -cp bin realestate.gui.RealEstateApp
```

## Usage Guide

### Adding a Property

1. Click **"Add Property"** button or go to **Property → Add Property** menu
2. Select the property type (Apartment, House, or Commercial)
3. Fill in the required information:
   - Address
   - Price
   - Size in square meters
   - Description
   - Type-specific details (bedrooms, bathrooms, etc.)
4. Click **"Add Property"** to save

### Viewing Property Details

- Click on any property in the table to view detailed information in the right panel
- Details include all property attributes and calculated tax

### Searching Properties

Use the **Search** menu to filter properties by:
- **Address**: Search by partial address match
- **Type**: Filter by Apartment, House, or Commercial
- **Price Range**: Filter by minimum and maximum price
- **Show All**: Display all properties

### Managing Properties

- **Delete**: Select a property and click "Delete Property" or use Property → Delete Property menu
- **Update Status**: Select a property and use Property → Update Status to change availability status
- **Save Data**: Use File → Save to persist changes to disk
- **Load Data**: Use File → Load to restore saved data

## Property Types

### Apartment
- Floor number
- Number of bedrooms and bathrooms
- Balcony availability
- Parking space availability
- Tax: 1.5% of property price

### House
- Number of bedrooms and bathrooms
- Garden availability
- Garage availability
- Number of floors
- Tax: 2.0% of property price

### Commercial Property
- Business type
- Number of rooms
- Parking availability and number of spaces
- Tax: 2.5% of property price

## Data Persistence

- Property data is automatically saved to `properties.dat` file
- Data persists between application sessions
- Use File → Save/Load menu to manually save or load data

## Technical Details

- **Programming Language**: Java
- **GUI Framework**: Java Swing
- **Design Pattern**: Model-View separation
- **Data Storage**: Object serialization
- **Java Version**: Compatible with Java 8+

## Learning Outcomes

This project demonstrates:
- Creating class hierarchies with inheritance
- Implementing abstract classes and methods
- Using polymorphism for flexible code design
- Building GUI applications with Swing
- Managing collections of objects
- File I/O and object serialization
- Event-driven programming

## Author

University OOP1 Java Programming Course Project

## License

Educational use only - University Project

---

© 2026 Real Estate Management System
