package pantryAppPackage;

public class Item {
    //attributes
    private String itemID, itemName, itemCategory, itemExpDate, itemUnit;
    private int itemQuantity, itemThreshold;

    //default constructor
    public Item() {
    }

    //full constructor
    public Item(String itemID, String itemName, String itemCategory, int itemQuantity, String itemUnit, int itemThreshold, String itemExpDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
        this.itemUnit = itemUnit;
        this.itemThreshold = itemThreshold;
        this.itemExpDate = itemExpDate;
    }

    //setters and getters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemExpDate() {
        return itemExpDate;
    }

    public void setItemExpDate(String itemExpDate) {
        this.itemExpDate = itemExpDate;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemThreshold() {
        return itemThreshold;
    }

    public void setItemThreshold(int itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

    //only getter for ID because it has to be unique and shouldn't change after creation
    public String getItemID() {
        return this.itemID;
    }

    //method to check if item is low in stock
    public boolean isLowStock() {
        //if quantity<threshold return true else false
        // lina u have to check that the quantities and threshold are not <0 and if its 0 then it's not low in stock +check if we need an interface
        return true;
    }

    //method to check if an item is about to expire
    public boolean isAboutToExpire() {
        //if the item doesn't have an expiry date, then it should return false
        //find the number of days between today and the expiry date
        // if days left <=15 and days left>=0 then its about to expire
        //if days left < 0 then its already expired
        //if days left > 15 then not close to expiry
        return true;
    }
}
