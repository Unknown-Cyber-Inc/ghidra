package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.collections.CenterTree;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
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
import unknowncyberplugin.models.treenodes.roots.ProcGroupNotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcGroupTagsRootNode;

public class CenterCreateButton extends BaseButton {
    private BaseCenterTabPane tabPane;
    private CenterTree tree;
    private DefaultMutableTreeNode selectedNode;
    private NotesRootNode notesRoot;
    private TagsRootNode tagsRoot;
    private SimilaritiesRootNode simRoot;
    private ProcGroupNotesRootNode procGroupNotesRoot;
    private ProcGroupTagsRootNode procGroupTagsRoot;
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
        String hardHash = null;

        // Leaving the conditional below in place for when we add proc group notes/tags
        // to derived procedure tabs.
        // 
        // if (tabPane instanceof CenterDerivedProcedureTabPane) {
        //     hardHash = ((CenterDerivedProcedureTabPane)tabPane).getHardHash();
        // }

        if (tabPane instanceof CenterProcedureTabPane){
            hardHash = ((CenterProcedureTabPane)tabPane).getHardHash();
            simRoot = procRoot.getSimilaritiesRootNode();
            procGroupNotesRoot = procRoot.getProcGroupNoteRoot();
            procGroupTagsRoot = procRoot.getProcGroupTagsRootNode();
        }

        // If the selected node is NotesRootNode, TagsRootNode, ProcGroupNotesRootNode, or ProcGroupTagsRootNode
        if (selectedNode instanceof NotesRootNode){
            createProcedureNoteNode(startEA, selectedNode);
        } else if (selectedNode instanceof TagsRootNode){
            createProcedureTagNode(startEA, selectedNode);
        } else if (selectedNode instanceof ProcGroupNotesRootNode){
            createProcedureGroupNoteNode(hardHash, selectedNode);
        } else if (selectedNode instanceof ProcGroupTagsRootNode){
            createProcedureGroupTagNode(hardHash, selectedNode);
        }else if (parentNode instanceof NotesRootNode){ // If the selected node is a Note/TagNode of a procedure
            createProcedureNoteNode(startEA, parentNode);
        } else if (parentNode instanceof TagsRootNode){
            createProcedureTagNode(startEA, parentNode);
        } else if (parentNode instanceof ProcGroupNotesRootNode){ // If the selected node is a Note/TagNode of a proc group
            createProcedureGroupNoteNode(hardHash, parentNode);
        } else if (parentNode instanceof ProcGroupTagsRootNode){
            createProcedureGroupTagNode(hardHash, parentNode);
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

    public void createProcedureGroupNoteNode(String hardHash, DefaultMutableTreeNode rootNode){
        NoteModel newNote = Api.createProcedureGroupNote(hardHash, popupReturnedText);
        if(newNote != null){
            tree.addNode(rootNode, new NoteNode(newNote));
        }
    }

    public void createProcedureGroupTagNode(String hardHash, DefaultMutableTreeNode rootNode){
        TagModel newTag = Api.createProcedureGroupTag(hardHash, popupReturnedText);
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
        if (procGroupNotesRoot != null){
            procGroupNotesRoot.clearNode();
        }
        if (procGroupTagsRoot != null){
            procGroupTagsRoot.clearNode();
        }
        simRoot = null;
        procGroupNotesRoot = null;
        procGroupTagsRoot = null;
    }
}
