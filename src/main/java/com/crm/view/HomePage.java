package com.crm.view;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.crm.service.CustomerService;

/**
 * Homepage
 */
public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	protected CustomerService customerService;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {

        // Add the simplest type of label
        //add(new Label("message", "If you see this message wicket is properly configured and running"));
        add( new Label( "customer", customerService.getCustomers() ) );

    }
}
