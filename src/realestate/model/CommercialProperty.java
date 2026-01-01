package realestate.model;

/**
 * CommercialProperty class - extends Property
 * Demonstrates OOP principles: Inheritance, Polymorphism
 */
public class CommercialProperty extends Property {
    private static final long serialVersionUID = 1L;
    
    private String businessType;
    private int numberOfRooms;
    private boolean hasParking;
    private int parkingSpaces;
    
    public CommercialProperty(String id, String address, double price, int size, String description,
                            String businessType, int numberOfRooms, boolean hasParking, int parkingSpaces) {
        super(id, address, price, size, description);
        this.businessType = businessType;
        this.numberOfRooms = numberOfRooms;
        this.hasParking = hasParking;
        this.parkingSpaces = parkingSpaces;
    }
    
    public String getBusinessType() {
        return businessType;
    }
    
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
    
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }
    
    public boolean hasParking() {
        return hasParking;
    }
    
    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }
    
    public int getParkingSpaces() {
        return parkingSpaces;
    }
    
    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
    
    @Override
    public String getPropertyType() {
        return "Commercial";
    }
    
    @Override
    public double calculateTax() {
        // Simple tax calculation: 2.5% of property price
        return getPrice() * 0.025;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" [Business: %s, Rooms: %d, Parking: %s (%d spaces)]",
            businessType, numberOfRooms, hasParking ? "Yes" : "No", parkingSpaces);
    }
}
