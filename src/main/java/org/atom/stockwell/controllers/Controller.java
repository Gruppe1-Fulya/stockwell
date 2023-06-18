package org.atom.stockwell.controllers;


/*
    UI ile DB arasındaki controller
 */

import org.atom.stockwell.controllers.interfaces.*;
import org.atom.stockwell.db.classes.*;
import org.atom.stockwell.inner.LagerPanel;
import org.atom.stockwell.inner.PersonenPanel;
import org.atom.stockwell.inner.TransaktionenPanel;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class Controller {

    // Login Operations
    public static boolean einLoggen(String username, String password) {
        return BaseController.einLoggen(username, password);
    }

    // Mitarbeiter and Kunde Operations
    public static List<Person> GetKundenList() {
        return BaseController.GetKundenList();
    }

    public static Optional<Mitarbeiter> GetMitarbeiter(String username) {
        return BaseController.GetMitarbeiter(username);
    }

    public static DefaultTableModel getMitarbeiterTable(PersonenPanel personenPanel) {
        return BaseController.getMitarbeiterTable(personenPanel);
    }

    public static DefaultTableModel getKundeTable(PersonenPanel personenPanel) {
        return BaseController.getKundeTable(personenPanel);
    }

    // LagerController
    public static DefaultTableModel getVerlaufTable(LagerPanel lagerPanel) {
        return LagerController.getVerlaufTable(lagerPanel);
    }

    public static DefaultTableModel getInventarTable(LagerPanel lagerPanel) {
        return LagerController.getInventarTable(lagerPanel);
    }

    public static boolean AddProductLagerDB(LagerProduct product) throws Exception {
        return LagerController.AddProductLagerDB(product);
    }

    // ProduktController
    public static List<Product> GetProductList() {
        return ProduktController.GetProductList();
    }

    public static boolean AddProductDB(Product product) throws Exception {
        return ProduktController.AddProductDB(product);
    }

    public static boolean DisposeProductDB(Product product) throws Exception {
        return ProduktController.DisposeProductDB(product);
    }

    // TransaktionController

    public static DefaultTableModel getTransaktionenTable(TransaktionenPanel transaktionenPanel) {
        return TransaktionController.getTransaktionenTable(transaktionenPanel);
    }

    public static boolean AddTransaktionDB(Transaktion transaktion) throws Exception {
        return TransaktionController.AddTransaktionDB(transaktion);
    }

    // FinanzController

    public static FinanzStatus getCurrentStatus() {
        return FinanzController.getCurrentStatus();
    }

}
