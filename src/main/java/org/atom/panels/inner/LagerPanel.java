package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class LagerPanel extends JPanel{
    private JPanel self;
    private JLabel lagerLabel;
    private JTable dataTable;

    public LagerPanel() {
        add(self);
        lagerLabel.setBorder(new EmptyBorder(5, 5, 0, 0));

        setBorder(new LineBorder(Color.BLACK)); // debugging

        RefreshData();
    }

    public void RefreshData() {
        String[] columnNames = {
                "Id",
                "Name",
                "Category",
                "Amount",
                "Unit Cost",
                "Total Cost"
        };

        Object[][] data = new Object[Database.lager.size()][6];

        for (int i = 0; i < Database.lager.size(); i++) {
            Product product = Database.lager.get(i);
            data[i] = new Object[]{
                    product.getId(),
                    product.name,
                    product.category.name,
                    product.amount,
                    product.unit_cost,
                    product.amount * product.unit_cost
            };
        }

        dataTable.setModel(new DefaultTableModel(
                data,
                columnNames
        ));
    }
}
