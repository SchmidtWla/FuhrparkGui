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
    private static String showedPanel;


    public static String getShowedPanel() {
        return showedPanel;
    }

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
        Tabelle uebersichtPanel = new Tabelle();
        CustomTableModel model =(CustomTableModel)uebersichtPanel.getTable().getModel();
        model.addRow((new Object[]{"230", "Peter", "Schneider", 5200} ));
        cardPanel.add(uebersichtPanel.getPanel(), "Uebersicht");
        showedPanel = "Uebersicht";
        MitarbeiterGui mitarbeiterGui = new MitarbeiterGui("Mitarbeiter");
        cardPanel.add(mitarbeiterGui.getPanel(), "Mitarbeiter");
        mainPanel.add(cardPanel, BorderLayout.NORTH);
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
                cardLayout.show(cardPanel, "Mitarbeiter");
                showedPanel = "Mitarbeiter";
            } else {
                cardLayout.show(cardPanel, "Uebersicht");
                showedPanel = "Uebersicht";
            }
        }));

        return sizeButton;

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
