package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KundenLoeschenDialog extends JDialog{
    private JPanel kundenLoeschenPanel;
    private JComboBox<Person> kundenListBox;
    private JLabel kundenLabel;
    private JButton cancelButton;
    private JButton okButton;

    public KundenLoeschenDialog(MainFrame mainFrame, PersonenPanel personenPanel){
        add(kundenLoeschenPanel);
        setTitle("Kunde Löschen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(230,120);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        List<Person> kundenList = Controller.GetKundenList();

        DefaultComboBoxModel<Person> kundenBoxModel = new DefaultComboBoxModel<>();
        for (Person kunde  : kundenList) {
            kundenBoxModel.addElement(kunde);
        }

        kundenListBox.setModel(kundenBoxModel);
        kundenListBox.setRenderer(new KundenRenderer());
        kundenListBox.setSelectedIndex(-1);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person selectedKunde = (Person) kundenListBox.getSelectedItem();
                try {
                    if(Controller.DisposePersonDB(selectedKunde))
                        personenPanel.updateTables();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static class KundenRenderer extends DefaultListCellRenderer {
        private final String placeholder = "----";
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value == null) {
                value = placeholder;
                setForeground(Color.GRAY);
            } else if (value instanceof Person) {
                Person kunde = (Person) value;
                value = kunde.getName();
                setForeground(list.getForeground());
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
