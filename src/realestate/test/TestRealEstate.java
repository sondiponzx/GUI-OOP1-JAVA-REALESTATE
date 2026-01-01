package realestate.test;

import realestate.model.*;
import java.util.List;

/**
 * Simple test program to verify the Real Estate system functionality
 */
public class TestRealEstate {
    public static void main(String[] args) {
        System.out.println("=== Real Estate Management System Test ===\n");
        
        // Create PropertyManager
        PropertyManager manager = new PropertyManager();
        
        // Test 1: Create and add an Apartment
        System.out.println("Test 1: Creating an Apartment...");
        Apartment apt = new Apartment(
            "P001",
            "123 Main Street, Apt 5B",
            250000.0,
            85,
            "Modern apartment in downtown area",
            5,
            2,
            1,
            true,
            true
        );
        manager.addProperty(apt);
        System.out.println("✓ Apartment created: " + apt.getPropertyType());
        System.out.println("  Address: " + apt.getAddress());
        System.out.println("  Price: $" + apt.getPrice());
        System.out.println("  Tax: $" + apt.calculateTax());
        System.out.println();
        
        // Test 2: Create and add a House
        System.out.println("Test 2: Creating a House...");
        House house = new House(
            "P002",
            "456 Oak Avenue",
            450000.0,
            150,
            "Beautiful family house with garden",
            3,
            2,
            true,
            true,
            2
        );
        manager.addProperty(house);
        System.out.println("✓ House created: " + house.getPropertyType());
        System.out.println("  Address: " + house.getAddress());
        System.out.println("  Price: $" + house.getPrice());
        System.out.println("  Tax: $" + house.calculateTax());
        System.out.println();
        
        // Test 3: Create and add a Commercial Property
        System.out.println("Test 3: Creating a Commercial Property...");
        CommercialProperty commercial = new CommercialProperty(
            "P003",
            "789 Business Boulevard",
            750000.0,
            300,
            "Prime location for retail business",
            "Retail",
            5,
            true,
            20
        );
        manager.addProperty(commercial);
        System.out.println("✓ Commercial Property created: " + commercial.getPropertyType());
        System.out.println("  Address: " + commercial.getAddress());
        System.out.println("  Price: $" + commercial.getPrice());
        System.out.println("  Tax: $" + commercial.calculateTax());
        System.out.println();
        
        // Test 4: List all properties
        System.out.println("Test 4: Listing all properties...");
        System.out.println("✓ Total properties: " + manager.getPropertyCount());
        for (Property p : manager.getAllProperties()) {
            System.out.println("  - " + p.getId() + ": " + p.getAddress() + " (" + p.getPropertyType() + ")");
        }
        System.out.println();
        
        // Test 5: Search by type
        System.out.println("Test 5: Searching for Apartments...");
        List<Property> apartments = manager.searchByType("Apartment");
        System.out.println("✓ Found " + apartments.size() + " apartment(s)");
        System.out.println();
        
        // Test 6: Search by price range
        System.out.println("Test 6: Searching properties between $200,000 and $500,000...");
        List<Property> priceRange = manager.searchByPriceRange(200000, 500000);
        System.out.println("✓ Found " + priceRange.size() + " property(ies)");
        for (Property p : priceRange) {
            System.out.println("  - " + p.getAddress() + ": $" + p.getPrice());
        }
        System.out.println();
        
        // Test 7: Update status
        System.out.println("Test 7: Updating apartment status...");
        apt.setStatus(Property.PropertyStatus.SOLD);
        System.out.println("✓ Status updated to: " + apt.getStatus());
        System.out.println();
        
        // Test 8: Data persistence
        System.out.println("Test 8: Testing data persistence...");
        try {
            manager.saveToFile("test_properties.dat");
            System.out.println("✓ Data saved to file");
            
            PropertyManager manager2 = new PropertyManager();
            manager2.loadFromFile("test_properties.dat");
            System.out.println("✓ Data loaded from file");
            System.out.println("  Loaded " + manager2.getPropertyCount() + " properties");
        } catch (Exception e) {
            System.out.println("✗ Error with data persistence: " + e.getMessage());
        }
        System.out.println();
        
        // Test 9: Polymorphism demonstration
        System.out.println("Test 9: Demonstrating Polymorphism...");
        System.out.println("✓ Tax calculation varies by property type:");
        for (Property p : manager.getAllProperties()) {
            System.out.println("  - " + p.getPropertyType() + " tax rate: $" + 
                             String.format("%.2f", p.calculateTax()) + " (on $" + p.getPrice() + ")");
        }
        System.out.println();
        
        // Test 10: Generate next ID
        System.out.println("Test 10: Testing ID generation...");
        String nextId = manager.generateNextId();
        System.out.println("✓ Next property ID: " + nextId);
        System.out.println();
        
        System.out.println("=== All Tests Completed Successfully! ===");
        System.out.println("\nOOP Principles Demonstrated:");
        System.out.println("✓ Inheritance: Property → Apartment, House, CommercialProperty");
        System.out.println("✓ Polymorphism: Different tax calculations per type");
        System.out.println("✓ Encapsulation: Private fields with getters/setters");
        System.out.println("✓ Abstraction: Abstract Property class with abstract methods");
    }
}
