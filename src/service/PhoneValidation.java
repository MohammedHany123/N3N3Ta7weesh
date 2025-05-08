package service;
import model.User;

public class PhoneValidation implements ValidationStrategy {
    @Override
    public boolean validate(User user) {
        return user.getPhone().matches("^\\+\\d{15}$");
    }
}