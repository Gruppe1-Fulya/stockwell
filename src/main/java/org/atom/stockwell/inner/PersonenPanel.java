package org.atom.stockwell.inner;

import javax.swing.*;

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
    }
}
