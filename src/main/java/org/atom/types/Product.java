package org.atom.types;

public class Product extends ProductTemplate {
    public int amount;
    public int unit_cost;

    public Person purchaser;

    public Product(ProductTemplate template, Person purchaser, int id, int amount, int unit_cost) {
        super(template);

        setId(id);
        this.amount = amount;
        this.unit_cost = unit_cost;
        this.purchaser = purchaser;
    }
}
