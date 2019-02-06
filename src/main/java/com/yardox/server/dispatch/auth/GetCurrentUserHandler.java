package com.yardox.server.dispatch.auth;

import com.yardox.server.auth.CurrentAppUserProvider;
import com.yardox.shared.action.GetCurrentUserAction;
import com.yardox.shared.action.GetCurrentUserResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class GetCurrentUserHandler extends AbstractAuthHandler<GetCurrentUserAction, GetCurrentUserResult> {
	@Inject
	public GetCurrentUserHandler(CurrentAppUserProvider currentAppUserProvider) {
		super(GetCurrentUserAction.class, currentAppUserProvider);
	}

	@Override
	public GetCurrentUserResult execute(GetCurrentUserAction action, ExecutionContext context) throws ActionException {
		return new GetCurrentUserResult(getCurrentUserDto());
	}

}
