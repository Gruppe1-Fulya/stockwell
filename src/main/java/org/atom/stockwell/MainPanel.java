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
    private JPanel displayedPanel;

    public MainPanel(MainFrame mainFrame){
        setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(new HomePanel());
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
    void displayPanel(JPanel panel) {

        panel.setPreferredSize(new Dimension(1045, 640));

        if (displayedPanel != null) {
            InnerPanel.remove(displayedPanel);
            displayedPanel.setVisible(false);
            displayedPanel = null;
        }
        try {
            InnerPanel.add(panel,BorderLayout.BEFORE_FIRST_LINE);
            displayedPanel = panel;
            panel.setVisible(true);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.toString());
        }
    }
}
