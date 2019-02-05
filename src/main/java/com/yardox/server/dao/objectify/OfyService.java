package com.yardox.server.dao.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import javax.inject.Inject;

public class OfyService {
	@Inject
	public static void setObjectifyFactory(OfyFactory factory) {
		ObjectifyService.setFactory(factory);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static OfyFactory factory() {
		return (OfyFactory) ObjectifyService.factory();
	}
}
