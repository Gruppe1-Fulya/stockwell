package org.atom.stockwell.inner;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.inner.dialogs.MitarbeiterHinzufuegenDialog;
import org.atom.stockwell.inner.dialogs.MitarbeiterLoeschenDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton mitarbeiterHinzufuegenButton;
    private JButton mitarbeiterLoeschenButton;
    private JButton kundenHinzufuegenButton;
    private JButton kundenLoeschenButton;
    private PersonenPanel realThis;

    public PersonenPanel(MainFrame mainFrame){
        realThis = this;
        add(personenPanel);
        updateTables();
        mitarbeiterHinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MitarbeiterHinzufuegenDialog mitarbeiterHinzufuegenDialog = new MitarbeiterHinzufuegenDialog(mainFrame,realThis);
            }
        });
        mitarbeiterLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MitarbeiterLoeschenDialog mitarbeiterLoeschenDialog = new MitarbeiterLoeschenDialog(mainFrame,realThis);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTables();
            }
        });
    }

    public void updateTables(){
        mitarbeiterTable.setModel(Controller.getMitarbeiterTable(this));
        kundenTable.setModel(Controller.getKundeTable(this));

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, false, false, row, column);
                return c;
            }
        };
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
