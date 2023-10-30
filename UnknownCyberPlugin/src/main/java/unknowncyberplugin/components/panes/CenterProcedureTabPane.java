package unknowncyberplugin.components.panes;

import javax.swing.tree.MutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.models.responsedata.File;
import unknowncyberplugin.models.responsedata.Note;
import unknowncyberplugin.models.responsedata.Procedure;
import unknowncyberplugin.models.responsedata.Tag;
import unknowncyberplugin.models.treenodes.roots.FilesRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;

public class CenterProcedureTabPane extends BaseCenterTabPane{
    private String startEa;
    private String binaryId;

    public CenterProcedureTabPane(String startEa){
        super(startEa, References.getFileProvider().getOriginalSha1(), "procedure");
        this.startEa = startEa;
        binaryId = ((ProcedureRootNode)getRootNode()).getBinaryId();
    }

    @Override
    protected void callExpandAction(Object subRootNode){
        if (subRootNode instanceof NotesRootNode){
            // Note[] notes = Api.listProcedureGenomicsNotes(binaryId, startEa);
            // ((ProcedureRootNode)getRootNode()).populateNotes(notes);
        } else if (subRootNode instanceof TagsRootNode){
            // Tag[] tags = Api.listProcedureGenomicsTags(binaryId, startEa);
            // ((ProcedureRootNode)getRootNode()).populateTags(tags);
        } else if (subRootNode instanceof SimilaritiesRootNode){
            // Procedure[] response = Api.listProcedureSimilarities(binaryId, startEa);
            // parseSimilarProcedures(response);
        }
    }

    public void parseSimilarProcedures(Procedure[] procs){
        String currentBinaryId = null;
        FilesRootNode currentFileRootNode = null;
        SimilaritiesRootNode simRootNode = ((ProcedureRootNode)getRootNode()).getSimilaritiesRootNode();

        for (Procedure proc : procs){
            if (currentBinaryId.equals(proc.getBinaryId())){
                currentFileRootNode.add((MutableTreeNode)proc);
            } else {
                File newFile = new File(proc.getBinaryId(), null, proc.getBinaryId());
                currentFileRootNode = new FilesRootNode(newFile);

                currentFileRootNode.add((MutableTreeNode)proc);
                simRootNode.add(currentFileRootNode);
            }
        }
    }
    
}
