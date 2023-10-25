package unknowncyberplugin.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import unknowncyberplugin.UnknownCyberFileProvider;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ghidra.util.Msg;

public class ProcTable extends JTable {
    // May replace DefaultTableModel with custom model to avoid copying response data
    // into an array of arrays.
    private DefaultTableModel tableModel;
    private UnknownCyberFileProvider fileProvider;
    private static final String[] COLUMN_NAMES = {"Address", "Occurrence #", "Type", "Notes", "Tags"};

    public ProcTable(Object[][] rowData, UnknownCyberFileProvider fileProvider){
        super(rowData, COLUMN_NAMES);
        this.fileProvider = fileProvider;

        tableModel = new DefaultTableModel(rowData, COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        setOpaque(true);
        setBackground(Color.WHITE);
        setRowHeight(20);
        
        ListSelectionModel rowSelectionModel = getSelectionModel();
        rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                if (ev.getClickCount() == 2) {
                    int row = getSelectedRow();
                    Object value = getValueAt(row, 0);
                    handleDoubleClick(value);
                }
            }
        });
    }

    public void addTableRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void handleDoubleClick(Object value) {
        Msg.info(this, ("Double-clicked on: " + value));
        // Create center tab for the double-clicked procedure
    }
}
