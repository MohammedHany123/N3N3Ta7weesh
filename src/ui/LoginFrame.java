package ui;

import model.User;
import repository.FileUserRepository;
import service.*;
import javax.swing.*;
import java.awt.*;

/**
 * The login frame for user authentication.
 * <p>
 * Allows the user to enter credentials and log in.
 * </p>
 */
public class LoginFrame extends JFrame {
    private final AuthService authService;
    private final FileUserRepository repository;

    /**
     * Constructs the LoginFrame.
     */
    public LoginFrame() {        
        setTitle("N3N3Ta7weesh - Login");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color Palette
        Color bgColor = new Color(0xCBF3F0);        // Light Aqua
        Color buttonColor = new Color(0x2EC4B6);    // Teal
        Color buttonHover = new Color(0xFFBF69);    // Light Orange
        Color welcomeColor = new Color(0x2A4759);   // Orange
        Color textColor = Color.BLACK;


        repository = new FileUserRepository();
        authService = new AuthService(repository,
                new EmailValidation(),
                new UsernameValidation(),
                new PasswordValidation(),
                new PhoneValidation());

        JLabel welcomeLabel = new JLabel("Login to N3N3 Ta7weesh!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(welcomeColor);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Go to Register");

        // Button styling
        for (JButton btn : new JButton[]{loginButton, registerButton}) {
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

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(bgColor);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(bgColor);
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            User user = repository.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new DashboardFrame(user, repository).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterFrame(authService, repository).setVisible(true);
            dispose();
        });
    }
}