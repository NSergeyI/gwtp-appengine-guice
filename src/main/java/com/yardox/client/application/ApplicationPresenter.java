package com.yardox.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import com.yardox.client.dispatch.AsyncCallbackImpl;
import com.yardox.client.event.LoginResetEvent;
import com.yardox.client.security.IsLoggedInGatekeeper;
import com.yardox.shared.action.SignOutAction;
import com.yardox.shared.action.SignOutResult;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements ApplicationUiHandlers {
    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {
        void displayUser(String userInfo, String userPictureURL);
    }

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {

    }

    public static final NestedSlot SLOT_MAIN = new NestedSlot();
    private final IsLoggedInGatekeeper gatekeeper;
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    ApplicationPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                         final IsLoggedInGatekeeper gatekeeper, final DispatchAsync dispatcher, final PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);

        this.gatekeeper = gatekeeper;
        this.dispatcher = dispatcher;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void onSignOutClick() {
        dispatcher.execute(new SignOutAction(), new AsyncCallbackImpl<SignOutResult>() {
            @Override
            public void onSuccess(SignOutResult result) {
                getEventBus().fireEvent(new LoginResetEvent());
                placeManager.revealCurrentPlace();
            }
        });
    }
}
