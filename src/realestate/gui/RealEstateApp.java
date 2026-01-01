package realestate.gui;

import realestate.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

/**
 * Main GUI application window for Real Estate Management System
 * Demonstrates GUI programming with Java Swing
 */
public class RealEstateApp extends JFrame {
    private PropertyManager propertyManager;
    private JTable propertyTable;
    private DefaultTableModel tableModel;
    private JTextArea detailsArea;
    
    private static final String DATA_FILE = "properties.dat";
    
    public RealEstateApp() {
        propertyManager = new PropertyManager();
        initializeGUI();
        loadData();
        refreshPropertyTable();
    }
    
    private void initializeGUI() {
        setTitle("Real Estate Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        createMenuBar();
        
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with title and buttons
        JPanel topPanel = createTopPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Center panel with split pane
        JSplitPane splitPane = createSplitPane();
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        // Bottom panel with statistics
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        saveItem.addActionListener(e -> saveData());
        loadItem.addActionListener(e -> {
            loadData();
            refreshPropertyTable();
        });
        exitItem.addActionListener(e -> {
            saveData();
            System.exit(0);
        });
        
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // Property menu
        JMenu propertyMenu = new JMenu("Property");
        JMenuItem addItem = new JMenuItem("Add Property");
        JMenuItem deleteItem = new JMenuItem("Delete Property");
        JMenuItem updateStatusItem = new JMenuItem("Update Status");
        
        addItem.addActionListener(e -> showAddPropertyDialog());
        deleteItem.addActionListener(e -> deleteSelectedProperty());
        updateStatusItem.addActionListener(e -> updatePropertyStatus());
        
        propertyMenu.add(addItem);
        propertyMenu.add(deleteItem);
        propertyMenu.add(updateStatusItem);
        
        // Search menu
        JMenu searchMenu = new JMenu("Search");
        JMenuItem searchByAddressItem = new JMenuItem("Search by Address");
        JMenuItem searchByTypeItem = new JMenuItem("Search by Type");
        JMenuItem searchByPriceItem = new JMenuItem("Search by Price Range");
        JMenuItem showAllItem = new JMenuItem("Show All");
        
        searchByAddressItem.addActionListener(e -> searchByAddress());
        searchByTypeItem.addActionListener(e -> searchByType());
        searchByPriceItem.addActionListener(e -> searchByPriceRange());
        showAllItem.addActionListener(e -> refreshPropertyTable());
        
        searchMenu.add(searchByAddressItem);
        searchMenu.add(searchByTypeItem);
        searchMenu.add(searchByPriceItem);
        searchMenu.addSeparator();
        searchMenu.add(showAllItem);
        
        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(propertyMenu);
        menuBar.add(searchMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Real Estate Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton addButton = new JButton("Add Property");
        JButton deleteButton = new JButton("Delete Property");
        JButton refreshButton = new JButton("Refresh");
        
        addButton.addActionListener(e -> showAddPropertyDialog());
        deleteButton.addActionListener(e -> deleteSelectedProperty());
        refreshButton.addActionListener(e -> refreshPropertyTable());
        
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return topPanel;
    }
    
    private JSplitPane createSplitPane() {
        // Table panel
        String[] columnNames = {"ID", "Type", "Address", "Price", "Size (m²)", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        propertyTable = new JTable(tableModel);
        propertyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        propertyTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showPropertyDetails();
            }
        });
        
        JScrollPane tableScrollPane = new JScrollPane(propertyTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Property List"));
        
        // Details panel
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        detailsScrollPane.setBorder(BorderFactory.createTitledBorder("Property Details"));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, detailsScrollPane);
        splitPane.setDividerLocation(600);
        
        return splitPane;
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        
        JLabel statusLabel = new JLabel("Ready");
        bottomPanel.add(statusLabel);
        
        return bottomPanel;
    }
    
    private void refreshPropertyTable() {
        tableModel.setRowCount(0);
        List<Property> properties = propertyManager.getAllProperties();
        for (Property property : properties) {
            Object[] row = {
                property.getId(),
                property.getPropertyType(),
                property.getAddress(),
                String.format("$%.2f", property.getPrice()),
                property.getSize(),
                property.getStatus()
            };
            tableModel.addRow(row);
        }
    }
    
