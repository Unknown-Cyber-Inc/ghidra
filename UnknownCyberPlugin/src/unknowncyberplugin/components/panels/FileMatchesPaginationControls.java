package unknowncyberplugin.components.panels;

import java.awt.FlowLayout;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileMatchesPaginationControls extends JPanel {
    private int currentPage;
    private int currentPageSize;
    private JButton firstButton;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel pageDisplay;
    private transient Consumer<Integer> onPageChangeCallback;

    public FileMatchesPaginationControls(Consumer<Integer> onPageChange){
        this.currentPage = 1;
        this.onPageChangeCallback = onPageChange;

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        firstButton = new JButton("<<");
        firstButton.setEnabled(false);
        prevButton = new JButton("<");
        prevButton.setEnabled(false);
        pageDisplay = new JLabel(String.valueOf(currentPage));
        nextButton = new JButton(">");
        nextButton.setEnabled(false);

        firstButton.addActionListener(ev -> {
            firstPage();
        });
        prevButton.addActionListener(ev -> {
            prevPage();
        });
        nextButton.addActionListener(ev -> {
            nextPage();
        });


        add(firstButton);
        add(prevButton);
        add(pageDisplay);
        add(nextButton);
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPageSize(int size){
        currentPageSize = size;
    }

    private void firstPage(){
        if (currentPage > 1){
            currentPage = 1;
            onPageChangeCallback.accept(currentPage);
            updateButtons();
            updatePageDisplay();
        }
    }

    private void prevPage(){
        if (currentPage > 1){
            currentPage--;
            onPageChangeCallback.accept(currentPage);
            updateButtons();
            updatePageDisplay();
        }
    }

    private void nextPage(){
        currentPage++;
        onPageChangeCallback.accept(currentPage);
        updateButtons();
        updatePageDisplay();
    }

    private void updateButtons() {
        firstButton.setEnabled(currentPage > 1);
        prevButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPageSize > 1);
    }

    private void updatePageDisplay() {
        pageDisplay.setText(String.valueOf(currentPage));
    }

    public void resetPageCount(){
        currentPage = 1;
        updateButtons();
    }
}
