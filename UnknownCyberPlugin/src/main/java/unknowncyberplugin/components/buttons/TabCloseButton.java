package unknowncyberplugin.components.buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;

public class TabCloseButton extends JButton{

    public TabCloseButton(){
        setIcon(UIManager.getIcon("InternalFrame.closeIcon"));
        setPreferredSize(new Dimension(10, 10));
        setToolTipText("close this tab");
        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
        
        // Enable mouse rollover effect and add listener for mouse enter/exits.
        setRolloverEnabled(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent ev){
                Component component = ev.getComponent();
                if (component instanceof AbstractButton){
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent ev){
                Component component = ev.getComponent();
                if (component instanceof AbstractButton){
                    AbstractButton button = (AbstractButton) component;
                    button.setBorderPainted(false);
                }
            }
        });
    }
    
}
