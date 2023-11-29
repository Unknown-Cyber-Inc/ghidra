package unknowncyberplugin.components.collections;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProcTable extends JTable {
    // May replace DefaultTableModel with custom model to avoid copying response data
    // into an array of arrays.
    private DefaultTableModel tableModel;
    private static final String[] COLUMN_NAMES = {
        "Address", "Name", "Group Hash", "Occurrence #",
        "Blocks", "Code", "Type", "Notes", "Tags"
    };

    public ProcTable(Object[][] rowData){
        super(rowData, COLUMN_NAMES);

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
                    Object startEa = getValueAt(row, 0);
                    Object hardHash = getValueAt(row, 2);
                    handleDoubleClick(startEa, hardHash);
                }
            }
        });
    }

    public void addTableRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void handleDoubleClick(Object startEa, Object hardHash) {
        CenterPanel cp = References.getCenterPanel();
        cp.addCenterTab(
            startEa.toString(),
            new CenterProcedureTabPane(startEa.toString(), hardHash.toString())
        );
    }

    public void clearTable(){
        tableModel.setRowCount(0);
    }
}
