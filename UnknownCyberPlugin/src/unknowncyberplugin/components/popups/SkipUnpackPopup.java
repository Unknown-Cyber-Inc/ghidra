package unknowncyberplugin.components.popups;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class SkipUnpackPopup {

    public boolean checkUnpack(){
        JCheckBox checkBox = new JCheckBox("Skip unpack");

        int result = JOptionPane.showConfirmDialog(
            null, checkBox, "Unpack the upload?", JOptionPane.OK_CANCEL_OPTION
        );

        if(result==JOptionPane.OK_OPTION){
            return checkBox.isSelected();
        }
        return false;
    }
}
