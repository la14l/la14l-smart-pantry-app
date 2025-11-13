package pantryAppPackage;

public class Item {
  //attributes
  private String Item_ID,Item_Name,Item_Category,Item_ExpDate,Item_Unit; 
  private int Item_Quantity,Item_Threshold;

public Item(){} //default constructor 
  
//full constructor 
public Item(String ID,String name,String category,int quantity,String unit,int threshold,String expd){ 
this.Item_ID= ID;
this.Item_Name= name;
this.Item_Category= category;
this.Item_Quantity= quantity;
this.Item_Unit= unit;
this.Item_Threshold= threshold;
this.Item_ExpDate= expd;
}
  //setters and getters
  public void setItem_Name(String name){
    this.Item_Name= name;
  }
  public String getItem_Name(){
    return this.Item_Name;
  }
  public void setItem_Category(String category){
    this.Item_Category= category;
  }
  public String getItem_Category(){
    return this.Item_Category;
  }
  public void setItem_Quantity(int quantity){
    this.Item_Quantity= quantity;
  }
  public int getItem_Quantity(){
    return this.Item_Quantity;
  }
  public void setItem_Unit(String unit){
    this.Item_Unit= unit;
  }
  public String getItem_Unit(){
    return this.Item_Unit;
  }
  public void setItem_Threshold(int threshold){
    this.Item_Threshold= threshold;
  }
  public int getItem_Threshold(){
    return this.Item_Threshold;
  }
  public void setItem_ExpDate(String expd){
    this.Item_ExpDate= expd;
  }
  public String getItem_ExpDate(){
    return this.Item_ExpDate;
  }
  //only getter for ID because it has to be unique and shouldnt change after creation 
  public String getItem_ID(){
    return this.Item_ID;
  }
  
//method to check if item is low in stock 
  public boolean isLowStock(){
    //if the threshold is negative its invalid
    if(Item_Threshold<0){
      System.out.print("Item threshold cannot be negative");
       return false;
       }
    //a negative quantity of an item is treated as a low in stock item
    if(Item_Quantity<0){
      return true;
    }
    //when item quantity is less than threshold then item is low in stock 
    if(Item_Quantity<Item_Threshold){
      return true;
    }
    //otherwise its not low in stock
    return false; 
  }
  
  //method to check if an item is about to expire 
  public boolean isAboutToExpire(String Todays_Date){
    //if the item doesnt have expiry date then it should return false 
    if(Item_ExpDate.equals("")||Item_ExpDate==null){
      return false;
    }
    //asking user for todays date
    //Scanner inputDate =new Scanner(System.in);
    //System.out.print("Enter today's date please (Y/M/D):");
   // String TodaysDate= inputDate.nextLine();
    //user must enter date in correct format 
    if(Todays_Date.length() != 10||Item_ExpDate.length()!= 10){
      System.out.print("Enter correct date format YYYY/MM/DD");
      return false;
    }
    
    //splitting the expiry date and todays date from user output
    String[] todayParts = Todays_Date.split("/");
    String[] expParts = Item_ExpDate.split("/");
    
    //getting each part of the date from the string 
    //parsing each string into integer for calculation
    int tYear = Integer.parseInt(todayParts[0]);
    int tMonth = Integer.parseInt(todayParts[1]);
    int tDay = Integer.parseInt(todayParts[2]);
    int eYear = Integer.parseInt(expParts[0]);
    int eMonth = Integer.parseInt(expParts[1]);
    int eDay = Integer.parseInt(expParts[2]);
    
    //difference in dates 
    //first case is if the items are made in the same year
    if (eYear==tYear){
      //check if they're in the same month
       if(eMonth==tMonth){
         int daysLeft=eDay-tDay;
         //if the daysleft between 0 and 15 then its about to expire
         if(daysLeft>=0 && daysLeft<=15){
           return true;
         }
       }
      //items in the same month 
      else if(eMonth==tMonth+1){
        int daysLeft=(30-tDay)+eDay;
        if(daysLeft<=15){
          return true;
        }
      }
    }
    return false;
  }
         
         /*if(Daysleft<0){
           System.out.print("already expired");
           return false;*/
  
  
}  
       


  
