package org.atom.stockwell;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.Mitarbeiter;
import org.atom.stockwell.db.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class LoginPanel extends JPanel{

    private JButton loginButton;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainFrame mainFrame){

        // Submitting information by pressing enter
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword()); // passwordlar char[] olarak aliniyor


                    DatabaseManager db = new DatabaseManager();
                    List<Mitarbeiter> mitarbeiterList = db.getMitarbeiterList();

                    if(mitarbeiterList
                            .stream()
                            .anyMatch(mitarbeiter ->
                                    mitarbeiter.getUsername().equals(username) &&
                                            mitarbeiter.getPassword().equals(password))
                    ){
                        System.out.println("show Home Screen");
                        mainFrame.showHomePanel();
                    }else{
                        JOptionPane.showMessageDialog(mainFrame,
                                "Sie haben einen ungültigen Benutzername oder Passwort eingegeben.",
                                "Fehler",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // passwordlar char[] olarak aliniyor


                DatabaseManager db = new DatabaseManager();
                List<Mitarbeiter> mitarbeiterList = db.getMitarbeiterList();

                if(mitarbeiterList
                        .stream()
                        .anyMatch(mitarbeiter ->
                                mitarbeiter.getUsername().equals(username) &&
                                        mitarbeiter.getPassword().equals(password))
                ){
                    System.out.println("show Home Screen");
                    mainFrame.showHomePanel();
                }else{
                    JOptionPane.showMessageDialog(mainFrame,
                            "Sie haben einen ungültigen Benutzername oder Passwort eingegeben.",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
