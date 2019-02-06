package com.yardox.server.dispatch.auth;

import com.yardox.server.auth.CurrentAppUserProvider;
import com.yardox.shared.action.SignOutAction;
import com.yardox.shared.action.SignOutResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class SignOutHandler extends AbstractAuthHandler<SignOutAction, SignOutResult> {
	@Inject
	public SignOutHandler(CurrentAppUserProvider currentAppUserProvider) {
		super(SignOutAction.class, currentAppUserProvider);
	}

	@Override
	public SignOutResult execute(SignOutAction action, ExecutionContext context) throws ActionException {
		currentAppUserProvider.set(null);
		return new SignOutResult();
	}

}
