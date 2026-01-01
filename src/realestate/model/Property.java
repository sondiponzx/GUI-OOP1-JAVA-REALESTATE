package realestate.model;

import java.io.Serializable;

/**
 * Abstract base class for all property types
 * Demonstrates OOP principles: Abstraction, Encapsulation
 */
public abstract class Property implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Private fields - Encapsulation
    private String id;
    private String address;
    private double price;
    private int size; // in square meters
    private String description;
    private PropertyStatus status;
    
    public enum PropertyStatus {
        AVAILABLE, SOLD, RENTED, PENDING
    }
    
    // Constructor
    public Property(String id, String address, double price, int size, String description) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
        this.status = PropertyStatus.AVAILABLE;
    }
    
    // Getters and Setters - Encapsulation
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public PropertyStatus getStatus() {
        return status;
    }
    
    public void setStatus(PropertyStatus status) {
        this.status = status;
    }
    
    // Abstract method - to be implemented by subclasses (Polymorphism)
    public abstract String getPropertyType();
    
    // Abstract method for calculating tax
    public abstract double calculateTax();
    
    @Override
    public String toString() {
        return String.format("%s - %s (%s) - $%.2f - %dm² - %s", 
            id, address, getPropertyType(), price, size, status);
    }
}
