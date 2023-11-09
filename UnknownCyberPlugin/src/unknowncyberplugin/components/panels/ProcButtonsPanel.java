package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.ProcToggleButton;

public class ProcButtonsPanel extends JPanel{

    public ProcButtonsPanel(){
        ProcToggleButton toggleButton = new ProcToggleButton();

        setLayout(new FlowLayout());
        add(toggleButton);
    }
    
}
