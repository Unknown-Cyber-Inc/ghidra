package unknowncyberplugin.components.popups;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ghidra.util.Msg;

public class FileUploadPopup extends JOptionPane {
    private JDialog dialog;
    
    public FileUploadPopup(){
        super("Upload with which method?", QUESTION_MESSAGE);
        JButton binaryButton = new JButton("Binary");
        JButton disassemblyButton = new JButton("Disassembly");
        setOptions(new Object[] {binaryButton, disassemblyButton});

        ActionListener buttonListener = ev -> {
            setValue(((JButton) ev.getSource()).getText());
            if (dialog != null) {
                dialog.setVisible(false);
            }
        };

        binaryButton.addActionListener(buttonListener);
        disassemblyButton.addActionListener(buttonListener);
    }

    public String displayAndGetResponse() {
        dialog = createDialog("File Upload");
        dialog.setVisible(true);

        Object response = getValue();
        dialog.dispose();

        Msg.info(this, ("Response is: " + response));

        return (String) response;
    }
}
