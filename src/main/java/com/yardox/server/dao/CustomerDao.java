package com.yardox.server.dao;

import com.yardox.domain.AppUser;
import com.yardox.domain.Customer;
import com.googlecode.objectify.Ref;

public class CustomerDao extends BaseDao<Customer> {

	public CustomerDao() {
		super(Customer.class);
	}

	@Override
	public Customer saveAndReturn(Customer customer) {

		AppUserDao appUserDao = new AppUserDao();
		AppUser appUser;

		if (customer.getUsers().size() == 0) {
			appUser = new AppUser();
			appUserDao.save(appUser);
			customer.getUsers().add(Ref.create(appUser));
		} else
			appUser = customer.getUsers().get(0).get();

		customer.setAdmin(customer.isAdmin());
		customer.setEmail(customer.getEmail());
		customer.setPictureURL(customer.getPictureURL());
		customer = super.saveAndReturn(customer);

		// should be updated because it can be modified in one of set methods above
		appUser.setCustomer(customer);
		// TODO all new USERS will be admins - remove after testing
		// appUser.setAdmin(true);
		if (customer.getEmail().contains("@akolchin.com"))
			appUser.setAdmin(true);
		else
			appUser.setAdmin(false);
		// ---
		appUserDao.save(appUser);

		return customer;
	}

	@Override
	public void delete(Customer customer) {
		AppUserDao appUserDao = new AppUserDao();
		appUserDao.delete(customer.getUser());
		super.delete(customer);
	}

}
