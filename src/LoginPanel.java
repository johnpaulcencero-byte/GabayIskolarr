import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPanel extends RoundedPanel {

    public LoginPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 500));

        setBackground(Color.WHITE);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(30, 45, 30, 45));

        // LOGO
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_AREA_AVERAGING);

        JLabel logo = new JLabel(new ImageIcon(img));

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TITLE
        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Arial", Font.BOLD, 30));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // FULL NAME
        JTextField fullName = new JTextField();

        fullName.setBorder(
                BorderFactory.createTitledBorder("Full Name"));

        fullName.setPreferredSize(new Dimension(300, 40));

        fullName.setMaximumSize(new Dimension(300, 40));

        // EMAIL
        JTextField email = new JTextField();

        email.setBorder(
                BorderFactory.createTitledBorder("Email"));

        email.setPreferredSize(new Dimension(300, 40));

        email.setMaximumSize(new Dimension(300, 40));

        // PASSWORD
        JPasswordField password = new JPasswordField();

        password.setBorder(
                BorderFactory.createTitledBorder("Password"));

        password.setPreferredSize(new Dimension(300, 40));

        password.setMaximumSize(new Dimension(300, 40));

        // LOGIN BUTTON
        JButton loginBtn = new JButton("Log in");

        loginBtn.setBackground(new Color(166, 95, 0));

        loginBtn.setForeground(Color.WHITE);

        loginBtn.setFocusPainted(false);

        loginBtn.setPreferredSize(new Dimension(300, 40));

        loginBtn.setMaximumSize(new Dimension(300, 40));

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> {

            String name = fullName.getText();

            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter full name.");

                return;
            }

            new DashboardFrame(name);

            frame.dispose();
        });

        // FORGOT BUTTON
        JButton forgotBtn = new JButton("Forgot password?");

        forgotBtn.setBorderPainted(false);

        forgotBtn.setContentAreaFilled(false);

        forgotBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        forgotBtn.addActionListener(e -> frame.showPage("forgot"));

        // SIGNUP BUTTON
        JButton signupBtn = new JButton("Sign up");

        signupBtn.setBorderPainted(false);

        signupBtn.setContentAreaFilled(false);

        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupBtn.addActionListener(e -> frame.showPage("signup"));

        // ADD COMPONENTS
        add(Box.createVerticalGlue());

        add(logo);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(title);

        add(Box.createRigidArea(new Dimension(0, 25)));

        add(fullName);

        add(Box.createRigidArea(new Dimension(0, 12)));

        add(email);

        add(Box.createRigidArea(new Dimension(0, 12)));

        add(password);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(loginBtn);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(forgotBtn);

        add(signupBtn);

        add(Box.createVerticalGlue());
    }
}