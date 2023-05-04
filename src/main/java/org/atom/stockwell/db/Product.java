package org.atom.stockwell.db;

import java.util.UUID;

public class Product {
    private String id;
    private String barcodeId;
    private String name;
    private String category;

    public Product() {
        id = UUID.randomUUID().toString();
    }

    public String getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(String barcodeId) {
        this.barcodeId = barcodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
