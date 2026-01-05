package FileIO;

import java.io.*;
import java.util.Scanner;
import Entity.Property;

public class FileIO{
    public static void loadFromFile(Property[] properties){
        File file = new File("FileIO/Store.txt");
        //if(!file.exists()) return;
        
        try(Scanner scanner = new Scanner(file)){
            int idx = 0;
            while(scanner.hasNextLine() && idx < properties.length){
                String[] parts = scanner.nextLine().split(",");
                if(parts.length == 6){
                    properties[idx++] = new Property(parts[0], parts[1], parts[2], 
                        Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), parts[5]);
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

