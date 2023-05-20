package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LagerPanel extends JPanel {
    private JPanel lagerPanel;
    private JLabel lagerLabel;
    private JPanel contentPanel;
    private JTable lagerTable;
    private JScrollPane verlaufScrollPane;
    private JTabbedPane tabbedPane;
    private JScrollPane inventarScrollPane;

    public LagerPanel(){
        add(lagerPanel);
        updateTable();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTable();
            }
        });
    }

    public void updateTable() {
        lagerTable.setModel(Controller.getVerlaufTable());
        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < lagerTable.getColumnCount(); columnIndex++) {
            lagerTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        lagerTable.setShowGrid(true);

    }
}
