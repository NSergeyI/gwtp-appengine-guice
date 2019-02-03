package com.yardox.client.gin;

import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;
import com.yardox.client.application.ApplicationModule;
import com.yardox.client.place.NameTokens;
//import com.yardox.client.resources.ResourceLoader;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {

        install(new DefaultModule.Builder().tokenFormatter(RouteTokenFormatter.class).build());
//        install(new RpcDispatchAsyncModule.Builder().build());

        install(new ApplicationModule());
//        install(new LoginModule());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.HOME);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.HOME);
//        install(new DefaultModule
//                .Builder()
//                .defaultPlace(NameTokens.HOME)
//                .errorPlace(NameTokens.HOME)
//                .unauthorizedPlace(NameTokens.HOME)
//                .build());
//        install(new ApplicationModule());

//        bind(ResourceLoader.class).asEagerSingleton();
    }
}
