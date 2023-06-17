package org.atom.stockwell.inner.graphs;

import javax.swing.*;
import java.awt.*;

public class SalesGraphPanel extends JPanel {

    private JPanel salesGraphPanel;
    private int[] salesData;
    public SalesGraphPanel(int[] salesData) {
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

        g.setColor(new Color(90, 87, 101));
        g.fillRect(0, 0, width, height);
        g2d.setStroke(new BasicStroke(1.5f));
        g.setColor(Color.BLACK);

        int graphWidth = width - 80;
        int graphHeight = height - 80;

        int xGap = graphWidth / (salesData.length - 1);
        int yGap = graphHeight / (getMaxValue() / 100);

        g2d.setStroke(new BasicStroke(0.5f));
        g.setColor(new Color(140, 140, 140));
        for (int i = 0; i < salesData.length; i++) {
            int x = 40 + i * xGap;
            g.drawLine(x, height - 40, x, 40);
        }

        for (int i = 1; i < getMaxValue() / 100; i++) {
            int y = height - 40 - i * yGap;
            g.drawLine(40, y, width - 40, y);
        }

        g2d.setStroke(new BasicStroke(1.5f));
        g.drawLine(40, height - 40, width - 40, height - 40);
        g.drawLine(40, height - 40, 40, 40);

        // Data lines
        g2d.setStroke(new BasicStroke(2.5f));
        g.setColor(new Color(44, 62, 80));
        for (int i = 0; i < salesData.length - 1; i++) {
            int x1 = 40 + i * xGap;
            int y1 = height - 40 - salesData[i] / 100 * yGap;
            int x2 = 40 + (i + 1) * xGap;
            int y2 = height - 40 - salesData[i + 1] / 100 * yGap;
            g.drawLine(x1, y1, x2, y2);
        }

        // Labels
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.BLACK);
        for (int i = 0; i < salesData.length; i++) {
            int x = 40 + i * xGap;

            // Y labels
            String salesLabel = String.valueOf(salesData[i]);
            int salesLabelWidth = fm.stringWidth(salesLabel);
            int y = height - 40 - salesData[i] / 100 * yGap;
            g.drawString(salesLabel, 20, y + fm.getAscent() / 2);

            // Month labels
            String monthLabel = "Month " + (i + 1);
            int labelWidth = fm.stringWidth(monthLabel);
            g.drawString(monthLabel, x - labelWidth / 2, height - 20);
        }
    }


    private int getMaxValue() {
        int max = salesData[0];
        for (int i = 1; i < salesData.length; i++) {
            if (salesData[i] > max) {
                max = salesData[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sales Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int[] salesData = {100, 150, 200, 250, 300, 350, 400, 450, 500};
        SalesGraphPanel graphPanel = new SalesGraphPanel(salesData);

        frame.add(graphPanel);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

