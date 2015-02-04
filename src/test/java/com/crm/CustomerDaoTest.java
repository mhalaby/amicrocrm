package com.crm;

import com.crm.dao.CustomerDao;
import com.crm.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import  org.springframework.util.StringUtils.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by muhammad on 04.02.15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class CustomerDaoTest {
    @Autowired
    protected CustomerDao customerDao;
    Customer customer;

    @Test
    public void getCustomerById(){
        Customer c = customerDao.getCustomerById(Long.valueOf(0));
        assertNotNull(c.getId());
    }

}
