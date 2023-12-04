package unknowncyberplugin.components.collections;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public class ProcTable extends JTable {
    // May replace DefaultTableModel with custom model to avoid copying response data
    // into an array of arrays.
    private DefaultTableModel tableModel;
    private static final String[] COLUMN_NAMES = {
        "Address", "Name", "Group Hash", "Occurrences",
        "Blocks", "Code", "Type", "Notes", "Tags"
    };

    public ProcTable(Object[][] rowData){
        super(rowData, COLUMN_NAMES);

        tableModel = new DefaultTableModel(rowData, COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 3: // Occurrences
                    case 4: // Blocks
                    case 5: // Code
                    case 7: // Notes
                    case 8: // Tags
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };
        setModel(tableModel);
        setAutoCreateRowSorter(true);
        setOpaque(true);
        setBackground(Color.WHITE);
        setRowHeight(20);

        TableRowSorter<?> rowSorter = new TableRowSorter<>(getModel());
        setRowSorter(rowSorter);

        Comparator<String> addressComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                try {
                    Integer val1 = Integer.parseInt(s1.substring(2), 16);
                    Integer val2 = Integer.parseInt(s2.substring(2), 16);
                    return val1.compareTo(val2);
                } catch (NumberFormatException exc) {
                    return s1.compareTo(s2);
                }
            }
        };

        rowSorter.setComparator(0, addressComparator);
        
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
