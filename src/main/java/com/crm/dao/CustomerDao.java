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
    /**
     * saves customer
     *@param customer
     *          customer to be saved
     */
    public void saveCustomer(Customer customer);
    /**
     * deletes customer
    *@param customer
    *          customer to be deleted
    */
    public void deleteCustomer  (Customer customer);
    /**
     * fetches customer by id
     *@param id
     *        id of a customer
     *@return Customer is fetched
     */
    Customer getCustomerById(Long id);
}