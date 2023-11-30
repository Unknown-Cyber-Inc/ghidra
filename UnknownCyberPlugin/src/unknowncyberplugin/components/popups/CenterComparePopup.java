package unknowncyberplugin.components.popups;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class CenterComparePopup extends JOptionPane {

    public CenterComparePopup() {
        super("COMPARE BUTTON CLICKED", INFORMATION_MESSAGE);
    }

    public void display(){
        JTextArea textArea = new JTextArea("Some procedure information.");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane jsp = new JScrollPane(textArea);

        JDialog dialog = new JDialog();
        dialog.setTitle("Compare Procedures");
        dialog.setContentPane(jsp);
        dialog.setSize(new Dimension(600, 300));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
