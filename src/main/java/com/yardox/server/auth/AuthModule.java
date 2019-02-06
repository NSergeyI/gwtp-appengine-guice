package com.yardox.server.auth;

import com.google.inject.AbstractModule;

import javax.inject.Singleton;

public class AuthModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(CurrentAppUserProvider.class).in(Singleton.class);
	}
}
