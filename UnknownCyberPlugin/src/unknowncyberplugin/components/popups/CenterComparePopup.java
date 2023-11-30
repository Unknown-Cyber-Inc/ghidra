package unknowncyberplugin.components.popups;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import unknowncyberplugin.models.responsedata.ProcedureModel;

public class CenterComparePopup extends JDialog {

    private JTextArea origCodeArea;
    private JTextArea derivedCodeArea;
    private JScrollPane origScrollPane;
    private JScrollPane derivedScrollPane;
    private JSplitPane splitPane;
    private boolean scrollLocked = false;

    public CenterComparePopup(
        String origCode,
        String origBinaryId,
        String origStartEa,
        String derivedCode,
        String derivedBinaryId,
        String derivedStartEa
        ) {
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

        JPanel origPanel = new JPanel(new BorderLayout());
        JPanel derivedPanel = new JPanel(new BorderLayout());

        origPanel.add(createInfoPanel("Original", origBinaryId, origStartEa), BorderLayout.NORTH);
        origPanel.add(origScrollPane, BorderLayout.CENTER);

        derivedPanel.add(createInfoPanel("Similar", derivedBinaryId, derivedStartEa), BorderLayout.NORTH);
        derivedPanel.add(derivedScrollPane, BorderLayout.CENTER);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, origPanel, derivedPanel);
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

    private JPanel createInfoPanel(String title, String binaryId, String startEa) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        JLabel fileHashLabel = new JLabel("File Hash: " + binaryId);
        JLabel addressLabel = new JLabel("Address: " + startEa);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(titleLabel);
        infoPanel.add(fileHashLabel);
        infoPanel.add(addressLabel);

        panel.add(infoPanel, BorderLayout.NORTH);
        return panel;
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
