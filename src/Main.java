import model.User;
import repository.InMemoryUserRepository;
import service.*;

public class Main {
    public static void main(String[] args) {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        AuthService authService = new AuthService(repository,
            new EmailValidation(),
            new UsernameValidation(),
            new PhoneValidation(),
            new PasswordValidation()
        );

        // test case 1
        User newUser = new User(
            "test@example.com",
            "john_doe123",
            "SecurePass123!",
            "+201234567890123"
        );

        System.out.println("\n--- Testing Registration ---");
        //System.out.println(newUser.getEmail());

        if (authService.register(newUser)) {
            System.out.println("Validation passed. OTP sent.");
            
            // Get generated OTP from service
            String simulatedOTP = authService.getCurrentOTP(); 
            System.out.println("Entering OTP: " + simulatedOTP);
            
            if (authService.verifyOTP(simulatedOTP)) {
                authService.completeRegistration(newUser);
                System.out.println("Registration successful!");
            } else {
                System.out.println("Invalid OTP!");
            }
        } else {
            System.out.println("Validation failed!");
        }

        // test login for test case 1
        System.out.println("\n--- Testing Login ---");
        User loggedInUser = authService.login("test@example.com", "SecurePass123!");
        if (loggedInUser != null) {
            System.out.println("Login successful! Redirecting to dashboard...");
        } else {
            System.out.println("Invalid credentials!");
        }
    }
}