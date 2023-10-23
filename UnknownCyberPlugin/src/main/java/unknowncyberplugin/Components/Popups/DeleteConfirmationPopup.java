package unknowncyberplugin.Components.Popups;
import javax.swing.*;

public class DeleteConfirmationPopup extends JOptionPane {

    public DeleteConfirmationPopup() {
        super(
            "This action cannot be undone. Are you sure you want to delete this item?",
            WARNING_MESSAGE,
            YES_NO_OPTION
        );
    }

    public int displayAndGetResponse() {
        return showConfirmDialog(
            null,
            getMessage(),
            "Confirm Delete",
            getOptionType(),
            getMessageType()
        );
    }
}
