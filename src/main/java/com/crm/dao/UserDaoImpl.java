package com.crm.dao;

import com.crm.model.Customer;
import com.crm.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class UserDaoImpl implements UserDao
{
    @Resource
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private static Logger logger = Logger.getLogger(UserDaoImpl.class );

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUser(String username) {
        logger.info("DAO authentication "+username);
        Criteria cr = this.sessionFactory.getCurrentSession().createCriteria(User.class);
        cr.add(Restrictions.eq("username", username));
        return  (User)cr.list().get(0);
    }
}
