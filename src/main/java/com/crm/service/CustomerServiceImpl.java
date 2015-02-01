package com.crm.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.CustomerDao;
import com.crm.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService
{
	private static Logger logger = Logger.getLogger( CustomerServiceImpl.class );
	
	/**
	 * The customer DAO class, injected by Spring
	 */
	@Autowired
	protected CustomerDao customerDao;
    public String getCustomers()
	{
		List<Customer> customers = customerDao.getCustomers();
		logger.info( "customerDao returned " + customers.size() + " customers" );
		if( customers.size() > 0 )
		{
			return customers.get( 1 ).getEmail();
		}
		return "Hello, Spring";
	}
}
