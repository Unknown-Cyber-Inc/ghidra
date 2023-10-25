package unknowncyberplugin.components.panels;

import javax.swing.*;

import unknowncyberplugin.components.buttons.FileCreateButton;
import unknowncyberplugin.components.buttons.FileDeleteButton;
import unknowncyberplugin.components.buttons.FileEditButton;

import java.awt.FlowLayout;

public class FileCRUDPanel extends JPanel{
    private FileCreateButton createButton;
    private FileEditButton editButton;
    private FileDeleteButton deleteButton;

    public FileCRUDPanel(){
        setLayout(new FlowLayout());

        createButton = new FileCreateButton();
        editButton = new FileEditButton();
        deleteButton = new FileDeleteButton();

        add(createButton);
        add(editButton);
        add(deleteButton);
        disableButtons();
    }

    public void disableButtons(){
        updateButtons(false, false, false);
    }

    public void notesTabSelected(){
        updateButtons(true, false, false);
    }

    public void noteItemSelected(){
        updateButtons(true, true, true);
    }

    public void tagsTabSelected(){
        updateButtons(true, false, false);
    }

    public void tagItemSelected(){
        updateButtons(true, false, true);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        createButton.setEnabled(create);
        editButton.setEnabled(edit);
        deleteButton.setEnabled(delete);
    }
    
}
