package unknowncyberplugin.Components.Popups;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileCRUDPopup extends JOptionPane {
    
    private JTextArea textArea;
    private JButton resetButton;
    private JButton saveButton;
    private JButton cancelButton;
    
    public FileCRUDPopup() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        setMessage(scrollPane);
        setOptionType(DEFAULT_OPTION);
        setMessageType(PLAIN_MESSAGE);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                setValue(resetButton);
            }
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving: " + textArea.getText());
                setValue(saveButton);
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValue(cancelButton);
            }
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
