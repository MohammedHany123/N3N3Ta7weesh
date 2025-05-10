package ui;

import repository.FileUserRepository;
import service.*;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("N3N3Ta7weesh - Home");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color palette
        Color bgColor = new Color(0xCBF3F0);        // Light Aqua
        Color panelColor = new Color(0xFFFFFF);     // White
        Color buttonColor = new Color(0x2EC4B6);    // Teal
        Color buttonHover = new Color(0xFFBF69);    // Light Orange
        Color welcomeColor = new Color(0x2A4759);   // Orange
        Color textColor = Color.BLACK;

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to N3N3 Ta7weesh!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setForeground(welcomeColor);

        // Buttons
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        // Button styling
        for (JButton btn : new JButton[]{loginBtn, registerBtn}) {
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
                    btn.setForeground(Color.WHITE);
                }
            });
        }

        // Panel setup
        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(panelColor);

        panel.add(welcomeLabel);
        panel.add(loginBtn);
        panel.add(registerBtn);

        setContentPane(panel);
        getContentPane().setBackground(bgColor);

        FileUserRepository repository = new FileUserRepository();
        AuthService authService = new AuthService(
            repository,
            new EmailValidation(),
            new UsernameValidation(),
            new PasswordValidation(),
            new PhoneValidation()
        );

        loginBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        registerBtn.addActionListener(e -> {
            new RegisterFrame(authService, repository).setVisible(true);
            dispose();
        });
    }
}
