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

        // LEFT
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        left.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("assets/logo.png");
        Image img = logoIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        JLabel title = new JLabel("Gabay - Iskolar");
        title.setFont(new Font("Arial", Font.BOLD, 28));

        left.add(logo);
        left.add(title);

        // RIGHT
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));
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

        right.add(homeBtn);
        right.add(aboutBtn);
        right.add(settingsBtn);

        JButton logoutBtn = new JButton("Log-out");
        logoutBtn.setBackground(new Color(109, 8, 0));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setPreferredSize(new Dimension(100, 35));

        logoutBtn.addActionListener(e -> {
            new AuthFrame();
            dispose();
        });

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

        // TITLE
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

        // ================= SETTINGS CARD =================
        JPanel settingsCard = new JPanel();
        settingsCard.setBackground(Color.WHITE);
        settingsCard.setBorder(new EmptyBorder(25, 25, 25, 25));
        settingsCard.setLayout(new BoxLayout(settingsCard, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("User Preferences");
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 24));

        // NAME DISPLAY
        JPanel namePanel = createRowPanel();

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel currentName = new JLabel(fullName);
        currentName.setForeground(Color.GRAY);

        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(currentName, BorderLayout.EAST);

        // CHANGE NAME
        JPanel changePanel = createRowPanel();

        JLabel changeLabel = new JLabel("Change Name");
        changeLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel changeRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        changeRight.setOpaque(false);

        JTextField changeField = new JTextField();
        changeField.setPreferredSize(new Dimension(180, 30));

        JButton changeBtn = new JButton("Change");
        changeBtn.setBackground(new Color(166, 95, 0));
        changeBtn.setForeground(Color.WHITE);
        changeBtn.setFocusPainted(false);
        changeBtn.setBorderPainted(false);

        changeBtn.addActionListener(e -> {

            String newName = changeField.getText().trim();

            if (newName.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a new name.");

                return;
            }

            UserSession.fullName = newName;

            JOptionPane.showMessageDialog(
                    this,
                    "Name updated successfully!");

            new SettingsFrame(UserSession.fullName);
            dispose();
        });

        changeRight.add(changeField);
        changeRight.add(changeBtn);

        changePanel.add(changeLabel, BorderLayout.WEST);
        changePanel.add(changeRight, BorderLayout.EAST);

        // PROGRAM FIELD
        JPanel programPanel = createRowPanel();

        JLabel programLabel = new JLabel("Program Field");
        programLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        String[] programs = {
                "CCS",
                "CHS",
                "CSM",
                "CASS",
                "COE",
                "CED",
                "CEBA"
        };

        JComboBox<String> comboBox = new JComboBox<>(programs);
        comboBox.setPreferredSize(new Dimension(120, 30));

        JPanel comboWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        comboWrap.setOpaque(false);
        comboWrap.add(comboBox);

        programPanel.add(programLabel, BorderLayout.WEST);
        programPanel.add(comboWrap, BorderLayout.EAST);

        // ADD ALL
        settingsCard.add(sectionTitle);
        settingsCard.add(Box.createRigidArea(new Dimension(0, 20)));
        settingsCard.add(namePanel);
        settingsCard.add(Box.createRigidArea(new Dimension(0, 15)));
        settingsCard.add(changePanel);
        settingsCard.add(Box.createRigidArea(new Dimension(0, 15)));
        settingsCard.add(programPanel);

        // HELP CARD
        JPanel helpCard = new JPanel(new BorderLayout());
        helpCard.setBackground(Color.WHITE);
        helpCard.setBorder(new EmptyBorder(20, 25, 20, 25));
        helpCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel help = new JLabel("How to use");
        help.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton goBtn = new JButton("Go");
        goBtn.setBackground(new Color(166, 95, 0));
        goBtn.setForeground(Color.WHITE);
        goBtn.setFocusPainted(false);
        goBtn.setBorderPainted(false);
        goBtn.setPreferredSize(new Dimension(80, 30));

        helpCard.add(help, BorderLayout.WEST);
        helpCard.add(goBtn, BorderLayout.EAST);

        // ADD TO CONTENT
        content.add(titlePanel);
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        content.add(settingsCard);
        content.add(Box.createRigidArea(new Dimension(0, 25)));
        content.add(helpCard);

        return content;
    }

    // ================= ROW PANEL =================
    private JPanel createRowPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        return panel;
    }

    // ================= NAV BUTTON =================
    private JButton createNavButton(String text) {

        JButton btn = new JButton(text);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));

        return btn;
    }

    // ================= ACTIVE BUTTON =================
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