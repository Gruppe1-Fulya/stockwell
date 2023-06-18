package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Transaktion;
import org.atom.stockwell.inner.TransaktionenPanel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface TransaktionController extends BaseController{

    static DefaultTableModel getTransaktionenTable(TransaktionenPanel transaktionenPanel){
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

    static boolean AddTransaktionDB(Transaktion transaktion) throws Exception {

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
