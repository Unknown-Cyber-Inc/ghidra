package unknowncyberplugin.components.panels;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.References;

public class MainPanel extends JPanel{

    public MainPanel(){
        // Order of panel creation does not impact GUI layout
        FilePanel filePanel = new FilePanel();
        FileButtonsPanel fileButtonsPanel = new FileButtonsPanel();
        CenterPanel centerPanel = new CenterPanel();
        ProcTablePanel procTablePanel = new ProcTablePanel();
        ProcButtonsPanel procButtonsPanel = new ProcButtonsPanel(procTablePanel);

        References.setFilePanel(filePanel);
        References.setCenterPanel(centerPanel);

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
