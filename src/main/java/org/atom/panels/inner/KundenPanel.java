package org.atom.panels.inner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class KundenPanel extends JPanel {
    private JPanel self;
    private JLabel kundenLabel;

    public KundenPanel() {
        add(self);
        kundenLabel.setBorder(new EmptyBorder(5, 5, 0, 0));

        setBorder(new LineBorder(Color.BLACK)); // debugging
    }
}
