import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupPanel extends RoundedPanel {

    public SignupPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 420));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(30, 45, 30, 45));

        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Arial", Font.BOLD, 30));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField email = new JTextField();

        email.setMaximumSize(new Dimension(300, 40));

        email.setBorder(
                BorderFactory.createTitledBorder("Username / Email"));

        JPasswordField password = new JPasswordField();

        password.setMaximumSize(new Dimension(300, 40));

        password.setBorder(
                BorderFactory.createTitledBorder("Password"));

        JButton signupBtn = new JButton("Sign up");

        signupBtn.setBackground(new Color(166, 95, 0));

        signupBtn.setForeground(Color.WHITE);

        signupBtn.setFocusPainted(false);

        signupBtn.setMaximumSize(new Dimension(300, 40));

        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginBtn = new JButton("Already a User? Log in");

        loginBtn.setBorderPainted(false);

        loginBtn.setContentAreaFilled(false);

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> {
            frame.showPage("login");
        });

        add(Box.createVerticalGlue());

        add(logo);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(title);

        add(Box.createRigidArea(new Dimension(0, 30)));

        add(email);

        add(Box.createRigidArea(new Dimension(0, 15)));

        add(password);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(signupBtn);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(loginBtn);

        add(Box.createVerticalGlue());
    }
}