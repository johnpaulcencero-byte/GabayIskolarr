import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScholarshipPopup extends JDialog {

    public ScholarshipPopup(JFrame parent,
                            String title,
                            String[][] scholarships) {

        super(parent, true);

        // ================= DIALOG =================
        setUndecorated(true);

        setSize(parent.getSize());

        setLocationRelativeTo(parent);

        setBackground(new Color(0, 0, 0, 120));

        // ================= OVERLAY =================
        JPanel overlay = new JPanel(new GridBagLayout());

        overlay.setBackground(new Color(0, 0, 0, 120));

        // ================= POPUP =================
        JPanel popup = new JPanel(new BorderLayout());

        popup.setPreferredSize(new Dimension(350, 560));

        popup.setBackground(Color.WHITE);

        popup.setBorder(new EmptyBorder(18, 18, 18, 18));

        // ================= HEADER =================
        JPanel header = new JPanel();

        header.setBackground(new Color(245, 245, 245));

        header.setLayout(new BoxLayout(
                header,
                BoxLayout.Y_AXIS));

        header.setBorder(
                new EmptyBorder(15, 15, 15, 15));

        // CLOSE BUTTON
        JPanel closePanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 0, 0));

        closePanel.setOpaque(false);

        JButton closeBtn = new JButton("✕");

        closeBtn.setPreferredSize(
                new Dimension(35, 30));

        closeBtn.setBackground(new Color(109, 8, 0));

        closeBtn.setForeground(Color.WHITE);

        closeBtn.setFocusPainted(false);

        closeBtn.setBorderPainted(false);

        closeBtn.addActionListener(e -> dispose());

        closePanel.add(closeBtn);

        // TITLE
        JLabel popupTitle = new JLabel(title);

        popupTitle.setFont(
                new Font("Arial", Font.BOLD, 30));

        popupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // NUMBER
        JLabel total = new JLabel(
                String.valueOf(scholarships.length));

        total.setFont(
                new Font("Arial", Font.BOLD, 75));

        total.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(closePanel);

        header.add(Box.createRigidArea(
                new Dimension(0, 5)));

        header.add(popupTitle);

        header.add(Box.createRigidArea(
                new Dimension(0, 5)));

        header.add(total);

        // ================= LIST CONTENT =================
        JPanel listPanel = new JPanel();

        listPanel.setBackground(Color.WHITE);

        listPanel.setLayout(new BoxLayout(
                listPanel,
                BoxLayout.Y_AXIS));

        // EMPTY STATE
        if (scholarships.length == 0) {

            for (int i = 0; i < 5; i++) {

                JPanel emptyCard = new JPanel();

                emptyCard.setBackground(
                        new Color(245, 245, 245));

                emptyCard.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, 90));

                emptyCard.setPreferredSize(
                        new Dimension(300, 90));

                emptyCard.setBorder(
                        new EmptyBorder(15, 15, 15, 15));

                listPanel.add(emptyCard);

                listPanel.add(Box.createRigidArea(
                        new Dimension(0, 12)));
            }
        }

        // SCHOLARSHIP LIST
        for (String[] scholarship : scholarships) {

            listPanel.add(createScholarshipCard(
                    scholarship[0],
                    scholarship[1]));

            listPanel.add(Box.createRigidArea(
                    new Dimension(0, 12)));
        }

        // ================= SCROLL =================
        JScrollPane scrollPane =
                new JScrollPane(listPanel);

        scrollPane.setBorder(null);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getViewport().setBackground(
                Color.WHITE);

        // CUSTOM SCROLL BAR
        JScrollBar bar = scrollPane.getVerticalScrollBar();

        bar.setPreferredSize(new Dimension(8, 0));

        bar.setBackground(Color.WHITE);

        // ================= ADD =================
        popup.add(header, BorderLayout.NORTH);

        popup.add(scrollPane, BorderLayout.CENTER);

        overlay.add(popup);

        add(overlay);

        setVisible(true);
    }

    // ================= SCHOLARSHIP CARD =================
    private JPanel createScholarshipCard(String name,
                                         String deadline) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(new Color(245, 245, 245));

        card.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 90));

        card.setPreferredSize(
                new Dimension(300, 90));

        card.setBorder(
                new EmptyBorder(15, 18, 15, 18));

        // LEFT SIDE
        JPanel left = new JPanel();

        left.setOpaque(false);

        left.setLayout(new BoxLayout(
                left,
                BoxLayout.Y_AXIS));

        JLabel scholarshipName = new JLabel(name);

        scholarshipName.setFont(
                new Font("Arial", Font.BOLD, 17));

        JLabel deadlineLabel = new JLabel(
                "Deadline : " + deadline);

        deadlineLabel.setForeground(Color.GRAY);

        deadlineLabel.setFont(
                new Font("Arial", Font.PLAIN, 14));

        left.add(scholarshipName);

        left.add(Box.createRigidArea(
                new Dimension(0, 5)));

        left.add(deadlineLabel);

        // RIGHT ARROW
        JLabel arrow = new JLabel(">");

        arrow.setFont(
                new Font("Arial", Font.BOLD, 22));

        arrow.setForeground(Color.GRAY);

        card.add(left, BorderLayout.WEST);

        card.add(arrow, BorderLayout.EAST);

        return card;
    }
}