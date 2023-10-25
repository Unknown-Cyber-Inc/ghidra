package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.*;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.buttons.FileToggleButton;
import unknowncyberplugin.components.buttons.FileUploadButton;

public class FileButtonsPanel extends JPanel {
    private FileToggleButton toggleButton;
    private FileUploadButton uploadButton;

    public FileButtonsPanel(UnknownCyberFileProvider fileProvider, FilePanel filePanel){
        toggleButton = new FileToggleButton(filePanel);
        uploadButton = new FileUploadButton(fileProvider);

        setLayout(new FlowLayout());
        add(toggleButton);
        add(uploadButton);
    }
    
}
