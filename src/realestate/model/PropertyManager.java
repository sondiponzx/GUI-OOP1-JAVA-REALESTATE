package realestate.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PropertyManager class - manages the collection of properties
 * Demonstrates OOP principles: Encapsulation, Data Management
 */
public class PropertyManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Property> properties;
    
    public PropertyManager() {
        this.properties = new ArrayList<>();
    }
    
    // Add a property
    public void addProperty(Property property) {
        properties.add(property);
    }
    
    // Remove a property by ID
    public boolean removeProperty(String id) {
        return properties.removeIf(p -> p.getId().equals(id));
    }
    
    // Get property by ID
    public Property getPropertyById(String id) {
        return properties.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // Get all properties
    public List<Property> getAllProperties() {
        return new ArrayList<>(properties);
    }
    
    // Search properties by address
    public List<Property> searchByAddress(String address) {
        return properties.stream()
                .filter(p -> p.getAddress().toLowerCase().contains(address.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Search properties by type
    public List<Property> searchByType(String type) {
        return properties.stream()
                .filter(p -> p.getPropertyType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    // Search properties by price range
    public List<Property> searchByPriceRange(double minPrice, double maxPrice) {
        return properties.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    // Search properties by status
    public List<Property> searchByStatus(Property.PropertyStatus status) {
        return properties.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }
    
    // Get total number of properties
    public int getPropertyCount() {
        return properties.size();
    }
    
    // Save properties to file
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(properties);
        }
    }
    
    // Load properties from file
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            properties = (List<Property>) ois.readObject();
        }
    }
    
    // Generate next property ID
    public String generateNextId() {
        if (properties.isEmpty()) {
            return "P001";
        }
        int maxId = properties.stream()
                .map(p -> p.getId())
                .filter(id -> id.matches("P\\d+"))
                .map(id -> Integer.parseInt(id.substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
        return String.format("P%03d", maxId + 1);
    }
}
