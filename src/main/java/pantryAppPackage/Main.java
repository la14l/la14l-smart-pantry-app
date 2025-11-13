package pantryAppPackage;

public class Main {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        new MainFrame(auth);
    }
}
