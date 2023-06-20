package org.atom.stockwell.inner.overview;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.theme.GGPlot2Theme;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ProfitGraphPanel extends XChartPanel<CategoryChart> {
    private JPanel profitGraphPanel;
    private ArrayList<String> keysData = new ArrayList<>(java.util.List.of(new String[]{"*"}));
    private ArrayList<Long> valuesData = new ArrayList<>(List.of(new Long[]{0L}));
    public ProfitGraphPanel(HashMap<String, Integer> profitData) {
        super(new CategoryChart(320, 80));
        this.getChart().setXAxisTitle("Datum");
        this.getChart().setYAxisTitle("Gewinn");
        this.getChart().setTitle("Gewinn");
        this.getChart().addSeries("e",
                keysData,
                valuesData
        );
        this.getChart().getStyler().setTheme(new GGPlot2Theme());
        this.getChart().getStyler().setLegendVisible(false);
        this.getChart().getStyler().setChartBackgroundColor(new Color(90, 87, 101));
        this.getChart().getStyler().setXAxisTickLabelsColor(new Color(208, 208, 208));
        this.getChart().getStyler().setYAxisTickLabelsColor(new Color(208, 208, 208));
        updateData(profitData);
    }


    public void updateData(HashMap<String, Integer> profitData) {
        keysData.clear();
        valuesData.clear();

        var keys = new ArrayList<>(profitData.keySet());
        keys.sort(Comparator.comparing(this::parseDate));

        for (String key : keys) {
            keysData.add(key);
            valuesData.add((long) profitData.get(key));
        }

        this.getChart().removeSeries("e");
        this.getChart().addSeries("e",
                keysData,
                valuesData
        );
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
