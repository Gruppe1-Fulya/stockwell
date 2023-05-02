package org.atom.stockwell;

import org.atom.stockwell.db.DatabaseManager;

import javax.swing.*;
// Genel olarak programın çalışacağı pencere bu olacak. Gerekli ayarlamalar da buradan yapılacak.
public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("Stockwell");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);

        LoginPanel loginPanel = new LoginPanel();

        setContentPane(loginPanel.getPanel());
    }
}
