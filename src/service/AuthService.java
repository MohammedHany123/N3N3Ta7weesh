package service;
import model.User;
import repository.FileUserRepository;

/**
 * Handles authentication and registration logic for users,
 * including validation and OTP verification.
 */
public class AuthService {
    private final FileUserRepository userRepository;
    private final ValidationStrategy[] validators;
    private String currentOTP;

    /**
     * Gets the current OTP.
     * @return the current OTP string
     */
    public String getCurrentOTP() {
        return currentOTP;
    }

     /**
     * Constructs an AuthService with the given user repository and validators.
     * @param userRepository the user repository
     * @param validators     the validation strategies to use
     */
    public AuthService(FileUserRepository userRepository, ValidationStrategy... validators) {
        this.userRepository = userRepository;
        this.validators = validators;
    }

    /**
     * Registers a new user after validating all fields and generating an OTP.
     * @param user the user to register
     * @return true if registration is successful, false otherwise
     */
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

    /**
     * Generates a random OTP and simulates sending it to the user's phone.
     * @param phone the user's phone number
     */
    private void generateOTP(String phone) {
        currentOTP = String.valueOf((int) (Math.random() * 900000) + 100000);
        System.out.println("[SIM] SMS sent to " + phone + " with OTP: " + currentOTP);
    }

    /**
     * Verifies the entered OTP against the generated OTP.
     * @param otp the OTP entered by the user
     * @return true if the OTP matches, false otherwise
     */
    public boolean verifyOTP(String otp) {
        return currentOTP != null && currentOTP.equals(otp);
    }

    /**
     * Completes the registration by saving the user and clearing the OTP.
     * @param user the user to save
     */
    public void completeRegistration(User user) {
        userRepository.save(user);
        currentOTP = null;
    }

    /**
     * Attempts to log in a user with the given email and password.
     * @param email    the user's email
     * @param password the user's password
     * @return the User if credentials are correct, otherwise null
     */
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}