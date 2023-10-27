package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTagsPane;
import unknowncyberplugin.components.popups.FileCRUDPopup;
import unknowncyberplugin.models.listitems.NoteItem;

public class FileEditButton extends BaseButton {
    private String popupReturnedText;
    private String binaryId;

    public FileEditButton() {
        super("Edit");
    }

    @Override
    protected void runClickedAction(){
        FilePanel fp = References.getFilePanel();
        String currentDisplayName = fp.getSelectedListItem().toString();
        UnknownCyberFileProvider fileProvider = Api.getFileProvider();
        FileCRUDPopup popup = new FileCRUDPopup();

        binaryId = fileProvider.getHash("sha1");
        popupReturnedText = null;
        popupReturnedText = popup.displayAndGetResponse(currentDisplayName);

        if (popupReturnedText != null){
            processItem((NoteItem)fp.getSelectedListItem());
        }
    }

    public void processItem(NoteItem selectedItem){
        // Object response = Api.updateFileNote(binaryId, selectedItem.getId(), popupReturnedText);;
        //     if (200 <= response.getStatus() <=300) {
        //         // create Note object from response data and place in method below
        //         selectedItem.setItemData(note);
        //     }
    }
}
