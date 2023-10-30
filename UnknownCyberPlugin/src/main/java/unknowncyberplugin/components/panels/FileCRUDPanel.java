package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.FileCreateButton;
import unknowncyberplugin.components.buttons.FileDeleteButton;
import unknowncyberplugin.components.buttons.FileEditButton;

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

    public void noteSelected(){
        updateButtons(true, true, true);
    }

    public void tagsTabSelected(){
        updateButtons(true, false, false);
    }

    public void tagSelected(){
        updateButtons(true, false, true);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        createButton.setEnabled(create);
        editButton.setEnabled(edit);
        deleteButton.setEnabled(delete);
    }
    
}