    private void showPropertyDetails() {
        int selectedRow = propertyTable.getSelectedRow();
        if (selectedRow == -1) {
            detailsArea.setText("");
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        Property property = propertyManager.getPropertyById(id);
        
        if (property != null) {
            StringBuilder details = new StringBuilder();
            details.append("═══════════════════════════════════════════\n");
            details.append("           PROPERTY DETAILS\n");
            details.append("═══════════════════════════════════════════\n\n");
            details.append(String.format("ID:          %s\n", property.getId()));
            details.append(String.format("Type:        %s\n", property.getPropertyType()));
            details.append(String.format("Address:     %s\n", property.getAddress()));
            details.append(String.format("Price:       $%.2f\n", property.getPrice()));
            details.append(String.format("Size:        %d m²\n", property.getSize()));
            details.append(String.format("Status:      %s\n", property.getStatus()));
            details.append(String.format("Description: %s\n", property.getDescription()));
            details.append(String.format("Tax:         $%.2f\n\n", property.calculateTax()));
            
            // Type-specific details
            if (property instanceof Apartment) {
                Apartment apt = (Apartment) property;
                details.append("───────────── Apartment Details ─────────────\n");
                details.append(String.format("Floor:          %d\n", apt.getFloor()));
                details.append(String.format("Bedrooms:       %d\n", apt.getBedrooms()));
                details.append(String.format("Bathrooms:      %d\n", apt.getBathrooms()));
                details.append(String.format("Balcony:        %s\n", apt.hasBalcony() ? "Yes" : "No"));
                details.append(String.format("Parking Space:  %s\n", apt.hasParkingSpace() ? "Yes" : "No"));
            } else if (property instanceof House) {
                House house = (House) property;
                details.append("─────────────── House Details ───────────────\n");
                details.append(String.format("Bedrooms:       %d\n", house.getBedrooms()));
                details.append(String.format("Bathrooms:      %d\n", house.getBathrooms()));
                details.append(String.format("Garden:         %s\n", house.hasGarden() ? "Yes" : "No"));
                details.append(String.format("Garage:         %s\n", house.hasGarage() ? "Yes" : "No"));
                details.append(String.format("Number of Floors: %d\n", house.getNumberOfFloors()));
            } else if (property instanceof CommercialProperty) {
                CommercialProperty comm = (CommercialProperty) property;
                details.append("────────── Commercial Property Details ──────────\n");
                details.append(String.format("Business Type:  %s\n", comm.getBusinessType()));
                details.append(String.format("Rooms:          %d\n", comm.getNumberOfRooms()));
                details.append(String.format("Parking:        %s\n", comm.hasParking() ? "Yes" : "No"));
                details.append(String.format("Parking Spaces: %d\n", comm.getParkingSpaces()));
            }
            
            details.append("\n═══════════════════════════════════════════\n");
            detailsArea.setText(details.toString());
        }
    }
    
    private void showAddPropertyDialog() {
        AddPropertyDialog dialog = new AddPropertyDialog(this, propertyManager);
        dialog.setVisible(true);
        refreshPropertyTable();
    }
    
    private void deleteSelectedProperty() {
        int selectedRow = propertyTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a property to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete property " + id + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            propertyManager.removeProperty(id);
            refreshPropertyTable();
            detailsArea.setText("");
            JOptionPane.showMessageDialog(this, "Property deleted successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void updatePropertyStatus() {
        int selectedRow = propertyTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a property to update.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        Property property = propertyManager.getPropertyById(id);
        
        if (property != null) {
            Property.PropertyStatus[] statuses = Property.PropertyStatus.values();
            Property.PropertyStatus newStatus = (Property.PropertyStatus) JOptionPane.showInputDialog(
                this, "Select new status:", "Update Status",
                JOptionPane.QUESTION_MESSAGE, null, statuses, property.getStatus());
            
            if (newStatus != null) {
                property.setStatus(newStatus);
                refreshPropertyTable();
                showPropertyDetails();
                JOptionPane.showMessageDialog(this, "Status updated successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    private void searchByAddress() {
        String address = JOptionPane.showInputDialog(this, "Enter address to search:");
        if (address != null && !address.trim().isEmpty()) {
            List<Property> results = propertyManager.searchByAddress(address);
            displaySearchResults(results);
        }
    }
    
    private void searchByType() {
        String[] types = {"Apartment", "House", "Commercial"};
        String type = (String) JOptionPane.showInputDialog(this, "Select property type:",
            "Search by Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
        
        if (type != null) {
            List<Property> results = propertyManager.searchByType(type);
            displaySearchResults(results);
        }
    }
    
    private void searchByPriceRange() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField minField = new JTextField();
        JTextField maxField = new JTextField();
        
        panel.add(new JLabel("Minimum Price:"));
        panel.add(minField);
        panel.add(new JLabel("Maximum Price:"));
        panel.add(maxField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Search by Price Range",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                double min = Double.parseDouble(minField.getText());
                double max = Double.parseDouble(maxField.getText());
                List<Property> results = propertyManager.searchByPriceRange(min, max);
                displaySearchResults(results);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void displaySearchResults(List<Property> results) {
        tableModel.setRowCount(0);
        for (Property property : results) {
            Object[] row = {
                property.getId(),
                property.getPropertyType(),
                property.getAddress(),
                String.format("$%.2f", property.getPrice()),
                property.getSize(),
                property.getStatus()
            };
            tableModel.addRow(row);
        }
        
        JOptionPane.showMessageDialog(this, "Found " + results.size() + " property(ies).",
            "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void saveData() {
        try {
            propertyManager.saveToFile(DATA_FILE);
            JOptionPane.showMessageDialog(this, "Data saved successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try {
                propertyManager.loadFromFile(DATA_FILE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showAboutDialog() {
        String message = "Real Estate Management System\n\n" +
                        "Version 1.0\n\n" +
                        "A Java GUI application demonstrating OOP principles:\n" +
                        "- Inheritance\n" +
                        "- Polymorphism\n" +
                        "- Encapsulation\n" +
                        "- Abstraction\n\n" +
                        "© 2026 University OOP1 Project";
        
        JOptionPane.showMessageDialog(this, message, "About",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            RealEstateApp app = new RealEstateApp();
            app.setVisible(true);
        });
    }
}
