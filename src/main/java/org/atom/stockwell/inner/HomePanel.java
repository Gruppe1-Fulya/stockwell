package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.classes.FinanzStatus;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.db.classes.Transaktion;
import org.atom.stockwell.inner.overview.BudgetStatusPanel;
import org.atom.stockwell.inner.overview.SalesGraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
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

    private final FinanzStatus finanzStatus = Controller.getCurrentStatus();

    public HomePanel(MainFrame mainFrame){
        add(homePanel);
        setupBudget(finanzStatus.totalIncome , finanzStatus.totalOutcome);
        setupLetzteTransaktion(Arrays.stream(finanzStatus.lastTransaktionen).findFirst().get());
        finanzStatus.lastProduct.ifPresent(this::setupLetzteProduct);
        //graphPanel.add(new SalesGraphPanel(salesData));
        displayPanel(mainFrame,salesPanel, new SalesGraphPanel(finanzStatus.salesPerDay), BorderLayout.CENTER);
        // displayPanel(mainFrame,budgetStatus, new BudgetStatusPanel(finanzStatus), BorderLayout.CENTER);
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

    public void setupLetzteTransaktion(Transaktion transaktion) {
        transaktionID.setText(transaktion.getId());
        produktID.setText(transaktion.getProduct().getId());
        typ.setText(transaktion.getType());
        kunde.setText(transaktion.getKunde().getName());
        mitarbeiter.setText(transaktion.getMitarbeiter().getName());
        anzahl.setText(String.valueOf(transaktion.getAmount()));
        einzelpreis.setText(String.valueOf(transaktion.getCost()));
        datum.setText(transaktion.getDate().toString());
    }

    public void setupLetzteProduct(Transaktion transaktion) {
        p_produktID.setText(transaktion.getProduct().getId());
        p_name.setText(transaktion.getProduct().getName());
        p_kategorie.setText(transaktion.getProduct().getCategory());
        p_barcode.setText(transaktion.getProduct().getBarcodeId());
        p_anzahl.setText(String.valueOf(transaktion.getAmount()));
        p_inventar.setText("BULUCAM !!"); // burasını çekmek biraz uzun ondan sonra yapıcam
        p_datum.setText(transaktion.getDate().toString());
    }
}
