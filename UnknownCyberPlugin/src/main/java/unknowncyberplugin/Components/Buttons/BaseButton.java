package unknowncyberplugin.Components.Buttons;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class BaseButton extends JButton {

    protected BaseButton(String text) {
        super(text);

        setFocusable(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                runClickedAction();
            }
        });
    }

    protected abstract void runClickedAction();
}
