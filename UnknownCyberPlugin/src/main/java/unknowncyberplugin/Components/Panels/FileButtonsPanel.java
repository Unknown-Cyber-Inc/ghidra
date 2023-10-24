package unknowncyberplugin.Components.Panels;

import java.awt.FlowLayout;

import javax.swing.*;

import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.Components.Buttons.FileToggleButton;
import unknowncyberplugin.Components.Buttons.FileUploadButton;

public class FileButtonsPanel extends JPanel {
    FileToggleButton toggleButton;
    FileUploadButton uploadButton;

    public FileButtonsPanel(UnknownCyberFileProvider fileProvider){
        toggleButton = new FileToggleButton();
        uploadButton = new FileUploadButton(fileProvider);

        setLayout(new FlowLayout());
        add(toggleButton);
        add(uploadButton);
    }
    
}
