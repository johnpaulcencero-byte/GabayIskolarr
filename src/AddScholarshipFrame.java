import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class AddScholarshipFrame extends JFrame {

    private JPanel listPanel;

    private JTextField searchField;

    private final ArrayList<JPanel> cards =
            new ArrayList<>();

    private final ArrayList<String> scholarshipNames =
            new ArrayList<>();

    public AddScholarshipFrame(String fullName) {

        setTitle("Add Scholarship");

        setSize(1200, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 245, 245));

        mainPanel.add(
                createNavbar(),
                BorderLayout.NORTH);

        mainPanel.add(
                createContent(),
                BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // ================= NAVBAR =================
    private JPanel createNavbar() {

        JPanel navbar =
                new JPanel(new BorderLayout());

        navbar.setBackground(Color.WHITE);

        navbar.setBorder(
                new EmptyBorder(
                        20,
                        40,
                        20,
                        40));

        // ================= LEFT =================
        JPanel left = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        0));

        left.setOpaque(false);

        ImageIcon logoIcon =
                new ImageIcon("assets/logo.png");

        Image img =
                logoIcon.getImage().getScaledInstance(
                        45,
                        45,
                        Image.SCALE_SMOOTH);

        JLabel logo =
                new JLabel(new ImageIcon(img));

        JLabel title =
                new JLabel("Gabay - Iskolar");

        title.setFont(
                new Font("Arial", Font.BOLD, 28));

        left.add(logo);

        left.add(title);

        // ================= RIGHT =================
        JPanel right = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        30,
                        0));

        right.setOpaque(false);

        JButton homeBtn =
                createNavButton("Home");

        JButton aboutBtn =
                createNavButton("About Us");

        JButton settingsBtn =
                createNavButton("Settings");

        homeBtn.addActionListener(e -> {

            new DashboardFrame(
                    UserSession.fullName);

            dispose();
        });

        aboutBtn.addActionListener(e -> {

            new AboutUsFrame(
                    UserSession.fullName);

            dispose();
        });

        settingsBtn.addActionListener(e -> {

            new SettingsFrame(
                    UserSession.fullName);

            dispose();
        });

        right.add(homeBtn);

        right.add(aboutBtn);

        right.add(settingsBtn);

        // ================= LOGOUT =================
        JButton logoutBtn =
                new JButton("Log-out");

        logoutBtn.setBackground(
                new Color(109, 8, 0));

        logoutBtn.setForeground(Color.WHITE);

        logoutBtn.setFocusPainted(false);

        logoutBtn.setBorderPainted(false);

        logoutBtn.setPreferredSize(
                new Dimension(100, 35));

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
    private JScrollPane createContent() {

        JPanel content = new JPanel();

        content.setBackground(
                new Color(245, 245, 245));

        content.setBorder(
                new EmptyBorder(
                        30,
                        60,
                        30,
                        60));

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS));

        // ================= TOP PANEL =================
        JPanel topPanel =
                new JPanel();

        topPanel.setOpaque(false);

        topPanel.setLayout(
                new BoxLayout(
                        topPanel,
                        BoxLayout.X_AXIS));

        topPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        50));

        // ================= SEARCH FIELD =================
        searchField = new JTextField();

        searchField.setFont(
                new Font("Arial", Font.PLAIN, 18));

        searchField.setPreferredSize(
                new Dimension(950, 50));

        searchField.setMaximumSize(
                new Dimension(950, 50));

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        230,
                                        230,
                                        230),
                                1,
                                true),
                        new EmptyBorder(
                                10,
                                20,
                                10,
                                20)));

        searchField.setToolTipText("Search");

        // ================= SEARCH FUNCTION =================
        searchField.addKeyListener(
                new java.awt.event.KeyAdapter() {

                    @Override
                    public void keyReleased(
                            java.awt.event.KeyEvent e) {

                        filterScholarships(
                                searchField.getText());
                    }
                });

        // ================= BACK BUTTON =================
        JButton backBtn =
                new JButton("‹");

        backBtn.setFont(
                new Font("Arial", Font.PLAIN, 40));

        backBtn.setBorderPainted(false);

        backBtn.setContentAreaFilled(false);

        backBtn.setForeground(
                new Color(166, 95, 0));

        backBtn.addActionListener(e -> {

            new DashboardFrame(
                    UserSession.fullName);

            dispose();
        });

        topPanel.add(searchField);

        topPanel.add(Box.createRigidArea(
                new Dimension(15, 0)));

        topPanel.add(backBtn);

        // ================= LIST PANEL =================
        listPanel = new JPanel();

        listPanel.setOpaque(false);

        listPanel.setLayout(
                new BoxLayout(
                        listPanel,
                        BoxLayout.Y_AXIS));

        addScholarshipCard(
                "MSU SASE",
                "May 10, 2026");

        addScholarshipCard(
                "CHED",
                "June 30, 2026");

        addScholarshipCard(
                "SM Foundation",
                "August 3, 2026");

        addScholarshipCard(
                "DOST",
                "February 21, 2026");

        addScholarshipCard(
                "UP CAT",
                "March 15, 2026");

        // ================= WRAPPER =================
        JPanel wrapperPanel =
                new JPanel(new BorderLayout());

        wrapperPanel.setOpaque(false);

        wrapperPanel.add(
                listPanel,
                BorderLayout.NORTH);

        // ================= ADD CONTENT =================
        content.add(topPanel);

        content.add(Box.createRigidArea(
                new Dimension(0, 35)));

        content.add(wrapperPanel);

        // ================= SCROLL =================
        JScrollPane scrollPane =
                new JScrollPane(content);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(
                new Color(245, 245, 245));

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    // ================= ADD CARD =================
    private void addScholarshipCard(String title,
                                    String deadline) {

        JPanel card =
                createScholarshipCard(
                        title,
                        deadline);

        cards.add(card);

        scholarshipNames.add(title);

        JPanel wrapper =
                new JPanel(new BorderLayout());

        wrapper.setOpaque(false);

        wrapper.add(card, BorderLayout.CENTER);

        wrapper.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100));

        listPanel.add(wrapper);

        listPanel.add(Box.createRigidArea(
                new Dimension(0, 20)));
    }

    // ================= FILTER =================
    private void filterScholarships(String keyword) {

        listPanel.removeAll();

        keyword = keyword.toLowerCase();

        for (int i = 0; i < cards.size(); i++) {

            String name =
                    scholarshipNames.get(i)
                            .toLowerCase();

            if (name.contains(keyword)) {

                JPanel wrapper =
                        new JPanel(
                                new BorderLayout());

                wrapper.setOpaque(false);

                wrapper.add(cards.get(i),
                        BorderLayout.CENTER);

                wrapper.setMaximumSize(
                        new Dimension(
                                Integer.MAX_VALUE,
                                100));

                listPanel.add(wrapper);

                listPanel.add(
                        Box.createRigidArea(
                                new Dimension(0, 20)));
            }
        }

        listPanel.revalidate();

        listPanel.repaint();
    }

    // ================= SCHOLARSHIP CARD =================
    private JPanel createScholarshipCard(String title,
                                         String deadline) {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100));

        card.setPreferredSize(
                new Dimension(1000, 100));

        card.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30));

        // ================= LEFT =================
        JPanel left = new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS));

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28));

        JLabel deadlineLabel =
                new JLabel(
                        "Deadline : " + deadline);

        deadlineLabel.setFont(
                new Font("Arial", Font.PLAIN, 18));

        deadlineLabel.setForeground(Color.GRAY);

        left.add(titleLabel);

        left.add(Box.createRigidArea(
                new Dimension(0, 5)));

        left.add(deadlineLabel);

        // ================= RIGHT =================
        JButton addBtn =
                new JButton();

        addBtn.setFocusPainted(false);

        addBtn.setBorderPainted(false);

        addBtn.setPreferredSize(
                new Dimension(100, 35));

        // ================= CHECK IF ALREADY ADDED =================
        boolean alreadyAdded = false;

        for (Scholarship s :
                ScholarshipManager.scholarships) {

            if (s.getName().equals(title)) {

                alreadyAdded = true;

                break;
            }
        }

        // ================= BUTTON STATE =================
        if (alreadyAdded) {

            addBtn.setText("Added");

            addBtn.setEnabled(false);

            addBtn.setBackground(
                    new Color(120, 120, 120));

            addBtn.setForeground(Color.WHITE);

        } else {

            addBtn.setText("+ Add");

            addBtn.setBackground(
                    new Color(58, 99, 193));

            addBtn.setForeground(Color.WHITE);

            // ================= ADD FUNCTION =================
            addBtn.addActionListener(e -> {

                ScholarshipManager.scholarships.add(
                        new Scholarship(
                                title,
                                deadline,
                                "Pending"));

                JOptionPane.showMessageDialog(
                        this,
                        title + " added successfully!");

                // CHANGE BUTTON
                addBtn.setText("Added");

                addBtn.setEnabled(false);

                addBtn.setBackground(
                        new Color(120, 120, 120));

                // REFRESH DASHBOARD
                new DashboardFrame(
                        UserSession.fullName);

                dispose();
            });
        }

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                10));

        right.setOpaque(false);

        right.add(addBtn);

        card.add(left, BorderLayout.WEST);

        card.add(right, BorderLayout.EAST);

        return card;
    }

    // ================= NAV BUTTON =================
    private JButton createNavButton(String text) {

        JButton btn = new JButton(text);

        btn.setBorderPainted(false);

        btn.setContentAreaFilled(false);

        btn.setFocusPainted(false);

        btn.setFont(
                new Font("Arial", Font.PLAIN, 16));

        return btn;
    }
}