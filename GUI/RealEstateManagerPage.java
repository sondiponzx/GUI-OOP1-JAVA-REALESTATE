package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.Property;


public class RealEstateManagerPage extends JFrame implements ActionListener {
    JLabel titleLabel, idJLabel, locationLabel,TypeLabel,priceLabel,sizLabel,statusLabel,filterLabel;
    JTextField idJTextField, locationJTextField,TypeJTextField,priceJTextField,sizeJTextField,statusJTextField,filterJTextField;
    JButton addButton, updateButton, deleteButton, clearButton,saveButton, loadButton;
    JTextArea screen;
    JComboBox<String> filterComboBox;

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

        // Title Label
        titleLabel = new JLabel("Real Estate Property Storing ");
        titleLabel.setBounds(250, 10, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0,0,128));
        panel.add(titleLabel);

        //Property ID
        idJLabel = new JLabel("Property ID:");
        idJLabel.setBounds(250, 60, 100, 25);
        idJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(idJLabel);

        idJTextField = new JTextField();
        idJTextField.setBounds(350, 60, 200, 25);
        panel.add(idJTextField);

        //Location
        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(250, 100, 100, 25);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(locationLabel);

        locationJTextField = new JTextField();
        locationJTextField.setBounds(350, 100, 200, 25);
        panel.add(locationJTextField);

        //Type
        TypeLabel = new JLabel("Type:");
        TypeLabel.setBounds(250, 140, 100, 25);
        TypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(TypeLabel);

        TypeJTextField = new JTextField();
        TypeJTextField.setBounds(350, 140, 200, 25);
        panel.add(TypeJTextField);


        //Price
        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(250, 180, 100, 25);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(priceLabel);

        priceJTextField = new JTextField();
        priceJTextField.setBounds(350, 180, 200, 25);   
        panel.add(priceJTextField);

        //Size
        sizLabel = new JLabel("Size:"); 
        sizLabel.setBounds(250, 220, 100, 25);
        sizLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(sizLabel);
        sizeJTextField = new JTextField();
        sizeJTextField.setBounds(350, 220, 200, 25);
        panel.add(sizeJTextField);
        
        //Status
        statusLabel = new JLabel("Status:");
        statusLabel.setBounds(250, 260, 100, 25);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(statusLabel);
        statusJTextField = new JTextField();
        statusJTextField.setBounds(350, 260, 200, 25);
        panel.add(statusJTextField);

        // Buttons
        addButton = new JButton("Add Property");
        addButton.setBounds(250, 310, 120, 30);
        addButton.addActionListener(this);
        panel.add(addButton);
        updateButton = new JButton("Update ");
        updateButton.setBounds(380, 310, 120, 30);
        updateButton.addActionListener(this);
        panel.add(updateButton);
        deleteButton = new JButton("Delete ");
        deleteButton.setBounds(510, 310, 120, 30);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);
        clearButton = new JButton("Clear ");
        clearButton.setBounds(640, 310, 120, 30);
        clearButton.addActionListener(this);
        panel.add(clearButton);
        saveButton = new JButton("Save ");
        saveButton.setBounds(360, 360, 120, 30);
        saveButton.addActionListener(this);
        panel.add(saveButton);
        loadButton = new JButton("Load ");
        loadButton.setBounds(520, 360, 120, 30);
        loadButton.addActionListener(this);
        panel.add(loadButton);
        // Filter
        filterLabel = new JLabel("Filter by Type:");
        filterLabel.setBounds(290, 410, 100, 25);
        filterLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        panel.add(filterLabel);
        filterComboBox = new JComboBox<>(new String[]{"All", "Apartment", "House", "Plot"});
        filterComboBox.setBounds(390, 410, 200, 25);
        filterComboBox.addActionListener(this);
        panel.add(filterComboBox);
        // Screen Area
        screen = new JTextArea();
        screen.setFont(new Font("Monospaced", Font.PLAIN, 12));
        screen.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(50, 450, 775,200);
        panel.add(scrollPane);
        




    }


    }

