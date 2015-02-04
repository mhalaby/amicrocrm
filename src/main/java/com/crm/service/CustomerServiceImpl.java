package com.crm.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.CustomerDao;
import com.crm.model.Customer;

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
        return customerDao.getCustomerById(id);
    }

    @Override
    public void save(Customer customer) {
        customerDao.saveCustomer(customer);
        logger.info( "customerDao saved");
    }

    @Override
    public void delete(Customer customer) {
        customerDao.deleteCustomer(customer);
        logger.info( "customerDao deleted");
    }
}