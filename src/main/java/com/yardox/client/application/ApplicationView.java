package com.yardox.client.application;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.yardox.client.application.widget.UserDropDownToggle;

import javax.inject.Inject;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    SimplePanel main;

    @UiField
    UserDropDownToggle userDdAnchor;

    @Inject
    ApplicationView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
    }

    @Override
    public void displayUser(String userInfo, String userPictureURL) {
        userDdAnchor.displayUserPicture(userPictureURL);
        userDdAnchor.displayUserName(userInfo);
    }

    @UiHandler("signOut")
    public void onSignOutClick(ClickEvent event) {
        getUiHandlers().onSignOutClick();
    }
}
