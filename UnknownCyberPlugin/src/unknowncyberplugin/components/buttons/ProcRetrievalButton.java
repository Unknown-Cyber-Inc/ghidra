package unknowncyberplugin.components.buttons;

import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.models.responsedata.ProcedureModel;

public class ProcRetrievalButton extends BaseButton {

    public ProcRetrievalButton() {
        super("Get Procedures");
    }

    @Override
    protected void runClickedAction() {
        String binaryId = References.getFileProvider().getOriginalSha1();

        ProcedureModel[] procs = Api.getFileGenomics(binaryId);

        if(procs != null){
            // Inform user that the query returned 0 hits; the alternative is to do
            // nothing and make it look like the button is broken.
            if (procs.length == 0) {
                References.getFileProvider().announce(
                    "No Procedures Found",
                    "No procedures were found for this file.",
                    false   
                );
            }
            String[][] procList = procsToStrings(procs);

            References.getProcTablePane().populate(procList);
        }
    }

    private String[][] procsToStrings(ProcedureModel[] procs) {
        if (procs == null) {
            return new String[0][];
        }
        String[][] procList = new String[procs.length][];

        int index = 0;
        for (ProcedureModel proc : procs) {
            procList[index] = new String[] { proc.getStartEA(), proc.getProcedureName(), proc.getHardHash(), proc.getCount(),
                     proc.getBlockCount(), proc.getCodeCount(), proc.getStatus(), proc.getNotes(), proc.getTags()};
            index++;
        }

        return procList;
    }
}
