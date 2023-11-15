package unknowncyberplugin.components.panes;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.models.responsedata.FileModel;
import unknowncyberplugin.models.responsedata.NoteModel;
import unknowncyberplugin.models.responsedata.ProcedureModel;
import unknowncyberplugin.models.responsedata.TagModel;
import unknowncyberplugin.models.treenodes.leaves.SimilarProcedureNode;
import unknowncyberplugin.models.treenodes.roots.FilesRootNode;
import unknowncyberplugin.models.treenodes.roots.NotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcedureRootNode;
import unknowncyberplugin.models.treenodes.roots.SimilaritiesRootNode;
import unknowncyberplugin.models.treenodes.roots.TagsRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcGroupNotesRootNode;
import unknowncyberplugin.models.treenodes.roots.ProcGroupTagsRootNode;

public class CenterProcedureTabPane extends BaseCenterTabPane{
    private String startEa;
    private String binaryId;
    private String hardHash;

    public CenterProcedureTabPane(String startEa, String hardHash){
        super(startEa, References.getFileProvider().getOriginalSha1(), "procedure");
        this.startEa = startEa;
        this.hardHash = hardHash;
        binaryId = References.getFileProvider().getOriginalSha1();

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                if (ev.getClickCount() == 2) {
                    int x = ev.getX();
                    int y = ev.getY();
                    int row = tree.getRowForLocation(x, y);
                    TreePath path = tree.getPathForRow(row);

                    if (path != null){
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                        handleDoubleClick(selectedNode);
                    }
                }
            }
        });
    }

    public String getHardHash(){
        return hardHash;
    }

    @Override
    protected void callExpandAction(Object subRootNode){
        if (subRootNode instanceof NotesRootNode){
            NoteModel[] notes = Api.listProcedureGenomicsNotes(binaryId, startEa);
            ((ProcedureRootNode)getRootNode()).populateNotes(notes);
        } else if (subRootNode instanceof TagsRootNode){
            TagModel[] tags = Api.listProcedureGenomicsTags(binaryId, startEa);
            ((ProcedureRootNode)getRootNode()).populateTags(tags);
        } else if (subRootNode instanceof ProcGroupNotesRootNode){
            NoteModel[] notes = Api.listProcedureGroupNotes(hardHash);
            ((ProcedureRootNode)getRootNode()).populateProcGroupNotes(notes);
        } else if (subRootNode instanceof ProcGroupTagsRootNode){
            TagModel[] tags = Api.listProcedureGroupTags(hardHash);
            ((ProcedureRootNode)getRootNode()).populateProcGroupTags(tags);
        } else if (subRootNode instanceof SimilaritiesRootNode){
            ProcedureModel[] response = Api.listProcedureSimilarities(binaryId, startEa);
            parseSimilarProcedures(response);
        }
    }

    public void parseSimilarProcedures(ProcedureModel[] procs){
        String currentBinaryId = "";
        FilesRootNode currentFileRootNode = null;
        SimilaritiesRootNode simRootNode = ((ProcedureRootNode)getRootNode()).getSimilaritiesRootNode();

        for (ProcedureModel proc : procs){
            if (currentBinaryId.equals(proc.getBinaryId())){
                currentFileRootNode.add(new SimilarProcedureNode(proc));
            } else {
                currentBinaryId = proc.getBinaryId();
                FileModel newFile = new FileModel(proc.getBinaryId(), null, proc.getBinaryId());
                currentFileRootNode = new FilesRootNode(newFile, proc.getBinaryId());
                currentFileRootNode.setBinaryId(proc.getBinaryId());

                currentFileRootNode.add(new SimilarProcedureNode(proc));
                simRootNode.add(currentFileRootNode);
            }
        }
    }

    private void handleDoubleClick(DefaultMutableTreeNode selectedNode){
        CenterTabbedPane ctp = References.getCenterTabbedPane();
        String nodeName;
        if (selectedNode instanceof FilesRootNode){
            nodeName = ((FilesRootNode)selectedNode).getNodeDisplayName();
            ctp.addClosableTab(
                nodeName,
                new CenterDerivedFileTabPane(selectedNode.toString())
            );
        } else if (selectedNode instanceof SimilarProcedureNode){
            nodeName = ((SimilarProcedureNode)selectedNode).getNodeDisplayName();
            FilesRootNode parentNode = (FilesRootNode) selectedNode.getParent();
            ctp.addClosableTab(
                nodeName,
                new CenterDerivedProcedureTabPane(
                    ((SimilarProcedureNode)selectedNode).getNodeDisplayName(),
                    parentNode.toString()
                )
            );
        }
    }
    
}
