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

public class CenterDefaultTabPane extends JPanel {

    public CenterDefaultTabPane(){
        super(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel instructionLabel = new JLabel(
            "<html>Double-click an address in the table below to create a new tab.<br><br>" +
            "Within a procedure tab's \"Similar Procedure Locations\" tree,<br>" +
            "double-click a file or procedure to create a tab for that item.</html>"
        );
        instructionLabel.setOpaque(true);
        instructionLabel.setBackground(Color.WHITE);

        JScrollPane defaultTab = new JScrollPane();

        add(instructionLabel);
        defaultTab.setViewportView(this);
        defaultTab.getViewport().setBackground(Color.WHITE);
    }
    
}
