package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTagsPane;
import unknowncyberplugin.components.popups.DeleteConfirmationPopup;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;

public class FileDeleteButton extends BaseButton {
    private String binaryId;

    public FileDeleteButton() {
        super("Delete");
    }

    @Override
    protected void runClickedAction(){
        FilePanel fp = References.getFilePanel();
        UnknownCyberFileProvider fileProvider = References.getFileProvider();
        DeleteConfirmationPopup delPopup = new DeleteConfirmationPopup();

        binaryId = fileProvider.getProgram().getExecutableMD5();
        int response = delPopup.displayAndGetResponse();

        if (response == 0){
            processItem(fp.getActiveTabComponent(), fp.getSelectedListItem());
        }
    }

    public void processItem(BaseFileListPane tabPane, Object selectedItem){
        if (tabPane instanceof FileNotesPane){
            boolean successful = Api.deleteFileNote(binaryId, ((NoteModel)selectedItem).getId());
            if (successful) {
                tabPane.getList().removeItem(selectedItem);
            }
        } else if (tabPane instanceof FileTagsPane) {
            boolean successful = Api.removeFileTag(binaryId, ((TagModel)selectedItem).getId());
            if (successful) {
                tabPane.getList().removeItem(selectedItem);
            }
        }
    }
}
