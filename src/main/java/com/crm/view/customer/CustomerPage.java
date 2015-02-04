package com.crm.view.customer;

import com.crm.view.BasePage;
import org.apache.log4j.Logger;

/**
 * Customerpage
 */
public class CustomerPage extends BasePage {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger( CustomerPage.class );


    public CustomerPage() {
        this.add(new CustomerTable("customerTable"));
    }

}