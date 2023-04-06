package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.TransactionType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    private JPanel self;
    private JLabel homeLabel;
    private JLabel totalProducts;
    private JLabel netProfit;
    private JLabel salesCount;
    private JLabel totalSales;
    private JLabel totalRevenue;

    public HomePanel() {
        add(self);
        homeLabel.setBorder(new EmptyBorder(5, 5, 0, 0));

        setBorder(new LineBorder(Color.BLACK)); // debugging

        int i_totalProducts;
        int i_netProfit = 0;
        int i_salesCount = 0;
        int i_totalSales = 0;
        int i_totalRevenue = 0;

        i_totalProducts = Database.lager.stream().mapToInt(p -> p.amount).sum();
        i_netProfit = Database.transactions.stream()
                .mapToInt(p -> (p.transactionType == TransactionType.SALE) ?
                        p.amount * p.cost : -1 * p.amount * p.cost).sum();
        i_salesCount = Database.transactions.stream()
                .mapToInt(p -> (p.transactionType == TransactionType.SALE) ?
                        p.amount : 0).sum();
        i_totalSales = Database.transactions.stream()
                .mapToInt(p -> (p.transactionType == TransactionType.SALE) ?
                        p.amount * p.cost : 0).sum();
        i_totalRevenue = Database.transactions.stream()
                .mapToInt(p -> (p.transactionType == TransactionType.PURCHASE) ?
                        p.amount * p.cost : 0).sum();

        totalProducts.setText(Integer.toString(i_totalProducts));
        netProfit.setText((i_netProfit > 0) ? "+" : "-" + Integer.toString(Math.abs(i_netProfit)) + "₺");
        salesCount.setText(Integer.toString(i_salesCount));
        totalSales.setText(Integer.toString(i_totalSales) + "₺");
        totalRevenue.setText(Integer.toString(i_totalRevenue) + "₺");
    }
}
