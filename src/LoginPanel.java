import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPanel extends RoundedPanel {

    public LoginPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 420));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(30, 45, 30, 45));

        // LOGO
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TITLE
        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Serif", Font.BOLD, 34));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // USERNAME
        JTextField username = new JTextField();

        username.setMaximumSize(new Dimension(300, 40));

        username.setBorder(
                BorderFactory.createTitledBorder("Username / Email"));

        // PASSWORD
        JPasswordField password = new JPasswordField();

        password.setMaximumSize(new Dimension(300, 40));

        password.setBorder(
                BorderFactory.createTitledBorder("Password"));

        // LOGIN BUTTON
        JButton loginBtn = new JButton("Log in");

        loginBtn.setBackground(new Color(166, 95, 0));

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFocusPainted(false);

        loginBtn.setMaximumSize(new Dimension(300, 40));

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // FORGOT BUTTON
        JButton forgotBtn = new JButton("Forgot your password?");

        forgotBtn.setBorderPainted(false);

        forgotBtn.setContentAreaFilled(false);

        forgotBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        forgotBtn.addActionListener(e -> {
            frame.showPage("forgot");
        });

        // SIGNUP BUTTON
        JButton signupBtn = new JButton("Sign up");

        signupBtn.setBorderPainted(false);

        signupBtn.setContentAreaFilled(false);

        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupBtn.addActionListener(e -> {
            frame.showPage("signup");
        });

        // ADD COMPONENTS
        add(Box.createVerticalGlue());

        add(logo);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(title);

        add(Box.createRigidArea(new Dimension(0, 30)));

        add(username);

        add(Box.createRigidArea(new Dimension(0, 15)));

        add(password);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(loginBtn);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(forgotBtn);

        add(signupBtn);

        add(Box.createVerticalGlue());
    }
}