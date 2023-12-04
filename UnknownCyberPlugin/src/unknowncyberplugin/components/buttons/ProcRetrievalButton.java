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
        String binaryId = References.getFileProvider().getProgram().getExecutableMD5();

        ProcedureModel[] procs = Api.getFileGenomics(binaryId);

        // Inform user that the query returned 0 hits; the alternative is to do
        // nothing and make it look like the button is broken.
        if (procs.length == 0) {
            References.getFileProvider().announce(
                "No Procedures Found",
                "No procedures were found for this file.",
                false   
            );
        }
        Object[][] procList = procsToStrings(procs);

        References.getProcTablePane().populate(procList);
    }

    private Object[][] procsToStrings(ProcedureModel[] procs) {
        if (procs == null) {
            return new Object[0][];
        }
        Object[][] procList = new Object[procs.length][];

        int index = 0;
        for (ProcedureModel proc : procs) {
            procList[index] = new Object[] {
                proc.getStartEA(),
                proc.getProcedureName(),
                proc.getHardHash(),
                Integer.parseInt(proc.getCount()),
                Integer.parseInt(proc.getBlockCount()),
                Integer.parseInt(proc.getCodeCount()),
                proc.getStatus(),
                Integer.parseInt(proc.getNotes()),
                Integer.parseInt(proc.getTags())
            };
            index++;
        }

        return procList;
    }
}
