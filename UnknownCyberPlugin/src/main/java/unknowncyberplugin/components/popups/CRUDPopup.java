package unknowncyberplugin.components.popups;

import javax.swing.*;
import ghidra.util.Msg;

import unknowncyberplugin.components.buttons.*;

public class CRUDPopup extends JOptionPane {
    
    private JTextArea textArea;
    private ResetButton resetButton;
    private SaveButton saveButton;
    private CancelButton cancelButton;
    
    public CRUDPopup() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);

        setMessage(scrollPane);
        setOptionType(DEFAULT_OPTION);
        setMessageType(PLAIN_MESSAGE);

        resetButton = new ResetButton(this);
        resetButton.addActionListener(ev -> {
            textArea.setText("");
            setValue(resetButton);
        });

        saveButton = new SaveButton(this);
        saveButton.addActionListener(ev -> {
            Msg.info(this, ("Saving: " + textArea.getText()));
            setValue(saveButton);
        });

        cancelButton = new CancelButton(this);
        cancelButton.addActionListener(ev -> {
            setValue(cancelButton);
        });

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