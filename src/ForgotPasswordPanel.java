import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ForgotPasswordPanel extends RoundedPanel {

    public ForgotPasswordPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 420));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(new EmptyBorder(25, 45, 30, 45));

        // BACK BUTTON
        JButton backBtn = new JButton("←");

        backBtn.setFont(new Font("Arial", Font.BOLD, 24));

        backBtn.setBorderPainted(false);

        backBtn.setContentAreaFilled(false);

        backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        backBtn.addActionListener(e -> {
            frame.showPage("login");
        });

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

        // DESCRIPTION
        JLabel description = new JLabel(
                "Confirm your email and we'll send instructions.");

        description.setForeground(Color.GRAY);

        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // EMAIL
        JTextField email = new JTextField();

        email.setMaximumSize(new Dimension(300, 40));

        email.setBorder(
                BorderFactory.createTitledBorder("Email"));

        // BUTTON
        JButton sendBtn = new JButton("Sign up");

        sendBtn.setBackground(new Color(166, 95, 0));

        sendBtn.setForeground(Color.WHITE);

        sendBtn.setFocusPainted(false);

        sendBtn.setMaximumSize(new Dimension(300, 40));

        sendBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // FOOTER
        JLabel footer = new JLabel("Please check your email.");

        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ADD COMPONENTS
        add(backBtn);

        add(Box.createVerticalGlue());

        add(logo);

        add(Box.createRigidArea(new Dimension(0, 10)));

        add(title);

        add(Box.createRigidArea(new Dimension(0, 15)));

        add(description);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(email);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(sendBtn);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(footer);

        add(Box.createVerticalGlue());
    }
}