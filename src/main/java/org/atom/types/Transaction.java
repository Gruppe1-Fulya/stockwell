package org.atom.types;

enum TransactionType {
    SALE,
    PURCHASE
}

public class Transaction {
    private int id;

    private Product product;
    private Person initiator;

    private int amount;


    public TransactionType transactionType;
}
