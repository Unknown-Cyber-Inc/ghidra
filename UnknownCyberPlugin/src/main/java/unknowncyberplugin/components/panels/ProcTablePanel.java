package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.References;
import unknowncyberplugin.components.buttons.ProcRetrievalButton;
import unknowncyberplugin.components.panes.ProcTablePane;

public class ProcTablePanel extends JPanel{
    private ProcTablePane tablePane;

    public ProcTablePanel(){
        ProcRetrievalButton procRetrievalButton = new ProcRetrievalButton();
        tablePane = new ProcTablePane();

        References.setProcTablePane(tablePane);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(procRetrievalButton);
        add(tablePane);
    }
    
}
