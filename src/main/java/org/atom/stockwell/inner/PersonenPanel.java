package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class PersonenPanel extends JPanel {
    private JPanel personenPanel;
    private JLabel PersonenLabel;
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

        mitarbeiterTable.setShowGrid(true);
        kundenTable.setShowGrid(true);
    }
}
