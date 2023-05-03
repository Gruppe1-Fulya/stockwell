package org.atom.stockwell;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.Mitarbeiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPanel extends JPanel{

    private JButton loginButton;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame){

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // passwordlar char[] olarak aliniyor


                DatabaseManager db = new DatabaseManager();
                List<Mitarbeiter> mitarbeiterList = db.getMitarbeiterList();

                for (Mitarbeiter mitarbeiter: mitarbeiterList) {
                    System.out.println(mitarbeiter.toString());
                }


                if(mitarbeiterList.stream()
                        .filter(mitarbeiter -> mitarbeiter.getUsername().equals(username) && mitarbeiter.getPassword().equals(password))
                        .count() > 0
                ){
                    System.out.println("show Home Screen");
                    mainFrame.showHomePanel();
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
