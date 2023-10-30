package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import ghidra.util.Msg;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.CenterDerivedFileTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTabbedPane;
import unknowncyberplugin.components.panes.FileTagsPane;
import unknowncyberplugin.components.popups.DeleteConfirmationPopup;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;

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

        binaryId = fileProvider.getOriginalSha1();
        int response = delPopup.displayAndGetResponse();

        if (response == 0){
            processItem(fp.getActiveTabComponent(), fp.getSelectedListItem());
        }
    }

    public void processItem(BaseFileListPane tabPane, Object selectedItem){
        if (tabPane instanceof FileNotesPane){
            // boolean successful = Api.deleteFileNote(binaryId, ((Note)selectedItem).getId());
            // if (successful) {
            //     tabPane.getList().removeItem(selectedItem);
            // }
        } else if (tabPane instanceof FileTagsPane) {
            // boolean successful = Api.removeFileTag(binaryId, ((Tag)selectedItem).getId());
            // if (successful) {
            //     tabPane.getList().removeItem(selectedItem);
            // }
        }
    }
}
