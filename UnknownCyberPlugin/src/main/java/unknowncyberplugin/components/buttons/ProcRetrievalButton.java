package unknowncyberplugin.components.buttons;

import ghidra.util.Msg;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.models.responsedata.Procedure;

public class ProcRetrievalButton extends BaseButton {
    
    public ProcRetrievalButton(){
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction(){
        String binaryId = Api.getFileProvider().getOriginalSha1();

        // Procedure[] procs = Api.getFileGenomics(binaryId);
        // String[][] procList = procsToStrings(procs);

        // References.getProcTablePane().populate(procList);
    }

    private String[][] procsToStrings(Procedure[] procs){
        if (procs == null) {
            return new String[0][];
        }
        String[][] procList = new String[procs.length][];

        int index = 0;
        for (Procedure proc : procs){
            procList[index] = new String[] {proc.getCount(), proc.getStatus(), proc.getStartEA(), proc.getProcedureName(), proc.getBinaryId()};
        }

        return procList;
    }
}
