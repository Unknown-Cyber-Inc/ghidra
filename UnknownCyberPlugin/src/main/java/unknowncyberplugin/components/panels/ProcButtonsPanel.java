package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.ProcToggleButton;

public class ProcButtonsPanel extends JPanel{

    public ProcButtonsPanel(ProcTablePanel table){
        ProcToggleButton toggleButton = new ProcToggleButton(table);

        setLayout(new FlowLayout());
        add(toggleButton);
    }
    
}
