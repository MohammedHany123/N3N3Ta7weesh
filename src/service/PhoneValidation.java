package service;
import model.User;

/**
 * Validates a user's phone number format.
 */
public class PhoneValidation implements ValidationStrategy {
    /**
     * Validates the user's phone number.
     * @param user the user to validate
     * @return true if the phone number is valid, false otherwise
     */
    @Override
    public boolean validate(User user) {
        return user.getPhone().matches("^\\+\\d{10,15}$");
    }
}