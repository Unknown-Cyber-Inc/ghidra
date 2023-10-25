package unknowncyberplugin.components.popups;

import javax.swing.*;
import java.awt.event.*;
import ghidra.util.Msg;

public class FileUploadPopup extends JOptionPane {
    private JDialog dialog;
    
    public FileUploadPopup(){
        super("Upload with which method?", QUESTION_MESSAGE);
        JButton binaryButton = new JButton("Binary");
        JButton disassemblyButton = new JButton("Disassembly");
        JButton idbButton = new JButton("IDB");
        setOptions(new Object[] {binaryButton, disassemblyButton, idbButton});

        ActionListener buttonListener = ev -> {
            setValue(((JButton) ev.getSource()).getText());
            if (dialog != null) {
                dialog.setVisible(false);
            }
        };

        binaryButton.addActionListener(buttonListener);
        disassemblyButton.addActionListener(buttonListener);
        idbButton.addActionListener(buttonListener);
    }

    public String displayAndGetResponse() {
        dialog = createDialog("File Upload");
        dialog.setVisible(true);

        Object response = getValue();
        dialog.dispose();

        Msg.info("Response is: " + response);

        return (String) response;
    }
}
