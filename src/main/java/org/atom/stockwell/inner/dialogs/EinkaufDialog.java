package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.MainPanel;
import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.builders.LagerProductBuilder;
import org.atom.stockwell.db.builders.TransaktionBuilder;
import org.atom.stockwell.db.classes.*;
import org.atom.stockwell.inner.TransaktionenPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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

    public EinkaufDialog(MainFrame mainFrame, TransaktionenPanel transaktionenPanel){
        add(einkaufPanel);
        setTitle("Einkauf");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,230);
        setResizable(false);
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
        produktListBox.setSelectedIndex(-1);

        kundenListBox.setModel(kundenBoxModel);
        kundenListBox.setRenderer(new KundenRenderer());
        kundenListBox.setSelectedIndex(-1);

        // setting min and max values for spinners
        einzelSpinner.setModel(new SpinnerNumberModel(0,0,999999999,1));
        anzahlSpinner.setModel(new SpinnerNumberModel(0,0,999999999,1));

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Product selectedProduct = (Product) produktListBox.getSelectedItem();

                // getting the user who logged in
                String username = mainFrame.getMainPanel().getUsername();

                Mitarbeiter user = db.getMitarbeiterList()
                        .stream()
                        .filter(u -> u.getUsername().equals(username))
                        .findFirst().get();

                Person selectedKunde = (Person) kundenListBox.getSelectedItem();

                // building a new transaction

                int amount = Integer.parseInt(anzahlSpinner.getValue().toString());
                int cost = Integer.parseInt(einzelSpinner.getValue().toString());
                Date date = Calendar.getInstance().getTime();
                if(amount != 0 && selectedKunde != null && selectedProduct != null && !selectedKunde.getName().equals("----") && !selectedProduct.getName().equals("----")) {
                    LagerProduct lagerProduct =
                            new LagerProductBuilder()
                                    .startBuild()
                                    .setProduct(selectedProduct)
                                    .setAmount(amount)
                                    .setCost(cost)
                                    .setDate(date)
                                    .doneBuild();

                    Transaktion transaktion =
                            new TransaktionBuilder()
                                    .startBuild()
                                    .setProduct(selectedProduct)
                                    .setAmount(amount)
                                    .setCost(cost)
                                    .setKunde(selectedKunde)
                                    .setMitarbeiter(user)
                                    .setType("EINKAUF")
                                    .setDate(date)
                                    .doneBuild();
                    try {
                        if (Controller.AddTransaktionDB(transaktion) && Controller.AddProductLagerDB(lagerProduct)) {
                            transaktionenPanel.updateTable();
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
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

    public static class ProductRenderer extends DefaultListCellRenderer {
        private final String placeholder = "----";
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value == null) {
                value = placeholder;
                setForeground(Color.GRAY);
            } else if (value instanceof Product) {
                Product product = (Product) value;
                value = product.getName();
                setForeground(list.getForeground());
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
    private static class KundenRenderer extends DefaultListCellRenderer {
        private final String placeholder = "----";
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value == null) {
                value = placeholder;
                setForeground(Color.GRAY);
            } else if (value instanceof Person) {
                Person kunde = (Person) value;
                value = kunde.getName();
                setForeground(list.getForeground());
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
