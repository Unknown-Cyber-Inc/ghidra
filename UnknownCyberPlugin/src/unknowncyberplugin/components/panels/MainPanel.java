package unknowncyberplugin.components.panels;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.References;

public class MainPanel extends JPanel{

    public MainPanel(){
        // Order of panel creation does not impact GUI layout
        FileButtonsPanel fileButtonsPanel = new FileButtonsPanel();
        FilePanel filePanel = new FilePanel();
        CenterPanel centerPanel = new CenterPanel();
        ProcButtonsPanel procButtonsPanel = new ProcButtonsPanel();
        ProcTablePanel procTablePanel = new ProcTablePanel();

        References.setFileButtonsPanel(fileButtonsPanel);
        References.setFilePanel(filePanel);
        References.setCenterPanel(centerPanel);
        References.setProcButtonsPanel(procButtonsPanel);
        References.setProcTablePanel(procTablePanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Order of added panels below dictates GUI layout
        add(fileButtonsPanel);
        add(filePanel);
        add(centerPanel);
        add(procButtonsPanel);
        add(procTablePanel);
    }
    
}
