package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;

import javax.swing.*;

public class HinzufuegenDialog extends JDialog {

    private JPanel hinzufuegenPanel;
    private JTextField nameField;
    private JTextField barcodeField;
    private JTextField kategorieField;
    private JButton cancelButton;
    private JButton okButton;
    private JLabel kundenLabel;
    private JLabel einzelpreisLabel;
    private JLabel produktLabel;

    public HinzufuegenDialog(MainFrame mainFrame){
        add(hinzufuegenPanel);
        setTitle("Produkt Hinzufügen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,230);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        setVisible(true);
    }

}
