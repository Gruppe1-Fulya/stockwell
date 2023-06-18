package org.atom.stockwell.inner.overview;

import javax.swing.*;

// ************** DISABLED *************************
public class BudgetStatusPanel extends JPanel {
    private JPanel budgetStatusPanel;
    private JPanel gesamtertrag;
    private JPanel gesamtaufwand;
    private JPanel budget;
    private JLabel gesamtertragLabel;
    private JLabel gesamtaufwandLabel;
    private JLabel budgetLabel;
    private JLabel gesamtertragAnzahlLabel;
    private JLabel gesamtaufwandAnzahlLabel;
    private JLabel budgetAnzahlLabel;

    public BudgetStatusPanel(){
        add(budgetStatusPanel);
        setupBudget(300000,210000);

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
