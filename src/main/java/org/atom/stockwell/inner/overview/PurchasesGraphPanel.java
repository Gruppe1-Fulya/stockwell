package org.atom.stockwell.inner.overview;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class PurchasesGraphPanel extends JPanel {
    private JPanel purchasesGraphPanel;
    private ArrayList<String> keysData = new ArrayList<>();
    private ArrayList<Long> valuesData = new ArrayList<>();
    public PurchasesGraphPanel(HashMap<String, Integer> purchasesData) {
        updateData(purchasesData);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g;

        AlphaComposite gridComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        AlphaComposite lineComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

        g.setColor(new Color(90, 87, 101));
        g.fillRect(0, 0, width, height);
        g2d.setStroke(new BasicStroke(1.5f));
        g.setColor(Color.BLACK);

        int graphWidth = width - 80;
        int graphHeight = height - 80;

        int xGap = graphWidth / ((valuesData.size() <= 1) ? 1 : (valuesData.size() - 1));
        int yGap = graphHeight / ((int) Math.ceil(((double) getMaxValue() / 100)));

        g2d.setStroke(new BasicStroke(0.1f));

        g2d.setComposite(gridComposite);
        g.setColor(new Color(140, 140, 140));
        int i = 0;
        for (String date : keysData) {
            int x = 40 + i * xGap;
            g.drawLine(x, height - 40, x, 40);
            i++;
        }

        i = 1;
        for (int j = 1; j <= getMaxValue() / 100; j++) {
            int y = height - 40 - j * yGap;
            g.drawLine(40, y, width - 40, y);
            i++;
        }

        g2d.setStroke(new BasicStroke(1.5f));
        g.drawLine(40, height - 40, width - 40, height - 40);
        g.drawLine(40, height - 40, 40, 40);

        // Data lines
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.setComposite(lineComposite);
        g.setColor(new Color(44, 62, 80));

        i = 0;
        int prevX = -1;
        int prevY = -1;
        for (Long value : valuesData) {
            int x = 40 + i * xGap;
            int y = height - 40 - (int) (value / 100 * yGap);

            if (prevX != -1 && prevY != -1) {
                g.drawLine(prevX, prevY, x, y);
            }

            prevX = x;
            prevY = y;
            i++;
        }

        // Labels
        g.setFont(new Font("Open Sans", Font.PLAIN, 14));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.BLACK);

        i = 0;
        for (int k = 0; k < keysData.size(); k++) {
            String date = keysData.get(k);
            Long value = valuesData.get(k);
            int x = 40 + i * xGap;

            // Y labels
            int labelWidth = fm.stringWidth(date);
            int y = height - 40 - (int) (value / 100 * yGap);

            // data point as a filled circle
            g.fillOval(x - 3, y - 3, 6, 6);

            // Value labels on data points
            String purchasesLabel = String.valueOf(value);
            int purchasesLabelWidth = fm.stringWidth(purchasesLabel);
            int valueLabelX = x - purchasesLabelWidth / 2;
            int valueLabelY = y - 10;
            g.drawString(purchasesLabel, valueLabelX, valueLabelY);

            // Month labels
            g.drawString(date, x - labelWidth / 2, height - 20);

            i++;
        }
    }


    private int getMaxValue() {
        long max = 1;
        for (long value : valuesData) {
            if (value > max) {
                max = value;
            }
        }
        return (int) max;
    }

    private int getMinValue() {
        long min = Long.MAX_VALUE;
        for (long value : valuesData) {
            if (value < min) {
                min = value;
            }
        }
        return (int) ((getMaxValue() != min) ? min : min - 1);
    }

    public void updateData(HashMap<String, Integer> purchasesData) {
        keysData.clear();
        valuesData.clear();
        int total = 0;

        var keys = new ArrayList<>(purchasesData.keySet());
        keys.sort(Comparator.comparing(this::parseDate));

        for (String key : keys) {
            // total += purchasesData.get(key);
            // valuesData.add((long) total);
            keysData.add(key);
            valuesData.add((long) purchasesData.get(key));
        }

        repaint();
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
