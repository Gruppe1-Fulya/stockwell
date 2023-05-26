package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.builders.ProductBuilder;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.inner.LagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

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

    public HinzufuegenDialog(MainFrame mainFrame, LagerPanel lagerPanel){
        add(hinzufuegenPanel);
        setTitle("Produkt Hinzufügen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,230);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String barcode = barcodeField.getText();
                String kategorie = kategorieField.getText();

                DatabaseManager db = new DatabaseManager();

                Product product = new ProductBuilder()
                        .startBuild()
                        .setName(name)
                        .setBarcodeId(barcode)
                        .setCategory(kategorie)
                        .doneBuild();

                try {
                    if(Controller.AddProductDB(product)){
                        lagerPanel.updateTables();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

}
