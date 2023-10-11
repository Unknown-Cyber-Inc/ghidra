package unknowncyberplugin.Components.Popups;

import javax.swing.*;
import java.awt.event.*;

public class FileUploadPopup extends JOptionPane {

    private JButton binaryButton = new JButton("Binary");
    private JButton disassemblyButton = new JButton("Disassembly");
    private JButton idbButton = new JButton("IDB");
    private JDialog dialog;
    
    public FileUploadPopup(){
        super("Upload with which method?", QUESTION_MESSAGE);
        setOptions(new Object[] {binaryButton, disassemblyButton, idbButton});

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValue(((JButton) e.getSource()).getText());
                if (dialog != null) {
                    dialog.setVisible(false);
                }
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

        System.out.println("Response is: " + response);

        return (String) response;
    }
}
