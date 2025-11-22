package pantryAppPackage;

import java.util.List;

public interface DashboardHelpers {

    // find an item by ID
    default Item findByID(List<Item> items, String itemID) {
        // items and ID must exist 
        if (items == null || itemID == null) {
            return null;
        }
      
        // loop through list 
        for (Item item : items) {
            // check for equal ID 
            if (item.getItemID().equals(itemID)) { // method name is getItemID()
                return item;
            }
        }
        return null;
    }

    // validating inputs 
    // strings can't be empty 
    default void cantEmpty(String something, String someValue) {
        if (someValue == null || someValue.equals("")) {
            throw new IllegalArgumentException(something + " can't be empty");
        }
    }

    // numbers can't be negative 
    default void cantBeNeg(String something, int someValue) {
        if (someValue < 0) {
            throw new IllegalArgumentException(something + " can't be negative");
        }
    }
}
