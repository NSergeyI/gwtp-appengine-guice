package com.yardox.server.dispatch.common;

import com.yardox.server.dispatch.MyAbstractActionHandler;
import com.yardox.shared.action.SetUserAdminAction;
import com.yardox.shared.action.SetUserAdminResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.yardox.domain.AppUser;
import com.yardox.server.dao.AppUserDao;
import com.yardox.server.utils.ServerUtils;

import javax.inject.Inject;

public class SetUserAdminHandler extends MyAbstractActionHandler<SetUserAdminAction, SetUserAdminResult> {
	@Inject
	public SetUserAdminHandler() {
		super(SetUserAdminAction.class);
	}

	@Override
	public SetUserAdminResult execute(SetUserAdminAction action, ExecutionContext context) throws ActionException {

		AppUserDao appUserDao = new AppUserDao();

		AppUser appUser = appUserDao.get(action.getId());

		appUser.setAdmin(action.getValue());

		return new SetUserAdminResult(ServerUtils.createUserDto(null, appUserDao.saveAndReturn(appUser)));

	}

}
