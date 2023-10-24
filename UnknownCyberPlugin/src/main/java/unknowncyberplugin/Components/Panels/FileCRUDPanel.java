package unknowncyberplugin.Components.Panels;

import javax.swing.*;
import unknowncyberplugin.Components.Buttons.FileCreateButton;
import unknowncyberplugin.Components.Buttons.FileDeleteButton;
import unknowncyberplugin.Components.Buttons.FileEditButton;

import java.awt.FlowLayout;

public class FileCRUDPanel extends JPanel{
    FileCreateButton createButton;
    FileEditButton editButton;
    FileDeleteButton deleteButton;

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
