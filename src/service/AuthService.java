package service;
import model.User;
import repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository;
    private final ValidationStrategy[] validators;
    private String currentOTP;
    public String getCurrentOTP() {
        return currentOTP;
    }
    public AuthService(UserRepository userRepository, ValidationStrategy... validators) {
        this.userRepository = userRepository;
        this.validators = validators;
    }

    public boolean register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        
        for (ValidationStrategy validator : validators) {
            //if (!validator.validate(user)) return false;
            boolean result = validator.validate(user);
            System.out.println(validator.getClass().getSimpleName() + " => " + result);
            if (!result) return false;
        }
        
        generateOTP(user.getPhone());
        return true;
    }

    private void generateOTP(String phone) {
        currentOTP = String.valueOf((int) (Math.random() * 900000) + 100000);
        System.out.println("[SIM] SMS sent to " + phone + " with OTP: " + currentOTP);
    }

    public boolean verifyOTP(String otp) {
        return currentOTP != null && currentOTP.equals(otp);
    }

    public void completeRegistration(User user) {
        userRepository.save(user);
        currentOTP = null;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}