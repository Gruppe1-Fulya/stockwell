package org.atom.panels.inner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    private JPanel self;
    private JLabel homeLabel;

    public HomePanel() {
        add(self);
        homeLabel.setBorder(new EmptyBorder(5, 5, 0, 0));

        setBorder(new LineBorder(Color.BLACK)); // debugging
    }
}
