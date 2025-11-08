package pantryAppPackage;

public class User {
    private String name, email, phone, password, ID;

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
    public boolean register(String n, String e, String ph, String pw) {
        // To do
        return true;
    }

    // Logs in users based on name, email, phone, password
    public boolean login(String n, String e, String ph, String pw) {
        // To do
        return true;
    }

    // Logs out of the current User based on ID
    public boolean logout(String id) {
        // To do
        return true;
    }


    // Getters
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public String getPassword() {return password;}
    public String getID() {return ID;}
    // Setters
    public void setName(String n) {this.name = n;}
    public void setEmail(String e) {this.email = e;}
    public void setPhone(String ph) {this.phone = ph;}
    public void setPassword(String pw) {this.password = pw;}
    public void setID(String id) {this.ID = id;}

}
