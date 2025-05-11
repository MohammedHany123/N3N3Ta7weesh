package service;
import model.User;

/**
 * Validates a user's password for length and complexity.
 */
public class PasswordValidation implements ValidationStrategy {
    /**
     * Validates the user's password.
     * @param user the user to validate
     * @return true if the password is valid, false otherwise
     */
    @Override
    public boolean validate(User user) {
        String password = user.getPassword();
        return password.length() >= 8 && password.length() <= 16 &&
               password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$");
    }
}
