package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.Main;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.builders.PersonBuilder;
import org.atom.stockwell.db.classes.Person;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KundenHinzufuegenDialog extends JDialog {

    private JPanel kundenHinzufuegenPanel;
    private JLabel nummerLabel;
    private JLabel mailAdresseLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JTextField nummerField;
    private JTextField mailField;
    private JButton cancelButton;
    private JButton okButton;

    public KundenHinzufuegenDialog(MainFrame mainFrame, PersonenPanel personenPanel){
        add(kundenHinzufuegenPanel);
        setTitle("Kunde Hinzufügen");
        setAlwaysOnTop(true);
        setModal(true);
        setSize(300,230);
        setResizable(false);
        setLocationRelativeTo(mainFrame);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = mailField.getText();
                String nummer = nummerField.getText();

                Person kunde = new PersonBuilder()
                        .startBuild()
                        .setName(name)
                        .setEmail(email)
                        .setPhoneNumber(nummer)
                        .doneBuild();

                try {
                    Controller.AddPersonDB(kunde);
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
}
