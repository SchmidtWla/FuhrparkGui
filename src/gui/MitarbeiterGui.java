package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MitarbeiterGui {

    private final String name;
    JPanel panel;
    JTextField textField;
    JSpinner spinner;

    public MitarbeiterGui(String name) {
        this.name = name;
        createPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel(this.name);
        label.setForeground(Color.BLACK);
        panel.add(label);
        createMitarbeiterPanel();
        panel.setVisible(true);
    }

    private Border createOuterBorder(String title) {
        Border dateEmptyOuterBorder = BorderFactory.createEmptyBorder(5, 10, 10, 10);
        Border dateOuterBorder = BorderFactory.createTitledBorder(null, title, 0, 0, null, Color.BLACK);
        Border dateInnerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border dateCompoundBorder = BorderFactory.createCompoundBorder(dateOuterBorder, dateInnerBorder);
        return BorderFactory.createCompoundBorder(dateEmptyOuterBorder, dateCompoundBorder);
    }

    private void createMitarbeiterPanel() {
        // North Panel Parameter
        JPanel mitarbeiterEingabePanel = new JPanel();
        mitarbeiterEingabePanel.setLayout(new BoxLayout(mitarbeiterEingabePanel, BoxLayout.PAGE_AXIS));
        JPanel distanzPanel = new JPanel(new BorderLayout(5, 5));
        mitarbeiterEingabePanel.add(distanzPanel);
        Border outerBorderParameter = createOuterBorder("Eingabe für " + name + ": ");
        mitarbeiterEingabePanel.setBorder(outerBorderParameter);
        panel.add(mitarbeiterEingabePanel);

        // GridLayout ParameterNorthEast
        JPanel parameterNorthEastPanel = new JPanel(new GridLayout(4, 1, 1, 5));

        distanzPanel.add(parameterNorthEastPanel, BorderLayout.WEST);

        // GridLayout ParameterNorthCenter
        JPanel parameterNorthCenterPanel = new JPanel(new GridLayout(4, 1, 1, 5));

        distanzPanel.add(parameterNorthCenterPanel, BorderLayout.CENTER);

        // Create IdLabel
        JLabel IdLabel = new JLabel("ID: ");
        parameterNorthEastPanel.add(IdLabel);

        // Create nameLabel
        JLabel nameLabel = new JLabel("Name: ");
        parameterNorthEastPanel.add(nameLabel);

        // Create vornameLabel
        JLabel vornameLabel = new JLabel("Vorname: ");
        parameterNorthEastPanel.add(vornameLabel);

        // Create gehaltLabel
        JLabel gehaltLabel = new JLabel("Gehalt: ");
        parameterNorthEastPanel.add(gehaltLabel);

        // Create JTextField
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        //Create idField
        JTextField idField = new JTextField();
        parameterNorthCenterPanel.add(idField);

        //Create nameField
        JTextField nameField = new JTextField();
        parameterNorthCenterPanel.add(nameField);

        //Create vornameField
        JTextField vornameField = new JTextField();
        parameterNorthCenterPanel.add(vornameField);

        //Create gehaltField
        JTextField gehaltField = new JTextField();
        parameterNorthCenterPanel.add(gehaltField);

        JButton button = new JButton("Submit");
        panel.add(button);
    }
}

