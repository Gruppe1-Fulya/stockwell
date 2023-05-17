package org.atom.stockwell;


/*
    UI ile DB arasındaki controller
 */

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Lager;
import org.atom.stockwell.db.classes.LagerProduct;

import javax.swing.table.DefaultTableModel;
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

}
