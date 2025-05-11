package service;
import model.User;

/**
 * Validates a user's username for length and allowed characters.
 */
public class UsernameValidation implements ValidationStrategy {
    /**
     * Validates the user's username.
     * @param user the user to validate
     * @return true if the username is valid, false otherwise
     */
    @Override
    public boolean validate(User user) {
        String username = user.getUsername();
        return username.length() >= 3 && username.length() <= 50 && 
               username.matches("^[a-zA-Z0-9_-]+$");
    }
}