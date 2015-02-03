package com.crm.dao;

import com.crm.model.User;

import java.util.List;

/**
 * Defines data access methods for interacting with Customers
 * 
 * @author muhammad
 */
public interface UserDao
{
	/**
	 * Returns a list of all customers in the database
	 * 
	 * @return		A list of all customers in the database
	 */
	public User getUser(String username);

}
