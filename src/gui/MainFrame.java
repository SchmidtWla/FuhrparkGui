package gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private JFrame myFrame;

    JPanel northPanelGrid;
    JLabel submit;
    JButton sizeButton;
    JPanel mainPanel;
    JPanel cardPanel;
    CardLayout cardLayout;
    private String showedPanel;
    private String previousPanel;
    JComboBox comboBox;



    public MainFrame() {
        this.myFrame = new JFrame("index");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.getContentPane().setLayout(new BorderLayout(5, 5));
        myFrame.setSize(450, 450);



        mainPanel = new JPanel(new BorderLayout(5, 5));

//        myFrame.add(tree, BorderLayout.WEST);
        // BoxLayout NorthPanel
        cardLayout = new MyCardLayout();
        cardPanel = new JPanel(cardLayout);

        northPanelGrid = new JPanel();
        northPanelGrid.setLayout(new BoxLayout(northPanelGrid, BoxLayout.PAGE_AXIS));
        createComboBox();
        northPanelGrid.add(comboBox);
        Tabelle uebersichtPanel = new Tabelle();
        cardPanel.add(uebersichtPanel.getPanel(), "Uebersicht");
        showedPanel = "Uebersicht";
        previousPanel = "Mitarbeiter";
        MitarbeiterGui mitarbeiter = new MitarbeiterGui("Mitarbeiter", false, false, false, false);
        MitarbeiterGui bueroarbeiter = new MitarbeiterGui("Büroarbeiter",true, false, false, false );
        MitarbeiterGui manager = new MitarbeiterGui("Manager",true, true, false, false );
        MitarbeiterGui schichtarbeiter = new MitarbeiterGui("Schichtarbeiter",false, false, true, true );

        cardPanel.add(mitarbeiter.getPanel(), "Mitarbeiter");
        cardPanel.add(bueroarbeiter.getPanel(), "Büroarbeiter");
        cardPanel.add(manager.getPanel(), "Manager");
        cardPanel.add(schichtarbeiter.getPanel(), "Schichtarbeiter");
        mainPanel.add(cardPanel, BorderLayout.NORTH);
        mainPanel.add(comboBox, BorderLayout.SOUTH);
        myFrame.add(mainPanel, BorderLayout.CENTER);


        // SouthPanel
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        MatteBorder border = new MatteBorder(1, 0, 0, 0, Color.WHITE);
        southPanel.setBorder(border);

        southPanel
                .setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        myFrame.add(southPanel, BorderLayout.SOUTH);

        // Create Button
        submit = new JLabel();

        southPanel.add(submit, BorderLayout.WEST);
        southPanel.add(createSwitchButton(), BorderLayout.EAST);

        myFrame.setVisible(true);
        System.out.println(myFrame.getContentPane().getLayout());
    }









    private JButton createSwitchButton() {

        sizeButton = new JButton("Ansicht Wechseln");
        sizeButton.addActionListener((event -> {
            if(showedPanel.equals("Uebersicht")) {
                cardLayout.show(cardPanel, previousPanel);
                showedPanel = previousPanel;
            } else {
                cardLayout.show(cardPanel, "Uebersicht");
                previousPanel = showedPanel;
                showedPanel = "Uebersicht";
            }
        }));

        return sizeButton;

    }

    private void createComboBox() {

        String[] mitarbeiter = {"Büroarbeiter", "Manager", "Schichtarbeiter"};
        comboBox = new JComboBox(mitarbeiter);
        comboBox.addActionListener(e -> {
            cardLayout.show(cardPanel, (String)comboBox.getSelectedItem());
        });
    }

    public static class MyCardLayout extends CardLayout {

        @Override
        public Dimension preferredLayoutSize(Container parent) {

            Component current = findCurrentComponent(parent);
            if (current != null) {
                Insets insets = parent.getInsets();
                Dimension pref = current.getPreferredSize();
                pref.width += insets.left + insets.right;
                pref.height += insets.top + insets.bottom;
                return pref;
            }
            return super.preferredLayoutSize(parent);
        }

        public Component findCurrentComponent(Container parent) {
            for (Component comp : parent.getComponents()) {
                if (comp.isVisible()) {
                    return comp;
                }
            }
            return null;
        }

    }
}
