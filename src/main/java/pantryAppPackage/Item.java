package pantryAppPackage;

public class Item {
  //attributes
  private String Item_ID,Item_Name,Item_Category,Item_Expdate,Item_Unit; 
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
this.Item_Expdate= expd;
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
  
//method to check if item is low in stock (
  public boolean isLowStock(){
//if quantity<threshold return true else false 
    //lina u have to check that the quantities and threshold is not <0 and if its 0 then its not low in stock
  }
  //method to check if an item is about to expire if less than 15 days (lina u have to use search)
  public boolean isAboutToExpire(){
    /*lina if the item doesnt have expiry date then it should return false 
    //find the number of days between today and the expiry date 
    // if daysleft <=15 and daysleft>=0 then its about to expire 
    //if daysleft<0 then its already expired 
    //if daysleft>15 then not close to expiry*/
  }


  
}
