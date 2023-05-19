package org.atom.stockwell;


/*
    UI ile DB arasındaki controller
 */

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.*;

import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.util.List;

public class Controller {

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
    public static DefaultTableModel getLagerTable() {
        
        String[] columnNames = {
                "ID",
                "Name",
                "Datum",
                "Einzelpreis",
                "Anzahl",
                "Gesamtpreis"
        };

        DatabaseManager db = new DatabaseManager();
        Lager lager = db.getLager();
        List<LagerProduct> productList =lager.lagerProducts();

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(LagerProduct lagerProduct : productList){
            Object[] rowData = {
                    lagerProduct.getId(),
                    lagerProduct.getProduct().getName(),
                    lagerProduct.getDate(),
                    lagerProduct.getCost(),
                    lagerProduct.getAmount(),
                    lagerProduct.getAmount() * lagerProduct.getCost()
            };
            tableModel.addRow(rowData);
        }

        return tableModel;
    }

    public static DefaultTableModel getTransaktionenTable(){
        String[] columnNames = {
                "ID",
                "ProduktID",
                "Typ",
                "KundenID",
                "MitarbeiterID",
                "Datum",
                "Einzelpreis",
                "Anzahl",
                "Gesamtpreis"
        };

        DatabaseManager db = new DatabaseManager();
        List<Transaktion> transaktions = db.getTransaktions();

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Transaktion transaktion : transaktions){
            Object[] rowData = {
                    transaktion.getId(),
                    transaktion.getProduct().getId(),
                    transaktion.getType(),
                    transaktion.getKunde().getId(),
                    transaktion.getMitarbeiter().getId(),
                    transaktion.getDate(),
                    transaktion.getCost(),
                    transaktion.getAmount(),
                    transaktion.getAmount() * transaktion.getCost()
            };
            tableModel.addRow(rowData);
        }

        return tableModel;
    }

    public static DefaultTableModel getMitarbeiterTable(){
        String[] columnNames = {
                "ID",
                "Name",
                "Telefonnummer",
                "E-Mail Adresse",
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
            throw new Exception("[SW] PRODUCT DOESN'T EXITS");

        if (!db.getPersonList()
                .stream()
                .anyMatch(p -> p.getId().equals(transaktion.getKunde().getId())))
            throw new Exception("[SW] KUNDE DOESN'T EXITS");

        if (!db.getMitarbeiterList()
                .stream()
                .anyMatch(p -> p.getId().equals(transaktion.getMitarbeiter().getId())))
            throw new Exception("[SW] MITARBEITER DOESN'T EXITS");

        if (db.getTransaktions()
                .stream()
                .anyMatch(t -> t.getId().equals(transaktion.getId())))
            throw new Exception("[SW] TRANSAKTION ALREADY EXITS");


        if (!db.createNewTransaktion(transaktion))
            throw new Exception("[SW] DB ERROR");

        // returns check
        return db.getTransaktions()
                .stream()
                .anyMatch(t -> t.getId().equals(transaktion.getId()));

    }

}