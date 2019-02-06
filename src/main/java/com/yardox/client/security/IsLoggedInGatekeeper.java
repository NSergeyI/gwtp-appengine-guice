package com.yardox.client.security;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

import com.yardox.client.event.LoginAuthenticatedEvent;
import com.yardox.client.event.LoginResetEvent;
import com.yardox.shared.dto.UserDto;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@DefaultGatekeeper
public class IsLoggedInGatekeeper implements Gatekeeper {
    private final EventBus eventBus;
    private UserDto userDto;

    @Inject
    public IsLoggedInGatekeeper(final EventBus eventBus) {
        // TODO re-implement security by using approach from carstore example
        this.eventBus = eventBus;
        this.eventBus.addHandler(LoginAuthenticatedEvent.getType(),
                new LoginAuthenticatedEvent.LoginAuthenticatedHandler() {
                    @Override
                    public void onLoginAuthenticated(LoginAuthenticatedEvent event) {
                        userDto = event.getCurrentUser();
                    }
                });
        this.eventBus.addHandler(LoginResetEvent.getType(), new LoginResetEvent.LoginResetHandler() {
            @Override
            public void onLoginReset(LoginResetEvent event) {
                userDto = null;
            }
        });
    }

    @Override
    public boolean canReveal() {
        // TODO for now just use isAdmin() to check is user approved. Later add more attributes and logic.
        return (userDto != null) && userDto.isLoggedIn() && userDto.isAdmin();

    }

    public UserDto getCurrentUser() {
        return userDto;
    }
}