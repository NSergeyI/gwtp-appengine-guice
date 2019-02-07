package com.yardox.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.yardox.client.dispatch.AsyncCallbackImpl;
import com.yardox.client.event.LoginResetEvent;
import com.yardox.client.place.NameTokens;
import com.yardox.client.security.IsLoggedInGatekeeper;
import com.yardox.shared.action.SignOutAction;
import com.yardox.shared.action.SignOutResult;
import com.yardox.shared.dto.UserDto;

import javax.inject.Inject;

public class LoginPresenter extends Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy>
		implements LoginUiHandlers {
	interface MyView extends View, HasUiHandlers<LoginUiHandlers> {

		void prepareView(UserDto userDto);
	}

	@ProxyStandard
	@NameToken(NameTokens.LOGIN)
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	IsLoggedInGatekeeper gatekeeper;
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	@Inject
	LoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
                   final IsLoggedInGatekeeper gatekeeper, final DispatchAsync dispatcher, final PlaceManager placeManager) {
		super(eventBus, view, proxy, RevealType.Root);
		GWT.log("LoginPresenter");
		this.gatekeeper = gatekeeper;
		this.dispatcher = dispatcher;
		this.placeManager = placeManager;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onReset() {
		super.onReset();

		getView().prepareView(gatekeeper.getCurrentUser());
	}

	@Override
	public void onSignInClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSignInWithGoogleClick() {
		Window.Location.replace("/withGoogle");
	}

	@Override
	public void onSignOutClick() {
		dispatcher.execute(new SignOutAction(), new AsyncCallbackImpl<SignOutResult>() {
			@Override
			public void onSuccess(SignOutResult result) {
				getEventBus().fireEvent(new LoginResetEvent());
				placeManager.revealDefaultPlace();
			}
		});
	}
}