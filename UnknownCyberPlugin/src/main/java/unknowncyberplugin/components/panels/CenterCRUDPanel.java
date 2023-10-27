package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import unknowncyberplugin.components.buttons.CenterCreateButton;
import unknowncyberplugin.components.buttons.CenterDeleteButton;
import unknowncyberplugin.components.buttons.CenterEditButton;

public class CenterCRUDPanel extends JPanel{
    private CenterCreateButton createButton;
    private CenterEditButton editButton;
    private CenterDeleteButton deleteButton;

    public CenterCRUDPanel(){
        setLayout(new FlowLayout());

        createButton = new CenterCreateButton();
        editButton = new CenterEditButton();
        deleteButton = new CenterDeleteButton();

        add(createButton);
        add(editButton);
        add(deleteButton);
        disableButtons();
    }

    public void disableButtons(){
        updateButtons(false, false, false);
    }

    public void notesNodeSelected(){
        updateButtons(true, false, false);
    }

    public void noteItemSelected(){
        updateButtons(true, true, true);
    }

    public void tagsNodeSelected(){
        updateButtons(true, false, false);
    }

    public void tagItemSelected(){
        updateButtons(true, false, true);
    }

    public void procedureRootSelected(){
        updateButtons(false, true, false);
    }

    public void updateButtons(boolean create, boolean edit, boolean delete){
        createButton.setEnabled(create);
        editButton.setEnabled(edit);
        deleteButton.setEnabled(delete);
    }
    
}
