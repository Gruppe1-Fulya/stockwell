package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;

import javax.swing.*;

public class EinkaufDialog extends JDialog {
    private JPanel einkaufPanel;

    public EinkaufDialog(MainFrame mainFrame){
        add(einkaufPanel);
        setTitle("Einkauf");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,200);
        setLocationRelativeTo(mainFrame);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}
