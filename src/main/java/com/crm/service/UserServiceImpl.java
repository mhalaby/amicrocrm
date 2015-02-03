package com.crm.service;

import com.crm.dao.CustomerDao;
import com.crm.dao.UserDao;
import com.crm.model.Customer;
import com.crm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.log4j.Logger;

/**
 * Defines data access methods for interacting with User
 * 
 * @author muhammad
 */
public class UserServiceImpl implements UserService
{
    private static Logger logger = Logger.getLogger(UserServiceImpl.class );
    /**
     * The user DAO class, injected by Spring
     */

    @Autowired
    protected UserDao userDao;
	/**
	 * Returns either success or fail of the user
	 * @param username
	 * @return bool
	 */
	public Boolean authenticate(String username){
        logger.info("HELLO ");
        User user = userDao.getUser(username);
        if(user == null)
            return false;
        logger.info( "userDao returned " + user );
        return true;
    };

}
