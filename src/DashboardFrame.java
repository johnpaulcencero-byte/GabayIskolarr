import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class DashboardFrame extends JFrame {

    public DashboardFrame(String fullName) {

        UserSession.fullName = fullName;

        setTitle("Dashboard");

        setSize(1200, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(245, 245, 245));

        JPanel navbar = createNavbar();

        JPanel content = createContent(fullName);

        mainPanel.add(navbar, BorderLayout.NORTH);

        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // ================= NAVBAR =================
    private JPanel createNavbar() {

        JPanel navbar = new JPanel(new BorderLayout());

        navbar.setBackground(Color.WHITE);

        navbar.setBorder(
                new EmptyBorder(20, 40, 20, 40));

        JPanel leftNav = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 15, 0));

        leftNav.setOpaque(false);

        ImageIcon logoIcon =
                new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                45,
                45,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(
                new Font("Arial", Font.BOLD, 28));

        leftNav.add(logo);

        leftNav.add(title);

        JPanel rightNav = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 30, 0));

        rightNav.setOpaque(false);

        JButton homeBtn =
                createActiveNavButton("Home");

        JButton aboutBtn =
                createNavButton("About Us");

        JButton settingsBtn =
                createNavButton("Settings");

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

        rightNav.add(homeBtn);

        rightNav.add(aboutBtn);

        rightNav.add(settingsBtn);

        JButton logoutBtn = new JButton("Log-out");

        logoutBtn.setBackground(
                new Color(220, 0, 0));

        logoutBtn.setForeground(Color.WHITE);

        logoutBtn.setFocusPainted(false);

        logoutBtn.setBorderPainted(false);

        logoutBtn.setPreferredSize(
                new Dimension(100, 35));

        logoutBtn.addActionListener(e -> {

            new AuthFrame();

            dispose();
        });

        rightNav.add(logoutBtn);

        navbar.add(leftNav, BorderLayout.WEST);

        navbar.add(rightNav, BorderLayout.EAST);

        return navbar;
    }

    // ================= CONTENT =================
    private JPanel createContent(String fullName) {

        JPanel content = new JPanel();

        content.setBackground(
                new Color(245, 245, 245));

        content.setBorder(
                new EmptyBorder(40, 60, 40, 60));

        content.setLayout(
                new BoxLayout(content, BoxLayout.Y_AXIS));

        // ================= TOP PANEL =================
        JPanel topPanel = new JPanel(
                new BorderLayout());

        topPanel.setOpaque(false);

        JLabel welcome = new JLabel(
                "Welcome, " + fullName + "!");

        welcome.setFont(
                new Font("Arial", Font.BOLD, 28));

        JButton addBtn = new JButton(
                "+ Add Scholarship");

        addBtn.setBackground(
                new Color(109, 8, 0));

        addBtn.setForeground(Color.WHITE);

        addBtn.setFocusPainted(false);

        addBtn.setBorderPainted(false);

        addBtn.setPreferredSize(
                new Dimension(190, 45));

        addBtn.addActionListener(e -> {

            new AddScholarshipFrame(
                    UserSession.fullName);

            dispose();
        });

        topPanel.add(welcome, BorderLayout.WEST);

        topPanel.add(addBtn, BorderLayout.EAST);

        // ================= COUNTS =================
        int total =
                ScholarshipManager.scholarships.size();

        int pending = 0;

        int ongoing = 0;

        int done = 0;

        for (Scholarship s :
                ScholarshipManager.scholarships) {

            switch (s.getStatus()) {

                case "Pending":
                    pending++;
                    break;

                case "Ongoing":
                    ongoing++;
                    break;

                case "Done":
                    done++;
                    break;
            }
        }

        // ================= CARDS =================
        JPanel cards = new JPanel(
                new GridLayout(1, 4, 20, 0));

        cards.setOpaque(false);

        cards.add(createClickableCard(
                "TOTAL",
                String.valueOf(total),
                Color.BLACK,
                getScholarshipsByStatus(null)));

        cards.add(createClickableCard(
                "PREVIEW",
                String.valueOf(pending),
                new Color(84, 130, 230),
                getScholarshipsByStatus("Pending")));

        cards.add(createClickableCard(
                "ONGOING",
                String.valueOf(ongoing),
                new Color(230, 150, 60),
                getScholarshipsByStatus("Ongoing")));

        cards.add(createClickableCard(
                "DONE",
                String.valueOf(done),
                new Color(100, 150, 20),
                getScholarshipsByStatus("Done")));

        // ================= HEADER =================
        JPanel listHeader = new JPanel(
                new BorderLayout());

        listHeader.setOpaque(false);

        JLabel left = new JLabel(
                "Scholarship list");

        left.setFont(
                new Font("Arial", Font.BOLD, 24));

        JLabel right = new JLabel("Status");

        right.setFont(
                new Font("Arial", Font.BOLD, 24));

        listHeader.add(left, BorderLayout.WEST);

        listHeader.add(right, BorderLayout.EAST);

        // ================= LIST =================
        JPanel list = new JPanel();

        list.setOpaque(false);

        list.setLayout(
                new BoxLayout(list, BoxLayout.Y_AXIS));

        if (ScholarshipManager.scholarships.isEmpty()) {

            list.add(createEmptyScholarshipCard());

        } else {

            for (Scholarship s :
                    ScholarshipManager.scholarships) {

                list.add(createScholarshipCard(
                        s.getName(),
                        s.getStatus()));

                list.add(Box.createRigidArea(
                        new Dimension(0, 20)));
            }
        }

        JScrollPane scroll = new JScrollPane(list);

        scroll.setBorder(null);

        scroll.getViewport().setBackground(
                new Color(245, 245, 245));

        // ================= ADD =================
        content.add(topPanel);

        content.add(Box.createRigidArea(
                new Dimension(0, 40)));

        content.add(cards);

        content.add(Box.createRigidArea(
                new Dimension(0, 50)));

        content.add(listHeader);

        content.add(Box.createRigidArea(
                new Dimension(0, 20)));

        content.add(scroll);

        return content;
    }

    // ================= GET BY STATUS =================
    private String[][] getScholarshipsByStatus(
            String status) {

        ArrayList<String[]> list =
                new ArrayList<>();

        for (Scholarship s :
                ScholarshipManager.scholarships) {

            if (status == null ||
                    s.getStatus().equals(status)) {

                list.add(new String[]{
                        s.getName(),
                        s.getDeadline()
                });
            }
        }

        return list.toArray(new String[0][]);
    }

    // ================= CLICKABLE CARD =================
    private JPanel createClickableCard(String title,
                                       String number,
                                       Color color,
                                       String[][] scholarships) {

        JPanel card = createCard(
                title,
                number,
                color);

        card.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        new ScholarshipPopup(
                                DashboardFrame.this,
                                title,
                                scholarships);
                    }
                });

        return card;
    }

    // ================= CARD =================
    private JPanel createCard(String title,
                              String number,
                              Color color) {

        JPanel card = new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS));

        card.setBackground(Color.WHITE);

        card.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20));

        JLabel t = new JLabel(title);

        t.setFont(
                new Font("Arial", Font.BOLD, 22));

        t.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JLabel n = new JLabel(number);

        n.setFont(
                new Font("Arial", Font.BOLD, 62));

        n.setForeground(color);

        n.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        card.add(t);

        card.add(Box.createRigidArea(
                new Dimension(0, 10)));

        card.add(n);

        return card;
    }

    // ================= SCHOLARSHIP CARD =================
    private JPanel createScholarshipCard(String name,
                                         String status) {

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBackground(Color.WHITE);

        panel.setPreferredSize(
                new Dimension(1000, 90));

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90));

        panel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20));

        JLabel title =
                new JLabel(name);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        JLabel statusLabel =
                new JLabel(status);

        statusLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        18));

        statusLabel.setForeground(
                new Color(84, 130, 230));

        panel.add(title, BorderLayout.WEST);

        panel.add(statusLabel, BorderLayout.EAST);

        return panel;
    }

    // ================= EMPTY =================
    private JPanel createEmptyScholarshipCard() {

        JPanel panel = new JPanel(
                new BorderLayout());

        panel.setBackground(
                new Color(235, 235, 235));

        panel.setPreferredSize(
                new Dimension(1000, 90));

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        90));

        panel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20));

        return panel;
    }

    // ================= NAV =================
    private JButton createNavButton(String text) {

        JButton btn = new JButton(text);

        btn.setBorderPainted(false);

        btn.setContentAreaFilled(false);

        btn.setFocusPainted(false);

        btn.setFont(
                new Font("Arial", Font.PLAIN, 16));

        return btn;
    }

    // ================= ACTIVE =================
    private JButton createActiveNavButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(
                new Color(109, 8, 0));

        btn.setForeground(Color.WHITE);

        btn.setFocusPainted(false);

        btn.setBorderPainted(false);

        btn.setOpaque(true);

        btn.setFont(
                new Font("Arial", Font.PLAIN, 16));

        return btn;
    }
}