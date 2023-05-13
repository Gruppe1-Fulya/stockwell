package org.atom.stockwell.db.classes;

import java.util.Date;
import java.util.UUID;

public class LagerProduct {

    private String id;
    private Product product;
    private Date date;
    private int amount;
    private int cost;

    public LagerProduct() {
        id = UUID.randomUUID().toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
