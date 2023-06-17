package org.atom.stockwell.controllers.interfaces;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.classes.FinanzStatus;

public interface FinanzController {

    static FinanzStatus getCurrentStatus() {
        FinanzStatus status = new FinanzStatus();

        DatabaseManager db = new DatabaseManager();

        // 1. bölge
        db.getTransaktions()
                .forEach(transaktion -> {
                    if (transaktion.getType().equals("EINKAUF")) {
                        status.totalOutcome += transaktion.getCost();
                    } else if (transaktion.getType().equals("VERKAUF")) {
                        status.totalIncome += transaktion.getCost();
                    }
                });
        status.totalProfit = status.totalIncome - status.totalOutcome;

        // 2. bölge

        return status;
    };
}
