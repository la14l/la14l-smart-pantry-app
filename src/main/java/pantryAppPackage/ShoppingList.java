package pantryAppPackage;

public class ShoppingList {
    private int list_id, Item_Quantity, Item_Unit;
    private String Date_Created, Item_Name, Item_Status;
    private Item[] items_list; // actual list of items in the shopping list

    public ShoppingList() {}

    public ShoppingList(int id, String dateCreated) {
        this.list_id = id;
        Date_Created = dateCreated;
        items_list = new Item[0];
    }

    public void addItem(Item item, int quantity, String unit) {
        // Checks for item quantity in store first
        // If enough, update shopping list
        // Else, warning

        // check
        if (item.getItemQuantity() > quantity) {
            // update
            Item[] temp_list = new Item[items_list.length + 1];

            // manual array copy as items_list has an extra element
            System.arraycopy(items_list, 0, temp_list, 0, items_list.length);

            temp_list[items_list.length] = item;
            items_list = temp_list;
        }
        // warning
        else {throw new IllegalStateException("The quantity requested exceeds the quantity in storage");}
    }


    public void removeItem(String name, int quantity, String unit) {

    }


    public void markPurchased(String name, int quantity, String unit) {

    }


    public void saveList() {

    }




    // Setters
    public void setList_id(int list_id) {
        this.list_id = list_id;
    }
    public void setItem_Quantity(int item_Quantity) {
        Item_Quantity = item_Quantity;
    }
    public void setItem_Unit(int item_Unit) {
        Item_Unit = item_Unit;
    }
    public void setDate_Created(String date_Created) {
        Date_Created = date_Created;
    }
    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }
    public void setItem_Status(String item_Status) {
        Item_Status = item_Status;
    }

    // Getters
    public int getList_id() {
        return list_id;
    }
    public int getItem_Quantity() {
        return Item_Quantity;
    }
    public int getItem_Unit() {
        return Item_Unit;
    }
    public String getDate_Created() {
        return Date_Created;
    }
    public String getItem_Name() {
        return Item_Name;
    }
    public String getItem_Status() {
        return Item_Status;
    }
}
