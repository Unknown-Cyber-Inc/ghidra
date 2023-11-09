package unknowncyberplugin.components.panes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import unknowncyberplugin.components.collections.ProcTable;

public class ProcTablePane extends JScrollPane{
    private ProcTable table;

    public ProcTablePane(){
        super();
        table = new ProcTable(null);

        setOpaque(true);
        setBackground(Color.WHITE);
        setViewportView(table);
        setPreferredSize(new Dimension(
            getPreferredSize().width,
            (10 * table.getRowHeight())
        ));
    }
    
    public void populate(Object[][] data){
        for (Object[] row : data){
            table.addTableRow(row);
        }
    }

    public ProcTable getProcTable(){
        return table;
    }
}
