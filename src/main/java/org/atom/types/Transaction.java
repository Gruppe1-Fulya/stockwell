package org.atom.types;

public class Transaction {
    private int id;

    public Product product;
    public Person initiator;
    public int amount;
    public int cost;
    public TransactionType transactionType;

    public Transaction(int id, Product product, Person initiator, int amount, int cost, TransactionType transactionType) {
        this.id = id;
        this.product = product;
        this.initiator = initiator;
        this.amount = amount;
        this.cost = cost;
        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
