package org.atom.stockwell.db.builders;

import org.atom.stockwell.db.classes.Product;

public class ProductBuilder {

    Product currentProduct;

    public ProductBuilder startBuild() {
        currentProduct = new Product();
        return this;
    }

    public ProductBuilder setId(String id) {
        currentProduct.setId(id);
        return this;
    }

    public ProductBuilder setBarcodeId(String barcodeId) {
        currentProduct.setBarcodeId(barcodeId);
        return this;
    }

    public ProductBuilder setName(String name) {
        currentProduct.setName(name);
        return this;
    }

    public ProductBuilder setCategory(String category) {
        currentProduct.setCategory(category);
        return this;
    }

    public ProductBuilder setActive(boolean active) {
        currentProduct.setActive(active);
        return this;
    }

    public Product doneBuild() {
        return currentProduct;
    }

}
