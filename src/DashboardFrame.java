import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Dashboard");

        setSize(1200, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(245, 245, 245));

        // NAVBAR
        JPanel navbar = new JPanel(new BorderLayout());

        navbar.setBackground(Color.WHITE);

        navbar.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel leftNav = new JPanel(new FlowLayout(FlowLayout.LEFT));

        leftNav.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("assets/logo.png");

        Image img = logoIcon.getImage().getScaledInstance(
                40,
                40,
                Image.SCALE_SMOOTH);

        JLabel logo = new JLabel(new ImageIcon(img));

        JLabel title = new JLabel("Gabay - Iskolar");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        leftNav.add(logo);

        leftNav.add(title);

        JPanel rightNav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));

        rightNav.setOpaque(false);

        rightNav.add(createNavButton("Home"));

        rightNav.add(createNavButton("About Us"));

        rightNav.add(createNavButton("Settings"));

        JButton logoutBtn = new JButton("Log-out");

        logoutBtn.setBackground(new Color(96, 0, 0));

        logoutBtn.setForeground(Color.WHITE);

        logoutBtn.setFocusPainted(false);

        logoutBtn.setPreferredSize(new Dimension(100, 35));

        logoutBtn.addActionListener(e -> {

            new AuthFrame();

            dispose();
        });

        rightNav.add(logoutBtn);

        navbar.add(leftNav, BorderLayout.WEST);

        navbar.add(rightNav, BorderLayout.EAST);

        // CONTENT
        JPanel content = new JPanel();

        content.setBackground(new Color(245, 245, 245));

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.setBorder(new EmptyBorder(40, 70, 40, 70));

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setOpaque(false);

        JLabel welcome = new JLabel("Welcome, Caroline Tomakin!");

        welcome.setFont(new Font("Arial", Font.BOLD, 28));

        JButton addBtn = new JButton("+ Add Scholarship");

        addBtn.setBackground(new Color(96, 0, 0));

        addBtn.setForeground(Color.WHITE);

        addBtn.setFocusPainted(false);

        addBtn.setPreferredSize(new Dimension(180, 45));

        topPanel.add(welcome, BorderLayout.WEST);

        topPanel.add(addBtn, BorderLayout.EAST);

        // CARDS
        JPanel cards = new JPanel(new GridLayout(1, 4, 20, 20));

        cards.setOpaque(false);

        cards.setMaximumSize(new Dimension(1000, 160));

        cards.add(createCard("TOTAL", "0", Color.BLACK));

        cards.add(createCard("PREVIEW", "0", Color.BLUE));

        cards.add(createCard("ONGOING", "0", Color.ORANGE));

        cards.add(createCard("DONE", "0", Color.GREEN));

        // LIST HEADER
        JPanel listHeader = new JPanel(new BorderLayout());

        listHeader.setOpaque(false);

        JLabel left = new JLabel("Scholarship list");

        left.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel right = new JLabel("Status");

        right.setFont(new Font("Arial", Font.BOLD, 22));

        listHeader.add(left, BorderLayout.WEST);

        listHeader.add(right, BorderLayout.EAST);

        // LIST
        JPanel scholarshipList = new JPanel();

        scholarshipList.setOpaque(false);

        scholarshipList.setLayout(new BoxLayout(scholarshipList, BoxLayout.Y_AXIS));

        scholarshipList.add(createScholarshipCard());

        scholarshipList.add(Box.createRigidArea(new Dimension(0, 20)));

        scholarshipList.add(createScholarshipCard());

        JScrollPane scrollPane = new JScrollPane(scholarshipList);

        scrollPane.setBorder(null);

        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        content.add(topPanel);

        content.add(Box.createRigidArea(new Dimension(0, 40)));

        content.add(cards);

        content.add(Box.createRigidArea(new Dimension(0, 50)));

        content.add(listHeader);

        content.add(Box.createRigidArea(new Dimension(0, 20)));

        content.add(scrollPane);

        mainPanel.add(navbar, BorderLayout.NORTH);

        mainPanel.add(content, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    private JButton createNavButton(String text) {

        JButton btn = new JButton(text);

        btn.setBorderPainted(false);

        btn.setContentAreaFilled(false);

        btn.setFocusPainted(false);

        btn.setFont(new Font("Arial", Font.PLAIN, 15));

        return btn;
    }

    private JPanel createCard(
            String title,
            String number,
            Color color) {

        JPanel card = new JPanel();

        card.setBackground(Color.WHITE);

        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel numberLabel = new JLabel(number);

        numberLabel.setFont(new Font("Arial", Font.BOLD, 55));

        numberLabel.setForeground(color);

        numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(titleLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        card.add(numberLabel);

        return card;
    }

    private JPanel createScholarshipCard() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(new Color(230, 230, 230));

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        panel.setPreferredSize(new Dimension(900, 100));

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("No scholarship added yet.");

        title.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel status = new JLabel("Pending");

        status.setFont(new Font("Arial", Font.BOLD, 16));

        status.setForeground(Color.ORANGE);

        panel.add(title, BorderLayout.WEST);

        panel.add(status, BorderLayout.EAST);

        return panel;
    }
}