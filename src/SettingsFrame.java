import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsFrame extends JFrame {

    public SettingsFrame(String fullName) {

        setTitle("Settings");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        mainPanel.add(createNavbar(), BorderLayout.NORTH);
        mainPanel.add(createContent(fullName), BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // ================= NAVBAR =================
    private JPanel createNavbar() {

        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(Color.WHITE);
        navbar.setBorder(new EmptyBorder(20, 40, 20, 40));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        left.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("assets/logo.png");
        Image img = logoIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));

        JLabel title = new JLabel("Gabay - Iskolar");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        left.add(logo);
        left.add(title);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        right.setOpaque(false);

        JButton homeBtn = createNavButton("Home");
        JButton aboutBtn = createNavButton("About Us");
        JButton settingsBtn = createActiveNavButton("Settings");

        homeBtn.addActionListener(e -> {
            new DashboardFrame(UserSession.fullName);
            dispose();
        });

        aboutBtn.addActionListener(e -> {
            new AboutUsFrame(UserSession.fullName);
            dispose();
        });

        JButton logoutBtn = new JButton("Log-out");
        logoutBtn.setBackground(new Color(109, 8, 0));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setPreferredSize(new Dimension(100, 35));

        logoutBtn.addActionListener(e -> new LogoutPopup(this));

        right.add(homeBtn);
        right.add(aboutBtn);
        right.add(settingsBtn);
        right.add(logoutBtn);

        navbar.add(left, BorderLayout.WEST);
        navbar.add(right, BorderLayout.EAST);

        return navbar;
    }

    // ================= CONTENT =================
    private JPanel createContent(String fullName) {

        JPanel content = new JPanel();
        content.setBackground(new Color(245, 245, 245));
        content.setBorder(new EmptyBorder(35, 70, 35, 70));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JPanel leftTitle = new JPanel();
        leftTitle.setOpaque(false);
        leftTitle.setLayout(new BoxLayout(leftTitle, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Arial", Font.BOLD, 40));

        JLabel subtitle = new JLabel("Customize your Gabay-Iskolar experience");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.GRAY);

        leftTitle.add(title);
        leftTitle.add(Box.createRigidArea(new Dimension(0, 5)));
        leftTitle.add(subtitle);

        JButton backBtn = new JButton("‹");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 40));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setForeground(new Color(166, 95, 0));

        backBtn.addActionListener(e -> {
            new DashboardFrame(UserSession.fullName);
            dispose();
        });

        titlePanel.add(leftTitle, BorderLayout.WEST);
        titlePanel.add(backBtn, BorderLayout.EAST);

        // ================= CARD =================
        JPanel settingsCard = new JPanel();
        settingsCard.setBackground(Color.WHITE);
        settingsCard.setBorder(new EmptyBorder(25, 25, 25, 25));
        settingsCard.setLayout(new BoxLayout(settingsCard, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("User Preferences");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 24));

        // ================= NAME =================
        JPanel namePanel = createRowPanel();
        JLabel nameLabel = new JLabel("Name");
        JLabel currentName = new JLabel(fullName);
        currentName.setForeground(Color.GRAY);

        namePanel.add(nameLabel);
        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(currentName);

        // ================= CHANGE NAME =================
        JPanel changePanel = createRowPanel();
        JLabel changeLabel = new JLabel("Change Name");

        JTextField changeField = new JTextField();
        changeField.setPreferredSize(new Dimension(180, 30));
        changeField.setMaximumSize(new Dimension(180, 30));

        JButton changeBtn = createButton("Change");

        setPlaceholder(changeField, "Type here...");

        changeBtn.addActionListener(e -> {
            String newName = changeField.getText().trim();

            if (newName.isEmpty() || newName.equals("Type here...")) {
                JOptionPane.showMessageDialog(this, "Please enter a new name.");
                return;
            }

            UserSession.fullName = newName;
            JOptionPane.showMessageDialog(this, "Name updated successfully!");

            new SettingsFrame(UserSession.fullName);
            dispose();
        });

        changePanel.add(changeLabel);
        changePanel.add(Box.createHorizontalGlue());
        changePanel.add(changeField);
        changePanel.add(changeBtn);

        // ================= PASSWORD (FIXED) =================
        JPanel passwordPanel = createRowPanel();
        JLabel passwordLabel = new JLabel("Change Password");

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(180, 30));
        passwordField.setMaximumSize(new Dimension(180, 30));

        JButton passwordBtn = createButton("Change");

        setPasswordPlaceholder(passwordField, "Type here...");

        passwordBtn.addActionListener(e -> {
            String newPassword = String.valueOf(passwordField.getPassword());

            if (newPassword.isEmpty() || newPassword.equals("Type here...")) {
                JOptionPane.showMessageDialog(this, "Please enter a new password.");
                return;
            }

            UserSession.password = newPassword;
            JOptionPane.showMessageDialog(this, "Password updated successfully!");
        });

        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createHorizontalGlue());
        passwordPanel.add(passwordField);
        passwordPanel.add(passwordBtn);

        // ================= PROGRAM =================
        JPanel programPanel = createRowPanel();
        JLabel programLabel = new JLabel("Program Field");

        String[] programs = {"CCS", "CHS", "CSM", "CASS", "COE", "CED", "CEBA"};
        JComboBox<String> comboBox = new JComboBox<>(programs);

        programPanel.add(programLabel);
        programPanel.add(Box.createHorizontalGlue());
        programPanel.add(comboBox);

        // ================= ADD =================
        settingsCard.add(sectionTitle);
        settingsCard.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsCard.add(namePanel);
        settingsCard.add(changePanel);
        settingsCard.add(passwordPanel);
        settingsCard.add(programPanel);

        JPanel helpCard = new JPanel(new BorderLayout());
        helpCard.setBackground(Color.WHITE);
        helpCard.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel help = new JLabel("How to use");
        JButton goBtn = createButton("Go");

        goBtn.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(
                        new java.net.URI("https://hhshsjsjajajjajajananqnnqnqqkqn.my.canva.site/gabayiskolar-pdf")
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Unable to open link.");
            }
        });

        helpCard.add(help, BorderLayout.WEST);
        helpCard.add(goBtn, BorderLayout.EAST);

        content.add(titlePanel);
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        content.add(settingsCard);
        content.add(Box.createRigidArea(new Dimension(0, 25)));
        content.add(helpCard);

        return content;
    }

    // ================= ROW =================
    private JPanel createRowPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        return panel;
    }

    // ================= PLACEHOLDER TEXTFIELD =================
    private void setPlaceholder(JTextField field, String text) {
        field.setText(text);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // ================= PLACEHOLDER PASSWORD (FIXED) =================
    private void setPasswordPlaceholder(JPasswordField field, String text) {

        field.setEchoChar((char) 0);
        field.setText(text);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                String value = String.valueOf(field.getPassword());
                if (value.equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('•');
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                String value = String.valueOf(field.getPassword());
                if (value.isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // ================= BUTTONS =================
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(166, 95, 0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(110, 30));
        return btn;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        return btn;
    }

    private JButton createActiveNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(109, 8, 0));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }
}