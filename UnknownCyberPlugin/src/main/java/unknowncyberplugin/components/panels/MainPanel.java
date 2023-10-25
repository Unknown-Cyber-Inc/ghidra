package unknowncyberplugin.components.panels;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import unknowncyberplugin.UnknownCyberFileProvider;

public class MainPanel extends JPanel{

    public MainPanel(UnknownCyberFileProvider fileProvider){
        // Order of panel creation does not impact GUI layout
        FilePanel filePanel = new FilePanel(fileProvider);
        FileButtonsPanel fileButtonsPanel = new FileButtonsPanel(fileProvider, filePanel);
        CenterPanel centerPanel = new CenterPanel(fileProvider);
        ProcTablePanel procTablePanel = new ProcTablePanel(fileProvider, centerPanel);
        ProcButtonsPanel procButtonsPanel = new ProcButtonsPanel(procTablePanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // order of added panels below dictates GUI layout
        add(fileButtonsPanel);
        add(filePanel);
        add(centerPanel);
        add(procButtonsPanel);
        add(procTablePanel);
    }
    
}
