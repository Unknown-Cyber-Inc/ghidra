package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
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
            References.getFileProvider().announce(
                    "Failed to Update",
                    "An error occurred while updating the file note, see the User Log for more information.",
                    true
                );
        }
    }
}
