package org.atom.stockwell.db.classes;

public class FinanzStatus {
    public int totalProfit;
    public int totalIncome;
    public int totalOutcome;
    public int[] salesDay; // her index bir gün
    public int[] salesMonth; // her index bir ay
    public int[] salesYear; // her index bir yıl
    public Transaktion[] lastTransaktionen;
    public Product lastProduct;
}
