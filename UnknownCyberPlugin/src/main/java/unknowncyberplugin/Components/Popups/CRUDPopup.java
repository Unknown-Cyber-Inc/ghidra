package unknowncyberplugin.Components.Popups;
import javax.swing.*;

import unknowncyberplugin.Components.Buttons.*;

public class CRUDPopup extends JOptionPane {
    
    private JTextArea textArea;
    private ResetButton resetButton;
    private SaveButton saveButton;
    private CancelButton cancelButton;
    
    public CRUDPopup() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        setMessage(scrollPane);
        setOptionType(DEFAULT_OPTION);
        setMessageType(PLAIN_MESSAGE);

        resetButton = new ResetButton(this);
        saveButton = new SaveButton(this);
        cancelButton = new CancelButton(this);

        setOptions(new Object[] { resetButton, saveButton, cancelButton });
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public Object displayAndGetResponse(String initialText) {
        textArea.setText(initialText);

        JDialog dialog = createDialog(null, "Enter your text");
        dialog.setContentPane(this);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);

        Object response = getValue();
        dialog.dispose();
        
        if (response == saveButton) {
            return textArea.getText();
        } else if (response == cancelButton) {
            return null;
        } else if (response == resetButton) {
            return displayAndGetResponse(initialText);
        }

        // Adding this incase there are ways to exit the dialog that are not accounted for.
        return null;
    }
}