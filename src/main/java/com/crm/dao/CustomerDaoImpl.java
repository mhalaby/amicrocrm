package com.crm.dao;

import java.util.List;

import com.crm.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
public class CustomerDaoImpl implements CustomerDao
{
    @Resource
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {
        return this.sessionFactory.getCurrentSession().createQuery("from com.crm.model.Customer").list();
    }

    @Override
    public List<Customer> getCustomersName() {
        return this.sessionFactory.getCurrentSession().createQuery("select c.first_name from com.crm.model.Customer c").list();
    }

    @Override
    public void saveCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().delete(customer);
    }

    @Override
    public Customer fetch(Long id) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }


}