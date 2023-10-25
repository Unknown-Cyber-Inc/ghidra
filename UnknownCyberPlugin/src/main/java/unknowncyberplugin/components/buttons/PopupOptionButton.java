package unknowncyberplugin.components.buttons;

import unknowncyberplugin.components.popups.CRUDPopup;

abstract class PopupOptionButton extends BaseButton {

    protected CRUDPopup popup;

    protected PopupOptionButton(CRUDPopup popup, String text) {
        super(text);
        this.popup = popup;
    }
}
