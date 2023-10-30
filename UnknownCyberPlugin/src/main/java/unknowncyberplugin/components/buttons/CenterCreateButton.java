package unknowncyberplugin.components.buttons;

import javax.swing.tree.DefaultMutableTreeNode;

import ghidra.util.Msg;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.panels.CenterPanel;
import unknowncyberplugin.components.panes.BaseCenterTabPane;
import unknowncyberplugin.components.panes.CenterDerivedFileTabPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.components.panes.CenterProcedureTabPane;
import unknowncyberplugin.components.popups.CenterCRUDPopup;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Tag;
import unknowncyberplugin.models.treenodes.leaves.NoteNode;
import unknowncyberplugin.models.treenodes.leaves.PlaceholderNode;
import unknowncyberplugin.models.treenodes.leaves.TagNode;
import unknowncyberplugin.models.treenodes.roots.DerivedFileRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;

public class CenterCreateButton extends BaseButton {
    private String popupReturnedText;

    public CenterCreateButton() {
        super("Create");
    }
    
    @Override
    protected void runClickedAction(){
        Msg.info(this, "Center create button clicked");
        
        CenterPanel cp = References.getCenterPanel();
        popupReturnedText = null;

        // bring up center popup
        CenterCRUDPopup popup = new CenterCRUDPopup();
        popupReturnedText = popup.displayAndGetResponse("");

        if (popupReturnedText != null){
            // determine api call based on tab pane and node type
            processNode(cp.getActiveTabComponent(), cp.getSelectedTreeNode());
        }
    }

    // Based on pane type, call sub-process method for procedure or file. 
    public void processNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode selectedNode){
        if (tabPane.getRootNode() instanceof ProcedureRootNode){
            processProcedureTreeNode(tabPane, selectedNode);
        } else if (tabPane.getRootNode() instanceof DerivedFileRootNode) {
            processDerivedFileTreeNode(tabPane, selectedNode);
        }
    }

    public void processProcedureTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        ProcedureRootNode rootNode = (ProcedureRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();
        String startEA = rootNode.getStartEA();

        // If the selected node is NotesRootNode or TagsRootNode
        if (node instanceof NotesRootNode){
            createProcedureNoteNode(binaryId, startEA, node);
        } else if (node instanceof TagsRootNode){
            createProcedureTagNode(binaryId, startEA, node);
        // If the selected node is a NoteNode or a TagNode
        } else if (parentNode instanceof NotesRootNode){
            createProcedureNoteNode(binaryId, startEA, parentNode);
        } else if (parentNode instanceof TagsRootNode){
            createProcedureTagNode(binaryId, startEA, parentNode);
        }
    }

    public void createProcedureNoteNode(String binaryId, String startEA, DefaultMutableTreeNode rootNode){
        // Note newNote = Api.createProcedureGenomicsNote(binaryId, startEA, popupReturnedText);
        // if(newNote != null){
        //     rootNode.add(new NoteNode(newNote));
        // }
    }

    public void createProcedureTagNode(String binaryId, String startEA, DefaultMutableTreeNode rootNode){
        // Tag newTag = Api.createProcedureGenomicsTag(binaryId, startEA, popupReturnedText);
        // if(newTag != null){
        //     rootNode.add(new TagNode(newTag));
        // }
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();

        if (node instanceof NotesRootNode){
            createFileNoteNode(binaryId, node);
        } else if (node instanceof TagsRootNode){
            createFileTagNode(binaryId, node);
        } else if (parentNode instanceof NotesRootNode){
            createFileNoteNode(binaryId, parentNode);
        } else if (parentNode instanceof TagsRootNode){
            createFileTagNode(binaryId, parentNode);
        }
    }

    public void createFileNoteNode(String binaryId, DefaultMutableTreeNode rootNode){
        // Note newNote = Api.createFileNote(binaryId, popupReturnedText);
        // if(newNote != null){
        //     rootNode.add(new NoteNode(newNote));
        // }
    }

    public void createFileTagNode(String binaryId, DefaultMutableTreeNode rootNode){
        // Tag newTag = Api.createFileTag(binaryId, popupReturnedText);
        // if(newTag != null){
        //     rootNode.add(new TagNode(newTag));
        // }
    }
}
