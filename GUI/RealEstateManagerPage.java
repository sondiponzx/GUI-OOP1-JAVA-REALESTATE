package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.Property;


public class RealEstateManagerPage extends JFrame implements ActionListener {
    JLabel titleLabel, idJLabel, locationLabel,TypeLabel,priceLabel,sizLabel,statusLabel;
    JTextField idJTextField, locationJTextField,priceJTextField,sizeJTextField,statusJTextField;
    JRadioButton commercialRadio, residentialRadio;
    ButtonGroup typeGroup;
    JButton addButton, updateButton, deleteButton, clearButton,saveButton, loadButton;
    JTextArea screen;

    Property[] properties = new Property[100];

    public RealEstateManagerPage(){
       
        super("Real Estate Property Storing");
        this.setSize(900,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,900,750);
        panel.setBackground(new Color(200,220,255));
        this.add(panel);
        
        titleLabel = new JLabel("Real Estate Property Storing ");
        titleLabel.setBounds(250, 10, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0,0,128));
        panel.add(titleLabel);
        
        idJLabel = new JLabel("Property ID:");
        idJLabel.setBounds(250, 60, 100, 25);
        idJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(idJLabel);
        
        idJTextField = new JTextField();
        idJTextField.setBounds(350, 60, 200, 25);
        panel.add(idJTextField);
        
        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(250, 100, 100, 25);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(locationLabel);
        
        locationJTextField = new JTextField();
        locationJTextField.setBounds(350, 100, 200, 25);
        panel.add(locationJTextField);
        
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(250, 140, 100, 25);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(priceLabel);
        
        priceJTextField = new JTextField();
        priceJTextField.setBounds(350, 140, 200, 25);   
        panel.add(priceJTextField);
        
        sizLabel = new JLabel("Size:"); 
        sizLabel.setBounds(250, 180, 100, 25);
        sizLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(sizLabel);
        
        sizeJTextField = new JTextField();
        sizeJTextField.setBounds(350, 180, 200, 25);
        panel.add(sizeJTextField);
        
        statusLabel = new JLabel("Status:");
        statusLabel.setBounds(250, 220, 100, 25);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(statusLabel);
        
        statusJTextField = new JTextField();
        statusJTextField.setBounds(350, 220, 200, 25);
        panel.add(statusJTextField);
        
        TypeLabel = new JLabel("Type:");
        TypeLabel.setBounds(250, 260, 100, 25);
        TypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(TypeLabel);
        
        commercialRadio = new JRadioButton("Commercial");
        commercialRadio.setBounds(350, 260, 100, 25);
        commercialRadio.setBackground(new Color(200,220,255));
        commercialRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(commercialRadio);
        
        residentialRadio = new JRadioButton("Residential");
        residentialRadio.setBounds(460, 260, 100, 25);
        residentialRadio.setBackground(new Color(200,220,255));
        residentialRadio.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(residentialRadio);
        
        typeGroup = new ButtonGroup();
        typeGroup.add(commercialRadio);
        typeGroup.add(residentialRadio);
        commercialRadio.setSelected(true);
        
        addButton = new JButton("Add");
        addButton.setBounds(150, 310, 100, 30);
        addButton.addActionListener(this);
        panel.add(addButton);
        
        updateButton = new JButton("Update");
        updateButton.setBounds(260, 310, 100, 30);
        updateButton.addActionListener(this);
        panel.add(updateButton);
        
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(370, 310, 100, 30);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);
        
        clearButton = new JButton("Clear");
        clearButton.setBounds(480, 310, 100, 30);
        clearButton.addActionListener(this);
        panel.add(clearButton);
        
        saveButton = new JButton("Save");
        saveButton.setBounds(590, 310, 100, 30);
        saveButton.addActionListener(this);
        panel.add(saveButton);
		
        loadButton = new JButton("Load");
        loadButton.setBounds(700, 310, 100, 30);
        loadButton.setBackground(new Color(52, 152, 219));
        loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("Arial", Font.BOLD, 12));
        loadButton.setFocusPainted(false);
        loadButton.addActionListener(this);
        panel.add(loadButton);
        
        screen = new JTextArea();
        screen.setFont(new Font("Monospaced", Font.PLAIN, 12));
        screen.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(50, 360, 775, 290);
        panel.add(scrollPane);

        FileIO.FileIO.loadFromFile(properties);
        updateScreen();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

