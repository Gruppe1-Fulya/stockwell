package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.FinanzStatus;
import org.atom.stockwell.db.classes.Transaktion;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public interface FinanzController {

    static FinanzStatus getCurrentStatus() {
        FinanzStatus status = new FinanzStatus();
        DatabaseManager db = new DatabaseManager();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

        // 1. bölge
        db.getTransaktions()
                .forEach(transaktion -> {
                    if (transaktion.getType().equals("EINKAUF")) {
                        status.totalOutcome += transaktion.getTotalCost();
                    } else if (transaktion.getType().equals("VERKAUF")) {
                        status.totalIncome += transaktion.getTotalCost();
                    }
                });
        status.totalProfit = status.totalIncome - status.totalOutcome;

        // 2. bölge
        db.getTransaktions()
                .stream().filter(transaktion -> transaktion.getType().equals("VERKAUF"))
                .forEach(transaktion -> status.salesPerDay.put(
                        fmt.format(transaktion.getDate()),
                        transaktion.getTotalCost() +
                        status.salesPerDay.getOrDefault(fmt.format(transaktion.getDate()), 0)
                        )
                );

        db.getTransaktions()
                .stream().filter(transaktion -> transaktion.getType().equals("EINKAUF"))
                .forEach(transaktion -> status.purchasesPerDay.put(
                                fmt.format(transaktion.getDate()),
                                transaktion.getTotalCost() +
                                status.purchasesPerDay.getOrDefault(fmt.format(transaktion.getDate()), 0)
                        )
                );

        status.salesPerDay.forEach((key, value) -> status.profitPerDay.put(key, status.profitPerDay.getOrDefault(key, 0) + value));
        status.purchasesPerDay.forEach((key, value) -> status.profitPerDay.put(key, status.profitPerDay.getOrDefault(key, 0) - value));

        // 3. bölge
        List<Transaktion> transaktionList = db.getTransaktions();
        Collections.reverse(transaktionList);
        status.lastTransaktionen = transaktionList.toArray(new Transaktion[0]);

        // 4. bölge
        status.lastProduct = transaktionList.stream().filter(t -> t.getType().equals("EINKAUF")).findFirst();

        return status;
    };
}
