package org.atom.stockwell.db.classes;

import java.util.ArrayList;

public class Lager {
    private ArrayList<Product> productArrayList;

    public Lager() {
        productArrayList = new ArrayList<>();
    }

    public int getAnzahl() {
        return productArrayList.size();
    }

    public void addProduct(Product product) {
        productArrayList.add(product);
    }
}
