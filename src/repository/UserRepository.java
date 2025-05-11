package repository;

import model.User;

/**
 * Interface for user repository operations.
 */
public interface UserRepository {
    /**
     * Saves a user to the repository.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Finds a user by email.
     * @param email the user's email
     * @return the User if found, otherwise null
     */
    User findByEmail(String email);
}