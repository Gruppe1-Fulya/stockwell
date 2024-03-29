package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;
import org.atom.stockwell.inner.PersonenPanel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
                .anyMatch(p -> p.getUsername().equals(username) && p.isActive());
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
        return new DatabaseManager().getKundeList()
                .stream()
                .filter(Person::isActive)
                .toList();
    }

    static List<Mitarbeiter> GetMitarbeiterList(){
        return new DatabaseManager().getMitarbeiterList()
                .stream()
                .filter(Person::isActive)
                .toList();
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
                "E-Mail-Adresse"
        };

        List<Mitarbeiter> mitarbeiterList = BaseController.GetMitarbeiterList();

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
                "E-Mail-Adresse"
        };

        List<Person> kundeList = BaseController.GetKundenList();

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

    static boolean AddPersonDB(Person person) throws Exception{
        DatabaseManager db = new DatabaseManager();

        if (db.getPersonList()
                .stream()
                .anyMatch(p -> p.getId().equals(person.getId())))
            throw new Exception("[SW] PERSON ALREADY EXISTS");

        return db.createNewPerson(person);
    }

    static boolean AddMitarbeiterDB(Mitarbeiter mitarbeiter) throws Exception{
        DatabaseManager db = new DatabaseManager();

        if (db.getMitarbeiterList()
                .stream()
                .anyMatch(m -> m.getId().equals(mitarbeiter.getId())))
            throw new Exception("[SW] MITARBEITER ALREADY EXISTS");

        if (db.getMitarbeiterList()
                .stream()
                .anyMatch(m -> m.getUsername().equals(mitarbeiter.getUsername())))
            throw new Exception("[SW] USERNAME TAKEN");

        return db.createNewMitarbeiter(mitarbeiter);
    }

    static boolean DisposePersonDB(Person person) throws Exception {
        DatabaseManager db = new DatabaseManager();

        if (db.getPersonList()
                .stream()
                .noneMatch(p -> p.getId().equals(person.getId())))
            throw new Exception("[SW] PERSON DOESN'T EXIST");

        person.setActive(false);
        return db.updatePerson(person);
    }

}
