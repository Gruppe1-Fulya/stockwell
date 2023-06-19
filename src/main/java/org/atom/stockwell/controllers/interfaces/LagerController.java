package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Lager;
import org.atom.stockwell.db.classes.LagerProduct;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.db.classes.Transaktion;
import org.atom.stockwell.inner.LagerPanel;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;

public interface LagerController extends BaseController {
    static DefaultTableModel getVerlaufTable(LagerPanel lagerPanel) {

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

    static DefaultTableModel getInventarTable(LagerPanel lagerPanel) {
        String[] columnNames = {
                "ID",
                "Name",
                "Barcode",
                "Kategorie",
                "Anzahl"
        };

        DatabaseManager db = new DatabaseManager();

        HashMap<Product, Integer> productMap = new HashMap<>();

        for (Product product : ProduktController.GetProductList())
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

    static boolean AddProductLagerDB(LagerProduct product) throws Exception {

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

    static int GetProductCountInLager(Product product) {
        int c = 0;
        DatabaseManager db = new DatabaseManager();
        for (LagerProduct lagerProduct : db.getLager().lagerProducts()) {
            if (lagerProduct.getProduct().getId().equals(product.getId()))
                c += lagerProduct.getAmount();
        }
        return c;
    }

}
