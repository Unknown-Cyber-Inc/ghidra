package unknowncyberplugin.components.buttons;
import javax.swing.*;

abstract class BaseButton extends JButton {

    protected BaseButton(String text) {
        super(text);

        setFocusable(false);
        addActionListener(ev -> {
            runClickedAction();
        });
    }

    protected abstract void runClickedAction();
}
