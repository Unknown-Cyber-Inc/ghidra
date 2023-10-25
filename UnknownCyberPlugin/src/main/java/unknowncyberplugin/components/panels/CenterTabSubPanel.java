package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.TabCloseButton;
import unknowncyberplugin.components.panes.CenterTabbedPane;

public class CenterTabSubPanel extends JPanel{

    public CenterTabSubPanel(final CenterTabbedPane pane, String tabName){
        super(new FlowLayout());
        setOpaque(false);

        JLabel label = new JLabel(tabName);

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
