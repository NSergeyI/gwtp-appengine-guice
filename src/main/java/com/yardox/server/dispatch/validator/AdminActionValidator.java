package com.yardox.server.dispatch.validator;

import com.yardox.domain.AppUser;
import com.yardox.server.auth.CurrentAppUserProvider;
import com.gwtplatform.dispatch.rpc.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

// TODO re-implement basing on LoggedInActionValidator
public class AdminActionValidator implements ActionValidator {

	CurrentAppUserProvider currentAppUserProvider;

	@Inject
	public AdminActionValidator(final CurrentAppUserProvider currentAppUserProvider) {
		this.currentAppUserProvider = currentAppUserProvider;
	}

	@Override
	public boolean isValid(Action<? extends Result> action) throws ActionException {
		if (currentAppUserProvider == null)
			throw new ActionException("currentAppUserProvider is null");

		AppUser appUser = currentAppUserProvider.get();

		return appUser != null && appUser.isAdmin();
	}

}
