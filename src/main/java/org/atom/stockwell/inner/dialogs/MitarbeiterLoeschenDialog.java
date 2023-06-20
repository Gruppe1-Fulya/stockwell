package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MitarbeiterLoeschenDialog extends JDialog{
    private JPanel mitarbeiterLoeschenPanel;
    private JComboBox<Mitarbeiter> mitarbeiterListBox;
    private JLabel mitarbeiterLabel;
    private JButton cancelButton;
    private JButton okButton;

    public MitarbeiterLoeschenDialog(MainFrame mainFrame, PersonenPanel personenPanel){
        add(mitarbeiterLoeschenPanel);
        setTitle("Mitarbeiter Löschen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(230,120);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // KENDİMİZİ SİLEMEYELİM
        List<Mitarbeiter> mitarbeiterList = Controller.GetMitarbeiterList()
                .stream()
                .filter(mitarbeiter -> !mitarbeiter.getUsername().equals(mainFrame.getMainPanel().getUsername()))
                .toList();

        DefaultComboBoxModel<Mitarbeiter> mitarbeiterBoxModel = new DefaultComboBoxModel<>();
        for (Mitarbeiter mitarbeiter  : mitarbeiterList) {
            mitarbeiterBoxModel.addElement(mitarbeiter);
        }

        mitarbeiterListBox.setModel(mitarbeiterBoxModel);
        mitarbeiterListBox.setRenderer(new MitarbeiterRenderer());
        mitarbeiterListBox.setSelectedIndex(-1);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mitarbeiter selectedMitarbeiter = (Mitarbeiter) mitarbeiterListBox.getSelectedItem();
                try {
                    if(Controller.DisposePersonDB(selectedMitarbeiter))
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

    private static class MitarbeiterRenderer extends DefaultListCellRenderer {
        private final String placeholder = "----";
        @Override
        public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value == null) {
                value = placeholder;
                setForeground(Color.GRAY);
            } else if (value instanceof Mitarbeiter) {
                Mitarbeiter mitarbeiter = (Mitarbeiter) value;
                value = mitarbeiter.getName();
                setForeground(list.getForeground());
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }
}
