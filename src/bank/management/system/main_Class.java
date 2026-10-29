
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Class extends JFrame implements ActionListener {

    private JButton b1, b2, b3, b4, b5, b6, b7;
    private final String pin;

    public main_Class(String pin) {
        this.pin = pin;

        setTitle("ATM Transaction Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1550, 830);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Load the ATM background image.
        java.net.URL imageUrl =
                ClassLoader.getSystemResource("icon/atm2.png");

        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage()
                    .getScaledInstance(1550, 830, Image.SCALE_SMOOTH);

            JLabel background = new JLabel(new ImageIcon(scaledImage));
            background.setBounds(0, 0, 1550, 830);
            background.setLayout(null);
            setContentPane(background);

            addTransactionButtons(background);
        } else {
            // Allow the menu to open even if the background image is missing.
            getContentPane().setBackground(Color.DARK_GRAY);
            addTransactionButtons(getContentPane());
        }

        setVisible(true);
    }

    private void addTransactionButtons(Container container) {
        JLabel heading = new JLabel("Please Select Your Transaction");
        heading.setBounds(430, 180, 700, 35);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        container.add(heading);

        b1 = createButton("DEPOSIT", 410, 274);
        b2 = createButton("CASH WITHDRAWAL", 700, 274);
        b3 = createButton("FAST CASH", 410, 318);
        b4 = createButton("MINI STATEMENT", 700, 318);
        b5 = createButton("PIN CHANGE", 410, 362);
        b6 = createButton("BALANCE ENQUIRY", 700, 362);
        b7 = createButton("EXIT", 700, 406);

        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(b4);
        container.add(b5);
        container.add(b6);
        container.add(b7);
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 170, 35);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(65, 125, 128));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == b1) {
            openScreen(new Deposit(pin));
        } else if (source == b2) {
            openScreen(new Withdrawl(pin));
        } else if (source == b3) {
            openScreen(new FastCash(pin));
        } else if (source == b4) {
            openScreen(new mini(pin));
        } else if (source == b5) {
            openScreen(new Pin(pin));
        } else if (source == b6) {
            // Confirm that the class and constructor use this exact spelling.
            openScreen(new BalanceEnquiry(pin));
        } else if (source == b7) {
            dispose();
        }
    }

    private void openScreen(JFrame screen) {
        setVisible(false);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main_Class(""));
    }
}
