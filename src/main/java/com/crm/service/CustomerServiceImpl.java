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
    public List<Customer> getCustomers()
	{
		List<Customer> customers = customerDao.getCustomers();
		logger.info( "customerDao returned " + customers.size() + " customers" );
		return customers;
	}

    @Override
    public Customer fetch(Long id) {
        return customerDao.fetch(id);
    }

    @Override
    public void save(Customer customer) {
        customerDao.saveCustomer(customer);
        logger.info( "customerDao saved");

    }
}
