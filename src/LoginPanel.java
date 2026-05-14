import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPanel extends RoundedPanel {

    public LoginPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 500));

        setBackground(Color.WHITE);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(35, 45, 35, 45));

        // ================= LOGO =================
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                110,
                110,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= TITLE =================
        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Arial", Font.BOLD, 18));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setForeground(Color.BLACK);

        // ================= EMAIL FIELD =================
        JTextField email = new JTextField("Email");

        email.setMaximumSize(new Dimension(300, 42));

        email.setPreferredSize(new Dimension(300, 42));

        email.setFont(new Font("Arial", Font.PLAIN, 13));

        email.setForeground(Color.GRAY);

        email.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 190, 190),
                                1,
                                true),
                        new EmptyBorder(10, 12, 10, 12)));

        email.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                if (email.getText().equals("Email")) {
                    email.setText("");
                    email.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                if (email.getText().isEmpty()) {
                    email.setText("Email");
                    email.setForeground(Color.GRAY);
                }
            }
        });

        // ================= PASSWORD FIELD =================
        JPasswordField password = new JPasswordField("Password");

        password.setMaximumSize(new Dimension(300, 42));

        password.setPreferredSize(new Dimension(300, 42));

        password.setFont(new Font("Arial", Font.PLAIN, 13));

        password.setForeground(Color.GRAY);

        password.setEchoChar((char) 0);

        password.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(190, 190, 190),
                                1,
                                true),
                        new EmptyBorder(10, 12, 10, 12)));

        password.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                String pass = String.valueOf(password.getPassword());

                if (pass.equals("Password")) {
                    password.setText("");
                    password.setForeground(Color.BLACK);
                    password.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                String pass = String.valueOf(password.getPassword());

                if (pass.isEmpty()) {
                    password.setText("Password");
                    password.setForeground(Color.GRAY);
                    password.setEchoChar((char) 0);
                }
            }
        });

        // ================= FORGOT PASSWORD =================
        JButton forgotBtn =
                new JButton("Forgot your username or password?");

        forgotBtn.setFont(new Font("Arial", Font.PLAIN, 10));

        forgotBtn.setForeground(new Color(150, 150, 150));

        forgotBtn.setBorderPainted(false);

        forgotBtn.setContentAreaFilled(false);

        forgotBtn.setFocusPainted(false);

        forgotBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        forgotBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        forgotBtn.addActionListener(e -> frame.showPage("forgot"));

        // ================= LOGIN BUTTON =================
        JButton loginBtn = new JButton("Log in");

        loginBtn.setBackground(new Color(166, 95, 0));

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFocusPainted(false);

        loginBtn.setBorderPainted(false);

        loginBtn.setFont(new Font("Arial", Font.BOLD, 13));

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.setMaximumSize(new Dimension(300, 42));

        loginBtn.setPreferredSize(new Dimension(300, 42));

        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginBtn.addActionListener(e -> {

            String enteredEmail = email.getText().trim();
            String enteredPassword = String.valueOf(password.getPassword());

            // ================= VALIDATION =================
            if (enteredEmail.isEmpty()
                    || enteredEmail.equals("Email")
                    || enteredPassword.isEmpty()
                    || enteredPassword.equals("Password")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.");
                return;
            }

            // ================= CHECK IF USER EXISTS =================
            if (!UserSession.userNames.containsKey(enteredEmail)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account not found. Please sign up first.");
                return;
            }

            // ================= PASSWORD CHECK =================
            if (!enteredPassword.equals(UserSession.password)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect password.");
                return;
            }

            // ================= LOGIN SUCCESS =================
            UserSession.email = enteredEmail;
            UserSession.password = enteredPassword;

            String name = UserSession.userNames.get(enteredEmail);

            if (name == null || name.isEmpty()) {
                name = "User";
            }

            new DashboardFrame(name);

            frame.dispose();
        });

        // ================= SIGN UP =================
        JPanel signupPanel = new JPanel();

        signupPanel.setOpaque(false);

        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.X_AXIS));

        JLabel signupText = new JLabel("Are you a new User? ");

        signupText.setFont(new Font("Arial", Font.PLAIN, 11));

        JButton signupBtn = new JButton("Sign up");

        signupBtn.setFont(new Font("Arial", Font.BOLD, 11));

        signupBtn.setForeground(new Color(109, 8, 0));

        signupBtn.setBorderPainted(false);

        signupBtn.setContentAreaFilled(false);

        signupBtn.setFocusPainted(false);

        signupBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signupBtn.addActionListener(e -> frame.showPage("signup"));

        signupPanel.add(signupText);

        signupPanel.add(signupBtn);

        signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= ADD COMPONENTS =================
        add(Box.createVerticalGlue());

        add(logo);

        add(Box.createRigidArea(new Dimension(0, 8)));

        add(title);

        add(Box.createRigidArea(new Dimension(0, 28)));

        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(email);

        add(Box.createRigidArea(new Dimension(0, 18)));

        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(password);

        add(Box.createRigidArea(new Dimension(0, 4)));

        add(forgotBtn);

        add(Box.createRigidArea(new Dimension(0, 12)));

        add(loginBtn);

        add(Box.createRigidArea(new Dimension(0, 15)));

        add(signupPanel);

        add(Box.createVerticalGlue());
    }
}