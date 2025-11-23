package pantryAppPackage;

import javax.management.ObjectName;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static ArrayList<Object[]> getLowStockItemsFromPantryFile(String pantryFilePath, String userID) throws FileNotFoundException {

        ArrayList<Object[]> lowStockItemsList = new ArrayList<>();

        Scanner database = new Scanner(new FileReader(pantryFilePath));
        while (database.hasNextLine()) {
            String entry = database.nextLine();
            if (entry.isEmpty()) continue; // skip empty lines
            String[] fields = entry.split(",");
            if (fields.length < 7) continue; // skip malformed rows

            if (fields[0].equals(userID) && (Integer.parseInt(fields[4]) < Integer.parseInt(fields[6]))) {
                lowStockItemsList.add(new Object[]{fields[2], String.valueOf(Integer.parseInt(fields[6]) - Integer.parseInt(fields[4])), fields[5], false});
            }
        }

        database.close();

        return lowStockItemsList;
    }

    public static Object[][] getLowStockItemsFromShoppingFile(String shoppingListFilePath, String userID) throws FileNotFoundException {
        ArrayList<Object[]> lowStockItemsList = new ArrayList<>();
        Scanner shoppingFile = new Scanner(new FileReader(shoppingListFilePath));

        // Format: userID, shoppingListID, dateCreated, itemName, itemQuantity, itemUnit, itemStatus
        while (shoppingFile.hasNextLine()) {
            String entry = shoppingFile.nextLine();
            String[] fields = entry.split(",");
            if (fields[0].equals(userID)) {
                lowStockItemsList.add(new Object[]{fields[3], fields[4], fields[5], Boolean.parseBoolean(fields[6])});
            }
        }

        shoppingFile.close();

        Object[][] lowStockItemsArray = new Object[lowStockItemsList.size()][];
        lowStockItemsArray = lowStockItemsList.toArray(lowStockItemsArray);

        return lowStockItemsArray;
    }

//    public static String getOrCreateShoppingListID(String shoppingFilePath, String userID) throws FileNotFoundException {
//        Scanner sc = new Scanner(new FileReader(shoppingFilePath));
//
//        while (sc.hasNextLine()) {
//            String line = sc.nextLine();
//            String[] fields = line.split(",");
//
//            if (fields[0].equals(userID)) {
//                sc.close();
//                return fields[1]; // shoppingListID found
//            }
//        }
//        sc.close();
//
//        // Create a new ID if no previous list exists
//        return "SL" + (int)(Math.random() * 10000);
//    }

    public static void createOrUpdateShoppingList(String pantryFilePath, String userID, String shoppingListFilePath) throws FileNotFoundException {
        ArrayList<String[]> shoppingFileTable = new ArrayList<>();
        ArrayList<Object[]> userLowStockItemsFromPantry = getLowStockItemsFromPantryFile(pantryFilePath, userID);
        String shoppingListID = "";
        String dateCreated = "";

        // Get the current content of the shopping list
        Scanner shoppingFileContent = new Scanner(new FileReader(shoppingListFilePath));
        while (shoppingFileContent.hasNextLine()) {
            String entry = shoppingFileContent.nextLine();
            String[] shoppingFileRow = entry.split(",");
            shoppingFileTable.add(shoppingFileRow);
        }
        shoppingFileContent.close();


        boolean userHasAShoppingList = false;
        for (String[] row : shoppingFileTable) {
            if (row[0].equals(userID)) {
                userHasAShoppingList = true;
                shoppingListID = row[1];
                dateCreated = row[2];
                break;
            }
        }

        // If the user does not have a shopping list, initialize one
        if (!userHasAShoppingList) {
            shoppingListID = "SL" + (int)(Math.random() * 10000);
            for (Object[] lowStockItem : userLowStockItemsFromPantry) {
                shoppingFileTable.add(new String[]{userID, shoppingListID, LocalDate.now().toString(), lowStockItem[0].toString(), lowStockItem[1].toString(), lowStockItem[2].toString(), lowStockItem[3].toString()});
            }
            // End the function here
        }
        // If the user does have a shopping list, update it
        else {
            // Remove all existing rows for this user from shoppingFileTable
            shoppingFileTable.removeIf(row -> row[0].equals(userID));

            // Re-add all current low stock items for this user
            for (Object[] lowStockItem : userLowStockItemsFromPantry) {
                shoppingFileTable.add(new String[]{
                        userID,               // userID
                        shoppingListID,       // shoppingListID
                        dateCreated,          // original creation date
                        lowStockItem[0].toString(), // item name
                        lowStockItem[1].toString(), // quantity
                        lowStockItem[2].toString(), // unit
                        lowStockItem[3].toString()  // expiry
                });
            }
        }

        // Rewrite the updated shopping lists file
        PrintWriter writer = new PrintWriter(shoppingListFilePath);
        for (String[] row : shoppingFileTable) {
            writer.println(String.join(",", row));
        }
        writer.close();
    }

    // Make a function that when purchased is clicked, it restocks the quantity in pantry
    public static void purchaseItem(String pantryFilePath, String userID, String itemName, boolean purchased, String purchasedQuantity, String shoppingListFilePath) throws FileNotFoundException {

        ArrayList<String[]> pantryTable = new ArrayList<>();
        Scanner pantryFileContent = new Scanner(new FileReader(pantryFilePath));
        while (pantryFileContent.hasNextLine()) {
            String entry = pantryFileContent.nextLine();
            pantryTable.add(entry.split(","));
        }
        pantryFileContent.close();

        for (String[] row : pantryTable) {
            if (row[0].equals(userID) && row[2].equals(itemName)) {
                if (purchased) {
                    row[4] = String.valueOf((Integer.parseInt(row[4]) + Integer.parseInt(purchasedQuantity)));
                }
//                else {
//                    row[4] = String.valueOf((Integer.parseInt(row[4]) - Integer.parseInt(purchasedQuantity)));
//                }
            }
        }

        // Rewrite the updated pantry file
        PrintWriter writer = new PrintWriter(pantryFilePath);
        for (String[] row : pantryTable) {
            writer.println(String.join(",", row));
        }
        writer.close();

        ArrayList<String[]> shoppingFileTable = new ArrayList<>();
        Scanner shoppingFileContent = new Scanner(new FileReader(shoppingListFilePath));
        while (shoppingFileContent.hasNextLine()) {
            String entry = shoppingFileContent.nextLine();
            String[] shoppingFileRow = entry.split(",");
            shoppingFileTable.add(shoppingFileRow);
        }
        shoppingFileContent.close();

        for (String[] row : shoppingFileTable) {
            if (row[0].equals(userID) && row[3].equals(itemName)) {
                row[6] = String.valueOf(purchased);
            }
        }

        PrintWriter writer2 = new PrintWriter(shoppingListFilePath);
        for (String[] row : shoppingFileTable) {
            writer2.println(String.join(",", row));
        }
        writer2.close();
    }
}
