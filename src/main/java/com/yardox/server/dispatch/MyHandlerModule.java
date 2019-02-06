package com.yardox.server.dispatch;


import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.yardox.server.dispatch.auth.GetCurrentUserHandler;
import com.yardox.server.dispatch.auth.SignOutHandler;
import com.yardox.server.dispatch.common.DeleteUserHandler;
import com.yardox.server.dispatch.common.GetUsersHandler;
import com.yardox.server.dispatch.common.SetUserAdminHandler;
import com.yardox.server.dispatch.validator.AdminActionValidator;
import com.yardox.shared.action.*;

public class MyHandlerModule extends HandlerModule {
	@Override
	protected void configureHandlers() {

		// Bind Action to Action Handler
		bindHandler(GetCurrentUserAction.class, GetCurrentUserHandler.class);
		bindHandler(SignOutAction.class, SignOutHandler.class);
		
		// ADMIN action handles (with validation)
		bindHandler(GetUsersAction.class, GetUsersHandler.class, AdminActionValidator.class);
		bindHandler(DeleteUserAction.class, DeleteUserHandler.class, AdminActionValidator.class);
		bindHandler(SetUserAdminAction.class, SetUserAdminHandler.class, AdminActionValidator.class);
	}
}
