package org.atom.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JFrame {

    private JFrame loginPanel = this;
    private JPanel mainPanel;
    private JTextField usernameText;
    private JButton einloggenButton;

    public LoginPanel() {

        add(mainPanel);
        setSize(640, 480);
        setVisible(true);
        setTitle("Stockwell");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getRootPane().setDefaultButton(einloggenButton);

        einloggenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = usernameText.getText();
                if (userName.length() <= 0) {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Bitte geben Sie einen gültigen Benutzername ein.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    new MainPanel(usernameText.getText());
                    setVisible(false);
                }
            }
        });

    }

}
