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
        CenterPanel cp = References.getCenterPanel();
        Msg.info(this, "Center create button clicked");
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
        String address = "PLACEHOLDER ADDRESS";

        // If the selected node is NotesRootNode or TagsRootNode
        if (node instanceof NotesRootNode){
            NoteNode newNode = createProcedureNoteNode(binaryId, address);
            if (newNode != null){
                node.add(newNode);
            }
        } else if (node instanceof TagsRootNode){
            TagNode newNode = createProcedureTagNode(binaryId, address);
            if (newNode != null){
                node.add(newNode);
            }
        // If the selected node is a NoteNode or a TagNode
        } else if (parentNode instanceof NotesRootNode){
            NoteNode newNode = createProcedureNoteNode(binaryId, address);
            if (newNode != null){
                parentNode.add(newNode);
            }
        } else if (parentNode instanceof TagsRootNode){
            TagNode newNode = createProcedureTagNode(binaryId, address);
            if (newNode != null){
                node.add(newNode);
            }
        }
    }

    public NoteNode createProcedureNoteNode(String binaryId, String address){
        NoteNode newNote;
        Object response = Api.createProcedureGenomicsNote(binaryId, address, popupReturnedText;
        if(200 <= response.getStatus() <=300){
            // create new NoteNode from response
            return newNote;
        }

        return null;
    }

    public TagNode createProcedureTagNode(String binaryId, String address){
        TagNode newTag;
        Object response = Api.createProcedureGenomicsTag(binaryId, address, popupReturnedText);
        if(200 <= response.getStatus() <=300){
            // create new TagNode from response
            return newTag;
        }
        return null;
    }

    public void processDerivedFileTreeNode(BaseCenterTabPane tabPane, DefaultMutableTreeNode node){
        DerivedFileRootNode rootNode = (DerivedFileRootNode) tabPane.getRootNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        String binaryId = rootNode.getBinaryId();

        if (node instanceof NotesRootNode){
            node.add(createFileNoteNode(binaryId));
        } else if (node instanceof TagsRootNode){
            node.add(createFileTagNode(binaryId));
        } else if (parentNode instanceof NotesRootNode){
            parentNode.add(createFileNoteNode(binaryId));
        } else if (parentNode instanceof TagsRootNode){
            parentNode.add(createFileTagNode(binaryId));
        }
    }

    public NoteNode createFileNoteNode(String binaryId){
        NoteNode newNote;
        Object response = Api.createFileNote(binaryId, popupReturnedText);
        if(200 <= response.getStatus() <=300){
            // create new NoteNode from response
            return newNote;
        }
        return null;
    }

    public TagNode createFileTagNode(String binaryId){
        TagNode newTag;
        Object response = Api.createFileTag(binaryId, popupReturnedText);
        if(200 <= response.getStatus() <=300){
            // create new TagNode from response
            return newTag;
        }
        return null;
    }
}
