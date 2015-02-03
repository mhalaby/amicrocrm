package com.crm.service;

import com.crm.model.Customer;

import java.util.List;

public interface CustomerService
{
    public List<Customer> getCustomers();
    public Customer fetch(Long id);
    public void save(Customer customer);
    public void delete(Customer customer);


}