package realestate.gui;

import realestate.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for adding new properties
 */
public class AddPropertyDialog extends JDialog {
    private PropertyManager propertyManager;
    
    private JComboBox<String> typeComboBox;
    private JTextField idField;
    private JTextField addressField;
    private JTextField priceField;
    private JTextField sizeField;
    private JTextArea descriptionArea;
    
    // Type-specific panels
    private JPanel apartmentPanel;
    private JPanel housePanel;
    private JPanel commercialPanel;
    
    // Apartment fields
    private JTextField floorField;
    private JTextField bedroomsAptField;
    private JTextField bathroomsAptField;
    private JCheckBox balconyCheck;
    private JCheckBox parkingSpaceCheck;
    
    // House fields
    private JTextField bedroomsHouseField;
    private JTextField bathroomsHouseField;
    private JCheckBox gardenCheck;
    private JCheckBox garageCheck;
    private JTextField floorsField;
    
    // Commercial fields
    private JTextField businessTypeField;
    private JTextField roomsField;
    private JCheckBox parkingCheck;
    private JTextField parkingSpacesField;
    
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    public AddPropertyDialog(Frame parent, PropertyManager propertyManager) {
        super(parent, "Add New Property", true);
        this.propertyManager = propertyManager;
        
        initializeDialog();
    }
    
