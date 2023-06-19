package org.atom.stockwell.inner.dialogs;

import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.atom.stockwell.db.builders.MitarbeiterBuilder;
import org.atom.stockwell.db.builders.PersonBuilder;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MitarbeiterHinzufuegenDialog extends JDialog {
    private JLabel nummerLabel;
    private JLabel mailAdresseLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JTextField nummerField;
    private JTextField mailField;
    private JButton cancelButton;
    private JButton okButton;
    private JPanel mitarbeiterHinzufuegenPanel;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel usernameLabel;
    private JTextField usernameField;

    public MitarbeiterHinzufuegenDialog(MainFrame mainFrame, PersonenPanel personenPanel){
        add(mitarbeiterHinzufuegenPanel);
        setTitle("Mitarbeiter Hinzufügen");
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
                String username = usernameField.getText();
                String password = passwordField.getText();

                Mitarbeiter mitarbeiter = new MitarbeiterBuilder()
                        .startBuild()
                        .setPerson(new PersonBuilder().startBuild().setName(name).setEmail(email).setPhoneNumber(nummer).doneBuild())
                        .setUsername(username)
                        .setPassword(password)
                        .doneBuild();

                try {
                    Controller.AddMitarbeiterDB(mitarbeiter);
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
