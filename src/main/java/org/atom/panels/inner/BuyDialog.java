package org.atom.panels.inner;

import org.atom.Database;
import org.atom.types.ProductTemplate;

import javax.swing.*;
import java.awt.event.*;

public class BuyDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox productBox;
    private JSpinner amountBox;
    private JSpinner costBox;
    private TransaktionPanel outerPanel;

    public BuyDialog(TransaktionPanel outerPanel) {
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        this.outerPanel = outerPanel;

        for (ProductTemplate template: Database.templates) {
            productBox.addItem(template.name + " #" + Integer.toString(template.getId()));
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        Database.BuyProduct(Database.templates.get(productBox.getSelectedIndex()),
                (int) amountBox.getValue(),
                (int) costBox.getValue()
        );
        outerPanel.RefreshData();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
