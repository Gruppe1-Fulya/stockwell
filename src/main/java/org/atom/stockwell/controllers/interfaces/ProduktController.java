package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.Product;

import java.util.List;

public interface ProduktController {
    static List<Product> GetProductList() {
        DatabaseManager db = new DatabaseManager();
        return db.getProductList()
                .stream()
                .filter(Product::isActive)
                .toList();
    }

    static boolean AddProductDB(Product product) throws Exception {
        DatabaseManager db = new DatabaseManager();

        // check if Product in db exits
        if (db.getProductList()
                .stream()
                .anyMatch(p -> p.getId().equals(product.getId())))
            throw new Exception("[SW] PRODUCT ALREADY EXITS");

        return db.createNewProduct(product);
    }

    static boolean DisposeProductDB(Product product) throws Exception {
        DatabaseManager db = new DatabaseManager();

        // check if Product in db exits
        if (db.getProductList()
                .stream()
                .noneMatch(p -> p.getId().equals(product.getId())))
            throw new Exception("[SW] PRODUCT NOT EXITS");

        product.setActive(false);
        return db.updateProduct(product);
    }

}
