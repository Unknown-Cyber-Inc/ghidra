package unknowncyberplugin.components.collections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.CenterProcedurePane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ghidra.util.Msg;

public class ProcTable extends JTable {
    // May replace DefaultTableModel with custom model to avoid copying response data
    // into an array of arrays.
    private DefaultTableModel tableModel;
    private UnknownCyberFileProvider fileProvider;
    private CenterPanel centerPanel;
    private static final String[] COLUMN_NAMES = {"Address", "Occurrence #", "Type", "Notes", "Tags"};

    public ProcTable(Object[][] rowData, UnknownCyberFileProvider fileProvider, CenterPanel centerPanel){
        super(rowData, COLUMN_NAMES);
        this.fileProvider = fileProvider;
        this.centerPanel = centerPanel;

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
        centerPanel.addCenterTab(
            value.toString(),
            new CenterProcedurePane(value.toString(), centerPanel.getCenterCRUDPanel(), fileProvider)
        );
    }
}
