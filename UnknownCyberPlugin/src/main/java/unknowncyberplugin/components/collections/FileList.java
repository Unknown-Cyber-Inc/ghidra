package unknowncyberplugin.components.collections;
import java.io.Serializable;

import javax.swing.*;

import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.FileCRUDPanel;

public class FileList<T extends Serializable> extends JList<T> {
    private DefaultListModel<T> listModel;
    private T currentSelection;

    public FileList(String listType, DefaultListModel<T> model){
        super(model);
        
        listModel = model;
        ListSelectionModel selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()){
                setSelection(getSelectedValue());
                updateButtons(listType);
            }
        });
    }

    public void addItem(T item){
        listModel.addElement(item);
    }

    // remove by item
    public void removeItem(T item){
        listModel.removeElement(item);
    }

    // remove by index
    public void removeItemAt(int index){
        listModel.removeElementAt(index);
    }

    // edit item at index
    public void editItem(int index, T newValue){
        listModel.setElementAt(newValue, index);
    }

    // get index of an item
    public int indexOf(T item){
        return listModel.indexOf(item);
    }

    public T getSelection(){
        return this.currentSelection;
    }

    public void setSelection(T item){
        this.currentSelection = item;
    }

    public void updateButtons(String listType){
        FileCRUDPanel fcp = References.getFileCRUDPanel();
        if (listType.equalsIgnoreCase("notes")){
            fcp.noteItemSelected();
        } else if (listType.equalsIgnoreCase("tags")){
            fcp.tagItemSelected();
        } else {
            fcp.disableButtons();
        }
    }

}