void updateScreen() {
        String all = "";
        all += String.format("%-6s| %-12s| %-15s| %-12s| %-12s| %-10s| %-10s\n", 
                             "S.No", "Property ID", "Location", "Type", "Price", "Size", "Status");// Column Titles
        all += "---------------------------------------------------------------------------------------------\n";
        int serial = 1;
        for (Property p : properties) {
            if (p != null) {
                all += String.format("%-6d| %-12s| %-15s| %-12s| %-12.2f| %-10.2f| %-10s\n",
                                     serial, p.getPropertyID(), p.getLocation(), p.getType(), 
                                     p.getPrice(), p.getSize(), p.getStatus());
                serial++;
            }
        }
        all += "\nTotal Properties: " + (serial - 1);
        screen.setText(all);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addButton) {
                String id = idJTextField.getText();
                String type = commercialRadio.isSelected() ? "Commercial" : "Residential";
                if (!idExists(id)) {
                    properties[getEmptyIndex()] = new Property(id, locationJTextField.getText(), type, 
                            Double.parseDouble(priceJTextField.getText()), Double.parseDouble(sizeJTextField.getText()), statusJTextField.getText());
                    updateScreen();
                } else JOptionPane.showMessageDialog(this, "ID already exists!");
            } else if (e.getSource() == updateButton) {
                String id = idJTextField.getText();
                String type = commercialRadio.isSelected() ? "Commercial" : "Residential";
                int idx = getIndexById(id);
                if (idx != -1) {
                    properties[idx].setLocation(locationJTextField.getText());
                    properties[idx].setType(type);
                    properties[idx].setPrice(Double.parseDouble(priceJTextField.getText()));
                    properties[idx].setSize(Double.parseDouble(sizeJTextField.getText()));
                    properties[idx].setStatus(statusJTextField.getText());
                    updateScreen();
                } else JOptionPane.showMessageDialog(this, "Property not found!");
            } else if (e.getSource() == deleteButton) {
                String id = idJTextField.getText();
                int idx = getIndexById(id);
                if (idx != -1) {
                    properties[idx] = null;
                    updateScreen();
                } else JOptionPane.showMessageDialog(this, "Property not found!");
            } else if (e.getSource() == saveButton) {
                FileIO.FileIO.saveChangesToFile(properties);
                JOptionPane.showMessageDialog(this, "Saved Successfully!");
            } else if (e.getSource() == clearButton) {
                idJTextField.setText(""); locationJTextField.setText(""); 
                commercialRadio.setSelected(true);
                priceJTextField.setText(""); sizeJTextField.setText(""); statusJTextField.setText("");
            } else if (e.getSource() == loadButton) {
                String id = idJTextField.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter a Property ID to load!");
                    return;
                }
                int idx = getIndexById(id);
                if (idx != -1) {
                    Property p = properties[idx];
                    idJTextField.setText(p.getPropertyID());
                    locationJTextField.setText(p.getLocation());
                    if (p.getType().equalsIgnoreCase("Commercial")) {
                        commercialRadio.setSelected(true);
                    } else {
                        residentialRadio.setSelected(true);
                    }
                    priceJTextField.setText(String.valueOf(p.getPrice()));
                    sizeJTextField.setText(String.valueOf(p.getSize()));
                    statusJTextField.setText(p.getStatus());
                } else JOptionPane.showMessageDialog(this, "Property not found! Enter a valid Property ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid numeric values for Price and Size!");
        }
    }

    int getEmptyIndex() {
        for (int i = 0; i < properties.length; i++)
            if (properties[i] == null) return i;
        return -1;
    }

    int getIndexById(String id) {
        for (int i = 0; i < properties.length; i++)
            if (properties[i] != null && properties[i].getPropertyID().equals(id)) return i;
        return -1;
    }

   boolean idExists(String id){
    return getIndexById(id) != -1;
   }

    int getIndexBySerial(int serial) {
        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i] != null) {
                count++;
                if (count == serial) return i;
            }
        }
        return -1;
    }
}
