package com.crm.dao;

import java.util.List;

import com.crm.model.Customer;

/**
 * Defines data access methods for interacting with Customers
 *
 * @author muhammad
 */
public interface CustomerDao
{
    /**
     * Returns a list of all customers in the database
     *
     * @return		A list of all customers in the database
     */
    public List<Customer> getCustomers();
    public List<Customer> getCustomersName();
    public void saveCustomer(Customer customer);
    public void deleteCustomer  (Customer customer);
    Customer fetch(Long id);
}