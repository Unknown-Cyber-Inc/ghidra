package unknowncyberplugin.components.popups;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import unknowncyberplugin.models.responsedata.ProcedureModel;

public class CenterComparePopup extends JDialog {

    private JTextArea origCodeArea;
    private JTextArea derivedCodeArea;
    private JScrollPane origScrollPane;
    private JScrollPane derivedScrollPane;
    private JSplitPane splitPane;
    private boolean scrollLocked = false;

    public CenterComparePopup(String origCode, String derivedCode) {
        setTitle("Procedure Comparison");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        origCodeArea = new JTextArea(origCode);
        derivedCodeArea = new JTextArea(derivedCode);

        setupTextArea(origCodeArea);
        setupTextArea(derivedCodeArea);

        origScrollPane = new JScrollPane(origCodeArea);
        derivedScrollPane = new JScrollPane(derivedCodeArea);

        splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT, origScrollPane, derivedScrollPane
        );
        add(splitPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout());
        JButton lockScrollButton = new JButton("Lock Scroll");
        lockScrollButton.addActionListener(e -> toggleScrollLock());
        topPanel.add(lockScrollButton);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> setVisible(false));
        topPanel.add(closeButton);

        add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            splitPane.setDividerLocation(0.5);
        }
    }

    private void setupTextArea(JTextArea textArea) {
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    private void toggleScrollLock() {
        scrollLocked = !scrollLocked;
        if (scrollLocked) {
            origScrollPane.getVerticalScrollBar().addAdjustmentListener(syncScrollListener);
            derivedScrollPane.getVerticalScrollBar().addAdjustmentListener(syncScrollListener);
        } else {
            origScrollPane.getVerticalScrollBar().removeAdjustmentListener(syncScrollListener);
            derivedScrollPane.getVerticalScrollBar().removeAdjustmentListener(syncScrollListener);
        }
    }

    private final transient AdjustmentListener syncScrollListener = new AdjustmentListener() {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar adjusted = (JScrollBar) e.getSource();
            JScrollBar toAdjust = adjusted == origScrollPane.getVerticalScrollBar()
                    ? derivedScrollPane.getVerticalScrollBar()
                    : origScrollPane.getVerticalScrollBar();

            toAdjust.setValue(adjusted.getValue());
        }
    };

    public void display() {
        setVisible(true);
    }
}
