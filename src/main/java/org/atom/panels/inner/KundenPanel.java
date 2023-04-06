package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KundenPanel extends JPanel {
    private JPanel self;
    private JLabel kundenLabel;
    private JTable dataTable;

    public KundenPanel() {
        add(self);
        kundenLabel.setBorder(new EmptyBorder(5, 5, 0, 0));
        setBorder(new LineBorder(Color.BLACK)); // debugging

        String[] columnLabels = {
                "Id",
                "Name",
                "Last Name",
                "Company",
                "Phone Number"
        };

        Object[][] data = new Object[Database.clients.size()][5];

        for (int i = 0; i < Database.clients.size(); i++) {
            Client client = Database.clients.get(i);
            data[i] = new Object[]{
                    client.getId(),
                    client.name,
                    client.lastName,
                    client.company,
                    client.phoneNumber
            };
        }

        dataTable.setModel(new DefaultTableModel(
                data,
                columnLabels
        ));
    }
}
