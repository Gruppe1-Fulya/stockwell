package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.dialogs.EinkaufDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransaktionenPanel extends JPanel {
    private JPanel transaktionenPanel;
    private JLabel transaktionenLabel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable transaktionenTable;
    private JButton verkaufButton;
    private JButton einkaufButton;

    public TransaktionenPanel(MainFrame mainFrame){
        add(transaktionenPanel);
        transaktionenTable.setModel(Controller.getTransaktionenTable());

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < transaktionenTable.getColumnCount(); columnIndex++) {
            transaktionenTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        transaktionenTable.setShowGrid(true);

        einkaufButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EinkaufDialog einkaufDialog = new EinkaufDialog(mainFrame);
            }
        });
    }
}
