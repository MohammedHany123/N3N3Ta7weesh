package service;
import model.User;

/**
 * Strategy interface for validating user fields.
 */
public interface ValidationStrategy {
    /**
     * Validates a user field.
     * @param user the user to validate
     * @return true if valid, false otherwise
     */
    boolean validate(User user);
}