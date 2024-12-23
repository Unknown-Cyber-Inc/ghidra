package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.popups.FileCRUDPopup;
import unknowncyberplugin.models.responsedata.NoteModel;

import ghidra.util.Msg;

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

        binaryId = fileProvider.getProgram().getExecutableMD5();
        popupReturnedText = null;
        popupReturnedText = popup.displayAndGetResponse(currentDisplayName);

        if (popupReturnedText != null){
            processItem((NoteModel)fp.getSelectedListItem());
        }
    }

    public void processItem(NoteModel selectedItem){
        if (Api.updateFileNote(binaryId, selectedItem.getId(), popupReturnedText)) {
            NoteModel note = new NoteModel(popupReturnedText, selectedItem.getId(), selectedItem.getUserName(), selectedItem.getTimeStamp());
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
