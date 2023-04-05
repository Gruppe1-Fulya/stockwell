package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.*;

import javax.swing.*;
import java.awt.event.*;

public class SellDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox productBox;
    private JSpinner amountBox;
    private JSpinner unitPriceBox;
    private JComboBox clientBox;
    private TransaktionPanel outerPanel;

    public SellDialog(TransaktionPanel outerPanel) {
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        this.outerPanel = outerPanel;

        for (Product product : Database.lager) {
            productBox.addItem(product.name + " #" + product.getId());
        }

        for (Client client : Database.clients) {
            clientBox.addItem(client.name + " #" + client.getId());
        }

        if (Database.lager.size() >= 1) {
            Product product = Database.lager.get(0);
            amountBox.setModel(new SpinnerNumberModel(product.amount, 0, product.amount, 1));
            unitPriceBox.setModel(new SpinnerNumberModel(product.unit_cost, product.unit_cost, Integer.MAX_VALUE, 1));
        }

        productBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = Database.lager.get(productBox.getSelectedIndex());
                amountBox.setModel(new SpinnerNumberModel(product.amount, 0, product.amount, 1));
                unitPriceBox.setModel(new SpinnerNumberModel(product.unit_cost, product.unit_cost, Integer.MAX_VALUE, 1));
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        Product product = Database.lager.get(productBox.getSelectedIndex());
        Client client = Database.clients.get(clientBox.getSelectedIndex());
        Database.SellProduct(product, client, (int) amountBox.getValue(), (int) unitPriceBox.getValue());
        outerPanel.RefreshData();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
