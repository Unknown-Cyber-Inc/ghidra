package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panes.CenterTabbedPane;

public class CenterPanel extends JPanel{
    private CenterCRUDPanel centerCRUDPanel;

    public CenterPanel(UnknownCyberFileProvider fileProvider){
        centerCRUDPanel = new CenterCRUDPanel();
        CenterTabbedPane centerTabs = new CenterTabbedPane();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(centerTabs);
        add(centerCRUDPanel);
    }

    public CenterCRUDPanel getCenterCRUDPanel(){
        return centerCRUDPanel;
    }
}
