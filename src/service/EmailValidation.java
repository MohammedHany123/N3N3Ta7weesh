package service;
import model.User;

public class EmailValidation implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        return user.getEmail().matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}