package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.overview.BudgetStatusPanel;
import org.atom.stockwell.inner.overview.SalesGraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class HomePanel extends JPanel {
    private JPanel homePanel;
    private JLabel homeLabel;
    private JPanel budgetStatus;
    private JPanel graphPanel;
    private JPanel latestTransactions;
    private JPanel latestProduct;
    private JLabel transaktionLabel;
    private JLabel letztesProduktLabel;
    private JPanel transaktionTitle;
    private JPanel produktTitle;
    private JTabbedPane tabbedPane;
    private JPanel salesPanel;
    private JPanel purchasesPanel;
    private JPanel profitPanel;

    private HashMap<String,Long> salesData = new HashMap<String,Long>();

    public HomePanel(MainFrame mainFrame){
        add(homePanel);
        salesData.put("Date 1", 100L);
        salesData.put("Date 2", 150L);
        salesData.put("Date 3", 200L);
        salesData.put("Date 4", 250L);
        salesData.put("Date 5", 300L);
        salesData.put("Date 6", 350L);
        salesData.put("Date 7", 400L);
        salesData.put("Date 8", 450L);
        salesData.put("Date 9", 500L);
        //graphPanel.add(new SalesGraphPanel(salesData));
        displayPanel(mainFrame,salesPanel, new SalesGraphPanel(salesData), BorderLayout.CENTER);
        displayPanel(mainFrame,budgetStatus, new BudgetStatusPanel(), BorderLayout.CENTER);
    }


    void displayPanel(MainFrame mainFrame, JPanel displayPanel, JPanel panel, String borderLayout) {
        panel.setPreferredSize(new Dimension(320, 80));
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(panel, borderLayout);
        mainFrame.validate();
    }
}
