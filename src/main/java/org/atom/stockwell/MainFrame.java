package org.atom.stockwell;

import org.atom.stockwell.db.DatabaseManager;

import javax.swing.*;
// Genel olarak programın çalışacağı pencere bu olacak. Gerekli ayarlamalar da buradan yapılacak.
public class MainFrame extends JFrame {
    private HomePanel homePanel;
    private LoginPanel loginPanel;
    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        // loginPanel ve homePaneller class isimleriyken lPanel ve hPanel onlara ait JPaneller.
        loginPanel = new LoginPanel(this);
        JPanel lPanel = loginPanel.getPanel();

        homePanel = new HomePanel(this);
        JPanel hPanel = homePanel.getPanel();

        setContentPane(lPanel);
    }

    public LoginPanel getLoginPanel(){
        return loginPanel;
    }

    public HomePanel getHomePanel(){
        return homePanel;
    }
    public void showHomePanel() {
        setContentPane(homePanel.getPanel());
        validate();
    }

    public void showLoginPanel() {
        setContentPane(loginPanel.getPanel());
        validate();
    }
}
