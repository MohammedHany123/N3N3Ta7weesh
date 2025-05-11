package service;
import model.User;

/**
 * Validates a user's email address format.
 */
public class EmailValidation implements ValidationStrategy {
    /**
     * Validates the user's email address.
     * @param user the user to validate
     * @return true if the email is valid, false otherwise
     */
    @Override
    public boolean validate(User user) {
        return user.getEmail().matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}