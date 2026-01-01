package realestate.model;

/**
 * House class - extends Property
 * Demonstrates OOP principles: Inheritance, Polymorphism
 */
public class House extends Property {
    private static final long serialVersionUID = 1L;
    
    private int bedrooms;
    private int bathrooms;
    private boolean hasGarden;
    private boolean hasGarage;
    private int numberOfFloors;
    
    public House(String id, String address, double price, int size, String description,
                int bedrooms, int bathrooms, boolean hasGarden, boolean hasGarage, int numberOfFloors) {
        super(id, address, price, size, description);
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.hasGarden = hasGarden;
        this.hasGarage = hasGarage;
        this.numberOfFloors = numberOfFloors;
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
    
    public boolean hasGarden() {
        return hasGarden;
    }
    
    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }
    
    public boolean hasGarage() {
        return hasGarage;
    }
    
    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }
    
    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
    
    @Override
    public String getPropertyType() {
        return "House";
    }
    
    @Override
    public double calculateTax() {
        // Simple tax calculation: 2% of property price
        return getPrice() * 0.02;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" [Beds: %d, Baths: %d, Garden: %s, Garage: %s, Floors: %d]",
            bedrooms, bathrooms, hasGarden ? "Yes" : "No", hasGarage ? "Yes" : "No", numberOfFloors);
    }
}
