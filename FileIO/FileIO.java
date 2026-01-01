package FileIO;

import java.io.*;
import java.util.Scanner;
import Entity.Property;

public class FileIO{
    public static void loadFromFile(Property[] properties){
        Scanner scanner = null;
        try{
            File file = new File("FileIO/Store.txt");
            if(!file.exists()){           
                return;
            }
            scanner = new Scanner(file);
            int idx = 0;
            while(scanner.hasNextLine() && idx < properties.length){
                String line = scanner.nextLine();
                if(line.isEmpty()){
                    continue;
                }
                String[] parts = line.split(",");
                if(parts.length == 6){
                    String propertyID = parts[0];
                    String location = parts[1];
                    String type = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    double size = Double.parseDouble(parts[4]);
                    String status = parts[5];
                    properties[idx] = new Property(propertyID, location, type, price, size, status);
                    idx++;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

public static void saveChangesToFile(Property[] properties){
    try{
        FileWriter writer = new FileWriter("FileIO/Store.txt");
        for(int i = 0; i < properties.length; i++){
            if(properties[i] != null){
                Property p = properties[i];
                writer.write(p.getPropertyID() + "," + p.getLocation() + "," + p.getType() + "," +
                             p.getPrice() + "," + p.getSize() + "," + p.getStatus() + "\n");
            }
        }
        writer.close();
    }
    catch(Exception e){
        System.out.println("Error saving to file: " + e.getMessage());
    }
}

}

