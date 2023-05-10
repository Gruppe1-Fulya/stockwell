package org.atom.stockwell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JPanel hPanel;
    private JPanel SidePanel;
    private JButton homeButton;
    private JButton lagerButton;
    private JButton transaktionenButton;
    private JButton ueberunsButton;
    private JButton abmeldenButton;
    private JLabel usernameLabel;
    private JPanel TopPanel;
    private JLabel ImageLabel;
    private JPanel MainPanel;
    private String username;
    public MainPanel(MainFrame mainFrame){
        setVisible(true);
        // Log out
        abmeldenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLoginPanel();
            }
        });
    }

    public JPanel getPanel(){
        return hPanel;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
        usernameLabel.setText(username);
    }

}
