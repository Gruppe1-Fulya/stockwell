package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PersonenPanel extends JPanel {
    private JPanel personenPanel;
    private JLabel personenLabel;
    private JPanel contentPanel;
    private JPanel kundenPanel;
    private JPanel mitarbeiterPanel;
    private JScrollPane kundenScrollPane;
    private JTable kundenTable;
    private JScrollPane mitarbeiterScrollPane;
    private JTable mitarbeiterTable;
    private JLabel kundenLabel;
    private JLabel mitarbeiterLabel;

    public PersonenPanel(){
        add(personenPanel);
        updateTables();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTables();
            }
        });
    }

    public void updateTables(){
        mitarbeiterTable.setModel(Controller.getMitarbeiterTable());
        kundenTable.setModel(Controller.getKundeTable());

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < mitarbeiterTable.getColumnCount(); columnIndex++) {
            mitarbeiterTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        for (int columnIndex = 0; columnIndex < kundenTable.getColumnCount(); columnIndex++) {
            kundenTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }

        mitarbeiterTable.getTableHeader().setResizingAllowed(false);
        kundenTable.getTableHeader().setResizingAllowed(false);
        mitarbeiterTable.setShowGrid(true);
        kundenTable.setShowGrid(true);
    }
}
