package org.atom.stockwell.inner;

import org.atom.stockwell.Controller;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.inner.dialogs.EinkaufDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TransaktionenPanel extends JPanel {
    private JPanel transaktionenPanel;
    private JLabel transaktionenLabel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTable transaktionenTable;
    private JButton verkaufButton;
    private JButton einkaufButton;

    private TransaktionenPanel realThis;

    public TransaktionenPanel(MainFrame mainFrame){

        realThis = this;

        add(transaktionenPanel);
        updateTable();

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
                EinkaufDialog einkaufDialog = new EinkaufDialog(mainFrame, realThis);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                updateTable();
            }
        });

    }

    public void updateTable() {
        transaktionenTable.setModel(Controller.getTransaktionenTable());
    }

}
