package org.atom.stockwell;

import javax.swing.*;
// Genel olarak programın çalışacağı pencere bu olacak. Gerekli ayarlamalar da buradan yapılacak.
public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private LoginPanel loginPanel;
    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        // loginPanel ve homePaneller class isimleriyken lPanel ve hPanel onlara ait JPaneller.
        loginPanel = new LoginPanel(this);
        JPanel lPanel = loginPanel.getPanel();

        mainPanel = new MainPanel(this);
        JPanel hPanel = mainPanel.getPanel();

        setContentPane(lPanel);
    }

    public LoginPanel getLoginPanel(){
        return loginPanel;
    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }
    public void showMainPanel() {
        setContentPane(mainPanel.getPanel());
        validate();
    }

    public void showLoginPanel() {
        setContentPane(loginPanel.getPanel());
        validate();
    }
}
