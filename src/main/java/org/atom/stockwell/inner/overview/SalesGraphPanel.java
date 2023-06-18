package org.atom.stockwell.inner.overview;

import ch.qos.logback.core.joran.action.NOPAction;
import org.atom.stockwell.MainFrame;
import org.springframework.context.ApplicationListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SalesGraphPanel extends JPanel {

    private JPanel salesGraphPanel;
    private HashMap<String, Long> salesData;

    public SalesGraphPanel(HashMap<String, Long> salesData) {
        this.salesData = salesData;
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

        int xGap = graphWidth / (salesData.size() - 1);
        int yGap = graphHeight / (getMaxValue() / 100);

        g2d.setStroke(new BasicStroke(0.1f));

        g2d.setComposite(gridComposite);
        g.setColor(new Color(140, 140, 140));
        int i = 0;
        for (String date : salesData.keySet()) {
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
        for (String date : salesData.keySet()) {
            int x = 40 + i * xGap;
            int y = height - 40 - (int) (salesData.get(date) / 100 * yGap);

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
        for (String date : salesData.keySet()) {
            int x = 40 + i * xGap;

            // Y labels
            String salesLabel = String.valueOf(salesData.get(date));
            int salesLabelWidth = fm.stringWidth(salesLabel);
            int y = height - 40 - (int) (salesData.get(date) / 100 * yGap);
            g.drawString(salesLabel, 14, y + fm.getAscent() / 2);

            // Month labels
            String monthLabel = "Month " + (i + 1);
            int labelWidth = fm.stringWidth(monthLabel);
            g.drawString(monthLabel, x - labelWidth / 2, height - 20);

            i++;
        }
    }

    private int getMaxValue() {
        long max = 0;
        for (long value : salesData.values()) {
            if (value > max) {
                max = value;
            }
        }
        return (int) max;
    }
}
