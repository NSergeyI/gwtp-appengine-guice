package com.yardox.server.dispatch.common;

import com.yardox.domain.AppUser;
import com.yardox.server.dao.AppUserDao;
import com.yardox.server.dispatch.MyAbstractActionHandler;
import com.yardox.server.utils.ServerUtils;
import com.yardox.shared.action.GetUsersAction;
import com.yardox.shared.action.GetUsersResult;
import com.yardox.shared.dto.UserDto;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUsersHandler extends MyAbstractActionHandler<GetUsersAction, GetUsersResult> {
	@Inject
	public GetUsersHandler() {
		super(GetUsersAction.class);
	}

	@Override
	public GetUsersResult execute(GetUsersAction action, ExecutionContext context) throws ActionException {
		List<AppUser> appUsers = new AppUserDao().listAll();

		List<UserDto> userDtos = new ArrayList<UserDto>(appUsers.size());

		for (AppUser appUser : appUsers) {
			userDtos.add(ServerUtils.createUserDto(null, appUser));
		}

		return new GetUsersResult(userDtos);
	}

}
