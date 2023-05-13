package org.atom.stockwell;

import javax.swing.*;
import java.awt.*;

// Genel olarak programın çalışacağı pencere bu olacak. Gerekli ayarlamalar da buradan yapılacak.
public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private LoginPanel loginPanel;
    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = new Dimension(1280,720);
        setSize(size);
        setMinimumSize(size);

        // loginPanel ve homePaneller class isimleriyken lPanel ve mPanel onlara ait JPaneller.
        loginPanel = new LoginPanel(this);
        JPanel lPanel = loginPanel.getPanel();

        mainPanel = new MainPanel(this);
        JPanel mPanel = mainPanel.getPanel();

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
