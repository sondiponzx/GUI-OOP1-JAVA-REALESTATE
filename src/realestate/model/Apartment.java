package realestate.model;

/**
 * Apartment class - extends Property
 * Demonstrates OOP principles: Inheritance, Polymorphism
 */
public class Apartment extends Property {
    private static final long serialVersionUID = 1L;
    
    private int floor;
    private int bedrooms;
    private int bathrooms;
    private boolean hasBalcony;
    private boolean hasParkingSpace;
    
    public Apartment(String id, String address, double price, int size, String description,
                    int floor, int bedrooms, int bathrooms, boolean hasBalcony, boolean hasParkingSpace) {
        super(id, address, price, size, description);
        this.floor = floor;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    public int getBedrooms() {
        return bedrooms;
    }
    
    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }
    
    public int getBathrooms() {
        return bathrooms;
    }
    
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
    
    public boolean hasBalcony() {
        return hasBalcony;
    }
    
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }
    
    public boolean hasParkingSpace() {
        return hasParkingSpace;
    }
    
    public void setHasParkingSpace(boolean hasParkingSpace) {
        this.hasParkingSpace = hasParkingSpace;
    }
    
    @Override
    public String getPropertyType() {
        return "Apartment";
    }
    
    @Override
    public double calculateTax() {
        // Simple tax calculation: 1.5% of property price
        return getPrice() * 0.015;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" [Floor: %d, Beds: %d, Baths: %d, Balcony: %s, Parking: %s]",
            floor, bedrooms, bathrooms, hasBalcony ? "Yes" : "No", hasParkingSpace ? "Yes" : "No");
    }
}
