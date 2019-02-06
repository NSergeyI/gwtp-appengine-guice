package com.yardox.server.dispatch.common;

import com.yardox.server.dao.AppUserDao;
import com.yardox.server.dispatch.MyAbstractActionHandler;
import com.yardox.shared.action.DeleteUserAction;
import com.yardox.shared.action.DeleteUserResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.yardox.server.dao.AppUserDao;

import javax.inject.Inject;

public class DeleteUserHandler extends MyAbstractActionHandler<DeleteUserAction, DeleteUserResult> {
	@Inject
	public DeleteUserHandler() {
		super(DeleteUserAction.class);
	}

	@Override
	public DeleteUserResult execute(DeleteUserAction action, ExecutionContext context) throws ActionException {

		new AppUserDao().delete(action.getId());

		return new DeleteUserResult();
	}

}
