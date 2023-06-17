package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.graphs.SalesGraphPanel;

import javax.swing.*;
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

        graphPanel.setLayout(new CardLayout());
        graphPanel.add(new SalesGraphPanel(salesData),"sales");
        displayPanel(mainFrame,"sales");
    }


    void displayPanel(MainFrame mainFrame, String name) {
        CardLayout cardLayout = (CardLayout) graphPanel.getLayout();
        cardLayout.show(graphPanel,name);
        mainFrame.validate();
    }
}
