package pantryAppPackage;

public class User {
    String name, email, phone, password, ID;

    // Default constructor
    public User() {}

    // Full constructor
    public User(String n, String e, String ph, String pw, String id) {
        name = n;
        email = e;
        phone = ph;
        password = pw;
        ID = id;
    }

    // Registers users using name, email, phone, password, and an auto-generated ID
    public void register(String n, String e, String ph, String pw) {
        // To do
    }

    // Logs in users based on name, email, phone, password
    public void login(String n, String e, String ph, String pw) {
        // To do
    }

    // Logs out of the current User based on ID
    public void logout(String id) {
        // To do
    }


    // Getters
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public String getPassword() {return password;}
    public String getID() {return ID;}
    // Setters
    public void setName(String n) {name = n;}
    public void setEmail(String e) {email = e;}
    public void setPhone(String ph) {phone = ph;}
    public void setPassword(String pw) {password = pw;}
    public void setID(String id) {ID = id;}

}
