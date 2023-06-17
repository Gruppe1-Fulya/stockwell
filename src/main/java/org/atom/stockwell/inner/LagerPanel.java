package org.atom.stockwell.inner;

import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.dialogs.HinzufuegenDialog;
import org.atom.stockwell.inner.dialogs.LoeschenDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton loeschenButton;
    private JButton hinzufuegenButton;
    private LagerPanel realThis;

    public LagerPanel(MainFrame mainFrame){

        realThis = this;
        add(lagerPanel);
        updateTables();

        hinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HinzufuegenDialog hinzufuegenDialog = new HinzufuegenDialog(mainFrame,realThis);

            }
        });
        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoeschenDialog loeschenDialog = new LoeschenDialog(mainFrame,realThis);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateTables();
            }
        });
    }

    public void updateTables() {
        inventarTable.setModel(Controller.getInventarTable(this));
        verlaufTable.setModel(Controller.getVerlaufTable(this));

        // Saga yatik olmasi icin
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, false, false, row, column);
                return c;
            }
        };
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

        for (int columnIndex = 0; columnIndex < inventarTable.getColumnCount(); columnIndex++) {
            inventarTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        for (int columnIndex = 0; columnIndex < verlaufTable.getColumnCount(); columnIndex++) {
            verlaufTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
        inventarTable.getTableHeader().setResizingAllowed(false);
        verlaufTable.getTableHeader().setResizingAllowed(false);
        verlaufTable.setShowGrid(true);
        inventarTable.setShowGrid(true);

    }
}
