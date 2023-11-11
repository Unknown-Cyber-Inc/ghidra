package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.popups.CenterCRUDPopup;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;

public class CenterCreateButton extends BaseButton {
    private BaseCenterTabPane tabPane;
    private CenterTree tree;
    private DefaultMutableTreeNode selectedNode;
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode simRoot;
    private String binaryId;
    private String popupReturnedText;

    public CenterCreateButton() {
        super("Create");
    }
    
    @Override
    protected void runClickedAction(){
        CenterPanel cp = References.getCenterPanel();
        tabPane = cp.getActiveTabComponent();
        selectedNode = cp.getSelectedTreeNode();
        tree = tabPane.getTree();
        popupReturnedText = null;

        // bring up center popup
        CenterCRUDPopup popup = new CenterCRUDPopup();
        popupReturnedText = popup.displayAndGetResponse("");

        if (popupReturnedText != null){
            // determine api call based on tab pane and node type
            processNode();
        }
    }

    // Based on pane type, call sub-process method for procedure or file. 
    public void processNode(){
        if (tabPane.getRootNode() instanceof ProcedureRootNode){
            ProcedureRootNode procRoot = (ProcedureRootNode) tabPane.getRootNode();
            binaryId = procRoot.getBinaryId();
            notesRoot = procRoot.getNoteRoot();
            tagsRoot = procRoot.getTagsRootNode();
            processProcedureTreeNode();
        } else if (tabPane.getRootNode() instanceof DerivedFileRootNode) {
            DerivedFileRootNode filesRoot = (DerivedFileRootNode) tabPane.getRootNode();
            binaryId = filesRoot.getBinaryId();
            notesRoot = filesRoot.getNoteRoot();
            tagsRoot = filesRoot.getTagsRootNode();
            processDerivedFileTreeNode();
        }
        // Clear node to prevent duplicates upon reopening the tree.
        clearSubRootNodes();
    }

    public void processProcedureTreeNode(){
        ProcedureRootNode procRoot = (ProcedureRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
        String startEA = procRoot.getStartEA();

        // If the selected node is NotesRootNode or TagsRootNode
        if (selectedNode instanceof NotesRootNode){
            createProcedureNoteNode(startEA, selectedNode);
        } else if (selectedNode instanceof TagsRootNode){
            createProcedureTagNode(startEA, selectedNode);
        // If the selected node is a NoteNode or a TagNode
        } else if (parentNode instanceof NotesRootNode){
            createProcedureNoteNode(startEA, parentNode);
        } else if (parentNode instanceof TagsRootNode){
            createProcedureTagNode(startEA, parentNode);
        }
    }

    public void processDerivedFileTreeNode(){
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();

        if (selectedNode instanceof NotesRootNode){
            createFileNoteNode(selectedNode);
        } else if (selectedNode instanceof TagsRootNode){
            createFileTagNode(selectedNode);
        } else if (parentNode instanceof NotesRootNode){
            createFileNoteNode(parentNode);
        } else if (parentNode instanceof TagsRootNode){
            createFileTagNode(parentNode);
        }
    }

    public void createProcedureNoteNode(String startEA, DefaultMutableTreeNode rootNode){
        NoteModel newNote = Api.createProcedureGenomicsNote(binaryId, startEA, popupReturnedText);
        if(newNote != null){
            // Closes the tree's every subnode.
            tree.addNode(rootNode, new NoteNode(newNote));
        }
    }

    public void createProcedureTagNode(String startEA, DefaultMutableTreeNode rootNode){
        TagModel newTag = Api.createProcedureGenomicsTag(binaryId, startEA, popupReturnedText);
        if(newTag != null){
            tree.addNode(rootNode, new TagNode(newTag));
        }
    }

    public void createFileNoteNode(DefaultMutableTreeNode rootNode){
        NoteModel newNote = Api.createFileNote(binaryId, popupReturnedText);
        if(newNote != null){
            tree.addNode(rootNode, new NoteNode(newNote));
        }
    }

    public void createFileTagNode(DefaultMutableTreeNode rootNode){
        TagModel newTag = Api.createFileTag(binaryId, popupReturnedText);
        if(newTag != null){
            tree.addNode(rootNode, new TagNode(newTag));
        }
    }

    public void clearSubRootNodes(){
        notesRoot.clearNode();
        tagsRoot.clearNode();
        if (simRoot != null){
            simRoot.clearNode();
        }
        simRoot = null;
    }
}
