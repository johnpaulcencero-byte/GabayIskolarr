import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LogoutPopup extends JDialog {

    public LogoutPopup(JFrame parent) {

        super(parent, true);

        // ================= DIALOG =================
        setUndecorated(true);

        setSize(parent.getSize());

        setLocationRelativeTo(parent);

        setBackground(new Color(0, 0, 0, 120));

        // ================= OVERLAY =================
        JPanel overlay = new JPanel(new GridBagLayout());

        overlay.setBackground(new Color(0, 0, 0, 120));

        // ================= POPUP CARD =================
        RoundedPanel popup = new RoundedPanel();

        popup.setLayout(new BoxLayout(
                popup,
                BoxLayout.Y_AXIS));

        popup.setPreferredSize(
                new Dimension(430, 250));

        popup.setBorder(
                new EmptyBorder(25, 30, 25, 30));

        // ================= ICON =================
        ImageIcon icon =
                new ImageIcon("assets/logout.png");

        Image img =
                icon.getImage().getScaledInstance(
                        75,
                        75,
                        Image.SCALE_SMOOTH);

        JLabel iconLabel =
                new JLabel(new ImageIcon(img));

        iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // ================= TEXT =================
        JLabel text = new JLabel(
                "Are you sure you want to Log out?");

        text.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        22));

        text.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        text.setHorizontalAlignment(
                SwingConstants.CENTER);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(1, 2, 20, 0));

        buttonPanel.setOpaque(false);

        buttonPanel.setMaximumSize(
                new Dimension(360, 45));

        // ================= CANCEL BUTTON =================
        JButton cancelBtn =
                new JButton("Cancel");

        cancelBtn.setFocusPainted(false);

        cancelBtn.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17));

        cancelBtn.setBackground(
                new Color(245, 245, 245));

        cancelBtn.setForeground(Color.BLACK);

        cancelBtn.setBorder(
                BorderFactory.createLineBorder(
                        new Color(180, 180, 180)));

        // ================= LOGOUT BUTTON =================
        JButton logoutBtn =
                new JButton("Log out");

        logoutBtn.setFocusPainted(false);

        logoutBtn.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17));

        logoutBtn.setBackground(
                new Color(128, 0, 0));

        logoutBtn.setForeground(Color.WHITE);

        logoutBtn.setBorderPainted(false);

        // ================= ACTIONS =================
        cancelBtn.addActionListener(e -> dispose());

        logoutBtn.addActionListener(e -> {

            new AuthFrame();

            parent.dispose();

            dispose();
        });

        buttonPanel.add(cancelBtn);

        buttonPanel.add(logoutBtn);

        // ================= ADD COMPONENTS =================
        popup.add(Box.createVerticalGlue());

        popup.add(iconLabel);

        popup.add(Box.createRigidArea(
                new Dimension(0, 18)));

        popup.add(text);

        popup.add(Box.createRigidArea(
                new Dimension(0, 28)));

        popup.add(buttonPanel);

        popup.add(Box.createVerticalGlue());

        overlay.add(popup);

        add(overlay);

        setVisible(true);
    }
}