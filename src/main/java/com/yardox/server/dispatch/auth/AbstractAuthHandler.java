package com.yardox.server.dispatch.auth;

import com.yardox.domain.AppUser;
import com.yardox.server.auth.CurrentAppUserProvider;
import com.yardox.server.dao.CustomerDao;
import com.yardox.server.dispatch.MyAbstractActionHandler;
import com.yardox.server.utils.ServerUtils;
import com.yardox.shared.dto.UserDto;
import com.gwtplatform.dispatch.rpc.shared.Action;
import com.gwtplatform.dispatch.rpc.shared.Result;
import com.gwtplatform.dispatch.shared.ActionException;

public abstract class AbstractAuthHandler<A extends Action<R>, R extends Result> extends MyAbstractActionHandler<A, R> {

	protected final CurrentAppUserProvider currentAppUserProvider;

	public AbstractAuthHandler(final Class<A> actionType, final CurrentAppUserProvider currentAppUserProvider) {
		super(actionType);

		this.currentAppUserProvider = currentAppUserProvider;
	}

	protected UserDto getCurrentUserDto(AppUser appUser) throws ActionException {
		currentAppUserProvider.set(appUser);

		return getCurrentUserDto();
	}

	protected UserDto getCurrentUserDto() throws ActionException {
		AppUser appUser = getCurrenAppUser();
		return ServerUtils.createUserDto(appUser != null, appUser);
	}

	protected static AppUser getAppUserForCustomer(Long customerId) {
		return new CustomerDao().get(customerId).getUser();
	}

	protected AppUser getCurrenAppUser() throws ActionException {
		if (currentAppUserProvider == null)
			throw new ActionException("currentAppUserProvider is null");
		AppUser appUser = currentAppUserProvider.get();
		return appUser;
	}
}