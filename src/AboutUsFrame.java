import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutUsFrame extends JFrame {

    public AboutUsFrame(String fullName) {

        setTitle("About Us");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        mainPanel.add(createNavbar(), BorderLayout.NORTH);
        mainPanel.add(createScrollableContent(), BorderLayout.CENTER);

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
        JButton aboutBtn = createActiveNavButton("About Us");
        JButton settingsBtn = createNavButton("Settings");

        // HOME BUTTON
        homeBtn.addActionListener(e -> {
            new DashboardFrame(UserSession.fullName);
            dispose();
        });

        // SETTINGS BUTTON
        settingsBtn.addActionListener(e -> {
            new SettingsFrame(UserSession.fullName);
            dispose();
        });

        right.add(homeBtn);
        right.add(aboutBtn);
        right.add(settingsBtn);

        // LOGOUT BUTTON
        JButton logoutBtn = new JButton("Log-out");
        logoutBtn.setBackground(new Color(220, 0, 0));
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

    // ================= SCROLLABLE CONTENT =================
    private JScrollPane createScrollableContent() {

        JPanel content = new JPanel();
        content.setBackground(new Color(245, 245, 245));
        content.setBorder(new EmptyBorder(30, 60, 30, 60));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // ===== TITLE =====
        JPanel titleSection = new JPanel(new BorderLayout());
        titleSection.setOpaque(false);

        JPanel leftTitle = new JPanel();
        leftTitle.setOpaque(false);
        leftTitle.setLayout(new BoxLayout(leftTitle, BoxLayout.Y_AXIS));

        JLabel aboutTitle = new JLabel("About us");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 44));

        JLabel subtitle = new JLabel(
                "Empowering Iskolar ng Bayan, one application at a time!");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 22));
        subtitle.setForeground(Color.GRAY);

        leftTitle.add(aboutTitle);
        leftTitle.add(Box.createRigidArea(new Dimension(0, 5)));
        leftTitle.add(subtitle);

        JButton backBtn = new JButton("‹");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 40));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setForeground(new Color(109, 8, 0));

        backBtn.addActionListener(e -> {
            new DashboardFrame(UserSession.fullName);
            dispose();
        });

        titleSection.add(leftTitle, BorderLayout.WEST);
        titleSection.add(backBtn, BorderLayout.EAST);

        // ===== TOP SECTION =====
        JPanel topSection = new JPanel(new GridLayout(1, 2, 30, 0));
        topSection.setOpaque(false);

        JTextArea description = new JTextArea(
                "A centralized desktop application designed to simplify the complex scholarship landscape for the students of Mindanao State University – Iligan Institute of Technology. "
                        + "Whether you're applying for CHED, DOST, or any school-based grant, Gabay-Iskolar keeps all your applications, requirements, and deadlines organized in one place — so you can focus on what matters most: your studies.\n\n"
                        + "Developed as a partial fulfillment for CCC102 – Programming 2\n"
                        + "College of Computer Studies | MSU-IIT, Iligan City");

        description.setFont(new Font("Arial", Font.PLAIN, 18));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setOpaque(false);
        description.setEditable(false);

        ImageIcon imgIcon = new ImageIcon("assets/graduates.jpg");
        Image scaled = imgIcon.getImage().getScaledInstance(520, 250, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(scaled));

        topSection.add(description);
        topSection.add(imageLabel);

        // ===== TEAM SECTION =====
        JPanel teamPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        teamPanel.setOpaque(false);

        teamPanel.add(createMemberCard(
                "assets/member1.jpg",
                "Caroline Tomakin",
                "UI Designer"));

        teamPanel.add(createMemberCard(
                "assets/member2.jpg",
                "Kaye H. Abitona",
                "Programmer"));

        teamPanel.add(createMemberCard(
                "assets/member3.jpg",
                "Member Name",
                "Backend"));

        teamPanel.add(createMemberCard(
                "assets/member4.jpg",
                "Member Name",
                "Documentation"));

        // ===== MISSION SECTION =====
        JPanel missionPanel = new JPanel();
        missionPanel.setOpaque(false);
        missionPanel.setLayout(new BoxLayout(missionPanel, BoxLayout.Y_AXIS));
        missionPanel.setPreferredSize(new Dimension(250, 200));

        JLabel missionTitle = new JLabel("Our Mission");
        missionTitle.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel missionText = new JLabel(
                "<html>\"To help MSU-IIT students<br>never miss a scholarship opportunity.\"</html>");

        missionText.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel year = new JLabel("A.Y. 2025 - 2026");

        JLabel prof = new JLabel(
                "<html>Professor:<br>Andrade, Mary Ann Cliefen B</html>");

        missionPanel.add(missionTitle);
        missionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        missionPanel.add(missionText);
        missionPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        missionPanel.add(year);
        missionPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        missionPanel.add(prof);

        // ===== BOTTOM SECTION =====
        JPanel bottomSection = new JPanel(new BorderLayout(30, 0));
        bottomSection.setOpaque(false);

        bottomSection.add(teamPanel, BorderLayout.CENTER);
        bottomSection.add(missionPanel, BorderLayout.EAST);

        // ===== ADD ALL =====
        content.add(titleSection);
        content.add(Box.createRigidArea(new Dimension(0, 25)));
        content.add(topSection);
        content.add(Box.createRigidArea(new Dimension(0, 25)));
        content.add(bottomSection);

        // ===== SCROLL =====
        JScrollPane scrollPane = new JScrollPane(content);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(
                new Color(245, 245, 245));

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    // ================= MEMBER CARD =================
    private JPanel createMemberCard(String imagePath,
                                    String name,
                                    String role) {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBorder(new EmptyBorder(10, 10, 10, 10));

        ImageIcon icon = new ImageIcon(imagePath);

        Image img = icon.getImage().getScaledInstance(
                120,
                140,
                Image.SCALE_SMOOTH);

        JLabel pic = new JLabel(new ImageIcon(img));

        pic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel n = new JLabel(name);

        n.setAlignmentX(Component.CENTER_ALIGNMENT);

        n.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel r = new JLabel(role);

        r.setAlignmentX(Component.CENTER_ALIGNMENT);

        r.setForeground(Color.GRAY);

        card.add(pic);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(n);
        card.add(r);

        return card;
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

    // ================= ACTIVE NAV BUTTON =================
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