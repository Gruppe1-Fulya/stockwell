package org.atom.stockwell.db.builders;

import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.db.classes.Transaktion;

import java.util.Date;

public class TransaktionBuilder {
    Transaktion currentTransaktion;

    public TransaktionBuilder startBuild() {
        currentTransaktion = new Transaktion();
        return this;
    }

    public TransaktionBuilder setId(String id) {
        currentTransaktion.setId(id);
        return this;
    }

    public TransaktionBuilder setProduct(Product product) {
        currentTransaktion.setProduct(product);
        return this;
    }

    public TransaktionBuilder setType(String type) {
        currentTransaktion.setType(type);
        return this;
    }

    public TransaktionBuilder setAmount(int amount) {
        currentTransaktion.setAmount(amount);
        return this;
    }

    public TransaktionBuilder setCost(int cost) {
        currentTransaktion.setCost(cost);
        return this;
    }

    public TransaktionBuilder setDate(Date date) {
        currentTransaktion.setDate(date);
        return this;
    }

    public Transaktion doneBuild() {
        return currentTransaktion;
    }
}
