package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import ghidra.util.Msg;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.UnknownCyberFileProvider;
import unknowncyberplugin.components.panels.FilePanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.BaseFileListPane;
import unknowncyberplugin.components.panes.FileNotesPane;
import unknowncyberplugin.components.panes.FileTagsPane;
import unknowncyberplugin.components.popups.FileCRUDPopup;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;

public class FileCreateButton extends BaseButton {
    private String popupReturnedText;
    private String binaryId;

    public FileCreateButton() {
        super("Create");
    }

    @Override
    protected void runClickedAction(){
        FilePanel fp = References.getFilePanel();
        UnknownCyberFileProvider fileProvider = References.getFileProvider();
        FileCRUDPopup popup = new FileCRUDPopup();

        binaryId = fileProvider.getOriginalSha1();
        popupReturnedText = null;
        popupReturnedText = popup.displayAndGetResponse("");

        if (popupReturnedText != null){
            processItem(fp.getActiveTabComponent());
        }
    }

    public void processItem(BaseFileListPane tabPane){
        if (tabPane instanceof FileNotesPane){
            processNewFileNote();
        } else if (tabPane instanceof FileTagsPane) {
            processNewFileTag();
        }
    }

    public void processNewFileNote(){
        Note newNote = Api.createFileNote(binaryId, popupReturnedText);
        if(newNote != null){
            References.getFileNotesPane().addItem(newNote);
        }
    }

    public void processNewFileTag(){
        Tag newTag = Api.createFileTag(binaryId, popupReturnedText);
        if(newTag != null){
            References.getFileTagsPane().addItem(newTag);
        }
    }
}
