package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.overview.SalesGraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class HomePanel extends JPanel {
    private JPanel homePanel;
    private JLabel homeLabel;
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
    private JPanel produktPanel;
    private JLabel transaktionIDLabel;
    private JLabel transaktionID;
    private JPanel budgetStatusPanel;
    private JPanel gesamtertrag;
    private JLabel gesamtertragLabel;
    private JLabel gesamtertragAnzahlLabel;
    private JPanel gesamtaufwand;
    private JLabel gesamtaufwandLabel;
    private JLabel gesamtaufwandAnzahlLabel;
    private JPanel budget;
    private JLabel budgetLabel;
    private JLabel budgetAnzahlLabel;
    private JPanel transaktionPanel;
    private JLabel typLabel;
    private JLabel typ;
    private JLabel datumLabel;
    private JLabel datum;
    private JLabel kundenLabel;
    private JLabel kunde;
    private JLabel mitarbeiterLabel;
    private JLabel mitarbeiter;
    private JLabel anzahlLabel;
    private JLabel anzahl;
    private JLabel produktIDLabel;
    private JLabel produktID;
    private JLabel einzelpreisLabel;
    private JLabel einzelpreis;
    private JLabel p_produktIDLabel;
    private JLabel p_produktID;
    private JLabel p_nameLabel;
    private JLabel p_name;
    private JLabel p_barcodeLabel;
    private JLabel p_barcode;
    private JLabel p_kategorieLabel;
    private JLabel p_kategorie;
    private JLabel p_anzahlLabel;
    private JLabel p_anzahl;
    private JLabel p_inventarLabel;
    private JLabel p_inventar;
    private JLabel p_datumLabel;
    private JLabel p_datum;

    private HashMap<String,Long> salesData = new HashMap<String,Long>();

    public HomePanel(MainFrame mainFrame){
        add(homePanel);
        setupBudget(5555555,3333333);
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
        //displayPanel(mainFrame,budgetStatus, new BudgetStatusPanel(), BorderLayout.CENTER);
    }


    public void displayPanel(MainFrame mainFrame, JPanel displayPanel, JPanel panel, String borderLayout) {
        panel.setPreferredSize(new Dimension(320, 80));
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(panel, borderLayout);
        mainFrame.validate();
    }
    public void setupBudget(long income, long outcome){
        gesamtertragAnzahlLabel.setText("+" + Long.toString(income) + "€");
        gesamtaufwandAnzahlLabel.setText("-" + Long.toString(outcome) + "€");
        long ertragAnzahl = Long.parseLong(gesamtertragAnzahlLabel.getText().replaceAll("[^0-9]", ""));
        long aufwandAnzahl = Long.parseLong(gesamtaufwandAnzahlLabel.getText().replaceAll("[^0-9]", ""));
        long budgetAnzahl = ertragAnzahl-aufwandAnzahl;
        budgetAnzahlLabel.setText("="+Long.toString(budgetAnzahl) + "€");
    }
}
