package unknowncyberplugin.components.panes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.collections.ProcTable;
import unknowncyberplugin.components.panels.CenterPanel;

public class ProcTablePane extends JScrollPane{
    private UnknownCyberFileProvider fileProvider;
    private ProcTable table;

    public ProcTablePane(UnknownCyberFileProvider fileProvider, CenterPanel centerPanel){
        super();
        this.fileProvider = fileProvider;
        table = new ProcTable(null, fileProvider, centerPanel);

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
