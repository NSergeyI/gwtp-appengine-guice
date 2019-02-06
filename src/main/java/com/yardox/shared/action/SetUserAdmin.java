package com.yardox.shared.action;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;
import com.yardox.shared.dto.UserDto;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class SetUserAdmin {

	@In(1)
	Long id;
	@In(2)
	Boolean value;

	@Out(1)
	UserDto userDto;

}
