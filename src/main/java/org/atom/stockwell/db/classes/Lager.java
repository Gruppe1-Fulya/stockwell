package org.atom.stockwell.db.classes;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private ArrayList<LagerProduct> productArrayList;

    public Lager() {
        productArrayList = new ArrayList<>();
    }

    public int getAnzahl() {
        return productArrayList.size();
    }

    public void addProduct(LagerProduct product) {
        productArrayList.add(product);
    }

    public void removeProduct(LagerProduct product) {
        productArrayList.remove(
                productArrayList.stream()
                        .filter(lp -> lp.getId().equals(product.getId()))
                        .findFirst().get()
        );
    }

    public List<LagerProduct> lagerProducts() {
        return productArrayList;
    }


}
