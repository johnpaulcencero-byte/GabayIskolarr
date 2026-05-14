import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupPanel extends RoundedPanel {

    public SignupPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 500));

        setBackground(Color.WHITE);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(30, 45, 30, 45));

        // ================= LOGO =================
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_AREA_AVERAGING);

        JLabel logo = new JLabel(new ImageIcon(img));

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= TITLE =================
        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Arial", Font.BOLD, 30));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= FULL NAME =================
        JTextField fullName = new JTextField();

        fullName.setBorder(BorderFactory.createTitledBorder("Full Name"));

        fullName.setPreferredSize(new Dimension(300, 40));

        fullName.setMaximumSize(new Dimension(300, 40));

        // ================= EMAIL =================
        JTextField email = new JTextField();

        email.setBorder(BorderFactory.createTitledBorder("Email"));

        email.setPreferredSize(new Dimension(300, 40));

        email.setMaximumSize(new Dimension(300, 40));

        // ================= PASSWORD =================
        JPasswordField password = new JPasswordField();

        password.setBorder(BorderFactory.createTitledBorder("Password"));

        password.setPreferredSize(new Dimension(300, 40));

        password.setMaximumSize(new Dimension(300, 40));

        // ================= SIGNUP BUTTON =================
        JButton signupBtn = new JButton("Sign up");

        signupBtn.setBackground(new Color(166, 95, 0));

        signupBtn.setForeground(Color.WHITE);

        signupBtn.setFocusPainted(false);

        signupBtn.setPreferredSize(new Dimension(300, 40));

        signupBtn.setMaximumSize(new Dimension(300, 40));

        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupBtn.addActionListener(e -> {

            String name = fullName.getText().trim();
            String userEmail = email.getText().trim();
            String userPass = String.valueOf(password.getPassword());

            // ================= VALIDATION =================
            if (name.isEmpty() || userEmail.isEmpty() || userPass.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.");
                return;
            }

            // ================= SAVE USER DATA =================
            UserSession.fullName = name;
            UserSession.email = userEmail;
            UserSession.password = userPass;

            // IMPORTANT: store mapping for login display name
            UserSession.userNames.put(userEmail, name);

            // ================= OPEN DASHBOARD =================
            new DashboardFrame(name);

            frame.dispose();
        });

        // ================= LOGIN BUTTON =================
        JButton loginBtn = new JButton(
                "Already a User? Log in");

        loginBtn.setBorderPainted(false);

        loginBtn.setContentAreaFilled(false);

        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> frame.showPage("login"));

        // ================= ADD COMPONENTS =================
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

        add(signupBtn);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(loginBtn);

        add(Box.createVerticalGlue());
    }
}