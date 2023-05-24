package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;

import javax.swing.*;

public class LoeschenDialog extends JDialog {

    private JPanel loeschenPanel;
    private JComboBox produktListBox;
    private JLabel produktLabel;
    private JButton cancelButton;
    private JButton okButton;

    public LoeschenDialog(MainFrame mainFrame){
        add(loeschenPanel);
        setTitle("Produkt Löschen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(230,120);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
