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
import unknowncyberplugin.models.listitems.NoteItem;
import unknowncyberplugin.models.listitems.TagItem;
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
        UnknownCyberFileProvider fileProvider = Api.getFileProvider();
        FileCRUDPopup popup = new FileCRUDPopup();

        binaryId = fileProvider.getHash("sha1");
        popupReturnedText = null;
        popupReturnedText = popup.displayAndGetResponse("");

        if (popupReturnedText != null){
            processItem(fp.getActiveTabComponent());
        }
    }

    public void processItem(BaseFileListPane tabPane){
        if (tabPane instanceof FileNotesPane){
            processFileNote(tabPane);
        } else if (tabPane instanceof FileTagsPane) {
            processFileTag(tabPane);
        }
    }

    public void processFileNote(BaseFileListPane tabPane){
        NoteItem newNote = createFileNoteItem();
        if (newNote != null){
            tabPane.getList().addItem(newNote);
        }
    }

    public void processFileTag(BaseFileListPane tabPane){
        TagItem newTag = createFileTagItem();
        if (newTag != null){
            tabPane.getList().addItem(newTag);
        }
    }

    public NoteItem createFileNoteItem(){
        // NoteItem newNote;
        // Object response = Api.createFileNote(binaryId, popupReturnedText);
        // if(200 <= response.getStatus() <=300){
        //     // create new NoteItem from response
        //     return newNote;
        // }
        return null;
    }

    public TagItem createFileTagItem(){
        // TagItem newTag;
        // Object response = Api.createFileTag(binaryId, popupReturnedText);
        // if(200 <= response.getStatus() <=300){
        //     // create new TagItem from response
        //     return newTag;
        // }
        return null;
    }
}
