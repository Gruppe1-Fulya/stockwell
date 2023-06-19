package org.atom.stockwell.inner.overview;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PurchasesGraphPanel extends XChartPanel<CategoryChart> {
    private JPanel purchasesGraphPanel;
    private ArrayList<String> keysData = new ArrayList<>(java.util.List.of(new String[]{"*"}));
    private ArrayList<Long> valuesData = new ArrayList<>(List.of(new Long[]{0L}));
    public PurchasesGraphPanel(HashMap<String, Integer> purchasesData) {
        super(new CategoryChart(320, 80));
        this.getChart().setXAxisTitle("Datum");
        this.getChart().setYAxisTitle("Aufwand");
        this.getChart().setTitle("Einkäufe");
        this.getChart().addSeries("e",
                keysData,
                valuesData
        );
        this.getChart().getStyler().setLegendVisible(false);
        updateData(purchasesData);
    }

    public void updateData(HashMap<String, Integer> purchasesData) {
        keysData.clear();
        valuesData.clear();

        var keys = new ArrayList<>(purchasesData.keySet());
        keys.sort(Comparator.comparing(this::parseDate));

        for (String key : keys) {
            keysData.add(key);
            valuesData.add((long) purchasesData.get(key));
        }

        this.getChart().removeSeries("e");
        this.getChart().addSeries("e",
                keysData,
                valuesData
        );
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
