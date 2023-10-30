package unknowncyberplugin.components.panels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.TabCloseButton;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterDerivedFileTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.panes.CenterTabbedPane;

public class CenterTabSubPanel extends JPanel{

    public CenterTabSubPanel(final CenterTabbedPane pane, BaseCenterTabPane tabPane, String tabName){
        super(new FlowLayout());
        setOpaque(false);

        if (tabName.length() > 8){
            tabName = tabName.substring(0, 8) + "...";
        }

        JLabel label = new JLabel(tabName);

        if (tabPane instanceof CenterProcedureTabPane){
            label.setForeground(Color.GREEN);
        } else if (tabPane instanceof CenterDerivedProcedureTabPane){
            label.setForeground(Color.BLUE);
        } else if (tabPane instanceof CenterDerivedFileTabPane){
            label.setForeground(Color.RED);
        }

        TabCloseButton closeButton = new TabCloseButton();
        closeButton.addActionListener(ev -> {
            int index = pane.indexOfTabComponent(CenterTabSubPanel.this);
            if (index != -1){
                pane.removeTabAndCheckTabCount(index);
            }
        });

        add(label);
        add(closeButton);
    }
}
