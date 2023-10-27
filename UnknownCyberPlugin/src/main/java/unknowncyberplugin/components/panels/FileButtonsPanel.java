package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.FileToggleButton;
import unknowncyberplugin.components.buttons.FileUploadButton;

public class FileButtonsPanel extends JPanel {
    private FileToggleButton toggleButton;
    private FileUploadButton uploadButton;

    public FileButtonsPanel(){
        toggleButton = new FileToggleButton();
        uploadButton = new FileUploadButton();

        setLayout(new FlowLayout());
        add(toggleButton);
        add(uploadButton);
    }
    
}
