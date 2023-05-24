package org.atom.stockwell;

import org.atom.stockwell.inner.HomePanel;
import org.atom.stockwell.inner.LagerPanel;
import org.atom.stockwell.inner.PersonenPanel;
import org.atom.stockwell.inner.TransaktionenPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JPanel mPanel;
    private JPanel SidePanel;
    private JButton homeButton;
    private JButton lagerButton;
    private JButton transaktionenButton;
    private JButton abmeldenButton;
    private JLabel usernameLabel;
    private JPanel TopPanel;
    private JLabel ImageLabel;
    private JButton personenButton;
    private String username;
    private JPanel InnerPanel;

    public MainPanel(MainFrame mainFrame){
        setVisible(true);
        // cardLayout ile cardlari onceden tanimlayip .show(cardName) ile cardlar arasinda gecis yapabiliyoruz
        InnerPanel.setLayout(new CardLayout());
        InnerPanel.add(new HomePanel(),"home");
        InnerPanel.add(new LagerPanel(mainFrame),"lager");
        InnerPanel.add(new TransaktionenPanel(mainFrame),"transaktionen");
        InnerPanel.add(new PersonenPanel(),"personen");

        displayPanel(mainFrame,"home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(mainFrame,"home");
                highlightButton(homeButton);
            }
        });
        lagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(mainFrame, "lager");
                highlightButton(lagerButton);
            }
        });

        transaktionenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(mainFrame,"transaktionen");
                highlightButton(transaktionenButton);
            }
        });

        personenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel(mainFrame,"personen");
                highlightButton(personenButton);
            }
        });

        // Log out
        abmeldenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showLoginPanel();
                //sifirla
                highlightButton(null);
            }
        });

    }

    public JPanel getPanel(){
        return mPanel;
    }
    public JButton getHomeButton(){
        return homeButton;
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
        mainFrame.validate();
    }

    void highlightButton(JButton button){
        JButton[] buttons = {homeButton,lagerButton,transaktionenButton, personenButton};
        for(JButton btn : buttons){
            if(btn.equals(button)){
                btn.setBackground(new Color(50, 54, 66));
                btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
            else{
                btn.setBackground(null);
                btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
        }
    }
}
