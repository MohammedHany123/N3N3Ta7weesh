package service;
import model.User;

public class UsernameValidation implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        String username = user.getUsername();
        return username.length() >= 3 && username.length() <= 50 && 
               username.matches("^[a-zA-Z0-9_-]+$");
    }
}