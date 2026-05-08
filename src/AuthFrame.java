import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {

    CardLayout cardLayout;

    JPanel container;

    public AuthFrame() {

        setTitle("Gabay - Iskolar");

        setSize(950, 630);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        // MAIN BACKGROUND
        JPanel background = new JPanel(new GridBagLayout());

        background.setBackground(new Color(109, 8, 0));

        // CARD CONTAINER
        cardLayout = new CardLayout();

        container = new JPanel(cardLayout);

        container.setOpaque(false);

        // PAGES
        container.add(new LoginPanel(this), "login");

        container.add(new SignupPanel(this), "signup");

        container.add(new ForgotPasswordPanel(this), "forgot");

        background.add(container);

        setContentPane(background);

        setVisible(true);
    }

    public void showPage(String page) {

        cardLayout.show(container, page);
    }
}