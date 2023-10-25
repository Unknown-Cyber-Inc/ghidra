package unknowncyberplugin.components.panes;

import javax.swing.JScrollPane;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.ProcTable;

public class ProcTablePane extends JScrollPane{
    private UnknownCyberFileProvider fileProvider;
    private ProcTable table;

    public ProcTablePane(UnknownCyberFileProvider fileProvider){
        super();
        this.fileProvider = fileProvider;
        table = new ProcTable(null, fileProvider);
    }
    
    public void populate(Object[][] data){
        for (Object[] row : data){
            table.addRow(row);
        }
    }

    public ProcTable getProcTable(){
        return table;
    }
}
