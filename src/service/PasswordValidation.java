package service;
import model.User;

public class PasswordValidation implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        String password = user.getPassword();
        return password.length() >= 8 && password.length() <= 16 &&
               password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$");
    }
}
