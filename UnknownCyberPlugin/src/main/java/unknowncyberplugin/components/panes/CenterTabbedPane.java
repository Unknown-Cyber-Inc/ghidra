package unknowncyberplugin.components.panes;

import java.awt.BorderLayout;
import java.awt.Color;

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
        tabName = colorizeTabTitle(tabName, tabPane);
        addTab(tabName, tabPane);
        setTabComponentAt(getTabCount()-1, new CenterTabSubPanel(this));
        if (getTabCount() == 2 && defaultTabExists){
            remove(DEFAULT_TAB_INDEX);
            defaultTabExists = false;
        }
    }

    public String colorizeTabTitle(String tabName, BaseCenterTabPane tabPane){
        String end = "</font></html>";

        if (tabPane instanceof CenterProcedurePane){
            tabName = "<html><font color='green'>" + tabName + end;
        } else if (tabPane instanceof CenterDerivedProcedurePane){
            tabName = "<html><font color='blue'>" + tabName + end;
        } else if (tabPane instanceof CenterDerivedFilePane){
            tabName = "<html><font color='red'>" + tabName + end;
        }
        return tabName;
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
        return (BaseCenterTabPane) getTabComponentAt(getSelectedIndex());
    }
}
