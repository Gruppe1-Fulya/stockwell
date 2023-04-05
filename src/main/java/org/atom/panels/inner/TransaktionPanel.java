package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.Product;
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
                "Transaction Type",
                "Amount",
                "per unit Cost/Income",
                "Total Cost/Income"
        };

        Object[][] data = new Object[Database.transactions.size()][7];

        for (int i = 0; i < Database.transactions.size(); i++) {
            Transaction transaction = Database.transactions.get(i);
            data[i] = new Object[]{
                    transaction.getId(),
                    transaction.product.getId(),
                    transaction.product.name,
                    transaction.transactionType,
                    transaction.amount,
                    transaction.cost,
                    (transaction.transactionType == TransactionType.SALE) ? transaction.amount * transaction.cost :
                            -1 * transaction.amount * transaction.cost
            };
        }

        dataTable.setModel(new DefaultTableModel(
                data,
                columnNames
        ));

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBuyDialog(selfPanel);
            }
        });
    }
}
