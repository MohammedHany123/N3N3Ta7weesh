package repository;

import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepository implements Serializable {
    private final String filename = "users.ser";
    private List<User> users;

    public FileUserRepository() {
        users = loadUsers();
    }

    public void save(User user) {
       addUser(user);
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> loadUsers() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<User>) stream.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<User> getAllUsers() {
        return users;
    }
}