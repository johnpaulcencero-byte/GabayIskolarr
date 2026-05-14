import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ForgotPasswordPanel extends RoundedPanel {

    public ForgotPasswordPanel(AuthFrame frame) {

        setPreferredSize(new Dimension(430, 500));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // ================= MAIN CONTENT =================
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(20, 45, 30, 45));

        // ================= TOP PANEL (BACK BUTTON) =================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setOpaque(false);

        JButton backBtn = new JButton("←");
        backBtn.setFont(new Font("Arial", Font.BOLD, 28));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setForeground(new Color(166, 95, 0));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn.addActionListener(e -> frame.showPage("login"));

        topPanel.add(backBtn);

        // ================= LOGO =================
        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                100,
                100,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= TITLE =================
        JLabel title = new JLabel("Gabay - Iskolar");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= DESCRIPTION =================
        JLabel description = new JLabel(
                "Confirm your email and we'll send instructions.");
        description.setForeground(Color.GRAY);
        description.setFont(new Font("Arial", Font.PLAIN, 13));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= EMAIL FIELD =================
        JTextField email = new JTextField();
        email.setMaximumSize(new Dimension(300, 42));
        email.setPreferredSize(new Dimension(300, 42));
        email.setFont(new Font("Arial", Font.PLAIN, 14));

        email.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 210, 210),
                                1,
                                true),
                        BorderFactory.createEmptyBorder(10, 12, 10, 12)
                )
        );

        // ================= SEND BUTTON =================
        JButton sendBtn = new JButton("Reset Password");
        sendBtn.setBackground(new Color(166, 95, 0));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setFocusPainted(false);
        sendBtn.setFont(new Font("Arial", Font.BOLD, 14));
        sendBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendBtn.setMaximumSize(new Dimension(300, 40));
        sendBtn.setPreferredSize(new Dimension(300, 40));
        sendBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ================= FOOTER MESSAGE =================
        JLabel footer = new JLabel("Please check your email.");
        footer.setFont(new Font("Arial", Font.PLAIN, 13));
        footer.setForeground(Color.BLACK);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // HIDDEN BY DEFAULT
        footer.setVisible(false);

        // ================= SEND ACTION =================
        sendBtn.addActionListener(e -> {

            String userEmail = email.getText().trim();

            if (userEmail.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your email.",
                        "Missing Email",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // SHOW MESSAGE
            footer.setVisible(true);

            // AUTO HIDE AFTER 3 SECONDS
            Timer timer = new Timer(3000, event -> {
                footer.setVisible(false);
            });

            timer.setRepeats(false);
            timer.start();
        });

        // ================= ADD COMPONENTS =================
        content.add(Box.createVerticalGlue());

        content.add(logo);

        content.add(Box.createRigidArea(new Dimension(0, 10)));

        content.add(title);

        content.add(Box.createRigidArea(new Dimension(0, 15)));

        content.add(description);

        content.add(Box.createRigidArea(new Dimension(0, 25)));

        email.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(email);

        content.add(Box.createRigidArea(new Dimension(0, 20)));

        content.add(sendBtn);

        content.add(Box.createRigidArea(new Dimension(0, 20)));

        content.add(footer);

        content.add(Box.createVerticalGlue());

        // ================= PANEL LAYOUT =================
        add(topPanel, BorderLayout.NORTH);
        add(content, BorderLayout.CENTER);
    }
}