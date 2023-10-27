package unknowncyberplugin.components.popups;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import ghidra.util.Msg;

public class FileCRUDPopup extends JOptionPane {
    
    private JTextArea textArea;
    private JButton resetButton;
    private JButton saveButton;
    private JButton cancelButton;
    private String currentText;
    
    public FileCRUDPopup() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);

        setMessage(scrollPane);
        setOptionType(DEFAULT_OPTION);
        setMessageType(PLAIN_MESSAGE);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(ev -> {
            textArea.setText(currentText);
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(ev -> {
            Msg.info(this, ("Saving: " + textArea.getText()));
            setValue(saveButton);
        });

        cancelButton = new JButton("Cancel");
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

    public String displayAndGetResponse(String initialText) {
        textArea.setText(initialText);
        currentText = initialText;

        JDialog dialog = createDialog(null, "Enter your text");
        dialog.setContentPane(this);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);

        Object response = getValue();
        dialog.dispose();
        
        if (response == saveButton) {
            return textArea.getText();
        }

        // Return null when either the cancel or close button selected.
        return null;
    }
}
