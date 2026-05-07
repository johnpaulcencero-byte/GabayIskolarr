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

        // BACKGROUND COLOR
        getContentPane().setBackground(new Color(96, 0, 0));

        // CARD LAYOUT
        cardLayout = new CardLayout();

        container = new JPanel(cardLayout);

        container.setOpaque(false);

        // ADD PAGES
        container.add(new LoginPanel(this), "login");

        container.add(new SignupPanel(this), "signup");

        container.add(new ForgotPasswordPanel(this), "forgot");

        // CENTER
        setLayout(new GridBagLayout());

        add(container);

        setVisible(true);
    }

    public void showPage(String page) {

        cardLayout.show(container, page);

    }
}