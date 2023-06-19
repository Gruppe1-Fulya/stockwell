package org.atom.stockwell.db.classes;

import org.atom.stockwell.controllers.Controller;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public FinanzStatus() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate currentDate = LocalDate.now();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM");
        int days = 7;
        while (days > 0) {
            salesPerDay.put(fmt.format(Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant())), 0);
            purchasesPerDay.put(fmt.format(Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant())), 0);
            profitPerDay.put(fmt.format(Date.from(currentDate.atStartOfDay(defaultZoneId).toInstant())), 0);
            currentDate = currentDate.minusDays(1);
            days--;
        }
    }

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
