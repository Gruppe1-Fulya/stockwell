package org.atom.stockwell;


/*
    UI ile DB arasındaki controller
 */

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.*;
import org.atom.stockwell.inner.LagerPanel;
import org.atom.stockwell.inner.PersonenPanel;
import org.atom.stockwell.inner.TransaktionenPanel;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Controller {

    static String pattern = "dd-MM-yyyy HH:mm:ss";
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
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

    public static boolean einLoggen(String username, String password) {
        return checkIfUserExits(username) && checkIfPasswordEqual(username, password);
    }

    // Lager Display Controller
    public static DefaultTableModel getVerlaufTable(LagerPanel lagerPanel) {
        
        String[] columnNames = {
                "TransaktionID",
                "ProduktID",
                "Name",
                "Datum",
                "Anzahl"
        };

        DatabaseManager db = new DatabaseManager();

        List<Transaktion> transaktionList = db.getTransaktions();

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Transaktion transaktion: transaktionList) {
            String dateString = simpleDateFormat.format(transaktion.getDate());
            Object[] rowData = {
                    transaktion.getId(),
                    transaktion.getProduct().getId(),
                    transaktion.getProduct().getName(),
                    dateString,
                    (transaktion.getType().equals("EINKAUF") ? "+" : "-") + transaktion.getAmount()
            };
            tableModel.addRow(rowData);
        }

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    lagerPanel.updateTables();
                }
            }
        });

        return tableModel;
    }

    public static DefaultTableModel getInventarTable(LagerPanel lagerPanel) {
        String[] columnNames = {
                "ID",
                "Name",
                "Barcode",
                "Kategorie",
                "Anzahl"
        };

        DatabaseManager db = new DatabaseManager();

        HashMap<Product, Integer> productMap = new HashMap<>();

        for (Product product : db.getProductList())
            productMap.put(product, 0);

        for (LagerProduct lagerProduct : db.getLager().lagerProducts())
            productMap.put(lagerProduct.getProduct(), lagerProduct.getAmount() + productMap.get(lagerProduct.getProduct()));

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Product product: productMap.keySet()) {
            int amount = productMap.get(product);

            Object[] rowData = {
                    product.getId(),
                    product.getName(),
                    product.getBarcodeId(),
                    product.getCategory(),
                    amount
            };
            tableModel.addRow(rowData);
        }
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    lagerPanel.updateTables();
                }
            }
        });

        return tableModel;
    }

    public static DefaultTableModel getTransaktionenTable(TransaktionenPanel transaktionenPanel){
        String[] columnNames = {
                "ID",
                "ProduktID",
                "Typ",
                "Kunde",
                "Mitarbeiter",
                "Datum",
                "Einzelpreis",
                "Anzahl",
                "Gesamtpreis"
        };

        DatabaseManager db = new DatabaseManager();
        List<Transaktion> transaktions = db.getTransaktions();
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Transaktion transaktion : transaktions){
            String dateString = simpleDateFormat.format(transaktion.getDate());
            Object[] rowData = {
                    transaktion.getId(),
                    transaktion.getProduct().getId(),
                    transaktion.getType(),
                    transaktion.getKunde().getName(),
                    transaktion.getMitarbeiter().getName(),
                    dateString,
                    transaktion.getCost(),
                    transaktion.getAmount(),
                    transaktion.getAmount() * transaktion.getCost()
            };
            tableModel.addRow(rowData);
        }

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS) {
                    transaktionenPanel.updateTable();
                }
            }
        });

        return tableModel;
    }

    public static DefaultTableModel getMitarbeiterTable(PersonenPanel personenPanel){
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

    public static DefaultTableModel getKundeTable(PersonenPanel personenPanel){
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

    // AddProductDB Template
    public static boolean AddProductDB(Product product) throws Exception {
        DatabaseManager db = new DatabaseManager();

        // check if Product in db exits
        if (db.getProductList()
                .stream()
                .anyMatch(p -> p.getId().equals(product.getId())))
            throw new Exception("[SW] PRODUCT ALREADY EXITS");

        return db.createNewProduct(product);
    }

    public static boolean AddProductLagerDB(LagerProduct product) throws Exception {

        DatabaseManager db = new DatabaseManager();

        Lager lager = db.getLager();

        if (!lager.lagerProducts()
                .stream()
                .anyMatch(lp -> lp.getId().equals(product.getId()))) {
            lager.addProduct(product);

            if (!db.updateLager(lager))
                throw new Exception("[SW] DB ERROR");

            return db.getLager().lagerProducts()
                    .stream()
                    .anyMatch(lp -> lp.getId().equals(product.getId()));

        }

        throw new Exception("[SW] PRODUCT ALREADY EXITS");
    }

    public static boolean AddTransaktionDB(Transaktion transaktion) throws Exception {

        DatabaseManager db = new DatabaseManager();

        if (!db.getProductList()
                .stream()
                .anyMatch(p -> p.getId().equals(transaktion.getProduct().getId())))
            throw new Exception("[SW] PRODUCT DOESN'T EXIST");

        if (!db.getPersonList()
                .stream()
                .anyMatch(p -> p.getId().equals(transaktion.getKunde().getId())))
            throw new Exception("[SW] KUNDE DOESN'T EXIST");

        if (!db.getMitarbeiterList()
                .stream()
                .anyMatch(p -> p.getId().equals(transaktion.getMitarbeiter().getId())))
            throw new Exception("[SW] MITARBEITER DOESN'T EXIST");

        if (db.getTransaktions()
                .stream()
                .anyMatch(t -> t.getId().equals(transaktion.getId())))
            throw new Exception("[SW] TRANSAKTION ALREADY EXISTS");


        if (!db.createNewTransaktion(transaktion))
            throw new Exception("[SW] DB ERROR");

        // returns check
        return db.getTransaktions()
                .stream()
                .anyMatch(t -> t.getId().equals(transaktion.getId()));

    }

}
