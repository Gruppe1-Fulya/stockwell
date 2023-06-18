package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.inner.LagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoeschenDialog extends JDialog {

    private JPanel loeschenPanel;
    private JComboBox<Product> produktListBox;
    private JLabel produktLabel;
    private JButton cancelButton;
    private JButton okButton;

    public LoeschenDialog(MainFrame mainFrame, LagerPanel lagerPanel){
        add(loeschenPanel);
        setTitle("Produkt Löschen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(230,120);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        List<Product> productList = Controller.GetProductList();

        DefaultComboBoxModel<Product> productBoxModel = new DefaultComboBoxModel<>();
        for (Product product : productList) {
            productBoxModel.addElement(product);
        }

        produktListBox.setModel(productBoxModel);
        produktListBox.setRenderer(new EinkaufDialog.ProductRenderer());
        produktListBox.setSelectedIndex(-1);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = (Product) produktListBox.getSelectedItem();
                try {
                    if(Controller.DisposeProductDB(selectedProduct))
                        lagerPanel.updateTables();
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
