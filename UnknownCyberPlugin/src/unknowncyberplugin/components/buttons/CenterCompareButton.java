package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.popups.CenterComparePopup;

public class CenterCompareButton extends BaseButton {

    public CenterCompareButton() {
        super("Compare Procedures");
    }

    @Override
    protected void runClickedAction(){
        CenterComparePopup popup = new CenterComparePopup();
        popup.display();
    }

    public void showButton(){
        this.setVisible(true);
    }

    public void hideButton(){
        this.setVisible(false);
    }
}