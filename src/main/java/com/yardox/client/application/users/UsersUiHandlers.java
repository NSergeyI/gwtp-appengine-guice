package com.yardox.client.application.users;

import com.yardox.shared.dto.UserDto;
import com.gwtplatform.mvp.client.UiHandlers;

public interface UsersUiHandlers extends UiHandlers {

	void onUserDeleteUpdate(UserDto userDto);

	void onIsAdminUpdate(UserDto userDto, Boolean value);
}
