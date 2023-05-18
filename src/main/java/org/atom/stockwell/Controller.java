package org.atom.stockwell;


/*
    UI ile DB arasındaki controller
 */

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Lager;
import org.atom.stockwell.db.classes.LagerProduct;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.db.classes.Transaktion;

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
                "Menge",
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
