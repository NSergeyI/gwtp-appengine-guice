package com.yardox.client.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {
	void onSignInClick();

	void onSignInWithGoogleClick();

	void onSignOutClick();
}
