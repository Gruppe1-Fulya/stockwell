package org.atom.types;

public class Product extends ProductTemplate {
    private int amount;
    private int unit_cost;

    public Person purchaser;

    public Product(ProductTemplate template, Person purchaser, int id, int amount, int unit_cost) {
        super(template);

        setId(id);
        this.amount = amount;
        this.unit_cost = unit_cost;
        this.purchaser = purchaser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(int unit_cost) {
        this.unit_cost = unit_cost;
    }
}
