package unknowncyberplugin.components.popups;

import unknowncyberplugin.References;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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

        // Check to make sure the file upload button doesn't function when the
        // file to upload doesn't exist.
        binaryButton.setEnabled(References.getFileProvider().getOriginalSha1() != null);
    }

    public String displayAndGetResponse() {
        dialog = createDialog("File Upload");
        dialog.setVisible(true);

        String response = (String) getValue();
        dialog.dispose();

        if (response.equals("Binary")){
            SkipUnpackPopup skipUnpackPopup = new SkipUnpackPopup();
            boolean skip = skipUnpackPopup.checkUnpack();
            return response + "," + skip;
        }

        return response;
    }
}
