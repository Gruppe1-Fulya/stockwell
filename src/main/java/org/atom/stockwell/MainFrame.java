package org.atom.stockwell;

import org.atom.stockwell.db.DatabaseManager;

import javax.swing.*;
// Genel olarak programın çalışacağı pencere bu olacak. Gerekli ayarlamalar da buradan yapılacak.
public class MainFrame extends JFrame {
    private JPanel homePanel;
    private JPanel loginPanel;
    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        loginPanel = new LoginPanel(this).getPanel();
        homePanel = new HomePanel(this).getPanel();
        setContentPane(loginPanel);
    }

    public void showHomePanel() {
        setContentPane(homePanel);
        validate();
    }

    public void showLoginPanel() {
        setContentPane(loginPanel);
        validate();
    }
}
