package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.builders.TransaktionBuilder;
import org.atom.stockwell.db.classes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

public class EinkaufDialog extends JDialog {
    private JPanel einkaufPanel;
    private JComboBox<Product> produktListBox;
    private JLabel produktLabel;
    private JLabel einzelpreisLabel;
    private JLabel anzahlLabel;
    private JSpinner anzahlSpinner;
    private JSpinner einzelSpinner;
    private JButton cancelButton;
    private JButton okButton;
    private JLabel kundenLabel;
    private JComboBox<Person> kundenListBox;

    public EinkaufDialog(MainFrame mainFrame){
        add(einkaufPanel);
        setTitle("Einkauf");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,200);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        DatabaseManager db = new DatabaseManager();
        List<Product> productList = db.getProductList();
        List<Person>  kundenList = db.getKundeList();
        DefaultComboBoxModel<Product> productBoxModel = new DefaultComboBoxModel<>();
        for (Product product : productList) {
            productBoxModel.addElement(product);
        }
        DefaultComboBoxModel<Person> kundenBoxModel = new DefaultComboBoxModel<>();
        for (Person kunde : kundenList) {
            kundenBoxModel.addElement(kunde);
        }
        produktListBox.setModel(productBoxModel);
        produktListBox.setRenderer(new ProductRenderer());

        kundenListBox.setModel(kundenBoxModel);
        kundenListBox.setRenderer(new KundenRenderer());

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = (Product) produktListBox.getSelectedItem();
                Lager lager = db.getLager();

                // getting the user who logged in
                String username = mainFrame.getMainPanel().getUsername();
                Mitarbeiter user = new Mitarbeiter();
                List<Mitarbeiter> mitarbeiterList = db.getMitarbeiterList();

                for(Mitarbeiter mitarbeiter : mitarbeiterList){
                    if(mitarbeiter.getUsername().equals(username)){
                        user = mitarbeiter;
                    }
                }
                Person kunde = new Person();
                Person selectedKunde = (Person) kundenListBox.getSelectedItem();

                // building a new transaction
                TransaktionBuilder builder = new TransaktionBuilder();

                Transaktion transaktion = builder
                        .startBuild()
                        .setProduct(selectedProduct)
                        .setAmount(Integer.parseInt(anzahlSpinner.getValue().toString()))
                        .setCost(Integer.parseInt(einzelSpinner.getValue().toString()))
                        .setKunde(selectedKunde)
                        .setMitarbeiter(user)
                        .setType("EINKAUF")
                        .setDate(Calendar.getInstance().getTime())
                        .doneBuild();

                db.createNewTransaktion(transaktion);
                db.updateLager(lager);
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

    private static class ProductRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Product) {
                Product product = (Product) value;
                value = product.getName();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
    private static class KundenRenderer extends DefaultListCellRenderer {
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Person) {
                Person kunde = (Person) value;
                value = kunde.getName();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
