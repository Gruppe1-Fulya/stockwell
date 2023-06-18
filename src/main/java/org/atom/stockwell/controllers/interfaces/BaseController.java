package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public interface BaseController {
    String pattern = "dd-MM-yyyy HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    // LoginController
    static boolean checkIfUserExits(String username) {
        DatabaseManager db = new DatabaseManager();
        return db.getMitarbeiterList()
                .stream()
                .anyMatch(p -> p.getUsername().equals(username));
    }

    static boolean checkIfPasswordEqual(String username, String password) {
        DatabaseManager db = new DatabaseManager();
        return db.getMitarbeiterList()
                .stream()
                .anyMatch(p -> p.getUsername().equals(username) && p.getPassword().equals(password));
    }

    static boolean einLoggen(String username, String password) {
        return checkIfUserExits(username) && checkIfPasswordEqual(username, password);
    }

    static List<Person> GetKundenList() {
        return (new DatabaseManager()).getKundeList();
    }

    static Optional<Mitarbeiter> GetMitarbeiter(String username) {
        return (new DatabaseManager()).getMitarbeiterList()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    static DefaultTableModel getMitarbeiterTable(PersonenPanel personenPanel){
        String[] columnNames = {
                "ID",
                "Name",
                "Telefonnummer",
                "E-Mail-Adresse",
                "Abteilung"
        };

        DatabaseManager db = new DatabaseManager();
        List<Mitarbeiter> mitarbeiterList = db.getMitarbeiterList();

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Mitarbeiter mitarbeiter : mitarbeiterList){
            Object[] rowData = {
                    mitarbeiter.getId(),
                    mitarbeiter.getName(),
                    mitarbeiter.getPhoneNo(),
                    mitarbeiter.getEmail()
            };
            tableModel.addRow(rowData);
        }

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    personenPanel.updateTables();
                }
            }
        });

        return tableModel;
    }

    static DefaultTableModel getKundeTable(PersonenPanel personenPanel){
        String[] columnNames = {
                "ID",
                "Name",
                "Telefonnummer",
                "E-Mail-Adresse",
                "Abteilung"
        };

        DatabaseManager db = new DatabaseManager();
        List<Person> kundeList = db.getKundeList();

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Person kunde : kundeList){
            Object[] rowData = {
                    kunde.getId(),
                    kunde.getName(),
                    kunde.getPhoneNo(),
                    kunde.getEmail()
            };
            tableModel.addRow(rowData);
        }
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    personenPanel.updateTables();
                }
            }
        });

        return tableModel;
    }
}
