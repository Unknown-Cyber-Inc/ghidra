package unknowncyberplugin.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProcTable extends JTable {
    private DefaultTableModel tableModel;
    private transient ListSelectionModel rowSelectionModel;
    private static final String[] COLUMN_NAMES = {"Address", "Occurrence #", "Type", "Notes", "Tags"};

    public ProcTable(Object[][] rowData){
        super(rowData, COLUMN_NAMES);
        
        tableModel = new DefaultTableModel(rowData, COLUMN_NAMES);
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        
        rowSelectionModel = getSelectionModel();
        rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = getSelectedRow();
                    Object value = getValueAt(row, 0);
                    handleDoubleClick(value);
                }
            }
        });
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void handleDoubleClick(Object value) {
        System.out.println("Double-clicked on: " + value);
        // Create center tab for the double-clicked procedure
    }
}
