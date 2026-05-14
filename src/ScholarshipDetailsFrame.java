import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URI;

public class ScholarshipDetailsFrame extends JFrame {

    public ScholarshipDetailsFrame(String scholarshipTitle,
                                   String deadline) {

        setTitle(scholarshipTitle);

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
                createScrollableContent(
                        scholarshipTitle,
                        deadline),
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

        // LEFT
        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                0));

        left.setOpaque(false);

        ImageIcon logoIcon =
                new ImageIcon(
                        "assets/logo.png");

        Image img =
                logoIcon.getImage()
                        .getScaledInstance(
                                45,
                                45,
                                Image.SCALE_SMOOTH);

        JLabel logo =
                new JLabel(
                        new ImageIcon(img));

        JLabel title =
                new JLabel(
                        "Gabay - Iskolar");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28));

        left.add(logo);

        left.add(title);

        // RIGHT
        JPanel right =
                new JPanel(
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
            new LogoutPopup(this);
        });

        right.add(logoutBtn);

        navbar.add(left, BorderLayout.WEST);

        navbar.add(right, BorderLayout.EAST);

        return navbar;
    }

    // ================= CONTENT =================
    private JScrollPane createScrollableContent(
            String scholarshipTitle,
            String deadline) {

        JPanel content =
                new JPanel();

        content.setBackground(
                new Color(245, 245, 245));

        content.setBorder(
                new EmptyBorder(
                        35,
                        55,
                        35,
                        55));

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS));

        // ================= TITLE SECTION =================
        JPanel titlePanel =
                new JPanel(
                        new BorderLayout());

        titlePanel.setOpaque(false);

        JPanel leftTitle =
                new JPanel();

        leftTitle.setOpaque(false);

        leftTitle.setLayout(
                new BoxLayout(
                        leftTitle,
                        BoxLayout.Y_AXIS));

        JLabel title =
                new JLabel(
                        scholarshipTitle);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42));

        JLabel deadlineLabel =
                new JLabel(
                        "Deadline : "
                                + deadline);

        deadlineLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        20));

        deadlineLabel.setForeground(
                Color.GRAY);

        leftTitle.add(title);

        leftTitle.add(
                Box.createRigidArea(
                        new Dimension(0, 5)));

        leftTitle.add(deadlineLabel);

        // RIGHT SIDE
        JPanel rightTitle =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                0));

        rightTitle.setOpaque(false);

        JButton backBtn =
                new JButton("‹");

        backBtn.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        40));

        backBtn.setBorderPainted(false);

        backBtn.setContentAreaFilled(false);

        backBtn.setForeground(
                new Color(166, 95, 0));

        backBtn.addActionListener(e -> {

            new AddScholarshipFrame(
                    UserSession.fullName);

            dispose();
        });

        // ================= ADD BUTTON =================
        JButton addedBtn =
                new JButton();

        addedBtn.setFocusPainted(false);

        addedBtn.setBorderPainted(false);

        addedBtn.setPreferredSize(
                new Dimension(100, 35));

        boolean alreadyAdded = false;

        for (Scholarship s :
                ScholarshipManager.scholarships) {

            if (s.getName().equals(
                    scholarshipTitle)) {

                alreadyAdded = true;

                break;
            }
        }

        if (alreadyAdded) {

            addedBtn.setText("Added");

            addedBtn.setEnabled(false);

            addedBtn.setBackground(
                    new Color(140, 160, 210));

            addedBtn.setForeground(
                    Color.WHITE);

        } else {

            addedBtn.setText("+ Add");

            addedBtn.setBackground(
                    new Color(58, 99, 193));

            addedBtn.setForeground(
                    Color.WHITE);

            addedBtn.addActionListener(e -> {

                ScholarshipManager.scholarships.add(
                        new Scholarship(
                                scholarshipTitle,
                                deadline,
                                "Preview")); // changed from Ongoing

                JOptionPane.showMessageDialog(
                        this,
                        scholarshipTitle
                                + " added successfully!");

                addedBtn.setText("Added");

                addedBtn.setEnabled(false);

                addedBtn.setBackground(
                        new Color(140, 160, 210));

                addedBtn.setForeground(Color.WHITE);
            });
        }

        rightTitle.add(backBtn);

        rightTitle.add(addedBtn);

        titlePanel.add(
                leftTitle,
                BorderLayout.WEST);

        titlePanel.add(
                rightTitle,
                BorderLayout.EAST);

        // ================= OVERVIEW =================
        JPanel overviewCard =
                createCardPanel();

        overviewCard.setLayout(
                new BoxLayout(
                        overviewCard,
                        BoxLayout.Y_AXIS));

        JLabel overviewTitle =
                new JLabel(
                        "Scholarship Overview");

        overviewTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        JTextArea overviewText =
                new JTextArea(
                        "The MSU-SASE is a system-wide "
                                + "examination first implemented "
                                + "in 1989. It serves as both "
                                + "an admission and scholarship "
                                + "evaluation tool for incoming "
                                + "students of Mindanao State "
                                + "University.");

        overviewText.setLineWrap(true);

        overviewText.setWrapStyleWord(true);

        overviewText.setEditable(false);

        overviewText.setOpaque(false);

        overviewText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17));

        overviewCard.add(overviewTitle);

        overviewCard.add(
                Box.createRigidArea(
                        new Dimension(0, 12)));

        overviewCard.add(overviewText);

        // ================= REQUIREMENTS =================
        JPanel requirementsCard =
                createCardPanel();

        requirementsCard.setLayout(
                new BoxLayout(
                        requirementsCard,
                        BoxLayout.Y_AXIS));

        JLabel reqTitle =
                new JLabel(
                        "Requirements Checklist");

        reqTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        requirementsCard.add(reqTitle);

        requirementsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        requirementsCard.add(
                createRequirementItem(
                        "Form 137"));

        requirementsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        requirementsCard.add(
                createRequirementItem(
                        "PSA Birth Certificate"));

        requirementsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        requirementsCard.add(
                createRequirementItem(
                        "Income Tax Return"));

        requirementsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        requirementsCard.add(
                createRequirementItem(
                        "2x2 ID Pictures"));

        requirementsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        requirementsCard.add(
                createRequirementItem(
                        "Certificate of Good Moral"));

        // ================= DOWNLOADABLE FORMS =================
        JPanel formsCard =
                createCardPanel();

        formsCard.setLayout(
                new BoxLayout(
                        formsCard,
                        BoxLayout.Y_AXIS));

        JLabel formsTitle =
                new JLabel(
                        "Downloadable Forms & Links");

        formsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        formsCard.add(formsTitle);

        formsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)));

        formsCard.add(
                createLinkButton(
                        "Admission & Enrollment Procedure (Freshmen)",
                        "https://www.msuiit.edu.ph/offices/admissions/downloads/DPM-MSU-IIT-OASG-001-Admission-and-Enrolment-of-Freshmen-Students-Online.pdf"));

        formsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        formsCard.add(
                createLinkButton(
                        "Checklist of Required Documents",
                        "https://admission.msuiit.edu.ph/apwebportal/"));

        formsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        formsCard.add(
                createLinkButton(
                        "SASE Application Portal",
                        "https://sase.msumain.edu.ph"));

        formsCard.add(
                Box.createRigidArea(
                        new Dimension(0, 10)));

        formsCard.add(
                createLinkButton(
                        "MSU-IIT Admission Portal",
                        "https://admission.msuiit.edu.ph/apwebportal/"));

        // ================= DEADLINE NOTE =================
        JPanel noteCard =
                createCardPanel();

        noteCard.setLayout(
                new BoxLayout(
                        noteCard,
                        BoxLayout.Y_AXIS));

        JLabel noteTitle =
                new JLabel(
                        "Deadline Note");

        noteTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        JTextArea noteText =
                new JTextArea(
                        "The current deadline for submission "
                                + "of applications in the "
                                + "MSU-IIT Admission Portal "
                                + "has been extended until "
                                + "May 10, 2026.");

        noteText.setLineWrap(true);

        noteText.setWrapStyleWord(true);

        noteText.setEditable(false);

        noteText.setOpaque(false);

        noteText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17));

        noteCard.add(noteTitle);

        noteCard.add(
                Box.createRigidArea(
                        new Dimension(0, 12)));

        noteCard.add(noteText);

        // ================= ADD ALL =================
        content.add(titlePanel);

        content.add(
                Box.createRigidArea(
                        new Dimension(0, 25)));

        content.add(overviewCard);

        content.add(
                Box.createRigidArea(
                        new Dimension(0, 25)));

        content.add(requirementsCard);

        content.add(
                Box.createRigidArea(
                        new Dimension(0, 25)));

        content.add(formsCard);

        content.add(
                Box.createRigidArea(
                        new Dimension(0, 25)));

        content.add(noteCard);

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

    // ================= CARD =================
    private JPanel createCardPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25));

        return panel;
    }

    // ================= REQUIREMENT =================
    private JPanel createRequirementItem(
            String text) {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setBackground(
                new Color(245, 245, 245));

        panel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15));

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16));

        JCheckBox checkBox =
                new JCheckBox();

        checkBox.setOpaque(false);

        panel.add(label, BorderLayout.WEST);

        panel.add(checkBox, BorderLayout.EAST);

        return panel;
    }

    // ================= LINK BUTTON =================
    private JButton createLinkButton(
            String text,
            String url) {

        JButton btn =
                new JButton(text);

        btn.setHorizontalAlignment(
                SwingConstants.LEFT);

        btn.setBackground(
                new Color(245, 245, 245));

        btn.setFocusPainted(false);

        btn.setBorderPainted(false);

        btn.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45));

        btn.addActionListener(e -> {

            try {

                Desktop.getDesktop().browse(
                        new URI(url));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open link.");
            }
        });

        return btn;
    }

    // ================= NAV BUTTON =================
    private JButton createNavButton(
            String text) {

        JButton btn =
                new JButton(text);

        btn.setBorderPainted(false);

        btn.setContentAreaFilled(false);

        btn.setFocusPainted(false);

        btn.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16));

        return btn;
    }
}