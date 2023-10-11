package unknowncyberplugin.Components;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FileList<T extends Serializable> extends JList<T> {
    private DefaultListModel<T> listModel;
    private transient ListSelectionModel selectionModel;
    private T currentSelection;

    public FileList(DefaultListModel<T> model){
        super(model);
        listModel = model;
        selectionModel = getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent ev){
                if (!ev.getValueIsAdjusting()){
                    setSelection(getSelectedValue());
                }
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
        updateButtons();
    }

    // This is subject to move out of this class and into the parent panel.
    public void updateButtons(){
        // update CRUD buttons
    }

}
