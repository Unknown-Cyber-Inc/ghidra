package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterTabbedPane;

public class CenterPanel extends JPanel{
    private CenterCRUDPanel centerCRUDPanel;
    private CenterTabbedPane centerTabs;

    public CenterPanel(UnknownCyberFileProvider fileProvider){
        centerCRUDPanel = new CenterCRUDPanel();
        centerTabs = new CenterTabbedPane();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(centerTabs);
        add(centerCRUDPanel);
    }

    public CenterCRUDPanel getCenterCRUDPanel(){
        return centerCRUDPanel;
    }

    public void addCenterTab(String tabName, BaseCenterTabPane tabPane){
        centerTabs.addClosableTab(tabName, tabPane);
    }
}
