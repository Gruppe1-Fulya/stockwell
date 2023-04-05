package org.atom.panels;

import org.atom.panels.inner.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class MainPanel extends JFrame {
    private JPanel panel;
    private JLabel helloText;

    private JButton homeButton;
    private JButton lagerButton;
    private JButton transaktionenButton;
    private JButton kundenButton;
    private JButton ausloggenButton;

    private JPanel displayPanel;
    private JPanel displayedPanel;

    MainPanel(String userName) {
        add(panel);
        helloText.setText(helloText.getText() + " " + userName);
        helloText.setBorder(new EmptyBorder(5, 0, 0, 5));
        setSize(1280, 720);
        setVisible(true);
        setTitle("Stockwell");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel(new HomePanel());
            }
        });

        lagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel(new LagerPanel());
            }
        });

        transaktionenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel(new TransaktionPanel());
            }
        });

        kundenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel(new KundenPanel());
            }
        });
    }

    void DisplayPanel(JPanel panel) {

        panel.setPreferredSize(new Dimension(1045, 640));

        if (displayedPanel != null) {
            displayPanel.remove(displayedPanel);
            displayedPanel.setVisible(false);
            displayedPanel = null;
        }
        try {
            displayPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
            displayedPanel = panel;
            panel.setVisible(true);
            this.revalidate();
            this.repaint();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.toString());
        }
    }
}
