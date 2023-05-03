package org.atom.stockwell;

import javax.swing.*;

public class HomePanel extends JPanel {

    private JPanel hPanel;
    private JPanel SidePanel;
    private JButton homeButton;
    private JButton lagerButton;
    private JButton transaktionenButton;
    private JButton aboutButton;

    public HomePanel(MainFrame mainFrame){
        setVisible(true);
    }

    public JPanel getPanel(){
        return hPanel;
    }

}
