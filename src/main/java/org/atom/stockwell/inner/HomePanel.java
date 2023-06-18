package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.classes.FinanzStatus;
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

    private final FinanzStatus finanzStatus = Controller.getCurrentStatus();

    public HomePanel(MainFrame mainFrame){
        add(homePanel);
        //graphPanel.add(new SalesGraphPanel(salesData));
        displayPanel(mainFrame,salesPanel, new SalesGraphPanel(finanzStatus.salesPerDay), BorderLayout.CENTER);
        displayPanel(mainFrame,budgetStatus, new BudgetStatusPanel(finanzStatus), BorderLayout.CENTER);
    }


    void displayPanel(MainFrame mainFrame, JPanel displayPanel, JPanel panel, String borderLayout) {
        panel.setPreferredSize(new Dimension(320, 80));
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(panel, borderLayout);
        mainFrame.validate();
    }
}
