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
    private JTable verlaufTable;
    private JScrollPane verlaufScrollPane;
    private JTabbedPane tabbedPane;
    private JScrollPane inventarScrollPane;
    private JTable inventarTable;

    public LagerPanel(){
        add(lagerPanel);
        updateTables();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTables();
            }
        });
    }

    public void updateTables() {
        inventarTable.setModel(Controller.getInventarTable());
        verlaufTable.setModel(Controller.getVerlaufTable());

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < inventarTable.getColumnCount(); columnIndex++) {
            inventarTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        for (int columnIndex = 0; columnIndex < verlaufTable.getColumnCount(); columnIndex++) {
            verlaufTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        verlaufTable.setShowGrid(true);
        inventarTable.setShowGrid(true);

    }
}
