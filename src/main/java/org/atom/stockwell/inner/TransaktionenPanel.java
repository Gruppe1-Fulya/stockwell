package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TransaktionenPanel extends JPanel {
    private JPanel transaktionenPanel;
    private JLabel transaktionenLabel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable transaktionenTable;

    public TransaktionenPanel(){
        add(transaktionenPanel);
        transaktionenTable.setModel(Controller.getTransaktionenTable());

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < transaktionenTable.getColumnCount(); columnIndex++) {
            transaktionenTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        transaktionenTable.setShowGrid(true);
    }
}
