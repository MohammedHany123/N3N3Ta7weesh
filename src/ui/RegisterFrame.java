package ui;

import model.User;
import repository.FileUserRepository;
import service.AuthService;
import javax.swing.*;
import java.awt.*;

/**
 * The registration frame for new users.
 * <p>
 * Allows the user to enter registration details and create an account.
 * </p>
 */
public class RegisterFrame extends JFrame {
    /**
     * Constructs the RegisterFrame.
     * @param authService the authentication service
     * @param repository the user repository for saving data
     */
    public RegisterFrame(AuthService authService, FileUserRepository repository) {
        setTitle("N3N3Ta7weesh - Register");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);        // Light Aqua
        Color buttonColor = new Color(0x2EC4B6);    // Teal
        Color buttonHover = new Color(0xFFBF69);    // Light Orange
        Color welcomeColor = new Color(0x2A4759);   // Orange
        Color textColor = Color.BLACK;

        JLabel welcomeLabel = new JLabel("Register for N3N3 Ta7weesh!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(welcomeColor);

        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneField = new JTextField();

        JButton registerButton = new JButton("Register");
        JButton logInButton = new JButton("Go to Login");

        // Button styling
        for (JButton btn : new JButton[]{registerButton, logInButton}) {
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonHover);
                    btn.setForeground(textColor);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor);
                    btn.setForeground(textColor);
                }
            });
        }

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(bgColor);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(registerButton);
        buttonPanel.add(logInButton);

        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String phone = phoneField.getText();

            User newUser = new User(email, username, password, phone);

            if (authService.register(newUser)) {
                String otp = authService.getCurrentOTP();
                String inputOtp = JOptionPane.showInputDialog(this, "OTP sent. Enter OTP:");
                if (inputOtp != null && authService.verifyOTP(inputOtp)) {
                    authService.completeRegistration(newUser);
                    repository.saveUsers();
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    new HomeFrame().setVisible(true); // ← back to home
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid OTP.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Validation failed or user exists.");
            }
        });

        logInButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
