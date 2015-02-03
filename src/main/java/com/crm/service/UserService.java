package com.crm.service;

import com.crm.model.User;

/**
 * Defines data access methods for interacting with Customers
 * 
 * @author muhammad
 */
public interface UserService
{
	/**
	 * Returns a list of all customers in the database
	 * 
	 * @return		A list of all customers in the database
	 */
	public Boolean authenticate(String username);

}
