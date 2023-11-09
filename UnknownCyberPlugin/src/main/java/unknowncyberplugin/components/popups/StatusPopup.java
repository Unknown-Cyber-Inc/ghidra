package unknowncyberplugin.components.popups;

import javax.swing.JOptionPane;

import unknowncyberplugin.models.responsedata.FileStatusModel;

public class StatusPopup extends JOptionPane {

    public StatusPopup(){
        super("Upload Status", INFORMATION_MESSAGE);
    }

    public void display(FileStatusModel status){
        setMessage(status.toString());
    }
}
