package pantryAppPackage;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DashboardBackend {

    // Returns the number of items the user has
    public static int getNumberOfLines(String filePath, String userID) throws FileNotFoundException{
        int numberOfLines = 0;
        Scanner database = new Scanner(new FileReader(filePath));

        while (database.hasNextLine()) {
            String entry = database.nextLine();
            String[] fields = entry.split(",");
            if (fields[0].equals(userID) && fields.length > 1) {
                numberOfLines++;
            }
        }

        database.close();

        return numberOfLines;
    }

    // Gets all the items the user has in pantry
    public static String[][] readTableDataFromFile(String filePath, String userID) throws FileNotFoundException {
        int numberOfRows = getNumberOfLines(filePath, userID);
        String[][] data = new String[numberOfRows][];

        Scanner database = new Scanner(new FileReader(filePath));

        int row = 0;
        while (row < numberOfRows && database.hasNextLine()) {
            String entry = database.nextLine();
            String[] fields = entry.split(",");
            if (!fields[0].equals(userID)) {
                continue;
            } else {
                data[row] = Arrays.copyOfRange(fields, 1, fields.length);
                row++;
            }
        }

        database.close();

        return data;
    }

    // Called via the Add Item Button in the pantry panel
    public static void writeItemDataIntoPantryFile(String filePath, String userID, String[] itemData) throws IOException {
        try (PrintWriter pantryFile = new PrintWriter(new FileWriter(filePath, true))) {
            String entry = userID + "," + String.join(",", itemData);
            pantryFile.println(entry);
        }
    }

    // Delete an item from the pantry
    public static void removeItemDataFromPantryFile(String filePath, String userID, String[] itemData) throws IOException {
        Scanner database = new Scanner(new FileReader(filePath));
        StringBuilder updatedContent = new StringBuilder();

        while (database.hasNextLine()) {
            String entry = database.nextLine();
            String[] fields = entry.split(",");

            if (!(fields[0].equals(userID) && fields[1].equals(itemData[0]))) {
                updatedContent.append(entry).append("\n");
            }
        }
        database.close();

        PrintWriter pantryFile = new PrintWriter(filePath);
        pantryFile.print(updatedContent);
        pantryFile.close();
    }

    // Edit an item in the pantry
    public static void editItemDataFromPantryFile(String filePath, String userID, String[] itemData) throws IOException {
        Scanner database = new Scanner(new FileReader(filePath));
        StringBuilder updatedContent = new StringBuilder();

        while (database.hasNextLine()) {
            String entry = database.nextLine();
            String[] fields = entry.split(",");

            if (!(fields[0].equals(userID) && fields[1].equals(itemData[0]))) {
                updatedContent.append(entry).append("\n");
            } else {
                String updatedEntry = fields[0] + "," + String.join(",", itemData);
                updatedContent.append(updatedEntry).append("\n");
            }
        }
        database.close();

        PrintWriter pantryFile = new PrintWriter(filePath);
        pantryFile.print(updatedContent);
        pantryFile.close();
    }

}
