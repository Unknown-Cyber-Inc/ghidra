package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.models.responsedata.ProcedureModel;

public class ProcRetrievalButton extends BaseButton {
    
    public ProcRetrievalButton(){
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction(){
        String binaryId = References.getFileProvider().getOriginalSha1();

        ProcedureModel[] procs = Api.getFileGenomics(binaryId);
        String[][] procList = procsToStrings(procs);

        References.getProcTablePane().populate(procList);
    }

    private String[][] procsToStrings(ProcedureModel[] procs){
        if (procs == null) {
            return new String[0][];
        }
        String[][] procList = new String[procs.length][];

        int index = 0;
        for (ProcedureModel proc : procs){
            procList[index] = new String[] {proc.getCount(), proc.getStatus(), proc.getStartEA(), proc.getProcedureName(), proc.getBinaryId()};
        }

        return procList;
    }
}
