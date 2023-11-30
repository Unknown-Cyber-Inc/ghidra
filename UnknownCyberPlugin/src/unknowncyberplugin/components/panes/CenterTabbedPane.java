package unknowncyberplugin.components.panes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterTabSubPanel;
import unknowncyberplugin.components.buttons.CenterCompareButton;

public class CenterTabbedPane extends JTabbedPane{
    private static final int DEFAULT_TAB_INDEX = 0;
    private boolean defaultTabExists;

    public CenterTabbedPane(){
        super();
        setPreferredSize(new Dimension(getPreferredSize().width, 200));
        generateDefaultTab();

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tabChanged();
            }
        });
    }

    private void tabChanged() {
        CenterCompareButton ccp = References.getCenterPanel().getCompareButton();
        if(getComponentAt(getSelectedIndex()) instanceof CenterDefaultTabPane){
            ccp.hideButton();
        } else {
            BaseCenterTabPane selectedTab =  getActiveTabComponent();
            if (selectedTab != null) {
                if (selectedTab instanceof CenterDerivedProcedureTabPane) {
                    ccp.showButton();
                } else {
                    ccp.hideButton();
                }
            }
        }
    }

    private void generateDefaultTab(){
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        CenterDefaultTabPane defaultTab = new CenterDefaultTabPane();
        
        addTab("Get Started", defaultTab);
        defaultTabExists = true;
    }

    public void addClosableTab(String tabName, BaseCenterTabPane tabPane){
        
        addTab(tabName, tabPane);

        // Place close button ('x') in the tab header
        setTabComponentAt(getTabCount()-1, new CenterTabSubPanel(this, tabPane, tabName));
        if (getTabCount() == 2 && defaultTabExists){
            remove(DEFAULT_TAB_INDEX);
            defaultTabExists = false;
        }
    }

    public void removeTabAndCheckTabCount(int index){
        // may need to update tab colors here
        if (getTabCount() == 1){
            generateDefaultTab();
            defaultTabExists = true;
        }
        remove(index);
    }

    public BaseCenterTabPane getActiveTabComponent(){
        if (getSelectedIndex() == -1){
            return null;
        }
        return (BaseCenterTabPane) getComponentAt(getSelectedIndex());
    }

    public void setActiveTabComponent(BaseCenterTabPane tab){
        setSelectedComponent(tab);
    }
}
