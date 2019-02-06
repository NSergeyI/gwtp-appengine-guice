package com.yardox.client.gin;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import com.yardox.client.dispatch.AsyncCallbackImpl;
import com.yardox.client.event.LoginAuthenticatedEvent;
import com.yardox.shared.action.GetCurrentUserAction;
import com.yardox.shared.action.GetCurrentUserResult;
import com.yardox.shared.dto.UserDto;

import javax.inject.Inject;

public class BootstrapperImpl implements Bootstrapper {
	private final PlaceManager placeManager;
	private final DispatchAsync dispatcher;

	private final UserDto userDto;

	private final EventBus eventBus;

	@Inject
	public BootstrapperImpl(final EventBus eventBus, final PlaceManager placeManager, final DispatchAsync dispatcher,
                            final UserDto userDto) {
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.userDto = userDto;

		this.eventBus = eventBus;
	}

	@Override
	public void onBootstrap() {
		getCurrentUser();
	}

	private void getCurrentUser() {

		dispatcher.execute(new GetCurrentUserAction(), new AsyncCallbackImpl<GetCurrentUserResult>() {
			@Override
			public void onSuccess(GetCurrentUserResult result) {
				onGetCurrentUserSuccess(result.getCurrentUserDto());
			}

			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				placeManager.revealCurrentPlace();
			}
		});
	}

	private void onGetCurrentUserSuccess(UserDto currentUserDto) {

		userDto.copyFrom(currentUserDto);

		eventBus.fireEvent(new LoginAuthenticatedEvent(userDto));

		placeManager.revealCurrentPlace();
		// TODO have we reveal direct to the desired place or first visit login page?

		// placeManager.revealPlace(new PlaceRequest(NameTokens.getLogin()));
	}

}
