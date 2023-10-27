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

        // PHONY DATA -- REMOVE ONCE API CALLS IN PLACE
        Object[][] data = {
            {"ox1", "5", "someMalware", "2", "1"},
            {"ox2", "2", "otherMalware", "1", "0"},
            {"ox3", "1", "badMalware", "3", "2"},
            {"ox4", "4", "someMalware", "5", "3"},
            {"ox5", "2", "BADMalware", "0", "1"},
            {"ox6", "3", "sosoMalware", "2", "1"},
            {"ox1", "5", "someMalware", "2", "1"},
            {"ox2", "2", "otherMalware", "1", "0"},
            {"ox3", "1", "badMalware", "3", "2"},
            {"ox4", "4", "someMalware", "5", "3"},
            {"ox5", "2", "BADMalware", "0", "1"},
            {"ox6", "3", "sosoMalware", "2", "1"},
            {"ox1", "5", "someMalware", "2", "1"},
            {"ox2", "2", "otherMalware", "1", "0"},
            {"ox3", "1", "badMalware", "3", "2"},
            {"ox4", "4", "someMalware", "5", "3"},
            {"ox5", "2", "BADMalware", "0", "1"},
            {"ox6", "3", "sosoMalware", "2", "1"},
        };

        populate(data);
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
