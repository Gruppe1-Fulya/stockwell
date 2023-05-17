package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;
import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Lager;
import org.atom.stockwell.db.classes.LagerProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LagerPanel extends JPanel {
    private JPanel lagerPanel;
    private JLabel lagerLabel;
    private JPanel contentPanel;
    private JTable lagerTable;
    private JScrollPane scrollPane;

    public LagerPanel(){
        add(lagerPanel);
        lagerTable.setModel(Controller.getLagerTable());

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < lagerTable.getColumnCount(); columnIndex++) {
            lagerTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        lagerTable.setShowGrid(true);
    }
}
