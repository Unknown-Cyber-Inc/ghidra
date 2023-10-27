package unknowncyberplugin.components.collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.FileCRUDPanel;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTagsPane;

public class FileList extends JList<Object> {
    private DefaultListModel<Object> listModel;
    private transient Object currentSelection;

    public FileList(DefaultListModel<Object> model){
        super(model);
        
        listModel = model;
        ListSelectionModel selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()){
                setSelection(getSelectedValue());
                updateButtons();
            }
        });
    }

    public void addItem(Object item){
        listModel.addElement(item);
    }

    // remove by item
    public void removeItem(Object item){
        listModel.removeElement(item);
    }

    // remove by index
    public void removeItemAt(int index){
        listModel.removeElementAt(index);
    }

    // edit item at index
    public void editItem(int index, Object newValue){
        listModel.setElementAt(newValue, index);
    }

    // get index of an item
    public int indexOf(Object item){
        return listModel.indexOf(item);
    }

    public Object getSelection(){
        return currentSelection;
    }

    public void setSelection(Object item){
        currentSelection = item;
    }

    public void updateButtons(){
        FileCRUDPanel fcp = References.getFileCRUDPanel();
        BaseFileListPane pane = References.getFilePanel().getActiveTabComponent();
        if (pane instanceof FileNotesPane){
            fcp.noteItemSelected();
        } else if (pane instanceof FileTagsPane){
            fcp.tagItemSelected();
        } else {
            fcp.disableButtons();
        }
    }

}
