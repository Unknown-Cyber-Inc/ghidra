package unknowncyberplugin.components.panes;

import javax.swing.tree.MutableTreeNode;

import unknowncyberplugin.Api;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.treenodes.roots.FilesRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;

public class CenterDerivedProcedureTabPane extends BaseCenterTabPane{
    private String startEa;
    private String binaryId;
    private String originStartEa;
    private String originBinaryId;

    public CenterDerivedProcedureTabPane(String startEa, String binaryId, String originStartEa, String originBinaryId){
        super(startEa, binaryId, "derived procedure");
        this.startEa = startEa;
        this.binaryId = binaryId;
        this.originStartEa = originStartEa;
        this.originBinaryId = originBinaryId;
    }

    @Override
    protected void callExpandAction(Object subRootNode){
        
        if (subRootNode instanceof NotesRootNode){
            NoteModel[] notes = Api.listProcedureGenomicsNotes(binaryId, startEa);
            ((ProcedureRootNode)getRootNode()).populateNotes(notes);
        } else if (subRootNode instanceof TagsRootNode){
            TagModel[] tags = Api.listProcedureGenomicsTags(binaryId, startEa);
            ((ProcedureRootNode)getRootNode()).populateTags(tags);
        }
    }

    public String getStartEa() {
        return startEa;
    }

    public String getBinaryId() {
        return binaryId;
    }

    public String getOriginStartEa() {
        return originStartEa;
    }

    public String getOriginBinaryId() {
        return originBinaryId;
    }
}
