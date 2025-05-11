package repository;

import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistent storage and retrieval of users using file serialization.
 */
public class FileUserRepository implements Serializable {
    private final String filename = "users.ser";
    private List<User> users;

    /**
     * Constructs a FileUserRepository and loads users from file.
     */
    public FileUserRepository() {
        users = loadUsers();
    }

    /**
     * Saves a user to the repository.
     * @param user the user to save
     */
    public void save(User user) {
       addUser(user);
    }

    /**
     * Adds a user to the repository and saves all users.
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    /**
     * Finds a user by email.
     * @param email the user's email
     * @return the User if found, otherwise null
     */
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    /**
     * Saves all users to the file.
     */
    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads users from the file.
     * @return the list of users
     */
    private List<User> loadUsers() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<User>) stream.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

     /**
     * Gets all users in the repository.
     * @return the list of users
     */
    public List<User> getAllUsers() {
        return users;
    }
}