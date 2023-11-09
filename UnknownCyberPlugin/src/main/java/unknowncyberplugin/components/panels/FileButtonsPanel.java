package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.FileToggleButton;
import unknowncyberplugin.components.buttons.FileUploadButton;
import unknowncyberplugin.components.buttons.StatusButton;

public class FileButtonsPanel extends JPanel {
    private FileToggleButton toggleButton;
    private FileUploadButton uploadButton;
    private StatusButton statusButton;

    public FileButtonsPanel(){
        toggleButton = new FileToggleButton();
        uploadButton = new FileUploadButton();
        statusButton = new StatusButton();
        statusButton.setEnabled(false);

        setLayout(new FlowLayout());
        add(statusButton);
        add(toggleButton);
        add(uploadButton);
    }

    public FileToggleButton getToggleButton(){
        return toggleButton;
    }

    public StatusButton getStatusButton(){
        return statusButton;
    }
    
}
