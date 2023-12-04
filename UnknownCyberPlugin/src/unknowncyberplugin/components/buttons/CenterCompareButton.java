package unknowncyberplugin.components.buttons;

import java.util.List;
import java.util.stream.Collectors;

import com.unknowncyber.magic.model.BlockSchema;
import unknowncyberplugin.Api;
import unknowncyberplugin.References;
import unknowncyberplugin.components.popups.CenterComparePopup;
import unknowncyberplugin.components.panes.CenterTabbedPane;
import unknowncyberplugin.components.panes.CenterDerivedProcedureTabPane;
import unknowncyberplugin.models.responsedata.ProcedureModel;

public class CenterCompareButton extends BaseButton {
    private CenterTabbedPane ctp;

    public CenterCompareButton() {
        super("Compare Procedures");
        ctp = References.getCenterTabbedPane();
    }

    @Override
    protected void runClickedAction(){
        ctp = References.getCenterTabbedPane();
        CenterDerivedProcedureTabPane tab = (CenterDerivedProcedureTabPane) ctp.getActiveTabComponent();
        ProcedureModel origProc = Api.listFileProcedureGenomics(tab.getOriginBinaryId(), tab.getOriginStartEa());
        ProcedureModel derivedProc = Api.listFileProcedureGenomics(tab.getBinaryId(), tab.getStartEa());

        String origCode = getCodeString(origProc);
        String derivedCode = getCodeString(derivedProc);

        CenterComparePopup popup = new CenterComparePopup(
            origCode,
            tab.getOriginBinaryId(),
            tab.getOriginStartEa(),
            derivedCode,
            tab.getBinaryId(),
            tab.getStartEa()
            );
        popup.display();
    }

    public String getCodeString(ProcedureModel proc) {
        List<BlockSchema> blocks = proc.getBlocks();
        return blocks.stream()
                     .map(block -> String.join("\n", block.getCode()))
                     .collect(Collectors.joining("\n\n"));
    }

    public void showButton(){
        this.setVisible(false);
        // TODO: Remove the line above and uncomment the line below when
        // the comparison feature is desired.
        // this.setVisible(true);
    }

    public void hideButton(){
        this.setVisible(false);
    }
}