package org.atom.panels;

import org.atom.Database;
import org.atom.types.Worker;

import javax.swing.*;
import javax.xml.crypto.Data;
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
                Worker user = Database.workers.stream()
                        .filter(worker -> userName.equals(worker.userName))
                        .findFirst()
                        .orElse(null);
                if (user == null) {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Bitte geben Sie einen gültigen Benutzername ein.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    Database.currentWorker = user;
                    new MainPanel(usernameText.getText());
                    dispose();
                }
            }
        });

    }

}
