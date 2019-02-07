package com.yardox.server.utils;

import com.yardox.domain.AppUser;
import com.yardox.domain.Customer;
import com.yardox.server.auth.CredentialManager;
import com.yardox.shared.dto.UserDto;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.Oauth2;
import com.google.appengine.api.utils.SystemProperty;
import com.gwtplatform.dispatch.shared.ActionException;

import java.io.FileInputStream;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ServerUtils {

	public static final String APPLICATION_NAME = "BECKER";

	public static final String OAUTH2_CALLBACK_PATH = "/oauth2callback";

	public static final String SIGN_IN_WITH_GOOGLE_PATH = "/withGoogle";

	public static final String PATH_FILE_PROPERTIES = "WEB-INF/resources/settings.properties";

	/**
	 * Build and return an Oauth2 service object based on given request parameters.
	 * 
	 * @param credential
	 *            User credentials.
	 * @return Drive service object that is ready to make requests, or null if there
	 *         was a problem.
	 */
	public static Oauth2 getOauth2Service(Credential credential) {
		return new Oauth2.Builder(CredentialManager.TRANSPORT, CredentialManager.JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	public static boolean isProduction() {
		return SystemProperty.environment.value() == SystemProperty.Environment.Value.Production;
	}

	public static UserDto createUserDto(Boolean isLoggedIn, AppUser appUser) throws ActionException {
		UserDto userDto = new UserDto();

		if (appUser != null) {
			Customer customer = safelyDetermineCustomer(appUser);
			userDto.setAttributes(isLoggedIn, appUser.getId(), appUser.getEmail(), appUser.isAdmin(),
					appUser.getPictureURL(), customer.getName(), customer.getDescription());
		}

		return userDto;
	}

	private static Customer safelyDetermineCustomer(AppUser appUser) throws ActionException {
		if (appUser == null)
			throw new ActionException("user provided by currentAppUserProvider is null");

		Customer customer = appUser.getCustomer();
		if (customer == null)
			throw new ActionException("Empty Customer for User with LOGIN: " + appUser.getEmail());

		return customer;
	}

	public static String getSpreadsheetsId() throws IOException {
		Properties spreadsheetsIdProp = new Properties();
		spreadsheetsIdProp.load(new FileInputStream(PATH_FILE_PROPERTIES));
		String result = spreadsheetsIdProp.getProperty("id");
		return result;
	}

	public static String setSpreadsheetsId(String id) throws IOException {
		Properties spreadsheetsIdProp = new Properties();
//		spreadsheetsIdProp.load(new FileInputStream(PATH_FILE_PROPERTIES));
		spreadsheetsIdProp.setProperty("id",id);
//		spreadsheetsIdProp.store(new FileWriter(PATH_FILE_PROPERTIES),"");
		String result = getSpreadsheetsId();
		return result;
	}
}
