package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.buttons.ProcRetrievalButton;
import unknowncyberplugin.components.panes.ProcTablePane;

public class ProcTablePanel extends JPanel{

    public ProcTablePanel(UnknownCyberFileProvider fileProvider){
        ProcRetrievalButton procRetrievalButton = new ProcRetrievalButton();
        ProcTablePane tablePane = new ProcTablePane(fileProvider);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(procRetrievalButton);
        add(tablePane);
    }
    
}
