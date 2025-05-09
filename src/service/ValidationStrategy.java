package service;
import model.User;

public interface ValidationStrategy {
    boolean validate(User user);
}