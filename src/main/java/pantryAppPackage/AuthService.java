package pantryAppPackage;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AuthService {
    private final Path usersFile = Paths.get("src/main/resources/users.txt"); // Create a Path object to make working with the file easier.
    private final ArrayList<User> users = new ArrayList<>(); // Declare an ArrayList of current registered users in the file.
    private static User currentUser; // Declare a reference to point to the current user object.

    public AuthService() {
        loadUsers(); // Load the users in the users.txt file to the ArrayList on instantiation of the class.
    }

    public void register(String name, String email, String phone, String password) throws IllegalStateException{
        // Check if the email already exists.
        if (findByEmail(email) != null) throw new IllegalStateException("Email already registered");

        String ID = generateID(); // Autogenerate the ID.
        User user = new User(ID, name, email, phone, password); // Create a user object.


        users.add(user); // Add the object to the ArrayList.
        saveUsers(); // Store the user in users.txt.
        currentUser = user; // Set as current user
    }

    public boolean login(String email, String password) {
        User user = findByEmail(email); // Look for the user by email. Null if DNE.
        if (user != null && password.equals(user.getPassword())) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public static void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    private User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    private String generateID() {
        if (users.isEmpty()) {
            return "0000";
        } else {
            String maxID = "0000";
            for (User user : users) {
                if (Integer.parseInt(user.getID()) > Integer.parseInt(maxID)) maxID = user.getID();
            }
            return String.format("%04d", Integer.parseInt(maxID) + 1);
        }
    }

    private void loadUsers() {
        try {
            if (!Files.exists(usersFile)) {
                Files.createDirectories(usersFile.getParent()); // Create the resources file if DNE.
                Files.createFile(usersFile); // Create the users.txt file if DNE.
                return;
            }
            try (BufferedReader br = Files.newBufferedReader(usersFile)) { // Automatically closes br
                String line = br.readLine();
                while (line != null) {
                    // Format: id,name,email,phone,password
                    String[] t = line.split("\\|"); // Split the line into the data
                    users.add(new User(t[0], t[1], t[2], t[3], t[4])); // Create an anonymous object of User and add it to the user's list.
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users.txt", e);
        }
    }

    private void saveUsers() {
        try (BufferedWriter bw = Files.newBufferedWriter(usersFile)) { // Automatically closes bw
            for (User u : users) {
                bw.write(String.join("|", u.getID(), u.getName(), u.getEmail(), u.getPhone(), u.getPassword()));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save users.txt", e);
        }
    }
}
