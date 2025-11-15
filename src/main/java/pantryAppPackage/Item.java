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
        //when item quantity is less than a threshold, then item is low in stock
        return itemQuantity < itemThreshold;
    }

    //method to check if an item is about to expire
    public boolean isAboutToExpire(String currentDate) {
        //if the item doesn't have an expiry date, then it should return false
        if (itemExpDate == null || itemExpDate.isEmpty()) {
            return false;
        }

        //asking user for today's date
        //Scanner inputDate = new Scanner(System.in);
        //System.out.print("Enter today's date please (Y/M/D): ");
        //String currentDate = inputDate.nextLine();
        //System.out.print("Enter expiry date (Y/M/D): ");
        //String expDate = input.nextLine();

        //user must enter date in the correct format
        if (currentDate.length() != 10 || itemExpDate.length() != 10) {
            System.out.print("Enter correct date format YYYY/MM/DD");
            return false;
        }

        //splitting the expiry date and today's date from user output
        String[] todayParts = currentDate.split("/");
        String[] expParts = itemExpDate.split("/");

        //getting each part of the date from the string
        //parsing each string into integer for calculation
        int tYear = Integer.parseInt(todayParts[0]);
        int tMonth = Integer.parseInt(todayParts[1]);
        int tDay = Integer.parseInt(todayParts[2]);
        int eYear = Integer.parseInt(expParts[0]);
        int eMonth = Integer.parseInt(expParts[1]);
        int eDay = Integer.parseInt(expParts[2]);

        //the difference in dates
        //first case is if the items are made in the same year
        if (eYear == tYear) {
            //check if they're in the same month
            if (eMonth == tMonth) {
                int daysLeft = eDay - tDay;
                //if the days left between 0 and 15, then its about to expire
                return daysLeft >= 0 && daysLeft <= 15;
            }
            //items in different months but could still have a 15 day difference
            else if (eMonth == tMonth + 1) {
                int daysLeft = (30 - tDay) + eDay;
                return daysLeft <= 15;
            }
        }
        return false;
    }

         /*if(Days left<0){
           System.out.print("already expired");
           return false;*/
}




