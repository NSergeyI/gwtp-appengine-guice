package com.yardox.server.guice;

import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;
import com.yardox.server.auth.AuthServlet;
import com.yardox.server.utils.ServerUtils;

import javax.inject.Singleton;

public class MyServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        // GWT-platform commands servlet
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);

        // auth servlets
        bind(AuthServlet.class).in(Singleton.class);
        serve(ServerUtils.SIGN_IN_WITH_GOOGLE_PATH).with(AuthServlet.class);
        serve(ServerUtils.OAUTH2_CALLBACK_PATH).with(AuthServlet.class);


        // Objectify filter
        bind(ObjectifyFilter.class).in(Singleton.class);
        filter("/*").through(ObjectifyFilter.class);
    }
}