    private void initializeDialog() {
        setSize(600, 700);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Common fields panel
        JPanel commonPanel = createCommonFieldsPanel();
        mainPanel.add(commonPanel, BorderLayout.NORTH);
        
        // Type-specific panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        apartmentPanel = createApartmentPanel();
        housePanel = createHousePanel();
        commercialPanel = createCommercialPanel();
        
        cardPanel.add(apartmentPanel, "Apartment");
        cardPanel.add(housePanel, "House");
        cardPanel.add(commercialPanel, "Commercial");
        
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createCommonFieldsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("General Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Property Type
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Property Type:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        typeComboBox = new JComboBox<>(new String[]{"Apartment", "House", "Commercial"});
        typeComboBox.addActionListener(e -> {
            String selected = (String) typeComboBox.getSelectedItem();
            cardLayout.show(cardPanel, selected);
        });
        panel.add(typeComboBox, gbc);
        
        // ID
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Property ID:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        idField = new JTextField(propertyManager.generateNextId());
        idField.setEditable(false);
        panel.add(idField, gbc);
        
        // Address
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Address:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        addressField = new JTextField(20);
        panel.add(addressField, gbc);
        
        // Price
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Price ($):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        priceField = new JTextField(20);
        panel.add(priceField, gbc);
        
        // Size
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Size (m²):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        sizeField = new JTextField(20);
        panel.add(sizeField, gbc);
        
        // Description
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Description:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        panel.add(scrollPane, gbc);
        
        return panel;
    }
    
    private JPanel createApartmentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Apartment Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Floor:"), gbc);
        gbc.gridx = 1;
        floorField = new JTextField(15);
        panel.add(floorField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Bedrooms:"), gbc);
        gbc.gridx = 1;
        bedroomsAptField = new JTextField(15);
        panel.add(bedroomsAptField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Bathrooms:"), gbc);
        gbc.gridx = 1;
        bathroomsAptField = new JTextField(15);
        panel.add(bathroomsAptField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Has Balcony:"), gbc);
        gbc.gridx = 1;
        balconyCheck = new JCheckBox();
        panel.add(balconyCheck, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Has Parking Space:"), gbc);
        gbc.gridx = 1;
        parkingSpaceCheck = new JCheckBox();
        panel.add(parkingSpaceCheck, gbc);
        
        return panel;
    }
    
    private JPanel createHousePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("House Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Bedrooms:"), gbc);
        gbc.gridx = 1;
        bedroomsHouseField = new JTextField(15);
        panel.add(bedroomsHouseField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Bathrooms:"), gbc);
        gbc.gridx = 1;
        bathroomsHouseField = new JTextField(15);
        panel.add(bathroomsHouseField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Has Garden:"), gbc);
        gbc.gridx = 1;
        gardenCheck = new JCheckBox();
        panel.add(gardenCheck, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Has Garage:"), gbc);
        gbc.gridx = 1;
        garageCheck = new JCheckBox();
        panel.add(garageCheck, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Number of Floors:"), gbc);
        gbc.gridx = 1;
        floorsField = new JTextField(15);
        panel.add(floorsField, gbc);
        
        return panel;
    }
    
    private JPanel createCommercialPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Commercial Property Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Business Type:"), gbc);
        gbc.gridx = 1;
        businessTypeField = new JTextField(15);
        panel.add(businessTypeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Number of Rooms:"), gbc);
        gbc.gridx = 1;
        roomsField = new JTextField(15);
        panel.add(roomsField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Has Parking:"), gbc);
        gbc.gridx = 1;
        parkingCheck = new JCheckBox();
        panel.add(parkingCheck, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Parking Spaces:"), gbc);
        gbc.gridx = 1;
        parkingSpacesField = new JTextField(15);
        panel.add(parkingSpacesField, gbc);
        
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton addButton = new JButton("Add Property");
        JButton cancelButton = new JButton("Cancel");
        
        addButton.addActionListener(e -> addProperty());
        cancelButton.addActionListener(e -> dispose());
        
        panel.add(addButton);
        panel.add(cancelButton);
        
        return panel;
    }
    
    private void addProperty() {
        try {
            // Validate common fields
            String id = idField.getText().trim();
            String address = addressField.getText().trim();
            String priceStr = priceField.getText().trim();
            String sizeStr = sizeField.getText().trim();
            String description = descriptionArea.getText().trim();
            
            if (address.isEmpty() || priceStr.isEmpty() || sizeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double price = Double.parseDouble(priceStr);
            int size = Integer.parseInt(sizeStr);
            
            Property property = null;
            String type = (String) typeComboBox.getSelectedItem();
            
            switch (type) {
                case "Apartment":
                    property = createApartment(id, address, price, size, description);
                    break;
                case "House":
                    property = createHouse(id, address, price, size, description);
                    break;
                case "Commercial":
                    property = createCommercial(id, address, price, size, description);
                    break;
            }
            
            if (property != null) {
                propertyManager.addProperty(property);
                JOptionPane.showMessageDialog(this, "Property added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for numeric fields.",
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding property: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Apartment createApartment(String id, String address, double price, int size, String description) {
        int floor = Integer.parseInt(floorField.getText().trim());
        int bedrooms = Integer.parseInt(bedroomsAptField.getText().trim());
        int bathrooms = Integer.parseInt(bathroomsAptField.getText().trim());
        boolean hasBalcony = balconyCheck.isSelected();
        boolean hasParkingSpace = parkingSpaceCheck.isSelected();
        
        return new Apartment(id, address, price, size, description,
            floor, bedrooms, bathrooms, hasBalcony, hasParkingSpace);
    }
    
    private House createHouse(String id, String address, double price, int size, String description) {
        int bedrooms = Integer.parseInt(bedroomsHouseField.getText().trim());
        int bathrooms = Integer.parseInt(bathroomsHouseField.getText().trim());
        boolean hasGarden = gardenCheck.isSelected();
        boolean hasGarage = garageCheck.isSelected();
        int floors = Integer.parseInt(floorsField.getText().trim());
        
        return new House(id, address, price, size, description,
            bedrooms, bathrooms, hasGarden, hasGarage, floors);
    }
    
    private CommercialProperty createCommercial(String id, String address, double price, int size, String description) {
        String businessType = businessTypeField.getText().trim();
        int rooms = Integer.parseInt(roomsField.getText().trim());
        boolean hasParking = parkingCheck.isSelected();
        int parkingSpaces = Integer.parseInt(parkingSpacesField.getText().trim());
        
        return new CommercialProperty(id, address, price, size, description,
            businessType, rooms, hasParking, parkingSpaces);
    }
}
