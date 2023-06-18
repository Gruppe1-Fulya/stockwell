package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.overview.BudgetStatusPanel;
import org.atom.stockwell.inner.overview.SalesGraphPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomePanel extends JPanel {
    private JPanel homePanel;
    private JLabel homeLabel;
    private JPanel budgetStatus;
    private JPanel graphPanel;
    private JPanel latestTransactions;
    private JPanel latestProduct;
    private JLabel transaktionLabel;
    private JLabel letztesProduktLabel;
    private int[] salesData = {70,123,521,704,904};
    public HomePanel(MainFrame mainFrame){
        add(homePanel);

        //graphPanel.add(new SalesGraphPanel(salesData));
        displayPanel(mainFrame,graphPanel, new SalesGraphPanel(salesData), BorderLayout.CENTER);
        displayPanel(mainFrame,budgetStatus, new BudgetStatusPanel(), BorderLayout.CENTER);
    }


    void displayPanel(MainFrame mainFrame, JPanel displayPanel, JPanel panel, String borderLayout) {
        panel.setPreferredSize(new Dimension(320, 80));
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(panel, borderLayout);
        mainFrame.validate();
    }
}
