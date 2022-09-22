package gui;

import arbeiter.Mitarbeiter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tabelle {


    static JTable table;
    static Map<String, Mitarbeiter> map = new HashMap<>();

    public static Map<String, Mitarbeiter> getMap() {
        return map;
    }

    JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    JTextField textField;
    JSpinner spinner;

    public Tabelle() {
        createPanel();
    }
    public JTable getTable() {
        return this.table;
    }
    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(5,5));
        panel.add(createTable(), BorderLayout.CENTER);
        JLabel label = new JLabel("Filtern");
        panel.setBorder(createOuterBorder("Tabelle"));
        panel.setVisible(true);

    }

    private Border createOuterBorder(String title) {
        Border dateEmptyOuterBorder = BorderFactory.createEmptyBorder(5, 10, 10, 10);
        Border dateOuterBorder = BorderFactory.createTitledBorder(null, title, 0, 0, null, Color.BLACK);
        Border dateInnerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border dateCompoundBorder = BorderFactory.createCompoundBorder(dateOuterBorder, dateInnerBorder);
        return BorderFactory.createCompoundBorder(dateEmptyOuterBorder, dateCompoundBorder);
    }

    private JScrollPane createTable() {

        CustomTableModel model = new CustomTableModel();


        table = new JTable(model);
        table.setShowGrid(false);
        JScrollPane scrollpane = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);
        table.setPreferredScrollableViewportSize(scrollpane.getPreferredSize());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return scrollpane;
    }
}
