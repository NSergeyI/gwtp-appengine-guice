package com.yardox.server.guice;

import com.google.inject.AbstractModule;
import com.yardox.server.auth.AuthModule;
import com.yardox.server.dao.objectify.OfyService;
import com.yardox.server.dispatch.MyHandlerModule;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(OfyService.class);

        install(new AuthModule());
        install(new MyHandlerModule());

    }
}
