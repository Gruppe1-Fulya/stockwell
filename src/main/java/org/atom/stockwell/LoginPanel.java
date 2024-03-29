package org.atom.stockwell;

import org.atom.stockwell.controllers.Controller;

import javax.swing.*;
import java.awt.event.*;

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
                    login(mainFrame);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordField.setText("");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(mainFrame);
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }

    public void login(MainFrame mainFrame){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // passwordlar char[] olarak aliniyor

        if(Controller.einLoggen(username, password)){
            System.out.println("show Main Panel");
            // home ile baslamasi icin
            mainFrame.getMainPanel().getHomeButton().doClick();
            mainFrame.showMainPanel();
            mainFrame.getMainPanel().setUsername(username);
            usernameField.setText("");
            passwordField.setText("");

        }else{
            JOptionPane.showMessageDialog(mainFrame,
                    "Sie haben einen ungültigen Benutzername oder Passwort eingegeben.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
}
