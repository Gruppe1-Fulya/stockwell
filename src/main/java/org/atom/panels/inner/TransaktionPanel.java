package org.atom.panels.inner;

import org.atom.Database;
import org.atom.Util;
import org.atom.types.Transaction;
import org.atom.types.TransactionType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransaktionPanel extends JPanel {
    private JPanel self;
    private JLabel transLabel;
    private JTable dataTable;
    private JButton buyButton;
    private JButton sellButton;

    private TransaktionPanel selfPanel;

    public TransaktionPanel() {
        add(self);
        transLabel.setBorder(new EmptyBorder(5, 5, 0, 0));
        selfPanel = this;

        setBorder(new LineBorder(Color.BLACK)); // debugging

        RefreshData();
    }

    public void RefreshData() {
        String[] columnNames = {
                "Id",
                "Product Id",
                "Product Name",
                "Initiator",
                "Client",
                "Transaction Type",
                "Amount",
                "per unit Cost/Income",
                "Total Cost/Income"
        };

        Object[][] data = new Object[Database.transactions.size()][9];

        for (int i = 0; i < Database.transactions.size(); i++) {
            Transaction transaction = Database.transactions.get(i);
            data[i] = new Object[]{
                    transaction.getId(),
                    transaction.product.getId(),
                    transaction.product.name,
                    transaction.initiator.name,
                    (transaction.client != null) ? transaction.client.name : "",
                    transaction.transactionType,
                    Util.CoolNumber(transaction.amount),
                    Util.CoolNumber(transaction.cost) + "₺",
                    (transaction.transactionType == TransactionType.SALE) ?
                            "+" + Util.CoolNumber(transaction.amount * transaction.cost) + "₺" :
                            "-" + Util.CoolNumber(transaction.amount * transaction.cost) + "₺"
            };
        }

        dataTable.setModel(new DefaultTableModel(
                data,
                columnNames
        ));

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyDialog(selfPanel);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellDialog(selfPanel); }
        });
    }
}
