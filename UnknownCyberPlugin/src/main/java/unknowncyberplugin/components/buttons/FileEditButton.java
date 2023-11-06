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
import unknowncyberplugin.models.responsedata.Note;

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
        UnknownCyberFileProvider fileProvider = References.getFileProvider();
        FileCRUDPopup popup = new FileCRUDPopup();

        binaryId = fileProvider.getOriginalSha1();
        popupReturnedText = null;
        popupReturnedText = popup.displayAndGetResponse(currentDisplayName);

        if (popupReturnedText != null){
            processItem((Note)fp.getSelectedListItem());
        }
    }

    public void processItem(Note selectedItem){
        if (Api.updateFileNote(binaryId, selectedItem.getId(), popupReturnedText)) {
            Note note = new Note(popupReturnedText, selectedItem.getId(), selectedItem.getUserName(), selectedItem.getTimeStamp());
            selectedItem.updateItemData(note);
        } else {
            // TODO: handle update failure
        }
    }
}
