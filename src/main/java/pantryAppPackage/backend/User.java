package pantryAppPackage.backend;

public class User {
    private String name, email, phone, password, ID;

    // Default constructor
    public User() {}

    // Full constructor
    public User(String ID, String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.ID = ID;
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
