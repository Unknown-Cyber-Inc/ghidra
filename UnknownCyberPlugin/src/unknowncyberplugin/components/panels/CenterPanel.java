package unknowncyberplugin.components.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.References;
import unknowncyberplugin.components.buttons.CenterCompareButton;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterTabbedPane;

public class CenterPanel extends JPanel{
    private CenterCRUDPanel centerCRUDPanel;
    private CenterCompareButton compareButton;
    private CenterTabbedPane centerTabs;

    public CenterPanel(){
        centerCRUDPanel = new CenterCRUDPanel();
        compareButton = new CenterCompareButton();
        compareButton.setVisible(false);
        centerTabs = new CenterTabbedPane();

        References.setCenterCRUDPanel(centerCRUDPanel);
        References.setCenterTabbedPane(centerTabs);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(centerTabs);
        add(centerCRUDPanel);
        add(compareButton);
    }

    public void addCenterTab(String tabName, BaseCenterTabPane tabPane){
        centerTabs.addClosableTab(tabName, tabPane);
    }
    
    public BaseCenterTabPane getActiveTabComponent(){
        return centerTabs.getActiveTabComponent();
    }

    public DefaultMutableTreeNode getSelectedTreeNode(){
        BaseCenterTabPane tab = centerTabs.getActiveTabComponent();
        CenterTree tree = tab.getTree();
        return tree.getCurrentSelection();
    }

    public CenterCompareButton getCompareButton() {
        return compareButton;
    }
}
