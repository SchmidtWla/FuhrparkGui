package gui;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

    public CustomTableModel() {
        super(new String[]{"Id", "Name","Vorname", "Gehalt"}, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 0:
                clazz = String.class;
                break;
            case 1:
                clazz = Double.class;
                break;
            case 2:
                clazz = Boolean.class;
                break;
            case 3:
                clazz = Boolean.class;
                break;

        }
        return clazz;
    }
}
