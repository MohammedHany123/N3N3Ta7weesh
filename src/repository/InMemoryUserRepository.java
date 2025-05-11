package repository;
import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 * Provides an in-memory implementation of the UserRepository interface.
 */
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    /**
     * Saves a user to the repository.
     * @param user the user to save
     */
    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    /**
     * Gets all users in the repository.
     * @return the list of users
     */
    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }
}
