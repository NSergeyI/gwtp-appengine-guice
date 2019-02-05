package com.yardox.server.dao.objectify;

import com.yardox.domain.AppUser;
import com.yardox.domain.Customer;
//import com.yardox.shared.domain.ImportLog;
import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Our version of ObjectifyFactory which integrates with Guice. You could and
 * convenience methods here too.
 *
 * @author Jeff Schnitzer
 */
@Singleton
public class OfyFactory extends ObjectifyFactory {
	private Injector injector;

	/** Register our entity types */
	@Inject
	public OfyFactory(Injector injector) {
		this.injector = injector;

		long time = System.currentTimeMillis();

		this.register(AppUser.class);
		this.register(Customer.class);
//		this.register(ImportLog.class);

		long millis = System.currentTimeMillis() - time;
		System.out.println("Registration took " + millis + " millis");
	}

	/** Use guice to make instances instead! */
	@Override
	public <T> T construct(Class<T> type) {
		return injector.getInstance(type);
	}
}