package unknowncyberplugin.components.popups;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

import unknowncyberplugin.models.responsedata.FileStatusModel;

public class StatusPopup extends JOptionPane {

    public StatusPopup(){
        super("Upload Status", INFORMATION_MESSAGE);
    }

    public void display(FileStatusModel status){
        JTextArea textArea = new JTextArea(status.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane jsp = new JScrollPane(textArea);

        JDialog dialog = new JDialog();
        dialog.setTitle("Upload Status");
        dialog.setContentPane(jsp);
        dialog.setSize(new Dimension(600, 300));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
