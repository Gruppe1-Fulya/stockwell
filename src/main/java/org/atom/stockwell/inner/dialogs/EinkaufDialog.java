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

        DefaultComboBoxModel<Product> comboBoxModel = new DefaultComboBoxModel<>();
        for (Product product : productList) {
            comboBoxModel.addElement(product);
        }
        produktListBox.setModel(comboBoxModel);
        produktListBox.setRenderer(new ProductRenderer());

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
                List<Person> personList = db.getPersonList();

                for(Person person : personList){
                    if(person.getName().equals("caglar")){
                        kunde = person;
                    }
                }

                // building a new transaction
                TransaktionBuilder builder = new TransaktionBuilder();

                Transaktion transaktion = builder
                        .startBuild()
                        .setProduct(selectedProduct)
                        .setAmount(Integer.parseInt(anzahlSpinner.getValue().toString()))
                        .setCost(Integer.parseInt(einzelSpinner.getValue().toString()))
                        .setKunde(kunde)
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
}
