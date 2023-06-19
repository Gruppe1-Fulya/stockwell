package org.atom.stockwell.db.classes;

import org.atom.stockwell.controllers.Controller;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class FinanzStatus {
    public int totalProfit;
    public int totalIncome;
    public int totalOutcome;
    public HashMap<String, Integer> salesPerDay = new HashMap<>();

    public HashMap<String, Integer> purchasesPerDay = new HashMap<>();
    public HashMap<String, Integer> profitPerDay = new HashMap<>();
    public Transaktion[] lastTransaktionen;
    public Optional<Transaktion> lastProduct;

    @Test
    public void test() {
        FinanzStatus status = Controller.getCurrentStatus();

        System.out.println("income: " + status.totalIncome +
                " \n outcome: " + status.totalOutcome +
                " \n profit: " + status.totalProfit
        );

        System.out.println("-----------------");

        status.salesPerDay.forEach((key, value) -> System.out.println(key.toString() + " " + value));

        System.out.println("-----------------");

        for (Transaktion transaktion : status.lastTransaktionen)
            System.out.println(transaktion.getId());

        System.out.println("-----------------");

        System.out.println("Last product id " + status.lastProduct.get().getId());
    }
}
