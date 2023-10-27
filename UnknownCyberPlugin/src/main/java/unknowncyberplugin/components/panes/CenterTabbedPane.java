package unknowncyberplugin.components.panes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import unknowncyberplugin.components.panels.CenterTabSubPanel;

public class CenterTabbedPane extends JTabbedPane{
    private static final int DEFAULT_TAB_INDEX = 0;
    private boolean defaultTabExists;

    public CenterTabbedPane(){
        super();
        setPreferredSize(new Dimension(getPreferredSize().width, 200));
        generateDefaultTab();
    }

    private void generateDefaultTab(){
        JPanel defaultPanel = new JPanel(new BorderLayout());
        defaultPanel.setBackground(Color.WHITE);
        
        JLabel instructionLabel = new JLabel(
            "<html>Double-click an address in the table below to create a new tab.<br><br>" +
            "Within a procedure tab's \"Similar Procedure Locations\" tree,<br>" +
            "double-click a file or procedure to create a tab for that item.</html>"
        );
        instructionLabel.setOpaque(true);
        instructionLabel.setBackground(Color.WHITE);

        JScrollPane defaultTab = new JScrollPane();

        defaultPanel.add(instructionLabel);
        defaultTab.setViewportView(defaultPanel);
        defaultTab.getViewport().setBackground(Color.WHITE);
        
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
        remove(index);
        // may need to update tab colors here
        if (getTabCount() == 0){
            generateDefaultTab();
            defaultTabExists = true;
        }
    }

    public BaseCenterTabPane getActiveTabComponent(){
        if (getSelectedIndex() == -1){
            return null;
        }
        return (BaseCenterTabPane) getComponentAt(getSelectedIndex());
    }
}
