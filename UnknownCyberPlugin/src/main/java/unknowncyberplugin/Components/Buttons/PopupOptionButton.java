package unknowncyberplugin.Components.Buttons;

import unknowncyberplugin.Components.Popups.CRUDPopup;

abstract class PopupOptionButton extends BaseButton {

    protected CRUDPopup popup;

    protected PopupOptionButton(CRUDPopup popup, String text) {
        super(text);
        this.popup = popup;
    }
}
