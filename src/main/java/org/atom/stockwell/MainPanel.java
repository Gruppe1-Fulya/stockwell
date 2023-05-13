package org.atom.stockwell;

import org.atom.stockwell.inner.HomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JPanel mPanel;
    private JPanel SidePanel;
    private JButton homeButton;
    private JButton lagerButton;
    private JButton transaktionenButton;
    private JButton ueberunsButton;
    private JButton abmeldenButton;
    private JLabel usernameLabel;
    private JPanel TopPanel;
    private JLabel ImageLabel;
    private JButton kundenButton;
    private String username;
    private JPanel InnerPanel;

    public MainPanel(MainFrame mainFrame){
        setVisible(true);
        InnerPanel.setLayout(new CardLayout());
        InnerPanel.add(new HomePanel(),"home");
        displayPanel(mainFrame,"home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(mainFrame,"home");
            }
        });

        // Log out
        abmeldenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLoginPanel();
            }
        });

    }

    public JPanel getPanel(){
        return mPanel;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
        usernameLabel.setText(username);
    }
    void displayPanel(MainFrame mainFrame, String name) {
        CardLayout cardLayout = (CardLayout) InnerPanel.getLayout();
        cardLayout.show(InnerPanel,name);
    }
}
