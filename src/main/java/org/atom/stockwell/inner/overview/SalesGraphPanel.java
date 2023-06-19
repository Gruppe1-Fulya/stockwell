package org.atom.stockwell.inner.overview;

import ch.qos.logback.core.joran.action.NOPAction;
import org.atom.stockwell.MainFrame;
import org.atom.stockwell.controllers.Controller;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.theme.GGPlot2Theme;
import org.knowm.xchart.style.theme.Theme;
import org.springframework.context.ApplicationListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class SalesGraphPanel extends XChartPanel<CategoryChart> {

    private JPanel salesGraphPanel;
    private ArrayList<String> keysData = new ArrayList<>(List.of(new String[]{"*"}));
    private ArrayList<Long> valuesData = new ArrayList<>(List.of(new Long[]{0L}));

    public SalesGraphPanel(HashMap<String, Integer> salesData) {
        super(new CategoryChart(320, 80));
        this.getChart().setXAxisTitle("Datum");
        this.getChart().setYAxisTitle("Ertrag");
        this.getChart().setTitle("Verkäufe");
        this.getChart().addSeries("e",
                keysData,
                valuesData
        );
        this.getChart().getStyler().setTheme(new GGPlot2Theme());
        this.getChart().getStyler().setLegendVisible(false);
        this.getChart().getStyler().setChartBackgroundColor(new Color(90, 87, 101));
        this.getChart().getStyler().setXAxisTickLabelsColor(new Color(208, 208, 208));
        this.getChart().getStyler().setYAxisTickLabelsColor(new Color(208, 208, 208));
        updateData(salesData);
    }
    public void updateData(HashMap<String, Integer> salesData) {
        keysData.clear();
        valuesData.clear();

        var keys = new ArrayList<>(salesData.keySet());
        keys.sort(Comparator.comparing(this::parseDate));

        for (String key : keys) {
            keysData.add(key);
            valuesData.add((long) salesData.get(key));
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
